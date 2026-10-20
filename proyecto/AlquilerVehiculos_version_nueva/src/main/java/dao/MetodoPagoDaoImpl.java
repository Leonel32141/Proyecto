package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.MetodoPago;

public class MetodoPagoDaoImpl implements IMetodoPagoDao {
    @Override
    public List<MetodoPago> obtenerTodos() {
        List<MetodoPago> lista = new ArrayList<>();
        String sql = "SELECT * FROM metodo_pago";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                MetodoPago obj = new MetodoPago();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public MetodoPago obtenerPorId(int id) { return null; }
    @Override public boolean registrar(MetodoPago metodoPago) { return true; }
    @Override public boolean actualizar(MetodoPago metodoPago) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}