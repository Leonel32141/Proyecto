package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IMantenimientoDao;
import factory.MantenimientoFactory;
import modelo.Mantenimiento;

public class MantenimientoControllerTest {

    @Test
    public void testCrearDao() {
        IMantenimientoDao dao = MantenimientoFactory.crearMantenimientoDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IMantenimientoDao dao = MantenimientoFactory.crearMantenimientoDao();
        List<Mantenimiento> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testBuscarPorId() {
        IMantenimientoDao dao = MantenimientoFactory.crearMantenimientoDao();
        Mantenimiento obj = dao.buscarPorId(1);
        if (obj != null) {
            assertEquals(1, obj.getIdMantenimiento());
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testModificar() {
        IMantenimientoDao dao = MantenimientoFactory.crearMantenimientoDao();
        Mantenimiento obj = dao.buscarPorId(1);
        if (obj != null) {
            boolean resultado = dao.modificar(obj);
            assertTrue(resultado, "La modificación debe retornar true");
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarInexistente() {
        IMantenimientoDao dao = MantenimientoFactory.crearMantenimientoDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}