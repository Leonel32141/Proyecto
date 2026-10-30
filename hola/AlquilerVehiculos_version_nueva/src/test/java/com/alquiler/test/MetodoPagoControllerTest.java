package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IMetodoPagoDao;
import factory.MetodoPagoFactory;
import modelo.MetodoPago;
import java.util.List;

public class MetodoPagoControllerTest {

    @Test
    public void testCrearDao() {
        IMetodoPagoDao dao = MetodoPagoFactory.crearMetodoPagoDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IMetodoPagoDao dao = MetodoPagoFactory.crearMetodoPagoDao();
        List<MetodoPago> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}