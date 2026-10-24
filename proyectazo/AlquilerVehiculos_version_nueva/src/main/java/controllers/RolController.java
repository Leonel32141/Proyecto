package controllers;

import modelo.Rol;
import java.util.ArrayList;
import java.util.List;

public class RolController {

    public List<Rol> obtenerRoles() {
        List<Rol> lista = new ArrayList<>();
        return lista;
    }

    public void registrarRol(Rol rol) {
        System.out.println("Rol registrado correctamente.");
    }
}