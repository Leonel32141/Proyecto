package controllers;

import modelo.AlquilerExtra;
import java.util.ArrayList;
import java.util.List;

public class AlquilerExtraController {

    public List<AlquilerExtra> obtenerAlquileresExtras() {
        List<AlquilerExtra> lista = new ArrayList<>();
        return lista;
    }

    public void registrarAlquilerExtra(AlquilerExtra alquilerExtra) {
        System.out.println("Alquiler extra registrado correctamente.");
    }
}