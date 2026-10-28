package dao;

import modelo.CheckinCheckout;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class CheckinCheckoutDaoImpl implements ICheckinCheckoutDao {

    @Override
    public boolean insertar(CheckinCheckout ch) {
        String sql = "{CALL sp_insertar_checkin_checkout(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, ch.getIdAlquiler());
            cs.setInt(2, ch.getIdUsuarioEmpleado());
            cs.setString(3, ch.getTipo());
            cs.setTimestamp(4, ch.getFechaHoraReal() != null ? Timestamp.valueOf(ch.getFechaHoraReal()) : null);
            cs.setInt(5, ch.getKilometraje());
            cs.setString(6, ch.getNivelCombustible());
            cs.setString(7, ch.getObservaciones());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(CheckinCheckout ch) {
        String sql = "{CALL sp_modificar_checkin_checkout(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, ch.getIdRegistro());
            cs.setInt(2, ch.getIdAlquiler());
            cs.setInt(3, ch.getIdUsuarioEmpleado());
            cs.setString(4, ch.getTipo());
            cs.setTimestamp(5, ch.getFechaHoraReal() != null ? Timestamp.valueOf(ch.getFechaHoraReal()) : null);
            cs.setInt(6, ch.getKilometraje());
            cs.setString(7, ch.getNivelCombustible());
            cs.setString(8, ch.getObservaciones());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idRegistro) {
        String sql = "{CALL sp_eliminar_checkin_checkout(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idRegistro);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public CheckinCheckout buscarPorId(int idRegistro) {
        String sql = "{CALL sp_buscar_checkin_checkout_por_id(?)}";
        CheckinCheckout ch = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idRegistro);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    ch = mapearCheckinCheckout(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return ch;
    }

    @Override
    public List<CheckinCheckout> listarTodos() {
        String sql = "{CALL sp_listar_checkin_checkouts()}";
        List<CheckinCheckout> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearCheckinCheckout(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private CheckinCheckout mapearCheckinCheckout(ResultSet rs) throws SQLException {
        CheckinCheckout ch = new CheckinCheckout();
        ch.setIdRegistro(rs.getInt("id_registro"));
        ch.setIdAlquiler(rs.getInt("id_alquiler"));
        ch.setIdUsuarioEmpleado(rs.getInt("id_usuario_empleado"));
        ch.setTipo(rs.getString("tipo"));
        
        Timestamp ts = rs.getTimestamp("fecha_hora_real");
        if (ts != null) {
            ch.setFechaHoraReal(ts.toLocalDateTime());
        }
        
        ch.setKilometraje(rs.getInt("kilometraje"));
        ch.setNivelCombustible(rs.getString("nivel_combustible"));
        ch.setObservaciones(rs.getString("observaciones"));
        return ch;
    }
}