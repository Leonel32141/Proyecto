package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import config.Conexion;
import modelo.Cliente;

public class ClienteDaoImpl implements IClienteDao {
    @Override
    public List<Cliente> obtenerTodos() {
        List<Cliente> lista = new ArrayList<>();
        String sql = "SELECT * FROM cliente";
        try (Connection con = Conexion.getConnection(); Statement st = con.createStatement(); ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                Cliente obj = new Cliente();
                lista.add(obj);
            }
        } catch (SQLException e) { e.printStackTrace(); }
        return lista;
    }
    @Override public Cliente obtenerPorId(int id) { return null; }
    @Override public boolean registrar(Cliente cliente) { return true; }
    @Override public boolean actualizar(Cliente cliente) { return true; }
    @Override public boolean eliminar(int id) { return true; }
}