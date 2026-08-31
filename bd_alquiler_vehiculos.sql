-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 31-08-2026 a las 18:54:38
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
-- Estructura de tabla para la tabla `alquiler_extra`
--

CREATE TABLE `alquiler_extra` (
  `id_alquiler` int(11) NOT NULL,
  `id_extra` int(11) NOT NULL,
  `cantidad` int(11) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `categoria_vehiculo`
--

CREATE TABLE `categoria_vehiculo` (
  `id_categoria` int(11) NOT NULL,
  `nombre_categoria` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `tarifa_base_dia` decimal(10,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `checkin_checkout`
--

CREATE TABLE `checkin_checkout` (
  `id_registro` int(11) NOT NULL,
  `id_alquiler` int(11) NOT NULL,
  `id_empleado` int(11) NOT NULL,
  `tipo` enum('ENTREGA','DEVOLUCION') NOT NULL,
  `fecha_hora_real` datetime DEFAULT current_timestamp(),
  `kilometraje` int(11) NOT NULL,
  `nivel_combustible` enum('RESERVA','1/4','1/2','3/4','LLENO') NOT NULL,
  `observaciones` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `cliente`
--

CREATE TABLE `cliente` (
  `id_cliente` int(11) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `licencia_conducir` varchar(50) NOT NULL,
  `domicilio` varchar(200) NOT NULL,
  `foto_identificacion_url` varchar(255) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `empleado`
--

CREATE TABLE `empleado` (
  `id_empleado` int(11) NOT NULL,
  `id_sucursal` int(11) NOT NULL,
  `legajo` varchar(50) NOT NULL,
  `antiguedad` int(11) DEFAULT 0,
  `salario` decimal(12,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `foto_inspeccion`
--

CREATE TABLE `foto_inspeccion` (
  `id_foto` int(11) NOT NULL,
  `id_registro` int(11) NOT NULL,
  `foto_url` varchar(255) NOT NULL,
  `observacion_dano` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `log_auditoria`
--

CREATE TABLE `log_auditoria` (
  `id_log` int(11) NOT NULL,
  `id_usuario` int(11) NOT NULL,
  `fecha_hora` datetime DEFAULT current_timestamp(),
  `accion_realizada` varchar(100) NOT NULL,
  `detalle_operacion` text NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `multa_infraccion`
--

CREATE TABLE `multa_infraccion` (
  `id_multa` int(11) NOT NULL,
  `id_alquiler` int(11) NOT NULL,
  `monto` decimal(10,2) NOT NULL,
  `fecha_infraccion` date NOT NULL,
  `motivo` text NOT NULL,
  `estado_cobro` enum('PENDIENTE','COBRADO','APELADO') DEFAULT 'PENDIENTE'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `opcional_extra`
--

CREATE TABLE `opcional_extra` (
  `id_extra` int(11) NOT NULL,
  `nombre_extra` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL,
  `precio_diario` decimal(8,2) NOT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `pago`
--

CREATE TABLE `pago` (
  `id_pago` int(11) NOT NULL,
  `id_alquiler` int(11) NOT NULL,
  `monto` decimal(12,2) NOT NULL,
  `fecha_pago` datetime DEFAULT current_timestamp(),
  `metodo_pago` enum('BILLETERA_VIRTUAL','EFECTIVO','TARJETA_SUCURSAL','TRANSFERENCIA') NOT NULL,
  `estado_pago` enum('PENDIENTE','APROBADO','RECHAZADO') DEFAULT 'APROBADO'
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `reserva_alquiler`
--

CREATE TABLE `reserva_alquiler` (
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
-- Estructura de tabla para la tabla `sucursal`
--

CREATE TABLE `sucursal` (
  `id_sucursal` int(11) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `direccion` varchar(200) NOT NULL,
  `ciudad` varchar(100) NOT NULL,
  `activa` tinyint(1) DEFAULT 1
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `usuario`
--

CREATE TABLE `usuario` (
  `id_usuario` int(11) NOT NULL,
  `email` varchar(150) NOT NULL,
  `password_hash` varchar(255) NOT NULL,
  `nombre` varchar(100) NOT NULL,
  `apellido` varchar(100) NOT NULL,
  `cuil` varchar(20) NOT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `tipo_rol` enum('CLIENTE','EMPLEADO_PLAYA','ADMINISTRATIVO','SUPERVISOR','ADMINISTRADOR') NOT NULL,
  `intentos_fallidos` int(11) DEFAULT 0,
  `bloqueado` tinyint(1) DEFAULT 0,
  `fecha_creacion` datetime DEFAULT current_timestamp()
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `vehiculo`
--

CREATE TABLE `vehiculo` (
  `id_vehiculo` int(11) NOT NULL,
  `patente` varchar(15) NOT NULL,
  `marca` varchar(50) NOT NULL,
  `modelo` varchar(50) NOT NULL,
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
-- Indices de la tabla `alquiler_extra`
--
ALTER TABLE `alquiler_extra`
  ADD PRIMARY KEY (`id_alquiler`,`id_extra`),
  ADD KEY `fk_ae_extra` (`id_extra`);

--
-- Indices de la tabla `categoria_vehiculo`
--
ALTER TABLE `categoria_vehiculo`
  ADD PRIMARY KEY (`id_categoria`),
  ADD UNIQUE KEY `nombre_categoria` (`nombre_categoria`);

--
-- Indices de la tabla `checkin_checkout`
--
ALTER TABLE `checkin_checkout`
  ADD PRIMARY KEY (`id_registro`),
  ADD KEY `fk_checkin_alquiler` (`id_alquiler`),
  ADD KEY `fk_checkin_empleado` (`id_empleado`);

--
-- Indices de la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD PRIMARY KEY (`id_cliente`),
  ADD UNIQUE KEY `dni` (`dni`);

--
-- Indices de la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD PRIMARY KEY (`id_empleado`),
  ADD UNIQUE KEY `legajo` (`legajo`),
  ADD KEY `fk_empleado_sucursal` (`id_sucursal`);

--
-- Indices de la tabla `foto_inspeccion`
--
ALTER TABLE `foto_inspeccion`
  ADD PRIMARY KEY (`id_foto`),
  ADD KEY `fk_foto_registro` (`id_registro`);

--
-- Indices de la tabla `log_auditoria`
--
ALTER TABLE `log_auditoria`
  ADD PRIMARY KEY (`id_log`),
  ADD KEY `fk_log_usuario` (`id_usuario`);

--
-- Indices de la tabla `multa_infraccion`
--
ALTER TABLE `multa_infraccion`
  ADD PRIMARY KEY (`id_multa`),
  ADD KEY `fk_multa_alquiler` (`id_alquiler`);

--
-- Indices de la tabla `opcional_extra`
--
ALTER TABLE `opcional_extra`
  ADD PRIMARY KEY (`id_extra`);

--
-- Indices de la tabla `pago`
--
ALTER TABLE `pago`
  ADD PRIMARY KEY (`id_pago`),
  ADD KEY `fk_pago_alquiler` (`id_alquiler`);

--
-- Indices de la tabla `reserva_alquiler`
--
ALTER TABLE `reserva_alquiler`
  ADD PRIMARY KEY (`id_alquiler`),
  ADD KEY `fk_alquiler_cliente` (`id_cliente`),
  ADD KEY `fk_alquiler_vehiculo` (`id_vehiculo`),
  ADD KEY `fk_alquiler_sucursal_retiro` (`id_sucursal_retiro`),
  ADD KEY `fk_alquiler_sucursal_devolucion` (`id_sucursal_devolucion`);

--
-- Indices de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  ADD PRIMARY KEY (`id_sucursal`);

--
-- Indices de la tabla `usuario`
--
ALTER TABLE `usuario`
  ADD PRIMARY KEY (`id_usuario`),
  ADD UNIQUE KEY `email` (`email`),
  ADD UNIQUE KEY `cuil` (`cuil`);

--
-- Indices de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD PRIMARY KEY (`id_vehiculo`),
  ADD UNIQUE KEY `patente` (`patente`),
  ADD KEY `fk_vehiculo_categoria` (`id_categoria`),
  ADD KEY `fk_vehiculo_sucursal` (`id_sucursal_actual`);

--
-- AUTO_INCREMENT de las tablas volcadas
--

--
-- AUTO_INCREMENT de la tabla `categoria_vehiculo`
--
ALTER TABLE `categoria_vehiculo`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `checkin_checkout`
--
ALTER TABLE `checkin_checkout`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `foto_inspeccion`
--
ALTER TABLE `foto_inspeccion`
  MODIFY `id_foto` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `log_auditoria`
--
ALTER TABLE `log_auditoria`
  MODIFY `id_log` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `multa_infraccion`
--
ALTER TABLE `multa_infraccion`
  MODIFY `id_multa` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `opcional_extra`
--
ALTER TABLE `opcional_extra`
  MODIFY `id_extra` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `pago`
--
ALTER TABLE `pago`
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `reserva_alquiler`
--
ALTER TABLE `reserva_alquiler`
  MODIFY `id_alquiler` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `sucursal`
--
ALTER TABLE `sucursal`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuario`
--
ALTER TABLE `usuario`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  MODIFY `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT;

--
-- Restricciones para tablas volcadas
--

--
-- Filtros para la tabla `alquiler_extra`
--
ALTER TABLE `alquiler_extra`
  ADD CONSTRAINT `fk_ae_alquiler` FOREIGN KEY (`id_alquiler`) REFERENCES `reserva_alquiler` (`id_alquiler`) ON DELETE CASCADE,
  ADD CONSTRAINT `fk_ae_extra` FOREIGN KEY (`id_extra`) REFERENCES `opcional_extra` (`id_extra`);

--
-- Filtros para la tabla `checkin_checkout`
--
ALTER TABLE `checkin_checkout`
  ADD CONSTRAINT `fk_checkin_alquiler` FOREIGN KEY (`id_alquiler`) REFERENCES `reserva_alquiler` (`id_alquiler`),
  ADD CONSTRAINT `fk_checkin_empleado` FOREIGN KEY (`id_empleado`) REFERENCES `empleado` (`id_empleado`);

--
-- Filtros para la tabla `cliente`
--
ALTER TABLE `cliente`
  ADD CONSTRAINT `fk_cliente_usuario` FOREIGN KEY (`id_cliente`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `empleado`
--
ALTER TABLE `empleado`
  ADD CONSTRAINT `fk_empleado_sucursal` FOREIGN KEY (`id_sucursal`) REFERENCES `sucursal` (`id_sucursal`),
  ADD CONSTRAINT `fk_empleado_usuario` FOREIGN KEY (`id_empleado`) REFERENCES `usuario` (`id_usuario`) ON DELETE CASCADE;

--
-- Filtros para la tabla `foto_inspeccion`
--
ALTER TABLE `foto_inspeccion`
  ADD CONSTRAINT `fk_foto_registro` FOREIGN KEY (`id_registro`) REFERENCES `checkin_checkout` (`id_registro`) ON DELETE CASCADE;

--
-- Filtros para la tabla `log_auditoria`
--
ALTER TABLE `log_auditoria`
  ADD CONSTRAINT `fk_log_usuario` FOREIGN KEY (`id_usuario`) REFERENCES `usuario` (`id_usuario`);

--
-- Filtros para la tabla `multa_infraccion`
--
ALTER TABLE `multa_infraccion`
  ADD CONSTRAINT `fk_multa_alquiler` FOREIGN KEY (`id_alquiler`) REFERENCES `reserva_alquiler` (`id_alquiler`);

--
-- Filtros para la tabla `pago`
--
ALTER TABLE `pago`
  ADD CONSTRAINT `fk_pago_alquiler` FOREIGN KEY (`id_alquiler`) REFERENCES `reserva_alquiler` (`id_alquiler`);

--
-- Filtros para la tabla `reserva_alquiler`
--
ALTER TABLE `reserva_alquiler`
  ADD CONSTRAINT `fk_alquiler_cliente` FOREIGN KEY (`id_cliente`) REFERENCES `cliente` (`id_cliente`),
  ADD CONSTRAINT `fk_alquiler_sucursal_devolucion` FOREIGN KEY (`id_sucursal_devolucion`) REFERENCES `sucursal` (`id_sucursal`),
  ADD CONSTRAINT `fk_alquiler_sucursal_retiro` FOREIGN KEY (`id_sucursal_retiro`) REFERENCES `sucursal` (`id_sucursal`),
  ADD CONSTRAINT `fk_alquiler_vehiculo` FOREIGN KEY (`id_vehiculo`) REFERENCES `vehiculo` (`id_vehiculo`);

--
-- Filtros para la tabla `vehiculo`
--
ALTER TABLE `vehiculo`
  ADD CONSTRAINT `fk_vehiculo_categoria` FOREIGN KEY (`id_categoria`) REFERENCES `categoria_vehiculo` (`id_categoria`),
  ADD CONSTRAINT `fk_vehiculo_sucursal` FOREIGN KEY (`id_sucursal_actual`) REFERENCES `sucursal` (`id_sucursal`);
COMMIT;

/*!40101 SET CHARACTER_SET_CLIENT=@OLD_CHARACTER_SET_CLIENT */;
/*!40101 SET CHARACTER_SET_RESULTS=@OLD_CHARACTER_SET_RESULTS */;
/*!40101 SET COLLATION_CONNECTION=@OLD_COLLATION_CONNECTION */;
