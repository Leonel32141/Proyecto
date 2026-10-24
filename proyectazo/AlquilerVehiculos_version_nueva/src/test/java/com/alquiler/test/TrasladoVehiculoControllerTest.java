package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.TrasladoVehiculoController;
import factory.TrasladoVehiculoFactory;
import modelo.TrasladoVehiculo;
import java.util.List;

public class TrasladoVehiculoControllerTest {

    @Test
    public void testCrearTrasladoVehiculoControllerConFactory() {
        TrasladoVehiculoController controller = TrasladoVehiculoFactory.crearTrasladoVehiculoController();
        assertNotNull(controller, "El controlador de traslado de vehículo creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerTrasladosVehiculos() {
        TrasladoVehiculoController controller = new TrasladoVehiculoController();
        List<TrasladoVehiculo> lista = controller.obtenerTrasladosVehiculos();
        
        assertNotNull(lista, "La lista de traslados de vehículos no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}