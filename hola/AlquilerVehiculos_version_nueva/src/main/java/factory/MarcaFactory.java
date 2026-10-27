package factory;

import dao.IMarcaDao;
import dao.MarcaDaoImpl;

public class MarcaFactory {
    public static IMarcaDao crearMarcaDao() {
        return new MarcaDaoImpl();
    }
}