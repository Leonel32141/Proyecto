package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.ITrasladoVehiculoDao;
import factory.TrasladoVehiculoFactory;
import modelo.TrasladoVehiculo;

public class TrasladoVehiculoControllerTest {

    @Test
    public void testCrearDao() {
        ITrasladoVehiculoDao dao = TrasladoVehiculoFactory.crearTrasladoVehiculoDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        ITrasladoVehiculoDao dao = TrasladoVehiculoFactory.crearTrasladoVehiculoDao();
        List<TrasladoVehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testBuscarPorId() {
        ITrasladoVehiculoDao dao = TrasladoVehiculoFactory.crearTrasladoVehiculoDao();
        TrasladoVehiculo obj = dao.buscarPorId(1);
        if (obj != null) {
            assertEquals(1, obj.getIdTraslado());
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testModificar() {
        ITrasladoVehiculoDao dao = TrasladoVehiculoFactory.crearTrasladoVehiculoDao();
        TrasladoVehiculo obj = dao.buscarPorId(1);
        if (obj != null) {
            boolean resultado = dao.modificar(obj);
            assertTrue(resultado, "La modificación debe retornar true");
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarInexistente() {
        ITrasladoVehiculoDao dao = TrasladoVehiculoFactory.crearTrasladoVehiculoDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}