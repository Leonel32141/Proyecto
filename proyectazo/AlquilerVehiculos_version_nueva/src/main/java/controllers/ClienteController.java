package controllers;

import modelo.Cliente;
import java.util.ArrayList;
import java.util.List;

public class ClienteController {

    // Método para obtener la lista de clientes
    public List<Cliente> obtenerClientes() {
        List<Cliente> lista = new ArrayList<>();
        // Acá después conectamos con el DAO para traerlos de la base de datos
        return lista;
    }

    public void registrarCliente(Cliente cliente) {
        // Lógica para registrar un nuevo cliente
        System.out.println("Cliente registrado correctamente.");
    }
}