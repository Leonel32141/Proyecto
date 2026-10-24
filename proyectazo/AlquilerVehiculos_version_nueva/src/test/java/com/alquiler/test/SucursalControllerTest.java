package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.SucursalController;
import factory.SucursalFactory;
import modelo.Sucursal;
import java.util.List;

public class SucursalControllerTest {

    @Test
    public void testCrearSucursalControllerConFactory() {
        SucursalController controller = SucursalFactory.crearSucursalController();
        assertNotNull(controller, "El controlador de sucursal creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerSucursales() {
        SucursalController controller = new SucursalController();
        List<Sucursal> sucursales = controller.obtenerSucursales();
        
        assertNotNull(sucursales, "La lista de sucursales no debe ser nula.");
        assertTrue(sucursales.isEmpty(), "La lista inicial de sucursales debería estar vacía.");
    }
}