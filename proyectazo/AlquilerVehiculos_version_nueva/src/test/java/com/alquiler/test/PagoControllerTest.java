package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.PagoController;
import factory.PagoFactory;
import modelo.Pago;
import java.util.List;

public class PagoControllerTest {

    @Test
    public void testCrearPagoControllerConFactory() {
        PagoController controller = PagoFactory.crearPagoController();
        assertNotNull(controller, "El controlador de pago creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerPagos() {
        PagoController controller = new PagoController();
        List<Pago> pagos = controller.obtenerPagos();
        
        assertNotNull(pagos, "La lista de pagos no debe ser nula.");
        assertTrue(pagos.isEmpty(), "La lista inicial de pagos debería estar vacía.");
    }
}