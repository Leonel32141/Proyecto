-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 08-09-2026 a las 16:25:38
-- Versión del servidor: 10.4.32-MariaDB
-- Versión de PHP: 8.2.12

SET SQL_MODE = "NO_AUTO_VALUE_ON_ZERO";
START TRANSACTION;
SET time_zone = "+00:00";


/*!40101 SET @OLD_CHARACTER_SET_CLIENT=@@CHARACTER_SET_CLIENT */;
/*!40101 SET @OLD_CHARACTER_SET_RESULTS=@@CHARACTER_SET_RESULTS */;
/*!40101 SET @OLD_COLLATION_CONNECTION=@@COLLATION_CONNECTION */;
/*!40101 SET NAMES utf8mb4 */;

--
-- Base de datos: `bd_alquiler_vehiculos`
--

DELIMITER $$
--
-- Procedimientos
--
CREATE DEFINER=`root`@`localhost` PROCEDURE `CalcularTotalRecaudadoPorMetodo` ()   BEGIN
    SELECT 
        metodo_pago, 
        SUM(monto) AS total_recaudado 
    FROM pago 
    WHERE estado_pago = 'APROBADO' 
    GROUP BY metodo_pago;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarClientes` ()   BEGIN
    SELECT 
        u.nombre, 
        u.apellido, 
        u.email, 
        u.telefono, 
        c.dni, 
        c.licencia_conducir 
    FROM cliente c 
    INNER JOIN usuario u ON c.id_cliente = u.id_usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ListarEmpleadosPorSucursal` ()   BEGIN
    SELECT 
        e.legajo, 
        u.nombre, 
        u.apellido, 
        s.nombre AS nombre_sucursal, 
        e.salario 
    FROM empleado e 
    INNER JOIN usuario u ON e.id_empleado = u.id_usuario 
    INNER JOIN sucursal s ON e.id_sucursal = s.id_sucursal;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerAlquileresEnCurso` ()   BEGIN
    SELECT 
        r.id_alquiler, 
        u.nombre AS nombre_cliente, 
        u.apellido AS apellido_cliente, 
        v.patente, 
        v.modelo, 
        r.fecha_inicio_pautada, 
        r.fecha_fin_pautada 
    FROM reserva_alquiler r 
    INNER JOIN cliente c ON r.id_cliente = c.id_cliente 
    INNER JOIN usuario u ON c.id_cliente = u.id_usuario 
    INNER JOIN vehiculo v ON r.id_vehiculo = v.id_vehiculo 
    WHERE r.estado_reserva = 'EN_CURSO';
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `ObtenerVehiculosDisponibles` ()   BEGIN
    SELECT 
        v.patente, 
        v.marca, 
        v.modelo, 
        c.nombre_categoria, 
        c.tarifa_base_dia 
    FROM vehiculo v 
    INNER JOIN categoria_vehiculo c ON v.id_categoria = c.id_categoria 
    WHERE v.estado = 'DISPONIBLE';
END$$

DELIMITER ;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquileres`
--

