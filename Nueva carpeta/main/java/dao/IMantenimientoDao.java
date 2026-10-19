package dao;

import java.util.List;
import modelo.Mantenimiento;

public interface IMantenimientoDao {
    List<Mantenimiento> obtenerTodos();
    Mantenimiento obtenerPorId(int id);
    boolean registrar(Mantenimiento mantenimiento);
    boolean actualizar(Mantenimiento mantenimiento);
    boolean eliminar(int id);
}