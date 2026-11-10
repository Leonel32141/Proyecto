package factory;

import dao.IVehiculoDao;
import dao.VehiculoDaoImpl;

public class VehiculoFactory {
    public static IVehiculoDao crearVehiculoDao() {
        return new VehiculoDaoImpl();
    }
}