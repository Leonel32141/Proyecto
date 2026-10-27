package dao;

import modelo.Sucursal;
import java.util.List;

public interface ISucursalDao {
    boolean insertar(Sucursal sucursal);
    boolean modificar(Sucursal sucursal);
    boolean eliminar(int idSucursal);
    Sucursal buscarPorId(int idSucursal);
    List<Sucursal> listarTodos();
}