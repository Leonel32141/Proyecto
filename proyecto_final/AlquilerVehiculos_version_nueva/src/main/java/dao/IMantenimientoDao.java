package dao;

import modelo.Mantenimiento;
import java.util.List;

public interface IMantenimientoDao {
    boolean insertar(Mantenimiento mantenimiento);
    boolean modificar(Mantenimiento mantenimiento);
    boolean eliminar(int idMantenimiento);
    Mantenimiento buscarPorId(int idMantenimiento);
    List<Mantenimiento> listarTodos();
}