package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IMetodoPagoDao;
import factory.MetodoPagoFactory;
import modelo.MetodoPago;

public class MetodoPagoControllerTest {

    @Test
    public void testCrearDao() {
        IMetodoPagoDao dao = MetodoPagoFactory.crearMetodoPagoDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IMetodoPagoDao dao = MetodoPagoFactory.crearMetodoPagoDao();
        List<MetodoPago> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty(), "Debe haber métodos de pago cargados");
    }

    @Test
    public void testBuscarPorId() {
        IMetodoPagoDao dao = MetodoPagoFactory.crearMetodoPagoDao();
        MetodoPago obj = dao.buscarPorId(1);
        assertNotNull(obj, "Debe existir el registro con ID 1");
        assertEquals(1, obj.getIdMetodoPago(), "El ID debe coincidir");
    }

    @Test
    public void testModificar() {
        IMetodoPagoDao dao = MetodoPagoFactory.crearMetodoPagoDao();
        MetodoPago obj = dao.buscarPorId(1);
        assertNotNull(obj);
        boolean resultado = dao.modificar(obj);
        assertTrue(resultado, "La modificación debe retornar true");
    }

    @Test
    public void testEliminarInexistente() {
        IMetodoPagoDao dao = MetodoPagoFactory.crearMetodoPagoDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}