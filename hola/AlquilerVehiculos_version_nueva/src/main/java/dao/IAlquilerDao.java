package dao;

import java.util.List;
import modelo.Alquiler;

public interface IAlquilerDao {
    boolean insertar(Alquiler alquiler);
    boolean modificar(Alquiler alquiler);
    boolean eliminar(int idAlquiler);
    Alquiler buscarPorId(int idAlquiler);
    List<Alquiler> listarTodos();
    boolean tieneAlquilerActivo(int idCliente);
    boolean cancelarReserva(int idAlquiler); // <-- Método agregado para la cancelación
}