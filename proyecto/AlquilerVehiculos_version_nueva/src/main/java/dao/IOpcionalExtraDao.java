package dao;

import java.util.List;
import modelo.OpcionalExtra;

public interface IOpcionalExtraDao {
    List<OpcionalExtra> obtenerTodos();
    OpcionalExtra obtenerPorId(int id);
    boolean registrar(OpcionalExtra opcional);
    boolean actualizar(OpcionalExtra opcional);
    boolean eliminar(int id);
}