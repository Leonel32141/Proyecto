package dao;

import modelo.Cliente;
import java.util.List;

public interface IClienteDao {
    boolean insertar(Cliente cliente);
    boolean modificar(Cliente cliente);
    boolean eliminar(int idCliente);
    Cliente buscarPorId(int idCliente);
    List<Cliente> listarTodos();
}