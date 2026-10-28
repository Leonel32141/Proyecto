package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.ITrasladoVehiculoDao;
import factory.TrasladoVehiculoFactory;
import modelo.TrasladoVehiculo;
import java.util.List;

public class TrasladoVehiculoControllerTest {

    @Test
    public void testCrearDao() {
        ITrasladoVehiculoDao dao = TrasladoVehiculoFactory.crearTrasladoVehiculoDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        ITrasladoVehiculoDao dao = TrasladoVehiculoFactory.crearTrasladoVehiculoDao();
        List<TrasladoVehiculo> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}