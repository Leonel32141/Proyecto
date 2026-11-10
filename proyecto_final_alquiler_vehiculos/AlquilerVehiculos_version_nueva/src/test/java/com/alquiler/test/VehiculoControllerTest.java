package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IVehiculoDao;
import factory.VehiculoFactory;
import modelo.Vehiculo;

public class VehiculoControllerTest {

    @Test
    public void testCrearDao() {
        IVehiculoDao dao = VehiculoFactory.crearVehiculoDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IVehiculoDao dao = VehiculoFactory.crearVehiculoDao();
        List<Vehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula");
        assertFalse(lista.isEmpty(), "Debe haber vehículos cargados");
    }

    @Test
    public void testBuscarPorId() {
        IVehiculoDao dao = VehiculoFactory.crearVehiculoDao();
        Vehiculo obj = dao.buscarPorId(1);
        assertNotNull(obj, "Debe existir el vehículo con ID 1");
        assertEquals(1, obj.getIdVehiculo(), "El ID debe coincidir");
    }

    @Test
    public void testModificar() {
        IVehiculoDao dao = VehiculoFactory.crearVehiculoDao();
        Vehiculo obj = dao.buscarPorId(1);
        assertNotNull(obj);
        boolean resultado = dao.modificar(obj);
        assertTrue(resultado, "La modificación debe retornar true");
    }

    @Test
    public void testEliminarInexistente() {
        IVehiculoDao dao = VehiculoFactory.crearVehiculoDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}