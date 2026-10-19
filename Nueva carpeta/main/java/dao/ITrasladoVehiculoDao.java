package dao;

import java.util.List;
import modelo.TrasladoVehiculo;

public interface ITrasladoVehiculoDao {
    List<TrasladoVehiculo> obtenerTodos();
    TrasladoVehiculo obtenerPorId(int id);
    boolean registrar(TrasladoVehiculo traslado);
    boolean actualizar(TrasladoVehiculo traslado);
    boolean eliminar(int id);
}