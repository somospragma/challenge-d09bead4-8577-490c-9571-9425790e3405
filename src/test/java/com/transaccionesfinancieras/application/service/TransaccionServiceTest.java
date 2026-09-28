package com.transaccionesfinancieras.application.service;

import com.transaccionesfinancieras.application.port.out.BuroRiesgoPort;
import com.transaccionesfinancieras.application.port.out.MotorAntifraudePort;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import reactor.core.publisher.Mono;
import reactor.test.StepVerifier;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
@DisplayName("Tests unitarios para TransaccionService")
class TransaccionServiceTest {

    @Mock
    private BuroRiesgoPort burOriesgoPort;

    @Mock
    private MotorAntifraudePort motorAntifraudePort;

    private TransaccionService transaccionService;

    private Transaccion transaccionValida;
    private ResultadoEvaluacion resultadoAprobado;
    private ResultadoEvaluacion resultadoRechazado;

    @BeforeEach
    void setUp() {
        transaccionService = new TransaccionService(burOriesgoPort, motorAntifraudePort);

        transaccionValida = new Transaccion(
            UUID.randomUUID().toString(),
            "CLIENTE-001",
            new BigDecimal("5000.00"),
            "CREDITO",
            LocalDateTime.now()
        );

        resultadoAprobado = new ResultadoEvaluacion(
            UUID.randomUUID().toString(),
            transaccionValida.getId(),
            "APROBADA",
            0.25,
            false,
            LocalDateTime.now()
        );

        resultadoRechazado = new ResultadoEvaluacion(
            UUID.randomUUID().toString(),
            transaccionValida.getId(),
            "RECHAZADA",
            0.85,
            true,
            LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("procesarTransaccion - flujo exitoso con bajo riesgo")
    void procesarTransaccion_flujoExitoso() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.just(0.25));
        when(motorAntifraudePort.evaluarFraude(any(Transaccion.class)))
            .thenReturn(Mono.just(false));
        when(motorAntifraudePort.registrarTransaccionEvaluada(any(Transaccion.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectNextMatches(resultado -> 
                resultado.getEstado().equals("APROBADA") && 
                resultado.getScoreRiesgo() == 0.25 &&
                !resultado.isFlagFraude()
            )
            .verifyComplete();

        verify(burOriesgoPort, times(1)).evaluarRiesgo(any(Transaccion.class));
        verify(motorAntifraudePort, times(1)).evaluarFraude(any(Transaccion.class));
        verify(motorAntifraudePort, times(1)).registrarTransaccionEvaluada(any(Transaccion.class));
    }

    @Test
    @DisplayName("procesarTransaccion - transaccion rechazada por alto riesgo")
    void procesarTransaccion_rechazadaPorRiesgo() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.just(0.85));
        when(motorAntifraudePort.evaluarFraude(any(Transaccion.class)))
            .thenReturn(Mono.just(false));
        when(motorAntifraudePort.registrarTransaccionEvaluada(any(Transaccion.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(TransaccionRechazadaException.class)
            .verify();
    }

    @Test
    @DisplayName("procesarTransaccion - transaccion rechazada por fraude detectado")
    void procesarTransaccion_rechazadaPorFraude() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.just(0.25));
        when(motorAntifraudePort.evaluarFraude(any(Transaccion.class)))
            .thenReturn(Mono.just(true));
        when(motorAntifraudePort.registrarTransaccionEvaluada(any(Transaccion.class)))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(TransaccionRechazadaException.class)
            .verify();
    }

    @Test
    @DisplayName("procesarTransaccion - timeout del buró de riesgo")
    void procesarTransaccion_timeoutBuro() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.error(new TimeoutBuroException("Timeout al evaluar riesgo")));

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(TimeoutBuroException.class)
            .verify();
    }

    @Test
    @DisplayName("procesarTransaccion - error 5xx del buró de riesgo")
    void procesarTransaccion_error5xxBuro() {
        when(burOriesgoPort.evaluarRiesgo(any(Transaccion.class)))
            .thenReturn(Mono.error(new Respuesta5xxException("Error interno del buró")));

        StepVerifier.create(transaccionService.procesarTransaccion(transaccionValida))
            .expectError(Respuesta5xxException.class)
            .verify();
    }

    @Test
    @DisplayName("consultarEstadoTransaccion - retorna estado guardado")
    void consultarEstadoTransaccion_existe() {
        String idTransaccion = transaccionValida.getId();

        when(burOriesgoPort.obtenerHistorialRiesgo(idTransaccion))
            .thenReturn(Mono.just("Historial: bajo riesgo"));

        StepVerifier.create(transaccionService.consultarEstadoTransaccion(idTransaccion))
            .expectNextMatches(resultado -> 
                resultado.getIdTransaccion().equals(idTransaccion)
            )
            .verifyComplete();
    }

    @Test
    @DisplayName("consultarEstadoTransaccion - transaccion no encontrada")
    void consultarEstadoTransaccion_noEncontrada() {
        String idInexistente = "TX-INEXISTENTE";

        when(burOriesgoPort.obtenerHistorialRiesgo(idInexistente))
            .thenReturn(Mono.empty());

        StepVerifier.create(transaccionService.consultarEstadoTransaccion(idInexistente))
            .verifyComplete();
    }
}