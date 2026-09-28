package com.transaccionesfinancieras.domain.exception;

import java.util.Map;

public class Respuesta5xxException extends RuntimeException {
    
    private final int codigoEstado;
    private final String mensajeError;
    private final String cuerpoRespuesta;
    private final String endpoint;
    private final Map<String, String> headers;

    public Respuesta5xxException(int codigoEstado, String mensajeError, String cuerpoRespuesta, 
                                   String endpoint, Map<String, String> headers) {
        super(String.format("Error %d del core bancario en endpoint %s: %s", codigoEstado, endpoint, mensajeError));
        this.codigoEstado = codigoEstado;
        this.mensajeError = mensajeError;
        this.cuerpoRespuesta = cuerpoRespuesta;
        this.endpoint = endpoint;
        this.headers = headers != null ? Map.copyOf(headers) : Map.of();
    }

    public Respuesta5xxException(int codigoEstado, String mensajeError, String endpoint) {
        this(codigoEstado, mensajeError, null, endpoint, null);
    }

    public int getCodigoEstado() {
        return codigoEstado;
    }

    public String getMensajeError() {
        return mensajeError;
    }

    public String getCuerpoRespuesta() {
        return cuerpoRespuesta;
    }

    public String getEndpoint() {
        return endpoint;
    }

    public Map<String, String> getHeaders() {
        return headers;
    }

    public boolean isErrorServidor() {
        return codigoEstado >= 500 && codigoEstado < 600;
    }

    public boolean isErrorBaseDatos() {
        return cuerpoRespuesta != null && cuerpoRespuesta.toLowerCase().contains("database");
    }

    public boolean isErrorTimeout() {
        return cuerpoRespuesta != null && 
               (cuerpoRespuesta.toLowerCase().contains("timeout") || 
                cuerpoRespuesta.toLowerCase().contains("timeout exceeded"));
    }

    public String getDetalleTecnico() {
        StringBuilder sb = new StringBuilder();
        sb.append("Codigo: ").append(codigoEstado).append("\n");
        sb.append("Endpoint: ").append(endpoint).append("\n");
        sb.append("Mensaje: ").append(mensajeError).append("\n");
        if (cuerpoRespuesta != null && !cuerpoRespuesta.isBlank()) {
            sb.append("Cuerpo: ").append(cuerpoRespuesta).append("\n");
        }
        if (!headers.isEmpty()) {
            sb.append("Headers: ").append(headers).append("\n");
        }
        return sb.toString();
    }

    public static Respuesta5xxException desdeWebClientResponse(int statusCode, String body, String url) {
        String mensaje = body != null && !body.isBlank() ? body : "Error sin mensaje";
        return new Respuesta5xxException(statusCode, mensaje, body, url, null);
    }
}