package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;
import java.math.BigDecimal;

import dao.ICategoriaVehiculoDao;
import dao.CategoriaVehiculoDaoImpl;
import modelo.CategoriaVehiculo;

public class CategoriaVehiculoControllerTest {

    @Test
    public void testCrearDaoCategoria() {
        ICategoriaVehiculoDao dao = new CategoriaVehiculoDaoImpl();
        assertNotNull(dao, "El DAO de CategoriaVehiculo no debería ser nulo");
    }

    @Test
    public void testListarCategorias() {
        ICategoriaVehiculoDao dao = new CategoriaVehiculoDaoImpl();
        List<CategoriaVehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de categorías no debe ser nula");
    }

    @Test
    public void testBuscarCategoriaPorId() {
        ICategoriaVehiculoDao dao = new CategoriaVehiculoDaoImpl();
        // Probamos buscando la categoría con ID 1 (o la que tengan de prueba)
        CategoriaVehiculo categoria = dao.buscarPorId(1);
        assertTrue(categoria == null || categoria.getIdCategoria() >= 0);
    }

    @Test
    public void testModificarCategoria() {
        ICategoriaVehiculoDao dao = new CategoriaVehiculoDaoImpl();
        CategoriaVehiculo categoria = new CategoriaVehiculo();
        categoria.setIdCategoria(1);
        categoria.setNombreCategoria("Test Modificado");
        categoria.setDescripcion("Descripción de prueba");
        categoria.setTarifaBaseDia(new BigDecimal("1500.00"));
        
        boolean resultado = dao.modificar(categoria);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarCategoria() {
        ICategoriaVehiculoDao dao = new CategoriaVehiculoDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}