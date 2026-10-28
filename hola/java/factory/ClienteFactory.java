package factory;

import dao.IClienteDao;
import dao.ClienteDaoImpl;

public class ClienteFactory {

    public static IClienteDao crearClienteDao() {
        return new ClienteDaoImpl();
    }
}