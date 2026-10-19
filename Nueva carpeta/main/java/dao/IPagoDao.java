package dao;

import java.util.List;
import modelo.Pago;

public interface IPagoDao {
    List<Pago> obtenerTodos();
    Pago obtenerPorId(int id);
    boolean registrar(Pago pago);
    boolean actualizar(Pago pago);
    boolean eliminar(int id);
}