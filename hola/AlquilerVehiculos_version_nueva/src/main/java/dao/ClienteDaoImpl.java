package dao;

import modelo.Cliente;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClienteDaoImpl implements IClienteDao {

    @Override
    public boolean insertar(Cliente cliente) {
        // Ahora pasamos 7 parámetros, incluyendo el idCliente (que coincide con id_usuario)
        String sql = "{CALL sp_insertar_cliente(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, cliente.getIdCliente());
            cs.setString(2, cliente.getDni());
            cs.setString(3, cliente.getLicenciaConducir());
            cs.setString(4, cliente.getDomicilio());
            cs.setString(5, cliente.getFotoIdentificacionUrl());
            cs.setDate(6, cliente.getFechaNacimiento() != null ? Date.valueOf(cliente.getFechaNacimiento()) : null);
            cs.setBoolean(7, cliente.isEsAptoLujo());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Cliente cliente) {
        String sql = "{CALL sp_modificar_cliente(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, cliente.getIdCliente());
            cs.setString(2, cliente.getDni());
            cs.setString(3, cliente.getLicenciaConducir());
            cs.setString(4, cliente.getDomicilio());
            cs.setString(5, cliente.getFotoIdentificacionUrl());
            cs.setDate(6, cliente.getFechaNacimiento() != null ? Date.valueOf(cliente.getFechaNacimiento()) : null);
            cs.setBoolean(7, cliente.isEsAptoLujo());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idCliente) {
        String sql = "{CALL sp_eliminar_cliente(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idCliente);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Cliente buscarPorId(int idCliente) {
        String sql = "{CALL sp_buscar_cliente_por_id(?)}";
        Cliente cliente = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idCliente);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    cliente = mapearCliente(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return cliente;
    }

    @Override
    public List<Cliente> listarTodos() {
        String sql = "{CALL sp_listar_clientes()}";
        List<Cliente> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearCliente(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Cliente mapearCliente(ResultSet rs) throws SQLException {
        Cliente c = new Cliente();
        c.setIdCliente(rs.getInt("id_cliente"));
        c.setDni(rs.getString("dni"));
        c.setLicenciaConducir(rs.getString("licencia_conducir"));
        c.setDomicilio(rs.getString("domicilio"));
        c.setFotoIdentificacionUrl(rs.getString("foto_identificacion_url"));
        
        Date sqlDate = rs.getDate("fecha_nacimiento");
        if (sqlDate != null) {
            c.setFechaNacimiento(sqlDate.toLocalDate());
        }
        
        c.setEsAptoLujo(rs.getBoolean("es_apto_lujo"));
        return c;
    }
}