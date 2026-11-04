package modelo;

import java.util.Objects;

public class Sucursal {

    private int idSucursal;
    private String nombre;
    private String direccion;
    private String ciudad;
    private boolean activa;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Sucursal() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Sucursal(String nombre, String direccion, String ciudad, boolean activa) {
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.activa = activa;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Sucursal(int idSucursal, String nombre, String direccion, String ciudad, boolean activa) {
        this.idSucursal = idSucursal;
        this.nombre = nombre;
        this.direccion = direccion;
        this.ciudad = ciudad;
        this.activa = activa;
    }

    // Getters y Setters
    public int getIdSucursal() {
        return idSucursal;
    }

    public void setIdSucursal(int idSucursal) {
        this.idSucursal = idSucursal;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCiudad() {
        return ciudad;
    }

    public void setCiudad(String ciudad) {
        this.ciudad = ciudad;
    }

    public boolean isActiva() {
        return activa;
    }

    public void setActiva(boolean activa) {
        this.activa = activa;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Sucursal sucursal = (Sucursal) o;
        return idSucursal == sucursal.idSucursal;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idSucursal);
    }

    @Override
    public String toString() {
        return "Sucursal{" +
                "idSucursal=" + idSucursal +
                ", nombre='" + nombre + '\'' +
                ", ciudad='" + ciudad + '\'' +
                ", activa=" + activa +
                '}';
    }
}