package factory;

import dao.AlquilerDaoImpl;
import dao.IAlquilerDao;

public class AlquilerFactory {

    // Método factory para instanciar el DAO de alquileres
    public static IAlquilerDao crearAlquilerDao() {
        return new AlquilerDaoImpl();
    }
}