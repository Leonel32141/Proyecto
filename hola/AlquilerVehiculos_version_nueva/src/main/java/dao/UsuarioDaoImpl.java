package dao;

import modelo.Usuario;

import java.sql.*;

import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public boolean insertar(Usuario u) {
        String sql = "{CALL sp_insertar_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, u.getIdRol());
            cs.setString(2, u.getEmail());
            cs.setString(3, u.getPasswordHash());
            cs.setString(4, u.getNombre());
            cs.setString(5, u.getApellido());
            cs.setString(6, u.getDni());
            cs.setString(7, u.getCuil());
            cs.setString(8, u.getTelefono());
            cs.setInt(9, u.getIntentosFallidos());
            cs.setBoolean(10, u.isCuentaBloqueada());
            cs.setTimestamp(11, u.getFechaCreacion() != null ? Timestamp.valueOf(u.getFechaCreacion()) : null);
            cs.setDate(12, u.getFechaNacimiento() != null ? Date.valueOf(u.getFechaNacimiento()) : null);

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Usuario u) {
        String sql = "{CALL sp_modificar_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, u.getIdUsuario());
            cs.setInt(2, u.getIdRol());
            cs.setString(3, u.getEmail());
            cs.setString(4, u.getPasswordHash());
            cs.setString(5, u.getNombre());
            cs.setString(6, u.getApellido());
            cs.setString(7, u.getDni());
            cs.setString(8, u.getCuil());
            cs.setString(9, u.getTelefono());
            cs.setInt(10, u.getIntentosFallidos());
            cs.setBoolean(11, u.isCuentaBloqueada());
            cs.setTimestamp(12, u.getFechaCreacion() != null ? Timestamp.valueOf(u.getFechaCreacion()) : null);
            cs.setDate(13, u.getFechaNacimiento() != null ? Date.valueOf(u.getFechaNacimiento()) : null);

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idUsuario) {
        String sql = "{CALL sp_eliminar_usuario(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idUsuario);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Usuario buscarPorId(int idUsuario) {
        String sql = "{CALL sp_buscar_usuario_por_id(?)}";
        Usuario u = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idUsuario);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    u = mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return u;
    }

    @Override
    public List<Usuario> listarTodos() {
        String sql = "{CALL sp_listar_usuarios()}";
        List<Usuario> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearUsuario(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Usuario mapearUsuario(ResultSet rs) throws SQLException {
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setIdRol(rs.getInt("id_rol"));
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setDni(rs.getString("dni"));
        u.setCuil(rs.getString("cuil"));
        u.setTelefono(rs.getString("telefono"));
        u.setIntentosFallidos(rs.getInt("intentos_fallidos"));
        u.setCuentaBloqueada(rs.getBoolean("cuenta_bloqueada"));
        
        Timestamp ts = rs.getTimestamp("fecha_creacion");
        if (ts != null) {
            u.setFechaCreacion(ts.toLocalDateTime());
        }

        Date sqlDate = rs.getDate("fecha_nacimiento");
        if (sqlDate != null) {
            u.setFechaNacimiento(sqlDate.toLocalDate());
        }

        return u;
    }
}