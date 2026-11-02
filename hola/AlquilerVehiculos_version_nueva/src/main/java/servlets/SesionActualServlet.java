package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;

@WebServlet("/sesion-actual")
public class SesionActualServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        // Verificamos si existe una sesión activa sin crear una nueva
        HttpSession session = request.getSession(false);
        
        if (session != null && session.getAttribute("usuarioLogueado") != null) {
            Usuario usuario = (Usuario) session.getAttribute("usuarioLogueado");
            // Devolvemos los datos del usuario logueado en formato JSON
            out.write("{\"logueado\": true, \"nombre\": \"" + usuario.getNombre() + "\", \"apellido\": \"" + usuario.getApellido() + "\"}");
        } else {
            out.write("{\"logueado\": false}");
        }
    }
}