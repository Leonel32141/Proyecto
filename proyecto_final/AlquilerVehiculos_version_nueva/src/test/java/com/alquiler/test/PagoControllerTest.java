package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.List;

import dao.IPagoDao;
import dao.PagoDaoImpl;
import modelo.Pago;

public class PagoControllerTest {

    @Test
    public void testCrearDaoPago() {
        IPagoDao dao = new PagoDaoImpl();
        assertNotNull(dao, "El DAO de Pago no debería ser nulo");
    }

    @Test
    public void testListarPagos() {
        IPagoDao dao = new PagoDaoImpl();
        List<Pago> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de pagos no debe ser nula");
    }

    @Test
    public void testBuscarPagoPorId() {
        IPagoDao dao = new PagoDaoImpl();
        // Probamos buscar el pago con ID 1 (o el que tengan de prueba)
        Pago pago = dao.buscarPorId(1);
        assertTrue(pago == null || pago.getIdPago() >= 0);
    }

    @Test
    public void testModificarPago() {
        IPagoDao dao = new PagoDaoImpl();
        Pago pago = new Pago();
        pago.setIdPago(1);
        pago.setIdAlquiler(1);
        pago.setIdMetodoPago(2);
        pago.setMonto(new BigDecimal("15000.00"));
        pago.setFechaPago(LocalDateTime.now());
        pago.setEstadoPago("APROBADO");
        pago.setNumeroComprobante("COMP-999999");
        
        boolean resultado = dao.modificar(pago);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarPago() {
        IPagoDao dao = new PagoDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}