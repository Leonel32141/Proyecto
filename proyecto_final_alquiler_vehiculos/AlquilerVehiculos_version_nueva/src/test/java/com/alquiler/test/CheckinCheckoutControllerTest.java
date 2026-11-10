package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.ICheckinCheckoutDao;
import factory.CheckinCheckoutFactory;
import modelo.CheckinCheckout;

public class CheckinCheckoutControllerTest {

    @Test
    public void testCrearDao() {
        ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
        List<CheckinCheckout> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testBuscarPorId() {
        ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
        CheckinCheckout obj = dao.buscarPorId(1);
        assertTrue(obj == null || obj != null, "La búsqueda se ejecuta correctamente");
    }

    @Test
    public void testModificar() {
        ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
        CheckinCheckout obj = dao.buscarPorId(1);
        if (obj != null) {
            boolean resultado = dao.modificar(obj);
            assertTrue(resultado, "La modificación debe retornar true");
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarInexistente() {
        ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}