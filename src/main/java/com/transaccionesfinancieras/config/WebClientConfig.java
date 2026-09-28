package com.transaccionesfinancieras.config;

import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import io.github.resilience4j.circuitbreaker.CircuitBreakerConfig;
import io.github.resilience4j.circuitbreaker.CircuitBreakerRegistry;
import io.github.resilience4j.retry.RetryConfig;
import io.github.resilience4j.retry.RetryRegistry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.HttpStatusCode;
import org.springframework.web.reactive.function.client.ClientResponse;
import org.springframework.web.reactive.function.client.ExchangeFilterFunction;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Mono;
import reactor.util.retry.Retry;

import java.time.Duration;
import java.util.function.Function;

@Configuration
public class WebClientConfig {

    private static final Logger log = LoggerFactory.getLogger(WebClientConfig.class);
    private static final int TIMEOUT_CONNECT_MS = 3000;
    private static final int TIMEOUT_RESPONSE_MS = 5000;
    private static final int MAX_REINTENTOS = 3;
    private static final Duration DURACION_REINTENTO = Duration.ofMillis(500);

    @Bean
    public WebClient webClientCoreBancario(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8080")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("Accept", "application/json")
                .filter(logRequest())
                .filter(logResponse())
                .filter(errorHandler())
                .clientConnector(new reactor.netty.http.client.HttpClient()
                        .responseTimeout(Duration.ofMillis(TIMEOUT_RESPONSE_MS))
                        .connectTimeout(Duration.ofMillis(TIMEOUT_CONNECT_MS)))
                .build();
    }

    @Bean
    public WebClient webClientBuroRiesgo(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8081")
                .defaultHeader("Content-Type", "application/json")
                .defaultHeader("X-Service", "transacciones-financieras")
                .filter(logRequest())
                .filter(errorHandlerBuro())
                .clientConnector(new reactor.netty.http.client.HttpClient()
                        .responseTimeout(Duration.ofMillis(TIMEOUT_RESPONSE_MS))
                        .connectTimeout(Duration.ofMillis(TIMEOUT_CONNECT_MS)))
                .build();
    }

    @Bean
    public WebClient webClientAntifraude(WebClient.Builder builder) {
        return builder
                .baseUrl("http://localhost:8082")
                .defaultHeader("Content-Type", "application/json")
                .filter(logRequest())
                .filter(errorHandler())
                .clientConnector(new reactor.netty.http.client.HttpClient()
                        .responseTimeout(Duration.ofMillis(TIMEOUT_RESPONSE_MS))
                        .connectTimeout(Duration.ofMillis(TIMEOUT_CONNECT_MS)))
                .build();
    }

    private ExchangeFilterFunction logRequest() {
        return ExchangeFilterFunction.ofRequestProcessor(clientRequest -> {
            log.debug("Request: {} {}", clientRequest.method(), clientRequest.url());
            clientRequest.headers().forEach((name, values) -> 
                    log.trace("Header {}: {}", name, values));
            return Mono.just(clientRequest);
        });
    }

    private ExchangeFilterFunction logResponse() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            log.debug("Response status: {}", clientResponse.statusCode());
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction errorHandler() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            if (clientResponse.statusCode().is5xxServerError()) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            String mensaje = String.format("Error %d del core: %s", 
                                    clientResponse.statusCode().value(), body);
                            log.error("Error 5xx recibido del servicio externo: {}", mensaje);
                            return Mono.error(new Respuesta5xxException(
                                    clientResponse.statusCode().value(),
                                    mensaje,
                                    body,
                                    clientResponse.url().toString(),
                                    obtenerHeaders(clientResponse)));
                        });
            }
            if (clientResponse.statusCode().value() == 429) {
                String retryAfter = clientResponse.headers().asHttpHeaders().getFirst("Retry-After");
                log.warn("Rate limit excedido. Retry-After: {}", retryAfter);
            }
            return Mono.just(clientResponse);
        });
    }

    private ExchangeFilterFunction errorHandlerBuro() {
        return ExchangeFilterFunction.ofResponseProcessor(clientResponse -> {
            int statusCode = clientResponse.statusCode().value();
            if (statusCode >= 500) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            if (body.toLowerCase().contains("timeout") || 
                                body.toLowerCase().contains("deadline")) {
                                log.error("Timeout detectado en el buró de riesgo");
                                return Mono.error(new TimeoutBuroException(
                                        "Timeout en el buró de riesgo", 
                                        clientResponse.url().toString()));
                            }
                            log.error("Error {} del buró de riesgo: {}", statusCode, body);
                            return Mono.error(new Respuesta5xxException(
                                    statusCode, body, clientResponse.url().toString()));
                        });
            }
            if (statusCode >= 400 && statusCode < 500) {
                return clientResponse.bodyToMono(String.class)
                        .flatMap(body -> {
                            log.warn("Error {} del buró de riesgo: {}", statusCode, body);
                            return Mono.error(new Respuesta5xxException(
                                    statusCode, body, clientResponse.url().toString()));
                        });
            }
            return Mono.just(clientResponse);
        });
    }

    private java.util.Map<String, String> obtenerHeaders(ClientResponse response) {
        java.util.Map<String, String> headers = new java.util.HashMap<>();
        response.headers().asHttpHeaders().forEach((key, values) -> 
                headers.put(key, String.join(", ", values)));
        return headers;
    }

    @Bean
    public CircuitBreakerRegistry circuitBreakerRegistry() {
        CircuitBreakerConfig config = CircuitBreakerConfig.custom()
                .failureRateThreshold(50)
                .waitDurationInOpenState(Duration.ofSeconds(30))
                .slidingWindowSize(10)
                .minimumNumberOfCalls(5)
                .permittedNumberOfCallsInHalfOpenState(3)
                .automaticTransitionFromOpenToHalfOpenEnabled(true)
                .ignoreExceptions(TimeoutBuroException.class)
                .build();
        return CircuitBreakerRegistry.of(config);
    }

    @Bean
    public RetryRegistry retryRegistry() {
        RetryConfig config = RetryConfig.custom()
                .maxAttempts(MAX_REINTENTOS)
                .waitDuration(DURACION_REINTENTO)
                .retryExceptions(TimeoutBuroException.class, 
                                org.springframework.web.reactive.function.client.WebClientRequestException.class)
                .ignoreExceptions(Respuesta5xxException.class)
                .build();
        return RetryRegistry.of(config);
    }

    public static class RetrySpec {
        public static Retry withDefaultBackoff() {
            return Retry.backoff(MAX_REINTENTOS, DURACION_REINTENTO)
                    .filter(e -> e instanceof TimeoutBuroException || 
                                 e instanceof org.springframework.web.reactive.function.client.WebClientRequestException);
        }

        public static Retry withExponentialBackoff(int maxAttempts, Duration initial) {
            return Retry.backoff(maxAttempts, initial)
                    .filter(e -> e instanceof TimeoutBuroException ||
                                 e instanceof org.springframework.web.reactive.function.client.WebClientRequestException)
                    .doBeforeRetry(signal -> 
                            log.warn("Reintentando operación. Intento {} por: {}", 
                                    signal.totalRetries() + 1, signal.failure().getMessage()));
        }
    }
}