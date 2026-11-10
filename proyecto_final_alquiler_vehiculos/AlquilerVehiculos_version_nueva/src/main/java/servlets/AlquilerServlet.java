package servlets;

import service.AlquilerService;
import modelo.Alquiler;
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
import java.util.List;

@WebServlet("/api/alquileres")
public class AlquilerServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Delegamos toda la lógica de negocio en el Servicio
    private final AlquilerService alquilerService = new AlquilerService()
    
    ;
    private final Gson gson = new GsonBuilder().create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        
        String idClienteActivoParam = req.getParameter("idClienteActivo");
        if (idClienteActivoParam != null && !idClienteActivoParam.isEmpty()) {
            try {
                int idCliente = Integer.parseInt(idClienteActivoParam);
                boolean tieneActivo = alquilerService.clienteTieneAlquilerActivo(idCliente);
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
                Alquiler alquiler = alquilerService.buscarPorId(id);
                
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
                List<Alquiler> lista = alquilerService.listarTodos();
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
            
            // Validación de alquiler activo a través del Service
            if (alquilerService.clienteTieneAlquilerActivo(alquiler.getIdCliente())) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Ya tienes un alquiler activo y vigente. Debes finalizarlo antes de solicitar otro vehículo.\"}");
                return;
            }
            
            // Asignación de vehículo físico real mediante lógica encapsulada en el Service
            int idVehiculoFisicoReal = alquilerService.asignarVehiculoDisponible(alquiler);
            
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
            
            boolean insertado = alquilerService.insertar(alquiler);

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
            
            Alquiler existente = alquilerService.buscarPorId(alquiler.getIdAlquiler());
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Alquiler no encontrado\"}");
                return;
            }
            
            boolean actualizado = alquilerService.modificar(alquiler);
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
            Alquiler existente = alquilerService.buscarPorId(id);
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Alquiler no encontrado\"}");
                return;
            }

            boolean eliminado = alquilerService.eliminar(id);
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