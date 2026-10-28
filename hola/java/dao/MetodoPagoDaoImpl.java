package dao;

import modelo.MetodoPago;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MetodoPagoDaoImpl implements IMetodoPagoDao {

    @Override
    public boolean insertar(MetodoPago mp) {
        String sql = "{CALL sp_insertar_metodo_pago(?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, mp.getNombreMetodo());
            cs.setBoolean(2, mp.isRequiereComprobante());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(MetodoPago mp) {
        String sql = "{CALL sp_modificar_metodo_pago(?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, mp.getIdMetodoPago());
            cs.setString(2, mp.getNombreMetodo());
            cs.setBoolean(3, mp.isRequiereComprobante());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idMetodoPago) {
        String sql = "{CALL sp_eliminar_metodo_pago(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idMetodoPago);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public MetodoPago buscarPorId(int idMetodoPago) {
        String sql = "{CALL sp_buscar_metodo_pago_por_id(?)}";
        MetodoPago mp = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idMetodoPago);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    mp = mapearMetodoPago(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mp;
    }

    @Override
    public List<MetodoPago> listarTodos() {
        String sql = "{CALL sp_listar_metodos_pagos()}";
        List<MetodoPago> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearMetodoPago(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private MetodoPago mapearMetodoPago(ResultSet rs) throws SQLException {
        MetodoPago mp = new MetodoPago();
        mp.setIdMetodoPago(rs.getInt("id_metodo_pago"));
        mp.setNombreMetodo(rs.getString("nombre_metodo"));
        mp.setRequiereComprobante(rs.getBoolean("requiere_comprobante"));
        return mp;
    }
}