package dao;

import modelo.FotoInspeccion;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class FotoInspeccionDaoImpl implements IFotoInspeccionDao {

    @Override
    public boolean insertar(FotoInspeccion foto) {
        String sql = "{CALL sp_insertar_foto_inspeccion(?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, foto.getIdRegistro());
            cs.setString(2, foto.getFotoUrl());
            cs.setString(3, foto.getObservacionDano());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(FotoInspeccion foto) {
        String sql = "{CALL sp_modificar_foto_inspeccion(?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, foto.getIdFoto());
            cs.setInt(2, foto.getIdRegistro());
            cs.setString(3, foto.getFotoUrl());
            cs.setString(4, foto.getObservacionDano());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idFoto) {
        String sql = "{CALL sp_eliminar_foto_inspeccion(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idFoto);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public FotoInspeccion buscarPorId(int idFoto) {
        String sql = "{CALL sp_buscar_foto_inspeccion_por_id(?)}";
        FotoInspeccion foto = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idFoto);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    foto = mapearFotoInspeccion(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return foto;
    }

    @Override
    public List<FotoInspeccion> listarTodos() {
        String sql = "{CALL sp_listar_fotos_inspecciones()}";
        List<FotoInspeccion> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearFotoInspeccion(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private FotoInspeccion mapearFotoInspeccion(ResultSet rs) throws SQLException {
        FotoInspeccion f = new FotoInspeccion();
        f.setIdFoto(rs.getInt("id_foto"));
        f.setIdRegistro(rs.getInt("id_registro"));
        f.setFotoUrl(rs.getString("foto_url"));
        f.setObservacionDano(rs.getString("observacion_dano"));
        return f;
    }
}