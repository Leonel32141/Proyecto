<?php
require_once 'conexion.php';

$mensaje = "";
$tipo_mensaje = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $nombre = trim($_POST['nombre']);
    $apellido = trim($_POST['apellido']);
    $email = trim($_POST['email']);
    $password = trim($_POST['password']);
    $cuil = trim($_POST['cuil']);
    $telefono = trim($_POST['telefono']);
    $dni = trim($_POST['dni']);
    $licencia = trim($_POST['licencia_conducir']);
    $domicilio = trim($_POST['domicilio']);

    // Limpiamos los guiones del CUIL para validación numérica limpia
    $cuil_limpio = str_replace('-', '', $cuil);

    // Validaciones estrictas en PHP
    if (!ctype_digit($dni) || strlen($dni) < 7 || strlen($dni) > 8) {
        $mensaje = "El DNI debe tener entre 7 y 8 números.";
        $tipo_mensaje = "error";
    } elseif (!ctype_digit($cuil_limpio) || strlen($cuil_limpio) !== 11) {
        $mensaje = "El CUIL debe contener exactamente 11 números.";
        $tipo_mensaje = "error";
    } elseif (!empty($telefono) && (!ctype_digit($telefono) || strlen($telefono) !== 10)) {
        $mensaje = "El teléfono debe contener exactamente 10 números (ej: 1122334455).";
        $tipo_mensaje = "error";
    } elseif (!empty($nombre) && !empty($apellido) && !empty($email) && !empty($password) && !empty($dni) && !empty($cuil_limpio)) {
        try {
            // Iniciamos la transacción para guardar en las dos tablas
            $conexion->beginTransaction();

            // Hash de la contraseña para seguridad
            $password_hash = password_hash($password, PASSWORD_DEFAULT);
            $id_rol_cliente = 3; // ID asignado al rol CLIENTE

            // 1. Insertamos en la tabla usuarios
            $sql_usuario = "INSERT INTO usuarios (id_rol, email, password_hash, nombre, apellido, cuil, telefono) 
                            VALUES (:id_rol, :email, :password_hash, :nombre, :apellido, :cuil, :telefono)";
            $stmt_u = $conexion->prepare($sql_usuario);
            $stmt_u->execute([
                ':id_rol' => $id_rol_cliente,
                ':email' => $email,
                ':password_hash' => $password_hash,
                ':nombre' => $nombre,
                ':apellido' => $apellido,
                ':cuil' => $cuil, // Se guarda formateado con guiones
                ':telefono' => $telefono
            ]);

            // Recuperamos el ID generado
            $id_usuario = $conexion->lastInsertId();

            // 2. Insertamos en la tabla clientes utilizando el mismo ID
            $sql_cliente = "INSERT INTO clientes (id_cliente, dni, licencia_conducir, domicilio) 
                            VALUES (:id_cliente, :dni, :licencia, :domicilio)";
            $stmt_c = $conexion->prepare($sql_cliente);
            $stmt_c->execute([
                ':id_cliente' => $id_usuario,
                ':dni' => $dni,
                ':licencia' => $licencia,
                ':domicilio' => $domicilio
            ]);

            // Confirmamos la transacción
            $conexion->commit();

            $mensaje = "¡Registro completado con éxito! Ya podés iniciar sesión.";
            $tipo_mensaje = "exito";

        } catch (PDOException $e) {
            $conexion->rollBack();
            if ($e->getCode() == 23000) {
                $mensaje = "El Email, DNI o CUIL ingresado ya se encuentra registrado.";
            } else {
                $mensaje = "Error al completar el registro: " . $e->getMessage();
            }
            $tipo_mensaje = "error";
        }
    } else {
        $mensaje = "Por favor, completá todos los campos obligatorios.";
        $tipo_mensaje = "error";
    }
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Registro de Cliente - Alquileres Vehículos</title>
    <style id="style">
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
        .card-registro {
            background-color: #ffffff;
            width: 100%;
            max-width: 650px;
            border-radius: 12px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.3);
            overflow: hidden;
        }
        .header-registro {
            background-color: #2563eb;
            color: #ffffff;
            padding: 25px;
            text-align: center;
        }
        .header-registro h1 {
            font-size: 24px;
            font-weight: 700;
            margin-bottom: 5px;
        }
        .header-registro p {
            font-size: 14px;
            opacity: 0.9;
        }
        .form-body {
            padding: 30px;
        }
        .grid-inputs {
            display: grid;
            grid-template-columns: 1fr 1fr;
            gap: 15px;
        }
        @media (max-width: 600px) {
            .grid-inputs {
                grid-template-columns: 1fr;
            }
        }
        .grupo-input {
            margin-bottom: 15px;
        }
        .grupo-input.full-width {
            grid-column: 1 / -1;
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
            padding: 10px 12px;
            border: 1px solid #cbd5e1;
            border-radius: 6px;
            font-size: 14px;
            transition: border-color 0.2s, box-shadow 0.2s;
        }
        input:focus {
            outline: none;
            border-color: #2563eb;
            box-shadow: 0 0 0 3px rgba(37, 99, 235, 0.15);
        }
        .btn-submit {
            width: 100%;
            background-color: #2563eb;
            color: #ffffff;
            border: none;
            padding: 12px;
            border-radius: 6px;
            font-size: 16px;
            font-weight: 600;
            cursor: pointer;
            margin-top: 10px;
            transition: background-color 0.2s;
        }
        .btn-submit:hover {
            background-color: #1d4ed8;
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
            <div class="mensaje <?php echo $tipo_mensaje; ?>">
                <?php echo htmlspecialchars($mensaje); ?>
            </div>
        <?php endif; ?>

        <form action="registro.php" method="POST">
            <div class="grid-inputs">
                <div class="grupo-input">
                    <label for="nombre">Nombre *</label>
                    <input type="text" id="nombre" name="nombre" placeholder="Juan" required>
                </div>

                <div class="grupo-input">
                    <label for="apellido">Apellido *</label>
                    <input type="text" id="apellido" name="apellido" placeholder="Pérez" required>
                </div>

                <div class="grupo-input">
                    <label for="dni">DNI *</label>
                    <input type="text" id="dni" name="dni" placeholder="38444555" maxlength="8"
                           inputmode="numeric" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required>
                </div>

                <div class="grupo-input">
                    <label for="cuil">CUIL *</label>
                    <input type="text" id="cuil" name="cuil" placeholder="20-38444555-9" maxlength="13"
                           inputmode="numeric" oninput="formatearCUIL(this)" required>
                </div>

                <div class="grupo-input">
                    <label for="email">Correo Electrónico *</label>
                    <input type="email" id="email" name="email" placeholder="juan.perez@email.com" required>
                </div>

                <div class="grupo-input">
                    <label for="password">Contraseña *</label>
                    <input type="password" id="password" name="password" placeholder="••••••••" required>
                </div>

                <div class="grupo-input">
                    <label for="telefono">Teléfono (10 dígitos) *</label>
                    <input type="text" id="telefono" name="telefono" placeholder="1122334455" maxlength="10"
                           inputmode="numeric" oninput="this.value = this.value.replace(/[^0-9]/g, '');" required>
                </div>

                <div class="grupo-input">
                    <label for="licencia_conducir">Licencia de Conducir *</label>
                    <input type="text" id="licencia_conducir" name="licencia_conducir" placeholder="B1-38444555" required>
                </div>

                <div class="grupo-input full-width">
                    <label for="domicilio">Domicilio *</label>
                    <input type="text" id="domicilio" name="domicilio" placeholder="Av. Rivadavia 4500, CABA" required>
                </div>
            </div>

            <button type="submit" class="btn-submit">Crear Cuenta</button>
        </form>
    </div>
</div>

<script>
// Función para aplicar formato automático de CUIL: XX-XXXXXXXX-X
function formatearCUIL(input) {
    let valor = input.value.replace(/[^0-9]/g, ''); // Deja solo números
    if (valor.length > 11) valor = valor.slice(0, 11); // Corta en 11 dígitos
    
    if (valor.length > 10) {
        input.value = valor.slice(0, 2) + '-' + valor.slice(2, 10) + '-' + valor.slice(10);
    } else if (valor.length > 2) {
        input.value = valor.slice(0, 2) + '-' + valor.slice(2);
    } else {
        input.value = valor;
    }
}
</script>

</body>
</html>

