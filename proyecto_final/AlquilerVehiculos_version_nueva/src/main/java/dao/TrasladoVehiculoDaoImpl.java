package dao;

import modelo.TrasladoVehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class TrasladoVehiculoDaoImpl implements ITrasladoVehiculoDao {

    @Override
    public boolean insertar(TrasladoVehiculo t) {
        String sql = "{CALL sp_insertar_traslado_vehiculo(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, t.getIdVehiculo());
            cs.setInt(2, t.getIdSucursalOrigen());
            cs.setInt(3, t.getIdSucursalDestino());
            cs.setInt(4, t.getIdUsuarioChofer());
            
            // Fecha Salida
            if (t.getFechaSalida() != null && !t.getFechaSalida().isEmpty()) {
                cs.setTimestamp(5, Timestamp.valueOf(t.getFechaSalida()));
            } else {
                cs.setNull(5, Types.TIMESTAMP);
            }
            
            // Fecha Llegada
            if (t.getFechaLlegada() != null && !t.getFechaLlegada().isEmpty()) {
                cs.setTimestamp(6, Timestamp.valueOf(t.getFechaLlegada()));
            } else {
                cs.setNull(6, Types.TIMESTAMP);
            }
            
            cs.setString(7, t.getObservaciones());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(TrasladoVehiculo t) {
        String sql = "{CALL sp_modificar_traslado_vehiculo(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, t.getIdTraslado());
            cs.setInt(2, t.getIdVehiculo());
            cs.setInt(3, t.getIdSucursalOrigen());
            cs.setInt(4, t.getIdSucursalDestino());
            cs.setInt(5, t.getIdUsuarioChofer());
            
            // Fecha Salida
            if (t.getFechaSalida() != null && !t.getFechaSalida().isEmpty()) {
                cs.setTimestamp(6, Timestamp.valueOf(t.getFechaSalida()));
            } else {
                cs.setNull(6, Types.TIMESTAMP);
            }
            
            // Fecha Llegada
            if (t.getFechaLlegada() != null && !t.getFechaLlegada().isEmpty()) {
                cs.setTimestamp(7, Timestamp.valueOf(t.getFechaLlegada()));
            } else {
                cs.setNull(7, Types.TIMESTAMP);
            }
            
            cs.setString(8, t.getObservaciones());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idTraslado) {
        String sql = "{CALL sp_eliminar_traslado_vehiculo(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idTraslado);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public TrasladoVehiculo buscarPorId(int idTraslado) {
        String sql = "{CALL sp_buscar_traslado_vehiculo_por_id(?)}";
        TrasladoVehiculo t = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idTraslado);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    t = mapearTrasladoVehiculo(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return t;
    }

    @Override
    public List<TrasladoVehiculo> listarTodos() {
        String sql = "{CALL sp_listar_traslados_vehiculos()}";
        List<TrasladoVehiculo> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearTrasladoVehiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private TrasladoVehiculo mapearTrasladoVehiculo(ResultSet rs) throws SQLException {
        TrasladoVehiculo t = new TrasladoVehiculo();
        t.setIdTraslado(rs.getInt("id_traslado"));
        t.setIdVehiculo(rs.getInt("id_vehiculo"));
        t.setIdSucursalOrigen(rs.getInt("id_sucursal_origen"));
        t.setIdSucursalDestino(rs.getInt("id_sucursal_destino"));
        t.setIdUsuarioChofer(rs.getInt("id_usuario_chofer"));
        
        Timestamp tsSalida = rs.getTimestamp("fecha_salida");
        if (tsSalida != null) {
            t.setFechaSalida(tsSalida.toLocalDateTime().toString().replace("T", " "));
        } else {
            t.setFechaSalida(null);
        }

        Timestamp tsLlegada = rs.getTimestamp("fecha_llegada");
        if (tsLlegada != null) {
            t.setFechaLlegada(tsLlegada.toLocalDateTime().toString().replace("T", " "));
        } else {
            t.setFechaLlegada(null);
        }

        t.setObservaciones(rs.getString("observaciones"));
        return t;
    }
}