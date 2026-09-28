package com.transaccionesfinancieras.application.port.out;

import com.transaccionesfinancieras.domain.model.Transaccion;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el buró de riesgos externo.
 * Define el contrato que la capa de infraestructura debe implementar para evaluar
 * el riesgo crediticio asociado a una transacción.
 */
public interface BuroRiesgoPort {

    /**
     * Evalúa el riesgo crediticio de una transacción consultando el buró de riesgos.
     *
     * @param transaccion La transacción a evaluar, incluyendo datos del cliente y monto.
     * @return Mono<Double> que emite la puntuación de riesgo (0.0 a 1.0) donde 1.0 es el riesgo máximo.
     *         Puede emitir errores como TimeoutBuroException si el buró no responde en el tiempo esperado,
     *         o Respuesta5xxException si el buró devuelve un error del servidor.
     */
    Mono<Double> evaluarRiesgo(Transaccion transaccion);

    /**
     * Recupera el historial de evaluaciones de riesgo para un cliente específico.
     *
     * @param idCliente Identificador único del cliente.
     * @return Mono<String> que emite un resumen del historial de riesgo en formato JSON.
     *         Si no hay historial, emite un Mono vacío.
     */
    Mono<String> obtenerHistorialRiesgo(String idCliente);
}