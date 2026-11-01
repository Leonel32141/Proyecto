package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IPagoDao;
import factory.PagoFactory;
import modelo.Pago;
import java.util.List;

public class PagoControllerTest {

    @Test
    public void testCrearDao() {
        IPagoDao dao = PagoFactory.crearPagoDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IPagoDao dao = PagoFactory.crearPagoDao();
        List<Pago> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}