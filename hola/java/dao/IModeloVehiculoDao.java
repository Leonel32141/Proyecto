package dao;

import modelo.ModeloVehiculo;
import java.util.List;

public interface IModeloVehiculoDao {
    boolean insertar(ModeloVehiculo modeloVehiculo);
    boolean modificar(ModeloVehiculo modeloVehiculo);
    boolean eliminar(int idModelo);
    ModeloVehiculo buscarPorId(int idModelo);
    List<ModeloVehiculo> listarTodos();
}