package dao;

import modelo.OpcionalExtra;
import java.util.List;

public interface IOpcionalExtraDao {
    boolean insertar(OpcionalExtra opcionalExtra);
    boolean modificar(OpcionalExtra opcionalExtra);
    boolean eliminar(int idExtra);
    OpcionalExtra buscarPorId(int idExtra);
    List<OpcionalExtra> listarTodos();
}