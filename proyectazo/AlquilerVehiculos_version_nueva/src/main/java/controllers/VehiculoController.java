package controllers;

import modelo.Vehiculo;
import java.util.ArrayList;
import java.util.List;

public class VehiculoController {

    // Método de ejemplo para manejar la lógica de vehículos
    public List<Vehiculo> obtenerVehiculos() {
        List<Vehiculo> lista = new ArrayList<>();
        // Acá después conectamos con el DAO para traerlos de la base de datos
        return lista;
    }

    public void registrarVehiculo(Vehiculo vehiculo) {
        // Lógica para dar de alta un vehículo
        System.out.println("Vehículo registrado correctamente.");
    }
}