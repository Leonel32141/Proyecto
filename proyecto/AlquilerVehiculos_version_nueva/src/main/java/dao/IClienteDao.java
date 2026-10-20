package dao;

import java.util.List;
import modelo.Cliente;

public interface IClienteDao {
    List<Cliente> obtenerTodos();
    Cliente obtenerPorId(int id);
    boolean registrar(Cliente cliente);
    boolean actualizar(Cliente cliente);
    boolean eliminar(int id);
}