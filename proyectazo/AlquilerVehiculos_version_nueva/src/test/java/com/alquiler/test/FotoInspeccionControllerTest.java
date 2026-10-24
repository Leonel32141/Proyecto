package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.FotoInspeccionController;
import factory.FotoInspeccionFactory;
import modelo.FotoInspeccion;
import java.util.List;

public class FotoInspeccionControllerTest {

    @Test
    public void testCrearFotoInspeccionControllerConFactory() {
        FotoInspeccionController controller = FotoInspeccionFactory.crearFotoInspeccionController();
        assertNotNull(controller, "El controlador de foto inspección creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerFotosInspeccion() {
        FotoInspeccionController controller = new FotoInspeccionController();
        List<FotoInspeccion> lista = controller.obtenerFotosInspeccion();
        
        assertNotNull(lista, "La lista de fotos de inspección no debe ser nula.");
        assertTrue(lista.isEmpty(), "La lista inicial debería estar vacía.");
    }
}