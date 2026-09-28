package modelo;

import java.time.LocalDateTime;

public class CheckinCheckout {
    private int idInspeccion;
    private Alquiler alquiler;
    private Usuario empleado;
    private String tipo; // CHECKIN o CHECKOUT
    private LocalDateTime fechaHora;
    private int kilometraje;
    private double nivelCombustible; // Ej: 0.25, 0.50, 1.0
    private String observaciones;

    public CheckinCheckout() {}

    public CheckinCheckout(int idInspeccion, Alquiler alquiler, Usuario empleado, 
                           String tipo, int kilometraje, double nivelCombustible, String observaciones) {
        this.idInspeccion = idInspeccion;
        this.alquiler = alquiler;
        this.empleado = empleado;
        this.tipo = tipo;
        this.fechaHora = LocalDateTime.now();
        this.kilometraje = kilometraje;
        this.nivelCombustible = nivelCombustible;
        this.observaciones = observaciones;
    }

    public int getIdInspeccion() { return idInspeccion; }
    public void setIdInspeccion(int idInspeccion) { this.idInspeccion = idInspeccion; }

    public Alquiler getAlquiler() { return alquiler; }
    public void setAlquiler(Alquiler alquiler) { this.alquiler = alquiler; }

    public Usuario getEmpleado() { return empleado; }
    public void setEmpleado(Usuario empleado) { this.empleado = empleado; }

    public String getTipo() { return tipo; }
    public void setTipo(String tipo) { this.tipo = tipo; }

    public LocalDateTime getFechaHora() { return fechaHora; }
    public void setFechaHora(LocalDateTime fechaHora) { this.fechaHora = fechaHora; }

    public int getKilometraje() { return kilometraje; }
    public void setKilometraje(int kilometraje) { this.kilometraje = kilometraje; }

    public double getNivelCombustible() { return nivelCombustible; }
    public void setNivelCombustible(double nivelCombustible) { this.nivelCombustible = nivelCombustible; }

    public String getObservaciones() { return observaciones; }
    public void setObservaciones(String observaciones) { this.observaciones = observaciones; }
}