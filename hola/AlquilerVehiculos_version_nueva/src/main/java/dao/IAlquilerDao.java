package dao;

import modelo.Alquiler;
import java.util.List;

public interface IAlquilerDao {
    boolean insertar(Alquiler alquiler);
    boolean modificar(Alquiler alquiler);
    boolean eliminar(int idAlquiler);
    Alquiler buscarPorId(int idAlquiler);
    List<Alquiler> listarTodos();
}