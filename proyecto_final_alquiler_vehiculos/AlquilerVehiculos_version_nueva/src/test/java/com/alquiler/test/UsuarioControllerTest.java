package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IUsuarioDao;
import factory.UsuarioFactory;
import modelo.Usuario;

public class UsuarioControllerTest {

    @Test
    public void testCrearDaoUsuario() {
        IUsuarioDao dao = UsuarioFactory.crearUsuarioDao();
        assertNotNull(dao, "El DAO de Usuario no debería ser nulo");
    }

    @Test
    public void testListarUsuarios() {
        IUsuarioDao dao = UsuarioFactory.crearUsuarioDao();
        List<Usuario> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de usuarios no debe ser nula");
        assertFalse(lista.isEmpty(), "Debe haber al menos un usuario registrado");
    }

    @Test
    public void testBuscarUsuarioPorId() {
        IUsuarioDao dao = UsuarioFactory.crearUsuarioDao();
        Usuario usuario = dao.buscarPorId(1);
        assertNotNull(usuario, "El usuario con ID 1 debe existir");
        assertEquals(1, usuario.getIdUsuario(), "El ID debe ser 1");
    }

    @Test
    public void testModificarUsuario() {
        IUsuarioDao dao = UsuarioFactory.crearUsuarioDao();
        Usuario usuario = dao.buscarPorId(1);
        assertNotNull(usuario, "Debe existir un usuario para modificar");
        
        boolean resultado = dao.modificar(usuario);
        assertTrue(resultado, "La modificación del usuario debe ser exitosa");
    }

    @Test
    public void testEliminarUsuario() {
        IUsuarioDao dao = UsuarioFactory.crearUsuarioDao();
        boolean resultado = dao.eliminar(99999);
        assertFalse(resultado, "No se puede eliminar un usuario con ID inexistente");
    }
}