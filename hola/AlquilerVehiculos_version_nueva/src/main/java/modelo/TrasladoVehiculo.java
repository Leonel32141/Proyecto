package modelo;

import java.time.LocalDateTime;
import java.util.Objects;

public class TrasladoVehiculo {

    private int idTraslado;
    private int idVehiculo;
    private int idSucursalOrigen;
    private int idSucursalDestino;
    private int idUsuarioChofer;
    private LocalDateTime fechaSalida;
    private LocalDateTime fechaLlegada;
    private String observaciones;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public TrasladoVehiculo() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public TrasladoVehiculo(int idVehiculo, int idSucursalOrigen, int idSucursalDestino, 
                            int idUsuarioChofer, LocalDateTime fechaSalida, 
                            LocalDateTime fechaLlegada, String observaciones) {
        this.idVehiculo = idVehiculo;
        this.idSucursalOrigen = idSucursalOrigen;
        this.idSucursalDestino = idSucursalDestino;
        this.idUsuarioChofer = idUsuarioChofer;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.observaciones = observaciones;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public TrasladoVehiculo(int idTraslado, int idVehiculo, int idSucursalOrigen, int idSucursalDestino, 
                            int idUsuarioChofer, LocalDateTime fechaSalida, 
                            LocalDateTime fechaLlegada, String observaciones) {
        this.idTraslado = idTraslado;
        this.idVehiculo = idVehiculo;
        this.idSucursalOrigen = idSucursalOrigen;
        this.idSucursalDestino = idSucursalDestino;
        this.idUsuarioChofer = idUsuarioChofer;
        this.fechaSalida = fechaSalida;
        this.fechaLlegada = fechaLlegada;
        this.observaciones = observaciones;
    }

    // Getters y Setters
    public int getIdTraslado() {
        return idTraslado;
    }

    public void setIdTraslado(int idTraslado) {
        this.idTraslado = idTraslado;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public int getIdSucursalOrigen() {
        return idSucursalOrigen;
    }

    public void setIdSucursalOrigen(int idSucursalOrigen) {
        this.idSucursalOrigen = idSucursalOrigen;
    }

    public int getIdSucursalDestino() {
        return idSucursalDestino;
    }

    public void setIdSucursalDestino(int idSucursalDestino) {
        this.idSucursalDestino = idSucursalDestino;
    }

    public int getIdUsuarioChofer() {
        return idUsuarioChofer;
    }

    public void setIdUsuarioChofer(int idUsuarioChofer) {
        this.idUsuarioChofer = idUsuarioChofer;
    }

    public LocalDateTime getFechaSalida() {
        return fechaSalida;
    }

    public void setFechaSalida(LocalDateTime fechaSalida) {
        this.fechaSalida = fechaSalida;
    }

    public LocalDateTime getFechaLlegada() {
        return fechaLlegada;
    }

    public void setFechaLlegada(LocalDateTime fechaLlegada) {
        this.fechaLlegada = fechaLlegada;
    }

    public String getObservaciones() {
        return observaciones;
    }

    public void setObservaciones(String observaciones) {
        this.observaciones = observaciones;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        TrasladoVehiculo that = (TrasladoVehiculo) o;
        return idTraslado == that.idTraslado;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idTraslado);
    }

    @Override
    public String toString() {
        return "TrasladoVehiculo{" +
                "idTraslado=" + idTraslado +
                ", idVehiculo=" + idVehiculo +
                ", idSucursalOrigen=" + idSucursalOrigen +
                ", idSucursalDestino=" + idSucursalDestino +
                '}';
    }
}