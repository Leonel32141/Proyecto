package modelo;

import java.time.LocalDateTime;

public class Pago {
    private int idPago;
    private Alquiler alquiler;
    private MetodoPago metodoPago;
    private double monto;
    private LocalDateTime fechaPago;
    private String estadoPago;

    public Pago() {}

    public Pago(int idPago, Alquiler alquiler, MetodoPago metodoPago, double monto, String estadoPago) {
        this.idPago = idPago;
        this.alquiler = alquiler;
        this.metodoPago = metodoPago;
        this.monto = monto;
        this.fechaPago = LocalDateTime.now();
        this.estadoPago = estadoPago;
    }

    public int getIdPago() { return idPago; }
    public void setIdPago(int idPago) { this.idPago = idPago; }

    public Alquiler getAlquiler() { return alquiler; }
    public void setAlquiler(Alquiler alquiler) { this.alquiler = alquiler; }

    public MetodoPago getMetodoPago() { return metodoPago; }
    public void setMetodoPago(MetodoPago metodoPago) { this.metodoPago = metodoPago; }

    public double getMonto() { return monto; }
    public void setMonto(double monto) { this.monto = monto; }

    public LocalDateTime getFechaPago() { return fechaPago; }
    public void setFechaPago(LocalDateTime fechaPago) { this.fechaPago = fechaPago; }

    public String getEstadoPago() { return estadoPago; }
    public void setEstadoPago(String estadoPago) { this.estadoPago = estadoPago; }
}