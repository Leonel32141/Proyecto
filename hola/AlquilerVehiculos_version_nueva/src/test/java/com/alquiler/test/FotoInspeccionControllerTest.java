package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IFotoInspeccionDao;
import factory.FotoInspeccionFactory;
import modelo.FotoInspeccion;
import java.util.List;

public class FotoInspeccionControllerTest {

    @Test
    public void testCrearDao() {
        IFotoInspeccionDao dao = FotoInspeccionFactory.crearFotoInspeccionDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IFotoInspeccionDao dao = FotoInspeccionFactory.crearFotoInspeccionDao();
        List<FotoInspeccion> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}