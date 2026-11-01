package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IAlquilerExtraDao;
import factory.AlquilerExtraFactory;
import modelo.AlquilerExtra;
import java.util.List;

public class AlquilerExtraControllerTest {

    @Test
    public void testCrearDao() {
        IAlquilerExtraDao dao = AlquilerExtraFactory.crearAlquilerExtraDao();
        assertNotNull(dao, "El DAO no debe ser nulo");
    }

    @Test
    public void testListar() {
        IAlquilerExtraDao dao = AlquilerExtraFactory.crearAlquilerExtraDao();
        List<AlquilerExtra> lista = dao.listarTodos();
        assertNotNull(lista, "La lista no debe ser nula.");
    }
}