CREATE TABLE `alquileres` (
  `id_alquiler` int(11) NOT NULL,
  `id_cliente` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `id_sucursal_retiro` int(11) NOT NULL,
  `id_sucursal_devolucion` int(11) NOT NULL,
  `fecha_reserva` datetime DEFAULT current_timestamp(),
  `fecha_inicio_pautada` datetime NOT NULL,
  `fecha_fin_pautada` datetime NOT NULL,
  `es_oneway` tinyint(1) DEFAULT 0,
  `monto_total` decimal(12,2) NOT NULL,
  `monto_sena` decimal(12,2) NOT NULL,
  `estado_reserva` enum('RESERVADA','EN_CURSO','FINALIZADA','CANCELADA') DEFAULT 'RESERVADA'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `alquileres_extras`
--

CREATE TABLE `alquileres_extras` (
  `id_alquiler` int(11) NOT NULL,
  `id_extra` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categorias_vehiculos`
--

CREATE TABLE `categorias_vehiculos` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `tarifa_base_dia` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `checkins_checkouts`
--

CREATE TABLE `checkins_checkouts` (
  `id_registro` int(11) NOT NULL,
  `id_alquiler` int(11) NOT NULL,
  `id_usuario_empleado` int(11) NOT NULL,
  `tipo` enum('ENTREGA','DEVOLUCION') NOT NULL,
  `fecha_hora_real` datetime DEFAULT current_timestamp(),
  `kilometraje` int(11) NOT NULL,
  `nivel_combustible` enum('RESERVA','1/4','1/2','3/4','LLENO') NOT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `licencia_conducir` varchar(50) NOT NULL,
  `domicilio` varchar(200) NOT NULL,
  `foto_identificacion_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `fotos_inspecciones`
--

CREATE TABLE `fotos_inspecciones` (
  `id_foto` int(11) NOT NULL,
  `id_registro` int(11) NOT NULL,
  `foto_url` varchar(255) NOT NULL,
  `observacion_dano` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `mantenimientos`
--

CREATE TABLE `mantenimientos` (
  `id_mantenimiento` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `tipo_mantenimiento` enum('PREVENTIVO','CORRECTIVO','SERVICE_OFICIAL','REPARACION_MECANICA') NOT NULL,
  `fecha_ingreso` date NOT NULL,
  `fecha_egreso_estimada` date DEFAULT NULL,
  `costo_total` decimal(10,2) DEFAULT 0.00,
  `taller_mecanico` varchar(100) DEFAULT NULL,
  `detalle_trabajo` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `marcas`
--

CREATE TABLE `marcas` (
  `id_marca` int(11) NOT NULL,
  `nombre_marca` varchar(50) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pagos`
--

CREATE TABLE `metodos_pagos` (
  `id_metodo_pago` int(11) NOT NULL,
  `nombre_metodo` varchar(50) NOT NULL,
  `requiere_comprobante` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelos`
--

CREATE TABLE `modelos` (
  `id_modelo` int(11) NOT NULL,
  `id_marca` int(11) NOT NULL,
  `nombre_modelo` varchar(50) NOT NULL,
  `anio` int(11) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcionales_extras`
--

CREATE TABLE `opcionales_extras` (
  `id_extra` int(11) NOT NULL,
  `nombre_extra` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_diario` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pagos`
--

CREATE TABLE `pagos` (
  `id_pago` int(11) NOT NULL,
  `id_alquiler` int(11) NOT NULL,
  `id_metodo_pago` int(11) NOT NULL,
  `monto` decimal(12,2) NOT NULL,
  `fecha_pago` datetime DEFAULT current_timestamp(),
  `estado_pago` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'APROBADO',
  `numero_comprobante` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `sucursales`
--

CREATE TABLE `sucursales` (
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `activa` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `traslados_vehiculos`
--

CREATE TABLE `traslados_vehiculos` (
  `id_traslado` int(11) NOT NULL,
  `id_vehiculo` int(11) NOT NULL,
  `id_sucursal_origen` int(11) NOT NULL,
  `id_sucursal_destino` int(11) NOT NULL,
  `id_usuario_chofer` int(11) NOT NULL,
  `fecha_salida` datetime DEFAULT current_timestamp(),
  `fecha_llegada` datetime DEFAULT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuarios`
--

CREATE TABLE `usuarios` (
  `id_usuario` int(11) NOT NULL,
  `id_rol` int(11) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `cuil` varchar(20) NOT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `intentos_fallidos` int(11) DEFAULT 0,
  `cuenta_bloqueada` tinyint(1) DEFAULT 0,
  `fecha_creacion` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculos`
--

CREATE TABLE `vehiculos` (
  `id_vehiculo` int(11) NOT NULL,
  `patente` varchar(15) NOT NULL,
  `id_modelo` int(11) NOT NULL,
  `id_categoria` int(11) NOT NULL,
  `id_sucursal_actual` int(11) NOT NULL,
  `kilometraje_actual` int(11) DEFAULT 0,
  `estado` enum('DISPONIBLE','ALQUILADO','EN_MANTENIMIENTO','REPARACION') DEFAULT 'DISPONIBLE',
  `vencimiento_seguro` date NOT NULL,
  `vencimiento_patente` date NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Índices para tablas volcadas
--

--
-- Indices de la tabla `alquileres`
--
ALTER TABLE `alquileres`
  ADD PRIMARY KEY (`id_alquiler`),
  ADD KEY `fk_alquileres_clientes` (`id_cliente`),
  ADD KEY `fk_alquileres_vehiculos` (`id_vehiculo`),
  ADD KEY `fk_alquileres_sucursal_retiro` (`id_sucursal_retiro`),
  ADD KEY `fk_alquileres_sucursal_devolucion` (`id_sucursal_devolucion`);

--
-- Indices de la tabla `alquileres_extras`
--
ALTER TABLE `alquileres_extras`
  ADD PRIMARY KEY (`id_alquiler`,`id_extra`),
  ADD KEY `fk_ae_extras` (`id_extra`);

--
-- Indices de la tabla `categorias_vehiculos`
--
ALTER TABLE `categorias_vehiculos`
  ADD PRIMARY KEY (`id_categoria`),
  ADD UNIQUE KEY `nombre_categoria` (`nombre_categoria`);

--
-- Indices de la tabla `checkins_checkouts`
--
ALTER TABLE `checkins_checkouts`
  ADD PRIMARY KEY (`id_registro`),
  ADD KEY `fk_checkins_alquileres` (`id_alquiler`),
  ADD KEY `fk_checkins_usuarios` (`id_usuario_empleado`);

--
-- Indices de la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `fotos_inspecciones`
--
ALTER TABLE `fotos_inspecciones`
  ADD PRIMARY KEY (`id_foto`),
  ADD KEY `fk_fotos_checkins` (`id_registro`);

--
-- Indices de la tabla `mantenimientos`
--
ALTER TABLE `mantenimientos`
  ADD PRIMARY KEY (`id_mantenimiento`),
  ADD KEY `fk_mantenimientos_vehiculos` (`id_vehiculo`);

--
-- Indices de la tabla `marcas`
--
ALTER TABLE `marcas`
  ADD PRIMARY KEY (`id_marca`),
  ADD UNIQUE KEY `nombre_marca` (`nombre_marca`);

--
-- Indices de la tabla `metodos_pagos`
--
ALTER TABLE `metodos_pagos`
  ADD PRIMARY KEY (`id_metodo_pago`),
  ADD UNIQUE KEY `nombre_metodo` (`nombre_metodo`);

--
-- Indices de la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD PRIMARY KEY (`id_modelo`),
  ADD KEY `fk_modelos_marcas` (`id_marca`);

--
-- Indices de la tabla `opcionales_extras`
--
ALTER TABLE `opcionales_extras`
  ADD PRIMARY KEY (`id_extra`);

--
-- Indices de la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `fk_pagos_alquileres` (`id_alquiler`),
  ADD KEY `fk_pagos_metodos` (`id_metodo_pago`);

--
-- Indices de la tabla `roles`
--
ALTER TABLE `roles`
  ADD PRIMARY KEY (`id_rol`),
  ADD UNIQUE KEY `nombre_rol` (`nombre_rol`);

--
-- Indices de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  ADD PRIMARY KEY (`id_sucursal`);

--
-- Indices de la tabla `traslados_vehiculos`
--
ALTER TABLE `traslados_vehiculos`
  ADD PRIMARY KEY (`id_traslado`),
  ADD KEY `fk_traslados_vehiculos` (`id_vehiculo`),
  ADD KEY `fk_traslados_origen` (`id_sucursal_origen`),
  ADD KEY `fk_traslados_destino` (`id_sucursal_destino`),
  ADD KEY `fk_traslados_chofer` (`id_usuario_chofer`);

--
-- Indices de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `cuil` (`cuil`),
  ADD KEY `fk_usuarios_roles` (`id_rol`);

--
-- Indices de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD PRIMARY KEY (`id_vehiculo`),
  ADD UNIQUE KEY `patente` (`patente`),
  ADD KEY `fk_vehiculos_modelos` (`id_modelo`),
  ADD KEY `fk_vehiculos_categorias` (`id_categoria`),
  ADD KEY `fk_vehiculos_sucursales` (`id_sucursal_actual`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `alquileres`
--
ALTER TABLE `alquileres`
  MODIFY `id_alquiler` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `categorias_vehiculos`
--
ALTER TABLE `categorias_vehiculos`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `checkins_checkouts`
--
ALTER TABLE `checkins_checkouts`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `fotos_inspecciones`
--
ALTER TABLE `fotos_inspecciones`
  MODIFY `id_foto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `mantenimientos`
--
ALTER TABLE `mantenimientos`
  MODIFY `id_mantenimiento` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `marcas`
--
ALTER TABLE `marcas`
  MODIFY `id_marca` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `metodos_pagos`
--
ALTER TABLE `metodos_pagos`
  MODIFY `id_metodo_pago` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `modelos`
--
ALTER TABLE `modelos`
  MODIFY `id_modelo` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `opcionales_extras`
--
ALTER TABLE `opcionales_extras`
  MODIFY `id_extra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `traslados_vehiculos`
--
ALTER TABLE `traslados_vehiculos`
  MODIFY `id_traslado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alquileres`
--
ALTER TABLE `alquileres`
  ADD CONSTRAINT `fk_alquileres_clientes` FOREIGN KEY (`id_cliente`) REFERENCES `clientes` (`id_cliente`),
  ADD CONSTRAINT `fk_alquileres_sucursal_devolucion` FOREIGN KEY (`id_sucursal_devolucion`) REFERENCES `sucursales` (`id_sucursal`),
  ADD CONSTRAINT `fk_alquileres_sucursal_retiro` FOREIGN KEY (`id_sucursal_retiro`) REFERENCES `sucursales` (`id_sucursal`),
  ADD CONSTRAINT `fk_alquileres_vehiculos` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`);

--
-- Filtros para la tabla `alquileres_extras`
--
ALTER TABLE `alquileres_extras`
  ADD CONSTRAINT `fk_ae_alquileres` FOREIGN KEY (`id_alquiler`) REFERENCES `alquileres` (`id_alquiler`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ae_extras` FOREIGN KEY (`id_extra`) REFERENCES `opcionales_extras` (`id_extra`);

--
-- Filtros para la tabla `checkins_checkouts`
--
ALTER TABLE `checkins_checkouts`
  ADD CONSTRAINT `fk_checkins_alquileres` FOREIGN KEY (`id_alquiler`) REFERENCES `alquileres` (`id_alquiler`),
  ADD CONSTRAINT `fk_checkins_usuarios` FOREIGN KEY (`id_usuario_empleado`) REFERENCES `usuarios` (`id_usuario`);

--
-- Filtros para la tabla `clientes`
--
ALTER TABLE `clientes`
  ADD CONSTRAINT `fk_clientes_usuarios` FOREIGN KEY (`id_cliente`) REFERENCES `usuarios` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `fotos_inspecciones`
--
ALTER TABLE `fotos_inspecciones`
  ADD CONSTRAINT `fk_fotos_checkins` FOREIGN KEY (`id_registro`) REFERENCES `checkins_checkouts` (`id_registro`) ON DELETE CASCADE;

--
-- Filtros para la tabla `mantenimientos`
--
ALTER TABLE `mantenimientos`
  ADD CONSTRAINT `fk_mantenimientos_vehiculos` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`);

--
-- Filtros para la tabla `modelos`
--
ALTER TABLE `modelos`
  ADD CONSTRAINT `fk_modelos_marcas` FOREIGN KEY (`id_marca`) REFERENCES `marcas` (`id_marca`);

--
-- Filtros para la tabla `pagos`
--
ALTER TABLE `pagos`
  ADD CONSTRAINT `fk_pagos_alquileres` FOREIGN KEY (`id_alquiler`) REFERENCES `alquileres` (`id_alquiler`),
  ADD CONSTRAINT `fk_pagos_metodos` FOREIGN KEY (`id_metodo_pago`) REFERENCES `metodos_pagos` (`id_metodo_pago`);

--
-- Filtros para la tabla `traslados_vehiculos`
--
ALTER TABLE `traslados_vehiculos`
  ADD CONSTRAINT `fk_traslados_chofer` FOREIGN KEY (`id_usuario_chofer`) REFERENCES `usuarios` (`id_usuario`),
  ADD CONSTRAINT `fk_traslados_destino` FOREIGN KEY (`id_sucursal_destino`) REFERENCES `sucursales` (`id_sucursal`),
  ADD CONSTRAINT `fk_traslados_origen` FOREIGN KEY (`id_sucursal_origen`) REFERENCES `sucursales` (`id_sucursal`),
  ADD CONSTRAINT `fk_traslados_vehiculos` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculos` (`id_vehiculo`);

--
-- Filtros para la tabla `usuarios`
--
ALTER TABLE `usuarios`
  ADD CONSTRAINT `fk_usuarios_roles` FOREIGN KEY (`id_rol`) REFERENCES `roles` (`id_rol`);

--
-- Filtros para la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  ADD CONSTRAINT `fk_vehiculos_categorias` FOREIGN KEY (`id_categoria`) REFERENCES `categorias_vehiculos` (`id_categoria`),
  ADD CONSTRAINT `fk_vehiculos_modelos` FOREIGN KEY (`id_modelo`) REFERENCES `modelos` (`id_modelo`),
  ADD CONSTRAINT `fk_vehiculos_sucursales` FOREIGN KEY (`id_sucursal_actual`) REFERENCES `sucursales` (`id_sucursal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
