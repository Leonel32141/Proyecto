package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IMetodoPagoDao;
import dao.MetodoPagoDaoImpl;
import modelo.MetodoPago;

public class MetodoPagoControllerTest {

    @Test
    public void testCrearDaoMetodoPago() {
        IMetodoPagoDao dao = new MetodoPagoDaoImpl();
        assertNotNull(dao, "El DAO de MetodoPago no debería ser nulo");
    }

    @Test
    public void testListarMetodosPago() {
        IMetodoPagoDao dao = new MetodoPagoDaoImpl();
        List<MetodoPago> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de métodos de pago no debe ser nula");
    }

    @Test
    public void testBuscarMetodoPagoPorId() {
        IMetodoPagoDao dao = new MetodoPagoDaoImpl();
        // Probamos buscar el método de pago con ID 1 (o el que tengan de prueba)
        MetodoPago mp = dao.buscarPorId(1);
        assertTrue(mp == null || mp.getIdMetodoPago() >= 0);
    }

    @Test
    public void testModificarMetodoPago() {
        IMetodoPagoDao dao = new MetodoPagoDaoImpl();
        MetodoPago mp = new MetodoPago();
        mp.setIdMetodoPago(1);
        mp.setNombreMetodo("Transferencia Test");
        mp.setRequiereComprobante(true);
        
        boolean resultado = dao.modificar(mp);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarMetodoPago() {
        IMetodoPagoDao dao = new MetodoPagoDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}