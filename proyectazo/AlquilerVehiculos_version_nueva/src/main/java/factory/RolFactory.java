package factory;

import controllers.RolController;

public class RolFactory {

    public static RolController crearRolController() {
        return new RolController();
    }
}