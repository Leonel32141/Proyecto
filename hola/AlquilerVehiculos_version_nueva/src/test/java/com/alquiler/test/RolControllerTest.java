package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IRolDao;
import factory.RolFactory;
import modelo.Rol;
import java.util.List;

public class RolControllerTest {

    @Test
    public void testCrearDao() {
        IRolDao dao = RolFactory.crearRolDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IRolDao dao = RolFactory.crearRolDao();
        List<Rol> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}