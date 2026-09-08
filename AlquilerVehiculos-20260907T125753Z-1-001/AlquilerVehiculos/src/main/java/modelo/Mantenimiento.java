package modelo;

import java.time.LocalDate;

public class Mantenimiento {
    private int idMantenimiento;
    private Vehiculo vehiculo;
    private LocalDate fechaInicio;
    private LocalDate fechaFin;
    private String tipoMantenimiento;
    private String descripcion;
    private double costoTotal;

    public Mantenimiento() {}

    public Mantenimiento(int idMantenimiento, Vehiculo vehiculo, LocalDate fechaInicio, 
                         LocalDate fechaFin, String tipoMantenimiento, String descripcion, double costoTotal) {
        this.idMantenimiento = idMantenimiento;
        this.vehiculo = vehiculo;
        this.fechaInicio = fechaInicio;
        this.fechaFin = fechaFin;
        this.tipoMantenimiento = tipoMantenimiento;
        this.descripcion = descripcion;
        this.costoTotal = costoTotal;
    }

    public int getIdMantenimiento() { return idMantenimiento; }
    public void setIdMantenimiento(int idMantenimiento) { this.idMantenimiento = idMantenimiento; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public LocalDate getFechaInicio() { return fechaInicio; }
    public void setFechaInicio(LocalDate fechaInicio) { this.fechaInicio = fechaInicio; }

    public LocalDate getFechaFin() { return fechaFin; }
    public void setFechaFin(LocalDate fechaFin) { this.fechaFin = fechaFin; }

    public String getTipoMantenimiento() { return tipoMantenimiento; }
    public void setTipoMantenimiento(String tipoMantenimiento) { this.tipoMantenimiento = tipoMantenimiento; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getCostoTotal() { return costoTotal; }
    public void setCostoTotal(double costoTotal) { this.costoTotal = costoTotal; }
}