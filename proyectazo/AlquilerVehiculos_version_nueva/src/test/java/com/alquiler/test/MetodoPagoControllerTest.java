package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.MetodoPagoController;
import factory.MetodoPagoFactory;
import modelo.MetodoPago;
import java.util.List;

public class MetodoPagoControllerTest {

    @Test
    public void testCrearMetodoPagoControllerConFactory() {
        MetodoPagoController controller = MetodoPagoFactory.crearMetodoPagoController();
        assertNotNull(controller, "El controlador de método de pago creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerMetodosPago() {
        MetodoPagoController controller = new MetodoPagoController();
        List<MetodoPago> lista = controller.obtenerMetodosPago();
        
        assertNotNull(lista, "La lista de métodos de pago no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}