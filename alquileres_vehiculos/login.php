<?php
session_start();
require_once 'conexion.php';

$mensaje = "";
$tipo_mensaje = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $email    = trim($_POST['email']);
    $password = trim($_POST['password']);

    if (!empty($email) && !empty($password)) {
        try {
            $sql = "SELECT u.id_usuario, u.nombre, u.apellido, u.password_hash, u.id_rol, r.nombre_rol 
                    FROM usuarios u 
                    INNER JOIN roles r ON u.id_rol = r.id_rol 
                    WHERE u.email = :email";
            
            $stmt = $conexion->prepare($sql);
            $stmt->execute([':email' => $email]);
            $usuario = $stmt->fetch();

            if ($usuario && password_verify($password, $usuario['password_hash'])) {
                $_SESSION['id_usuario'] = $usuario['id_usuario'];
                $_SESSION['nombre']     = $usuario['nombre'];
                $_SESSION['apellido']   = $usuario['apellido'];
                $_SESSION['email']      = $email;
                $_SESSION['id_rol']     = $usuario['id_rol'];
                $_SESSION['nombre_rol'] = $usuario['nombre_rol'];

                // REDIRECCIÓN INMEDIATA AL INDEX
                header("Location: index.php");
                exit();

            } else {

        
                $mensaje = "Correo electrónico o contraseña incorrectos.";
                $tipo_mensaje = "error";
            }

        } catch (PDOException $e) {
            $mensaje = "Error en el sistema: " . $e->getMessage();
            $tipo_mensaje = "error";
        }
    } else {
        $mensaje = "Por favor, completá todos los campos.";
        $tipo_mensaje = "error";
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Iniciar Sesión - Alquileres Vehículos</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }
        body {
            background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%);
            min-height: 100vh;
            display: flex;
            justify-content: center;
            align-items: center;
            padding: 20px;
        }
        .card-login {
            background-color: #ffffff;
            width: 100%;
            max-width: 420px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
            overflow: hidden;
        }
        .header-login {
            background-color: #0284c7; /* Un tono un poco distinto para diferenciarlo del registro */
            color: #ffffff;
            padding: 30px 20px;
            text-align: center;
        }
        .header-login h1 {
            font-size: 22px;
            font-weight: 700;
            margin-bottom: 5px;
        }
        .header-login p {
            font-size: 13px;
            opacity: 0.9;
        }
        .form-body {
            padding: 30px;
        }
        .grupo-input {
            margin-bottom: 20px;
        }
        label {
            display: block;
            font-size: 13px;
            font-weight: 600;
            color: #475569;
            margin-bottom: 6px;
        }
        input {
            width: 100%;
            padding: 11px 14px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            font-size: 14px;
            transition: border-color 0.2s, box-shadow 0.2s;
        }
        input:focus {
            outline: none;
            border-color: #0284c7;
            box-shadow: 0 0 0 3px rgba(2, 132, 199, 0.15);
        }
        .btn-submit {
            width: 100%;
            background-color: #0284c7;
            color: #ffffff;
            border: none;
            padding: 12px;
            border-radius: 6px;
            font-size: 15px;
            font-weight: 600;
            cursor: pointer;
            transition: background-color 0.2s;
        }
        .btn-submit:hover {
            background-color: #0369a1;
        }
        .mensaje {
            padding: 12px;
            border-radius: 6px;
            font-size: 14px;
            margin-bottom: 20px;
            text-align: center;
        }
        .exito {
            background-color: #dcfce7;
            color: #166534;
            border: 1px solid #bbf7d0;
        }
        .error {
            background-color: #fee2e2;
            color: #991b1b;
            border: 1px solid #fecaca;
        }
        .footer-links {
            margin-top: 25px;
            text-align: center;
            font-size: 14px;
            color: #64748b;
        }
        .footer-links a {
            color: #0284c7;
            text-decoration: none;
            font-weight: 600;
        }
        .footer-links a:hover {
            text-decoration: underline;
        }
    </style>
</head>
<body>

<div class="card-login">
    <div class="header-login">
        <h1>Iniciar Sesión</h1>
        <p>Ingresá a tu cuenta de Alquileres Vehículos</p>
    </div>

    <div class="form-body">
        <?php if (!empty($mensaje)): ?>
            <div class="mensaje <?php echo $tipo_mensaje; ?>">
                <?php echo htmlspecialchars($mensaje); ?>
            </div>
        <?php endif; ?>

        <form action="login.php" method="POST">
            <div class="grupo-input">
                <label for="email">Correo Electrónico</label>
                <input type="email" id="email" name="email" placeholder="tucorreo@email.com" required>
            </div>

            <div class="grupo-input">
                <label for="password">Contraseña</label>
                <input type="password" id="password" name="password" placeholder="••••••••" required>
            </div>

            <button type="submit" class="btn-submit">Ingresar</button>
        </form>

        <div class="footer-links">
            <p>¿Todavía no tenés cuenta? <a href="registro.php">Registrate acá</a></p>
        </div>
    </div>
</div>

</body>
</html>