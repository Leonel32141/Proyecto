package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.CheckinCheckoutController;
import factory.CheckinCheckoutFactory;
import modelo.CheckinCheckout;
import java.util.List;

public class CheckinCheckoutControllerTest {

    @Test
    public void testCrearCheckinCheckoutControllerConFactory() {
        CheckinCheckoutController controller = CheckinCheckoutFactory.crearCheckinCheckoutController();
        assertNotNull(controller, "El controlador de checkin/checkout creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerCheckinsCheckouts() {
        CheckinCheckoutController controller = new CheckinCheckoutController();
        List<CheckinCheckout> lista = controller.obtenerCheckinsCheckouts();
        
        assertNotNull(lista, "La lista de checkins/checkouts no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}