package controllers;

import modelo.Marca;
import java.util.ArrayList;
import java.util.List;

public class MarcaController {

    public List<Marca> obtenerMarcas() {
        List<Marca> lista = new ArrayList<>();
        return lista;
    }

    public void registrarMarca(Marca marca) {
        System.out.println("Marca registrada correctamente.");
    }
}