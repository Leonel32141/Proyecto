package factory;

import controllers.AlquilerExtraController;

public class AlquilerExtraFactory {

    public static AlquilerExtraController crearAlquilerExtraController() {
        return new AlquilerExtraController();
    }
}