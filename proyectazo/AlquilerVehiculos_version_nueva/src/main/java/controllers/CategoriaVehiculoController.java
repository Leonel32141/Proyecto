package controllers;

import modelo.CategoriaVehiculo;
import java.util.ArrayList;
import java.util.List;

public class CategoriaVehiculoController {

    public List<CategoriaVehiculo> obtenerCategoriasVehiculos() {
        List<CategoriaVehiculo> lista = new ArrayList<>();
        return lista;
    }

    public void registrarCategoriaVehiculo(CategoriaVehiculo categoriaVehiculo) {
        System.out.println("Categoría de vehículo registrada correctamente.");
    }
}