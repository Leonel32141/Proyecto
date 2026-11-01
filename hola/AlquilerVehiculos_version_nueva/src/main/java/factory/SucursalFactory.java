package factory;

import dao.ISucursalDao;
import dao.SucursalDaoImpl;

public class SucursalFactory {
    public static ISucursalDao crearSucursalDao() {
        return new SucursalDaoImpl();
    }
}