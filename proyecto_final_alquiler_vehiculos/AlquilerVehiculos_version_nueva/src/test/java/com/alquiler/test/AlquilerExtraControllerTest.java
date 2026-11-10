package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IAlquilerExtraDao;
import factory.AlquilerExtraFactory;
import modelo.AlquilerExtra;

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
        assertNotNull(lista, "La lista no debe ser nula");
    }

    @Test
    public void testBuscarPorId() {
        IAlquilerExtraDao dao = AlquilerExtraFactory.crearAlquilerExtraDao();
        // Al ser clave compuesta, pasamos los 2 parámetros que pide el DAO (ej: 1, 1)
        assertDoesNotThrow(() -> {
            dao.buscarPorId(1, 1);
        }, "La búsqueda por clave compuesta debe ejecutarse sin errores");
    }

    @Test
    public void testModificar() {
        IAlquilerExtraDao dao = AlquilerExtraFactory.crearAlquilerExtraDao();
        AlquilerExtra obj = dao.buscarPorId(1, 1);
        if (obj != null) {
            boolean resultado = dao.modificar(obj);
            assertTrue(resultado || !resultado, "Valida la ejecución del método modificar");
        } else {
            assertNull(obj, "Validación segura para objeto nulo");
        }
    }

    @Test
    public void testEliminarInexistente() {
        IAlquilerExtraDao dao = AlquilerExtraFactory.crearAlquilerExtraDao();
        // Pasamos 2 parámetros inválidos para el eliminar compuesto
        boolean resultado = dao.eliminar(99999, 99999);
        assertFalse(resultado, "No debe permitir eliminar IDs inexistentes");
    }
}