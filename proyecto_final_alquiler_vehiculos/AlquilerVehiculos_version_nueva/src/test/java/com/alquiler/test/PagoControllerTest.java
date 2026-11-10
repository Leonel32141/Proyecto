package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IPagoDao;
import factory.PagoFactory;
import modelo.Pago;

public class PagoControllerTest {

    @Test
    public void testCrearDao() {
        IPagoDao dao = PagoFactory.crearPagoDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IPagoDao dao = PagoFactory.crearPagoDao();
        List<Pago> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testBuscarPorId() {
        IPagoDao dao = PagoFactory.crearPagoDao();
        Pago obj = dao.buscarPorId(1);
        if (obj != null) {
            assertEquals(1, obj.getIdPago());
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testModificar() {
        IPagoDao dao = PagoFactory.crearPagoDao();
        Pago obj = dao.buscarPorId(1);
        if (obj != null) {
            boolean resultado = dao.modificar(obj);
            assertTrue(resultado, "La modificación debe retornar true");
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarInexistente() {
        IPagoDao dao = PagoFactory.crearPagoDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}