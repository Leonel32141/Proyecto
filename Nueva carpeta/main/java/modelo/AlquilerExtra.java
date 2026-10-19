package modelo;

public class AlquilerExtra {
    private Alquiler alquiler;
    private OpcionalExtra extra;
    private int cantidad;
    private double precioUnitarioHistorico;

    public AlquilerExtra() {}

    public AlquilerExtra(Alquiler alquiler, OpcionalExtra extra, int cantidad, double precioUnitarioHistorico) {
        this.alquiler = alquiler;
        this.extra = extra;
        this.cantidad = cantidad;
        this.precioUnitarioHistorico = precioUnitarioHistorico;
    }

    public Alquiler getAlquiler() { return alquiler; }
    public void setAlquiler(Alquiler alquiler) { this.alquiler = alquiler; }

    public OpcionalExtra getExtra() { return extra; }
    public void setExtra(OpcionalExtra extra) { this.extra = extra; }

    public int getCantidad() { return cantidad; }
    public void setCantidad(int cantidad) { this.cantidad = cantidad; }

    public double getPrecioUnitarioHistorico() { return precioUnitarioHistorico; }
    public void setPrecioUnitarioHistorico(double precioUnitarioHistorico) { this.precioUnitarioHistorico = precioUnitarioHistorico; }
}