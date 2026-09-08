package dao;

import modelo.Alquiler;
import java.util.List;

public interface IAlquilerDao {
    boolean insertar(Alquiler alquiler);
    boolean actualizar(Alquiler alquiler);
    boolean eliminar(int idAlquiler);
    Alquiler obtenerPorId(int idAlquiler);
    List<Alquiler> obtenerTodos();
}