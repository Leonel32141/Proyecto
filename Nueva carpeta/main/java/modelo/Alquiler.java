package modelo;

import java.time.LocalDateTime;

public class Alquiler {
    private int idAlquiler;
    private Cliente cliente;
    private Vehiculo vehiculo;
    private Sucursal sucursalRetiro;
    private Sucursal sucursalDevolucion;
    private LocalDateTime fechaReserva;
    private LocalDateTime fechaInicioPautada;
    private LocalDateTime fechaFinPautada;
    private boolean esOneway;
    private double montoTotal;
    private double montoSena;
    private String estadoReserva;

    public Alquiler() {}

    public Alquiler(int idAlquiler, Cliente cliente, Vehiculo vehiculo, Sucursal sucursalRetiro, 
                    Sucursal sucursalDevolucion, LocalDateTime fechaInicioPautada, 
                    LocalDateTime fechaFinPautada, boolean esOneway, double montoTotal, double montoSena) {
        this.idAlquiler = idAlquiler;
        this.cliente = cliente;
        this.vehiculo = vehiculo;
        this.sucursalRetiro = sucursalRetiro;
        this.sucursalDevolucion = sucursalDevolucion;
        this.fechaReserva = LocalDateTime.now();
        this.fechaInicioPautada = fechaInicioPautada;
        this.fechaFinPautada = fechaFinPautada;
        this.esOneway = esOneway;
        this.montoTotal = montoTotal;
        this.montoSena = montoSena;
        this.estadoReserva = "RESERVADA";
    }

    public int getIdAlquiler() { return idAlquiler; }
    public void setIdAlquiler(int idAlquiler) { this.idAlquiler = idAlquiler; }

    public Cliente getCliente() { return cliente; }
    public void setCliente(Cliente cliente) { this.cliente = cliente; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Sucursal getSucursalRetiro() { return sucursalRetiro; }
    public void setSucursalRetiro(Sucursal sucursalRetiro) { this.sucursalRetiro = sucursalRetiro; }

    public Sucursal getSucursalDevolucion() { return sucursalDevolucion; }
    public void setSucursalDevolucion(Sucursal sucursalDevolucion) { this.sucursalDevolucion = sucursalDevolucion; }

    public LocalDateTime getFechaReserva() { return fechaReserva; }
    public void setFechaReserva(LocalDateTime fechaReserva) { this.fechaReserva = fechaReserva; }

    public LocalDateTime getFechaInicioPautada() { return fechaInicioPautada; }
    public void setFechaInicioPautada(LocalDateTime fechaInicioPautada) { this.fechaInicioPautada = fechaInicioPautada; }

    public LocalDateTime getFechaFinPautada() { return fechaFinPautada; }
    public void setFechaFinPautada(LocalDateTime fechaFinPautada) { this.fechaFinPautada = fechaFinPautada; }

    public boolean isEsOneway() { return esOneway; }
    public void setEsOneway(boolean esOneway) { this.esOneway = esOneway; }

    public double getMontoTotal() { return montoTotal; }
    public void setMontoTotal(double montoTotal) { this.montoTotal = montoTotal; }

    public double getMontoSena() { return montoSena; }
    public void setMontoSena(double montoSena) { this.montoSena = montoSena; }

    public String getEstadoReserva() { return estadoReserva; }
    public void setEstadoReserva(String estadoReserva) { this.estadoReserva = estadoReserva; }
}