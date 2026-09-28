package com.transaccionesfinancieras.adapter.out.external;

import com.transaccionesfinancieras.application.port.out.BuroRiesgoPort;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.model.Transaccion;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import io.github.resilience4j.retry.annotation.Retry;
import io.github.resilience4j.bulkhead.annotation.Bulkhead;
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
import java.util.Map;

@Component
public class BuroRiesgoAdapter implements BuroRiesgoPort {

    private static final Logger log = LoggerFactory.getLogger(BuroRiesgoAdapter.class);
    private static final String BURO_RIESGO_BASE_URL = "http://localhost:8081/api/buro-riesgo";
    private static final Duration TIMEOUT = Duration.ofSeconds(3);

    private final WebClient webClient;

    public BuroRiesgoAdapter(@Qualifier("buroRiesgoWebClient") WebClient webClient) {
        this.webClient = webClient;
    }

    @Override
    @CircuitBreaker(name = "buroRiesgoCircuitBreaker", fallbackMethod = "evaluarRiesgoFallback")
    @Retry(name = "buroRiesgoRetry")
    @Bulkhead(name = "buroRiesgoBulkhead")
    public Mono<Double> evaluarRiesgo(Transaccion transaccion) {
        log.debug("Evaluando riesgo para cliente: {} con monto: {}", transaccion.idCliente(), transaccion.monto());
        
        return webClient
                .post()
                .uri("/evaluar")
                .bodyValue(Map.of(
                        "idCliente", transaccion.idCliente(),
                        "monto", transaccion.monto(),
                        "tipoTransaccion", transaccion.tipoTransaccion()
                ))
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("Respuesta 5xx del buró de riesgo: {}", response.statusCode());
                    return Mono.error(new Respuesta5xxException("Error del servidor del buró de riesgo"));
                })
                .bodyToMono(Map.class)
                .timeout(TIMEOUT)
                .map(response -> {
                    Object riesgo = response.get("nivelRiesgo");
                    log.info("Riesgo evaluado para cliente {}: {}", transaccion.idCliente(), riesgo);
                    if (riesgo instanceof Number) {
                        return ((Number) riesgo).doubleValue();
                    }
                    return parseDoubleOrDefault(riesgo.toString(), 0.5);
                })
                .doOnError(WebClientResponseException.NotFound.class, e -> {
                    log.warn("Cliente {} no encontrado en buró de riesgo", transaccion.idCliente());
                })
                .onErrorResume(WebClientResponseException.class, e -> {
                    log.error("Error de cliente web al consultar buró de riesgo: {}", e.getMessage());
                    if (e.getStatusCode().is5xxServerError()) {
                        return Mono.error(new Respuesta5xxException("Error del servidor del buró de riesgo"));
                    }
                    return Mono.just(0.5);
                });
    }

    @Override
    @CircuitBreaker(name = "buroRiesgoCircuitBreaker", fallbackMethod = "obtenerHistorialFallback")
    @Retry(name = "buroRiesgoRetry")
    @Bulkhead(name = "buroRiesgoBulkhead")
    public Mono<String> obtenerHistorialRiesgo(String idCliente) {
        log.debug("Obteniendo historial de riesgo para cliente: {}", idCliente);
        
        return webClient
                .get()
                .uri("/historial/{idCliente}", idCliente)
                .retrieve()
                .onStatus(HttpStatusCode::is5xxServerError, response -> {
                    log.error("Respuesta 5xx al obtener historial: {}", response.statusCode());
                    return Mono.error(new Respuesta5xxException("Error del servidor del buró de riesgo"));
                })
                .bodyToMono(Map.class)
                .timeout(TIMEOUT)
                .map(response -> {
                    Object historial = response.get("historial");
                    return historial != null ? historial.toString() : "SIN_HISTORIAL";
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error al obtener historial de riesgo para cliente: {}", idCliente, e);
                    return Mono.just("ERROR_OBTENIENDO_HISTORIAL");
                });
    }

    private Mono<Double> evaluarRiesgoFallback(Transaccion transaccion, Throwable e) {
        log.warn("Fallback activado para evaluarRiesgo. Cliente: {}. Error: {}", transaccion.idCliente(), e.getMessage());
        if (e instanceof TimeoutBuroException || e.getCause() instanceof java.util.concurrent.TimeoutException) {
            log.error("Timeout en el fallback de buró de riesgo");
            return Mono.error(new TimeoutBuroException("Timeout al evaluar riesgo (fallback)"));
        }
        return Mono.just(0.5);
    }

    private Mono<String> obtenerHistorialFallback(String idCliente, Throwable e) {
        log.warn("Fallback activado para obtenerHistorialRiesgo. Cliente: {}. Error: {}", idCliente, e.getMessage());
        return Mono.just("HISTORIAL_NO_DISPONIBLE");
    }

    private double parseDoubleOrDefault(String value, double defaultValue) {
        try {
            return Double.parseDouble(value);
        } catch (NumberFormatException e) {
            log.warn("No se pudo parsear el valor '{}' a double, usando valor por defecto: {}", value, defaultValue);
            return defaultValue;
        }
    }
}