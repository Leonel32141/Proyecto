package dao;

import modelo.Alquiler;
import modelo.Cliente;
import modelo.Vehiculo;
import modelo.Sucursal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AlquilerDaoImpl implements IAlquilerDao {

    @Override
    public boolean insertar(Alquiler a) {
        String sql = "{CALL sp_insertar_alquiler(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, a.getCliente().getIdUsuario());
            cs.setInt(2, a.getVehiculo().getIdVehiculo());
            cs.setInt(3, a.getSucursalRetiro().getIdSucursal());
            cs.setInt(4, a.getSucursalDevolucion().getIdSucursal());
            cs.setTimestamp(5, Timestamp.valueOf(a.getFechaReserva()));
            cs.setTimestamp(6, Timestamp.valueOf(a.getFechaInicioPautada()));
            cs.setTimestamp(7, Timestamp.valueOf(a.getFechaFinPautada()));
            cs.setBoolean(8, a.isEsOneway());
            cs.setDouble(9, a.getMontoTotal());
            cs.setDouble(10, a.getMontoSena());
            cs.setString(11, a.getEstadoReserva());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Alquiler a) {
        String sql = "{CALL sp_actualizar_alquiler(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, a.getIdAlquiler());
            cs.setInt(2, a.getCliente().getIdUsuario());
            cs.setInt(3, a.getVehiculo().getIdVehiculo());
            cs.setInt(4, a.getSucursalRetiro().getIdSucursal());
            cs.setInt(5, a.getSucursalDevolucion().getIdSucursal());
            cs.setTimestamp(6, Timestamp.valueOf(a.getFechaInicioPautada()));
            cs.setTimestamp(7, Timestamp.valueOf(a.getFechaFinPautada()));
            cs.setBoolean(8, a.isEsOneway());
            cs.setDouble(9, a.getMontoTotal());
            cs.setDouble(10, a.getMontoSena());
            cs.setString(11, a.getEstadoReserva());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        }
        return false;
    }

    @Override
    public Alquiler obtenerPorId(int idAlquiler) {
        String sql = "{CALL sp_obtener_alquiler_por_id(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idAlquiler);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapearAlquiler(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Alquiler> obtenerTodos() {
        List<Alquiler> lista = new ArrayList<>();
        String sql = "{CALL sp_obtener_todos_alquileres()}";
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
        Cliente cli = new Cliente();
        cli.setIdUsuario(rs.getInt("id_cliente"));

        Vehiculo veh = new Vehiculo();
        veh.setIdVehiculo(rs.getInt("id_vehiculo"));

        Sucursal ret = new Sucursal();
        ret.setIdSucursal(rs.getInt("id_sucursal_retiro"));

        Sucursal dev = new Sucursal();
        dev.setIdSucursal(rs.getInt("id_sucursal_devolucion"));

        Alquiler a = new Alquiler();
        a.setIdAlquiler(rs.getInt("id_alquiler"));
        a.setCliente(cli);
        a.setVehiculo(veh);
        a.setSucursalRetiro(ret);
        a.setSucursalDevolucion(dev);
        a.setFechaReserva(rs.getTimestamp("fecha_reserva").toLocalDateTime());
        a.setFechaInicioPautada(rs.getTimestamp("fecha_inicio_pautada").toLocalDateTime());
        a.setFechaFinPautada(rs.getTimestamp("fecha_fin_pautada").toLocalDateTime());
        a.setEsOneway(rs.getBoolean("es_oneway"));
        a.setMontoTotal(rs.getDouble("monto_total"));
        a.setMontoSena(rs.getDouble("monto_sena"));
        a.setEstadoReserva(rs.getString("estado_reserva"));

        return a;
    }
}