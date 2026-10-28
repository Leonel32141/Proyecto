package modelo;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.Objects;

public class Usuario {

    private int idUsuario;
    private int idRol;
    private String email;
    private String passwordHash;
    private String nombre;
    private String apellido;
    private String dni;
    private String cuil;
    private String telefono;
    private int intentosFallidos;
    private boolean cuentaBloqueada;
    private LocalDateTime fechaCreacion;
    private LocalDate fechaNacimiento;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Usuario() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Usuario(int idRol, String email, String passwordHash, String nombre, 
                   String apellido, String dni, String cuil, String telefono, 
                   int intentosFallidos, boolean cuentaBloqueada, 
                   LocalDateTime fechaCreacion, LocalDate fechaNacimiento) {
        this.idRol = idRol;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuil = cuil;
        this.telefono = telefono;
        this.intentosFallidos = intentosFallidos;
        this.cuentaBloqueada = cuentaBloqueada;
        this.fechaCreacion = fechaCreacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Usuario(int idUsuario, int idRol, String email, String passwordHash, String nombre, 
                   String apellido, String dni, String cuil, String telefono, 
                   int intentosFallidos, boolean cuentaBloqueada, 
                   LocalDateTime fechaCreacion, LocalDate fechaNacimiento) {
        this.idUsuario = idUsuario;
        this.idRol = idRol;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nombre = nombre;
        this.apellido = apellido;
        this.dni = dni;
        this.cuil = cuil;
        this.telefono = telefono;
        this.intentosFallidos = intentosFallidos;
        this.cuentaBloqueada = cuentaBloqueada;
        this.fechaCreacion = fechaCreacion;
        this.fechaNacimiento = fechaNacimiento;
    }

    // Getters y Setters
    public int getIdUsuario() {
        return idUsuario;
    }

    public void setIdUsuario(int idUsuario) {
        this.idUsuario = idUsuario;
    }

    public int getIdRol() {
        return idRol;
    }

    public void setIdRol(int idRol) {
        this.idRol = idRol;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getPasswordHash() {
        return passwordHash;
    }

    public void setPasswordHash(String passwordHash) {
        this.passwordHash = passwordHash;
    }

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getCuil() {
        return cuil;
    }

    public void setCuil(String cuil) {
        this.cuil = cuil;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public int getIntentosFallidos() {
        return intentosFallidos;
    }

    public void setIntentosFallidos(int intentosFallidos) {
        this.intentosFallidos = intentosFallidos;
    }

    public boolean isCuentaBloqueada() {
        return cuentaBloqueada;
    }

    public void setCuentaBloqueada(boolean cuentaBloqueada) {
        this.cuentaBloqueada = cuentaBloqueada;
    }

    public LocalDateTime getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDateTime fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaNacimiento() {
        return fechaNacimiento;
    }

    public void setFechaNacimiento(LocalDate fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Usuario usuario = (Usuario) o;
        return idUsuario == usuario.idUsuario;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idUsuario);
    }

    @Override
    public String toString() {
        return "Usuario{" +
                "idUsuario=" + idUsuario +
                ", email='" + email + '\'' +
                ", nombre='" + nombre + '\'' +
                ", apellido='" + apellido + '\'' +
                '}';
    }
}