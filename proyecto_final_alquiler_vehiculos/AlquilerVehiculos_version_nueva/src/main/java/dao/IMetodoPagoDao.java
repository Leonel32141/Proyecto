package dao;

import modelo.MetodoPago;
import java.util.List;

public interface IMetodoPagoDao {
    boolean insertar(MetodoPago metodoPago);
    boolean modificar(MetodoPago metodoPago);
    boolean eliminar(int idMetodoPago);
    MetodoPago buscarPorId(int idMetodoPago);
    List<MetodoPago> listarTodos();
}