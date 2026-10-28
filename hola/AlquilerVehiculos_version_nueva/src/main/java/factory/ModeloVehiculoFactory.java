package factory;

import dao.IModeloVehiculoDao;
import dao.ModeloVehiculoDaoImpl;

public class ModeloVehiculoFactory {
    public static IModeloVehiculoDao crearModeloVehiculoDao() {
        return new ModeloVehiculoDaoImpl();
    }
}