package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.VehiculoController;
import factory.VehiculoFactory;
import modelo.Vehiculo;
import java.util.List;

public class VehiculoControllerTest {

    @Test
    public void testCrearControladorConFactory() {
        // Verificamos que la fábrica devuelva el controlador correctamente
        VehiculoController controller = VehiculoFactory.crearVehiculoController();
        assertNotNull(controller, "El controlador creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerVehiculos() {
        // Verificamos que el controlador devuelva la lista de vehículos sin romper
        VehiculoController controller = new VehiculoController();
        List<Vehiculo> vehiculos = controller.obtenerVehiculos();
        
        assertNotNull(vehiculos, "La lista de vehículos no debe ser nula.");
        assertTrue(vehiculos.isEmpty(), "La lista inicial debería estar vacía o con elementos de prueba.");
    }
}