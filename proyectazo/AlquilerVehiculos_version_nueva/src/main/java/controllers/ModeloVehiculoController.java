package controllers;

import modelo.ModeloVehiculo;
import java.util.ArrayList;
import java.util.List;

public class ModeloVehiculoController {

    public List<ModeloVehiculo> obtenerModelosVehiculos() {
        List<ModeloVehiculo> lista = new ArrayList<>();
        return lista;
    }

    public void registrarModeloVehiculo(ModeloVehiculo modeloVehiculo) {
        System.out.println("Modelo de vehículo registrado correctamente.");
    }
}