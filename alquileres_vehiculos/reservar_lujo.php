<?php
session_start();
require_once 'conexion.php';

if (!isset($_SESSION['id_usuario'])) {
    header("Location: login.php");
    exit();
}

$id_vehiculo = $_GET['id'] ?? null;
$origen = $_GET['origen'] ?? 'lujo';

if (!$id_vehiculo) {
    header("Location: index.php");
    exit();
}

// Obtener los datos del vehículo, su modelo y el nombre de la sucursal actual usando un JOIN
$sql = "SELECT v.*, m.nombre_modelo, m.anio, m.imagen, s.id_sucursal, s.nombre AS nombre_sucursal, s.direccion AS direccion_sucursal 
        FROM vehiculos v 
        JOIN modelos m ON v.id_modelo = m.id_modelo 
        LEFT JOIN sucursales s ON v.id_sucursal_actual = s.id_sucursal 
        WHERE v.id_vehiculo = :id_vehiculo";
$stmt = $conexion->prepare($sql);
$stmt->execute([':id_vehiculo' => $id_vehiculo]);
$vehiculo = $stmt->fetch(PDO::FETCH_ASSOC);

// Obtener datos del usuario logueado
$sql_user = "SELECT * FROM usuarios WHERE id_usuario = :id_usuario";
$stmt_user = $conexion->prepare($sql_user);
$stmt_user->execute([':id_usuario' => $_SESSION['id_usuario']]);
$usuario = $stmt_user->fetch(PDO::FETCH_ASSOC);

if (!$vehiculo) {
    echo "Vehículo no encontrado.";
    exit();
}

$tarifa_diaria = 150000;

$reserva_exitosa = false;
$contrato_generado = "";
$total_pagado = 0;
$dias_alquiler = 1;
$fecha_retiro = "";
$hora_retiro = "";
$conductores_adicionales = 0;
$error_db = "";

