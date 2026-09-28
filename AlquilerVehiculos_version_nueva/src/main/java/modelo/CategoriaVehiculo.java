package modelo;

public class CategoriaVehiculo {
    private int idCategoria;
    private String nombreCategoria;
    private double tarifaDiariaBase;
    private double depositoGarantia;

    public CategoriaVehiculo() {}

    public CategoriaVehiculo(int idCategoria, String nombreCategoria, double tarifaDiariaBase, double depositoGarantia) {
        this.idCategoria = idCategoria;
        this.nombreCategoria = nombreCategoria;
        this.tarifaDiariaBase = tarifaDiariaBase;
        this.depositoGarantia = depositoGarantia;
    }

    public int getIdCategoria() { return idCategoria; }
    public void setIdCategoria(int idCategoria) { this.idCategoria = idCategoria; }

    public String getNombreCategoria() { return nombreCategoria; }
    public void setNombreCategoria(String nombreCategoria) { this.nombreCategoria = nombreCategoria; }

    public double getTarifaDiariaBase() { return tarifaDiariaBase; }
    public void setTarifaDiariaBase(double tarifaDiariaBase) { this.tarifaDiariaBase = tarifaDiariaBase; }

    public double getDepositoGarantia() { return depositoGarantia; }
    public void setDepositoGarantia(double depositoGarantia) { this.depositoGarantia = depositoGarantia; }
}