package com.transaccionesfinancieras.domain.model;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

public class Transaccion {
    private final String id;
    private final String idCliente;
    private final String idOriginador;
    private final BigDecimal monto;
    private final String moneda;
    private final String tipoTransaccion;
    private final String cuentaOrigen;
    private final String cuentaDestino;
    private final LocalDateTime fechaCreacion;
    private final LocalDateTime fechaProcesamiento;
    private final String estado;
    private final String descripcion;
    private final String canalOrigen;
    private final String ipOrigen;

    public Transaccion(String id, String idCliente, String idOriginador, BigDecimal monto, String moneda,
                       String tipoTransaccion, String cuentaOrigen, String cuentaDestino,
                       LocalDateTime fechaCreacion, LocalDateTime fechaProcesamiento, String estado,
                       String descripcion, String canalOrigen, String ipOrigen) {
        this.id = id != null ? id : UUID.randomUUID().toString();
        this.idCliente = idCliente;
        this.idOriginador = idOriginador;
        this.monto = monto;
        this.moneda = moneda;
        this.tipoTransaccion = tipoTransaccion;
        this.cuentaOrigen = cuentaOrigen;
        this.cuentaDestino = cuentaDestino;
        this.fechaCreacion = fechaCreacion != null ? fechaCreacion : LocalDateTime.now();
        this.fechaProcesamiento = fechaProcesamiento;
        this.estado = estado;
        this.descripcion = descripcion;
        this.canalOrigen = canalOrigen;
        this.ipOrigen = ipOrigen;
    }

    public String getId() {
        return id;
    }

    public String getIdCliente() {
        return idCliente;
    }

    public String getIdOriginador() {
        return idOriginador;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public String getMoneda() {
        return moneda;
    }

    public String getTipoTransaccion() {
        return tipoTransaccion;
    }

    public String getCuentaOrigen() {
        return cuentaOrigen;
    }

    public String getCuentaDestino() {
        return cuentaDestino;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public LocalDateTime getFechaProcesamiento() {
        return fechaProcesamiento;
    }

    public String getEstado() {
        return estado;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public String getCanalOrigen() {
        return canalOrigen;
    }

    public String getIpOrigen() {
        return ipOrigen;
    }

    public boolean esMontoValido() {
        return monto != null && monto.compareTo(BigDecimal.ZERO) > 0;
    }

    public boolean esTransaccionMayorA(double umbral) {
        return monto != null && monto.doubleValue() > umbral;
    }

    public Transaccion conEstado(String nuevoEstado) {
        return new Transaccion(
            this.id, this.idCliente, this.idOriginador, this.monto, this.moneda,
            this.tipoTransaccion, this.cuentaOrigen, this.cuentaDestino,
            this.fechaCreacion, LocalDateTime.now(), nuevoEstado,
            this.descripcion, this.canalOrigen, this.ipOrigen
        );
    }

    public Transaccion conFechaProcesamiento(LocalDateTime fechaProcesamiento) {
        return new Transaccion(
            this.id, this.idCliente, this.idOriginador, this.monto, this.moneda,
            this.tipoTransaccion, this.cuentaOrigen, this.cuentaDestino,
            this.fechaCreacion, fechaProcesamiento, this.estado,
            this.descripcion, this.canalOrigen, this.ipOrigen
        );
    }
}