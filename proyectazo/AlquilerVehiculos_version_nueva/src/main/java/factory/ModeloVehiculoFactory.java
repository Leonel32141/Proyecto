package factory;

import controllers.ModeloVehiculoController;

public class ModeloVehiculoFactory {

    public static ModeloVehiculoController crearModeloVehiculoController() {
        return new ModeloVehiculoController();
    }
}