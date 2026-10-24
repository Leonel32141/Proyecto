package factory;

import controllers.PagoController;

public class PagoFactory {

    // Método factory para instanciar el controlador de pagos
    public static PagoController crearPagoController() {
        return new PagoController();
    }
}