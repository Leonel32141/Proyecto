package dao;

import modelo.AlquilerExtra; 
import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlquilerExtraDaoImpl implements IAlquilerExtraDao {

    @Override
    public boolean insertar(AlquilerExtra extra) {
        String sql = "{CALL sp_insertar_alquiler_extra(?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, extra.getIdAlquiler());
            cs.setInt(2, extra.getIdExtra());
            cs.setInt(3, extra.getCantidad());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(AlquilerExtra extra) {
        String sql = "{CALL sp_modificar_alquiler_extra(?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, extra.getIdAlquiler());
            cs.setInt(2, extra.getIdExtra());
            cs.setInt(3, extra.getCantidad());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idAlquiler, int idExtra) {
        String sql = "{CALL sp_eliminar_alquiler_extra(?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idAlquiler);
            cs.setInt(2, idExtra);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public AlquilerExtra buscarPorId(int idAlquiler, int idExtra) {
        String sql = "{CALL sp_buscar_alquiler_extra_por_id(?, ?)}";
        AlquilerExtra extra = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idAlquiler);
            cs.setInt(2, idExtra);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    extra = mapearAlquilerExtra(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extra;
    }

    @Override
    public List<AlquilerExtra> listarTodos() {
        String sql = "{CALL sp_listar_alquileres_extras()}";
        List<AlquilerExtra> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearAlquilerExtra(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private AlquilerExtra mapearAlquilerExtra(ResultSet rs) throws SQLException {
        AlquilerExtra ae = new AlquilerExtra();
        ae.setIdAlquiler(rs.getInt("id_alquiler"));
        ae.setIdExtra(rs.getInt("id_extra"));
        ae.setCantidad(rs.getInt("cantidad"));
        return ae;
    }
}