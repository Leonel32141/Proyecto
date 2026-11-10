package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IModeloVehiculoDao;
import factory.ModeloVehiculoFactory;
import modelo.ModeloVehiculo;

public class ModeloVehiculoControllerTest {

    @Test
    public void testCrearDao() {
        IModeloVehiculoDao dao = ModeloVehiculoFactory.crearModeloVehiculoDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IModeloVehiculoDao dao = ModeloVehiculoFactory.crearModeloVehiculoDao();
        List<ModeloVehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty());
    }

    @Test
    public void testBuscarPorId() {
        IModeloVehiculoDao dao = ModeloVehiculoFactory.crearModeloVehiculoDao();
        ModeloVehiculo obj = dao.buscarPorId(1);
        assertNotNull(obj, "Debe existir el modelo con ID 1");
        assertEquals(1, obj.getIdModelo(), "El ID debe coincidir");
    }

    @Test
    public void testModificar() {
        IModeloVehiculoDao dao = ModeloVehiculoFactory.crearModeloVehiculoDao();
        ModeloVehiculo obj = dao.buscarPorId(1);
        assertNotNull(obj);
        boolean resultado = dao.modificar(obj);
        assertTrue(resultado, "La modificación debe retornar true");
    }

    @Test
    public void testEliminarInexistente() {
        IModeloVehiculoDao dao = ModeloVehiculoFactory.crearModeloVehiculoDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}