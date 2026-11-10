package servlets;

import factory.ClienteFactory; 
import dao.IClienteDao;
import modelo.Cliente;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/clientes")
public class ClienteServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    // Corregido: declarada correctamente como 'dao'
    private final IClienteDao dao = ClienteFactory.crearClienteDao();
    
    private final Gson gson = new GsonBuilder()
            .registerTypeAdapter(java.time.LocalDate.class, (com.google.gson.JsonSerializer<java.time.LocalDate>) (src, typeOfSrc, context) -> 
                new com.google.gson.JsonPrimitive(src.toString()))
            .registerTypeAdapter(java.time.LocalDate.class, (com.google.gson.JsonDeserializer<java.time.LocalDate>) (json, typeOfT, context) -> 
                java.time.LocalDate.parse(json.getAsString()))
            .create();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        String idParam = req.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                Cliente cliente = dao.buscarPorId(id);
                
                if (cliente != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(cliente));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\":\"Cliente no encontrado\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"El parámetro id debe ser numérico\"}");
            }
        } else {
            List<Cliente> lista = dao.listarTodos();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            Cliente cliente = gson.fromJson(json, Cliente.class);
            
            if (cliente == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Datos de cliente inválidos\"}");
                return;
            }
            
            Cliente existente = dao.buscarPorId(cliente.getIdCliente());
            boolean operacionExitosa = false;
            
            if (existente != null) {
                operacionExitosa = dao.modificar(cliente);
            } else {
                operacionExitosa = dao.insertar(cliente);
            }

            if (operacionExitosa) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"mensaje\":\"Cliente registrado o actualizado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo guardar el cliente en la base de datos\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"JSON inválido: " + e.getMessage() + "\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            Cliente cliente = gson.fromJson(json, Cliente.class);
            
            if (cliente == null || cliente.getIdCliente() <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Debe indicar un idCliente válido\"}");
                return;
            }
            
            Cliente existente = dao.buscarPorId(cliente.getIdCliente());
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Cliente no encontrado\"}");
                return;
            }
            
            boolean actualizado = dao.modificar(cliente);
            if (actualizado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Cliente actualizado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo actualizar el cliente\"}");
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
            resp.getWriter().write("{\"error\":\"Debe indicar el id del cliente\"}");
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            Cliente existente = dao.buscarPorId(id);
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Cliente no encontrado\"}");
                return;
            }

            boolean eliminado = dao.eliminar(id);
            if (eliminado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Cliente eliminado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo eliminar el cliente\"}");
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