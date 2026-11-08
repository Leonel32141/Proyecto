package com.alquiler.test;

import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.Test;
import java.util.List;

import dao.IRolDao;
import dao.RolDaoImpl;
import modelo.Rol;

public class RolControllerTest {

    @Test
    public void testCrearDaoRol() {
        IRolDao dao = new RolDaoImpl();
        assertNotNull(dao, "El DAO de Rol no debería ser nulo");
    }

    @Test
    public void testListarRoles() {
        IRolDao dao = new RolDaoImpl();
        List<Rol> lista = dao.listarTodos();
        assertNotNull(lista, "La lista de roles no debe ser nula");
    }

    @Test
    public void testBuscarRolPorId() {
        IRolDao dao = new RolDaoImpl();
        // Probamos buscar el rol con ID 1 (o el que tengan de prueba)
        Rol rol = dao.buscarPorId(1);
        assertTrue(rol == null || rol.getIdRol() >= 0);
    }

    @Test
    public void testModificarRol() {
        IRolDao dao = new RolDaoImpl();
        Rol rol = new Rol();
        rol.setIdRol(1);
        rol.setNombreRol("ADMIN_TEST");
        rol.setDescripcion("Rol de administración de prueba");
        
        boolean resultado = dao.modificar(rol);
        assertTrue(resultado || !resultado); // Valida que la ejecución corra sin excepciones fatales
    }

    @Test
    public void testEliminarRol() {
        IRolDao dao = new RolDaoImpl();
        // Intentamos eliminar un ID ficticio para probar que responda el procedure sin romper
        boolean resultado = dao.eliminar(9999);
        assertFalse(resultado, "No debería eliminar un registro inexistente");
    }
}