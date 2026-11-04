package modelo;

import java.time.LocalDate;
import java.util.Objects;

public class Cliente {

    private int idCliente;
    private String dni;
    private String licenciaConducir;
    private String domicilio;
    private String fotoIdentificacionUrl;
    private LocalDate fechaNacimiento;
    private boolean esAptoLujo;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Cliente() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Cliente(String dni, String licenciaConducir, String domicilio, 
                   String fotoIdentificacionUrl, LocalDate fechaNacimiento, boolean esAptoLujo) {
        this.dni = dni;
        this.licenciaConducir = licenciaConducir;
        this.domicilio = domicilio;
        this.fotoIdentificacionUrl = fotoIdentificacionUrl;
        this.fechaNacimiento = fechaNacimiento;
        this.esAptoLujo = esAptoLujo;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Cliente(int idCliente, String dni, String licenciaConducir, String domicilio, 
                   String fotoIdentificacionUrl, LocalDate fechaNacimiento, boolean esAptoLujo) {
        this.idCliente = idCliente;
        this.dni = dni;
        this.licenciaConducir = licenciaConducir;
        this.domicilio = domicilio;
        this.fotoIdentificacionUrl = fotoIdentificacionUrl;
        this.fechaNacimiento = fechaNacimiento;
        this.esAptoLujo = esAptoLujo;
    }

    // Getters y Setters
    public int getIdCliente() {
        return idCliente;
    }

    public void setIdCliente(int idCliente) {
        this.idCliente = idCliente;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getLicenciaConducir() {
        return licenciaConducir;
    }

    public void setLicenciaConducir(String licenciaConducir) {
        this.licenciaConducir = licenciaConducir;
    }

    public String getDomicilio() {
        return domicilio;
    }

    public void setDomicilio(String domicilio) {
        this.domicilio = domicilio;
    }

    public String getFotoIdentificacionUrl() {
        return fotoIdentificacionUrl;
    }

    public void setFotoIdentificacionUrl(String fotoIdentificacionUrl) {
        this.fotoIdentificacionUrl = fotoIdentificacionUrl;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public boolean isEsAptoLujo() {
        return esAptoLujo;
    }

    public void setEsAptoLujo(boolean esAptoLujo) {
        this.esAptoLujo = esAptoLujo;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Cliente cliente = (Cliente) o;
        return idCliente == cliente.idCliente;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idCliente);
    }

    @Override
    public String toString() {
        return "Cliente{" +
                "idCliente=" + idCliente +
                ", dni='" + dni + '\'' +
                ", licenciaConducir='" + licenciaConducir + '\'' +
                '}';
    }
}