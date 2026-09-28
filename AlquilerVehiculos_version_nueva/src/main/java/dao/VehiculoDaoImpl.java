package dao;

import modelo.CategoriaVehiculo;
import modelo.Marca;
import modelo.ModeloVehiculo;
import modelo.Sucursal;
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
            cs.setInt(2, v.getModelo().getIdModelo());
            cs.setInt(3, v.getCategoria().getIdCategoria());
            cs.setInt(4, v.getSucursalActual().getIdSucursal());
            cs.setInt(5, v.getKilometrajeActual());
            cs.setString(6, v.getEstado());
            cs.setDate(7, Date.valueOf(v.getVencimientoSeguro()));
            cs.setDate(8, Date.valueOf(v.getVencimientoPatente()));

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean actualizar(Vehiculo v) {
        String sql = "{CALL sp_actualizar_vehiculo(?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, v.getIdVehiculo());
            cs.setString(2, v.getPatente());
            cs.setInt(3, v.getModelo().getIdModelo());
            cs.setInt(4, v.getCategoria().getIdCategoria());
            cs.setInt(5, v.getSucursalActual().getIdSucursal());
            cs.setInt(6, v.getKilometrajeActual());
            cs.setString(7, v.getEstado());
            cs.setDate(8, Date.valueOf(v.getVencimientoSeguro()));
            cs.setDate(9, Date.valueOf(v.getVencimientoPatente()));

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
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
        }
        return false;
    }

    @Override
    public Vehiculo obtenerPorId(int idVehiculo) {
        String sql = "{CALL sp_obtener_vehiculo_por_id(?)}";
        try (Connection con = ConexionBD.obtenerConexion();
             CallableStatement cs = con.prepareCall(sql)) {

            cs.setInt(1, idVehiculo);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    return mapearVehiculo(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public List<Vehiculo> obtenerTodos() {
        List<Vehiculo> lista = new ArrayList<>();
        String sql = "{CALL sp_obtener_todos_vehiculos()}";
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
        Marca marca = new Marca();
        marca.setIdMarca(rs.getInt("id_marca"));

        ModeloVehiculo modelo = new ModeloVehiculo();
        modelo.setIdModelo(rs.getInt("id_modelo"));
        modelo.setMarca(marca);

        CategoriaVehiculo cat = new CategoriaVehiculo();
        cat.setIdCategoria(rs.getInt("id_categoria"));
        cat.setNombreCategoria(rs.getString("nombre_categoria"));

        Sucursal suc = new Sucursal();
        suc.setIdSucursal(rs.getInt("id_sucursal_actual"));

        Vehiculo v = new Vehiculo();
        v.setIdVehiculo(rs.getInt("id_vehiculo"));
        v.setPatente(rs.getString("patente"));
        v.setModelo(modelo);
        v.setCategoria(cat);
        v.setSucursalActual(suc);
        v.setKilometrajeActual(rs.getInt("kilometraje_actual"));
        v.setEstado(rs.getString("estado"));

        Date seg = rs.getDate("vencimiento_seguro");
        if (seg != null) v.setVencimientoSeguro(seg.toLocalDate());

        Date pat = rs.getDate("vencimiento_patente");
        if (pat != null) v.setVencimientoPatente(pat.toLocalDate());

        return v;
    }
}