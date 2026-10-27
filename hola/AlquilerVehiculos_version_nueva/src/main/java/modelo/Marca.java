package modelo;

import java.util.Objects;

public class Marca {

    private int idMarca;
    private String nombreMarca;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Marca() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Marca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Marca(int idMarca, String nombreMarca) {
        this.idMarca = idMarca;
        this.nombreMarca = nombreMarca;
    }

    // Getters y Setters
    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreMarca() {
        return nombreMarca;
    }

    public void setNombreMarca(String nombreMarca) {
        this.nombreMarca = nombreMarca;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Marca marca = (Marca) o;
        return idMarca == marca.idMarca;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMarca);
    }

    @Override
    public String toString() {
        return "Marca{" +
                "idMarca=" + idMarca +
                ", nombreMarca='" + nombreMarca + '\'' +
                '}';
    }
}