package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IOpcionalExtraDao;
import factory.OpcionalExtraFactory;
import modelo.OpcionalExtra;
import java.util.List;

public class OpcionalExtraControllerTest {

    @Test
    public void testCrearDao() {
        IOpcionalExtraDao dao = OpcionalExtraFactory.crearOpcionalExtraDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IOpcionalExtraDao dao = OpcionalExtraFactory.crearOpcionalExtraDao();
        List<OpcionalExtra> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}