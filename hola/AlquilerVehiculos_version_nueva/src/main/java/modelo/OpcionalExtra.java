package modelo;

import java.math.BigDecimal;
import java.util.Objects;

public class OpcionalExtra {

    private int idExtra;
    private String nombreExtra;
    private String descripcion;
    private BigDecimal precioDiario;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public OpcionalExtra() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public OpcionalExtra(String nombreExtra, String descripcion, BigDecimal precioDiario) {
        this.nombreExtra = nombreExtra;
        this.descripcion = descripcion;
        this.precioDiario = precioDiario;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public OpcionalExtra(int idExtra, String nombreExtra, String descripcion, BigDecimal precioDiario) {
        this.idExtra = idExtra;
        this.nombreExtra = nombreExtra;
        this.descripcion = descripcion;
        this.precioDiario = precioDiario;
    }

    // Getters y Setters
    public int getIdExtra() {
        return idExtra;
    }

    public void setIdExtra(int idExtra) {
        this.idExtra = idExtra;
    }

    public String getNombreExtra() {
        return nombreExtra;
    }

    public void setNombreExtra(String nombreExtra) {
        this.nombreExtra = nombreExtra;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getPrecioDiario() {
        return precioDiario;
    }

    public void setPrecioDiario(BigDecimal precioDiario) {
        this.precioDiario = precioDiario;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        OpcionalExtra that = (OpcionalExtra) o;
        return idExtra == that.idExtra;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idExtra);
    }

    @Override
    public String toString() {
        return "OpcionalExtra{" +
                "idExtra=" + idExtra +
                ", nombreExtra='" + nombreExtra + '\'' +
                ", precioDiario=" + precioDiario +
                '}';
    }
}