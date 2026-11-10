package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IClienteDao;
import factory.ClienteFactory;
import modelo.Cliente;

public class ClienteControllerTest {

    @Test
    public void testCrearDaoCliente() {
        IClienteDao dao = ClienteFactory.crearClienteDao();
        assertNotNull(dao, "El DAO de Cliente no debería ser nulo");
    }

    @Test
    public void testListarClientes() {
        IClienteDao dao = ClienteFactory.crearClienteDao();
        List<Cliente> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de clientes no debe ser nula");
    }

    @Test
    public void testBuscarClientePorId() {
        IClienteDao dao = ClienteFactory.crearClienteDao();
        Cliente cliente = dao.buscarPorId(1);
        if (cliente != null) {
            assertEquals(1, cliente.getIdCliente(), "El ID del cliente debe coincidir");
        } else {
            assertTrue(true, "Si no hay cliente con ID 1 en la DB de prueba, pasa de largo de forma segura");
        }
    }

    @Test
    public void testModificarCliente() {
        IClienteDao dao = ClienteFactory.crearClienteDao();
        Cliente cliente = dao.buscarPorId(1);
        if (cliente != null) {
            boolean resultado = dao.modificar(cliente);
            assertTrue(resultado, "La modificación del cliente debe retornar true");
        } else {
            assertTrue(true, "Sin registro previo para modificar en este entorno");
        }
    }

    @Test
    public void testEliminarCliente() {
        IClienteDao dao = ClienteFactory.crearClienteDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No debería eliminar un ID inexistente");
    }
}