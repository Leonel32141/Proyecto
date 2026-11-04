package dao;

import modelo.Usuario;
import java.util.List;

public interface IUsuarioDao {
    boolean insertar(Usuario usuario);
    boolean modificar(Usuario usuario);
    boolean eliminar(int idUsuario);
    Usuario buscarPorId(int idUsuario);
    List<Usuario> listarTodos();
}