if ($_SERVER['REQUEST_METHOD'] === 'POST') {
    $tipo_doc = $_POST['tipo_doc'] ?? 'DNI';
    $numero_doc = $_POST['numero_doc'] ?? '';
    $fecha_retiro = $_POST['fecha_retiro'] ?? '';
    $hora_retiro = $_POST['hora_retiro'] ?? '';
    $dias_alquiler = intval($_POST['dias_alquiler'] ?? 1);
    $conductores_adicionales = intval($_POST['conductores_adicionales'] ?? 0);
    $idioma_contrato = $_POST['idioma_contrato'] ?? 'es';
    $tarjeta_numero = $_POST['tarjeta_numero'] ?? '';
    $tarjeta_vencimiento = $_POST['tarjeta_vencimiento'] ?? '';
    $tarjeta_cvv = $_POST['tarjeta_cvv'] ?? '';
    $tarjeta_marca = $_POST['tarjeta_marca'] ?? 'Visa';

    // Validación estricta de tarjeta vencida en el backend (Año actual: 2026)
    $partes_venc = explode('/', $tarjeta_vencimiento);
    $mes_venc = intval($partes_venc[0] ?? 0);
    $anio_venc = intval('20' . ($partes_venc[1] ?? '0'));
    $anio_actual = 2026;
    $mes_actual = 10; // Octubre 2026

    $tarjeta_valida = true;
    if ($anio_venc < $anio_actual || ($anio_venc === $anio_actual && $mes_venc < $mes_actual)) {
        $tarjeta_valida = false;
        $error_db = "Error: La tarjeta ingresada se encuentra vencida. Ingrese una fecha válida.";
    }

    if ($tarjeta_valida && !empty($numero_doc) && !empty($tarjeta_numero) && !empty($fecha_retiro)) {
        $costo_conductores = $conductores_adicionales * 15000 * $dias_alquiler;
        $total_pagado = ($tarifa_diaria * max(1, $dias_alquiler)) + $costo_conductores;

        $fecha_hora_inicio = $fecha_retiro . ' ' . $hora_retiro . ':00';
        $fecha_fin = date('Y-m-d H:i:s', strtotime($fecha_hora_inicio . " + $dias_alquiler days"));

        try {
            $conexion->beginTransaction();

            $insert_alquiler = "INSERT INTO alquileres (id_cliente, id_vehiculo, id_sucursal_retiro, id_sucursal_devolucion, fecha_reserva, fecha_inicio_pautada, fecha_fin_pautada, es_oneway, monto_total, monto_sena, estado_reserva) 
                                VALUES (:id_cliente, :id_vehiculo, :id_sucursal, :id_sucursal, NOW(), :fecha_inicio, :fecha_fin, 0, :monto_total, :monto_sena, 'RESERVADA')";
            $stmt_ins = $conexion->prepare($insert_alquiler);
            $stmt_ins->execute([
                ':id_cliente' => $_SESSION['id_usuario'],
                ':id_vehiculo' => $id_vehiculo,
                ':id_sucursal' => $vehiculo['id_sucursal'] ?? 1,
                ':fecha_inicio' => $fecha_hora_inicio,
                ':fecha_fin' => $fecha_fin,
                ':monto_total' => $total_pagado,
                ':monto_sena' => $total_pagado
            ]);

            $id_alquiler_nuevo = $conexion->lastInsertId();

            $nro_comprobante = 'LUX-' . rand(100000, 999999);
            $insert_pago = "INSERT INTO pagos (id_alquiler, id_metodo_pago, monto, fecha_pago, estado_pago, numero_comprobante) 
                            VALUES (:id_alquiler, 2, :monto, NOW(), 'APROBADO', :comprobante)";
            $stmt_pago = $conexion->prepare($insert_pago);
            $stmt_pago->execute([
                ':id_alquiler' => $id_alquiler_nuevo,
                ':monto' => $total_pagado,
                ':comprobante' => $nro_comprobante
            ]);

            $update_vehiculo = "UPDATE vehiculos SET estado = 'ALQUILADO' WHERE id_vehiculo = :id_vehiculo";
            $stmt_upd = $conexion->prepare($update_vehiculo);
            $stmt_upd->execute([':id_vehiculo' => $id_vehiculo]);

            $conexion->commit();
            $reserva_exitosa = true;

        } catch (Exception $e) {
            $conexion->rollBack();
            $error_db = "Error al procesar la reserva: " . $e->getMessage();
        }

        if ($reserva_exitosa) {
            if ($idioma_contrato === 'en') {
                $contrato_generado = "LUXURY VEHICLE CONTRACT (#PIP-" . rand(10000, 99999) . ")\n" .
                                     "--------------------------------------------------\n" .
                                     "Vehicle: " . $vehiculo['nombre_modelo'] . " (" . $vehiculo['anio'] . ")\n" .
                                     "License Plate: " . $vehiculo['patente'] . "\n" .
                                     "Pickup Branch: " . htmlspecialchars($vehiculo['nombre_sucursal'] ?? 'Central') . "\n" .
                                     "Pickup Date & Time: " . $fecha_retiro . " at " . $hora_retiro . " hs\n" .
                                     "Duration: " . $dias_alquiler . " day(s)\n" .
                                     "Additional Drivers: " . $conductores_adicionales . " (Max. 3 allowed)\n" .
                                     "Voucher Ref: " . $nro_comprobante . "\n" .
                                     "--------------------------------------------------\n" .
                                     "NON-NEGOTIABLE LUXURY TERMS:\n" .
                                     "- Total Paid (100% Advance): $ " . number_format($total_pagado, 0, ',', '.') . "\n" .
                                     "- Payment Method: " . $tarjeta_marca . " (No cash accepted)\n" .
                                     "- Security Deposit: Mandatory hold secured via credit card.\n" .
                                     "- Insurance Policy: Mandatory Full Coverage (Todo Riesgo) included.\n" .
                                     "Thank you for choosing Los Pipiautos!";
            } else {
                $contrato_generado = "CONTRATO DE LOCACIÓN - VEHÍCULO DE LUJO (#PIP-" . rand(10000, 99999) . ")\n" .
                                     "--------------------------------------------------\n" .
                                     "Vehículo: " . $vehiculo['nombre_modelo'] . " (" . $vehiculo['anio'] . ")\n" .
                                     "Patente: " . $vehiculo['patente'] . "\n" .
                                     "Sucursal de Retiro: " . htmlspecialchars($vehiculo['nombre_sucursal'] ?? 'Central') . "\n" .
                                     "Fecha y Hora de Retiro: " . $fecha_retiro . " a las " . $hora_retiro . " hs\n" .
                                     "Duración del Alquiler: " . $dias_alquiler . " día(s)\n" .
                                     "Conductores Adicionales: " . $conductores_adicionales . " (Máx. 3 permitidos)\n" .
                                     "Comprobante de Pago: " . $nro_comprobante . "\n" .
                                     "--------------------------------------------------\n" .
                                     "CONDICIONES NO NEGOCIABLES (GAMA ALTA):\n" .
                                     "- Total Abonado (100% Adelantado): $ " . number_format($total_pagado, 0, ',', '.') . "\n" .
                                     "- Medio de Pago: " . $tarjeta_marca . " (No se acepta efectivo)\n" .
                                     "- Depósito de Garantía: Retención obligatoria en tarjeta de crédito.\n" .
                                     "- Póliza de Seguro: Seguro Todo Riesgo Obligatorio incluido.\n" .
                                     "¡Gracias por elegir Los Pipiautos!";
            }
        }
    }
}

