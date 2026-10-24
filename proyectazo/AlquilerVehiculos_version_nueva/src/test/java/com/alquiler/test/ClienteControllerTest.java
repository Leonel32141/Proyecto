package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import controllers.ClienteController;
import factory.ClienteFactory;
import modelo.Cliente;
import java.util.List;

public class ClienteControllerTest {

    @Test
    public void testCrearClienteControllerConFactory() {
        ClienteController controller = ClienteFactory.crearClienteController();
        assertNotNull(controller, "El controlador de cliente creado por la factory no debería ser nulo.");
    }

    @Test
    public void testObtenerClientes() {
        ClienteController controller = new ClienteController();
        List<Cliente> clientes = controller.obtenerClientes();
        
        assertNotNull(clientes, "La lista de clientes no debe ser nula.");
        assertTrue(clientes.isEmpty(), "La lista inicial de clientes debería estar vacía.");
    }
}