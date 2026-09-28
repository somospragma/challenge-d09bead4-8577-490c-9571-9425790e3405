package com.transaccionesfinancieras.adapter.out.external;


import com.transaccionesfinancieras.domain.model.Resultado;
import com.transaccionesfinancieras.application.port.out.MotorAntifraudePort;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.model.Transaccion;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.http.HttpStatusCode;
import org.springframework.stereotype.Component;
import org.springframework.web.reactive.function.client.WebClient;
import org.springframework.web.reactive.function.client.WebClientResponseException;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;

@Component
public class MotorAntifraudeAdapter implements MotorAntifraudePort {

    private static final Logger log = LoggerFactory.getLogger(MotorAntifraudeAdapter.class);
    private static final String SERVICIO_ANTIFRAUDE = "motor-antifraude";
    private static final String ENDPOINT_EVALUAR = "/evaluar";
    private static final String ENDPOINT_REGISTRAR = "/registrar";

    private final WebClient webClient;
    private final int timeoutSegundos;
    private final int reintentos;
    private final long duracionReintentoMs;

    public MotorAntifraudeAdapter(
            @Qualifier("webClientAntifraude") WebClient webClient,
            @Qualifier("timeoutAntifraude") int timeoutSegundos,
            @Qualifier("reintentosAntifraude") int reintentos,
            @Qualifier("duracionReintentoAntifraude") long duracionReintentoMs) {
        this.webClient = webClient;
        this.timeoutSegundos = timeoutSegundos;
        this.reintentos = reintentos;
        this.duracionReintentoMs = duracionReintentoMs;
    }

    @Override
    public Mono<Boolean> evaluarFraude(Transaccion transaccion) {
        log.info("Evaluando fraude para transacción: {}", transaccion.id());
        
        return webClient
                .post()
                .uri(ENDPOINT_EVALUAR)
                .bodyValue(transaccion)
                .retrieve()
                .bodyToMono(Boolean.class)
                .timeout(Duration.ofSeconds(timeoutSegundos))
                .doOnSuccess(resultado -> log.info("Resultado evaluación fraude: {} para tx: {}", resultado, transaccion.id()))
                .doOnError(error -> log.error("Error en evaluación de fraude para tx {}: {}", transaccion.id(), error.getMessage()))
                .onErrorResume(WebClientResponseException.class, this::manejarErrorRespuesta)
                .onErrorResume(TimeoutException.class, this::manejarTimeout)
                .retryWhen(Retry.backoff(reintentos, Duration.ofMillis(duracionReintentoMs))
                        .filter(throwable -> throwable instanceof WebClientResponseException serverError 
                                && serverError.getStatusCode().is5xxServerError()))
                .defaultIfEmpty(Boolean.FALSE);
    }

    @Override
    public Mono<Void> registrarTransaccionEvaluada(Transaccion transaccion) {
        log.info("Registrando transacción evaluada: {}", transaccion.id());
        
        return webClient
                .post()
                .uri(ENDPOINT_REGISTRAR)
                .bodyValue(transaccion)
                .retrieve()
                .bodyToMono(Void.class)
                .timeout(Duration.ofSeconds(timeoutSegundos))
                .doOnSuccess(aVoid -> log.info("Transacción registrada exitosamente: {}", transaccion.id()))
                .doOnError(error -> log.error("Error al registrar tx {}: {}", transaccion.id(), error.getMessage()))
                .onErrorResume(WebClientResponseException.class, this::manejarErrorRespuesta)
                .onErrorResume(TimeoutException.class, this::manejarTimeout);
    }

    private Mono<Boolean> manejarErrorRespuesta(WebClientResponseException ex) {
        HttpStatusCode status = ex.getStatusCode();
        log.error("Respuesta de error del servicio {}: {} - {}", SERVICIO_ANTIFRAUDE, status.value(), ex.getResponseBodyAsString());
        
        if (status.is5xxServerError()) {
            return Mono.error(new Respuesta5xxException(
                    SERVICIO_ANTIFRAUDE, 
                    status.value(), 
                    ex.getMessage()));
        }
        return Mono.error(ex);
    }

    private Mono<Boolean> manejarTimeout(TimeoutException ex) {
        log.error("Timeout al conectar con servicio {}", SERVICIO_ANTIFRAUDE);
        return Mono.error(ex);
    }
}