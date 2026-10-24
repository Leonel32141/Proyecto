package controllers;

import modelo.Usuario;
import java.util.ArrayList;
import java.util.List;

public class UsuarioController {

    // Método para obtener la lista de usuarios
    public List<Usuario> obtenerUsuarios() {
        List<Usuario> lista = new ArrayList<>();
        // Acá después conectamos con el DAO para traerlos de la base de datos
        return lista;
    }

    public void registrarUsuario(Usuario usuario) {
        // Lógica para registrar un nuevo usuario
        System.out.println("Usuario registrado correctamente.");
    }
}