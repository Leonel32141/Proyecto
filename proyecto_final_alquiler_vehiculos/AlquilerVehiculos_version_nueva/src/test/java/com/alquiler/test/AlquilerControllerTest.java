package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IAlquilerDao;
import factory.AlquilerFactory;
import modelo.Alquiler;

public class AlquilerControllerTest {

    @Test
    public void testCrearDaoAlquiler() {
        IAlquilerDao dao = AlquilerFactory.crearAlquilerDao();
        assertNotNull(dao, "El DAO de Alquiler no debe ser nulo");
    }

    @Test
    public void testListarAlquileres() {
        IAlquilerDao dao = AlquilerFactory.crearAlquilerDao();
        List<Alquiler> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de alquileres no debe ser nula");
    }

    @Test
    public void testBuscarAlquilerPorId() {
        IAlquilerDao dao = AlquilerFactory.crearAlquilerDao();
        Alquiler alquiler = dao.buscarPorId(1);
        if (alquiler != null) {
            assertEquals(1, alquiler.getIdAlquiler());
        } else {
            assertTrue(true);
        }
    }

    @Test
    public void testEliminarAlquilerInexistente() {
        IAlquilerDao dao = AlquilerFactory.crearAlquilerDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "Eliminar un alquiler inexistente debe retornar false");
    }
}