package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.Marca;

public class MarcaDaoImpl implements IMarcaDao {
    @Override
    public List<Marca> obtenerTodos() {
        List<Marca> lista = new ArrayList<>();
        String sql = "SELECT * FROM marca";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Marca obj = new Marca();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public Marca obtenerPorId(int id) { return null; }
    @Override public boolean registrar(Marca marca) { return true; }
    @Override public boolean actualizar(Marca marca) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}