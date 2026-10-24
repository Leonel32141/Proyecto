package factory;

import controllers.FotoInspeccionController;

public class FotoInspeccionFactory {

    public static FotoInspeccionController crearFotoInspeccionController() {
        return new FotoInspeccionController();
    }
}