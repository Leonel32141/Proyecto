package servlets;

import factory.CheckinCheckoutFactory;
import dao.ICheckinCheckoutDao;
import modelo.CheckinCheckout;
import com.google.gson.Gson;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

@WebServlet("/api/checkins")
public class CheckinCheckoutServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    
    private final ICheckinCheckoutDao dao = CheckinCheckoutFactory.crearCheckinCheckoutDao();
    private final Gson gson = new Gson();

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        String idParam = req.getParameter("id");
        
        if (idParam != null && !idParam.isEmpty()) {
            try {
                int id = Integer.parseInt(idParam);
                CheckinCheckout ch = dao.buscarPorId(id);
                
                if (ch != null) {
                    resp.setStatus(HttpServletResponse.SC_OK);
                    resp.getWriter().write(gson.toJson(ch));
                } else {
                    resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                    resp.getWriter().write("{\"error\":\"Registro de checkin/checkout no encontrado\"}");
                }
            } catch (NumberFormatException e) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"El parámetro id debe ser numérico\"}");
            }
        } else {
            List<CheckinCheckout> lista = dao.listarTodos();
            resp.setStatus(HttpServletResponse.SC_OK);
            resp.getWriter().write(gson.toJson(lista));
        }
    }

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            CheckinCheckout ch = gson.fromJson(json, CheckinCheckout.class);
            
            if (ch == null) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Datos inválidos\"}");
                return;
            }
            
            boolean insertado = dao.insertar(ch);

            if (insertado) {
                resp.setStatus(HttpServletResponse.SC_CREATED);
                resp.getWriter().write("{\"mensaje\":\"Checkin/Checkout creado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo insertar el registro\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
            resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            resp.getWriter().write("{\"error\":\"JSON inválido o error en los datos\"}");
        }
    }

    @Override
    protected void doPut(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        prepararRespuesta(resp);
        try {
            String json = leerBody(req);
            CheckinCheckout ch = gson.fromJson(json, CheckinCheckout.class);
            
            if (ch == null || ch.getIdRegistro() <= 0) {
                resp.setStatus(HttpServletResponse.SC_BAD_REQUEST);
                resp.getWriter().write("{\"error\":\"Debe indicar un idRegistro válido\"}");
                return;
            }
            
            CheckinCheckout existente = dao.buscarPorId(ch.getIdRegistro());
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Registro no encontrado\"}");
                return;
            }
            
            boolean actualizado = dao.modificar(ch);
            if (actualizado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Checkin/Checkout actualizado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo actualizar el registro\"}");
            }
        } catch (Exception e) {
            e.printStackTrace();
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
            resp.getWriter().write("{\"error\":\"Debe indicar el id del registro\"}");
            return;
        }
        try {
            int id = Integer.parseInt(idParam);
            CheckinCheckout existente = dao.buscarPorId(id);
            if (existente == null) {
                resp.setStatus(HttpServletResponse.SC_NOT_FOUND);
                resp.getWriter().write("{\"error\":\"Registro no encontrado\"}");
                return;
            }

            boolean eliminado = dao.eliminar(id);
            if (eliminado) {
                resp.setStatus(HttpServletResponse.SC_OK);
                resp.getWriter().write("{\"mensaje\":\"Checkin/Checkout eliminado exitosamente\"}");
            } else {
                resp.setStatus(HttpServletResponse.SC_INTERNAL_SERVER_ERROR);
                resp.getWriter().write("{\"error\":\"No se pudo eliminar el registro\"}");
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