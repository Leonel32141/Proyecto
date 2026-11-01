package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.ICategoriaVehiculoDao;
import factory.CategoriaVehiculoFactory;
import modelo.CategoriaVehiculo;
import java.util.List;

public class CategoriaVehiculoControllerTest {

    @Test
    public void testCrearDao() {
        ICategoriaVehiculoDao dao = CategoriaVehiculoFactory.crearCategoriaVehiculoDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        ICategoriaVehiculoDao dao = CategoriaVehiculoFactory.crearCategoriaVehiculoDao();
        List<CategoriaVehiculo> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}