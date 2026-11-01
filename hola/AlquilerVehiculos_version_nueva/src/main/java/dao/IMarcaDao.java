package dao;

import modelo.Marca;
import java.util.List;

public interface IMarcaDao {
    boolean insertar(Marca marca);
    boolean modificar(Marca marca);
    boolean eliminar(int idMarca);
    Marca buscarPorId(int idMarca);
    List<Marca> listarTodos();
}