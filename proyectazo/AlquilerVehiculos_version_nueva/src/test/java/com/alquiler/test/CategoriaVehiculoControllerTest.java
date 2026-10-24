package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.CategoriaVehiculoController;
import factory.CategoriaVehiculoFactory;
import modelo.CategoriaVehiculo;
import java.util.List;

public class CategoriaVehiculoControllerTest {

    @Test
    public void testCrearCategoriaVehiculoControllerConFactory() {
        CategoriaVehiculoController controller = CategoriaVehiculoFactory.crearCategoriaVehiculoController();
        assertNotNull(controller, "El controlador de categoría de vehículo creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerCategoriasVehiculos() {
        CategoriaVehiculoController controller = new CategoriaVehiculoController();
        List<CategoriaVehiculo> lista = controller.obtenerCategoriasVehiculos();
        
        assertNotNull(lista, "La lista de categorías de vehículos no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}