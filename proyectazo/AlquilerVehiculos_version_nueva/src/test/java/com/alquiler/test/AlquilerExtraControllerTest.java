package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.AlquilerExtraController;
import factory.AlquilerExtraFactory;
import modelo.AlquilerExtra;
import java.util.List;

public class AlquilerExtraControllerTest {

    @Test
    public void testCrearAlquilerExtraControllerConFactory() {
        AlquilerExtraController controller = AlquilerExtraFactory.crearAlquilerExtraController();
        assertNotNull(controller, "El controlador de alquiler extra creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerAlquileresExtras() {
        AlquilerExtraController controller = new AlquilerExtraController();
        List<AlquilerExtra> lista = controller.obtenerAlquileresExtras();
        
        assertNotNull(lista, "La lista de alquileres extras no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}