package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Alquiler {

    private int idAlquiler;
    private int idCliente;
    private int idVehiculo;
    private int idSucursalRetiro;
    private int idSucursalDevolucion;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaInicioPautada;
    private LocalDateTime fechaFinPautada;
    private boolean esOneway;
    private BigDecimal montoTotal;
    private BigDecimal montoSena;
    private String estadoReserva;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Alquiler() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Alquiler(int idCliente, int idVehiculo, int idSucursalRetiro, int idSucursalDevolucion,
                    LocalDateTime fechaReserva, LocalDateTime fechaInicioPautada, LocalDateTime fechaFinPautada,
                    boolean esOneway, BigDecimal montoTotal, BigDecimal montoSena, String estadoReserva) {
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.idSucursalRetiro = idSucursalRetiro;
        this.idSucursalDevolucion = idSucursalDevolucion;
        this.fechaReserva = fechaReserva;
        this.fechaInicioPautada = fechaInicioPautada;
        this.fechaFinPautada = fechaFinPautada;
        this.esOneway = esOneway;
        this.montoTotal = montoTotal;
        this.montoSena = montoSena;
        this.estadoReserva = estadoReserva;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Alquiler(int idAlquiler, int idCliente, int idVehiculo, int idSucursalRetiro, int idSucursalDevolucion,
                    LocalDateTime fechaReserva, LocalDateTime fechaInicioPautada, LocalDateTime fechaFinPautada,
                    boolean esOneway, BigDecimal montoTotal, BigDecimal montoSena, String estadoReserva) {
        this.idAlquiler = idAlquiler;
        this.idCliente = idCliente;
        this.idVehiculo = idVehiculo;
        this.idSucursalRetiro = idSucursalRetiro;
        this.idSucursalDevolucion = idSucursalDevolucion;
        this.fechaReserva = fechaReserva;
        this.fechaInicioPautada = fechaInicioPautada;
        this.fechaFinPautada = fechaFinPautada;
        this.esOneway = esOneway;
        this.montoTotal = montoTotal;
        this.montoSena = montoSena;
        this.estadoReserva = estadoReserva;
    }

    // Getters y Setters
    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdSucursalRetiro() {
        return idSucursalRetiro;
    }

    public void setIdSucursalRetiro(int idSucursalRetiro) {
        this.idSucursalRetiro = idSucursalRetiro;
    }

    public int getIdSucursalDevolucion() {
        return idSucursalDevolucion;
    }

    public void setIdSucursalDevolucion(int idSucursalDevolucion) {
        this.idSucursalDevolucion = idSucursalDevolucion;
    }

    public LocalDateTime getFechaReserva() {
        return fechaReserva;
    }

    public void setFechaReserva(LocalDateTime fechaReserva) {
        this.fechaReserva = fechaReserva;
    }

    public LocalDateTime getFechaInicioPautada() {
        return fechaInicioPautada;
    }

    public void setFechaInicioPautada(LocalDateTime fechaInicioPautada) {
        this.fechaInicioPautada = fechaInicioPautada;
    }

    public LocalDateTime getFechaFinPautada() {
        return fechaFinPautada;
    }

    public void setFechaFinPautada(LocalDateTime fechaFinPautada) {
        this.fechaFinPautada = fechaFinPautada;
    }

    public boolean isEsOneway() {
        return esOneway;
    }

    public void setEsOneway(boolean esOneway) {
        this.esOneway = esOneway;
    }

    public BigDecimal getMontoTotal() {
        return montoTotal;
    }

    public void setMontoTotal(BigDecimal montoTotal) {
        this.montoTotal = montoTotal;
    }

    public BigDecimal getMontoSena() {
        return montoSena;
    }

    public void setMontoSena(BigDecimal montoSena) {
        this.montoSena = montoSena;
    }

    public String getEstadoReserva() {
        return estadoReserva;
    }

    public void setEstadoReserva(String estadoReserva) {
        this.estadoReserva = estadoReserva;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Alquiler alquiler = (Alquiler) o;
        return idAlquiler == alquiler.idAlquiler;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlquiler);
    }

    @Override
    public String toString() {
        return "Alquiler{" +
                "idAlquiler=" + idAlquiler +
                ", idCliente=" + idCliente +
                ", idVehiculo=" + idVehiculo +
                ", estadoReserva='" + estadoReserva + '\'' +
                '}';
    }
}