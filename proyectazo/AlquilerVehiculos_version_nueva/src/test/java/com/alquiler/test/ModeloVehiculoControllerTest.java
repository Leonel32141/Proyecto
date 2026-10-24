package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.ModeloVehiculoController;
import factory.ModeloVehiculoFactory;
import modelo.ModeloVehiculo;
import java.util.List;

public class ModeloVehiculoControllerTest {

    @Test
    public void testCrearModeloVehiculoControllerConFactory() {
        ModeloVehiculoController controller = ModeloVehiculoFactory.crearModeloVehiculoController();
        assertNotNull(controller, "El controlador de modelo de vehículo creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerModelosVehiculos() {
        ModeloVehiculoController controller = new ModeloVehiculoController();
        List<ModeloVehiculo> lista = controller.obtenerModelosVehiculos();
        
        assertNotNull(lista, "La lista de modelos de vehículos no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}