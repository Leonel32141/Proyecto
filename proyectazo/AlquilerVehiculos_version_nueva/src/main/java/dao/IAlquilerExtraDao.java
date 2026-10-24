package dao;

import java.util.List;
import modelo.AlquilerExtra;

public interface IAlquilerExtraDao {
    List<AlquilerExtra> obtenerTodos();
    AlquilerExtra obtenerPorId(int id);
    boolean registrar(AlquilerExtra extra);
    boolean actualizar(AlquilerExtra extra);
    boolean eliminar(int id);
}