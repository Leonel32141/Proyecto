package factory;

import controllers.SucursalController;

public class SucursalFactory {

    // Método factory para instanciar el controlador de sucursales
    public static SucursalController crearSucursalController() {
        return new SucursalController();
    }
}