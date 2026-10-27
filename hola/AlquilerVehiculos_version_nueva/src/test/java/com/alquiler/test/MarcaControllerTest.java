package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IMarcaDao;
import factory.MarcaFactory;
import modelo.Marca;
import java.util.List;

public class MarcaControllerTest {

    @Test
    public void testCrearDao() {
        IMarcaDao dao = MarcaFactory.crearMarcaDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IMarcaDao dao = MarcaFactory.crearMarcaDao();
        List<Marca> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}