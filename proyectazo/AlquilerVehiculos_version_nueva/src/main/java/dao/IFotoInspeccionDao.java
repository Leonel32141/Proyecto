package dao;

import java.util.List;
import modelo.FotoInspeccion;

public interface IFotoInspeccionDao {
    List<FotoInspeccion> obtenerTodos();
    FotoInspeccion obtenerPorId(int id);
    boolean registrar(FotoInspeccion foto);
    boolean actualizar(FotoInspeccion foto);
    boolean eliminar(int id);
}