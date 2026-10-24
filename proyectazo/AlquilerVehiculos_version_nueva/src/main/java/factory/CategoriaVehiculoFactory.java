package factory;

import controllers.CategoriaVehiculoController;

public class CategoriaVehiculoFactory {

    public static CategoriaVehiculoController crearCategoriaVehiculoController() {
        return new CategoriaVehiculoController();
    }
}