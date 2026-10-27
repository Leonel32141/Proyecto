package servlets;

import dao.UsuarioDaoImpl;
import modelo.Usuario;
import com.google.gson.Gson;
import com.google.gson.JsonObject;

import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import java.util.List;

@WebServlet("/api/auth/login")
public class LoginServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json; charset=UTF-8");

        // Leemos el JSON que manda el fetch de login.js
        StringBuilder sb = new StringBuilder();
        try (BufferedReader reader = request.getReader()) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                sb.append(linea);
            }
        }

        Gson gson = new Gson();
        JsonObject jsonRequest = gson.fromJson(sb.toString(), JsonObject.class);

        if (jsonRequest == null || !jsonRequest.has("email") || !jsonRequest.has("password")) {
            response.setStatus(HttpServletResponse.SC_BAD_REQUEST);
            response.getWriter().write("{\"status\":\"error\", \"mensaje\":\"Faltan credenciales\"}");
            return;
        }

        String email = jsonRequest.get("email").getAsString();
        String passwordPlana = jsonRequest.get("password").getAsString();

        // Hasheamos la contraseña ingresada para compararla con la de la BD
        String passwordIngresadaHash = hashPassword(passwordPlana);

        UsuarioDaoImpl dao = new UsuarioDaoImpl();
        List<Usuario> usuarios = dao.listarTodos();
        Usuario usuarioEncontrado = null;

        for (Usuario u : usuarios) {
            if (u.getEmail() != null && u.getEmail().equals(email)) {
                if (u.getPasswordHash() != null && u.getPasswordHash().equals(passwordIngresadaHash)) {
                    usuarioEncontrado = u;
                    break;
                }
            }
        }

        if (usuarioEncontrado != null) {
            response.setStatus(HttpServletResponse.SC_OK);
            
            // ⭐ Armamos el JSON manualmente para evitar conflictos de reflexión con fechas ⭐
            JsonObject respuestaJson = new JsonObject();
            respuestaJson.addProperty("status", "success");
            respuestaJson.addProperty("nombre", usuarioEncontrado.getNombre());
            respuestaJson.addProperty("apellido", usuarioEncontrado.getApellido() != null ? usuarioEncontrado.getApellido() : "");
            
            if (usuarioEncontrado.getFechaNacimiento() != null) {
                respuestaJson.addProperty("fechaNacimiento", usuarioEncontrado.getFechaNacimiento().toString());
            }
            
            respuestaJson.addProperty("email", usuarioEncontrado.getEmail());

            response.getWriter().write(gson.toJson(respuestaJson));
        } else {
            response.setStatus(HttpServletResponse.SC_UNAUTHORIZED);
            response.getWriter().write("{\"status\":\"error\", \"mensaje\":\"Correo o contraseña incorrectos.\"}");
        }
    }

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