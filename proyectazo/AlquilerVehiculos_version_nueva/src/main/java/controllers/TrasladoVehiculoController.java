package controllers;

import modelo.TrasladoVehiculo;
import java.util.ArrayList;
import java.util.List;

public class TrasladoVehiculoController {

    public List<TrasladoVehiculo> obtenerTrasladosVehiculos() {
        List<TrasladoVehiculo> lista = new ArrayList<>();
        return lista;
    }

    public void registrarTrasladoVehiculo(TrasladoVehiculo trasladoVehiculo) {
        System.out.println("Traslado de vehículo registrado correctamente.");
    }
}