package config;
import java.io.IOException;
import java.sql.Connection;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

@SuppressWarnings("serial")
@WebServlet("/testdb")
public class TestDB extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws IOException {
	
	    resp.setContentType("text/html;charset=UTF-8");

	    Connection conn = config.Conexion.getConnection();
	    if (conn != null) {
	        resp.getWriter().println("¡Conexión con la DB exitosa!");
	    } else {
	        resp.getWriter().println("No se pudo conectar con la DB.");
	    }
	
}
}