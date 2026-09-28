package com.transaccionesfinancieras.adapter.in.web;

import com.transaccionesfinancieras.application.port.in.ProcesarTransaccionUseCase;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import com.transaccionesfinancieras.domain.model.Transaccion;
import com.transaccionesfinancieras.domain.exception.TransaccionRechazadaException;
import com.transaccionesfinancieras.domain.exception.TimeoutBuroException;
import com.transaccionesfinancieras.domain.exception.Respuesta5xxException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Mono;

import java.util.Map;

@RestController
@RequestMapping("/api/v1/transacciones")
public class TransaccionController {

    private static final Logger log = LoggerFactory.getLogger(TransaccionController.class);
    private final ProcesarTransaccionUseCase procesarTransaccionUseCase;

    public TransaccionController(ProcesarTransaccionUseCase procesarTransaccionUseCase) {
        this.procesarTransaccionUseCase = procesarTransaccionUseCase;
    }

    @PostMapping("/procesar")
    public Mono<ResponseEntity<ResultadoEvaluacion>> procesarTransaccion(@RequestBody Transaccion transaccion) {
        log.info("Recibida solicitud de procesamiento para transaccion: {}", transaccion.id());
        
        return procesarTransaccionUseCase.procesarTransaccion(transaccion)
                .map(resultado -> {
                    log.info("Transaccion {} procesada con estado: {}", transaccion.id(), resultado.estado());
                    if ("APROBADA".equals(resultado.estado())) {
                        return ResponseEntity.ok(resultado);
                    } else {
                        return ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).body(resultado);
                    }
                })
                .onErrorResume(TransaccionRechazadaException.class, e -> {
                    log.warn("Transaccion rechazada por regla de negocio: {}", e.getMessage());
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_REQUEST)
                            .body(ResultadoEvaluacion.error(transaccion.id(), e.getMessage())));
                })
                .onErrorResume(TimeoutBuroException.class, e -> {
                    log.error("Timeout al consultar buró de riesgo para transaccion: {}", transaccion.id());
                    return Mono.just(ResponseEntity.status(HttpStatus.GATEWAY_TIMEOUT)
                            .body(ResultadoEvaluacion.error(transaccion.id(), "Timeout del servicio de riesgo")));
                })
                .onErrorResume(Respuesta5xxException.class, e -> {
                    log.error("Error 5xx del servicio de riesgo para transaccion: {}", transaccion.id());
                    return Mono.just(ResponseEntity.status(HttpStatus.BAD_GATEWAY)
                            .body(ResultadoEvaluacion.error(transaccion.id(), "Error en el servicio de riesgo")));
                })
                .onErrorResume(Exception.class, e -> {
                    log.error("Error inesperado procesando transaccion: {}", transaccion.id(), e);
                    return Mono.just(ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                            .body(ResultadoEvaluacion.error(transaccion.id(), "Error interno del servidor")));
                });
    }

    @GetMapping("/{idTransaccion}")
    public Mono<ResponseEntity<ResultadoEvaluacion>> consultarEstado(@PathVariable String idTransaccion) {
        log.info("Consultando estado de transaccion: {}", idTransaccion);
        
        return procesarTransaccionUseCase.consultarEstadoTransaccion(idTransaccion)
                .map(resultado -> ResponseEntity.ok(resultado))
                .onErrorResume(Exception.class, e -> {
                    log.error("Error consultando estado de transaccion: {}", idTransaccion, e);
                    return Mono.just(ResponseEntity.status(HttpStatus.NOT_FOUND)
                            .body(ResultadoEvaluacion.error(idTransaccion, "Transaccion no encontrada")));
                });
    }

    @GetMapping("/health")
    public Mono<ResponseEntity<Map<String, String>>> healthCheck() {
        return Mono.just(ResponseEntity.ok(Map.of("status", "UP", "service", "transacciones-financieras")));
    }
}