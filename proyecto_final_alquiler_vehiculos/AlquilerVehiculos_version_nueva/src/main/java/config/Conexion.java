package config;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class Conexion {
    
    // 1. Variable estática para guardar la única instancia de la clase
    private static Conexion instancia = null;
    private Connection conexion = null;
    
    // Datos de conexión
    private static final String URL = "jdbc:mysql://localhost:3306/bd_alquiler_vehiculos?useSSL=false&serverTimezone=UTC";
    private static final String USER = "root";
    private static final String PASS = "";

    // 2. Constructor privado (evita que se haga 'new Conexion()' desde afuera)
    private Conexion() {
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
    }

    // 3. Método estático sincronizado para obtener la única instancia (Singleton)
    public static synchronized Conexion getInstancia() {
        if (instancia == null) {
            instancia = new Conexion();
        }
        return instancia;
    }

    // 4. Método para obtener la conexión activa a la BD
    public Connection getConnection() throws SQLException {
        if (conexion == null || conexion.isClosed()) {
            conexion = DriverManager.getConnection(URL, USER, PASS);
        }
        return conexion;
    }
}