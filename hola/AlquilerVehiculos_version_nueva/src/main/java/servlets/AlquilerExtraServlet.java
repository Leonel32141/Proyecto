package servlets;

import dao.AlquilerExtraDaoImpl;
import dao.IAlquilerExtraDao;
import modelo.AlquilerExtra;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/alquileres-extras")
public class AlquilerExtraServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final IAlquilerExtraDao dao = new AlquilerExtraDaoImpl();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        String idAlquilerParam = req.getParameter("idAlquiler");
        String idExtraParam = req.getParameter("idExtra");
        
        // Si mandan ambos parámetros, busca por la clave compuesta
        if (idAlquilerParam != null && !idAlquilerParam.isEmpty() && idExtraParam != null && !idExtraParam.isEmpty()) {
            try {
                int idAlquiler = Integer.parseInt(idAlquilerParam);
                int idExtra = Integer.parseInt(idExtraParam);
                
                AlquilerExtra extra = dao.buscarPorId(idAlquiler, idExtra);
                
                if (extra != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(extra));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\":\"Relación Alquiler-Extra no encontrada\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Los parámetros de ID deben ser numéricos\"}");
            }
        } else {
            // Si no, lista todos los registros
            List<AlquilerExtra> lista = dao.listarTodos();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            AlquilerExtra extra = gson.fromJson(json, AlquilerExtra.class);
            
            if (extra == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Datos inválidos\"}");
                return;
            }
            
            boolean insertado = dao.insertar(extra);

            if (insertado) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"mensaje\":\"AlquilerExtra creado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo insertar el registro\"}");
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
            AlquilerExtra extra = gson.fromJson(json, AlquilerExtra.class);
            
            if (extra == null || extra.getIdAlquiler() <= 0 || extra.getIdExtra() <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Debe indicar idAlquiler e idExtra válidos\"}");
                return;
            }
            
            AlquilerExtra existente = dao.buscarPorId(extra.getIdAlquiler(), extra.getIdExtra());
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Registro no encontrado para modificar\"}");
                return;
            }
            
            boolean actualizado = dao.modificar(extra);
            if (actualizado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"AlquilerExtra actualizado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo actualizar el registro\"}");
            }
        } catch (Exception e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"JSON inválido\"}");
        }
    }

    @Override
    protected void doDelete(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        String idAlquilerParam = req.getParameter("idAlquiler");
        String idExtraParam = req.getParameter("idExtra");
        
        if (idAlquilerParam == null || idAlquilerParam.isEmpty() || idExtraParam == null || idExtraParam.isEmpty()) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Debe indicar idAlquiler e idExtra como parámetros\"}");
            return;
        }
        
        try {
            int idAlquiler = Integer.parseInt(idAlquilerParam);
            int idExtra = Integer.parseInt(idExtraParam);
            
            AlquilerExtra existente = dao.buscarPorId(idAlquiler, idExtra);
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Registro no encontrado\"}");
                return;
            }

            boolean eliminado = dao.eliminar(idAlquiler, idExtra);
            if (eliminado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"AlquilerExtra eliminado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo eliminar el registro\"}");
            }
        } catch (NumberFormatException e) {
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"Los parámetros de ID deben ser numéricos\"}");
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