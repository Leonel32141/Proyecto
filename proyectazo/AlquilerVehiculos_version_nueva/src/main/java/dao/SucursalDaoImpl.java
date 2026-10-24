package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.Sucursal;

public class SucursalDaoImpl implements ISucursalDao {
    @Override
    public List<Sucursal> obtenerTodos() {
        List<Sucursal> lista = new ArrayList<>();
        String sql = "SELECT * FROM sucursal";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Sucursal obj = new Sucursal();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public Sucursal obtenerPorId(int id) { return null; }
    @Override public boolean registrar(Sucursal sucursal) { return true; }
    @Override public boolean actualizar(Sucursal sucursal) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}