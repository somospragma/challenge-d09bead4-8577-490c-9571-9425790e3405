package com.transaccionesfinancieras.application.port.in;

import com.transaccionesfinancieras.domain.model.Transaccion;
import com.transaccionesfinancieras.domain.model.ResultadoEvaluacion;
import reactor.core.publisher.Mono;

/**
 * Caso de uso para procesar transacciones financieras.
 * Define la interfaz que el dominio expone para procesar una transacción,
 * incluyendo la evaluación de riesgos y antifraude de manera reactiva.
 */
public interface ProcesarTransaccionUseCase {

    /**
     * Procesa una transacción financiera evaluando su riesgo y potencial fraude.
     *
     * @param transaccion La transacción a procesar, incluyendo detalles como monto, origen y destino.
     * @return Mono<ResultadoEvaluacion> que emite el resultado de la evaluación una vez completada.
     *         El Mono puede emitir errores tipados como TimeoutBuroException, Respuesta5xxException
     *         o TransaccionRechazadaException dependiendo del escenario.
     */
    Mono<ResultadoEvaluacion> procesarTransaccion(Transaccion transaccion);

    /**
     * Consulta el estado actual de una transacción previamente procesada.
     *
     * @param idTransaccion Identificador único de la transacción.
     * @return Mono<ResultadoEvaluacion> que emite el estado actual de la transacción.
     *         Si la transacción no existe, emite un Mono vacío.
     */
    Mono<ResultadoEvaluacion> consultarEstadoTransaccion(String idTransaccion);
}