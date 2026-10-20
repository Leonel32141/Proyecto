package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.CategoriaVehiculo;

public class CategoriaVehiculoDaoImpl implements ICategoriaVehiculoDao {
    @Override
    public List<CategoriaVehiculo> obtenerTodos() {
        List<CategoriaVehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM categoria_vehiculo";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                CategoriaVehiculo obj = new CategoriaVehiculo();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public CategoriaVehiculo obtenerPorId(int id) { return null; }
    @Override public boolean registrar(CategoriaVehiculo categoria) { return true; }
    @Override public boolean actualizar(CategoriaVehiculo categoria) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}