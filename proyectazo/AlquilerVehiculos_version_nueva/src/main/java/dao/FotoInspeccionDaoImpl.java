package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.FotoInspeccion;

public class FotoInspeccionDaoImpl implements IFotoInspeccionDao {
    @Override
    public List<FotoInspeccion> obtenerTodos() {
        List<FotoInspeccion> lista = new ArrayList<>();
        String sql = "SELECT * FROM foto_inspeccion";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                FotoInspeccion obj = new FotoInspeccion();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public FotoInspeccion obtenerPorId(int id) { return null; }
    @Override public boolean registrar(FotoInspeccion foto) { return true; }
    @Override public boolean actualizar(FotoInspeccion foto) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}