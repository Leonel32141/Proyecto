<?php
require_once 'conexion.php';

$mensaje = "";
$tipo_mensaje = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nombre            = trim($_POST['nombre']);
    $apellido          = trim($_POST['apellido']);
    $email             = trim($_POST['email']);
    $password          = trim($_POST['password']);
    $cuil              = trim($_POST['cuil']);
    $telefono          = trim($_POST['telefono']);
    $dni               = trim($_POST['dni']);
    $domicilio         = trim($_POST['domicilio']);
    $fecha_nacimiento  = trim($_POST['fecha_nacimiento']);

    $cuil_limpio = str_replace('-', '', $cuil);

    if (!ctype_digit($dni) || strlen($dni) < 7 || strlen($dni) > 8) {
        $mensaje = "El DNI debe tener entre 7 y 8 números.";
        $tipo_mensaje = "error";
    } elseif (!ctype_digit($cuil_limpio) || strlen($cuil_limpio) !== 11) {
        $mensaje = "El CUIL debe contener exactamente 11 números.";
        $tipo_mensaje = "error";
    } elseif (!empty($telefono) && (!ctype_digit($telefono) || strlen($telefono) !== 10)) {
        $mensaje = "El teléfono debe contener exactamente 10 números.";
        $tipo_mensaje = "error";
    } elseif (empty($fecha_nacimiento)) {
        $mensaje = "Por favor, ingresá tu fecha de nacimiento.";
        $tipo_mensaje = "error";
    } else {
        // Cálculo de edad para la restricción de los 21 años
        $hoy = new DateTime();
        $nacimiento = new DateTime($fecha_nacimiento);
        $edad = $hoy->diff($nacimiento)->y;

        if ($edad < 21) {
            $mensaje = "Acceso denegado: Debes tener al menos 21 años para registrarte.";
            $tipo_mensaje = "error";
        } elseif (!empty($nombre) && !empty($apellido) && !empty($email) && !empty($password) && !empty($dni) && !empty($cuil_limpio)) {
            try {
                $conexion->beginTransaction();

                $password_hash = password_hash($password, PASSWORD_DEFAULT);
                $id_rol_cliente = 3;
                $es_apto_lujo = ($edad >= 25) ? 1 : 0;

                $sql_usuario = "INSERT INTO usuarios (id_rol, email, password_hash, nombre, apellido, cuil, telefono) 
                                VALUES (:id_rol, :email, :password_hash, :nombre, :apellido, :cuil, :telefono)";
                $stmt_u = $conexion->prepare($sql_usuario);
                $stmt_u->execute([
                    ':id_rol'         => $id_rol_cliente,
                    ':email'          => $email,
                    ':password_hash'  => $password_hash,
                    ':nombre'         => $nombre,
                    ':apellido'       => $apellido,
                    ':cuil'           => $cuil,
                    ':telefono'       => $telefono
                ]);

                $id_usuario = $conexion->lastInsertId();

                $sql_cliente = "INSERT INTO clientes (id_cliente, dni, domicilio, fecha_nacimiento, es_apto_lujo) 
                                VALUES (:id_cliente, :dni, :domicilio, :fecha_nacimiento, :es_apto_lujo)";
                $stmt_c = $conexion->prepare($sql_cliente);
                $stmt_c->execute([
                    ':id_cliente'       => $id_usuario,
                    ':dni'              => $dni,
                    ':domicilio'        => $domicilio,
                    ':fecha_nacimiento' => $fecha_nacimiento,
                    ':es_apto_lujo'     => $es_apto_lujo
                ]);

                $conexion->commit();

                header("Location: login.php");
                exit();

            } catch (PDOException $e) {
                $conexion->rollBack();
                if ($e->getCode() == 23000) {
                    $mensaje = "El Email, DNI o CUIL ya se encuentra registrado.";
                } else {
                    $mensaje = "Error: " . $e->getMessage();
                }
                $tipo_mensaje = "error";
            }
        } else {
            $mensaje = "Por favor, completá todos los campos.";
            $tipo_mensaje = "error";
        }
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro - Alquileres Vehículos</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        body { background: linear-gradient(135deg, #1e293b 0%, #0f172a 100%); min-height: 100vh; display: flex; justify-content: center; align-items: center; padding: 20px; }
        .card-registro { background-color: #ffffff; width: 100%; max-width: 650px; border-radius: 12px; box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3); overflow: hidden; }
        .header-registro { background-color: #2563eb; color: #ffffff; padding: 25px; text-align: center; }
        .form-body { padding: 30px; }
        .grid-inputs { display: grid; grid-template-columns: 1fr 1fr; gap: 15px; }
        .grupo-input { margin-bottom: 15px; }
        .grupo-input.full-width { grid-column: 1 / -1; }
        label { display: block; font-size: 13px; font-weight: 600; color: #475569; margin-bottom: 6px; }
        input { width: 100%; padding: 10px 12px; border: 1px solid #cbd5e1; border-radius: 6px; font-size: 14px; }
        .btn-submit { width: 100%; background-color: #2563eb; color: #ffffff; border: none; padding: 12px; border-radius: 6px; font-size: 16px; font-weight: 600; cursor: pointer; margin-top: 10px; }
        .mensaje { padding: 12px; border-radius: 6px; font-size: 14px; margin-bottom: 20px; text-align: center; }
        .error { background-color: #fee2e2; color: #991b1b; border: 1px solid #fecaca; }
        .footer-links { margin-top: 20px; text-align: center; font-size: 14px; color: #64748b; }
        .footer-links a { color: #2563eb; text-decoration: none; font-weight: 600; }
    </style>
</head>
<body>
<div class="card-registro">
    <div class="header-registro">
        <h1>Alquileres Vehículos</h1>
        <p>Creá tu cuenta para gestionar tus reservas</p>
    </div>
    <div class="form-body">
        <?php if (!empty($mensaje)): ?>
            <div class="mensaje <?php echo $tipo_mensaje; ?>"><?php echo htmlspecialchars($mensaje); ?></div>
        <?php endif; ?>
        <form action="registro.php" method="POST">
            <div class="grid-inputs">
                <div class="grupo-input"><label>Nombre *</label><input type="text" name="nombre" required></div>
                <div class="grupo-input"><label>Apellido *</label><input type="text" name="apellido" required></div>
                <div class="grupo-input"><label>DNI *</label><input type="text" name="dni" maxlength="8" required></div>
                <div class="grupo-input"><label>CUIL *</label><input type="text" name="cuil" id="cuil" maxlength="13" placeholder="XX-XXXXXXXX-X" required></div>
                <div class="grupo-input"><label>Correo Electrónico *</label><input type="email" name="email" required></div>
                <div class="grupo-input"><label>Contraseña *</label><input type="password" name="password" required></div>
                <div class="grupo-input"><label>Teléfono *</label><input type="text" name="telefono" maxlength="10" required></div>
                <div class="grupo-input"><label>Fecha de Nacimiento *</label><input type="date" name="fecha_nacimiento" required></div>
                <div class="grupo-input full-width"><label>Domicilio *</label><input type="text" name="domicilio" required></div>
            </div>
            <button type="submit" class="btn-submit">Crear Cuenta</button>
        </form>
        <div class="footer-links">
            <p>¿Ya tenés cuenta? <a href="login.php">Iniciá sesión acá</a></p>
        </div>
    </div>
</div>

<script>
    // Script para autoformatear el CUIL con guiones
    const inputCuil = document.getElementById('cuil');
    if (inputCuil) {
        inputCuil.addEventListener('input', function (e) {
            let value = e.target.value.replace(/\D/g, '');
            if (value.length > 11) {
                value = value.slice(0, 11);
            }
            if (value.length > 2 && value.length <= 10) {
                value = value.slice(0, 2) + '-' + value.slice(2);
            } else if (value.length > 10) {
                value = value.slice(0, 2) + '-' + value.slice(2, 10) + '-' + value.slice(10);
            }
            e.target.value = value;
        });
    }
</script>
</body>
</html>