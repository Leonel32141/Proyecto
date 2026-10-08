<?php
session_start();
require_once 'conexion.php';

if (!isset($_SESSION['id_usuario'])) {
    header("Location: login.php");
    exit();
}

try {
    $sql_motos = "SELECT m.id_modelo, m.nombre_modelo, m.anio, m.imagen, COUNT(v.id_vehiculo) AS total_unidades
                  FROM modelos m 
                  JOIN vehiculos v ON m.id_modelo = v.id_modelo 
                  WHERE m.id_modelo BETWEEN 27 AND 30
                  GROUP BY m.id_modelo, m.nombre_modelo, m.anio, m.imagen";
    $stmt = $conexion->prepare($sql_motos);
    $stmt->execute();
    $motos = $stmt->fetchAll(PDO::FETCH_ASSOC);
} catch (Exception $e) {
    $motos = [];
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Catálogo de Motocicletas - Los Pipiautos</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif; }
        
        /* Fondo turquesa vibrante y colorido */
        body { background-color: #117584; color: #f1f5f9; min-height: 100vh; padding: 40px 20px; }
        
        .contenedor { max-width: 1200px; margin: 0 auto; }
        
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

        .header-catalogo { text-align: center; margin-bottom: 50px; }
        .header-catalogo .badge-categoria { 
            background: rgba(255, 255, 255, 0.2); 
            color: #ffffff; 
            padding: 6px 16px; 
            border-radius: 20px; 
            font-size: 11px; 
            font-weight: 700; 
            text-transform: uppercase; 
            letter-spacing: 2px; 
            border: 1px solid rgba(255, 255, 255, 0.3); 
        }
        .header-catalogo h1 { color: #ffffff; font-size: 34px; font-weight: 600; margin-top: 15px; letter-spacing: 0.5px; }
        .header-catalogo p { color: #e2e8f0; font-size: 15px; margin-top: 8px; }
        
        .grid-motos { 
            display: grid; 
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr)); 
            gap: 24px; 
        }
        
        /* Tarjetas con tono oscuro refinado que contrasta hermoso con el turquesa */
        .tarjeta-moto { 
            background: #05393d; 
            border: 1px solid rgba(255, 255, 255, 0.12); 
            border-radius: 14px; 
            overflow: hidden; 
            box-shadow: 0 10px 25px rgba(0,0,0,0.3); 
            display: flex;
            flex-direction: column;
            justify-content: space-between;
            transition: transform 0.25s ease, border-color 0.25s ease, box-shadow 0.25s ease; 
        }
        .tarjeta-moto:hover { 
            transform: translateY(-4px); 
            border-color: rgba(52, 211, 153, 0.6); 
            box-shadow: 0 15px 35px rgba(0,0,0,0.45); 
        }
        
        .imagen-container { 
            height: 190px; 
            background: #ffffff; 
            display: flex; 
            align-items: center; 
            justify-content: center; 
            padding: 20px; 
            border-bottom: 1px solid rgba(255,255,255,0.08);
        }
        .imagen-container img { 
            max-width: 100%; 
            max-height: 100%; 
            object-fit: contain; 
            transition: transform 0.3s;
        }
        .tarjeta-moto:hover .imagen-container img { transform: scale(1.03); }

        .info-moto { padding: 22px; display: flex; flex-direction: column; flex-grow: 1; justify-content: space-between; }
        
        .info-moto h3 { 
            color: #ffffff; 
            font-size: 18px; 
            font-weight: 600;
            margin-bottom: 6px; 
            display: flex;
            justify-content: space-between;
            align-items: center;
        }
        .info-moto h3 span { font-size: 13px; color: #38bdf8; font-weight: 400; background: rgba(56,189,248,0.15); padding: 2px 8px; border-radius: 4px; }
        
        .stock-info { 
            color: #cbd5e1; 
            font-size: 13px; 
            margin: 12px 0 18px 0; 
            display: flex;
            align-items: center;
            gap: 6px;
        }
        .stock-info strong { color: #ffffff; }

        .footer-tarjeta { 
            display: flex; 
            justify-content: space-between; 
            align-items: center; 
            border-top: 1px solid rgba(255,255,255,0.08); 
            padding-top: 15px; 
        }
        
        .badge-estado {
            font-size: 11px;
            font-weight: 600;
            color: #34d399;
            background: rgba(52, 211, 153, 0.15);
            padding: 4px 10px;
            border-radius: 6px;
            letter-spacing: 0.5px;
        }

        .btn-ver-unidades { 
            background-color: #0ea5e9; 
            color: #ffffff; 
            padding: 8px 16px; 
            border-radius: 6px; 
            text-decoration: none; 
            font-weight: 600; 
            font-size: 13px; 
            transition: background 0.2s, box-shadow 0.2s; 
        }
        .btn-ver-unidades:hover { 
            background-color: #0284c7; 
            box-shadow: 0 4px 12px rgba(14,165,233,0.4); 
        }
    </style>
</head>
<body>

<div class="contenedor">
    <div class="nav-superior">
        <a href="index.php#flota" class="btn-volver">← Volver al inicio</a>
    </div>
    
    <div class="header-catalogo">
        <span class="badge-categoria">Movilidad Ágil</span>
        <h1>Catálogo de Motocicletas</h1>
        <p>Seleccioná un modelo para consultar las unidades disponibles en nuestra red y sus respectivas sucursales.</p>
    </div>

    <div class="grid-motos">
        <?php if (!empty($motos)): ?>
            <?php foreach ($motos as $moto): ?>
                <div class="tarjeta-moto">
                    <div>
                        <div class="imagen-container">
                            <img src="foto_vehiculos/<?php echo htmlspecialchars($moto['imagen']); ?>" alt="<?php echo htmlspecialchars($moto['nombre_modelo']); ?>">
                        </div>
                        <div class="info-moto">
                            <div>
                                <h3><?php echo htmlspecialchars($moto['nombre_modelo']); ?> <span><?php echo $moto['anio']; ?></span></h3>
                                <p class="stock-info">📍 Unidades activas en red: <strong><?php echo $moto['total_unidades']; ?></strong></p>
                            </div>
                        </div>
                    </div>
                    <div style="padding: 0 22px 22px 22px;">
                        <div class="footer-tarjeta">
                            <span class="badge-estado">DISPONIBLE</span>
                            <a href="unidades_moto.php?id_modelo=<?php echo $moto['id_modelo']; ?>" class="btn-ver-unidades">Ver Unidades</a>
                        </div>
                    </div>
                </div>
            <?php endforeach; ?>
        <?php else: ?>
            <p style="text-align: center; color: #f1f5f9; grid-column: 1 / -1; padding: 40px;">No hay motocicletas disponibles en este momento.</p>
        <?php endif; ?>
    </div>
</div>

</body>
</html>