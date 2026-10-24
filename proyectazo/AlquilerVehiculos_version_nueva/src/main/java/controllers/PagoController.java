package controllers;

import modelo.Pago;
import java.util.ArrayList;
import java.util.List;

public class PagoController {

    // Método para obtener la lista de pagos
    public List<Pago> obtenerPagos() {
        List<Pago> lista = new ArrayList<>();
        // Acá después conectamos con el DAO para traerlos de la base de datos
        return lista;
    }

    public void registrarPago(Pago pago) {
        // Lógica para registrar un nuevo pago
        System.out.println("Pago registrado correctamente.");
    }
}