package factory;

import controllers.TrasladoVehiculoController;

public class TrasladoVehiculoFactory {

    public static TrasladoVehiculoController crearTrasladoVehiculoController() {
        return new TrasladoVehiculoController();
    }
}