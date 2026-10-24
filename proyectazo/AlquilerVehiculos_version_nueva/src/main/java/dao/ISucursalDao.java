package dao;

import java.util.List;
import modelo.Sucursal;

public interface ISucursalDao {
    List<Sucursal> obtenerTodos();
    Sucursal obtenerPorId(int id);
    boolean registrar(Sucursal sucursal);
    boolean actualizar(Sucursal sucursal);
    boolean eliminar(int id);
}