package service;

import dao.AlquilerDaoImpl;
import dao.IAlquilerDao;
import dao.IVehiculoDao;
import factory.AlquilerFactory;
import factory.VehiculoFactory;
import modelo.Alquiler;
import modelo.Vehiculo;
import java.time.LocalDate;
import java.util.List;

public class AlquilerService {
    private final IAlquilerDao alquilerDao = AlquilerFactory.crearAlquilerDao();
    private final IVehiculoDao vehiculoDao = VehiculoFactory.crearVehiculoDao();

    // Métodos puente requeridos por el AlquilerServlet
    public Alquiler buscarPorId(int id) {
        return alquilerDao.buscarPorId(id);
    }

    public List<Alquiler> listarTodos() {
        return alquilerDao.listarTodos();
    }

    public boolean insertar(Alquiler alquiler) {
        return alquilerDao.insertar(alquiler);
    }

    public boolean modificar(Alquiler alquiler) {
        return alquilerDao.modificar(alquiler);
    }

    public boolean eliminar(int id) {
        return alquilerDao.eliminar(id);
    }

    public boolean clienteTieneAlquilerActivo(int idCliente) {
        return ((AlquilerDaoImpl) alquilerDao).tieneAlquilerActivo(idCliente);
    }

    // Lógica de negocio pesada separada del Servlet
    public int asignarVehiculoDisponible(Alquiler alquiler) throws Exception {
        int modelIdIngresado = alquiler.getIdVehiculo(); 
        List<Vehiculo> todosLosVehiculos = vehiculoDao.listarTodos();
        List<Alquiler> todosLosAlquileres = alquilerDao.listarTodos();
        
        LocalDate nuevoInicio = (alquiler.getFechaInicioPautada() != null && !alquiler.getFechaInicioPautada().isEmpty()) 
                ? LocalDate.parse(alquiler.getFechaInicioPautada().substring(0, 10)) : null;
        LocalDate nuevoFin = (alquiler.getFechaFinPautada() != null && !alquiler.getFechaFinPautada().isEmpty()) 
                ? LocalDate.parse(alquiler.getFechaFinPautada().substring(0, 10)) : null;

        for (Vehiculo v : todosLosVehiculos) {
            String estadoVehiculo = v.getEstado() != null ? v.getEstado().toUpperCase() : "DISPONIBLE";
            
            if (v.getIdModelo() == modelIdIngresado && "DISPONIBLE".equals(estadoVehiculo)) {
                boolean estaOcupado = false;
                if (nuevoInicio != null && nuevoFin != null) {
                    for (Alquiler a : todosLosAlquileres) {
                        if (a.getIdVehiculo() == v.getIdVehiculo()) {
                            LocalDate inicioExistente = (a.getFechaInicioPautada() != null && !a.getFechaInicioPautada().isEmpty()) 
                                    ? LocalDate.parse(a.getFechaInicioPautada().substring(0, 10)) : null;
                            LocalDate finExistente = (a.getFechaFinPautada() != null && !a.getFechaFinPautada().isEmpty()) 
                                    ? LocalDate.parse(a.getFechaFinPautada().substring(0, 10)) : null;
                            
                            if (inicioExistente != null && finExistente != null) {
                                if (!nuevoInicio.isAfter(finExistente) && !nuevoFin.isBefore(inicioExistente)) {
                                    estaOcupado = true;
                                    break;
                                }
                            }
                        }
                    }
                }
                if (!estaOcupado) {
                    return v.getIdVehiculo(); // Devuelve el ID del vehículo físico disponible
                }
            }
        }
        return -1; // No hay stock disponible
    }
}