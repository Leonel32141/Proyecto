package factory;

import dao.IUsuarioDao;
import dao.UsuarioDaoImpl;

public class UsuarioFactory {
    public static IUsuarioDao crearUsuarioDao() {
        return new UsuarioDaoImpl();
    }
}