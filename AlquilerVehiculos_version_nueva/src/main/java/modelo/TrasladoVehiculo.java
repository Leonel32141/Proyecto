package modelo;

import java.time.LocalDateTime;

public class TrasladoVehiculo {
    private int idTraslado;
    private Vehiculo vehiculo;
    private Sucursal sucursalOrigen;
    private Sucursal sucursalDestino;
    private Usuario choferAsignado;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private String estadoTraslado;

    public TrasladoVehiculo() {}

    public TrasladoVehiculo(int idTraslado, Vehiculo vehiculo, Sucursal sucursalOrigen, 
                            Sucursal sucursalDestino, Usuario choferAsignado, 
                            LocalDateTime fechaSalida, LocalDateTime fechaLlegada, String estadoTraslado) {
        this.idTraslado = idTraslado;
        this.vehiculo = vehiculo;
        this.sucursalOrigen = sucursalOrigen;
        this.sucursalDestino = sucursalDestino;
        this.choferAsignado = choferAsignado;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.estadoTraslado = estadoTraslado;
    }

    public int getIdTraslado() { return idTraslado; }
    public void setIdTraslado(int idTraslado) { this.idTraslado = idTraslado; }

    public Vehiculo getVehiculo() { return vehiculo; }
    public void setVehiculo(Vehiculo vehiculo) { this.vehiculo = vehiculo; }

    public Sucursal getSucursalOrigen() { return sucursalOrigen; }
    public void setSucursalOrigen(Sucursal sucursalOrigen) { this.sucursalOrigen = sucursalOrigen; }

    public Sucursal getSucursalDestino() { return sucursalDestino; }
    public void setSucursalDestino(Sucursal sucursalDestino) { this.sucursalDestino = sucursalDestino; }

    public Usuario getChoferAsignado() { return choferAsignado; }
    public void setChoferAsignado(Usuario choferAsignado) { this.choferAsignado = choferAsignado; }

    public LocalDateTime getFechaSalida() { return fechaSalida; }
    public void setFechaSalida(LocalDateTime fechaSalida) { this.fechaSalida = fechaSalida; }

    public LocalDateTime getFechaLlegada() { return fechaLlegada; }
    public void setFechaLlegada(LocalDateTime fechaLlegada) { this.fechaLlegada = fechaLlegada; }

    public String getEstadoTraslado() { return estadoTraslado; }
    public void setEstadoTraslado(String estadoTraslado) { this.estadoTraslado = estadoTraslado; }
}