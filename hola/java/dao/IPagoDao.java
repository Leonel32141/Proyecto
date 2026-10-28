package dao;

import modelo.Pago;
import java.util.List;

public interface IPagoDao {
    boolean insertar(Pago pago);
    boolean modificar(Pago pago);
    boolean eliminar(int idPago);
    Pago buscarPorId(int idPago);
    List<Pago> listarTodos();
}