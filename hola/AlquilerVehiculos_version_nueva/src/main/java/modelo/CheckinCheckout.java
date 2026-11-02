package modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class CheckinCheckout {

    private int idRegistro;
    private int idAlquiler;
    private int idUsuarioEmpleado;
    private String tipo;
    private LocalDateTime fechaHoraReal;
    private int kilometraje;
    private String nivelCombustible;
    private String observaciones;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public CheckinCheckout() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public CheckinCheckout(int idAlquiler, int idUsuarioEmpleado, String tipo, LocalDateTime fechaHoraReal,
                           int kilometraje, String nivelCombustible, String observaciones) {
        this.idAlquiler = idAlquiler;
        this.idUsuarioEmpleado = idUsuarioEmpleado;
        this.tipo = tipo;
        this.fechaHoraReal = fechaHoraReal;
        this.kilometraje = kilometraje;
        this.nivelCombustible = nivelCombustible;
        this.observaciones = observaciones;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public CheckinCheckout(int idRegistro, int idAlquiler, int idUsuarioEmpleado, String tipo, LocalDateTime fechaHoraReal,
                           int kilometraje, String nivelCombustible, String observaciones) {
        this.idRegistro = idRegistro;
        this.idAlquiler = idAlquiler;
        this.idUsuarioEmpleado = idUsuarioEmpleado;
        this.tipo = tipo;
        this.fechaHoraReal = fechaHoraReal;
        this.kilometraje = kilometraje;
        this.nivelCombustible = nivelCombustible;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdUsuarioEmpleado() {
        return idUsuarioEmpleado;
    }

    public void setIdUsuarioEmpleado(int idUsuarioEmpleado) {
        this.idUsuarioEmpleado = idUsuarioEmpleado;
    }

    public String getTipo() {
        return tipo;
    }

    public void setTipo(String tipo) {
        this.tipo = tipo;
    }

    public LocalDateTime getFechaHoraReal() {
        return fechaHoraReal;
    }

    public void setFechaHoraReal(LocalDateTime fechaHoraReal) {
        this.fechaHoraReal = fechaHoraReal;
    }

    public int getKilometraje() {
        return kilometraje;
    }

    public void setKilometraje(int kilometraje) {
        this.kilometraje = kilometraje;
    }

    public String getNivelCombustible() {
        return nivelCombustible;
    }

    public void setNivelCombustible(String nivelCombustible) {
        this.nivelCombustible = nivelCombustible;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CheckinCheckout that = (CheckinCheckout) o;
        return idRegistro == that.idRegistro;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idRegistro);
    }

    @Override
    public String toString() {
        return "CheckinCheckout{" +
                "idRegistro=" + idRegistro +
                ", idAlquiler=" + idAlquiler +
                ", tipo='" + tipo + '\'' +
                ", kilometraje=" + kilometraje +
                '}';
    }
}