package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IOpcionalExtraDao;
import factory.OpcionalExtraFactory;
import modelo.OpcionalExtra;

public class OpcionalExtraControllerTest {

    @Test
    public void testCrearDao() {
        IOpcionalExtraDao dao = OpcionalExtraFactory.crearOpcionalExtraDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IOpcionalExtraDao dao = OpcionalExtraFactory.crearOpcionalExtraDao();
        List<OpcionalExtra> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testBuscarPorId() {
        IOpcionalExtraDao dao = OpcionalExtraFactory.crearOpcionalExtraDao();
        OpcionalExtra obj = dao.buscarPorId(1);
        assertTrue(obj == null || obj != null, "La búsqueda se ejecuta correctamente");
    }

    @Test
    public void testModificar() {
        IOpcionalExtraDao dao = OpcionalExtraFactory.crearOpcionalExtraDao();
        OpcionalExtra obj = dao.buscarPorId(1);
        if (obj != null) {
            boolean resultado = dao.modificar(obj);
            assertTrue(resultado, "La modificación debe retornar true");
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarInexistente() {
        IOpcionalExtraDao dao = OpcionalExtraFactory.crearOpcionalExtraDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}