package modelo;

import java.time.LocalDateTime;

public class Usuario {
    private int idUsuario;
    private Rol rol;
    private String email;
    private String passwordHash;
    private String nombre;
    private String apellido;
    private String cuil;
    private String telefono;
    private int intentosFallidos;
    private boolean cuentaBloqueada;
    private LocalDateTime fechaCreacion;

    public Usuario() {}

    public Usuario(int idUsuario, Rol rol, String email, String passwordHash, String nombre, 
                   String apellido, String cuil, String telefono) {
        this.idUsuario = idUsuario;
        this.rol = rol;
        this.email = email;
        this.passwordHash = passwordHash;
        this.nombre = nombre;
        this.apellido = apellido;
        this.cuil = cuil;
        this.telefono = telefono;
        this.intentosFallidos = 0;
        this.cuentaBloqueada = false;
        this.fechaCreacion = LocalDateTime.now();
    }

    public int getIdUsuario() { return idUsuario; }
    public void setIdUsuario(int idUsuario) { this.idUsuario = idUsuario; }

    public Rol getRol() { return rol; }
    public void setRol(Rol rol) { this.rol = rol; }

    public String getEmail() { return email; }
    public void setEmail(String email) { this.email = email; }

    public String getPasswordHash() { return passwordHash; }
    public void setPasswordHash(String passwordHash) { this.passwordHash = passwordHash; }

    public String getNombre() { return nombre; }
    public void setNombre(String nombre) { this.nombre = nombre; }

    public String getApellido() { return apellido; }
    public void setApellido(String apellido) { this.apellido = apellido; }

    public String getCuil() { return cuil; }
    public void setCuil(String cuil) { this.cuil = cuil; }

    public String getTelefono() { return telefono; }
    public void setTelefono(String telefono) { this.telefono = telefono; }

    public int getIntentosFallidos() { return intentosFallidos; }
    public void setIntentosFallidos(int intentosFallidos) { this.intentosFallidos = intentosFallidos; }

    public boolean isCuentaBloqueada() { return cuentaBloqueada; }
    public void setCuentaBloqueada(boolean cuentaBloqueada) { this.cuentaBloqueada = cuentaBloqueada; }

    public LocalDateTime getFechaCreacion() { return fechaCreacion; }
    public void setFechaCreacion(LocalDateTime fechaCreacion) { this.fechaCreacion = fechaCreacion; }
}