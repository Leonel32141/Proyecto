package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IVehiculoDao;
import factory.VehiculoFactory;
import modelo.Vehiculo;
import java.util.List;

public class VehiculoControllerTest {

    @Test
    public void testCrearDao() {
        IVehiculoDao dao = VehiculoFactory.crearVehiculoDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IVehiculoDao dao = VehiculoFactory.crearVehiculoDao();
        List<Vehiculo> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}