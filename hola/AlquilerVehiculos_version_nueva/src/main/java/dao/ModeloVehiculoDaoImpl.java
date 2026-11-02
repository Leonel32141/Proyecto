package dao;

import modelo.ModeloVehiculo;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ModeloVehiculoDaoImpl implements IModeloVehiculoDao {

    @Override
    public boolean insertar(ModeloVehiculo mv) {
        String sql = "{CALL sp_insertar_modelo(?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, mv.getIdMarca());
            cs.setString(2, mv.getNombreModelo());
            cs.setInt(3, mv.getAnio());
            cs.setString(4, mv.getImagen());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(ModeloVehiculo mv) {
        String sql = "{CALL sp_modificar_modelo(?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, mv.getIdModelo());
            cs.setInt(2, mv.getIdMarca());
            cs.setString(3, mv.getNombreModelo());
            cs.setInt(4, mv.getAnio());
            cs.setString(5, mv.getImagen());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idModelo) {
        String sql = "{CALL sp_eliminar_modelo(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idModelo);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public ModeloVehiculo buscarPorId(int idModelo) {
        String sql = "{CALL sp_buscar_modelo_por_id(?)}";
        ModeloVehiculo mv = null;
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idModelo);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    mv = mapearModeloVehiculo(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return mv;
    }

    @Override
    public List<ModeloVehiculo> listarTodos() {
        String sql = "{CALL sp_listar_modelos()}";
        List<ModeloVehiculo> lista = new ArrayList<>();
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearModeloVehiculo(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    private ModeloVehiculo mapearModeloVehiculo(ResultSet rs) throws SQLException {
        ModeloVehiculo mv = new ModeloVehiculo();
        mv.setIdModelo(rs.getInt("id_modelo"));
        mv.setIdMarca(rs.getInt("id_marca"));
        mv.setNombreModelo(rs.getString("nombre_modelo"));
        mv.setAnio(rs.getInt("anio"));
        mv.setImagen(rs.getString("imagen"));
        return mv;
    }
}