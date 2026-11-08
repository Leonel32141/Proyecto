package factory;

import dao.IMantenimientoDao;
import dao.MantenimientoDaoImpl;

public class MantenimientoFactory {
    public static IMantenimientoDao crearMantenimientoDao() {
        return new MantenimientoDaoImpl();
    }
}