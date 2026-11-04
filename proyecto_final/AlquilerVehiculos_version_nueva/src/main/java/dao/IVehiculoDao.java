package dao;

import modelo.Vehiculo;
import java.util.List;

public interface IVehiculoDao {
    boolean insertar(Vehiculo vehiculo);
    boolean modificar(Vehiculo vehiculo);
    boolean eliminar(int idVehiculo);
    Vehiculo buscarPorId(int idVehiculo);
    List<Vehiculo> listarTodos();
}