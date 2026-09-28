package com.transaccionesfinancieras.application.port.out;

import com.transaccionesfinancieras.domain.model.Transaccion;
import reactor.core.publisher.Mono;

/**
 * Puerto para interactuar con el motor antifraude externo.
 * Define el contrato que la capa de infraestructura debe implementar para evaluar
 * el potencial fraude en una transacción.
 */
public interface MotorAntifraudePort {

    /**
     * Evalúa una transacción en busca de patrones de fraude.
     *
     * @param transaccion La transacción a evaluar, incluyendo detalles como monto, origen, destino y timestamp.
     * @return Mono<Boolean> que emite true si la transacción es sospechosa de fraude, false en caso contrario.
     *         Puede emitir errores como TimeoutBuroException si el motor no responde en el tiempo esperado,
     *         o Respuesta5xxException si el motor devuelve un error del servidor.
     */
    Mono<Boolean> evaluarFraude(Transaccion transaccion);

    /**
     * Registra una transacción evaluada en el motor antifraude para mejorar futuras detecciones.
     *
     * @param transaccion La transacción evaluada, incluyendo su resultado de fraude.
     * @return Mono<Void> que completa cuando la transacción ha sido registrada.
     *         No emite valor, solo completa o emite error.
     */
    Mono<Void> registrarTransaccionEvaluada(Transaccion transaccion);
}