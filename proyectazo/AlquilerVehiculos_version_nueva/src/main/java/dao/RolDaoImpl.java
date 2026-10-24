package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.Rol;

public class RolDaoImpl implements IRolDao {
    @Override
    public List<Rol> obtenerTodos() {
        List<Rol> lista = new ArrayList<>();
        String sql = "SELECT * FROM rol";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Rol obj = new Rol();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public Rol obtenerPorId(int id) { return null; }
    @Override public boolean registrar(Rol rol) { return true; }
    @Override public boolean actualizar(Rol rol) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}