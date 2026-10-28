package modelo;

import java.util.Objects;

public class ModeloVehiculo {

    private int idModelo;
    private int idMarca;
    private String nombreModelo;
    private int anio;
    private String imagen;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public ModeloVehiculo() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public ModeloVehiculo(int idMarca, String nombreModelo, int anio, String imagen) {
        this.idMarca = idMarca;
        this.nombreModelo = nombreModelo;
        this.anio = anio;
        this.imagen = imagen;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public ModeloVehiculo(int idModelo, int idMarca, String nombreModelo, int anio, String imagen) {
        this.idModelo = idModelo;
        this.idMarca = idMarca;
        this.nombreModelo = nombreModelo;
        this.anio = anio;
        this.imagen = imagen;
    }

    // Getters y Setters
    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdMarca() {
        return idMarca;
    }

    public void setIdMarca(int idMarca) {
        this.idMarca = idMarca;
    }

    public String getNombreModelo() {
        return nombreModelo;
    }

    public void setNombreModelo(String nombreModelo) {
        this.nombreModelo = nombreModelo;
    }

    public int getAnio() {
        return anio;
    }

    public void setAnio(int anio) {
        this.anio = anio;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ModeloVehiculo that = (ModeloVehiculo) o;
        return idModelo == that.idModelo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idModelo);
    }

    @Override
    public String toString() {
        return "ModeloVehiculo{" +
                "idModelo=" + idModelo +
                ", nombreModelo='" + nombreModelo + '\'' +
                ", anio=" + anio +
                '}';
    }
}