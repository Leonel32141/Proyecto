package servlets;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.servlet.http.HttpSession;
import dao.UsuarioDaoImpl;
import modelo.Usuario;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String email = request.getParameter("email");
        String passwordPlana = request.getParameter("password");

        // Hasheamos la contraseña ingresada para compararla con la de la BD
        String passwordIngresadaHash = hashPassword(passwordPlana);

        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        List<Usuario> usuarios = dao.obtenerTodos();
        Usuario usuarioEncontrado = null;

        for (Usuario u : usuarios) {
            if (u.getEmail() != null && u.getEmail().equals(email) && 
                u.getPasswordHash() != null && u.getPasswordHash().equals(passwordIngresadaHash)) {
                usuarioEncontrado = u;
                break;
            }
        }

        if (usuarioEncontrado != null) {
            // Guardamos el usuario en la sesión
            HttpSession session = request.getSession();
            session.setAttribute("usuarioLogueado", usuarioEncontrado);

            // Respondemos con éxito en JSON indicando el nombre
            out.write("{\"status\": \"success\", \"mensaje\": \"¡Login exitoso!\", \"nombre\": \"" + usuarioEncontrado.getNombre() + "\"}");
        } else {
            // Respondemos con error en JSON
            out.write("{\"status\": \"error\", \"mensaje\": \"Email o contraseña incorrectos.\"}");
        }
    }

    // Método auxiliar para hashear con SHA-256 igual que en el registro
    private String hashPassword(String password) {
        try {
            MessageDigest digest = MessageDigest.getInstance("SHA-256");
            byte[] encodedhash = digest.digest(password.getBytes(StandardCharsets.UTF_8));
            StringBuilder hexString = new StringBuilder();
            for (byte b : encodedhash) {
                String hex = Integer.toHexString(0xff & b);
                if (hex.length() == 1) hexString.append('0');
                hexString.append(hex);
            }
            return hexString.toString();
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException("Error al hashear la contraseña", e);
        }
    }
}