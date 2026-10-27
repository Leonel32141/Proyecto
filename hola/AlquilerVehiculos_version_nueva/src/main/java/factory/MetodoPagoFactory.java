package factory;

import dao.IMetodoPagoDao;
import dao.MetodoPagoDaoImpl;

public class MetodoPagoFactory {
    public static IMetodoPagoDao crearMetodoPagoDao() {
        return new MetodoPagoDaoImpl();
    }
}