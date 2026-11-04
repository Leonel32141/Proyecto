package dao;

import modelo.Rol;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class RolDaoImpl implements IRolDao {

    @Override
    public boolean insertar(Rol rol) {
        String sql = "{CALL sp_insertar_rol(?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setString(1, rol.getNombreRol());
            cs.setString(2, rol.getDescripcion());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Rol rol) {
        String sql = "{CALL sp_modificar_rol(?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, rol.getIdRol());
            cs.setString(2, rol.getNombreRol());
            cs.setString(3, rol.getDescripcion());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idRol) {
        String sql = "{CALL sp_eliminar_rol(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idRol);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Rol buscarPorId(int idRol) {
        String sql = "{CALL sp_buscar_rol_por_id(?)}";
        Rol rol = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idRol);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    rol = mapearRol(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return rol;
    }

    @Override
    public List<Rol> listarTodos() {
        String sql = "{CALL sp_listar_roles()}";
        List<Rol> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearRol(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private Rol mapearRol(ResultSet rs) throws SQLException {
        Rol r = new Rol();
        r.setIdRol(rs.getInt("id_rol"));
        r.setNombreRol(rs.getString("nombre_rol"));
        r.setDescripcion(rs.getString("descripcion"));
        return r;
    }
}