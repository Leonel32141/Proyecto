package dao;

import modelo.OpcionalExtra;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class OpcionalExtraDaoImpl implements IOpcionalExtraDao {

    @Override
    public boolean insertar(OpcionalExtra extra) {
        String sql = "{CALL sp_insertar_opcional_extra(?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, extra.getNombreExtra());
            cs.setString(2, extra.getDescripcion());
            cs.setBigDecimal(3, extra.getPrecioDiario());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(OpcionalExtra extra) {
        String sql = "{CALL sp_modificar_opcional_extra(?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, extra.getIdExtra());
            cs.setString(2, extra.getNombreExtra());
            cs.setString(3, extra.getDescripcion());
            cs.setBigDecimal(4, extra.getPrecioDiario());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idExtra) {
        String sql = "{CALL sp_eliminar_opcional_extra(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idExtra);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public OpcionalExtra buscarPorId(int idExtra) {
        String sql = "{CALL sp_buscar_opcional_extra_por_id(?)}";
        OpcionalExtra extra = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idExtra);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    extra = mapearOpcionalExtra(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return extra;
    }

    @Override
    public List<OpcionalExtra> listarTodos() {
        String sql = "{CALL sp_listar_opcionales_extras()}";
        List<OpcionalExtra> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearOpcionalExtra(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private OpcionalExtra mapearOpcionalExtra(ResultSet rs) throws SQLException {
        OpcionalExtra oe = new OpcionalExtra();
        oe.setIdExtra(rs.getInt("id_extra"));
        oe.setNombreExtra(rs.getString("nombre_extra"));
        oe.setDescripcion(rs.getString("descripcion"));
        oe.setPrecioDiario(rs.getBigDecimal("precio_diario"));
        return oe;
    }
}