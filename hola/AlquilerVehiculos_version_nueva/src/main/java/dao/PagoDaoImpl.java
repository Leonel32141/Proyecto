package dao;

import modelo.Pago;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class PagoDaoImpl implements IPagoDao {

    @Override
    public boolean insertar(Pago pago) {
        String sql = "{CALL sp_insertar_pago(?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, pago.getIdAlquiler());
            cs.setInt(2, pago.getIdMetodoPago());
            cs.setBigDecimal(3, pago.getMonto());
            cs.setTimestamp(4, pago.getFechaPago() != null ? Timestamp.valueOf(pago.getFechaPago()) : null);
            cs.setString(5, pago.getEstadoPago());
            cs.setString(6, pago.getNumeroComprobante());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Pago pago) {
        String sql = "{CALL sp_modificar_pago(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, pago.getIdPago());
            cs.setInt(2, pago.getIdAlquiler());
            cs.setInt(3, pago.getIdMetodoPago());
            cs.setBigDecimal(4, pago.getMonto());
            cs.setTimestamp(5, pago.getFechaPago() != null ? Timestamp.valueOf(pago.getFechaPago()) : null);
            cs.setString(6, pago.getEstadoPago());
            cs.setString(7, pago.getNumeroComprobante());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idPago) {
        String sql = "{CALL sp_eliminar_pago(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idPago);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Pago buscarPorId(int idPago) {
        String sql = "{CALL sp_buscar_pago_por_id(?)}";
        Pago pago = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idPago);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    pago = mapearPago(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return pago;
    }

    @Override
    public List<Pago> listarTodos() {
        String sql = "{CALL sp_listar_pagos()}";
        List<Pago> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearPago(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Pago mapearPago(ResultSet rs) throws SQLException {
        Pago p = new Pago();
        p.setIdPago(rs.getInt("id_pago"));
        p.setIdAlquiler(rs.getInt("id_alquiler"));
        p.setIdMetodoPago(rs.getInt("id_metodo_pago"));
        p.setMonto(rs.getBigDecimal("monto"));
        
        Timestamp ts = rs.getTimestamp("fecha_pago");
        if (ts != null) {
            p.setFechaPago(ts.toLocalDateTime());
        }
        
        p.setEstadoPago(rs.getString("estado_pago"));
        p.setNumeroComprobante(rs.getString("numero_comprobante"));
        return p;
    }
}