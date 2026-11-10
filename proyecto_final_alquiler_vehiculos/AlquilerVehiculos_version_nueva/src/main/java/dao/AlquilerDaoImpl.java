package dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

import modelo.Alquiler;

public class AlquilerDaoImpl implements IAlquilerDao {

    @Override
    public boolean insertar(Alquiler alquiler) {
        String sql = "{CALL sp_insertar_alquiler(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = config.Conexion.getInstancia().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, alquiler.getIdCliente());
            cs.setInt(2, alquiler.getIdVehiculo());
            cs.setInt(3, alquiler.getIdSucursalRetiro());
            cs.setInt(4, alquiler.getIdSucursalDevolucion());
            
            // Fecha Reserva con parseo seguro formato yyyy-MM-dd HH:mm:ss
            cs.setTimestamp(5, parsearFechaSegura(alquiler.getFechaReserva()));

            // Fecha Inicio Pautada
            cs.setTimestamp(6, parsearFechaSegura(alquiler.getFechaInicioPautada()));

            // Fecha Fin Pautada
            cs.setTimestamp(7, parsearFechaSegura(alquiler.getFechaFinPautada()));

            cs.setBoolean(8, alquiler.isEsOneway());
            cs.setBigDecimal(9, alquiler.getMontoTotal());
            cs.setBigDecimal(10, alquiler.getMontoSena());
            cs.setString(11, alquiler.getEstadoReserva());

            int filasAfectadas = cs.executeUpdate();

            if (filasAfectadas > 0) {
                try (Statement stmt = con.createStatement();
                     ResultSet rs = stmt.executeQuery("SELECT LAST_INSERT_ID()")) {
                    if (rs.next()) {
                        alquiler.setIdAlquiler(rs.getInt(1));
                    }
                }
                return true;
            }
            return false;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean modificar(Alquiler alquiler) {
        String sql = "{CALL sp_modificar_alquiler(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)}";
        try (Connection con = config.Conexion.getInstancia().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, alquiler.getIdAlquiler());
            cs.setInt(2, alquiler.getIdCliente());
            cs.setInt(3, alquiler.getIdVehiculo());
            cs.setInt(4, alquiler.getIdSucursalRetiro());
            cs.setInt(5, alquiler.getIdSucursalDevolucion());
            
            cs.setTimestamp(6, parsearFechaSegura(alquiler.getFechaInicioPautada()));
            cs.setTimestamp(7, parsearFechaSegura(alquiler.getFechaFinPautada()));

            cs.setBoolean(8, alquiler.isEsOneway());
            cs.setBigDecimal(9, alquiler.getMontoTotal());
            cs.setBigDecimal(10, alquiler.getMontoSena());
            cs.setString(11, alquiler.getEstadoReserva());

            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public boolean eliminar(int idAlquiler) {
        String sql = "{CALL sp_eliminar_alquiler(?)}";
        try (Connection con = config.Conexion.getInstancia().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idAlquiler);
            return cs.executeUpdate() > 0;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }

    @Override
    public Alquiler buscarPorId(int idAlquiler) {
        String sql = "{CALL sp_buscar_alquiler_por_id(?)}";
        Alquiler alquiler = null;
        try (Connection con = config.Conexion.getInstancia().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            cs.setInt(1, idAlquiler);
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    alquiler = mapearAlquiler(rs);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return alquiler;
    }

    @Override
    public List<Alquiler> listarTodos() {
        String sql = "{CALL sp_listar_alquileres()}";
        List<Alquiler> lista = new ArrayList<>();
        try (Connection con = config.Conexion.getInstancia().getConnection();
             CallableStatement cs = con.prepareCall(sql);
             ResultSet rs = cs.executeQuery()) {
            while (rs.next()) {
                lista.add(mapearAlquiler(rs));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }

    @Override
    public List<Alquiler> listarPorCliente(int idCliente) {
        String sql = "{CALL sp_listar_alquileres_por_cliente(?)}";
        List<Alquiler> lista = new ArrayList<>();
        try (Connection con = config.Conexion.getInstancia().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, idCliente);
            
            try (ResultSet rs = cs.executeQuery()) {
                while (rs.next()) {
                    lista.add(mapearAlquiler(rs));
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return lista;
    }
    
    @Override
    public boolean tieneAlquilerActivo(int idCliente) {
        String sql = "{CALL sp_verificar_alquiler_activo(?)}";
        try (Connection con = config.Conexion.getInstancia().getConnection();
             CallableStatement cs = con.prepareCall(sql)) {
            
            cs.setInt(1, idCliente);
            
            try (ResultSet rs = cs.executeQuery()) {
                if (rs.next()) {
                    int total = rs.getInt("total");
                    return total > 0;
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return false;
    }

    @Override
    public boolean cancelarReserva(int idAlquiler) {
        Alquiler alquiler = buscarPorId(idAlquiler);
        if (alquiler != null) {
            alquiler.setEstadoReserva("CANCELADA");
            return modificar(alquiler);
        }
        return false;
    }

    private Alquiler mapearAlquiler(ResultSet rs) throws SQLException {
        Alquiler a = new Alquiler();
        a.setIdAlquiler(rs.getInt("id_alquiler"));
        a.setIdCliente(rs.getInt("id_cliente"));
        a.setIdVehiculo(rs.getInt("id_vehiculo"));
        a.setIdSucursalRetiro(rs.getInt("id_sucursal_retiro"));
        a.setIdSucursalDevolucion(rs.getInt("id_sucursal_devolucion"));
        
        Timestamp tsReserva = rs.getTimestamp("fecha_reserva");
        if (tsReserva != null) {
            a.setFechaReserva(tsReserva.toLocalDateTime().toString().replace("T", " "));
        }

        Timestamp tsInicio = rs.getTimestamp("fecha_inicio_pautada");
        if (tsInicio != null) {
            a.setFechaInicioPautada(tsInicio.toLocalDateTime().toString().replace("T", " "));
        }

        Timestamp tsFin = rs.getTimestamp("fecha_fin_pautada");
        if (tsFin != null) {
            a.setFechaFinPautada(tsFin.toLocalDateTime().toString().replace("T", " "));
        }

        a.setEsOneway(rs.getBoolean("es_oneway"));
        
        java.math.BigDecimal monto = rs.getBigDecimal("monto_total");
        a.setMontoTotal(monto != null ? monto : java.math.BigDecimal.ZERO);

        java.math.BigDecimal sena = rs.getBigDecimal("monto_sena");
        a.setMontoSena(sena != null ? sena : java.math.BigDecimal.ZERO);

        a.setEstadoReserva(rs.getString("estado_reserva"));
        return a;
    }

    // Método auxiliar privado súper robusto para formatear y parsear cualquier variante de fecha a Timestamp
    private Timestamp parsearFechaSegura(String fechaStr) {
        if (fechaStr == null || fechaStr.trim().isEmpty()) {
            return null;
        }
        try {
            // Limpiamos espacios y aseguramos formato yyyy-MM-dd HH:mm:ss o con 'T'
            String limpio = fechaStr.trim().replace("T", " ");
            
            // Si viene solo como fecha "yyyy-MM-dd", le agregamos la hora por defecto
            if (limpio.length() == 10) {
                limpio += " 00:00:00";
            }
            
            // Si le falta los segundos (ej: "2026-11-08 12:00")
            if (limpio.length() == 16) {
                limpio += ":00";
            }
            
            return Timestamp.valueOf(limpio);
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }
}