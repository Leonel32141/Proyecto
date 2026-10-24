package factory;

import controllers.MarcaController;

public class MarcaFactory {

    public static MarcaController crearMarcaController() {
        return new MarcaController();
    }
}