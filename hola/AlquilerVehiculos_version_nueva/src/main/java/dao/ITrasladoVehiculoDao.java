package dao;

import modelo.TrasladoVehiculo;
import java.util.List;

public interface ITrasladoVehiculoDao {
    boolean insertar(TrasladoVehiculo traslado);
    boolean modificar(TrasladoVehiculo traslado);
    boolean eliminar(int idTraslado);
    TrasladoVehiculo buscarPorId(int idTraslado);
    List<TrasladoVehiculo> listarTodos();
}