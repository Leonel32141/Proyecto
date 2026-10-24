package factory;

import controllers.AlquilerController;

public class AlquilerFactory {

    // Método factory para instanciar el controlador de alquileres
    public static AlquilerController crearAlquilerController() {
        return new AlquilerController();
    }
}