package dao;

import modelo.Vehiculo;
import java.util.List;

public interface IVehiculoDao {
    boolean insertar(Vehiculo vehiculo);
    boolean actualizar(Vehiculo vehiculo);
    boolean eliminar(int idVehiculo);
    Vehiculo obtenerPorId(int idVehiculo);
    List<Vehiculo> obtenerTodos();
}