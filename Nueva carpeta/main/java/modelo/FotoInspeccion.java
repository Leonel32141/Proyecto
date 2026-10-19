package modelo;

public class FotoInspeccion {
    private int idFoto;
    private CheckinCheckout inspeccion;
    private String urlFoto;
    private String descripcion;

    public FotoInspeccion() {}

    public FotoInspeccion(int idFoto, CheckinCheckout inspeccion, String urlFoto, String descripcion) {
        this.idFoto = idFoto;
        this.inspeccion = inspeccion;
        this.urlFoto = urlFoto;
        this.descripcion = descripcion;
    }

    public int getIdFoto() { return idFoto; }
    public void setIdFoto(int idFoto) { this.idFoto = idFoto; }

    public CheckinCheckout getInspeccion() { return inspeccion; }
    public void setInspeccion(CheckinCheckout inspeccion) { this.inspeccion = inspeccion; }

    public String getUrlFoto() { return urlFoto; }
    public void setUrlFoto(String urlFoto) { this.urlFoto = urlFoto; }

    public String getDescripcion() { return descripcion; }
    public void setDescripcion(String descripcion) { this.descripcion = descripcion; }
}