$link_volver = ($origen === 'motos') ? 'catalogo_motos.php' : 'index.php#flota-lujo';
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Reservar Unidad - Los Pipiautos</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        body { background: #0b0f19; color: #f1f5f9; min-height: 100vh; padding: 40px 20px; display: flex; justify-content: center; align-items: center; }
        .card-reserva { background: #111827; border: 1px solid rgba(212, 175, 55, 0.4); border-radius: 16px; width: 100%; max-width: 900px; overflow: hidden; box-shadow: 0 15px 35px rgba(0,0,0,0.6); display: grid; grid-template-columns: 1fr 1.2fr; }
        @media(max-width: 768px) { .card-reserva { grid-template-columns: 1fr; } }
        .imagen-box { background: #000; display: flex; align-items: center; justify-content: center; padding: 20px; min-height: 300px; }
        .imagen-box img { max-width: 100%; max-height: 100%; object-fit: contain; border-radius: 8px; }
        .info-box { padding: 35px; display: flex; flex-direction: column; justify-content: space-between; }
        h1 { color: #ffffff; font-size: 22px; margin-bottom: 6px; }
        .badge { background: rgba(212, 175, 55, 0.2); color: #d4af37; padding: 4px 12px; border-radius: 20px; font-size: 11px; font-weight: bold; text-transform: uppercase; letter-spacing: 1px; display: inline-block; margin-bottom: 10px; }
        p { color: #94a3b8; font-size: 13px; line-height: 1.5; margin-bottom: 6px; }
        
        .form-row { display: grid; grid-template-columns: 1fr 1fr; gap: 8px; }
        .form-group { margin-bottom: 10px; }
        .form-group label { display: block; font-size: 11px; color: #94a3b8; margin-bottom: 3px; font-weight: 500; }
        .form-group input, .form-group select { width: 100%; padding: 8px; background: #1f2937; border: 1px solid rgba(255,255,255,0.15); border-radius: 6px; color: #fff; font-size: 12px; }
        .form-group input:focus, .form-group select:focus { outline: none; border-color: #d4af37; }

        .alerta-seguridad { background: rgba(212, 175, 55, 0.1); border-left: 3px solid #d4af37; padding: 8px; font-size: 11px; color: #d4af37; margin-bottom: 10px; border-radius: 4px; }
        .precio-box { background: rgba(212, 175, 55, 0.1); border: 1px solid rgba(212, 175, 55, 0.3); padding: 8px; border-radius: 6px; margin-bottom: 10px; display: flex; justify-content: space-between; align-items: center; }
        .precio-box span { font-size: 11px; color: #d4af37; }
        .precio-box strong { font-size: 15px; color: #fff; }

        .btn-confirmar { background-color: #d4af37; color: #000; border: none; padding: 10px; border-radius: 6px; font-weight: bold; font-size: 13px; cursor: pointer; width: 100%; margin-top: 10px; transition: background 0.2s; }
        .btn-confirmar:hover { background-color: #c5a059; }
        
        .btn-volver { display: block; text-align: center; margin-top: 10px; color: #94a3b8; text-decoration: none; font-size: 12px; }
        .btn-volver:hover { color: #ffffff; }

        .contrato-box { background: #1f2937; border: 1px solid #d4af37; padding: 10px; border-radius: 6px; margin-top: 10px; max-height: 200px; overflow-y: auto; }
        .contrato-box pre { white-space: pre-wrap; font-size: 10px; color: #d4af37; font-family: monospace; }
        .alerta-error { background: rgba(239, 68, 68, 0.2); color: #f87171; padding: 8px; border-radius: 6px; font-size: 11px; margin-bottom: 10px; }
    </style>
</head>
<body>

<div class="card-reserva">
    <div class="imagen-box">
        <img src="foto_vehiculos/<?php echo htmlspecialchars($vehiculo['imagen']); ?>" alt="Vehículo de Lujo">
    </div>
    <div class="info-box">
        <div>
            <span class="badge">Gestión de Reserva - Gama Alta</span>
            <h1><?php echo htmlspecialchars($vehiculo['nombre_modelo']); ?> (<?php echo $vehiculo['anio']; ?>)</h1>
            <p><strong>Patente:</strong> <?php echo htmlspecialchars($vehiculo['patente']); ?> | <strong>Km:</strong> <?php echo number_format($vehiculo['kilometraje_actual'], 0, ',', '.'); ?></p>
            <p>📍 <strong>Sucursal:</strong> <?php echo htmlspecialchars($vehiculo['nombre_sucursal'] ?? 'Sucursal Central'); ?></p>
            <hr style="border: 0; border-top: 1px solid rgba(255,255,255,0.1); margin: 8px 0;">
        </div>

        <div>
            <?php if (!empty($error_db)): ?>
                <div class="alerta-error"><?php echo $error_db; ?></div>
            <?php endif; ?>

            <?php if (!$reserva_exitosa): ?>
                <form method="POST" id="formReserva">
                    <div class="alerta-seguridad">
                      🔒 Seguro Todo Riesgo Obligatorio incluido. Máx. 3 conductores adicionales. Cero efectivo.
                    </div>

                    <div class="precio-box">
                        <span>Tarifa Día: $<?php echo number_format($tarifa_diaria, 0, ',', '.'); ?></span>
                        <div><span>Total: </span><strong id="lblTotal">$<?php echo number_format($tarifa_diaria, 0, ',', '.'); ?></strong></div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Fecha Retiro</label>
                            <input type="date" name="fecha_retiro" required min="<?php echo date('Y-m-d'); ?>">
                        </div>
                        <div class="form-group">
                            <label>Hora Retiro</label>
                            <select name="hora_retiro">
                                <option value="10:00">10:00 hs</option>
                                <option value="12:00">12:00 hs</option>
                                <option value="15:00">15:00 hs</option>
                                <option value="18:00">18:00 hs</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Días de Alquiler</label>
                            <input type="number" name="dias_alquiler" id="dias_alquiler" value="1" min="1" max="30" required>
                        </div>
                        <div class="form-group">
                            <label>Conductores Extra (Máx. 3)</label>
                            <select name="conductores_adicionales" id="conductores_adicionales">
                                <option value="0">Ninguno</option>
                                <option value="1">1 conductor</option>
                                <option value="2">2 conductores</option>
                                <option value="3">3 conductores</option>
                            </select>
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Tipo Documento</label>
                            <select name="tipo_doc" id="tipo_doc">
                                <option value="DNI">DNI (Máx. 8 dígitos)</option>
                                <option value="Pasaporte">Pasaporte</option>
                            </select>
                        </div>
                        <div class="form-group">
                            <label>Número Documento</label>
                            <input type="text" name="numero_doc" id="numero_doc" required placeholder="Ej: 95745350" maxlength="8">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>Tarjeta (Garantía / Pago)</label>
                            <input type="text" name="tarjeta_numero" id="tarjeta_numero" required placeholder="0000 0000 0000 0000" maxlength="19">
                        </div>
                        <div class="form-group">
                            <label>Vencimiento (MM/AA)</label>
                            <input type="text" name="tarjeta_vencimiento" id="tarjeta_vencimiento" required placeholder="MM/AA" maxlength="5">
                        </div>
                    </div>

                    <div class="form-row">
                        <div class="form-group">
                            <label>CVV (Máx. 3 dígitos)</label>
                            <input type="password" name="tarjeta_cvv" id="tarjeta_cvv" required placeholder="123" maxlength="3">
                        </div>
                        <div class="form-group">
                            <label>Idioma Contrato</label>
                            <select name="idioma_contrato">
                                <option value="es">Español</option>
                                <option value="en">English</option>
                            </select>
                        </div>
                    </div>

                    <button type="submit" class="btn-confirmar">Confirmar Reserva de Lujo (100% Adelantado)</button>
                </form>

                <script>
                    const tarifaDiaria = <?php echo $tarifa_diaria; ?>;
                    const diasInput = document.getElementById('dias_alquiler');
                    const condInput = document.getElementById('conductores_adicionales');
                    const lblTotal = document.getElementById('lblTotal');

                    function calcularTotal() {
                        let dias = parseInt(diasInput.value) || 1;
                        let conds = parseInt(condInput.value) || 0;
                        let costoConds = conds * 15000 * dias;
                        let total = (tarifaDiaria * dias) + costoConds;
                        lblTotal.textContent = '$' + total.toLocaleString('es-AR');
                    }

                    diasInput.addEventListener('input', calcularTotal);
                    condInput.addEventListener('change', calcularTotal);

                    const tipoDocSelect = document.getElementById('tipo_doc');
                    const numDocInput = document.getElementById('numero_doc');

                    tipoDocSelect.addEventListener('change', function() {
                        numDocInput.value = '';
                        if (this.value === 'DNI') {
                            numDocInput.setAttribute('maxlength', '8');
                            numDocInput.setAttribute('placeholder', 'Ej: 95745350');
                        } else {
                            numDocInput.setAttribute('maxlength', '12');
                            numDocInput.setAttribute('placeholder', 'Nro Pasaporte');
                        }
                    });

                    numDocInput.addEventListener('input', function (e) {
                        if (tipoDocSelect.value === 'DNI') {
                            e.target.value = e.target.value.replace(/\D/g, '').substring(0, 8);
                        } else {
                            e.target.value = e.target.value.replace(/[^a-zA-Z0-9]/g, '').substring(0, 12).toUpperCase();
                        }
                    });

                    document.getElementById('tarjeta_numero').addEventListener('input', function (e) {
                        let value = e.target.value.replace(/\D/g, '').substring(0, 16);
                        let formatted = value.match(/.{1,4}/g)?.join(' ') || '';
                        e.target.value = formatted;
                    });

                    // Validación estricta CVV (solo números, máximo 3 dígitos)
                    document.getElementById('tarjeta_cvv').addEventListener('input', function (e) {
                        e.target.value = e.target.value.replace(/\D/g, '').substring(0, 3);
                    });

                    // Formato automático de vencimiento (MM/AA) y control de vigencia básica en vivo
                    document.getElementById('tarjeta_vencimiento').addEventListener('input', function (e) {
                        let value = e.target.value.replace(/\D/g, '').substring(0, 4);
                        if (value.length >= 2) {
                            value = value.substring(0, 2) + '/' + value.substring(2, 4);
                        }
                        e.target.value = value;
                    });
                </script>
            <?php else: ?>
                <div style="text-align: center;">
                    <h3 style="color: #d4af37; margin-bottom: 6px;">¡Reserva de Lujo Exitosa!</h3>
                    <p style="font-size: 13px; color: #f1f5f9;">¡Transacción registrada y vehículo de alta gama asignado correctamente!</p>
                    <p style="font-size: 11px; color: #94a3b8; margin-top: 4px;">Te esperamos el <strong><?php echo htmlspecialchars($fecha_retiro); ?></strong> a las <strong><?php echo htmlspecialchars($hora_retiro); ?> hs</strong> en nuestra sucursal de <strong><?php echo htmlspecialchars($vehiculo['nombre_sucursal']); ?></strong>.</p>
                    
                    <div class="contrato-box">
                        <p style="color: #fff; font-weight: bold; margin-bottom: 4px; font-size: 11px;">📄 Contrato Oficial de Lujo:</p>
                        <pre><?php echo htmlspecialchars($contrato_generado); ?></pre>
                    </div>
                </div>
            <?php endif; ?>

            <a href="<?php echo $link_volver; ?>" class="btn-volver">← Volver al inicio</a>
        </div>
    </div>
</div>

</body>
</html>