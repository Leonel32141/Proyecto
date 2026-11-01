package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IAlquilerDao;
import factory.AlquilerFactory;
import modelo.Alquiler;
import java.util.List;

public class AlquilerControllerTest {

    @Test
    public void testCrearAlquilerDao() {
        IAlquilerDao dao = AlquilerFactory.crearAlquilerDao();
        assertNotNull(dao, "El DAO de alquiler no debería ser nulo");
    }

    @Test
    public void testListarAlquileres() {
        IAlquilerDao dao = AlquilerFactory.crearAlquilerDao();
        List<Alquiler> alquileres = dao.listarTodos();
        assertNotNull(alquileres, "La lista de alquileres no debe ser nula.");
    }
}