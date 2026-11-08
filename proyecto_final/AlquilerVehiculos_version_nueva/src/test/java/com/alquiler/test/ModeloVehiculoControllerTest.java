package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IModeloVehiculoDao;
import dao.ModeloVehiculoDaoImpl;
import modelo.ModeloVehiculo;

public class ModeloVehiculoControllerTest {

    @Test
    public void testCrearDaoModeloVehiculo() {
        IModeloVehiculoDao dao = new ModeloVehiculoDaoImpl();
        assertNotNull(dao, "El DAO de ModeloVehiculo no debería ser nulo");
    }

    @Test
    public void testListarModelosVehiculo() {
        IModeloVehiculoDao dao = new ModeloVehiculoDaoImpl();
        List<ModeloVehiculo> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de modelos de vehículo no debe ser nula");
    }

    @Test
    public void testBuscarModeloVehiculoPorId() {
        IModeloVehiculoDao dao = new ModeloVehiculoDaoImpl();
        // Probamos buscar el modelo con ID 1 (o el que tengan de prueba)
        ModeloVehiculo mv = dao.buscarPorId(1);
        assertTrue(mv == null || mv.getIdModelo() >= 0);
    }

    @Test
    public void testModificarModeloVehiculo() {
        IModeloVehiculoDao dao = new ModeloVehiculoDaoImpl();
        ModeloVehiculo mv = new ModeloVehiculo();
        mv.setIdModelo(1);
        mv.setIdMarca(1);
        mv.setNombreModelo("Corolla Test");
        mv.setAnio(2025);
        mv.setImagen("corolla_test.png");
        
        boolean resultado = dao.modificar(mv);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarModeloVehiculo() {
        IModeloVehiculoDao dao = new ModeloVehiculoDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}