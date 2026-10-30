package dao;

import modelo.AlquilerExtra;
import java.util.List;

public interface IAlquilerExtraDao {
    boolean insertar(AlquilerExtra alquilerExtra);
    boolean modificar(AlquilerExtra alquilerExtra);
    boolean eliminar(int idAlquiler, int idExtra);
    AlquilerExtra buscarPorId(int idAlquiler, int idExtra);
    List<AlquilerExtra> listarTodos();
}