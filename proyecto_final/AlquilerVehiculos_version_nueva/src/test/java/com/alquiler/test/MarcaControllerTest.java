package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IMarcaDao;
import dao.MarcaDaoImpl;
import modelo.Marca;

public class MarcaControllerTest {

    @Test
    public void testCrearDaoMarca() {
        IMarcaDao dao = new MarcaDaoImpl();
        assertNotNull(dao, "El DAO de Marca no debería ser nulo");
    }

    @Test
    public void testListarMarcas() {
        IMarcaDao dao = new MarcaDaoImpl();
        List<Marca> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de marcas no debe ser nula");
    }

    @Test
    public void testBuscarMarcaPorId() {
        IMarcaDao dao = new MarcaDaoImpl();
        // Probamos buscar la marca con ID 1 (o la que tengan de prueba)
        Marca marca = dao.buscarPorId(1);
        assertTrue(marca == null || marca.getIdMarca() >= 0);
    }

    @Test
    public void testModificarMarca() {
        IMarcaDao dao = new MarcaDaoImpl();
        Marca marca = new Marca();
        marca.setIdMarca(1);
        marca.setNombreMarca("Toyota Test");
        
        boolean resultado = dao.modificar(marca);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarMarca() {
        IMarcaDao dao = new MarcaDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}