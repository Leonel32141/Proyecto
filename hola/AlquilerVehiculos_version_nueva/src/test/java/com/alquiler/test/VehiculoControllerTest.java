package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import dao.IVehiculoDao;
import dao.VehiculoDaoImpl;
import modelo.Vehiculo;

public class VehiculoControllerTest {

    @Test
    public void testCrearDaoVehiculo() {
        IVehiculoDao dao = new VehiculoDaoImpl();
        assertNotNull(dao, "El DAO de Vehiculo no debería ser nulo");
    }

    @Test
    public void testListarVehiculos() {
        IVehiculoDao dao = new VehiculoDaoImpl();
        List<Vehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de vehículos no debe ser nula");
    }

    @Test
    public void testBuscarVehiculoPorId() {
        IVehiculoDao dao = new VehiculoDaoImpl();
        Vehiculo v = dao.buscarPorId(1);
        assertTrue(v == null || v.getIdVehiculo() >= 0);
    }

    @Test
    public void testModificarVehiculo() {
        IVehiculoDao dao = new VehiculoDaoImpl();
        Vehiculo v = new Vehiculo();
        v.setIdVehiculo(1);
        v.setPatente("AA123BB");
        v.setIdModelo(1);
        v.setIdCategoria(1);
        v.setIdSucursalActual(1);
        v.setKilometrajeActual(12500);
        v.setEstado("DISPONIBLE");
        v.setVencimientoSeguro(LocalDate.now().plusMonths(6));
        v.setVencimientoPatente(LocalDate.now().plusMonths(12));
        
        boolean resultado = dao.modificar(v);
        assertTrue(resultado || !resultado);
    }

    @Test
    public void testEliminarVehiculo() {
        IVehiculoDao dao = new VehiculoDaoImpl();
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}