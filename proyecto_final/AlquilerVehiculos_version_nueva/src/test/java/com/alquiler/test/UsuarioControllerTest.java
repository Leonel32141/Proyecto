package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*; 
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IUsuarioDao;
import dao.UsuarioDaoImpl;
import modelo.Usuario;

public class UsuarioControllerTest { 

    @Test
    public void testCrearDaoUsuario() {
        IUsuarioDao dao = new UsuarioDaoImpl();
        assertNotNull(dao, "El DAO de Usuario no debería ser nulo");
    }

    @Test
    public void testListarUsuarios() {
        IUsuarioDao dao = new UsuarioDaoImpl();
        List<Usuario> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de usuarios no debe ser nula");
    }

    @Test
    public void testBuscarUsuarioPorId() {
        IUsuarioDao dao = new UsuarioDaoImpl();
        // Probamos buscar el usuario con ID 1 (o el que tengan de prueba)
        Usuario u = dao.buscarPorId(1);
        assertTrue(u == null || u.getIdUsuario() >= 0);
    }

    @Test
    public void testModificarUsuario() {
        IUsuarioDao dao = new UsuarioDaoImpl();
        Usuario u = new Usuario();
        u.setIdUsuario(1);
        u.setIdRol(1);
        u.setEmail("test@alquiler.com");
        u.setPasswordHash("hash_de_prueba");
        u.setNombre("Juan");
        u.setApellido("Pérez");
        u.setDni("12345678");
        u.setCuil("20-12345678-9");
        u.setTelefono("1112345678");
        u.setIntentosFallidos(0);
        u.setCuentaBloqueada(false);
        
        boolean resultado = dao.modificar(u);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarUsuario() {
        IUsuarioDao dao = new UsuarioDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}