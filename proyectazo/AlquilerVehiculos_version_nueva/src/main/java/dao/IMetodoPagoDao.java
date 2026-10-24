package dao;

import java.util.List;
import modelo.MetodoPago;

public interface IMetodoPagoDao {
    List<MetodoPago> obtenerTodos();
    MetodoPago obtenerPorId(int id);
    boolean registrar(MetodoPago metodoPago);
    boolean actualizar(MetodoPago metodoPago);
    boolean eliminar(int id);
}