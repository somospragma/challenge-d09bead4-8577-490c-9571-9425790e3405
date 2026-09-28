package com.transaccionesfinancieras;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Hooks;

@SpringBootApplication
public class Application {
    private final ApplicationProperties properties;

    public Application(ApplicationProperties properties) {
        this.properties = properties;
        validateProperties();
    }

    public static void main(String[] args) {
        Hooks.onOperatorDebug();
        SpringApplication.run(Application.class, args);
    }

    @Bean
    public WebClient webClient(WebClient.Builder builder) {
        return builder
                .baseUrl(properties.getBuroRiesgoBaseUrl())
                .defaultHeader("Content-Type", "application/json")
                .build();
    }

    private void validateProperties() {
        if (properties.getBuroRiesgoBaseUrl() == null || properties.getBuroRiesgoBaseUrl().isBlank()) {
            throw new IllegalStateException("La URL base del buró de riesgos no puede estar vacía");
        }
        if (properties.getBuroRiesgoTimeout() <= 0) {
            throw new IllegalStateException("El timeout del buró de riesgos debe ser positivo");
        }
    }

    // Clase interna para propiedades de configuración
    public static class ApplicationProperties {
        private String buroRiesgoBaseUrl;
        private int buroRiesgoTimeout;

        public String getBuroRiesgoBaseUrl() {
            return buroRiesgoBaseUrl;
        }

        public void setBuroRiesgoBaseUrl(String buroRiesgoBaseUrl) {
            this.buroRiesgoBaseUrl = buroRiesgoBaseUrl;
        }

        public int getBuroRiesgoTimeout() {
            return buroRiesgoTimeout;
        }

        public void setBuroRiesgoTimeout(int buroRiesgoTimeout) {
            this.buroRiesgoTimeout = buroRiesgoTimeout;
        }
    }
}