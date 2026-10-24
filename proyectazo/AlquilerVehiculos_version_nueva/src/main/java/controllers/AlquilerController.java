package controllers;

import modelo.Alquiler;
import java.util.ArrayList;
import java.util.List;

public class AlquilerController {

    // Método para obtener la lista de alquileres
    public List<Alquiler> obtenerAlquileres() {
        List<Alquiler> lista = new ArrayList<>();
        // Acá después conectamos con el DAO para traerlos de la base de datos
        return lista;
    }

    public void registrarAlquiler(Alquiler alquiler) {
        // Lógica para registrar un nuevo alquiler
        System.out.println("Alquiler registrado correctamente.");
    }
}