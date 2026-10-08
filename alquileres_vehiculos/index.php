<?php
session_start();
require_once 'conexion.php';

if (!isset($_SESSION['id_usuario'])) {
    header("Location: login.php");
    exit();
}
?>
<!DOCTYPE html>
<html lang="es">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Los Pipiautos - Alquiler de Vehículos en CABA y AMBA</title>
    <style>
        * {
            box-sizing: border-box;
            margin: 0;
            padding: 0;
            font-family: 'Segoe UI', Tahoma, Geneva, Verdana, sans-serif;
        }

        body {
            background-color: #f1f5f9;
            color: #334155;
            min-height: 100vh;
            overflow-x: hidden;
        }

        /* --- BARRA SUPERIOR --- */
        header {
            width: 100%;
            height: 75px;
            background-color: #0f172a;
            border-bottom: 3px solid #2563eb;
            display: flex;
            justify-content: space-between;
            align-items: center;
            padding: 0 40px;
            position: fixed;
            top: 0;
            z-index: 1000;
        }

        .menu-container { position: relative; }

        .btn-menu-toggle {
            background-color: #3b82f6;
            border: none;
            color: #ffffff;
            padding: 10px 18px;
            border-radius: 8px;
            font-weight: 600;
            font-size: 14px;
            cursor: pointer;
            display: flex;
            align-items: center;
            gap: 8px;
            transition: background-color 0.2s;
        }
        .btn-menu-toggle:hover { background-color: #2563eb; }

        .sidebar-dropdown {
            position: absolute;
            top: 50px;
            left: 0;
            background-color: #ffffff;
            border: 1px solid #cbd5e1;
            border-radius: 10px;
            padding: 12px;
            width: 220px;
            display: none;
            flex-direction: column;
            gap: 6px;
            box-shadow: 0 10px 25px rgba(0, 0, 0, 0.15);
        }
        .sidebar-dropdown.activo { display: flex; }
        .sidebar-dropdown a {
            color: #334155;
            text-decoration: none;
            font-weight: 500;
            font-size: 14px;
            padding: 9px 12px;
            border-radius: 6px;
            transition: background-color 0.2s;
        }
        .sidebar-dropdown a:hover {
            background-color: #eff6ff;
            color: #2563eb;
            padding-left: 16px;
        }

        .btn-salir {
            background-color: #ef4444;
            color: #ffffff;
            padding: 9px 18px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 600;
            font-size: 13px;
        }
        .btn-salir:hover { background-color: #dc2626; }

        /* --- BANDAS --- */
        main { margin-top: 75px; }

        .banda {
            width: 100%;
            padding: 80px 20px;
            display: flex;
            justify-content: center;
        }

        .contenedor-interno {
            width: 100%;
            max-width: 1200px;
        }

        .reveal {
            opacity: 0;
            transform: translateY(40px);
            transition: all 0.8s ease-out;
        }
        .reveal.visible {
            opacity: 1;
            transform: translateY(0);
        }

        /* --- BANDA 1: HERO SLIDER --- */
        .banda-hero {
            padding: 0;
            height: 550px;
            background-color: #0f172a;
            position: relative;
            background-size: cover;
            background-position: center;
            display: flex;
            align-items: center;
            justify-content: center;
        }

        .hero-overlay {
            position: absolute;
            top: 0; left: 0; width: 100%; height: 100%;
            background: rgba(15, 23, 42, 0.15);
        }

        .hero-contenido {
            position: relative;
            z-index: 10;
            text-align: center;
            background: rgba(15, 23, 42, 0.82);
            padding: 40px 60px;
            border-radius: 14px;
            border: 1px solid rgba(255, 255, 255, 0.15);
            box-shadow: 0 15px 35px rgba(0,0,0,0.4);
        }
        .hero-contenido h1 { color: #ffffff; font-size: 40px; letter-spacing: 2px; margin-bottom: 10px; text-transform: uppercase; }
        .hero-contenido p { color: #60a5fa; font-size: 18px; font-weight: 500; }
        .hero-contenido .user { color: #94a3b8; font-size: 13px; margin-top: 15px; }

        /* --- BANDA 2: QUIÉNES SOMOS --- */
        .banda-oscura-institucional {
            background: linear-gradient(135deg, #0f172a 0%, #1e293b 100%);
        }
        .texto-institucional-box {
            background-color: #0b0f19;
            border: 1px solid rgba(255, 255, 255, 0.12);
            border-radius: 16px;
            padding: 50px;
            box-shadow: 0 15px 35px rgba(0,0,0,0.3);
            text-align: center;
            max-width: 950px;
            margin: 0 auto;
        }
        .texto-institucional-box h2 { color: #ffffff; font-size: 32px; margin-bottom: 20px; border-bottom: 3px solid #3b82f6; display: inline-block; padding-bottom: 5px;}
        .texto-institucional-box p { color: #cbd5e1; font-size: 16px; line-height: 1.8; margin-bottom: 15px; }

        /* --- BANDA 3: FLOTA CON VISOR DINÁMICO + "VER TODOS" --- */
        .banda-categorias { background-color: #f8fafc; border-top: 1px solid #e2e8f0; border-bottom: 1px solid #e2e8f0; }
        .titulo-seccion { text-align: center; font-size: 32px; color: #0f172a; margin-bottom: 30px; }

        .filtros-flota {
            display: flex;
            justify-content: center;
            gap: 12px;
            margin-bottom: 40px;
            flex-wrap: wrap;
        }
        .btn-filtro {
            background-color: #ffffff;
            border: 2px solid #cbd5e1;
            color: #334155;
            padding: 10px 20px;
            border-radius: 30px;
            font-weight: 600;
            font-size: 14px;
            cursor: pointer;
            transition: all 0.2s;
        }
        .btn-filtro:hover, .btn-filtro.activo {
            background-color: #2563eb;
            border-color: #2563eb;
            color: #ffffff;
            box-shadow: 0 4px 12px rgba(37, 99, 235, 0.3);
        }

        .visor-flota-card {
            background: #ffffff;
            border-radius: 16px;
            border: 1px solid #e2e8f0;
            box-shadow: 0 10px 30px rgba(0,0,0,0.06);
            overflow: hidden;
            display: grid;
            grid-template-columns: 1fr 1fr;
            align-items: center;
            max-width: 900px;
            margin: 0 auto;
        }
        @media(max-width: 768px) { .visor-flota-card { grid-template-columns: 1fr; } }

        .visor-imagen-container {
            width: 100%;
            height: 300px;
            background-color: #f1f5f9;
            display: flex;
            align-items: center;
            justify-content: center;
            padding: 20px;
        }
        .visor-imagen-container img {
            max-width: 100%;
            max-height: 100%;
            object-fit: contain;
        }
        .visor-info {
            padding: 40px;
            text-align: left;
        }
        .visor-info h3 { font-size: 26px; color: #0f172a; margin-bottom: 12px; }
        .visor-info p { font-size: 15px; color: #64748b; line-height: 1.6; margin-bottom: 25px; }
        .btn-ver-catalogo {
            background-color: #0f172a;
            color: #ffffff;
            padding: 12px 25px;
            border-radius: 8px;
            text-decoration: none;
            font-weight: 600;
            display: inline-block;
            transition: background-color 0.2s;
        }
        .btn-ver-catalogo:hover { background-color: #2563eb; }

        /* --- BANDA 4: SUCURSALES --- */
        .banda-sucursales {
            background-color: #0f172a;
            color: #ffffff;
        }

        .sucursal-principal-master {
            background: linear-gradient(135deg, #2563eb 0%, #1d4ed8 100%);
            border-radius: 14px;
            padding: 40px;
            text-align: center;
            margin-bottom: 50px;
            box-shadow: 0 10px 25px rgba(37, 99, 235, 0.3);
            border: 1px solid rgba(255,255,255,0.2);
        }
        .sucursal-principal-master span { background: rgba(255,255,255,0.2); padding: 5px 15px; border-radius: 20px; font-size: 12px; font-weight: bold; text-transform: uppercase; letter-spacing: 1px; }
        .sucursal-principal-master h3 { font-size: 30px; margin: 15px 0 10px 0; color: #ffffff; }
        .sucursal-principal-master p { font-size: 16px; color: #eff6ff; max-width: 600px; margin: 0 auto; line-height: 1.6; }

        .zona-bloque {
            margin-bottom: 45px;
        }
        .zona-bloque h3 {
            color: #f1f6fc;
            font-size: 22px;
            margin-bottom: 20px;
            border-left: 4px solid #eaedf3;
            padding-left: 10px;
        }

        .grid-sucursales-zona {
            display: grid;
            grid-template-columns: repeat(auto-fit, minmax(260px, 1fr));
            gap: 20px;
        }

        .tarjeta-sucursal {
            background: #1e293b;
            padding: 22px;
            border-radius: 10px;
            border: 1px solid rgba(255, 255, 255, 0.08);
            transition: transform 0.2s;
        }
        .tarjeta-sucursal:hover { transform: translateY(-3px); border-color: #3b82f6; }
        .tarjeta-sucursal h4 { color: #ffffff; font-size: 17px; margin-bottom: 6px; }
        .tarjeta-sucursal p { color: #94a3b8; font-size: 13px; line-height: 1.5; }
    </style>
</head>
<body>

    <!-- BARRA SUPERIOR -->
    <header>
        <div class="menu-container">
            <button class="btn-menu-toggle" onclick="toggleMenu()">
                ☰ Menú Opciones
            </button>
            <div id="sidebarMenu" class="sidebar-dropdown">
                <a href="#inicio">Inicio</a>
                <a href="#quienes-somos">¿Quiénes somos?</a>
                <a href="#flota">Nuestra Flota</a>
                <a href="#flota-lujo">Flota de Lujo</a>
                <a href="#sucursales">Red de Sucursales</a>
            </div>
        </div>
        <a href="logout.php" class="btn-salir">Cerrar Sesión</a>
    </header>

    <main>
        <!-- BANDA 1: HERO SLIDER -->
        <section id="inicio" class="banda banda-hero">
            <div class="hero-overlay"></div>
            <div class="hero-contenido reveal">
                <h1>LOS PIPIAUTOS</h1>
                <p>automóviles de calidad y gestión profesional</p>
                <div class="user">
                    Sesión activa: <?php echo htmlspecialchars($_SESSION['nombre'] . ' ' .$_SESSION['apellido']); ?>
                </div>
            </div>
        </section>

        <!-- BANDA 2: QUIÉNES SOMOS -->
        <section id="quienes-somos" class="banda banda-oscura-institucional">
            <div class="contenedor-interno">
                <div class="texto-institucional-box reveal">
                    <h2>¿Quiénes somos?</h2>
                    <p>
                        En <strong>Los Pipiautos</strong> nos consolidamos como la empresa líder en alquiler de vehículos en la Ciudad Autónoma de Buenos Aires, diseñada para responder con máxima agilidad y seguridad a las exigencias del mercado actual. Nuestra misión es brindar una experiencia transparente, moderna y eficiente tanto para clientes particulares como corporativos.
                    </p>
                    <p>
                        Contamos con una flota completamente normalizada y en constante renovación, respaldada por una infraestructura tecnológica robusta que permite gestionar reservas y verificar disponibilidad en nuestras sucursales de forma inmediata.
                    </p>
                </div>
            </div>
        </section>

        <!-- BANDA 3: FLOTA GENERAL -->
        <section id="flota" class="banda banda-categorias">
            <div class="contenedor-interno reveal">
                <h2 class="titulo-seccion">Nuestra Flota de Vehículos</h2>
                
                <div class="filtros-flota">
                    <button class="btn-filtro activo" onclick="cambiarCategoria('todos', this)">Ver Todos</button>
                    <button class="btn-filtro" onclick="cambiarCategoria('compacto', this)">Compactos Urbanos</button>
                    <button class="btn-filtro" onclick="cambiarCategoria('sedan', this)">Sedán Ejecutivo</button>
                    <button class="btn-filtro" onclick="cambiarCategoria('4x4', this)">Pick-Ups 4x4</button>
                    <button class="btn-filtro" onclick="cambiarCategoria('moto', this)">Motocicletas</button>
                </div>

                <div class="visor-flota-card" id="visorCard">
                    <div class="visor-imagen-container">
                        <img id="visorImg" src="foto_vehiculos/COROLLA_2023.jpg" alt="Vehículo">
                    </div>
                    <div class="visor-info">
                        <h3 id="visorTitulo">Flota General Los Pipiautos</h3>
                        <p id="visorDesc">Explorá nuestras categorías seleccionando los botones superiores. Unidades auditadas, con seguro total y listas para retirar en cualquier punto de CABA.</p>
                      <a href="catalogo_motos.php" class="btn-ver-catalogo">Ver Catálogo Completo</a>
                    </div>
                </div>
            </div>
        </section>

     <?php
// Consulta exacta para traer solo los vehículos de lujo de alta gama
try {
    $sql_lujo = "SELECT v.id_vehiculo, m.nombre_modelo, m.anio, m.imagen, v.patente, v.kilometraje_actual, v.estado 
                 FROM modelos m 
                 JOIN vehiculos v ON m.id_modelo = v.id_modelo 
                 WHERE m.nombre_modelo IN ('Ferrari 488 GTB', 'Lamborghini Urus', 'BMW M4 Competition', 'Audi R8 V10', 'Porsche 911 Turbo S', 'Mercedes-AMG G 63', 'Chevrolet Corvette C8', 'Aston Martin Vantage', 'Maserati Levante Trofeo', 'Bentley Continental GT')";
    $stmt_lujo = $conexion->prepare($sql_lujo);
    $stmt_lujo->execute();
    $vehiculos_lujo = $stmt_lujo->fetchAll(PDO::FETCH_ASSOC);
} catch (Exception $e) {
    $vehiculos_lujo = [];
}
?>

        <!-- BANDA EXCLUSIVA: FLOTA DE LUJO (+25 AÑOS) -->
        <section id="flota-lujo" class="banda" style="background: linear-gradient(135deg, #0b0f19 0%, #0f172a 100%); border-top: 3px solid #d4af37; padding: 80px 20px;">
            <div class="contenedor-interno reveal">
                <div style="text-align: center; margin-bottom: 50px;">
                    <span style="background: rgba(212, 175, 55, 0.15); color: #d4af37; padding: 6px 16px; border-radius: 20px; font-size: 12px; font-weight: bold; text-transform: uppercase; letter-spacing: 2px; border: 1px solid rgba(212, 175, 55, 0.3);">Exclusivo Alta Gama</span>
                    <h2 class="titulo-seccion" style="color: #ffffff; margin-top: 15px;">Flota de Lujo & Superdeportivos</h2>
                    <p style="color: #94a3b8; font-size: 15px; max-width: 600px; margin: 0 auto;">Unidades de alta performance reservadas exclusivamente para clientes con requisitos de edad verificados.</p>
                </div>

                <?php 
                $id_usr = $_SESSION['id_usuario'];
                $sql_check_lujo = "SELECT c.es_apto_lujo FROM clientes c WHERE c.id_cliente = :id_usr";
                $stmt_chk = $conexion->prepare($sql_check_lujo);
                $stmt_chk->execute([':id_usr' => $id_usr]);
                $datos_cliente = $stmt_chk->fetch(PDO::FETCH_ASSOC);
                $es_apto = $datos_cliente['es_apto_lujo'] ?? 0;
                ?>

                <?php if ($es_apto == 1): ?>
                    <!-- SI TIENE 25 AÑOS O MÁS: MUESTRA EL CATALOGO DE LUJO EN TARJETAS ESTILO VISOR -->
                    <div style="display: flex; flex-direction: column; gap: 30px; max-width: 900px; margin: 0 auto;">
                        <?php foreach ($vehiculos_lujo as $auto): ?>
                            <div style="background: #111827; border: 1px solid rgba(212, 175, 55, 0.3); border-radius: 16px; box-shadow: 0 10px 30px rgba(0,0,0,0.5); overflow: hidden; display: grid; grid-template-columns: 1fr 1fr; align-items: center;">
                                <div style="width: 100%; height: 260px; background-color: #000; display: flex; align-items: center; justify-content: center; padding: 15px;">
                                    <img src="foto_vehiculos/<?php echo htmlspecialchars($auto['imagen']); ?>" alt="<?php echo htmlspecialchars($auto['nombre_modelo']); ?>" style="max-width: 100%; max-height: 100%; object-fit: cover; border-radius: 8px;">
                                </div>
                                <div style="padding: 35px; text-align: left;">
                                    <span style="color: #d4af37; font-size: 12px; font-weight: bold; text-transform: uppercase; letter-spacing: 1px;">Modelo <?php echo $auto['anio']; ?></span>
                                    <h3 style="color: #ffffff; font-size: 26px; margin: 8px 0 12px 0;"><?php echo htmlspecialchars($auto['nombre_modelo']); ?></h3>
                                    <p style="color: #94a3b8; font-size: 14px; line-height: 1.6; margin-bottom: 25px;">Unidad auditada de alta performance, completamente asegurada y lista para retiro prioritario en sucursal central.</p>
                                    <div style="display: flex; align-items: center; justify-content: space-between;">
                                        <span style="color: #10b981; font-size: 13px; font-weight: 600; background: rgba(16, 185, 129, 0.1); padding: 5px 12px; border-radius: 20px;"><?php echo htmlspecialchars($auto['estado']); ?></span>
                                        <a href="reservar_lujo.php?id=<?php echo $auto['id_vehiculo']; ?>" style="background-color: #d4af37; color: #000000; padding: 10px 22px; border-radius: 8px; text-decoration: none; font-weight: bold; font-size: 14px; transition: background-color 0.2s;">Reservar Lujo</a>
                                    </div>
                                </div>
                            </div>
                        <?php endforeach; ?>
                    </div>
                <?php else: ?>
                    <!-- SI ES MENOR DE 25 AÑOS: BLOQUEO ESTETICO -->
                    <div style="background: linear-gradient(135deg, #1f2937 11%, #111827 100%); border: 1px solid rgba(239, 68, 68, 0.4); border-radius: 16px; padding: 40px; text-align: center; max-width: 700px; margin: 0 auto; box-shadow: 0 10px 30px rgba(0,0,0,0.4);">
                        <div style="font-size: 40px; margin-bottom: 15px;">🔒</div>
                        <h3 style="color: #ffffff; font-size: 24px; margin-bottom: 10px;">Acceso Restringido a Flota de Lujo</h3>
                        <p style="color: #94a3b8; font-size: 15px; line-height: 1.6; margin-bottom: 20px;">
                            Por políticas de seguridad de nuestra compañía y normativas de las aseguradoras para vehículos de alta gama, esta categoría se encuentra habilitada exclusivamente para conductores con <strong>25 años o más</strong>.
                        </p>
                        <span style="display: inline-block; background: rgba(239, 68, 68, 0.2); color: #f87171; padding: 8px 20px; border-radius: 20px; font-size: 13px; font-weight: 600;">Requisito etario no cumplido</span>
                    </div>
                <?php endif; ?>
            </div>
        </section>

        <!-- BANDA 4: SUCURSALES -->
        <section id="sucursales" class="banda banda-sucursales" style="background: linear-gradient(135deg, #92c9ee 0%, #779dd3 100%); border-top: 1px solid rgba(255, 255, 255, 0.1); padding: 90px 20px;">
            <div class="contenedor-interno reveal">
                <h2 class="titulo-seccion" style="color: #ffffff;">Nuestra Red de Sucursales en CABA</h2>
                <p style="text-align: center; color: #eef0f3; margin-bottom: 40px;">Conocé nuestra casa matriz y el despliegue completo de nuestras 48 sucursales.</p>

                <div class="sucursal-principal-master">
                    <span>Casa Matriz Oficial</span>
                    <h3>Sucursal Central Obelisco</h3>
                    <p>Av. Corrientes 1500, CABA — Centro de operaciones principal, atención comercial centralizada, gestión de flotas ejecutivas y soporte 24/7.</p>
                </div>

                <!-- ZONA CENTRO Y MICROCENTRO -->
                <div class="zona-bloque">
                    <h3>CABA - Zona Centro y Microcentro (12 Sucursales)</h3>
                    <div class="grid-sucursales-zona">
                        <div class="tarjeta-sucursal"><h4>Sucursal Congreso</h4><p>Av. Rivadavia 2100, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Retiro</h4><p>Av. Libertador 800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal San Nicolás</h4><p>Bartolomé Mitre 1200, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Tribunales</h4><p>Viamonte 1500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Monserrat</h4><p>Av. de Mayo 900, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Puerto Madero</h4><p>Juana Manso 1100, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal San Telmo</h4><p>Defensa 800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Plaza de Mayo</h4><p>Hipólito Yrigoyen 200, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Diagonal Norte</h4><p>Av. Roque Sáenz Peña 700, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Leandro N. Alem</h4><p>Av. Leandro N. Alem 600, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Córdoba Centro</h4><p>Av. Córdoba 1300, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Corrientes Peatonal</h4><p>Av. Corrientes 800, CABA</p></div>
                    </div>
                </div>

                <!-- ZONA NORTE -->
                <div class="zona-bloque">
                    <h3>CABA - Zona Norte (Palermo / Belgrano / Recoleta) (12 Sucursales)</h3>
                    <div class="grid-sucursales-zona">
                        <div class="tarjeta-sucursal"><h4>Sucursal Palermo Hollywood</h4><p>Gorriti 5500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Palermo Soho</h4><p>Honduras 4800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Belgrano R</h4><p>Av. Cabildo 2300, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Belgrano Barrancas</h4><p>Juramento 1800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Recoleta Mall</h4><p>Vicente López 2050, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Recoleta Las Heras</h4><p>Av. Las Heras 2400, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Núñez</h4><p>Av. Cabildo 4500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Colegiales</h4><p>Álvarez Thomas 800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Chacarita</h4><p>Av. Federico Lacroze 3900, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Villa Urquiza</h4><p>Av. Triunvirato 4500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Saavedra</h4><p>Arias 3500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Palermo Botánico</h4><p>Av. Santa Fe 3900, CABA</p></div>
                    </div>
                </div>

                <!-- ZONA OESTE -->
                <div class="zona-bloque">
                    <h3>CABA - Zona Oeste (Caballito / Flores / Almagro) (12 Sucursales)</h3>
                    <div class="grid-sucursales-zona">
                        <div class="tarjeta-sucursal"><h4>Sucursal Caballito Centro</h4><p>Av. Rivadavia 5100, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Caballito Parque</h4><p>Av. Directorio 800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Flores</h4><p>Av. Rivadavia 7000, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Almagro</h4><p>Av. Corrientes 4100, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Boedo</h4><p>Av. San Juan 3800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Caballito Norte</h4><p>Av. Gaona 2200, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Flores Norte</h4><p>Av. Gaona 3800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Parque Patricios</h4><p>Av. Caseros 3000, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Liniers</h4><p>Av. Rivadavia 11500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Mataderos</h4><p>Av. Juan Bautista Alberdi 6000, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Villa Luro</h4><p>Av. Rivadavia 9500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Versalles</h4><p>Arregui 6200, CABA</p></div>
                    </div>
                </div>

                <!-- ZONA SUR Y OTROS PUNTOS -->
                <div class="zona-bloque">
                    <h3>CABA - Zona Sur y Adyacencias (12 Sucursales)</h3>
                    <div class="grid-sucursales-zona">
                        <div class="tarjeta-sucursal"><h4>Sucursal Constitución</h4><p>Av. Brasil 1100, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Barracas</h4><p>Av. Montes de Oca 1200, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal La Boca</h4><p>Almirante Brown 800, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Pompeya</h4><p>Av. Sáenz 1000, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Parque Avellaneda</h4><p>Av. Directorio 4500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Villa Soldati</h4><p>Av. 27 de Febrero 2500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Lugano</h4><p>Av. Riestra 5500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Parque Chas</h4><p>Gándara 2500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Villa Devoto</h4><p>Av. Beiró 4500, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Villa del Parque</h4><p>Cuenca 3000, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Paternal</h4><p>Av. San Martín 2200, CABA</p></div>
                        <div class="tarjeta-sucursal"><h4>Sucursal Abasto</h4><p>Agüero 600, CABA</p></div>
                    </div>
                </div>

            </div>
        </section>
    </main>

    <!-- SCRIPTS -->
    <script>
        function toggleMenu() {
            document.getElementById('sidebarMenu').classList.toggle('activo');
        }
        window.onclick = function(e) {
            if (!e.target.matches('.btn-menu-toggle')) {
                const menu = document.getElementById('sidebarMenu');
                if (menu.classList.contains('activo')) menu.classList.remove('activo');
            }
        }

        // Slider del Banner Principal con Precarga para evitar el parpadeo
        const rutasBanner = [
            'fotos_decoracion/decoracion_1.jpg',
            'fotos_decoracion/decoracion_2.jpg',
            'fotos_decoracion/decoracion_3.jpg',
            'fotos_decoracion/decoracion_4.jpg',
            'fotos_decoracion/decoracion_5.jpg',
            'fotos_decoracion/decoracion_6.jpg'
        ];

        // Precargar imágenes en memoria
        const imagenesPrecarghadas = [];
        rutasBanner.forEach((ruta) => {
            const img = new Image();
            img.src = ruta;
            imagenesPrecarghadas.push(img);
        });

        let index = 0;
        const heroBg = document.getElementById('inicio');
        
        function cambiarBanner() {
            heroBg.style.backgroundImage = `url('${rutasBanner[index]}')`;
            index = (index + 1) % rutasBanner.length;
        }
        
        cambiarBanner();
        setInterval(cambiarBanner, 4000);

        // Sistema de Visor Interactivo con "Ver Todos"
        const datosFlota = {
            todos: {
                titulo: "Flota General Los Pipiautos",
                desc: "Explorá nuestras categorías seleccionando los botones superiores. Unidades auditadas, con seguro total y listas para retirar en cualquier punto de CABA.",
                img: "foto_vehiculos/COROLLA_2023.jpg"
            },
            compacto: {
                titulo: "Compactos Urbanos",
                desc: "Ideales para el tránsito diario de CABA, muy cómodos y con consumo ultra eficiente para fácil estacionamiento en la ciudad.",
                img: "foto_vehiculos/ETIOS_2021.jpg"
            },
            sedan: {
                titulo: "Sedán Ejecutivo",
                desc: "Confort superior, excelente andar en autopistas y gran capacidad de baúl para viajes de negocios o familia.",
                img: "foto_vehiculos/COROLLA_2023.jpg"
            },
            '4x4': {
                titulo: "Pick-Ups 4x4",
                desc: "Máxima potencia, tracción integral y robustez absoluta para cualquier tipo de carga o camino exigente.",
                img: "foto_vehiculos/HILUX_2024.jpg"
            },
            moto: {
                titulo: "Motocicletas",
                desc: "Agilidad absoluta para traslados veloces esquivando el tráfico porteño con total seguridad y bajo costo.",
                img: "foto_vehiculos/XR150L_2023.jpg"
            }
        };

        function cambiarCategoria(cat, boton) {
            const botones = document.querySelectorAll('.btn-filtro');
            botones.forEach(b => b.classList.remove('activo'));
            boton.classList.add('activo');

            const info = datosFlota[cat];
            document.getElementById('visorTitulo').innerText = info.titulo;
            document.getElementById('visorDesc').innerText = info.desc;
            document.getElementById('visorImg').src = info.img;
        }

        // Efecto Scroll Reveal
        function reveal() {
            var reveals = document.querySelectorAll(".reveal");
            for (var i = 0; i < reveals.length; i++) {
                var windowHeight = window.innerHeight;
                var elementTop = reveals[i].getBoundingClientRect().top;
                var elementVisible = 100;
                if (elementTop < windowHeight - elementVisible) {
                    reveals[i].classList.add("visible");
                }
            }
        }
        window.addEventListener("scroll", reveal);
        reveal();
    </script>

</body>
</html>