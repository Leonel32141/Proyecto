package servlets;

import dao.AlquilerDaoImpl;
import dao.IAlquilerDao;
import dao.VehiculoDaoImpl;
import dao.IVehiculoDao;
import modelo.Alquiler;
import modelo.Vehiculo;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonObject;
import com.google.gson.JsonParser;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;

@WebServlet("/api/alquileres")
public class AlquilerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final IAlquilerDao alquilerDao = new AlquilerDaoImpl();
    private final IVehiculoDao vehiculoDao = new VehiculoDaoImpl();
    
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        
        String idClienteActivoParam = req.getParameter("idClienteActivo");
        if (idClienteActivoParam != null && !idClienteActivoParam.isEmpty()) {
            try {
                int idCliente = Integer.parseInt(idClienteActivoParam);
                boolean tieneActivo = ((AlquilerDaoImpl) alquilerDao).tieneAlquilerActivo(idCliente);
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"tieneActivo\":" + tieneActivo + "}");
                return;
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Parámetro idClienteActivo inválido\"}");
                return;
            }
        }

        String idParam = req.getParameter("id");
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Alquiler alquiler = alquilerDao.buscarPorId(id);
                
                if (alquiler != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(alquiler));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\":\"Alquiler no encontrado\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"El parámetro id debe ser numérico\"}");
            }
        } else {
            try {
                List<Alquiler> lista = alquilerDao.listarTodos();
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write(gson.toJson(lista));
            } catch (Exception e) {
                e.printStackTrace();
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"Error al listar alquileres: " + e.getMessage() + "\"}");
            }
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            
            JsonObject jsonObj = JsonParser.parseString(json).getAsJsonObject();
            Alquiler alquiler = gson.fromJson(json, Alquiler.class);
            
            if (alquiler == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Datos de alquiler inválidos\"}");
                return;
            }
            
            BigDecimal montoExtraido = BigDecimal.ZERO;
            if (jsonObj.has("montoTotal") && !jsonObj.get("montoTotal").isJsonNull()) {
                montoExtraido = jsonObj.get("montoTotal").getAsBigDecimal();
            } else if (jsonObj.has("monto_total") && !jsonObj.get("monto_total").isJsonNull()) {
                montoExtraido = jsonObj.get("monto_total").getAsBigDecimal();
            } else if (jsonObj.has("totalFinal") && !jsonObj.get("totalFinal").isJsonNull()) {
                montoExtraido = jsonObj.get("totalFinal").getAsBigDecimal();
            }

            if (montoExtraido.compareTo(BigDecimal.ZERO) <= 0 && alquiler.getMontoTotal() != null) {
                montoExtraido = alquiler.getMontoTotal();
            }

            alquiler.setMontoTotal(montoExtraido);
            alquiler.setMontoSena(montoExtraido);

            if (alquiler.getIdCliente() <= 0) {
                alquiler.setIdCliente(1); 
            }
            
            if (((AlquilerDaoImpl) alquilerDao).tieneAlquilerActivo(alquiler.getIdCliente())) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Ya tienes un alquiler activo y vigente. Debes finalizarlo antes de solicitar otro vehículo.\"}");
                return;
            }
            
            int modelIdIngresado = alquiler.getIdVehiculo(); 
            List<Vehiculo> todosLosVehiculos = vehiculoDao.listarTodos();
            List<Alquiler> todosLosAlquileres = alquilerDao.listarTodos();
            
            int idVehiculoFisicoReal = -1;
            
            // Parseo seguro de fechas String a LocalDate para comparar disponibilidad
            LocalDate nuevoInicio = (alquiler.getFechaInicioPautada() != null && !alquiler.getFechaInicioPautada().isEmpty()) 
                    ? LocalDate.parse(alquiler.getFechaInicioPautada().substring(0, 10)) : null;
            LocalDate nuevoFin = (alquiler.getFechaFinPautada() != null && !alquiler.getFechaFinPautada().isEmpty()) 
                    ? LocalDate.parse(alquiler.getFechaFinPautada().substring(0, 10)) : null;

            for (Vehiculo v : todosLosVehiculos) {
                String estadoVehiculo = v.getEstado() != null ? v.getEstado().toUpperCase() : "DISPONIBLE";
                
                if (v.getIdModelo() == modelIdIngresado && "DISPONIBLE".equals(estadoVehiculo)) {
                    boolean estaOcupado = false;
                    
                    if (nuevoInicio != null && nuevoFin != null) {
                        for (Alquiler a : todosLosAlquileres) {
                            if (a.getIdVehiculo() == v.getIdVehiculo()) {
                                LocalDate inicioExistente = (a.getFechaInicioPautada() != null && !a.getFechaInicioPautada().isEmpty()) 
                                        ? LocalDate.parse(a.getFechaInicioPautada().substring(0, 10)) : null;
                                LocalDate finExistente = (a.getFechaFinPautada() != null && !a.getFechaFinPautada().isEmpty()) 
                                        ? LocalDate.parse(a.getFechaFinPautada().substring(0, 10)) : null;
                                
                                if (inicioExistente != null && finExistente != null) {
                                    if (!nuevoInicio.isAfter(finExistente) && !nuevoFin.isBefore(inicioExistente)) {
                                        estaOcupado = true;
                                        break;
                                    }
                                }
                            }
                        }
                    }
                    
                    if (!estaOcupado) {
                        idVehiculoFisicoReal = v.getIdVehiculo();
                        break; 
                    }
                }
            }
            
            if (idVehiculoFisicoReal <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"No hay unidades disponibles de este modelo (pueden estar en mantenimiento o reservadas).\"}");
                return;
            }
            
            alquiler.setIdVehiculo(idVehiculoFisicoReal);
            
            if (alquiler.getIdSucursalRetiro() <= 0) alquiler.setIdSucursalRetiro(1);
            if (alquiler.getIdSucursalDevolucion() <= 0) alquiler.setIdSucursalDevolucion(1);
            if (alquiler.getEstadoReserva() == null || alquiler.getEstadoReserva().isEmpty()) {
                alquiler.setEstadoReserva("RESERVADA");
            }
            
            boolean insertado = alquilerDao.insertar(alquiler);

            if (insertado) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"mensaje\":\"Alquiler creado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo insertar el alquiler en la base de datos\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Error en el servidor: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            Alquiler alquiler = gson.fromJson(json, Alquiler.class);
            
            if (alquiler == null || alquiler.getIdAlquiler() <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Debe indicar un idAlquiler válido\"}");
                return;
            }
            
            Alquiler existente = alquilerDao.buscarPorId(alquiler.getIdAlquiler());
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Alquiler no encontrado\"}");
                return;
            }
            
            boolean actualizado = alquilerDao.modificar(alquiler);
            if (actualizado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Alquiler actualizado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo actualizar el alquiler\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"JSON inválido\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        String idParam = req.getParameter("id");
        if (idParam == null || idParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Debe indicar el id del alquiler\"}");
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            Alquiler existente = alquilerDao.buscarPorId(id);
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Alquiler no encontrado\"}");
                return;
            }

            boolean eliminado = alquilerDao.eliminar(id);
            if (eliminado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Alquiler eliminado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo eliminar el alquiler\"}");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"El parámetro id debe ser numérico\"}");
        }
    }

    private String leerBody(HttpServletRequest req) throws IOException {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = req.getReader()) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea);
            }
        }
        return sb.toString();
    }

    private void prepararRespuesta(HttpServletResponse resp) {
        resp.setContentType("application/json");
        resp.setCharacterEncoding("UTF-8");
    }
}