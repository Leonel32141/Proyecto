package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IMantenimientoDao;
import factory.MantenimientoFactory;
import modelo.Mantenimiento;
import java.util.List;

public class MantenimientoControllerTest {

    @Test
    public void testCrearDao() {
        IMantenimientoDao dao = MantenimientoFactory.crearMantenimientoDao();
        assertNotNull(dao, "El DAO de mantenimiento no debe ser nulo");
    }

    @Test
    public void testListar() {
        IMantenimientoDao dao = MantenimientoFactory.crearMantenimientoDao();
        List<Mantenimiento> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de mantenimientos no debe ser nula.");
    }
}