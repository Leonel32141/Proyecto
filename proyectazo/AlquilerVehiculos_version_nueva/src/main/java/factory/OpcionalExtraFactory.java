package factory;

import controllers.OpcionalExtraController;

public class OpcionalExtraFactory {

    public static OpcionalExtraController crearOpcionalExtraController() {
        return new OpcionalExtraController();
    }
}