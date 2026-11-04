package modelo;

import java.util.Objects;

public class FotoInspeccion {

    private int idFoto;
    private int idRegistro;
    private String fotoUrl;
    private String observacionDano;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public FotoInspeccion() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public FotoInspeccion(int idRegistro, String fotoUrl, String observacionDano) {
        this.idRegistro = idRegistro;
        this.fotoUrl = fotoUrl;
        this.observacionDano = observacionDano;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public FotoInspeccion(int idFoto, int idRegistro, String fotoUrl, String observacionDano) {
        this.idFoto = idFoto;
        this.idRegistro = idRegistro;
        this.fotoUrl = fotoUrl;
        this.observacionDano = observacionDano;
    }

    // Getters y Setters
    public int getIdFoto() {
        return idFoto;
    }

    public void setIdFoto(int idFoto) {
        this.idFoto = idFoto;
    }

    public int getIdRegistro() {
        return idRegistro;
    }

    public void setIdRegistro(int idRegistro) {
        this.idRegistro = idRegistro;
    }

    public String getFotoUrl() {
        return fotoUrl;
    }

    public void setFotoUrl(String fotoUrl) {
        this.fotoUrl = fotoUrl;
    }

    public String getObservacionDano() {
        return observacionDano;
    }

    public void setObservacionDano(String observacionDano) {
        this.observacionDano = observacionDano;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        FotoInspeccion that = (FotoInspeccion) o;
        return idFoto == that.idFoto;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idFoto);
    }

    @Override
    public String toString() {
        return "FotoInspeccion{" +
                "idFoto=" + idFoto +
                ", idRegistro=" + idRegistro +
                ", fotoUrl='" + fotoUrl + '\'' +
                '}';
    }
}