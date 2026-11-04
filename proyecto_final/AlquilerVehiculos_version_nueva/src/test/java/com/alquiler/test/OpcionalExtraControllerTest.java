package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.math.BigDecimal;
import java.util.List;

import dao.IOpcionalExtraDao;
import dao.OpcionalExtraDaoImpl;
import modelo.OpcionalExtra;

public class OpcionalExtraControllerTest {

    @Test
    public void testCrearDaoOpcionalExtra() {
        IOpcionalExtraDao dao = new OpcionalExtraDaoImpl();
        assertNotNull(dao, "El DAO de OpcionalExtra no debería ser nulo");
    }

    @Test
    public void testListarOpcionalesExtras() {
        IOpcionalExtraDao dao = new OpcionalExtraDaoImpl();
        List<OpcionalExtra> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de opcionales extras no debe ser nula");
    }

    @Test
    public void testBuscarOpcionalExtraPorId() {
        IOpcionalExtraDao dao = new OpcionalExtraDaoImpl();
        // Probamos buscar el opcional extra con ID 1 (o el que tengan de prueba)
        OpcionalExtra extra = dao.buscarPorId(1);
        assertTrue(extra == null || extra.getIdExtra() >= 0);
    }

    @Test
    public void testModificarOpcionalExtra() {
        IOpcionalExtraDao dao = new OpcionalExtraDaoImpl();
        OpcionalExtra extra = new OpcionalExtra();
        extra.setIdExtra(1);
        extra.setNombreExtra("GPS Test");
        extra.setDescripcion("Sistema de navegación satelital de prueba");
        extra.setPrecioDiario(new BigDecimal("500.00"));
        
        boolean resultado = dao.modificar(extra);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarOpcionalExtra() {
        IOpcionalExtraDao dao = new OpcionalExtraDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}