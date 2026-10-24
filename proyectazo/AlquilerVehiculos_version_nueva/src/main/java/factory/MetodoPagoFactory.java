package factory;

import controllers.MetodoPagoController;

public class MetodoPagoFactory {

    public static MetodoPagoController crearMetodoPagoController() {
        return new MetodoPagoController();
    }
}