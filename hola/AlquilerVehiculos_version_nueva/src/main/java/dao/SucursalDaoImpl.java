package dao;

import modelo.Sucursal;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class SucursalDaoImpl implements ISucursalDao {

    @Override
    public boolean insertar(Sucursal sucursal) {
        String sql = "{CALL sp_insertar_sucursal(?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, sucursal.getNombre());
            cs.setString(2, sucursal.getDireccion());
            cs.setString(3, sucursal.getCiudad());
            cs.setBoolean(4, sucursal.isActiva());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Sucursal sucursal) {
        String sql = "{CALL sp_modificar_sucursal(?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, sucursal.getIdSucursal());
            cs.setString(2, sucursal.getNombre());
            cs.setString(3, sucursal.getDireccion());
            cs.setString(4, sucursal.getCiudad());
            cs.setBoolean(5, sucursal.isActiva());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idSucursal) {
        String sql = "{CALL sp_eliminar_sucursal(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idSucursal);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Sucursal buscarPorId(int idSucursal) {
        String sql = "{CALL sp_buscar_sucursal_por_id(?)}";
        Sucursal sucursal = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idSucursal);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    sucursal = mapearSucursal(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return sucursal;
    }

    @Override
    public List<Sucursal> listarTodos() {
        String sql = "{CALL sp_listar_sucursales()}";
        List<Sucursal> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearSucursal(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Sucursal mapearSucursal(ResultSet rs) throws SQLException {
        Sucursal s = new Sucursal();
        s.setIdSucursal(rs.getInt("id_sucursal"));
        s.setNombre(rs.getString("nombre"));
        s.setDireccion(rs.getString("direccion"));
        s.setCiudad(rs.getString("ciudad"));
        s.setActiva(rs.getBoolean("activa"));
        return s;
    }
}