package modelo;

public class MetodoPago {
    private int idMetodoPago;
    private String nombreMetodo;
    private boolean requiereComprobante;

    public MetodoPago() {}

    public MetodoPago(int idMetodoPago, String nombreMetodo, boolean requiereComprobante) {
        this.idMetodoPago = idMetodoPago;
        this.nombreMetodo = nombreMetodo;
        this.requiereComprobante = requiereComprobante;
    }

    public int getIdMetodoPago() { return idMetodoPago; }
    public void setIdMetodoPago(int idMetodoPago) { this.idMetodoPago = idMetodoPago; }

    public String getNombreMetodo() { return nombreMetodo; }
    public void setNombreMetodo(String nombreMetodo) { this.nombreMetodo = nombreMetodo; }

    public boolean isRequiereComprobante() { return requiereComprobante; }
    public void setRequiereComprobante(boolean requiereComprobante) { this.requiereComprobante = requiereComprobante; }
}