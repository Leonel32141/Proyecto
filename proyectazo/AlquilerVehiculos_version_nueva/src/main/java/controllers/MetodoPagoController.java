package controllers;

import modelo.MetodoPago;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoController {

    public List<MetodoPago> obtenerMetodosPago() {
        List<MetodoPago> lista = new ArrayList<>();
        return lista;
    }

    public void registrarMetodoPago(MetodoPago metodoPago) {
        System.out.println("Método de pago registrado correctamente.");
    }
}