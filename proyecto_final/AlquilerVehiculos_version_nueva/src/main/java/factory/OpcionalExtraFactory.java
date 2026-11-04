package factory;

import dao.IOpcionalExtraDao;
import dao.OpcionalExtraDaoImpl;

public class OpcionalExtraFactory {
    public static IOpcionalExtraDao crearOpcionalExtraDao() {
        return new OpcionalExtraDaoImpl();
    }
}