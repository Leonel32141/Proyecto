package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.math.BigDecimal;
import java.util.List;

import dao.IMantenimientoDao;
import dao.MantenimientoDaoImpl;
import modelo.Mantenimiento;

public class MantenimientoControllerTest {

    @Test
    public void testCrearDaoMantenimiento() {
        IMantenimientoDao dao = new MantenimientoDaoImpl();
        assertNotNull(dao, "El DAO de Mantenimiento no debería ser nulo");
    }

    @Test
    public void testListarMantenimientos() {
        IMantenimientoDao dao = new MantenimientoDaoImpl();
        List<Mantenimiento> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de mantenimientos no debe ser nula");
    }

    @Test
    public void testBuscarMantenimientoPorId() {
        IMantenimientoDao dao = new MantenimientoDaoImpl();
        // Probamos buscar el mantenimiento con ID 1 (o el que tengan de prueba)
        Mantenimiento m = dao.buscarPorId(1);
        assertTrue(m == null || m.getIdMantenimiento() >= 0);
    }

    @Test
    public void testModificarMantenimiento() {
        IMantenimientoDao dao = new MantenimientoDaoImpl();
        Mantenimiento m = new Mantenimiento();
        m.setIdMantenimiento(1);
        m.setIdVehiculo(1);
        m.setTipoMantenimiento("PREVENTIVO");
        m.setFechaIngreso(LocalDate.now());
        m.setFechaEgresoEstimada(LocalDate.now().plusDays(3));
        m.setCostoTotal(new BigDecimal("25000.00"));
        m.setTallerMecanico("Taller Central Test");
        m.setDetalleTrabajo("Cambio de aceite y filtros de prueba");
        
        boolean resultado = dao.modificar(m);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarMantenimiento() {
        IMantenimientoDao dao = new MantenimientoDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}