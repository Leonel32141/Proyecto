package modelo;

public class ModeloVehiculo {
    private int idModelo;
    private Marca marca;
    private String nombreModelo;
    private int anio;

    public ModeloVehiculo() {}

    public ModeloVehiculo(int idModelo, Marca marca, String nombreModelo, int anio) {
        this.idModelo = idModelo;
        this.marca = marca;
        this.nombreModelo = nombreModelo;
        this.anio = anio;
    }

    public int getIdModelo() { return idModelo; }
    public void setIdModelo(int idModelo) { this.idModelo = idModelo; }

    public Marca getMarca() { return marca; }
    public void setMarca(Marca marca) { this.marca = marca; }

    public String getNombreModelo() { return nombreModelo; }
    public void setNombreModelo(String nombreModelo) { this.nombreModelo = nombreModelo; }

    public int getAnio() { return anio; }
    public void setAnio(int anio) { this.anio = anio; }
}