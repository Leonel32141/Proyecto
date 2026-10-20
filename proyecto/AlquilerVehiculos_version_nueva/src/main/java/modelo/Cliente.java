package modelo;

public class Cliente extends Usuario {
    private String licenciaConducir;
    private String domicilio;

    public Cliente() {
        super();
    }

    public Cliente(int idUsuario, Rol rol, String email, String passwordHash, String nombre, 
                   String apellido, String dni, String cuil, String telefono, 
                   String licenciaConducir, String domicilio) {
        super(idUsuario, rol, email, passwordHash, nombre, apellido, dni, cuil, telefono);
        this.licenciaConducir = licenciaConducir;
        this.domicilio = domicilio;
    }

    public String getLicenciaConducir() { return licenciaConducir; }
    public void setLicenciaConducir(String licenciaConducir) { this.licenciaConducir = licenciaConducir; }

    public String getDomicilio() { return domicilio; }
    public void setDomicilio(String domicilio) { this.domicilio = domicilio; }
}