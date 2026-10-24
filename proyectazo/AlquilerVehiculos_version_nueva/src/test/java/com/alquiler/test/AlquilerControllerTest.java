package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.AlquilerController;
import factory.AlquilerFactory;
import modelo.Alquiler;
import java.util.List;

public class AlquilerControllerTest {

    @Test
    public void testCrearAlquilerControllerConFactory() {
        AlquilerController controller = AlquilerFactory.crearAlquilerController();
        assertNotNull(controller, "El controlador de alquiler creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerAlquileres() {
        AlquilerController controller = new AlquilerController();
        List<Alquiler> alquileres = controller.obtenerAlquileres();
        
        assertNotNull(alquileres, "La lista de alquileres no debe ser nula.");
        assertTrue(alquileres.isEmpty(), "La lista inicial de alquileres debería estar vacía.");
    }
}