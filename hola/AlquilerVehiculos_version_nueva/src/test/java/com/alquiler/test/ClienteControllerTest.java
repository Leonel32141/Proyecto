package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IClienteDao;
import factory.ClienteFactory;
import modelo.Cliente;
import java.util.List;

public class ClienteControllerTest {

    @Test
    public void testCrearDao() {
        IClienteDao dao = ClienteFactory.crearClienteDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IClienteDao dao = ClienteFactory.crearClienteDao();
        List<Cliente> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}