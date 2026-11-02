package factory;

import dao.ITrasladoVehiculoDao;
import dao.TrasladoVehiculoDaoImpl;

public class TrasladoVehiculoFactory {
    public static ITrasladoVehiculoDao crearTrasladoVehiculoDao() {
        return new TrasladoVehiculoDaoImpl();
    }
}