package dao;

import java.util.List;
import modelo.Rol;

public interface IRolDao {
    List<Rol> obtenerTodos();
    Rol obtenerPorId(int id);
    boolean registrar(Rol rol);
    boolean actualizar(Rol rol);
    boolean eliminar(int id);
}