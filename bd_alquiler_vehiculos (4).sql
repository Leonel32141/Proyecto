-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 02-11-2026 a las 22:08:56
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
CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_alquiler_extra_por_id` (IN `p_id_alquiler` INT, IN `p_id_extra` INT)   BEGIN
    SELECT * FROM alquileres_extras WHERE id_alquiler = p_id_alquiler AND id_extra = p_id_extra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_alquiler_por_id` (IN `p_id_alquiler` INT)   BEGIN
    SELECT * FROM alquileres WHERE id_alquiler = p_id_alquiler;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_categoria_por_id` (IN `p_id_categoria` INT)   BEGIN
    SELECT * FROM categorias_vehiculos WHERE id_categoria = p_id_categoria;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_checkin_checkout_por_id` (IN `p_id_registro` INT)   BEGIN
    SELECT * FROM checkins_checkouts WHERE id_registro = p_id_registro;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_cliente_por_id` (IN `p_id_cliente` INT)   BEGIN
    SELECT * FROM clientes WHERE id_cliente = p_id_cliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_foto_inspeccion_por_id` (IN `p_id_foto` INT)   BEGIN
    SELECT * FROM fotos_inspecciones WHERE id_foto = p_id_foto;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_mantenimiento_por_id` (IN `p_id_mantenimiento` INT)   BEGIN
    SELECT * FROM mantenimientos WHERE id_mantenimiento = p_id_mantenimiento;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_marca_por_id` (IN `p_id_marca` INT)   BEGIN
    SELECT * FROM marcas WHERE id_marca = p_id_marca;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_metodo_pago_por_id` (IN `p_id_metodo_pago` INT)   BEGIN
    SELECT * FROM metodos_pagos WHERE id_metodo_pago = p_id_metodo_pago;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_modelo_por_id` (IN `p_id_modelo` INT)   BEGIN
    SELECT * FROM modelos WHERE id_modelo = p_id_modelo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_opcional_extra_por_id` (IN `p_id_extra` INT)   BEGIN
    SELECT * FROM opcionales_extras WHERE id_extra = p_id_extra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_pago_por_id` (IN `p_id_pago` INT)   BEGIN
    SELECT * FROM pagos WHERE id_pago = p_id_pago;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_rol_por_id` (IN `p_id_rol` INT)   BEGIN
    SELECT * FROM roles WHERE id_rol = p_id_rol;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_sucursal_por_id` (IN `p_id_sucursal` INT)   BEGIN
    SELECT * FROM sucursales WHERE id_sucursal = p_id_sucursal;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_traslado_por_id` (IN `p_id_traslado` INT)   BEGIN
    SELECT * FROM traslados_vehiculos WHERE id_traslado = p_id_traslado;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_usuario_por_id` (IN `p_id_usuario` INT)   BEGIN
    SELECT * FROM usuarios WHERE id_usuario = p_id_usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_buscar_vehiculo_por_id` (IN `p_id_vehiculo` INT)   BEGIN
    SELECT * FROM vehiculos WHERE id_vehiculo = p_id_vehiculo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_alquiler` (IN `p_id_alquiler` INT)   BEGIN
    DELETE FROM alquileres WHERE id_alquiler = p_id_alquiler;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_alquiler_extra` (IN `p_id_alquiler` INT, IN `p_id_extra` INT)   BEGIN
    DELETE FROM alquileres_extras WHERE id_alquiler = p_id_alquiler AND id_extra = p_id_extra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_categoria` (IN `p_id_categoria` INT)   BEGIN
    DELETE FROM categorias_vehiculos WHERE id_categoria = p_id_categoria;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_checkin_checkout` (IN `p_id_registro` INT)   BEGIN
    DELETE FROM checkins_checkouts WHERE id_registro = p_id_registro;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_cliente` (IN `p_id_cliente` INT)   BEGIN
    DELETE FROM clientes WHERE id_cliente = p_id_cliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_foto_inspeccion` (IN `p_id_foto` INT)   BEGIN
    DELETE FROM fotos_inspecciones WHERE id_foto = p_id_foto;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_mantenimiento` (IN `p_id_mantenimiento` INT)   BEGIN
    DELETE FROM mantenimientos WHERE id_mantenimiento = p_id_mantenimiento;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_marca` (IN `p_id_marca` INT)   BEGIN
    DELETE FROM marcas WHERE id_marca = p_id_marca;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_metodo_pago` (IN `p_id_metodo_pago` INT)   BEGIN
    DELETE FROM metodos_pagos WHERE id_metodo_pago = p_id_metodo_pago;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_modelo` (IN `p_id_modelo` INT)   BEGIN
    DELETE FROM modelos WHERE id_modelo = p_id_modelo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_opcional_extra` (IN `p_id_extra` INT)   BEGIN
    DELETE FROM opcionales_extras WHERE id_extra = p_id_extra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_pago` (IN `p_id_pago` INT)   BEGIN
    DELETE FROM pagos WHERE id_pago = p_id_pago;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_rol` (IN `p_id_rol` INT)   BEGIN
    DELETE FROM roles WHERE id_rol = p_id_rol;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_sucursal` (IN `p_id_sucursal` INT)   BEGIN
    DELETE FROM sucursales WHERE id_sucursal = p_id_sucursal;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_traslado` (IN `p_id_traslado` INT)   BEGIN
    DELETE FROM traslados_vehiculos WHERE id_traslado = p_id_traslado;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_usuario` (IN `p_id_usuario` INT)   BEGIN
    DELETE FROM usuarios WHERE id_usuario = p_id_usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_eliminar_vehiculo` (IN `p_id_vehiculo` INT)   BEGIN
    DELETE FROM vehiculos WHERE id_vehiculo = p_id_vehiculo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_alquiler` (IN `p_id_cliente` INT, IN `p_id_vehiculo` INT, IN `p_id_sucursal_retiro` INT, IN `p_id_sucursal_devolucion` INT, IN `p_fecha_reserva` DATETIME, IN `p_fecha_inicio_pautada` DATETIME, IN `p_fecha_fin_pautada` DATETIME, IN `p_es_oneway` BOOLEAN, IN `p_monto_total` DECIMAL(10,2), IN `p_monto_sena` DECIMAL(10,2), IN `p_estado_reserva` VARCHAR(50))   BEGIN
    INSERT INTO alquileres (
        id_cliente, id_vehiculo, id_sucursal_retiro, id_sucursal_devolucion,
        fecha_reserva, fecha_inicio_pautada, fecha_fin_pautada, es_oneway,
        monto_total, monto_sena, estado_reserva
    ) VALUES (
        p_id_cliente, p_id_vehiculo, p_id_sucursal_retiro, p_id_sucursal_devolucion,
        p_fecha_reserva, p_fecha_inicio_pautada, p_fecha_fin_pautada, p_es_oneway,
        p_monto_total, p_monto_sena, p_estado_reserva
    );
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_alquiler_extra` (IN `p_id_alquiler` INT, IN `p_id_extra` INT, IN `p_cantidad` INT)   BEGIN
    INSERT INTO alquileres_extras (id_alquiler, id_extra, cantidad)
    VALUES (p_id_alquiler, p_id_extra, p_cantidad);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_categoria` (IN `p_nombre_categoria` VARCHAR(50), IN `p_descripcion` TEXT, IN `p_tarifa_base_diaria` DECIMAL(10,2))   BEGIN
    INSERT INTO categorias_vehiculos (nombre_categoria, descripcion, tarifa_base_diaria)
    VALUES (p_nombre_categoria, p_descripcion, p_tarifa_base_diaria);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_checkin_checkout` (IN `p_id_alquiler` INT, IN `p_id_usuario_empleado` INT, IN `p_tipo` VARCHAR(20), IN `p_fecha_hora_real` DATETIME, IN `p_kilometraje` INT, IN `p_nivel_combustible` VARCHAR(20), IN `p_observaciones` TEXT)   BEGIN
    INSERT INTO checkins_checkouts (id_alquiler, id_usuario_empleado, tipo, fecha_hora_real, kilometraje, nivel_combustible, observaciones)
    VALUES (p_id_alquiler, p_id_usuario_empleado, p_tipo, p_fecha_hora_real, p_kilometraje, p_nivel_combustible, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_cliente` (IN `p_id_cliente` INT, IN `p_dni` VARCHAR(20), IN `p_licencia` VARCHAR(50), IN `p_domicilio` VARCHAR(200), IN `p_foto` VARCHAR(255), IN `p_fecha` DATE, IN `p_apto` TINYINT)   BEGIN
    INSERT INTO clientes (id_cliente, dni, licencia_conducir, domicilio, foto_identificacion_url, fecha_nacimiento, es_apto_lujo)
    VALUES (p_id_cliente, p_dni, p_licencia, p_domicilio, p_foto, p_fecha, p_apto);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_foto_inspeccion` (IN `p_id_registro` INT, IN `p_foto_url` VARCHAR(255), IN `p_observacion_dano` TEXT)   BEGIN
    INSERT INTO fotos_inspecciones (id_registro, foto_url, observacion_dano)
    VALUES (p_id_registro, p_foto_url, p_observacion_dano);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_mantenimiento` (IN `p_id_vehiculo` INT, IN `p_tipo_mantenimiento` VARCHAR(50), IN `p_fecha_ingreso` DATE, IN `p_fecha_egreso_estimada` DATE, IN `p_costo_total` DECIMAL(10,2), IN `p_taller_mecanico` VARCHAR(100), IN `p_detalle_trabajo` TEXT)   BEGIN
    INSERT INTO mantenimientos (id_vehiculo, tipo_mantenimiento, fecha_ingreso, fecha_egreso_estimada, costo_total, taller_mecanico, detalle_trabajo)
    VALUES (p_id_vehiculo, p_tipo_mantenimiento, p_fecha_ingreso, p_fecha_egreso_estimada, p_costo_total, p_taller_mecanico, p_detalle_trabajo);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_marca` (IN `p_nombre_marca` VARCHAR(50))   BEGIN
    INSERT INTO marcas (nombre_marca)
    VALUES (p_nombre_marca);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_metodo_pago` (IN `p_nombre_metodo` VARCHAR(50), IN `p_requiere_comprobante` TINYINT)   BEGIN
    INSERT INTO metodos_pagos (nombre_metodo, requiere_comprobante)
    VALUES (p_nombre_metodo, p_requiere_comprobante);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_modelo` (IN `p_id_marca` INT, IN `p_nombre_modelo` VARCHAR(50), IN `p_anio` INT, IN `p_imagen` VARCHAR(100))   BEGIN
    INSERT INTO modelos (id_marca, nombre_modelo, anio, imagen)
    VALUES (p_id_marca, p_nombre_modelo, p_anio, p_imagen);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_opcional_extra` (IN `p_nombre_extra` VARCHAR(50), IN `p_descripcion` TEXT, IN `p_precio_diario` DECIMAL(8,2))   BEGIN
    INSERT INTO opcionales_extras (nombre_extra, descripcion, precio_diario)
    VALUES (p_nombre_extra, p_descripcion, p_precio_diario);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_pago` (IN `p_id_alquiler` INT, IN `p_id_metodo_pago` INT, IN `p_monto` DECIMAL(12,2), IN `p_fecha_pago` DATETIME, IN `p_estado_pago` VARCHAR(20), IN `p_numero_comprobante` VARCHAR(100))   BEGIN
    INSERT INTO pagos (id_alquiler, id_metodo_pago, monto, fecha_pago, estado_pago, numero_comprobante)
    VALUES (p_id_alquiler, p_id_metodo_pago, p_monto, p_fecha_pago, p_estado_pago, p_numero_comprobante);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_rol` (IN `p_nombre_rol` VARCHAR(50), IN `p_descripcion` TEXT)   BEGIN
    INSERT INTO roles (nombre_rol, descripcion)
    VALUES (p_nombre_rol, p_descripcion);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_sucursal` (IN `p_nombre` VARCHAR(100), IN `p_direccion` VARCHAR(200), IN `p_ciudad` VARCHAR(100), IN `p_activa` TINYINT)   BEGIN
    INSERT INTO sucursales (nombre, direccion, ciudad, activa)
    VALUES (p_nombre, p_direccion, p_ciudad, p_activa);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_traslado` (IN `p_id_vehiculo` INT, IN `p_id_sucursal_origen` INT, IN `p_id_sucursal_destino` INT, IN `p_id_usuario_chofer` INT, IN `p_fecha_salida` DATETIME, IN `p_fecha_llegada` DATETIME, IN `p_observaciones` TEXT)   BEGIN
    INSERT INTO traslados_vehiculos (id_vehiculo, id_sucursal_origen, id_sucursal_destino, id_usuario_chofer, fecha_salida, fecha_llegada, observaciones)
    VALUES (p_id_vehiculo, p_id_sucursal_origen, p_id_sucursal_destino, p_id_usuario_chofer, p_fecha_salida, p_fecha_llegada, p_observaciones);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_usuario` (IN `p_id_rol` INT, IN `p_email` VARCHAR(150), IN `p_password_hash` VARCHAR(255), IN `p_nombre` VARCHAR(100), IN `p_apellido` VARCHAR(100), IN `p_dni` VARCHAR(8), IN `p_cuil` VARCHAR(20), IN `p_telefono` VARCHAR(30), IN `p_intentos_fallidos` INT, IN `p_cuenta_bloqueada` TINYINT, IN `p_fecha_creacion` DATETIME, IN `p_fecha_nacimiento` DATE)   BEGIN
    INSERT INTO usuarios (id_rol, email, password_hash, nombre, apellido, dni, cuil, telefono, intentos_fallidos, cuenta_bloqueada, fecha_creacion, fecha_nacimiento)
    VALUES (p_id_rol, p_email, p_password_hash, p_nombre, p_apellido, p_dni, p_cuil, p_telefono, p_intentos_fallidos, p_cuenta_bloqueada, p_fecha_creacion, p_fecha_nacimiento);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_vehiculo` (IN `p_patente` VARCHAR(15), IN `p_id_modelo` INT, IN `p_id_categoria` INT, IN `p_id_sucursal_actual` INT, IN `p_kilometraje_actual` INT, IN `p_estado` VARCHAR(50), IN `p_vencimiento_seguro` DATE, IN `p_vencimiento_patente` DATE)   BEGIN
    INSERT INTO vehiculos (patente, id_modelo, id_categoria, id_sucursal_actual, kilometraje_actual, estado, vencimiento_seguro, vencimiento_patente)
    VALUES (p_patente, p_id_modelo, p_id_categoria, p_id_sucursal_actual, p_kilometraje_actual, p_estado, p_vencimiento_seguro, p_vencimiento_patente);
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_alquileres` ()   BEGIN
    SELECT 
        id_alquiler, 
        id_cliente, 
        id_vehiculo, 
        id_sucursal_retiro, 
        id_sucursal_devolucion, 
        fecha_reserva, 
        fecha_inicio_pautada, 
        fecha_fin_pautada, 
        es_oneway, 
        monto_total, 
        monto_sena, 
        estado_reserva 
    FROM alquileres;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_alquileres_extras` ()   BEGIN
    SELECT * FROM alquileres_extras;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_categorias` ()   BEGIN
    SELECT * FROM categorias_vehiculos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_checkins_checkouts` ()   BEGIN
    SELECT * FROM checkins_checkouts;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_clientes` ()   BEGIN
    SELECT * FROM clientes;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_fotos_inspecciones` ()   BEGIN
    SELECT * FROM fotos_inspecciones;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_mantenimientos` ()   BEGIN
    SELECT * FROM mantenimientos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_marcas` ()   BEGIN
    SELECT * FROM marcas;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_metodos_pagos` ()   BEGIN
    SELECT * FROM metodos_pagos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_modelos` ()   BEGIN
    SELECT * FROM modelos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_opcionales_extras` ()   BEGIN
    SELECT * FROM opcionales_extras;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_pagos` ()   BEGIN
    SELECT * FROM pagos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_roles` ()   BEGIN
    SELECT * FROM roles;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_sucursales` ()   BEGIN
    SELECT * FROM sucursales;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_traslados` ()   BEGIN
    SELECT * FROM traslados_vehiculos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_usuarios` ()   BEGIN
    SELECT * FROM usuarios;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_listar_vehiculos` ()   BEGIN
    SELECT * FROM vehiculos;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_alquiler` (IN `p_id_alquiler` INT, IN `p_id_cliente` INT, IN `p_id_vehiculo` INT, IN `p_id_sucursal_retiro` INT, IN `p_id_sucursal_devolucion` INT, IN `p_fecha_inicio_pautada` DATETIME, IN `p_fecha_fin_pautada` DATETIME, IN `p_es_oneway` TINYINT, IN `p_monto_total` DECIMAL(10,2), IN `p_monto_sena` DECIMAL(10,2), IN `p_estado_reserva` VARCHAR(50))   BEGIN
    UPDATE alquileres 
    SET id_cliente = p_id_cliente, 
        id_vehiculo = p_id_vehiculo, 
        id_sucursal_retiro = p_id_sucursal_retiro, 
        id_sucursal_devolucion = p_id_sucursal_devolucion, 
        fecha_inicio_pautada = p_fecha_inicio_pautada, 
        fecha_fin_pautada = p_fecha_fin_pautada, 
        es_oneway = p_es_oneway, 
        monto_total = p_monto_total, 
        monto_sena = p_monto_sena, 
        estado_reserva = p_estado_reserva
    WHERE id_alquiler = p_id_alquiler;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_alquiler_extra` (IN `p_id_alquiler` INT, IN `p_id_extra` INT, IN `p_cantidad` INT)   BEGIN
    UPDATE alquileres_extras 
    SET cantidad = p_cantidad
    WHERE id_alquiler = p_id_alquiler AND id_extra = p_id_extra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_categoria` (IN `p_id_categoria` INT, IN `p_nombre_categoria` VARCHAR(50), IN `p_descripcion` TEXT, IN `p_tarifa_base_diaria` DECIMAL(10,2))   BEGIN
    UPDATE categorias_vehiculos 
    SET nombre_categoria = p_nombre_categoria, 
        descripcion = p_descripcion, 
        tarifa_base_diaria = p_tarifa_base_diaria
    WHERE id_categoria = p_id_categoria;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_checkin_checkout` (IN `p_id_registro` INT, IN `p_id_alquiler` INT, IN `p_id_usuario_empleado` INT, IN `p_tipo` VARCHAR(20), IN `p_fecha_hora_real` DATETIME, IN `p_kilometraje` INT, IN `p_nivel_combustible` VARCHAR(20), IN `p_observaciones` TEXT)   BEGIN
    UPDATE checkins_checkouts 
    SET id_alquiler = p_id_alquiler, 
        id_usuario_empleado = p_id_usuario_empleado, 
        tipo = p_tipo, 
        fecha_hora_real = p_fecha_hora_real, 
        kilometraje = p_kilometraje, 
        nivel_combustible = p_nivel_combustible, 
        observaciones = p_observaciones
    WHERE id_registro = p_id_registro;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_cliente` (IN `p_id_cliente` INT, IN `p_dni` VARCHAR(20), IN `p_licencia_conducir` VARCHAR(50), IN `p_domicilio` VARCHAR(200), IN `p_foto_identificacion_url` VARCHAR(255), IN `p_fecha_nacimiento` DATE, IN `p_es_apto_lujo` TINYINT)   BEGIN
    UPDATE clientes 
    SET dni = p_dni, 
        licencia_conducir = p_licencia_conducir, 
        domicilio = p_domicilio, 
        foto_identificacion_url = p_foto_identificacion_url, 
        fecha_nacimiento = p_fecha_nacimiento, 
        es_apto_lujo = p_es_apto_lujo
    WHERE id_cliente = p_id_cliente;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_foto_inspeccion` (IN `p_id_foto` INT, IN `p_id_registro` INT, IN `p_foto_url` VARCHAR(255), IN `p_observacion_dano` TEXT)   BEGIN
    UPDATE fotos_inspecciones 
    SET id_registro = p_id_registro, 
        foto_url = p_foto_url, 
        observacion_dano = p_observacion_dano
    WHERE id_foto = p_id_foto;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_mantenimiento` (IN `p_id_mantenimiento` INT, IN `p_id_vehiculo` INT, IN `p_tipo_mantenimiento` VARCHAR(50), IN `p_fecha_ingreso` DATE, IN `p_fecha_egreso_estimada` DATE, IN `p_costo_total` DECIMAL(10,2), IN `p_taller_mecanico` VARCHAR(100), IN `p_detalle_trabajo` TEXT)   BEGIN
    UPDATE mantenimientos 
    SET id_vehiculo = p_id_vehiculo, 
        tipo_mantenimiento = p_tipo_mantenimiento, 
        fecha_ingreso = p_fecha_ingreso, 
        fecha_egreso_estimada = p_fecha_egreso_estimada, 
        costo_total = p_costo_total, 
        taller_mecanico = p_taller_mecanico, 
        detalle_trabajo = p_detalle_trabajo
    WHERE id_mantenimiento = p_id_mantenimiento;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_marca` (IN `p_id_marca` INT, IN `p_nombre_marca` VARCHAR(50))   BEGIN
    UPDATE marcas 
    SET nombre_marca = p_nombre_marca
    WHERE id_marca = p_id_marca;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_metodo_pago` (IN `p_id_metodo_pago` INT, IN `p_nombre_metodo` VARCHAR(50), IN `p_requiere_comprobante` TINYINT)   BEGIN
    UPDATE metodos_pagos 
    SET nombre_metodo = p_nombre_metodo, 
        requiere_comprobante = p_requiere_comprobante
    WHERE id_metodo_pago = p_id_metodo_pago;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_modelo` (IN `p_id_modelo` INT, IN `p_id_marca` INT, IN `p_nombre_modelo` VARCHAR(50), IN `p_anio` INT, IN `p_imagen` VARCHAR(100))   BEGIN
    UPDATE modelos 
    SET id_marca = p_id_marca, 
        nombre_modelo = p_nombre_modelo, 
        anio = p_anio, 
        imagen = p_imagen
    WHERE id_modelo = p_id_modelo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_opcional_extra` (IN `p_id_extra` INT, IN `p_nombre_extra` VARCHAR(50), IN `p_descripcion` TEXT, IN `p_precio_diario` DECIMAL(8,2))   BEGIN
    UPDATE opcionales_extras 
    SET nombre_extra = p_nombre_extra, 
        descripcion = p_descripcion, 
        precio_diario = p_precio_diario
    WHERE id_extra = p_id_extra;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_pago` (IN `p_id_pago` INT, IN `p_id_alquiler` INT, IN `p_id_metodo_pago` INT, IN `p_monto` DECIMAL(12,2), IN `p_fecha_pago` DATETIME, IN `p_estado_pago` VARCHAR(50), IN `p_numero_comprobante` VARCHAR(100))   BEGIN
    UPDATE pagos 
    SET id_alquiler = p_id_alquiler, 
        id_metodo_pago = p_id_metodo_pago, 
        monto = p_monto, 
        fecha_pago = p_fecha_pago, 
        estado_pago = p_estado_pago, 
        numero_comprobante = p_numero_comprobante
    WHERE id_pago = p_id_pago;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_rol` (IN `p_id_rol` INT, IN `p_nombre_rol` VARCHAR(50), IN `p_descripcion` TEXT)   BEGIN
    UPDATE roles 
    SET nombre_rol = p_nombre_rol, 
        descripcion = p_descripcion
    WHERE id_rol = p_id_rol;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_sucursal` (IN `p_id_sucursal` INT, IN `p_nombre` VARCHAR(100), IN `p_direccion` VARCHAR(200), IN `p_ciudad` VARCHAR(100), IN `p_activa` TINYINT)   BEGIN
    UPDATE sucursales 
    SET nombre = p_nombre, 
        direccion = p_direccion, 
        ciudad = p_ciudad, 
        activa = p_activa
    WHERE id_sucursal = p_id_sucursal;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_traslado` (IN `p_id_traslado` INT, IN `p_id_vehiculo` INT, IN `p_id_sucursal_origen` INT, IN `p_id_sucursal_destino` INT, IN `p_id_usuario_chofer` INT, IN `p_fecha_salida` DATETIME, IN `p_fecha_llegada` DATETIME, IN `p_observaciones` TEXT)   BEGIN
    UPDATE traslados_vehiculos 
    SET id_vehiculo = p_id_vehiculo, 
        id_sucursal_origen = p_id_sucursal_origen, 
        id_sucursal_destino = p_id_sucursal_destino, 
        id_usuario_chofer = p_id_usuario_chofer, 
        fecha_salida = p_fecha_salida, 
        fecha_llegada = p_fecha_llegada, 
        observaciones = p_observaciones
    WHERE id_traslado = p_id_traslado;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_usuario` (IN `p_id_usuario` INT, IN `p_id_rol` INT, IN `p_email` VARCHAR(150), IN `p_password_hash` VARCHAR(255), IN `p_nombre` VARCHAR(100), IN `p_apellido` VARCHAR(100), IN `p_dni` VARCHAR(8), IN `p_cuil` VARCHAR(20), IN `p_telefono` VARCHAR(30), IN `p_intentos_fallidos` INT, IN `p_cuenta_bloqueada` TINYINT, IN `p_fecha_nacimiento` DATE)   BEGIN
    UPDATE usuarios 
    SET id_rol = p_id_rol, 
        email = p_email, 
        password_hash = p_password_hash, 
        nombre = p_nombre, 
        apellido = p_apellido, 
        dni = p_dni, 
        cuil = p_cuil, 
        telefono = p_telefono, 
        intentos_fallidos = p_intentos_fallidos, 
        cuenta_bloqueada = p_cuenta_bloqueada, 
        fecha_nacimiento = p_fecha_nacimiento
    WHERE id_usuario = p_id_usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_modificar_vehiculo` (IN `p_id_vehiculo` INT, IN `p_patente` VARCHAR(15), IN `p_id_modelo` INT, IN `p_id_categoria` INT, IN `p_id_sucursal_actual` INT, IN `p_kilometraje_actual` INT, IN `p_estado` VARCHAR(50), IN `p_vencimiento_seguro` DATE, IN `p_vencimiento_patente` DATE)   BEGIN
    UPDATE vehiculos 
    SET patente = p_patente, 
        id_modelo = p_id_modelo, 
        id_categoria = p_id_categoria, 
        id_sucursal_actual = p_id_sucursal_actual, 
        kilometraje_actual = p_kilometraje_actual, 
        estado = p_estado, 
        vencimiento_seguro = p_vencimiento_seguro, 
        vencimiento_patente = p_vencimiento_patente
    WHERE id_vehiculo = p_id_vehiculo;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtener_todos_usuarios` ()   BEGIN
    SELECT u.*, r.nombre_rol, r.descripcion AS desc_rol 
    FROM usuarios u
    INNER JOIN roles r ON u.id_rol = r.id_rol;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_obtener_usuario_por_id` (IN `p_id_usuario` INT)   BEGIN
    SELECT u.*, r.nombre_rol, r.descripcion AS desc_rol 
    FROM usuarios u
    INNER JOIN roles r ON u.id_rol = r.id_rol
    WHERE u.id_usuario = p_id_usuario;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_validar_login` (IN `p_email` VARCHAR(150))   BEGIN
    SELECT id_usuario, id_rol, email, password_hash, nombre, apellido, dni, cuil, telefono
    FROM usuarios 
    WHERE email = p_email;
END$$

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_verificar_alquiler_activo` (IN `p_id_cliente` INT)   BEGIN
    SELECT COUNT(*) AS total
    FROM alquileres 
    WHERE id_cliente = p_id_cliente 
      AND estado_reserva = 'RESERVADA' 
      AND fecha_fin_pautada >= NOW();
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

--
-- Volcado de datos para la tabla `alquileres`
--

INSERT INTO `alquileres` (`id_alquiler`, `id_cliente`, `id_vehiculo`, `id_sucursal_retiro`, `id_sucursal_devolucion`, `fecha_reserva`, `fecha_inicio_pautada`, `fecha_fin_pautada`, `es_oneway`, `monto_total`, `monto_sena`, `estado_reserva`) VALUES
(1, 83, 17, 1, 1, '2026-11-01 01:24:21', '2026-11-27 03:00:00', '2026-12-12 03:00:00', 0, 2108000.00, 2108000.00, 'CANCELADA'),
(2, 84, 3, 1, 1, '2026-11-01 01:37:42', '2026-11-01 03:00:00', '2026-11-11 03:00:00', 0, 480000.00, 480000.00, 'CANCELADA'),
(3, 85, 44, 1, 1, '2026-11-01 02:14:07', '2026-11-01 03:00:00', '2026-11-04 03:00:00', 0, 0.00, 123000.00, 'CANCELADA'),
(4, 70, 9, 1, 1, '2026-11-02 17:32:14', '2026-11-02 03:00:00', '2026-11-12 03:00:00', 0, 0.00, 700000.00, 'CANCELADA'),
(5, 70, 12, 1, 1, '2026-11-02 17:39:04', '2026-11-02 03:00:00', '2026-11-12 03:00:00', 0, 0.00, 650000.00, 'CANCELADA'),
(6, 70, 10, 1, 1, '2026-11-02 17:48:05', '2026-11-02 03:00:00', '2026-11-05 03:00:00', 0, 0.00, 174000.00, 'CANCELADA'),
(7, 70, 8, 1, 1, '2026-11-02 17:52:34', '2026-11-02 03:00:00', '2026-11-03 03:00:00', 0, 0.00, 50000.00, 'CANCELADA'),
(8, 70, 13, 1, 1, '2026-11-02 17:56:56', '2026-11-02 03:00:00', '2026-11-03 03:00:00', 0, 0.00, 52000.00, 'CANCELADA'),
(9, 70, 36, 1, 1, '2026-11-02 18:00:57', '2026-11-02 03:00:00', '2026-11-05 03:00:00', 0, 0.00, 240000.00, 'CANCELADA'),
(10, 70, 6, 1, 1, NULL, '2026-11-02 03:00:00', '2026-11-12 03:00:00', 0, 450000.00, 450000.00, 'CANCELADA');

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

--
-- Volcado de datos para la tabla `categorias_vehiculos`
--

INSERT INTO `categorias_vehiculos` (`id_categoria`, `nombre_categoria`, `descripcion`, `tarifa_base_dia`) VALUES
(1, 'ECONOMICO', 'Autos compactos de bajo consumo', 35000.00),
(2, 'SEDAN', 'Vehículos confortables de 4 puertas', 50000.00),
(3, 'CAMIONETA', 'Pick-ups y vehículos 4x4', 85000.00);

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

--
-- Volcado de datos para la tabla `checkins_checkouts`
--

INSERT INTO `checkins_checkouts` (`id_registro`, `id_alquiler`, `id_usuario_empleado`, `tipo`, `fecha_hora_real`, `kilometraje`, `nivel_combustible`, `observaciones`) VALUES
(1, 1, 2, 'ENTREGA', '2026-09-28 20:15:31', 32000, 'LLENO', 'Vehículo entregado en óptimas condiciones.');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `clientes`
--

CREATE TABLE `clientes` (
  `id_cliente` int(11) NOT NULL,
  `dni` varchar(20) NOT NULL,
  `licencia_conducir` varchar(50) NOT NULL,
  `domicilio` varchar(200) NOT NULL,
  `foto_identificacion_url` varchar(255) DEFAULT NULL,
  `fecha_nacimiento` date DEFAULT NULL,
  `es_apto_lujo` tinyint(4) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `clientes`
--

INSERT INTO `clientes` (`id_cliente`, `dni`, `licencia_conducir`, `domicilio`, `foto_identificacion_url`, `fecha_nacimiento`, `es_apto_lujo`) VALUES
(66, '99999998', '99999998', 'cachimayo 112', NULL, '2000-01-01', 0),
(67, '33333333', '33333333', 'rivadavia 1415', NULL, '2000-01-01', 0),
(68, '45123456', '45123456', 'cachimayo 112', NULL, '2000-01-01', 0),
(69, '45143456', '45143456', '1232', NULL, '2000-01-01', 0),
(70, '99999999', '99999999', 'cachimayo 112', NULL, '2000-01-01', 0),
(79, '99994995', '99994995', '1232', NULL, '2000-01-01', 0),
(80, '12345688', '12345688', 'rivadavia 1414', NULL, '2000-01-01', 0),
(81, '99499999', '99499999', 'cachimayo 112', NULL, '2000-01-01', 0),
(82, '66666666', '66666666', 'rivadavia 1415', NULL, '2000-01-01', 0),
(83, '92399999', '92399999', 'rivadavia 1414', NULL, '2000-01-01', 0),
(84, '11999999', '11999999', 'cachimayo 112', NULL, '2000-01-01', 0),
(85, '12555573', '12555573', 'rivadavia 1414', NULL, '2000-01-01', 0);

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

--
-- Volcado de datos para la tabla `marcas`
--

INSERT INTO `marcas` (`id_marca`, `nombre_marca`) VALUES
(4, 'Chevrolet'),
(6, 'Fiat'),
(2, 'Ford'),
(8, 'Honda'),
(5, 'Peugeot'),
(7, 'Renault'),
(1, 'Toyota'),
(3, 'Volkswagen'),
(9, 'Yamaha');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `metodos_pagos`
--

CREATE TABLE `metodos_pagos` (
  `id_metodo_pago` int(11) NOT NULL,
  `nombre_metodo` varchar(50) NOT NULL,
  `requiere_comprobante` tinyint(1) DEFAULT 0
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `metodos_pagos`
--

INSERT INTO `metodos_pagos` (`id_metodo_pago`, `nombre_metodo`, `requiere_comprobante`) VALUES
(2, 'Tarjeta de Crédito', 1),
(3, 'Transferencia Bancaria', 1);

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `modelos`
--

CREATE TABLE `modelos` (
  `id_modelo` int(11) NOT NULL,
  `id_marca` int(11) NOT NULL,
  `nombre_modelo` varchar(50) NOT NULL,
  `anio` int(11) DEFAULT NULL,
  `imagen` varchar(100) DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `modelos`
--

INSERT INTO `modelos` (`id_modelo`, `id_marca`, `nombre_modelo`, `anio`, `imagen`) VALUES
(1, 1, 'Corolla', 2023, 'COROLLA_2023.jpg'),
(2, 1, 'Yaris', 2022, 'YARIS_2022.jpg'),
(3, 1, 'Hilux', 2024, 'HILUX_2024.jpg'),
(4, 1, 'Etios', 2021, 'ETIOS_2021.jpg'),
(5, 2, 'Ranger', 2023, 'RANGER_2023.jpg'),
(6, 2, 'Ka', 2021, 'KA_2021.jpg'),
(7, 2, 'Focus', 2020, 'FOCUS_2020.jpg'),
(8, 2, 'Territory', 2023, 'TERRITORY_2023.jpg'),
(9, 3, 'Gol Trend', 2022, 'GOL_TREND_2022.jpg'),
(10, 3, 'Polo', 2023, 'POLO_2023.jpg'),
(11, 3, 'Amarok', 2024, 'AMAROK_2024.jpg'),
(12, 3, 'T-Cross', 2023, 'T_CROSS_2023.jpg'),
(13, 3, 'Taos', 2024, 'TAOS_2024.jpg'),
(14, 4, 'Onix', 2022, 'ONIX_2022.jpg'),
(15, 4, 'Cruze', 2023, 'CRUZE_2023.jpg'),
(16, 4, 'Tracker', 2023, 'TRACKER_2023.jpg'),
(17, 4, 'S10', 2024, 'S10_2024.jpg'),
(18, 5, '208', 2023, '208_2023.jpg'),
(19, 5, '2008', 2022, '2008_2022.jpg'),
(20, 5, '3008', 2023, '3008_2023.jpg'),
(21, 6, 'Cronos', 2023, 'CRONOS_2023.jpg'),
(22, 6, 'Toro', 2024, 'TORO_2024.jpg'),
(23, 6, 'Mobi', 2022, 'MOBI_2022.jpg'),
(24, 7, 'Sandero', 2022, 'SANDERO_2022.jpg'),
(25, 7, 'Duster', 2023, 'DUSTER_2023.jpg'),
(26, 7, 'Logan', 2021, 'LOGAN_2021.jpg'),
(27, 8, 'XR 150L', 2023, 'XR150L_2023.jpg'),
(28, 8, 'Wave 110S', 2023, 'WAVE110S_2023.jpg'),
(29, 8, 'CB300R', 2024, 'CB300R_2024.jpg'),
(30, 9, 'YBR 125', 2022, 'YBR125_2022.jpg'),
(31, 1, 'Ferrari 488 GTB', 2017, 'FERRARI_488.jpg'),
(32, 1, 'Lamborghini Urus', 2019, 'LAMBO_URUS.jpg'),
(33, 1, 'BMW M4 Competition', 2018, 'BMW_M4.jpg'),
(34, 1, 'Audi R8 V10', 2016, 'AUDI_R8.jpg'),
(35, 1, 'Porsche 911 Turbo S', 2018, 'PORSCHE_911.jpg'),
(36, 1, 'Mercedes-AMG G 63', 2019, 'AMG_G63.jpg'),
(37, 1, 'Chevrolet Corvette C8', 2020, 'CORVETTE_C8.jpg'),
(38, 1, 'Aston Martin Vantage', 2017, 'ASTON_VANTAGE.jpg'),
(39, 1, 'Maserati Levante Trofeo', 2018, 'MASERATI_LEVANTE.jpg'),
(40, 1, 'Bentley Continental GT', 2016, 'BENTLEY_GT.jpg');

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

--
-- Volcado de datos para la tabla `opcionales_extras`
--

INSERT INTO `opcionales_extras` (`id_extra`, `nombre_extra`, `descripcion`, `precio_diario`) VALUES
(1, 'Silla de Bebé', 'Silla de seguridad para niños', 5000.00),
(2, 'GPS', 'Navegador satelital', 3000.00);

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

--
-- Volcado de datos para la tabla `pagos`
--

INSERT INTO `pagos` (`id_pago`, `id_alquiler`, `id_metodo_pago`, `monto`, `fecha_pago`, `estado_pago`, `numero_comprobante`) VALUES
(28, 26, 2, 93000.00, '2026-11-01 03:34:17', 'APROBADO', 'COMP-604584.0'),
(29, 27, 2, 100000.00, '2026-11-01 03:38:29', 'APROBADO', 'COMP-237833.0'),
(30, 28, 2, 100000.00, '2026-11-01 03:56:06', 'APROBADO', 'COMP-504359.0'),
(31, 29, 2, 180000.00, '2026-11-01 04:02:05', 'APROBADO', 'COMP-322497.0'),
(32, 1, 2, 2108000.00, '2026-11-01 04:24:21', 'APROBADO', 'COMP-119095.0'),
(33, 2, 2, 480000.00, '2026-11-01 04:37:42', 'APROBADO', 'COMP-412050.0'),
(34, 3, 2, 123000.00, '2026-11-01 05:14:07', 'APROBADO', 'COMP-198551.0'),
(35, 4, 2, 700000.00, '2026-11-02 20:32:14', 'APROBADO', 'COMP-170287.0'),
(36, 5, 2, 650000.00, '2026-11-02 20:39:04', 'APROBADO', 'COMP-159110.0'),
(37, 6, 2, 174000.00, '2026-11-02 20:48:05', 'APROBADO', 'COMP-498693.0'),
(38, 7, 2, 50000.00, '2026-11-02 20:52:34', 'APROBADO', 'COMP-868394.0'),
(39, 8, 2, 52000.00, '2026-11-02 20:56:56', 'APROBADO', 'COMP-642200.0'),
(40, 9, 2, 240000.00, '2026-11-02 21:00:57', 'APROBADO', 'COMP-155618.0'),
(41, 10, 2, 450000.00, '2026-11-02 21:03:26', 'APROBADO', 'COMP-321973.0');

-- --------------------------------------------------------

--
-- Estructura de tabla para la tabla `roles`
--

CREATE TABLE `roles` (
  `id_rol` int(11) NOT NULL,
  `nombre_rol` varchar(50) NOT NULL,
  `descripcion` text DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `roles`
--

INSERT INTO `roles` (`id_rol`, `nombre_rol`, `descripcion`) VALUES
(1, 'ADMINISTRADOR', 'Acceso total al sistema'),
(2, 'EMPLEADO', 'Atención al cliente, checkins y flota'),
(3, 'CLIENTE', 'Usuarios finales que alquilan vehículos');

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

--
-- Volcado de datos para la tabla `sucursales`
--

INSERT INTO `sucursales` (`id_sucursal`, `nombre`, `direccion`, `ciudad`, `activa`) VALUES
(1, 'Central - Microcentro', 'Av. Corrientes 800', 'CABA', 1),
(2, 'Sucursal Caballito', 'Av. Rivadavia 5100', 'CABA', 1),
(3, 'Sucursal Palermo', 'Av. Santa Fe 3400', 'CABA', 1),
(4, 'Sucursal Belgrano', 'Av. Cabildo 2100', 'CABA', 1),
(5, 'Sucursal Flores', 'Av. Gaona 2800', 'CABA', 1),
(6, 'Sucursal Villa Crespo', 'Av. Corrientes 4800', 'CABA', 1),
(7, 'Sucursal Almagro', 'Av. Medrano 600', 'CABA', 1),
(8, 'Sucursal Recoleta', 'Av. Las Heras 2200', 'CABA', 1),
(9, 'Sucursal San Telmo', 'Av. Paseo Colón 700', 'CABA', 1),
(10, 'Sucursal Barracas', 'Av. Montes de Oca 1100', 'CABA', 1),
(11, 'Sucursal Devoto', 'Av. Beiró 3800', 'CABA', 1),
(12, 'Sucursal Nuñez', 'Av. del Libertador 6500', 'CABA', 1),
(13, 'Sucursal Liniers', 'Av. Rivadavia 11500', 'CABA', 1),
(14, 'Sucursal Boedo', 'Av. San Juan 3500', 'CABA', 1),
(15, 'Sucursal Saavedra', 'Av. Balbín 4000', 'CABA', 1),
(16, 'Sucursal Parque Patricios', 'Av. Caseros 2900', 'CABA', 1),
(17, 'Sucursal Mataderos', 'Av. Juan B. Alberdi 6000', 'CABA', 1),
(18, 'Sucursal Villa Urquiza', 'Av. Triunvirato 4500', 'CABA', 1),
(19, 'Sucursal Vicente López', 'Av. Maipú 1500', 'Vicente López', 1),
(20, 'Sucursal Olivos', 'Av. del Libertador 2400', 'Olivos', 1),
(21, 'Sucursal San Isidro', 'Av. Centenario 800', 'San Isidro', 1),
(22, 'Sucursal Martínez', 'Av. Santa Fe 1800', 'Martínez', 1),
(23, 'Sucursal San Fernando', 'Av. Constitución 1100', 'San Fernando', 1),
(24, 'Sucursal Tigre', 'Av. Cazón 1200', 'Tigre', 1),
(25, 'Sucursal Pilar', 'Panamericana Km 50', 'Pilar', 1),
(26, 'Sucursal Escobar', 'Av. Tapia de Cruz 700', 'Escobar', 1),
(27, 'Sucursal San Martín', 'Av. San Martín 1900', 'San Martín', 1),
(28, 'Sucursal Villa Ballester', 'Calle Lacroze 4800', 'San Martín', 1),
(29, 'Sucursal Morón', 'Av. Rivadavia 18200', 'Morón', 1),
(30, 'Sucursal Castelar', 'Av. Arias 2300', 'Castelar', 1),
(31, 'Sucursal Ramos Mejía', 'Av. de Mayo 800', 'Ramos Mejía', 1),
(32, 'Sucursal San Justo', 'Av. Illia 2500', 'San Justo', 1),
(33, 'Sucursal Ituzaingó', 'Av. Néstor Kirchner 21500', 'Ituzaingó', 1),
(34, 'Sucursal Merlo', 'Av. del Libertador 600', 'Merlo', 1),
(35, 'Sucursal Moreno', 'Av. Victorica 1100', 'Moreno', 1),
(36, 'Sucursal Hurlingham', 'Av. Jauretche 1400', 'Hurlingham', 1),
(37, 'Sucursal El Palomar', 'Av. Rosales 1200', 'El Palomar', 1),
(38, 'Sucursal Caseros', 'Av. San Martín 2600', 'Caseros', 1),
(39, 'Sucursal Avellaneda', 'Av. Mitre 1400', 'Avellaneda', 1),
(40, 'Sucursal Quilmes', 'Peatonal Rivadavia 300', 'Quilmes', 1),
(41, 'Sucursal Lanús', 'Av. Hipólito Yrigoyen 4500', 'Lanús', 1),
(42, 'Sucursal Lomas de Zamora', 'Calle Boedo 300', 'Lomas de Zamora', 1),
(43, 'Sucursal Banfield', 'Av. Alsina 800', 'Banfield', 1),
(44, 'Sucursal Temperley', 'Av. Meeks 1200', 'Temperley', 1),
(45, 'Sucursal Adrogué', 'Av. Espora 700', 'Adrogué', 1),
(46, 'Sucursal Berazategui', 'Calle 14 nro 4500', 'Berazategui', 1),
(47, 'Sucursal Florencio Varela', 'Av. San Martín 2800', 'Florencio Varela', 1),
(48, 'Sucursal Ezeiza', 'Av. Fair 1100', 'Ezeiza', 1);

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
  `dni` varchar(8) NOT NULL,
  `cuil` varchar(20) NOT NULL,
  `telefono` varchar(30) DEFAULT NULL,
  `intentos_fallidos` int(11) DEFAULT 0,
  `cuenta_bloqueada` tinyint(1) DEFAULT 0,
  `fecha_creacion` datetime DEFAULT current_timestamp(),
  `fecha_nacimiento` date DEFAULT NULL
) ENGINE=InnoDB DEFAULT CHARSET=utf8mb4 COLLATE=utf8mb4_general_ci;

--
-- Volcado de datos para la tabla `usuarios`
--

INSERT INTO `usuarios` (`id_usuario`, `id_rol`, `email`, `password_hash`, `nombre`, `apellido`, `dni`, `cuil`, `telefono`, `intentos_fallidos`, `cuenta_bloqueada`, `fecha_creacion`, `fecha_nacimiento`) VALUES
(1, 1, 'admin@autorent.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Carlos', 'Gómez', '', '20-30111222-8', '1144556677', 0, 0, '2026-09-28 20:15:30', NULL),
(2, 2, 'empleado1@autorent.com', 'hash_emp_123', 'Lucía', 'Fernández', '', '27-35222333-4', '1155667788', 0, 0, '2026-09-28 20:15:30', NULL),
(66, 3, 'ian@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'ian', 'vecchio', '99999998', '20957454504', '8888888888', 0, 0, NULL, '2000-11-11'),
(67, 3, 'leo@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'leos', 'Quintero', '33333333', '20957653504', '1233333333', 0, 0, NULL, '2000-02-20'),
(68, 3, 'mama@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'mama', 'mami', '45123456', '21957453504', '8888888888', 0, 0, NULL, '2000-02-11'),
(69, 3, 'la@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'lala', 'lalpo', '45143456', '20957553504', '1111111111', 0, 0, NULL, '2000-11-11'),
(70, 3, 'sebastian.quinteroet32@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Sebastian', 'Quintero', '99999999', '20954453504', '8888888888', 0, 0, NULL, '2000-11-11'),
(79, 3, 'ga@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'gaga', 'gagas', '99994995', '20957153504', '1231111111', 0, 0, NULL, '2000-11-11'),
(80, 3, 'ka@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'mamama', 'memi', '12345688', '20957444504', '8888888888', 0, 0, NULL, '2000-11-11'),
(81, 3, 'papa@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'papa', 'notengo', '99499999', '20557453504', '1233333333', 0, 0, NULL, '2000-11-11'),
(82, 3, 'na@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'nana', 'nene', '66666666', '20666456784', '8888888888', 0, 0, NULL, '2000-12-11'),
(83, 3, 'mel@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'gagamel', 'mele', '92399999', '20955553504', '8888888888', 0, 0, NULL, '2000-11-11'),
(84, 3, 'opa@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'opa', 'popa', '11999999', '20117453504', '8888888888', 0, 0, NULL, '2000-11-11'),
(85, 3, 'kakak@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'kkaka', 'mmmm', '12555573', '20444443504', '8888888888', 0, 0, NULL, '2000-11-11');

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
-- Volcado de datos para la tabla `vehiculos`
--

INSERT INTO `vehiculos` (`id_vehiculo`, `patente`, `id_modelo`, `id_categoria`, `id_sucursal_actual`, `kilometraje_actual`, `estado`, `vencimiento_seguro`, `vencimiento_patente`) VALUES
(1, 'AB111AA', 2, 1, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(2, 'AB222BB', 4, 1, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(3, 'AB333CC', 6, 1, 3, 0, 'EN_MANTENIMIENTO', '2026-10-15', '2027-04-15'),
(4, 'AB444DD', 9, 1, 1, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(5, 'AB555EE', 14, 1, 4, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(6, 'AB665FF', 23, 1, 2, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(7, 'AB777GG', 24, 1, 5, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(8, 'AB888HH', 26, 1, 1, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(9, 'AC111AA', 1, 2, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(10, 'AC222BB', 7, 2, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(11, 'AC333CC', 10, 2, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(12, 'AC444DD', 15, 2, 4, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(13, 'AC555EE', 18, 2, 1, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(14, 'AC666FF', 21, 2, 5, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(15, 'VIP01AA', 31, 2, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(16, 'VIP02BB', 32, 2, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(17, 'VIP03CC', 33, 2, 1, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(18, 'VIP04DD', 34, 2, 3, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(19, 'VIP05EE', 35, 2, 1, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(20, 'VIP06FF', 36, 2, 2, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(21, 'VIP07GG', 37, 2, 4, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(22, 'VIP08HH', 38, 2, 1, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(23, 'VIP09II', 39, 2, 3, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(24, 'VIP10JJ', 40, 2, 2, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(25, 'AD111AA', 3, 3, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(26, 'AD112AB', 3, 3, 2, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(27, 'AD222BB', 5, 3, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(28, 'AD333CC', 8, 3, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(29, 'AD444DD', 11, 3, 1, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(30, 'AD555EE', 12, 3, 4, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(31, 'AD666FF', 13, 3, 2, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(32, 'AD777GG', 16, 3, 5, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(33, 'AD888HH', 17, 3, 1, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(34, 'AD999II', 19, 3, 3, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(35, 'AD000JJ', 20, 3, 2, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(36, 'AE111AA', 22, 3, 4, 0, 'DISPONIBLE', '2026-12-15', '2027-06-15'),
(37, 'AE222BB', 25, 3, 1, 0, 'DISPONIBLE', '2026-11-20', '2027-05-20'),
(38, 'AM111AA', 27, 1, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(39, 'AM222BB', 28, 1, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(40, 'AM333CC', 29, 1, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(41, 'AM444DD', 30, 1, 1, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(42, 'AA100AA', 2, 1, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(43, 'AA101BB', 4, 1, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(44, 'AA102CC', 6, 1, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(45, 'AA103DD', 9, 1, 4, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(46, 'AA104EE', 14, 1, 5, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(47, 'AA105FF', 23, 1, 6, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(48, 'AA106GG', 24, 1, 7, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(49, 'AA107HH', 26, 1, 8, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(50, 'AA108II', 2, 1, 9, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(51, 'AA109JJ', 4, 1, 10, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(52, 'AA110KK', 6, 1, 11, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(53, 'AA111LL', 9, 1, 12, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(54, 'AA112MM', 14, 1, 13, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(55, 'AA113NN', 23, 1, 14, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(56, 'AA114OO', 24, 1, 15, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(57, 'AA115PP', 26, 1, 16, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(58, 'AA116QQ', 2, 1, 17, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(59, 'AA117RR', 4, 1, 18, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(60, 'AA118SS', 6, 1, 19, 0, 'EN_MANTENIMIENTO', '2026-10-15', '2027-04-15'),
(61, 'AA119TT', 9, 1, 20, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(62, 'AA120UU', 14, 1, 21, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(63, 'AA121VV', 23, 1, 22, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(64, 'AA122WW', 24, 1, 23, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(65, 'AA123XX', 26, 1, 24, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(66, 'AA124YY', 2, 1, 25, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(67, 'AA125ZZ', 4, 1, 26, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(68, 'BB200AA', 1, 2, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(69, 'BB201BB', 7, 2, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(70, 'BB202CC', 10, 2, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(71, 'BB203DD', 15, 2, 4, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(72, 'BB204EE', 18, 2, 5, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(73, 'BB205FF', 21, 2, 6, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(74, 'BB206GG', 1, 2, 7, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(75, 'BB207HH', 7, 2, 8, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(76, 'BB208II', 10, 2, 9, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(77, 'BB209JJ', 15, 2, 10, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(78, 'BB210KK', 18, 2, 11, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(79, 'BB211LL', 21, 2, 12, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(80, 'BB212MM', 1, 2, 13, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(81, 'BB213NN', 7, 2, 14, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(82, 'BB214OO', 10, 2, 15, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(83, 'BB215PP', 15, 2, 16, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(84, 'BB216QQ', 18, 2, 17, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(85, 'BB217RR', 21, 2, 18, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(86, 'BB218SS', 1, 2, 19, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(87, 'BB219TT', 7, 2, 20, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(88, 'BB220UU', 10, 2, 21, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(89, 'BB221VV', 15, 2, 22, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(90, 'BB222WW', 18, 2, 23, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(91, 'BB223XX', 21, 2, 24, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(92, 'BB224YY', 1, 2, 25, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(93, 'BB225ZZ', 7, 2, 26, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(94, 'CC300AA', 3, 3, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(95, 'CC301BB', 5, 3, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(96, 'CC302CC', 8, 3, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(97, 'CC303DD', 11, 3, 4, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(98, 'CC304EE', 12, 3, 5, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(99, 'CC305FF', 13, 3, 6, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(100, 'CC306GG', 16, 3, 7, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(101, 'CC307HH', 17, 3, 8, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(102, 'CC308II', 19, 3, 9, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(103, 'CC309JJ', 20, 3, 10, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(104, 'CC310KK', 22, 3, 11, 0, 'DISPONIBLE', '2026-12-15', '2027-06-15'),
(105, 'CC311LL', 25, 3, 12, 0, 'DISPONIBLE', '2026-11-20', '2027-05-20'),
(106, 'CC312MM', 3, 3, 13, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(107, 'CC313NN', 5, 3, 14, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(108, 'CC314OO', 8, 3, 15, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(109, 'CC315PP', 11, 3, 16, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(110, 'CC316QQ', 12, 3, 17, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(111, 'CC317RR', 13, 3, 18, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(112, 'CC318SS', 16, 3, 19, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(113, 'CC319TT', 17, 3, 20, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(114, 'CC320UU', 19, 3, 21, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(115, 'CC321VV', 20, 3, 22, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(116, 'CC322WW', 22, 3, 23, 0, 'DISPONIBLE', '2026-12-15', '2027-06-15'),
(117, 'CC323XX', 25, 3, 24, 0, 'DISPONIBLE', '2026-11-20', '2027-05-20'),
(118, 'CC324YY', 3, 3, 25, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(119, 'CC325ZZ', 5, 3, 26, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(120, 'CC326AA', 8, 3, 27, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(121, 'CC327BB', 11, 3, 28, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(122, 'CC328CC', 12, 3, 29, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(123, 'CC329DD', 13, 3, 30, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(124, 'CC330EE', 16, 3, 31, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(125, 'CC331FF', 17, 3, 32, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(126, 'CC332GG', 19, 3, 33, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(127, 'CC333HH', 20, 3, 34, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(128, 'CC334II', 22, 3, 35, 0, 'DISPONIBLE', '2026-12-15', '2027-06-15'),
(129, 'CC335JJ', 25, 3, 36, 0, 'DISPONIBLE', '2026-11-20', '2027-05-20'),
(130, 'CC336KK', 3, 3, 37, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(131, 'CC337LL', 5, 3, 38, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(132, 'CC338MM', 8, 3, 39, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(133, 'CC339NN', 11, 3, 40, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(134, 'CC340OO', 12, 3, 41, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(135, 'CC341PP', 13, 3, 42, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(136, 'CC342QQ', 16, 3, 43, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(137, 'CC343RR', 17, 3, 44, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(138, 'CC344SS', 19, 3, 45, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(139, 'CC345TT', 20, 3, 46, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(140, 'CC346UU', 22, 3, 47, 0, 'DISPONIBLE', '2026-12-15', '2027-06-15'),
(141, 'CC347VV', 25, 3, 48, 0, 'DISPONIBLE', '2026-11-20', '2027-05-20'),
(142, 'DD400AA', 27, 1, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(143, 'DD401BB', 28, 1, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(144, 'DD402CC', 29, 1, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(145, 'DD403DD', 30, 1, 4, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(146, 'DD404EE', 27, 1, 5, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(147, 'DD405FF', 28, 1, 6, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(148, 'DD406GG', 29, 1, 7, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(149, 'DD407HH', 30, 1, 8, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(150, 'DD408II', 27, 1, 9, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(151, 'DD409JJ', 28, 1, 10, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(152, 'DD410KK', 29, 1, 11, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(153, 'DD411LL', 30, 1, 12, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(154, 'DD412MM', 27, 1, 13, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(155, 'DD413NN', 28, 1, 14, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(156, 'DD414OO', 29, 1, 15, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(157, 'DD415PP', 30, 1, 16, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(158, 'DD416QQ', 27, 1, 17, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(159, 'DD417RR', 28, 1, 18, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(160, 'DD418SS', 29, 1, 19, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(161, 'DD419TT', 30, 1, 20, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(162, 'EE500AA', 31, 2, 1, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(163, 'EE501BB', 32, 2, 2, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(164, 'EE502CC', 33, 2, 3, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(165, 'EE503DD', 34, 2, 4, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(166, 'EE504EE', 35, 2, 5, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(167, 'EE505FF', 36, 2, 6, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(168, 'EE506GG', 37, 2, 7, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(169, 'EE507HH', 38, 2, 8, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(170, 'EE508II', 39, 2, 9, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(171, 'EE509JJ', 40, 2, 10, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(172, 'EE510KK', 31, 2, 11, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(173, 'EE511LL', 32, 2, 12, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(174, 'EE512MM', 33, 2, 13, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(175, 'EE513NN', 34, 2, 14, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(176, 'EE514OO', 35, 2, 15, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(177, 'EE515PP', 36, 2, 16, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(178, 'EE516QQ', 37, 2, 17, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(179, 'EE517RR', 38, 2, 18, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(180, 'EE518SS', 39, 2, 19, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(181, 'EE519TT', 40, 2, 20, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05'),
(182, 'EE520UU', 31, 2, 21, 0, 'DISPONIBLE', '2026-12-31', '2027-06-30'),
(183, 'EE521VV', 32, 2, 22, 0, 'DISPONIBLE', '2026-11-30', '2027-05-30'),
(184, 'EE522WW', 33, 2, 23, 0, 'DISPONIBLE', '2026-10-15', '2027-04-15'),
(185, 'EE523XX', 34, 2, 24, 0, 'DISPONIBLE', '2026-09-20', '2027-03-20'),
(186, 'EE524YY', 35, 2, 25, 0, 'DISPONIBLE', '2026-12-01', '2027-06-01'),
(187, 'EE525ZZ', 36, 2, 26, 0, 'DISPONIBLE', '2026-08-10', '2027-02-10'),
(188, 'EE526AA', 37, 2, 27, 0, 'DISPONIBLE', '2026-12-25', '2027-07-01'),
(189, 'EE527BB', 38, 2, 28, 0, 'DISPONIBLE', '2026-11-15', '2027-05-15'),
(190, 'EE528CC', 39, 2, 29, 0, 'DISPONIBLE', '2026-10-10', '2027-04-10'),
(191, 'EE529DD', 40, 2, 30, 0, 'DISPONIBLE', '2026-09-05', '2027-03-05');

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
  MODIFY `id_alquiler` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=11;

--
-- AUTO_INCREMENT de la tabla `categorias_vehiculos`
--
ALTER TABLE `categorias_vehiculos`
  MODIFY `id_categoria` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `checkins_checkouts`
--
ALTER TABLE `checkins_checkouts`
  MODIFY `id_registro` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=2;

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
  MODIFY `id_marca` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=10;

--
-- AUTO_INCREMENT de la tabla `metodos_pagos`
--
ALTER TABLE `metodos_pagos`
  MODIFY `id_metodo_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `modelos`
--
ALTER TABLE `modelos`
  MODIFY `id_modelo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=41;

--
-- AUTO_INCREMENT de la tabla `opcionales_extras`
--
ALTER TABLE `opcionales_extras`
  MODIFY `id_extra` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=3;

--
-- AUTO_INCREMENT de la tabla `pagos`
--
ALTER TABLE `pagos`
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=42;

--
-- AUTO_INCREMENT de la tabla `roles`
--
ALTER TABLE `roles`
  MODIFY `id_rol` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=4;

--
-- AUTO_INCREMENT de la tabla `sucursales`
--
ALTER TABLE `sucursales`
  MODIFY `id_sucursal` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=49;

--
-- AUTO_INCREMENT de la tabla `traslados_vehiculos`
--
ALTER TABLE `traslados_vehiculos`
  MODIFY `id_traslado` int(11) NOT NULL AUTO_INCREMENT;

--
-- AUTO_INCREMENT de la tabla `usuarios`
--
ALTER TABLE `usuarios`
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=86;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=192;

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
