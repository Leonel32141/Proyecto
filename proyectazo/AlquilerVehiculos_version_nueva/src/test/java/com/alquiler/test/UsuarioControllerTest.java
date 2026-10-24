package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.UsuarioController;
import factory.UsuarioFactory;
import modelo.Usuario;
import java.util.List;

public class UsuarioControllerTest {

    @Test
    public void testCrearUsuarioControllerConFactory() {
        UsuarioController controller = UsuarioFactory.crearUsuarioController();
        assertNotNull(controller, "El controlador de usuario creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerUsuarios() {
        UsuarioController controller = new UsuarioController();
        List<Usuario> usuarios = controller.obtenerUsuarios();
        
        assertNotNull(usuarios, "La lista de usuarios no debe ser nula.");
        assertTrue(usuarios.isEmpty(), "La lista inicial de usuarios debería estar vacía.");
    }
}