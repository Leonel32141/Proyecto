package factory;

import dao.IFotoInspeccionDao;
import dao.FotoInspeccionDaoImpl;

public class FotoInspeccionFactory {
    public static IFotoInspeccionDao crearFotoInspeccionDao() {
        return new FotoInspeccionDaoImpl();
    }
}