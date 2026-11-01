package factory;

import dao.IPagoDao;
import dao.PagoDaoImpl;

public class PagoFactory {
    public static IPagoDao crearPagoDao() {
        return new PagoDaoImpl();
    }
}