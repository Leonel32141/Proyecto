package factory;

import controllers.ClienteController;

public class ClienteFactory {

    // Método factory para instanciar el controlador de clientes
    public static ClienteController crearClienteController() {
        return new ClienteController();
    }
}