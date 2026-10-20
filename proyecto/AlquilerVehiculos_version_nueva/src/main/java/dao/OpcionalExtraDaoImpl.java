package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.OpcionalExtra;

public class OpcionalExtraDaoImpl implements IOpcionalExtraDao {
    @Override
    public List<OpcionalExtra> obtenerTodos() {
        List<OpcionalExtra> lista = new ArrayList<>();
        String sql = "SELECT * FROM opcional_extra";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                OpcionalExtra obj = new OpcionalExtra();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public OpcionalExtra obtenerPorId(int id) { return null; }
    @Override public boolean registrar(OpcionalExtra opcional) { return true; }
    @Override public boolean actualizar(OpcionalExtra opcional) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}