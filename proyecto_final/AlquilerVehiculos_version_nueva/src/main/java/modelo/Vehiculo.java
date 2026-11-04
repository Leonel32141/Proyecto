package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Vehiculo {

    private int idVehiculo;
    private String patente;
    private int idModelo;
    private int idCategoria;
    private int idSucursalActual;
    private int kilometrajeActual;
    private String estado;
    private LocalDate vencimientoSeguro;
    private LocalDate vencimientoPatente;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Vehiculo() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Vehiculo(String patente, int idModelo, int idCategoria, int idSucursalActual, 
                    int kilometrajeActual, String estado, LocalDate vencimientoSeguro, 
                    LocalDate vencimientoPatente) {
        this.patente = patente;
        this.idModelo = idModelo;
        this.idCategoria = idCategoria;
        this.idSucursalActual = idSucursalActual;
        this.kilometrajeActual = kilometrajeActual;
        this.estado = estado;
        this.vencimientoSeguro = vencimientoSeguro;
        this.vencimientoPatente = vencimientoPatente;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Vehiculo(int idVehiculo, String patente, int idModelo, int idCategoria, int idSucursalActual, 
                    int kilometrajeActual, String estado, LocalDate vencimientoSeguro, 
                    LocalDate vencimientoPatente) {
        this.idVehiculo = idVehiculo;
        this.patente = patente;
        this.idModelo = idModelo;
        this.idCategoria = idCategoria;
        this.idSucursalActual = idSucursalActual;
        this.kilometrajeActual = kilometrajeActual;
        this.estado = estado;
        this.vencimientoSeguro = vencimientoSeguro;
        this.vencimientoPatente = vencimientoPatente;
    }

    // Getters y Setters
    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getPatente() {
        return patente;
    }

    public void setPatente(String patente) {
        this.patente = patente;
    }

    public int getIdModelo() {
        return idModelo;
    }

    public void setIdModelo(int idModelo) {
        this.idModelo = idModelo;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public int getIdSucursalActual() {
        return idSucursalActual;
    }

    public void setIdSucursalActual(int idSucursalActual) {
        this.idSucursalActual = idSucursalActual;
    }

    public int getKilometrajeActual() {
        return kilometrajeActual;
    }

    public void setKilometrajeActual(int kilometrajeActual) {
        this.kilometrajeActual = kilometrajeActual;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public LocalDate getVencimientoSeguro() {
        return vencimientoSeguro;
    }

    public void setVencimientoSeguro(LocalDate vencimientoSeguro) {
        this.vencimientoSeguro = vencimientoSeguro;
    }

    public LocalDate getVencimientoPatente() {
        return vencimientoPatente;
    }

    public void setVencimientoPatente(LocalDate vencimientoPatente) {
        this.vencimientoPatente = vencimientoPatente;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Vehiculo vehiculo = (Vehiculo) o;
        return idVehiculo == vehiculo.idVehiculo;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idVehiculo);
    }

    @Override
    public String toString() {
        return "Vehiculo{" +
                "idVehiculo=" + idVehiculo +
                ", patente='" + patente + '\'' +
                ", estado='" + estado + '\'' +
                '}';
    }
}