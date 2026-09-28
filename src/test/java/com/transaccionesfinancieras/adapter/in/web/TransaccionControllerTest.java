package com.transaccionesfinancieras.adapter.in.web;

import com.transaccionesfinancieras.application.port.in.ProcesarTransaccionUseCase;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.reactive.WebFluxTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.reactive.server.WebTestClient;
import reactor.core.publisher.Mono;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;

@WebFluxTest(TransaccionController.class)
@DisplayName("Tests de integración para TransaccionController")
class TransaccionControllerTest {

    @Autowired
    private WebTestClient webTestClient;

    @MockBean
    private ProcesarTransaccionUseCase procesarTransaccionUseCase;

    private Transaccion crearTransaccionValida() {
        return new Transaccion(
            UUID.randomUUID().toString(),
            "CLIENTE-001",
            new BigDecimal("5000.00"),
            "CREDITO",
            LocalDateTime.now()
        );
    }

    private ResultadoEvaluacion crearResultadoAprobado(String idTransaccion) {
        return new ResultadoEvaluacion(
            UUID.randomUUID().toString(),
            idTransaccion,
            "APROBADA",
            0.25,
            false,
            LocalDateTime.now()
        );
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 200 cuando la transacción es aprobada")
    void procesarTransaccion_retorna200CuandoAprobada() {
        Transaccion transaccion = crearTransaccionValida();
        ResultadoEvaluacion resultado = crearResultadoAprobado(transaccion.getId());

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.just(resultado));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.id").isEqualTo(resultado.getId())
            .jsonPath("$.idTransaccion").isEqualTo(transaccion.getId())
            .jsonPath("$.estado").isEqualTo("APROBADA")
            .jsonPath("$.scoreRiesgo").isEqualTo(0.25)
            .jsonPath("$.flagFraude").isEqualTo(false);
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 400 cuando la transacción es rechazada")
    void procesarTransaccion_retorna400CuandoRechazada() {
        Transaccion transaccion = crearTransaccionValida();

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.error(new TransaccionRechazadaException("Riesgo demasiado alto")));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isBadRequest()
            .expectBody()
            .jsonPath("$.mensaje").isEqualTo("Riesgo demasiado alto");
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 504 cuando hay timeout del buró")
    void procesarTransaccion_retorna504CuandoTimeout() {
        Transaccion transaccion = crearTransaccionValida();

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.error(new TimeoutBuroException("Timeout en el servicio de riesgo")));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isEqualTo(504)
            .expectBody()
            .jsonPath("$.mensaje").exists();
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 502 cuando hay error 5xx del buró")
    void procesarTransaccion_retorna502CuandoError5xx() {
        Transaccion transaccion = crearTransaccionValida();

        when(procesarTransaccionUseCase.procesarTransaccion(any(Transaccion.class)))
            .thenReturn(Mono.error(new Respuesta5xxException("Error interno del buró")));

        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue(transaccion)
            .exchange()
            .expectStatus().isEqualTo(502)
            .expectBody()
            .jsonPath("$.mensaje").exists();
    }

    @Test
    @DisplayName("POST /api/transacciones - retorna 400 cuando el body está vacío")
    void procesarTransaccion_retorna400CuandoBodyVacio() {
        webTestClient
            .post()
            .uri("/api/transacciones")
            .contentType(MediaType.APPLICATION_JSON)
            .bodyValue("{}")
            .exchange()
            .expectStatus().isBadRequest();
    }

    @Test
    @DisplayName("GET /api/transacciones/{id} - retorna 200 cuando existe la transacción")
    void consultarEstado_retorna200CuandoExiste() {
        String idTransaccion = "TX-12345";
        ResultadoEvaluacion resultado = crearResultadoAprobado(idTransaccion);

        when(procesarTransaccionUseCase.consultarEstadoTransaccion(idTransaccion))
            .thenReturn(Mono.just(resultado));

        webTestClient
            .get()
            .uri("/api/transacciones/{id}", idTransaccion)
            .exchange()
            .expectStatus().isOk()
            .expectHeader().contentType(MediaType.APPLICATION_JSON)
            .expectBody()
            .jsonPath("$.idTransaccion").isEqualTo(idTransaccion)
            .jsonPath("$.estado").isEqualTo("APROBADA");
    }

    @Test
    @DisplayName("GET /api/transacciones/{id} - retorna 404 cuando no existe la transacción")
    void consultarEstado_retorna404CuandoNoExiste() {
        String idInexistente = "TX-INEXISTENTE";

        when(procesarTransaccionUseCase.consultarEstadoTransaccion(idInexistente))
            .thenReturn(Mono.empty());

        webTestClient
            .get()
            .uri("/api/transacciones/{id}", idInexistente)
            .exchange()
            .expectStatus().isNotFound();
    }
}