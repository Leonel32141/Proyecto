package dao;

import java.util.List;
import modelo.ModeloVehiculo;

public interface IModeloVehiculoDao {
    List<ModeloVehiculo> obtenerTodos();
    ModeloVehiculo obtenerPorId(int id);
    boolean registrar(ModeloVehiculo modelo);
    boolean actualizar(ModeloVehiculo modelo);
    boolean eliminar(int id);
}