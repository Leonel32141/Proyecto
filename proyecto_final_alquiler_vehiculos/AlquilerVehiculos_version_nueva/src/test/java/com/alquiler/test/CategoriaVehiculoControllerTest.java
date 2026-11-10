package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.ICategoriaVehiculoDao;
import factory.CategoriaVehiculoFactory;
import modelo.CategoriaVehiculo;

public class CategoriaVehiculoControllerTest {

    @Test
    public void testCrearDao() {
        ICategoriaVehiculoDao dao = CategoriaVehiculoFactory.crearCategoriaVehiculoDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        ICategoriaVehiculoDao dao = CategoriaVehiculoFactory.crearCategoriaVehiculoDao();
        List<CategoriaVehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty(), "Debe haber registros");
    }

    @Test
    public void testBuscarPorId() {
        ICategoriaVehiculoDao dao = CategoriaVehiculoFactory.crearCategoriaVehiculoDao();
        CategoriaVehiculo obj = dao.buscarPorId(1);
        assertNotNull(obj, "Debe existir el registro con ID 1");
        assertEquals(1, obj.getIdCategoria(), "El ID debe coincidir");
    }

    @Test
    public void testModificar() {
        ICategoriaVehiculoDao dao = CategoriaVehiculoFactory.crearCategoriaVehiculoDao();
        CategoriaVehiculo obj = dao.buscarPorId(1);
        assertNotNull(obj);
        boolean resultado = dao.modificar(obj);
        assertTrue(resultado, "La modificación debe retornar true");
    }

    @Test
    public void testEliminarInexistente() {
        ICategoriaVehiculoDao dao = CategoriaVehiculoFactory.crearCategoriaVehiculoDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}