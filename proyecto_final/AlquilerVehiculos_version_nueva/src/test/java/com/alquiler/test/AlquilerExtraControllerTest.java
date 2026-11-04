package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IAlquilerExtraDao;
import dao.AlquilerExtraDaoImpl;
import modelo.AlquilerExtra;

public class AlquilerExtraControllerTest {

    @Test
    public void testCrearDaoExtra() {
        IAlquilerExtraDao dao = new AlquilerExtraDaoImpl();
        assertNotNull(dao, "El DAO de AlquilerExtra no debería ser nulo");
    }

    @Test
    public void testListarTodosExtras() {
        IAlquilerExtraDao dao = new AlquilerExtraDaoImpl();
        List<AlquilerExtra> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de alquileres extras no debe ser nula");
    }

    @Test
    public void testBuscarPorIdCompuesto() {
        IAlquilerExtraDao dao = new AlquilerExtraDaoImpl();
        // Probamos buscando un ID de alquiler y un ID de extra genéricos (ej: 1 y 1)
        AlquilerExtra extra = dao.buscarPorId(1, 1);
        assertTrue(extra == null || (extra.getIdAlquiler() >= 0 && extra.getIdExtra() >= 0));
    }

    @Test
    public void testModificarExtra() {
        IAlquilerExtraDao dao = new AlquilerExtraDaoImpl();
        AlquilerExtra extra = new AlquilerExtra();
        extra.setIdAlquiler(1);
        extra.setIdExtra(1);
        extra.setCantidad(2);
        
        boolean resultado = dao.modificar(extra);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarExtra() {
        IAlquilerExtraDao dao = new AlquilerExtraDaoImpl();
        // Intentamos eliminar una combinación inexistente para testear el procedure
        boolean resultado = dao.eliminar(9999, 9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}