package com.transaccionesfinancieras.domain.exception;

public class TimeoutBuroException extends RuntimeException {
    private final String idTransaccion;
    private final String idCliente;
    private final long tiempoEsperaMs;
    private final String mensajeOriginal;

    public TimeoutBuroException(String mensaje) {
        super(mensaje);
        this.idTransaccion = null;
        this.idCliente = null;
        this.tiempoEsperaMs = 0;
        this.mensajeOriginal = mensaje;
    }

    public TimeoutBuroException(String mensaje, Throwable causa) {
        super(mensaje, causa);
        this.idTransaccion = null;
        this.idCliente = null;
        this.tiempoEsperaMs = 0;
        this.mensajeOriginal = mensaje;
    }

    public TimeoutBuroException(String idTransaccion, String idCliente, long tiempoEsperaMs) {
        super(String.format("Timeout al consultar buró de riesgos para transacción %s del cliente %s. " +
                           "Tiempo de espera excedido: %d ms", idTransaccion, idCliente, tiempoEsperaMs));
        this.idTransaccion = idTransaccion;
        this.idCliente = idCliente;
        this.tiempoEsperaMs = tiempoEsperaMs;
        this.mensajeOriginal = null;
    }

    public TimeoutBuroException(String idTransaccion, String idCliente, long tiempoEsperaMs, Throwable causa) {
        super(String.format("Timeout al consultar buró de riesgos para transacción %s del cliente %s. " +
                           "Tiempo de espera excedido: %d ms", idTransaccion, idCliente, tiempoEsperaMs), causa);
        this.idTransaccion = idTransaccion;
        this.idCliente = idCliente;
        this.tiempoEsperaMs = tiempoEsperaMs;
        this.mensajeOriginal = null;
    }

    public String getIdTransaccion() {
        return idTransaccion;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public long getTiempoEsperaMs() {
        return tiempoEsperaMs;
    }

    public String getMensajeOriginal() {
        return mensajeOriginal;
    }

    public boolean tieneDatosContexto() {
        return idTransaccion != null && idCliente != null;
    }

    public String getCodigoError() {
        return "TIMEOUT_BURO_001";
    }

    public String getSeverity() {
        return "HIGH";
    }

    public String getCategoria() {
        return "INFRAESTRUCTURA";
    }

    public boolean esRecuperable() {
        return true;
    }
}