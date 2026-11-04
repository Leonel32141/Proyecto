package dao;

import modelo.CheckinCheckout;
import java.util.List;

public interface ICheckinCheckoutDao {
    boolean insertar(CheckinCheckout checkinCheckout);
    boolean modificar(CheckinCheckout checkinCheckout);
    boolean eliminar(int idRegistro);
    CheckinCheckout buscarPorId(int idRegistro);
    List<CheckinCheckout> listarTodos();
}