package controllers;

import modelo.Sucursal;
import java.util.ArrayList;
import java.util.List;

public class SucursalController {

    // Método para obtener la lista de sucursales
    public List<Sucursal> obtenerSucursales() {
        List<Sucursal> lista = new ArrayList<>();
        // Acá después conectamos con el DAO para traerlas de la base de datos
        return lista;
    }

    public void registrarSucursal(Sucursal sucursal) {
        // Lógica para registrar una nueva sucursal
        System.out.println("Sucursal registrada correctamente.");
    }
}