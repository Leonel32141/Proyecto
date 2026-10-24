package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.MarcaController;
import factory.MarcaFactory;
import modelo.Marca;
import java.util.List;

public class MarcaControllerTest {

    @Test
    public void testCrearMarcaControllerConFactory() {
        MarcaController controller = MarcaFactory.crearMarcaController();
        assertNotNull(controller, "El controlador de marca creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerMarcas() {
        MarcaController controller = new MarcaController();
        List<Marca> lista = controller.obtenerMarcas();
        
        assertNotNull(lista, "La lista de marcas no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}