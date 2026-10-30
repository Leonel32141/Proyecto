package dao;

import modelo.Rol;
import java.util.List;

public interface IRolDao {
    boolean insertar(Rol rol);
    boolean modificar(Rol rol);
    boolean eliminar(int idRol);
    Rol buscarPorId(int idRol);
    List<Rol> listarTodos();
}