package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.ICheckinCheckoutDao;
import factory.CheckinCheckoutFactory;
import modelo.CheckinCheckout;
import java.util.List;

public class CheckinCheckoutControllerTest {

    @Test
    public void testCrearDao() {
        ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
        List<CheckinCheckout> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}