package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.ISucursalDao;
import factory.SucursalFactory;
import modelo.Sucursal;

public class SucursalControllerTest {

    @Test
    public void testCrearDao() {
        ISucursalDao dao = SucursalFactory.crearSucursalDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        ISucursalDao dao = SucursalFactory.crearSucursalDao();
        List<Sucursal> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty(), "La lista debe contener elementos");
    }

    @Test
    public void testBuscarPorId() {
        ISucursalDao dao = SucursalFactory.crearSucursalDao();
        Sucursal obj = dao.buscarPorId(1);
        assertNotNull(obj, "El registro con ID 1 debe existir");
        assertEquals(1, obj.getIdSucursal(), "El ID debe coincidir");
    }

    @Test
    public void testModificar() {
        ISucursalDao dao = SucursalFactory.crearSucursalDao();
        Sucursal obj = dao.buscarPorId(1);
        assertNotNull(obj);
        boolean resultado = dao.modificar(obj);
        assertTrue(resultado, "La modificación debe retornar true");
    }

    @Test
    public void testEliminarInexistente() {
        ISucursalDao dao = SucursalFactory.crearSucursalDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}