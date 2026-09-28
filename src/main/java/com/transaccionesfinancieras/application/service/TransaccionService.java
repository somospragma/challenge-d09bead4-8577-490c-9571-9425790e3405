package com.transaccionesfinancieras.application.service;

import com.transaccionesfinanciales.application.port.in.ProcesarTransaccionUseCase;
import com.transaccionesfinancieras.application.port.out.BuroRiesgoPort;
import com.transaccionesfinancieras.application.port.out.MotorAntifraudePort;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Mono;
import reactor.util.function.Tuple2;
import reactor.util.function.Tuples;

import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
public class TransaccionService implements ProcesarTransaccionUseCase {

    private static final Logger log = LoggerFactory.getLogger(TransaccionService.class);
    private static final double UMBRAL_RIESGO_ALTO = 0.75;
    private static final double UMBRAL_RIESGO_MEDIO = 0.40;
    private static final String ESTADO_APROBADA = "APROBADA";
    private static final String ESTADO_RECHAZADA = "RECHAZADA";
    private static final String ESTADO_PENDIENTE = "PENDIENTE";
    private static final String ESTADO_FALLIDA = "FALLIDA";

    private final BuroRiesgoPort buróRiesgoPort;
    private final MotorAntifraudePort motorAntifraudePort;

    public TransaccionService(BuroRiesgoPort buróRiesgoPort, MotorAntifraudePort motorAntifraudePort) {
        this.buróRiesgoPort = buróRiesgoPort;
        this.motorAntifraudePort = motorAntifraudePort;
    }

    @Override
    @CircuitBreaker(name = "transaccionCircuitBreaker", fallbackMethod = "procesarTransaccionFallback")
    @Retry(name = "transaccionRetry")
    public Mono<ResultadoEvaluacion> procesarTransaccion(Transaccion transaccion) {
        log.info("Iniciando procesamiento de transacción: {}", transaccion.id());
        
        String idTransaccion = transaccion.id() != null ? transaccion.id() : UUID.randomUUID().toString();
        Transaccion transaccionConId = new Transaccion(
                idTransaccion,
                transaccion.idCliente(),
                transaccion.monto(),
                transaccion.moneda(),
                transaccion.tipo(),
                transaccion.descripcion(),
                LocalDateTime.now()
        );

        return evaluarRiesgoYFraude(transaccionConId)
                .flatMap(this::determinarResultado)
                .doOnSuccess(resultado -> log.info("Transacción {} procesada con estado: {}", idTransaccion, resultado.estado()))
                .doOnError(error -> log.error("Error procesando transacción {}: {}", idTransaccion, error.getMessage()));
    }

    private Mono<Tuple2<Double, Boolean>> evaluarRiesgoYFraude(Transaccion transaccion) {
        Mono<Double> evaluacionRiesgo = buróRiesgoPort.evaluarRiesgo(transaccion)
                .doOnNext(riesgo -> log.debug("Riesgo evaluado para tx {}: {}", transaccion.id(), riesgo));

        Mono<Boolean> evaluacionFraude = motorAntifraudePort.evaluarFraude(transaccion)
                .doOnNext(fraude -> log.debug("Fraude evaluado para tx {}: {}", transaccion.id(), fraude));

        return Mono.zip(evaluacionRiesgo, evaluacionFraude)
                .timeout(Duration.ofSeconds(3))
                .switchIfEmpty(Mono.error(new TimeoutBuroException(transaccion.id())));
    }

    private Mono<ResultadoEvaluacion> determinarResultado(Tuple2<Double, Boolean> evaluaciones) {
        Double riesgo = evaluations.getT1();
        Boolean esFraude = evaluations.getT2();

        if (Boolean.TRUE.equals(esFraude)) {
            return construirResultado(ESTADO_RECHAZADA, "Transacción rechazada por detección de fraude", riesgo, esFraude);
        }

        if (riesgo >= UMBRAL_RIESGO_ALTO) {
            return construirResultado(ESTADO_RECHAZADA, "Riesgo demasiado alto para aprobar la transacción", riesgo, esFraude);
        }

        if (riesgo >= UMBRAL_RIESGO_MEDIO) {
            return construirResultado(ESTADO_PENDIENTE, "Transacción requiere revisión manual", riesgo, esFraude);
        }

        return construirResultado(ESTADO_APROBADA, "Transacción aprobada", riesgo, esFraude);
    }

    private Mono<ResultadoEvaluacion> construirResultado(String estado, String mensaje, Double riesgo, Boolean esFraude) {
        ResultadoEvaluacion resultado = new ResultadoEvaluacion(
                UUID.randomUUID().toString(),
                estado,
                mensaje,
                riesgo,
                esFraude,
                LocalDateTime.now()
        );
        return Mono.just(resultado);
    }

    private Mono<ResultadoEvaluacion> procesarTransaccionFallback(Transaccion transaccion, Throwable error) {
        log.warn("Fallback activado para transacción {}: {}", transaccion.id(), error.getMessage());
        
        String estado fallback;
        String mensaje;
        
        if (error instanceof TimeoutBuroException) {
            estado = ESTADO_PENDIENTE;
            mensaje = "Transacción en revisión por timeout del buró de riesgo";
        } else if (error instanceof Respuesta5xxException) {
            estado = ESTADO_PENDIENTE;
            mensaje = "Transacción en revisión por error en servicio externo";
        } else if (error instanceof TransaccionRechazadaException) {
            estado = ESTADO_RECHAZADA;
            mensaje = error.getMessage();
        } else {
            estado = ESTADO_FALLIDA;
            mensaje = "Error interno al procesar la transacción";
        }

        ResultadoEvaluacion resultadoFallback = new ResultadoEvaluacion(
                transaccion.id() != null ? transaccion.id() : UUID.randomUUID().toString(),
                estado,
                mensaje,
                -1.0,
                false,
                LocalDateTime.now()
        );

        return Mono.just(resultadoFallback);
    }

    @Override
    public Mono<ResultadoEvaluacion> consultarEstadoTransaccion(String idTransaccion) {
        log.info("Consultando estado de transacción: {}", idTransaccion);
        return buróRiesgoPort.obtenerHistorialRiesgo(idTransaccion)
                .flatMap(historial -> {
                    ResultadoEvaluacion resultado = new ResultadoEvaluacion(
                            idTransaccion,
                            ESTADO_PENDIENTE,
                            "Estado consultado desde historial",
                            0.0,
                            false,
                            LocalDateTime.now()
                    );
                    return Mono.just(resultado);
                })
                .switchIfEmpty(Mono.error(new TransaccionRechazadaException(
                        "Transacción no encontrada: " + idTransaccion)));
    }
}