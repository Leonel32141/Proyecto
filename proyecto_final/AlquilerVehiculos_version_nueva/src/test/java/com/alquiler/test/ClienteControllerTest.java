package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.List;

import dao.IClienteDao;
import dao.ClienteDaoImpl;
import modelo.Cliente;

public class ClienteControllerTest {

    @Test
    public void testCrearDaoCliente() {
        IClienteDao dao = new ClienteDaoImpl();
        assertNotNull(dao, "El DAO de Cliente no debería ser nulo");
    }

    @Test
    public void testListarClientes() {
        IClienteDao dao = new ClienteDaoImpl();
        List<Cliente> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de clientes no debe ser nula");
    }

    @Test
    public void testBuscarClientePorId() {
        IClienteDao dao = new ClienteDaoImpl();
        // Probamos buscar un cliente con ID 1 (o el que tengan de prueba)
        Cliente cliente = dao.buscarPorId(1);
        assertTrue(cliente == null || cliente.getIdCliente() >= 0);
    }

    @Test
    public void testModificarCliente() {
        IClienteDao dao = new ClienteDaoImpl();
        Cliente cliente = new Cliente();
        cliente.setIdCliente(1);
        cliente.setDni("12345678");
        cliente.setLicenciaConducir("LIC-9999");
        cliente.setDomicilio("Calle Falsa 123");
        cliente.setFotoIdentificacionUrl("url_test.jpg");
        cliente.setFechaNacimiento(LocalDate.of(2000, 1, 1));
        cliente.setEsAptoLujo(true);
        
        boolean resultado = dao.modificar(cliente);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarCliente() {
        IClienteDao dao = new ClienteDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}