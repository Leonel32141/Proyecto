<?php
session_start();
require_once 'conexion.php';

if (!isset($_SESSION['id_usuario'])) {
    header("Location: login.php");
    exit();
}

$id_modelo = $_GET['id_modelo'] ?? null;

if (!$id_modelo) {
    header("Location: catalogo_motos.php");
    exit();
}

try {
    $sql_unidades = "SELECT v.*, m.nombre_modelo, m.anio, m.imagen, s.nombre AS nombre_sucursal, s.direccion AS direccion_sucursal 
                     FROM vehiculos v 
                     JOIN modelos m ON v.id_modelo = m.id_modelo 
                     LEFT JOIN sucursales s ON v.id_sucursal_actual = s.id_sucursal 
                     WHERE v.id_modelo = :id_modelo";
    $stmt = $conexion->prepare($sql_unidades);
    $stmt->execute([':id_modelo' => $id_modelo]);
    $unidades = $stmt->fetchAll(PDO::FETCH_ASSOC);

    $modelo_info = $unidades[0] ?? null;
} catch (Exception $e) {
    $unidades = [];
    $modelo_info = null;
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Unidades Disponibles - Los Pipiautos</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        
        /* Fondo turquesa vibrante a juego con el catálogo principal */
        body { background-color: #117584; color: #f1f5f9; min-height: 100vh; padding: 40px 20px; }
        
        .contenedor { max-width: 1000px; margin: 0 auto; }
        
        .nav-superior { margin-bottom: 30px; }
        .btn-volver { 
            display: inline-flex; 
            align-items: center; 
            gap: 8px; 
            color: #ffffff; 
            text-decoration: none; 
            font-size: 14px; 
            font-weight: 500; 
            padding: 9px 16px; 
            background: rgba(15, 23, 42, 0.4); 
            border: 1px solid rgba(255,255,255,0.2); 
            border-radius: 8px; 
            transition: all 0.2s; 
        }
        .btn-volver:hover { background: rgba(15, 23, 42, 0.7); border-color: #ffffff; }

        /* Encabezado elegante con el mismo tono oscuro refinado */
        .header-unidades { display: flex; align-items: center; gap: 30px; background: #0d1b2a; border: 1px solid rgba(255,255,255,0.12); border-radius: 16px; padding: 30px; margin-bottom: 30px; box-shadow: 0 10px 25px rgba(0,0,0,0.3); }
        @media(max-width: 768px) { .header-unidades { flex-direction: column; text-align: center; } }
        
        .header-img { width: 180px; height: 120px; background: #ffffff; border-radius: 10px; display: flex; align-items: center; justify-content: center; padding: 10px; }
        .header-img img { max-width: 100%; max-height: 100%; object-fit: contain; }
        
        .header-info h1 { color: #ffffff; font-size: 26px; margin-bottom: 6px; }
        .header-info p { color: #cbd5e1; font-size: 14px; }

        .lista-unidades { display: flex; flex-direction: column; gap: 15px; }
        
        /* Tarjetas de unidades físicas individuales */
        .tarjeta-unidad { background: #0d1b2a; border: 1px solid rgba(255,255,255,0.12); border-radius: 12px; padding: 20px 25px; display: flex; justify-content: space-between; align-items: center; box-shadow: 0 5px 15px rgba(0,0,0,0.25); transition: border-color 0.2s, transform 0.2s; }
        .tarjeta-unidad:hover { border-color: rgba(52, 211, 153, 0.6); transform: translateY(-2px); }
        @media(max-width: 768px) { .tarjeta-unidad { flex-direction: column; gap: 15px; align-items: flex-start; } }

        .unidad-detalles div { margin-bottom: 4px; color: #cbd5e1; font-size: 14px; }
        .unidad-detalles strong { color: #ffffff; }
        .sucursal-tag { color: #38bdf8; font-weight: 500; font-size: 14px; display: flex; align-items: center; gap: 5px; margin-top: 8px; }
        
        .btn-seleccionar { background-color: #0ea5e9; color: #ffffff; padding: 10px 20px; border-radius: 8px; text-decoration: none; font-weight: 600; font-size: 14px; transition: background 0.2s, box-shadow 0.2s; }
        .btn-seleccionar:hover { background-color: #0284c7; box-shadow: 0 4px 12px rgba(14,165,233,0.4); }
    </style>
</head>
<body>

<div class="contenedor">
    <div class="nav-superior">
        <a href="catalogo_motos.php" class="btn-volver">← Volver al catálogo</a>
    </div>

    <?php if ($modelo_info): ?>
        <div class="header-unidades">
            <div class="header-img">
                <img src="foto_vehiculos/<?php echo htmlspecialchars($modelo_info['imagen']); ?>" alt="Moto">
            </div>
            <div class="header-info">
                <h1><?php echo htmlspecialchars($modelo_info['nombre_modelo']); ?> (<?php echo $modelo_info['anio']; ?>)</h1>
                <p>Listado oficial de unidades físicas disponibles en nuestra red de sucursales con kilometraje auditado.</p>
            </div>
        </div>
    <?php endif; ?>

    <div class="lista-unidades">
        <?php if (!empty($unidades)): ?>
            <?php foreach ($unidades as $unidad): ?>
                <div class="tarjeta-unidad">
                    <div class="unidad-detalles">
                        <div><strong>Patente:</strong> <?php echo htmlspecialchars($unidad['patente']); ?></div>
                        <div><strong>Kilometraje:</strong> <?php echo number_format($unidad['kilometraje_actual'], 0, ',', '.'); ?> km</div>
                        <div class="sucursal-tag">
                            📍 <?php echo htmlspecialchars($unidad['nombre_sucursal'] ?? 'Sucursal Central'); ?> 
                            <span style="font-size: 12px; color: #94a3b8;">(<?php echo htmlspecialchars($unidad['direccion_sucursal'] ?? 'CABA'); ?>)</span>
                        </div>
                    </div>
                    <div>
                        <!-- Apunta directo al nuevo archivo general de reservas con origen en motos -->
                        <a href="reservar_moto.php?id=<?php echo $unidad['id_vehiculo']; ?>" class="btn-seleccionar">Seleccionar Unidad</a>
                    </div>
                </div>
            <?php endforeach; ?>
        <?php else: ?>
            <p style="text-align: center; color: #f1f5f9; padding: 40px;">No hay unidades físicas disponibles para este modelo.</p>
        <?php endif; ?>
    </div>
</div>

</body>
</html>