package modelo;

import java.util.Objects;

public class MetodoPago {

    private int idMetodoPago;
    private String nombreMetodo;
    private boolean requiereComprobante;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public MetodoPago() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public MetodoPago(String nombreMetodo, boolean requiereComprobante) {
        this.nombreMetodo = nombreMetodo;
        this.requiereComprobante = requiereComprobante;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public MetodoPago(int idMetodoPago, String nombreMetodo, boolean requiereComprobante) {
        this.idMetodoPago = idMetodoPago;
        this.nombreMetodo = nombreMetodo;
        this.requiereComprobante = requiereComprobante;
    }

    // Getters y Setters
    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public String getNombreMetodo() {
        return nombreMetodo;
    }

    public void setNombreMetodo(String nombreMetodo) {
        this.nombreMetodo = nombreMetodo;
    }

    public boolean isRequiereComprobante() {
        return requiereComprobante;
    }

    public void setRequiereComprobante(boolean requiereComprobante) {
        this.requiereComprobante = requiereComprobante;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        MetodoPago that = (MetodoPago) o;
        return idMetodoPago == that.idMetodoPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMetodoPago);
    }

    @Override
    public String toString() {
        return "MetodoPago{" +
                "idMetodoPago=" + idMetodoPago +
                ", nombreMetodo='" + nombreMetodo + '\'' +
                ", requiereComprobante=" + requiereComprobante +
                '}';
    }
}