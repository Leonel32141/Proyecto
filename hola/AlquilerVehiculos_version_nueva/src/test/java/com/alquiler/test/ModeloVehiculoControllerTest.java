package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IModeloVehiculoDao;
import factory.ModeloVehiculoFactory;
import modelo.ModeloVehiculo;
import java.util.List;

public class ModeloVehiculoControllerTest {

    @Test
    public void testCrearDao() {
        IModeloVehiculoDao dao = ModeloVehiculoFactory.crearModeloVehiculoDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IModeloVehiculoDao dao = ModeloVehiculoFactory.crearModeloVehiculoDao();
        List<ModeloVehiculo> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}