package modelo;

import java.util.Objects;

public class AlquilerExtra {

    private int idAlquiler;
    private int idExtra;
    private int cantidad;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public AlquilerExtra() {
    }

    // 2. Constructor completo (Para inserciones o consultas con clave compuesta)
    public AlquilerExtra(int idAlquiler, int idExtra, int cantidad) {
        this.idAlquiler = idAlquiler;
        this.idExtra = idExtra;
        this.cantidad = cantidad;
    }

    // Getters y Setters
    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(int idExtra) {
        this.idExtra = idExtra;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    // hashCode, equals (basado en la clave compuesta) y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AlquilerExtra that = (AlquilerExtra) o;
        return idAlquiler == that.idAlquiler && idExtra == that.idExtra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idAlquiler, idExtra);
    }

    @Override
    public String toString() {
        return "AlquilerExtra{" +
                "idAlquiler=" + idAlquiler +
                ", idExtra=" + idExtra +
                ", cantidad=" + cantidad +
                '}';
    }
}