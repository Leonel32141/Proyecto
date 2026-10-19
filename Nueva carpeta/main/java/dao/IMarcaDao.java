package dao;

import java.util.List;
import modelo.Marca;

public interface IMarcaDao {
    List<Marca> obtenerTodos();
    Marca obtenerPorId(int id);
    boolean registrar(Marca marca);
    boolean actualizar(Marca marca);
    boolean eliminar(int id);
}