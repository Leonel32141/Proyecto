package dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import modelo.AlquilerExtra;
import config.Conexion;

public class AlquilerExtraDaoImpl implements IAlquilerExtraDao {

    @Override
    public List<AlquilerExtra> obtenerTodos() {
        List<AlquilerExtra> lista = new ArrayList<>();
        String sql = "SELECT * FROM alquiler_extra";
        try (Connection con = Conexion.getConnection();
        	     Statement st = con.createStatement(); 
        	     ResultSet rs = st.executeQuery(sql)) {
            while (rs.next()) {
                AlquilerExtra obj = new AlquilerExtra();
                lista.add(obj);
            }
        } catch (SQLException e) { 
            e.printStackTrace(); 
        }
        return lista;
    }

    @Override
    public AlquilerExtra obtenerPorId(int id) {
        return null;
    }

    @Override
    public boolean registrar(AlquilerExtra extra) {
        return true;
    }

    @Override
    public boolean actualizar(AlquilerExtra extra) {
        return true;
    }

    @Override
    public boolean eliminar(int id) {
        return true;
    }
}