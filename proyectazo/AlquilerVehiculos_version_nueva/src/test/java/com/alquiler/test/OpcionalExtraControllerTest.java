package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.OpcionalExtraController;
import factory.OpcionalExtraFactory;
import modelo.OpcionalExtra;
import java.util.List;

public class OpcionalExtraControllerTest {

    @Test
    public void testCrearOpcionalExtraControllerConFactory() {
        OpcionalExtraController controller = OpcionalExtraFactory.crearOpcionalExtraController();
        assertNotNull(controller, "El controlador de opcional extra creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerOpcionalesExtras() {
        OpcionalExtraController controller = new OpcionalExtraController();
        List<OpcionalExtra> lista = controller.obtenerOpcionalesExtras();
        
        assertNotNull(lista, "La lista de opcionales extras no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}