package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.ISucursalDao;
import factory.SucursalFactory;
import modelo.Sucursal;
import java.util.List;

public class SucursalControllerTest {

    @Test
    public void testCrearDao() {
        ISucursalDao dao = SucursalFactory.crearSucursalDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        ISucursalDao dao = SucursalFactory.crearSucursalDao();
        List<Sucursal> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}