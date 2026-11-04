package dao;

import modelo.CategoriaVehiculo;
import java.util.List;

public interface ICategoriaVehiculoDao {
    boolean insertar(CategoriaVehiculo categoria);
    boolean modificar(CategoriaVehiculo categoria);
    boolean eliminar(int idCategoria);
    CategoriaVehiculo buscarPorId(int idCategoria);
    List<CategoriaVehiculo> listarTodos();
}