package dao;

import modelo.Mantenimiento;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class MantenimientoDaoImpl implements IMantenimientoDao {

    @Override
    public boolean insertar(Mantenimiento m) {
        String sql = "{CALL sp_insertar_mantenimiento(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, m.getIdVehiculo());
            cs.setString(2, m.getTipoMantenimiento());
            cs.setDate(3, m.getFechaIngreso() != null ? Date.valueOf(m.getFechaIngreso()) : null);
            cs.setDate(4, m.getFechaEgresoEstimada() != null ? Date.valueOf(m.getFechaEgresoEstimada()) : null);
            cs.setBigDecimal(5, m.getCostoTotal());
            cs.setString(6, m.getTallerMecanico());
            cs.setString(7, m.getDetalleTrabajo());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Mantenimiento m) {
        String sql = "{CALL sp_modificar_mantenimiento(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, m.getIdMantenimiento());
            cs.setInt(2, m.getIdVehiculo());
            cs.setString(3, m.getTipoMantenimiento());
            cs.setDate(4, m.getFechaIngreso() != null ? Date.valueOf(m.getFechaIngreso()) : null);
            cs.setDate(5, m.getFechaEgresoEstimada() != null ? Date.valueOf(m.getFechaEgresoEstimada()) : null);
            cs.setBigDecimal(6, m.getCostoTotal());
            cs.setString(7, m.getTallerMecanico());
            cs.setString(8, m.getDetalleTrabajo());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idMantenimiento) {
        String sql = "{CALL sp_eliminar_mantenimiento(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idMantenimiento);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Mantenimiento buscarPorId(int idMantenimiento) {
        String sql = "{CALL sp_buscar_mantenimiento_por_id(?)}";
        Mantenimiento m = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idMantenimiento);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    m = mapearMantenimiento(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return m;
    }

    @Override
    public List<Mantenimiento> listarTodos() {
        String sql = "{CALL sp_listar_mantenimientos()}";
        List<Mantenimiento> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearMantenimiento(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Mantenimiento mapearMantenimiento(ResultSet rs) throws SQLException {
        Mantenimiento m = new Mantenimiento();
        m.setIdMantenimiento(rs.getInt("id_mantenimiento"));
        m.setIdVehiculo(rs.getInt("id_vehiculo"));
        m.setTipoMantenimiento(rs.getString("tipo_mantenimiento"));
        
        Date fechaIng = rs.getDate("fecha_ingreso");
        if (fechaIng != null) m.setFechaIngreso(fechaIng.toLocalDate());

        Date fechaEgr = rs.getDate("fecha_egreso_estimada");
        if (fechaEgr != null) m.setFechaEgresoEstimada(fechaEgr.toLocalDate());

        m.setCostoTotal(rs.getBigDecimal("costo_total"));
        m.setTallerMecanico(rs.getString("taller_mecanico"));
        m.setDetalleTrabajo(rs.getString("detalle_trabajo"));
        return m;
    }
}