package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.RolController;
import factory.RolFactory;
import modelo.Rol;
import java.util.List;

public class RolControllerTest {

    @Test
    public void testCrearRolControllerConFactory() {
        RolController controller = RolFactory.crearRolController();
        assertNotNull(controller, "El controlador de rol creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerRoles() {
        RolController controller = new RolController();
        List<Rol> lista = controller.obtenerRoles();
        
        assertNotNull(lista, "La lista de roles no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}