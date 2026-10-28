package modelo;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;

public class Mantenimiento {

    private int idMantenimiento;
    private int idVehiculo;
    private String tipoMantenimiento;
    private LocalDate fechaIngreso;
    private LocalDate fechaEgresoEstimada;
    private BigDecimal costoTotal;
    private String tallerMecanico;
    private String detalleTrabajo;

    // 1. Constructor vacío (Obligatorio para Jackson / JSON)
    public Mantenimiento() {
    }

    // 2. Constructor sin ID (Para registros nuevos)
    public Mantenimiento(int idVehiculo, String tipoMantenimiento, LocalDate fechaIngreso, 
                         LocalDate fechaEgresoEstimada, BigDecimal costoTotal, 
                         String tallerMecanico, String detalleTrabajo) {
        this.idVehiculo = idVehiculo;
        this.tipoMantenimiento = tipoMantenimiento;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgresoEstimada = fechaEgresoEstimada;
        this.costoTotal = costoTotal;
        this.tallerMecanico = tallerMecanico;
        this.detalleTrabajo = detalleTrabajo;
    }

    // 3. Constructor con ID (Para consultas desde la BD)
    public Mantenimiento(int idMantenimiento, int idVehiculo, String tipoMantenimiento, LocalDate fechaIngreso, 
                         LocalDate fechaEgresoEstimada, BigDecimal costoTotal, 
                         String tallerMecanico, String detalleTrabajo) {
        this.idMantenimiento = idMantenimiento;
        this.idVehiculo = idVehiculo;
        this.tipoMantenimiento = tipoMantenimiento;
        this.fechaIngreso = fechaIngreso;
        this.fechaEgresoEstimada = fechaEgresoEstimada;
        this.costoTotal = costoTotal;
        this.tallerMecanico = tallerMecanico;
        this.detalleTrabajo = detalleTrabajo;
    }

    // Getters y Setters
    public int getIdMantenimiento() {
        return idMantenimiento;
    }

    public void setIdMantenimiento(int idMantenimiento) {
        this.idMantenimiento = idMantenimiento;
    }

    public int getIdVehiculo() {
        return idVehiculo;
    }

    public void setIdVehiculo(int idVehiculo) {
        this.idVehiculo = idVehiculo;
    }

    public String getTipoMantenimiento() {
        return tipoMantenimiento;
    }

    public void setTipoMantenimiento(String tipoMantenimiento) {
        this.tipoMantenimiento = tipoMantenimiento;
    }

    public LocalDate getFechaIngreso() {
        return fechaIngreso;
    }

    public void setFechaIngreso(LocalDate fechaIngreso) {
        this.fechaIngreso = fechaIngreso;
    }

    public LocalDate getFechaEgresoEstimada() {
        return fechaEgresoEstimada;
    }

    public void setFechaEgresoEstimada(LocalDate fechaEgresoEstimada) {
        this.fechaEgresoEstimada = fechaEgresoEstimada;
    }

    public BigDecimal getCostoTotal() {
        return costoTotal;
    }

    public void setCostoTotal(BigDecimal costoTotal) {
        this.costoTotal = costoTotal;
    }

    public String getTallerMecanico() {
        return tallerMecanico;
    }

    public void setTallerMecanico(String tallerMecanico) {
        this.tallerMecanico = tallerMecanico;
    }

    public String getDetalleTrabajo() {
        return detalleTrabajo;
    }

    public void setDetalleTrabajo(String detalleTrabajo) {
        this.detalleTrabajo = detalleTrabajo;
    }

    // hashCode, equals y toString
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Mantenimiento that = (Mantenimiento) o;
        return idMantenimiento == that.idMantenimiento;
    }

    @Override
    public int hashCode() {
        return Objects.hash(idMantenimiento);
    }

    @Override
    public String toString() {
        return "Mantenimiento{" +
                "idMantenimiento=" + idMantenimiento +
                ", idVehiculo=" + idVehiculo +
                ", tipoMantenimiento='" + tipoMantenimiento + '\'' +
                ", costoTotal=" + costoTotal +
                '}';
    }
}