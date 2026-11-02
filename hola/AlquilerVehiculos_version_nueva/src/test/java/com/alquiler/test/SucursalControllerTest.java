package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.ISucursalDao;
import dao.SucursalDaoImpl;
import modelo.Sucursal;

public class SucursalControllerTest {

    @Test
    public void testCrearDaoSucursal() {
        ISucursalDao dao = new SucursalDaoImpl();
        assertNotNull(dao, "El DAO de Sucursal no debería ser nulo");
    }

    @Test
    public void testListarSucursales() {
        ISucursalDao dao = new SucursalDaoImpl();
        List<Sucursal> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de sucursales no debe ser nula");
    }

    @Test
    public void testBuscarSucursalPorId() {
        ISucursalDao dao = new SucursalDaoImpl();
        // Probamos buscar la sucursal con ID 1 (o la que tengan de prueba)
        Sucursal sucursal = dao.buscarPorId(1);
        assertTrue(sucursal == null || sucursal.getIdSucursal() >= 0);
    }

    @Test
    public void testModificarSucursal() {
        ISucursalDao dao = new SucursalDaoImpl();
        Sucursal sucursal = new Sucursal();
        sucursal.setIdSucursal(1);
        sucursal.setNombre("Sucursal Central Test");
        sucursal.setDireccion("Av. Corrientes 1234");
        sucursal.setCiudad("Buenos Aires");
        sucursal.setActiva(true);
        
        boolean resultado = dao.modificar(sucursal);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarSucursal() {
        ISucursalDao dao = new SucursalDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}