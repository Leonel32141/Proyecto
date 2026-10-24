package controllers;

import modelo.CheckinCheckout;
import java.util.ArrayList;
import java.util.List;

public class CheckinCheckoutController {

    public List<CheckinCheckout> obtenerCheckinsCheckouts() {
        List<CheckinCheckout> lista = new ArrayList<>();
        return lista;
    }

    public void registrarCheckinCheckout(CheckinCheckout checkinCheckout) {
        System.out.println("Checkin/Checkout registrado correctamente.");
    }
}