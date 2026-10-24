package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.TrasladoVehiculo;

public class TrasladoVehiculoDaoImpl implements ITrasladoVehiculoDao {
    @Override
    public List<TrasladoVehiculo> obtenerTodos() {
        List<TrasladoVehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM traslado_vehiculo";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                TrasladoVehiculo obj = new TrasladoVehiculo();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public TrasladoVehiculo obtenerPorId(int id) { return null; }
    @Override public boolean registrar(TrasladoVehiculo traslado) { return true; }
    @Override public boolean actualizar(TrasladoVehiculo traslado) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}