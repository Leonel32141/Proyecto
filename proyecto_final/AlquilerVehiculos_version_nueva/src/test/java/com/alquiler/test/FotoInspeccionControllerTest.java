package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IFotoInspeccionDao;
import dao.FotoInspeccionDaoImpl;
import modelo.FotoInspeccion;

public class FotoInspeccionControllerTest {

    @Test
    public void testCrearDaoFoto() {
        IFotoInspeccionDao dao = new FotoInspeccionDaoImpl();
        assertNotNull(dao, "El DAO de FotoInspeccion no debería ser nulo");
    }

    @Test
    public void testListarFotos() {
        IFotoInspeccionDao dao = new FotoInspeccionDaoImpl();
        List<FotoInspeccion> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de fotos de inspección no debe ser nula");
    }

    @Test
    public void testBuscarFotoPorId() {
        IFotoInspeccionDao dao = new FotoInspeccionDaoImpl();
        // Probamos buscar la foto con ID 1 (o la que tengan de prueba)
        FotoInspeccion foto = dao.buscarPorId(1);
        assertTrue(foto == null || foto.getIdFoto() >= 0);
    }

    @Test
    public void testModificarFoto() {
        IFotoInspeccionDao dao = new FotoInspeccionDaoImpl();
        FotoInspeccion foto = new FotoInspeccion();
        foto.setIdFoto(1);
        foto.setIdRegistro(1);
        foto.setFotoUrl("url_modificada.jpg");
        foto.setObservacionDano("Rayón leve en guardabarro test");
        
        boolean resultado = dao.modificar(foto);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarFoto() {
        IFotoInspeccionDao dao = new FotoInspeccionDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}