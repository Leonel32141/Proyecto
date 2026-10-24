package factory;

import controllers.VehiculoController;

public class VehiculoFactory {

    // Método factory para instanciar el controlador de forma limpia
    public static VehiculoController crearVehiculoController() {
        return new VehiculoController();
    }
}