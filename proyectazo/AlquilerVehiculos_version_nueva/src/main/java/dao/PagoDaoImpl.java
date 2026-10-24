package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.Pago;

public class PagoDaoImpl implements IPagoDao {
    @Override
    public List<Pago> obtenerTodos() {
        List<Pago> lista = new ArrayList<>();
        String sql = "SELECT * FROM pago";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Pago obj = new Pago();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public Pago obtenerPorId(int id) { return null; }
    @Override public boolean registrar(Pago pago) { return true; }
    @Override public boolean actualizar(Pago pago) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}