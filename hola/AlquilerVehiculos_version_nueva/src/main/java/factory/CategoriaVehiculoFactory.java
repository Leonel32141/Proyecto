package factory;

import dao.ICategoriaVehiculoDao;
import dao.CategoriaVehiculoDaoImpl;

public class CategoriaVehiculoFactory {

    public static ICategoriaVehiculoDao crearCategoriaVehiculoDao() {
        return new CategoriaVehiculoDaoImpl();
    }
}