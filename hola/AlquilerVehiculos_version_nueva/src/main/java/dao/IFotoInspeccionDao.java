package dao;

import modelo.FotoInspeccion;
import java.util.List;

public interface IFotoInspeccionDao {
    boolean insertar(FotoInspeccion fotoInspeccion);
    boolean modificar(FotoInspeccion fotoInspeccion);
    boolean eliminar(int idFoto);
    FotoInspeccion buscarPorId(int idFoto);
    List<FotoInspeccion> listarTodos();
}