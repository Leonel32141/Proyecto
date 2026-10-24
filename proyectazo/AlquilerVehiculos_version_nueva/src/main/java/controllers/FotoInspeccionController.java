package controllers;

import modelo.FotoInspeccion;
import java.util.ArrayList;
import java.util.List;

public class FotoInspeccionController {

    public List<FotoInspeccion> obtenerFotosInspeccion() {
        List<FotoInspeccion> lista = new ArrayList<>();
        return lista;
    }

    public void registrarFotoInspeccion(FotoInspeccion fotoInspeccion) {
        System.out.println("Foto de inspección registrada correctamente.");
    }
}