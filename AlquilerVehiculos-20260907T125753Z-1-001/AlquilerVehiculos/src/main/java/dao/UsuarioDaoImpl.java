package dao;

import modelo.Rol;
import modelo.Usuario;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UsuarioDaoImpl implements IUsuarioDao {

    @Override
    public boolean insertar(Usuario u) {
        String sql = "{CALL sp_insertar_usuario(?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, u.getRol().getIdRol());
            cs.setString(2, u.getEmail());
            cs.setString(3, u.getPasswordHash());
            cs.setString(4, u.getNombre());
            cs.setString(5, u.getApellido());
            cs.setString(6, u.getCuil());
            cs.setString(7, u.getTelefono());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Usuario u) {
        String sql = "{CALL sp_actualizar_usuario(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, u.getIdUsuario());
            cs.setInt(2, u.getRol().getIdRol());
            cs.setString(3, u.getEmail());
            cs.setString(4, u.getNombre());
            cs.setString(5, u.getApellido());
            cs.setString(6, u.getCuil());
            cs.setString(7, u.getTelefono());
            cs.setInt(8, u.getIntentosFallidos());
            cs.setBoolean(9, u.isCuentaBloqueada());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        }
        return false;
    }

    @Override
    public Usuario obtenerPorId(int idUsuario) {
        String sql = "{CALL sp_obtener_usuario_por_id(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idUsuario);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapearUsuario(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Usuario> obtenerTodos() {
        List<Usuario> lista = new ArrayList<>();
        String sql = "{CALL sp_obtener_todos_usuarios()}";
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
        Rol rol = new Rol(rs.getInt("id_rol"), rs.getString("nombre_rol"), rs.getString("desc_rol"));
        Usuario u = new Usuario();
        u.setIdUsuario(rs.getInt("id_usuario"));
        u.setRol(rol);
        u.setEmail(rs.getString("email"));
        u.setPasswordHash(rs.getString("password_hash"));
        u.setNombre(rs.getString("nombre"));
        u.setApellido(rs.getString("apellido"));
        u.setCuil(rs.getString("cuil"));
        u.setTelefono(rs.getString("telefono"));
        u.setIntentosFallidos(rs.getInt("intentos_fallidos"));
        u.setCuentaBloqueada(rs.getBoolean("cuenta_bloqueada"));
        
        Timestamp ts = rs.getTimestamp("fecha_creacion");
        if (ts != null) {
            u.setFechaCreacion(ts.toLocalDateTime());
        }
        return u;
    }
}