package servlets;

import dao.TrasladoVehiculoDaoImpl; // Ajustá el nombre si tu DAO se llama distinto
import dao.ITrasladoVehiculoDao;
import modelo.TrasladoVehiculo;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/traslados")
public class TrasladoVehiculoServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final ITrasladoVehiculoDao dao = new TrasladoVehiculoDaoImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        String idParam = req.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                TrasladoVehiculo traslado = dao.buscarPorId(id);
                
                if (traslado != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(traslado));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\":\"Traslado no encontrado\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"El parámetro id debe ser numérico\"}");
            }
        } else {
            List<TrasladoVehiculo> lista = dao.listarTodos();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            TrasladoVehiculo traslado = gson.fromJson(json, TrasladoVehiculo.class);
            
            if (traslado == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Datos de traslado inválidos\"}");
                return;
            }
            
            boolean insertado = dao.insertar(traslado);

            if (insertado) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"mensaje\":\"Traslado creado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo insertar el traslado\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"JSON inválido\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            TrasladoVehiculo traslado = gson.fromJson(json, TrasladoVehiculo.class);
            
            // Si en tu modelo el getter del ID es getIdTraslado(), asegurate de ajustarlo si difiere:
            if (traslado == null || traslado.getIdTraslado() <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Debe indicar un idTraslado válido\"}");
                return;
            }
            
            TrasladoVehiculo existente = dao.buscarPorId(traslado.getIdTraslado());
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Traslado no encontrado\"}");
                return;
            }
            
            boolean actualizado = dao.modificar(traslado);
            if (actualizado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Traslado actualizado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo actualizar el traslado\"}");
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
            resp.getWriter().write("{\"error\":\"Debe indicar el id del traslado\"}");
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            TrasladoVehiculo existente = dao.buscarPorId(id);
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Traslado no encontrado\"}");
                return;
            }

            boolean eliminado = dao.eliminar(id);
            if (eliminado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Traslado eliminado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo eliminar el traslado\"}");
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