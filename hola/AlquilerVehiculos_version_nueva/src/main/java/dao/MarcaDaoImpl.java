package dao;

import modelo.Marca;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MarcaDaoImpl implements IMarcaDao {

    @Override
    public boolean insertar(Marca marca) {
        String sql = "{CALL sp_insertar_marca(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, marca.getNombreMarca());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Marca marca) {
        String sql = "{CALL sp_modificar_marca(?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, marca.getIdMarca());
            cs.setString(2, marca.getNombreMarca());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idMarca) {
        String sql = "{CALL sp_eliminar_marca(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idMarca);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Marca buscarPorId(int idMarca) {
        String sql = "{CALL sp_buscar_marca_por_id(?)}";
        Marca marca = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idMarca);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    marca = mapearMarca(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return marca;
    }

    @Override
    public List<Marca> listarTodos() {
        String sql = "{CALL sp_listar_marcas()}";
        List<Marca> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearMarca(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Marca mapearMarca(ResultSet rs) throws SQLException {
        Marca m = new Marca();
        m.setIdMarca(rs.getInt("id_marca"));
        m.setNombreMarca(rs.getString("nombre_marca"));
        return m;
    }
}