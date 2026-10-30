package factory;

import dao.AlquilerExtraDaoImpl;
import dao.IAlquilerExtraDao;

public class AlquilerExtraFactory {

    public static IAlquilerExtraDao crearAlquilerExtraDao() {
        return new AlquilerExtraDaoImpl();
    }
}