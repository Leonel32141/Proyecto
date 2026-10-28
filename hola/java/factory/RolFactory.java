package factory;

import dao.IRolDao;
import dao.RolDaoImpl;

public class RolFactory {
    public static IRolDao crearRolDao() {
        return new RolDaoImpl();
    }
}