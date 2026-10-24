package factory;

import controllers.CheckinCheckoutController;

public class CheckinCheckoutFactory {

    public static CheckinCheckoutController crearCheckinCheckoutController() {
        return new CheckinCheckoutController();
    }
}