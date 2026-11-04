package modelo;

import java.math.BigDecimal;
import java.util.Objects;

public class CategoriaVehiculo {

    private int idCategoria;
    private String nombreCategoria;
    private String descripcion;
    private BigDecimal tarifaBaseDia;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public CategoriaVehiculo() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public CategoriaVehiculo(String nombreCategoria, String descripcion, BigDecimal tarifaBaseDia) {
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
        this.tarifaBaseDia = tarifaBaseDia;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public CategoriaVehiculo(int idCategoria, String nombreCategoria, String descripcion, BigDecimal tarifaBaseDia) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.descripcion = descripcion;
        this.tarifaBaseDia = tarifaBaseDia;
    }

    // Getters y Setters
    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getNombreCategoria() {
        return nombreCategoria;
    }

    public void setNombreCategoria(String nombreCategoria) {
        this.nombreCategoria = nombreCategoria;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public BigDecimal getTarifaBaseDia() {
        return tarifaBaseDia;
    }

    public void setTarifaBaseDia(BigDecimal tarifaBaseDia) {
        this.tarifaBaseDia = tarifaBaseDia;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        CategoriaVehiculo that = (CategoriaVehiculo) o;
        return idCategoria == that.idCategoria;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCategoria);
    }

    @Override
    public String toString() {
        return "CategoriaVehiculo{" +
                "idCategoria=" + idCategoria +
                ", nombreCategoria='" + nombreCategoria + '\'' +
                ", tarifaBaseDia=" + tarifaBaseDia +
                '}';
    }
}