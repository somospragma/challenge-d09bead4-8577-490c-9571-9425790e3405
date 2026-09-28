package com.transaccionesfinancieras.domain.exception;

import com.transaccionesfinancieras.domain.model.Transaccion;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Map;

public class TransaccionRechazadaException extends RuntimeException {

    public enum MotivoRechazo {
        RIESGO_ALTO("Riesgo alto detectado"),
        FRAUDE_DETECTADO("Transaccion marcada como fraude"),
        MONTO_EXCEDIDO("Monto excede limite permitido"),
        CUENTA_BLOQUEADA("Cuenta bloqueada o inactiva"),
        SCORE_INSUFICIENTE("Score de riesgo insuficiente"),
        PATRON_SOSPECHOSO("Patron de comportamiento sospechoso"),
        RULE_ENGINE("Regla de negocio rechazada"),
        BLACKLIST("Entidad en lista negra"),
        VELOCIDAD_EXCESIVA("Demasiadas transacciones en periodo corto"),
        GEOLOCALIZACION_INVALIDA("Ubicacion geografica no valida");

        private final String descripcion;

        MotivoRechazo(String descripcion) {
            this.descripcion = descripcion;
        }

        public String getDescripcion() {
            return descripcion;
        }
    }

    private final String idTransaccion;
    private final MotivoRechazo motivo;
    private final BigDecimal monto;
    private final String idCliente;
    private final LocalDateTime timestamp;
    private final Double scoreRiesgo;
    private final Map<String, Object> metadatos;

    public TransaccionRechazadaException(String idTransaccion, MotivoRechazo motivo, 
                                          BigDecimal monto, String idCliente) {
        super(String.format("Transaccion %s rechazada por: %s - Monto: %s - Cliente: %s", 
                idTransaccion, motivo.getDescripcion(), monto, idCliente));
        this.idTransaccion = idTransaccion;
        this.motivo = motivo;
        this.monto = monto;
        this.idCliente = idCliente;
        this.timestamp = LocalDateTime.now();
        this.scoreRiesgo = null;
        this.metadatos = Map.of();
    }

    public TransaccionRechazadaException(String idTransaccion, MotivoRechazo motivo,
                                          BigDecimal monto, String idCliente, Double scoreRiesgo) {
        super(String.format("Transaccion %s rechazada por: %s (score: %.2f) - Monto: %s", 
                idTransaccion, motivo.getDescripcion(), scoreRiesgo, monto));
        this.idTransaccion = idTransaccion;
        this.motivo = motivo;
        this.monto = monto;
        this.idCliente = idCliente;
        this.timestamp = LocalDateTime.now();
        this.scoreRiesgo = scoreRiesgo;
        this.metadatos = Map.of("scoreRiesgo", scoreRiesgo);
    }

    public TransaccionRechazadaException(Transaccion transaccion, MotivoRechazo motivo, Double scoreRiesgo) {
        super(String.format("Transaccion %s rechazada por: %s", 
                transaccion.getId(), motivo.getDescripcion()));
        this.idTransaccion = transaccion.getId();
        this.motivo = motivo;
        this.monto = transaccion.getMonto();
        this.idCliente = transaccion.getIdCliente();
        this.timestamp = LocalDateTime.now();
        this.scoreRiesgo = scoreRiesgo;
        this.metadatos = Map.of("scoreRiesgo", scoreRiesgo != null ? scoreRiesgo : 0.0);
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public MotivoRechazo getMotivo() {
        return motivo;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public LocalDateTime getTimestamp() {
        return timestamp;
    }

    public Double getScoreRiesgo() {
        return scoreRiesgo;
    }

    public Map<String, Object> getMetadatos() {
        return metadatos;
    }

    public boolean esPorRiesgo() {
        return motivo == MotivoRechazo.RIESGO_ALTO || 
               motivo == MotivoRechazo.SCORE_INSUFICIENTE ||
               motivo == MotivoRechazo.PATRON_SOSPECHOSO;
    }

    public boolean esPorFraude() {
        return motivo == MotivoRechazo.FRAUDE_DETECTADO ||
               motivo == MotivoRechazo.BLACKLIST ||
               motivo == MotivoRechazo.VELOCIDAD_EXCESIVA;
    }

    public String getCodigoError() {
        return "TXN_" + motivo.name();
    }
}