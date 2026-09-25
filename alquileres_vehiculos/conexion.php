<?php
// Configuración de parámetros de conexión
$host = "localhost";
$dbname = "bd_alquiler_vehiculos";
$usuario = "root";
$password = ""; // Si tenés contraseña en tu phpMyAdmin / MySQL, ponela acá

try {
    // Creación de la conexión PDO con codificación UTF-8
    $conexion = new PDO("mysql:host=$host;dbname=$dbname;charset=utf8mb4", $usuario, $password);
    
    // Configuración del modo de errores a excepciones para capturar fallas
    $conexion->setAttribute(PDO::ATTR_ERRMODE, PDO::ERRMODE_EXCEPTION);
    
    // Configuración del modo de obtención predeterminado (arrays asociativos)
    $conexion->setAttribute(PDO::ATTR_DEFAULT_FETCH_MODE, PDO::FETCH_ASSOC);

} catch (PDOException $e) {
    // Si la conexión falla, interrumpe la ejecución y muestra el mensaje
    die("Error al conectar con la base de datos: " . $e->getMessage());
}
?>