package modelo;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.Objects;

public class Pago {

    private int idPago;
    private int idAlquiler;
    private int idMetodoPago;
    private BigDecimal monto;
    private LocalDateTime fechaPago;
    private String estadoPago;
    private String numeroComprobante;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Pago() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Pago(int idAlquiler, int idMetodoPago, BigDecimal monto, 
                LocalDateTime fechaPago, String estadoPago, String numeroComprobante) {
        this.idAlquiler = idAlquiler;
        this.idMetodoPago = idMetodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estadoPago = estadoPago;
        this.numeroComprobante = numeroComprobante;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Pago(int idPago, int idAlquiler, int idMetodoPago, BigDecimal monto, 
                LocalDateTime fechaPago, String estadoPago, String numeroComprobante) {
        this.idPago = idPago;
        this.idAlquiler = idAlquiler;
        this.idMetodoPago = idMetodoPago;
        this.monto = monto;
        this.fechaPago = fechaPago;
        this.estadoPago = estadoPago;
        this.numeroComprobante = numeroComprobante;
    }

    // Getters y Setters
    public int getIdPago() {
        return idPago;
    }

    public void setIdPago(int idPago) {
        this.idPago = idPago;
    }

    public int getIdAlquiler() {
        return idAlquiler;
    }

    public void setIdAlquiler(int idAlquiler) {
        this.idAlquiler = idAlquiler;
    }

    public int getIdMetodoPago() {
        return idMetodoPago;
    }

    public void setIdMetodoPago(int idMetodoPago) {
        this.idMetodoPago = idMetodoPago;
    }

    public BigDecimal getMonto() {
        return monto;
    }

    public void setMonto(BigDecimal monto) {
        this.monto = monto;
    }

    public LocalDateTime getFechaPago() {
        return fechaPago;
    }

    public void setFechaPago(LocalDateTime fechaPago) {
        this.fechaPago = fechaPago;
    }

    public String getEstadoPago() {
        return estadoPago;
    }

    public void setEstadoPago(String estadoPago) {
        this.estadoPago = estadoPago;
    }

    public String getNumeroComprobante() {
        return numeroComprobante;
    }

    public void setNumeroComprobante(String numeroComprobante) {
        this.numeroComprobante = numeroComprobante;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Pago pago = (Pago) o;
        return idPago == pago.idPago;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idPago);
    }

    @Override
    public String toString() {
        return "Pago{" +
                "idPago=" + idPago +
                ", idAlquiler=" + idAlquiler +
                ", monto=" + monto +
                ", estadoPago='" + estadoPago + '\'' +
                '}';
    }
}