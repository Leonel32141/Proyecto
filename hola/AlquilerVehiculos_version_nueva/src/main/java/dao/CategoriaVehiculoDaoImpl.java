package dao;

import modelo.CategoriaVehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class CategoriaVehiculoDaoImpl implements ICategoriaVehiculoDao {

    @Override
    public boolean insertar(CategoriaVehiculo categoria) {
        String sql = "{CALL sp_insertar_categoria(?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, categoria.getNombreCategoria());
            cs.setString(2, categoria.getDescripcion());
            cs.setBigDecimal(3, categoria.getTarifaBaseDia()); // <--- Corregido al nombre exacto del modelo

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(CategoriaVehiculo categoria) {
        String sql = "{CALL sp_modificar_categoria(?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, categoria.getIdCategoria());
            cs.setString(2, categoria.getNombreCategoria());
            cs.setString(3, categoria.getDescripcion());
            cs.setBigDecimal(4, categoria.getTarifaBaseDia()); // <--- Corregido al nombre exacto del modelo

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idCategoria) {
        String sql = "{CALL sp_eliminar_categoria(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idCategoria);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CategoriaVehiculo buscarPorId(int idCategoria) {
        String sql = "{CALL sp_buscar_categoria_por_id(?)}";
        CategoriaVehiculo categoria = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idCategoria);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    categoria = mapearCategoria(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return categoria;
    }

    @Override
    public List<CategoriaVehiculo> listarTodos() {
        String sql = "{CALL sp_listar_categorias()}";
        List<CategoriaVehiculo> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearCategoria(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private CategoriaVehiculo mapearCategoria(ResultSet rs) throws SQLException {
        CategoriaVehiculo c = new CategoriaVehiculo();
        c.setIdCategoria(rs.getInt("id_categoria"));
        c.setNombreCategoria(rs.getString("nombre_categoria"));
        c.setDescripcion(rs.getString("descripcion"));
        c.setTarifaBaseDia(rs.getBigDecimal("tarifa_base_diaria"));
        return c;
    }
}