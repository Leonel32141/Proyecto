package dao;

import java.util.List;
import modelo.CategoriaVehiculo;

public interface ICategoriaVehiculoDao {
    List<CategoriaVehiculo> obtenerTodos();
    CategoriaVehiculo obtenerPorId(int id);
    boolean registrar(CategoriaVehiculo categoria);
    boolean actualizar(CategoriaVehiculo categoria);
    boolean eliminar(int id);
}