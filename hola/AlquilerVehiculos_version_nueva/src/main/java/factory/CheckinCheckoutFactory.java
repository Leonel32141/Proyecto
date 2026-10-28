package factory;

import dao.ICheckinCheckoutDao;
import dao.CheckinCheckoutDaoImpl;

public class CheckinCheckoutFactory {

    public static ICheckinCheckoutDao crearCheckinCheckoutDao() {
        return new CheckinCheckoutDaoImpl();
    }
}