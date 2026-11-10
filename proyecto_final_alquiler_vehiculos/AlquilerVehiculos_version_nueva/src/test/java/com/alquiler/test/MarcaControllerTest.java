package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IMarcaDao;
import factory.MarcaFactory; // Usamos el Factory que unificamos antes
import modelo.Marca;

public class MarcaControllerTest {

    @Test
    public void testCrearDaoMarca() {
        IMarcaDao dao = MarcaFactory.crearMarcaDao();
        assertNotNull(dao, "El DAO de Marca creado por la fábrica no debería ser nulo");
    }

    @Test
    public void testListarMarcas() {
        IMarcaDao dao = MarcaFactory.crearMarcaDao();
        List<Marca> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de marcas no debe ser nula");
        // Aseguramos que la base de datos de prueba tenga al menos un registro cargado
        assertFalse(lista.isEmpty(), "La lista de marcas debería contener al menos un elemento");
    }

    @Test
    public void testBuscarMarcaPorId() {
        IMarcaDao dao = MarcaFactory.crearMarcaDao();
        int idPrueba = 1; // ID que sabemos que existe en la DB
        Marca marca = dao.buscarPorId(idPrueba);
        
        assertNotNull(marca, "La marca con ID " + idPrueba + " debería existir en la base de datos");
        assertEquals(idPrueba, marca.getIdMarca(), "El ID de la marca encontrada debe coincidir con el solicitado");
        assertNotNull(marca.getNombreMarca(), "El nombre de la marca no debería ser nulo");
    }

    @Test
    public void testModificarMarca() {
        IMarcaDao dao = MarcaFactory.crearMarcaDao();
        
        // Primero buscamos una real para modificar o aseguramos una estructura válida
        Marca marca = dao.buscarPorId(1);
        if (marca != null) {
            String nombreOriginal = marca.getNombreMarca();
            marca.setNombreMarca("Toyota Test Update");
            
            boolean resultado = dao.modificar(marca);
            assertTrue(resultado, "La modificación de la marca debería retornar true si se ejecutó correctamente");
            
            // Revertimos el cambio para no alterar los datos de prueba
            marca.setNombreMarca(nombreOriginal);
            dao.modificar(marca);
        } else {
            fail("No se encontró la marca con ID 1 para realizar el test de modificación");
        }
    }

    @Test
    public void testEliminarMarca() {
        IMarcaDao dao = MarcaFactory.crearMarcaDao();
        // Intentamos eliminar un ID ficticio y validamos de forma estricta que devuelva false
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "El sistema no debería permitir eliminar un registro con ID inexistente");
    }
}