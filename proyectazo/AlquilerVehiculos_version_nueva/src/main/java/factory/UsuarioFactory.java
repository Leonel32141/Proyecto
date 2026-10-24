package factory;

import controllers.UsuarioController;

public class UsuarioFactory {

    // Método factory para instanciar el controlador de usuarios
    public static UsuarioController crearUsuarioController() {
        return new UsuarioController();
    }
}