package dao;

import java.util.List;
import modelo.CheckinCheckout;

public interface ICheckinCheckoutDao {
    List<CheckinCheckout> obtenerTodos();
    CheckinCheckout obtenerPorId(int id);
    boolean registrar(CheckinCheckout checkinCheckout);
    boolean actualizar(CheckinCheckout checkinCheckout);
    boolean eliminar(int id);
}