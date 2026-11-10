package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IRolDao;
import factory.RolFactory;
import modelo.Rol;

public class RolControllerTest {

    @Test
    public void testCrearDao() {
        IRolDao dao = RolFactory.crearRolDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IRolDao dao = RolFactory.crearRolDao();
        List<Rol> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty(), "Debe haber roles en el sistema");
    }

    @Test
    public void testBuscarPorId() {
        IRolDao dao = RolFactory.crearRolDao();
        Rol obj = dao.buscarPorId(1);
        assertNotNull(obj, "Debe existir el rol con ID 1");
        assertEquals(1, obj.getIdRol(), "El ID debe coincidir");
    }

    @Test
    public void testModificar() {
        IRolDao dao = RolFactory.crearRolDao();
        Rol obj = dao.buscarPorId(1);
        assertNotNull(obj);
        boolean resultado = dao.modificar(obj);
        assertTrue(resultado, "La modificación debe retornar true");
    }

    @Test
    public void testEliminarInexistente() {
        IRolDao dao = RolFactory.crearRolDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}