package modelo;

import java.time.LocalDate;

public class Vehiculo {
    private int idVehiculo;
    private String patente;
    private ModeloVehiculo modelo;
    private CategoriaVehiculo categoria;
    private String estado; // DISPONIBLE, RESERVADO, ALQUILADO, MANTENIMIENTO, TRASLADO
    private Sucursal sucursalActual;
    private int kilometrajeActual;
    private LocalDate vencimientoSeguro;
    private LocalDate vencimientoPatente;

    public Vehiculo() {}

    public Vehiculo(int idVehiculo, String patente, ModeloVehiculo modelo, CategoriaVehiculo categoria,
                    String estado, Sucursal sucursalActual, int kilometrajeActual,
                    LocalDate vencimientoSeguro, LocalDate vencimientoPatente) {
        this.idVehiculo = idVehiculo;
        this.patente = patente;
        this.modelo = modelo;
        this.categoria = categoria;
        this.estado = estado;
        this.sucursalActual = sucursalActual;
        this.kilometrajeActual = kilometrajeActual;
        this.vencimientoSeguro = vencimientoSeguro;
        this.vencimientoPatente = vencimientoPatente;
    }

    public int getIdVehiculo() { return idVehiculo; }
    public void setIdVehiculo(int idVehiculo) { this.idVehiculo = idVehiculo; }

    public String getPatente() { return patente; }
    public void setPatente(String patente) { this.patente = patente; }

    public ModeloVehiculo getModelo() { return modelo; }
    public void setModelo(ModeloVehiculo modelo) { this.modelo = modelo; }

    public CategoriaVehiculo getCategoria() { return categoria; }
    public void setCategoria(CategoriaVehiculo categoria) { this.categoria = categoria; }

    public String getEstado() { return estado; }
    public void setEstado(String estado) { this.estado = estado; }

    public Sucursal getSucursalActual() { return sucursalActual; }
    public void setSucursalActual(Sucursal sucursalActual) { this.sucursalActual = sucursalActual; }

    public int getKilometrajeActual() { return kilometrajeActual; }
    public void setKilometrajeActual(int kilometrajeActual) { this.kilometrajeActual = kilometrajeActual; }

    public LocalDate getVencimientoSeguro() { return vencimientoSeguro; }
    public void setVencimientoSeguro(LocalDate vencimientoSeguro) { this.vencimientoSeguro = vencimientoSeguro; }

    public LocalDate getVencimientoPatente() { return vencimientoPatente; }
    public void setVencimientoPatente(LocalDate vencimientoPatente) { this.vencimientoPatente = vencimientoPatente; }
}