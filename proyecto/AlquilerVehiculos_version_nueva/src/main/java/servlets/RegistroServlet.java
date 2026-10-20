package servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.nio.charset.StandardCharsets;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import dao.UsuarioDaoImpl;
import modelo.Rol;
import modelo.Usuario;

@WebServlet("/registro")
public class RegistroServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        request.setCharacterEncoding("UTF-8");
        response.setContentType("application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();

        String nombre = request.getParameter("nombre");
        String apellido = request.getParameter("apellido");
        String dni = request.getParameter("dni");
        String cuil = request.getParameter("cuil");
        String telefono = request.getParameter("telefono");
        String email = request.getParameter("email");
        String passwordPlana = request.getParameter("password");

        // Hasheamos la contraseña con SHA-256 antes de guardarla
        String passwordSegura = hashPassword(passwordPlana);

        Usuario usuario = new Usuario();
        usuario.setNombre(nombre);
        usuario.setApellido(apellido);
        usuario.setDni(dni);
        usuario.setCuil(cuil);
        usuario.setTelefono(telefono);
        usuario.setEmail(email);
        usuario.setPasswordHash(passwordSegura); // Guardamos el hash, no la plana

        Rol rolCliente = new Rol();
        rolCliente.setIdRol(3); 
        usuario.setRol(rolCliente);

        UsuarioDaoImpl usuarioDao = new UsuarioDaoImpl();
        boolean registrado = usuarioDao.insertar(usuario);

        if (registrado) {
            out.write("{\"status\": \"success\", \"mensaje\": \"¡Usuario registrado correctamente!\"}");
        } else {
            out.write("{\"status\": \"error\", \"mensaje\": \"No se pudo registrar el usuario en la base de datos.\"}");
        }
    }

    // Método auxiliar para hashear contraseñas con SHA-256
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