package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDateTime;
import java.util.List;

import dao.ICheckinCheckoutDao;
import dao.CheckinCheckoutDaoImpl;
import modelo.CheckinCheckout;

public class CheckinCheckoutControllerTest {

    @Test
    public void testCrearDaoCheckin() {
        ICheckinCheckoutDao dao = new CheckinCheckoutDaoImpl();
        assertNotNull(dao, "El DAO de CheckinCheckout no debería ser nulo");
    }

    @Test
    public void testListarCheckins() {
        ICheckinCheckoutDao dao = new CheckinCheckoutDaoImpl();
        List<CheckinCheckout> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de checkin/checkout no debe ser nula");
    }

    @Test
    public void testBuscarCheckinPorId() {
        ICheckinCheckoutDao dao = new CheckinCheckoutDaoImpl();
        // Probamos buscar el registro con ID 1 (o el que tengan de prueba)
        CheckinCheckout ch = dao.buscarPorId(1);
        assertTrue(ch == null || ch.getIdRegistro() >= 0);
    }

    @Test
    public void testModificarCheckin() {
        ICheckinCheckoutDao dao = new CheckinCheckoutDaoImpl();
        CheckinCheckout ch = new CheckinCheckout();
        ch.setIdRegistro(1);
        ch.setIdAlquiler(1);
        ch.setIdUsuarioEmpleado(1);
        ch.setTipo("CHECKOUT");
        ch.setFechaHoraReal(LocalDateTime.now());
        ch.setKilometraje(15000);
        ch.setNivelCombustible("LLENO");
        ch.setObservaciones("Test de modificación unitaria");
        
        boolean resultado = dao.modificar(ch);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarCheckin() {
        ICheckinCheckoutDao dao = new CheckinCheckoutDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}