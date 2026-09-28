package com.transaccionesfinancieras.domain.model;

import java.time.LocalDateTime;
import java.util.List;

public class ResultadoEvaluacion {
    private final String idTransaccion;
    private final boolean aprobada;
    private final String codigoResultado;
    private final String mensaje;
    private final double puntuacionRiesgo;
    private final boolean fraudeDetectado;
    private final String idEvaluadorRiesgo;
    private final String idEvaluadorFraude;
    private final LocalDateTime fechaEvaluacion;
    private final List<String> razonesRechazo;
    private final double montoAprobado;
    private final String nivelRiesgo;

    public ResultadoEvaluacion(String idTransaccion, boolean aprobada, String codigoResultado,
                                String mensaje, double puntuacionRiesgo, boolean fraudeDetectado,
                                String idEvaluadorRiesgo, String idEvaluadorFraude,
                                LocalDateTime fechaEvaluacion, List<String> razonesRechazo,
                                double montoAprobado, String nivelRiesgo) {
        this.idTransaccion = idTransaccion;
        this.aprobada = aprobada;
        this.codigoResultado = codigoResultado;
        this.mensaje = mensaje;
        this.puntuacionRiesgo = puntuacionRiesgo;
        this.fraudeDetectado = fraudeDetectado;
        this.idEvaluadorRiesgo = idEvaluadorRiesgo;
        this.idEvaluadorFraude = idEvaluadorFraude;
        this.fechaEvaluacion = fechaEvaluacion != null ? fechaEvaluacion : LocalDateTime.now();
        this.razonesRechazo = razonesRechazo;
        this.montoAprobado = montoAprobado;
        this.nivelRiesgo = nivelRiesgo;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public boolean isAprobada() {
        return aprobada;
    }

    public String getCodigoResultado() {
        return codigoResultado;
    }

    public String getMensaje() {
        return mensaje;
    }

    public double getPuntuacionRiesgo() {
        return puntuacionRiesgo;
    }

    public boolean isFraudeDetectado() {
        return fraudeDetectado;
    }

    public String getIdEvaluadorRiesgo() {
        return idEvaluadorRiesgo;
    }

    public String getIdEvaluadorFraude() {
        return idEvaluadorFraude;
    }

    public LocalDateTime getFechaEvaluacion() {
        return fechaEvaluacion;
    }

    public List<String> getRazonesRechazo() {
        return razonesRechazo;
    }

    public double getMontoAprobado() {
        return montoAprobado;
    }

    public String getNivelRiesgo() {
        return nivelRiesgo;
    }

    public boolean requiereRevisionManual() {
        return puntuacionRiesgo >= 70.0 && puntuacionRiesgo < 90.0;
    }

    public boolean esAltoRiesgo() {
        return puntuacionRiesgo >= 90.0;
    }

    public boolean esMontoParcialmenteAprobado() {
        return montoAprobado > 0 && !aprobada;
    }

    public Resultado evaluarNuevoMonto(double nuevoMonto) {
        if (nuevoMonto <= 0) {
            return this;
        }
        return new ResultadoEvaluacion(
            this.idTransaccion,
            this.aprobada,
            this.codigoResultado,
            this.mensaje,
            this.puntuacionRiesgo,
            this.fraudeDetectado,
            this.idEvaluadorRiesgo,
            this.idEvaluadorFraude,
            this.fechaEvaluacion,
            this.razonesRechazo,
            nuevoMonto,
            this.nivelRiesgo
        );
    }

    public static ResultadoEvaluacion aprobacion(String idTransaccion, double puntuacionRiesgo,
                                                   String nivelRiesgo, String idEvaluadorRiesgo,
                                                   String idEvaluadorFraude, double montoAprobado) {
        return new ResultadoEvaluacion(
            idTransaccion, true, "APROBADA", "Transacción aprobada por riesgo y antifraude",
            puntuacionRiesgo, false, idEvaluadorRiesgo, idEvaluadorFraude,
            LocalDateTime.now(), null, montoAprobado, nivelRiesgo
        );
    }

    public static ResultadoEvaluacion rechazo(String idTransaccion, String codigo, String mensaje,
                                               List<String> razones, String idEvaluadorRiesgo,
                                               String idEvaluadorFraude, double puntuacionRiesgo) {
        return new ResultadoEvaluacion(
            idTransaccion, false, codigo, mensaje, puntuacionRiesgo, true,
            idEvaluadorRiesgo, idEvaluadorFraude, LocalDateTime.now(),
            razones, 0.0, "ALTO"
        );
    }

    public static ResultadoEvaluacion aprobacionParcial(String idTransaccion, double puntuacionRiesgo,
                                                          String nivelRiesgo, double montoAprobado,
                                                          List<String> razones, String idEvaluadorRiesgo,
                                                          String idEvaluadorFraude) {
        return new ResultadoEvaluacion(
            idTransaccion, false, "APROBADA_PARCIAL",
            "Transacción aprobada por monto menor al solicitado",
            puntuacionRiesgo, false, idEvaluadorRiesgo, idEvaluadorFraude,
            LocalDateTime.now(), razones, montoAprobado, nivelRiesgo
        );
    }

    public static class Resultado {
    }
}