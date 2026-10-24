package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.ModeloVehiculo;

public class ModeloVehiculoDaoImpl implements IModeloVehiculoDao {
    @Override
    public List<ModeloVehiculo> obtenerTodos() {
        List<ModeloVehiculo> lista = new ArrayList<>();
        String sql = "SELECT * FROM modelo_vehiculo";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                ModeloVehiculo obj = new ModeloVehiculo();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public ModeloVehiculo obtenerPorId(int id) { return null; }
    @Override public boolean registrar(ModeloVehiculo modelo) { return true; }
    @Override public boolean actualizar(ModeloVehiculo modelo) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}