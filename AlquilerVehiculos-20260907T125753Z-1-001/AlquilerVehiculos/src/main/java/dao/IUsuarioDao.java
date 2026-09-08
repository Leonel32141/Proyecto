package dao;

import modelo.Usuario;
import java.util.List;

public interface IUsuarioDao {
    boolean insertar(Usuario usuario);
    boolean actualizar(Usuario usuario);
    boolean eliminar(int idUsuario);
    Usuario obtenerPorId(int idUsuario);
    List<Usuario> obtenerTodos();
}