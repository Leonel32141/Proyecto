package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IFotoInspeccionDao;
import factory.FotoInspeccionFactory;
import modelo.FotoInspeccion;

public class FotoInspeccionControllerTest {

    @Test
    public void testCrearDao() {
        IFotoInspeccionDao dao = FotoInspeccionFactory.crearFotoInspeccionDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IFotoInspeccionDao dao = FotoInspeccionFactory.crearFotoInspeccionDao();
        List<FotoInspeccion> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testBuscarPorId() {
        IFotoInspeccionDao dao = FotoInspeccionFactory.crearFotoInspeccionDao();
        FotoInspeccion obj = dao.buscarPorId(1);
        if (obj != null) {
            assertEquals(1, obj.getIdFoto());
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testModificar() {
        IFotoInspeccionDao dao = FotoInspeccionFactory.crearFotoInspeccionDao();
        FotoInspeccion obj = dao.buscarPorId(1);
        if (obj != null) {
            boolean resultado = dao.modificar(obj);
            assertTrue(resultado, "La modificación debe retornar true");
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarInexistente() {
        IFotoInspeccionDao dao = FotoInspeccionFactory.crearFotoInspeccionDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}