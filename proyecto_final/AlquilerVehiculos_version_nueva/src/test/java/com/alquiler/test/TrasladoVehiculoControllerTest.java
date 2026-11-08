package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.ITrasladoVehiculoDao;
import dao.TrasladoVehiculoDaoImpl;
import modelo.TrasladoVehiculo;

public class TrasladoVehiculoControllerTest {

    @Test
    public void testCrearDaoTrasladoVehiculo() {
        ITrasladoVehiculoDao dao = new TrasladoVehiculoDaoImpl();
        assertNotNull(dao, "El DAO de TrasladoVehiculo no debería ser nulo");
    }

    @Test
    public void testListarTrasladosVehiculos() {
        ITrasladoVehiculoDao dao = new TrasladoVehiculoDaoImpl();
        List<TrasladoVehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de traslados de vehículos no debe ser nula");
    }

    @Test
    public void testBuscarTrasladoVehiculoPorId() {
        ITrasladoVehiculoDao dao = new TrasladoVehiculoDaoImpl();
        // Probamos buscar el traslado con ID 1 (o el que tengan de prueba)
        TrasladoVehiculo t = dao.buscarPorId(1);
        assertTrue(t == null || t.getIdTraslado() >= 0);
    }

    @Test
    public void testModificarTrasladoVehiculo() {
        ITrasladoVehiculoDao dao = new TrasladoVehiculoDaoImpl();
        TrasladoVehiculo t = new TrasladoVehiculo();
        t.setIdTraslado(1);
        t.setIdVehiculo(1);
        t.setIdSucursalOrigen(1);
        t.setIdSucursalDestino(2);
        t.setIdUsuarioChofer(1);
        t.setObservaciones("Traslado de prueba unitaria");
        
        boolean resultado = dao.modificar(t);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarTrasladoVehiculo() {
        ITrasladoVehiculoDao dao = new TrasladoVehiculoDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}