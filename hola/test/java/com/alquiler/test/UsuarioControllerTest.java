package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import dao.IUsuarioDao;
import factory.UsuarioFactory;
import modelo.Usuario;
import java.util.List;

public class UsuarioControllerTest {

    @Test
    public void testCrearDao() {
        IUsuarioDao dao = UsuarioFactory.crearUsuarioDao();
        assertNotNull(dao);
    }

    @Test
    public void testListar() {
        IUsuarioDao dao = UsuarioFactory.crearUsuarioDao();
        List<Usuario> lista = dao.listarTodos();
        assertNotNull(lista);
    }
}