package dao;


import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.Alquiler;

public class AlquilerDaoImpl implements IAlquilerDao {

    @Override
    public boolean insertar(Alquiler alquiler) {
        String sql = "{CALL sp_insertar_alquiler(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion(); // <--- Corregido al nombre real del método
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, alquiler.getIdCliente());
            cs.setInt(2, alquiler.getIdVehiculo());
            cs.setInt(3, alquiler.getIdSucursalRetiro());
            cs.setInt(4, alquiler.getIdSucursalDevolucion());
            cs.setTimestamp(5, alquiler.getFechaReserva() != null ? Timestamp.valueOf(alquiler.getFechaReserva()) : null);
            cs.setTimestamp(6, alquiler.getFechaInicioPautada() != null ? Timestamp.valueOf(alquiler.getFechaInicioPautada()) : null);
            cs.setTimestamp(7, alquiler.getFechaFinPautada() != null ? Timestamp.valueOf(alquiler.getFechaFinPautada()) : null);
            cs.setBoolean(8, alquiler.isEsOneway());
            cs.setBigDecimal(9, alquiler.getMontoTotal());
            cs.setBigDecimal(10, alquiler.getMontoSena());
            cs.setString(11, alquiler.getEstadoReserva());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Alquiler alquiler) {
        String sql = "{CALL sp_modificar_alquiler(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, alquiler.getIdAlquiler());
            cs.setInt(2, alquiler.getIdCliente());
            cs.setInt(3, alquiler.getIdVehiculo());
            cs.setInt(4, alquiler.getIdSucursalRetiro());
            cs.setInt(5, alquiler.getIdSucursalDevolucion());
            cs.setTimestamp(6, alquiler.getFechaInicioPautada() != null ? Timestamp.valueOf(alquiler.getFechaInicioPautada()) : null);
            cs.setTimestamp(7, alquiler.getFechaFinPautada() != null ? Timestamp.valueOf(alquiler.getFechaFinPautada()) : null);
            cs.setBoolean(8, alquiler.isEsOneway());
            cs.setBigDecimal(9, alquiler.getMontoTotal());
            cs.setBigDecimal(10, alquiler.getMontoSena());
            cs.setString(11, alquiler.getEstadoReserva());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idAlquiler) {
        String sql = "{CALL sp_eliminar_alquiler(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idAlquiler);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Alquiler buscarPorId(int idAlquiler) {
        String sql = "{CALL sp_buscar_alquiler_por_id(?)}";
        Alquiler alquiler = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idAlquiler);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    alquiler = mapearAlquiler(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alquiler;
    }

    @Override
    public List<Alquiler> listarTodos() {
        String sql = "{CALL sp_listar_alquileres()}";
        List<Alquiler> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearAlquiler(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Alquiler mapearAlquiler(ResultSet rs) throws SQLException {
        Alquiler a = new Alquiler();
        a.setIdAlquiler(rs.getInt("id_alquiler"));
        a.setIdCliente(rs.getInt("id_cliente"));
        a.setIdVehiculo(rs.getInt("id_vehiculo"));
        a.setIdSucursalRetiro(rs.getInt("id_sucursal_retiro"));
        a.setIdSucursalDevolucion(rs.getInt("id_sucursal_devolucion"));
        
        Timestamp tsReserva = rs.getTimestamp("fecha_reserva");
        if (tsReserva != null) a.setFechaReserva(tsReserva.toLocalDateTime());

        Timestamp tsInicio = rs.getTimestamp("fecha_inicio_pautada");
        if (tsInicio != null) a.setFechaInicioPautada(tsInicio.toLocalDateTime());

        Timestamp tsFin = rs.getTimestamp("fecha_fin_pautada");
        if (tsFin != null) a.setFechaFinPautada(tsFin.toLocalDateTime());

        a.setEsOneway(rs.getBoolean("es_oneway"));
        a.setMontoTotal(rs.getBigDecimal("monto_total"));
        a.setMontoSena(rs.getBigDecimal("monto_sena"));
        a.setEstadoReserva(rs.getString("estado_reserva"));
        return a;
    }
}