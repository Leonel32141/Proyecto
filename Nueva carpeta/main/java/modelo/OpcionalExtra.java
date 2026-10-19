package modelo;

public class OpcionalExtra {
    private int idExtra;
    private String nombreExtra;
    private String descripcion;
    private double precioPorDia;

    public OpcionalExtra() {}

    public OpcionalExtra(int idExtra, String nombreExtra, String descripcion, double precioPorDia) {
        this.idExtra = idExtra;
        this.nombreExtra = nombreExtra;
        this.descripcion = descripcion;
        this.precioPorDia = precioPorDia;
    }

    public int getIdExtra() { return idExtra; }
    public void setIdExtra(int idExtra) { this.idExtra = idExtra; }

    public String getNombreExtra() { return nombreExtra; }
    public void setNombreExtra(String nombreExtra) { this.nombreExtra = nombreExtra; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }

    public double getPrecioPorDia() { return precioPorDia; }
    public void setPrecioPorDia(double precioPorDia) { this.precioPorDia = precioPorDia; }
}