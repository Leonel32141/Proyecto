package dao;

import modelo.Vehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class VehiculoDaoImpl implements IVehiculoDao {

    @Override
    public boolean insertar(Vehiculo v) {
        String sql = "{CALL sp_insertar_vehiculo(?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, v.getPatente());
            cs.setInt(2, v.getIdModelo());
            cs.setInt(3, v.getIdCategoria());
            cs.setInt(4, v.getIdSucursalActual());
            cs.setInt(5, v.getKilometrajeActual());
            cs.setString(6, v.getEstado());
            cs.setDate(7, v.getVencimientoSeguro() != null ? Date.valueOf(v.getVencimientoSeguro()) : null);
            cs.setDate(8, v.getVencimientoPatente() != null ? Date.valueOf(v.getVencimientoPatente()) : null);

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Vehiculo v) {
        String sql = "{CALL sp_modificar_vehiculo(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, v.getIdVehiculo());
            cs.setString(2, v.getPatente());
            cs.setInt(3, v.getIdModelo());
            cs.setInt(4, v.getIdCategoria());
            cs.setInt(5, v.getIdSucursalActual());
            cs.setInt(6, v.getKilometrajeActual());
            cs.setString(7, v.getEstado());
            cs.setDate(8, v.getVencimientoSeguro() != null ? Date.valueOf(v.getVencimientoSeguro()) : null);
            cs.setDate(9, v.getVencimientoPatente() != null ? Date.valueOf(v.getVencimientoPatente()) : null);

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idVehiculo) {
        String sql = "{CALL sp_eliminar_vehiculo(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idVehiculo);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Vehiculo buscarPorId(int idVehiculo) {
        String sql = "{CALL sp_buscar_vehiculo_por_id(?)}";
        Vehiculo v = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idVehiculo);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    v = mapearVehiculo(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return v;
    }

    @Override
    public List<Vehiculo> listarTodos() {
        String sql = "{CALL sp_listar_vehiculos()}";
        List<Vehiculo> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearVehiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Vehiculo mapearVehiculo(ResultSet rs) throws SQLException {
        Vehiculo v = new Vehiculo();
        v.setIdVehiculo(rs.getInt("id_vehiculo"));
        v.setPatente(rs.getString("patente"));
        v.setIdModelo(rs.getInt("id_modelo"));
        v.setIdCategoria(rs.getInt("id_categoria"));
        v.setIdSucursalActual(rs.getInt("id_sucursal_actual"));
        v.setKilometrajeActual(rs.getInt("kilometrajeActual".toLowerCase().equals("kilometraje_actual") ? "kilometraje_actual" : "kilometraje_actual")); // asegurando nombre de columna correcto
        v.setKilometrajeActual(rs.getInt("kilometraje_actual"));
        v.setEstado(rs.getString("estado"));
        
        Date dateSeguro = rs.getDate("vencimiento_seguro");
        if (dateSeguro != null) {
            v.setVencimientoSeguro(dateSeguro.toLocalDate());
        }

        Date datePatente = rs.getDate("vencimiento_patente");
        if (datePatente != null) {
            v.setVencimientoPatente(datePatente.toLocalDate());
        }

        return v;
    }
}