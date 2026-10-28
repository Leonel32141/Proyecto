-- phpMyAdmin SQL Dump
-- version 5.2.1
-- https://www.phpmyadmin.net/
--
-- Servidor: 127.0.0.1
-- Tiempo de generación: 28-10-2026 a las 04:53:23
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

CREATE DEFINER=`root`@`localhost` PROCEDURE `sp_insertar_alquiler` (IN `p_id_cliente` INT, IN `p_id_vehiculo` INT, IN `p_id_sucursal_retiro` INT, IN `p_id_sucursal_devolucion` INT, IN `p_fecha_reserva` DATETIME, IN `p_fecha_inicio_pautada` DATETIME, IN `p_fecha_fin_pautada` DATETIME, IN `p_es_oneway` TINYINT, IN `p_monto_total` DECIMAL(10,2), IN `p_monto_sena` DECIMAL(10,2), IN `p_estado_reserva` VARCHAR(50))   BEGIN
    INSERT INTO alquileres (
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
    ) 
    VALUES (
        p_id_cliente, 
        p_id_vehiculo, 
        p_id_sucursal_retiro, 
        p_id_sucursal_devolucion, 
        IFNULL(p_fecha_reserva, NOW()), 
        p_fecha_inicio_pautada, 
        p_fecha_fin_pautada, 
        p_es_oneway, 
        p_monto_total, 
        p_monto_sena, 
        IFNULL(NULLIF(p_estado_reserva, ''), 'CONFIRMADA')
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
    SELECT * FROM alquileres;
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
(18, 60, 2, 1, 1, '2026-10-28 00:51:11', '2026-10-28 03:00:00', '2026-10-29 03:00:00', 0, 55000.00, 55000.00, 'RESERVADA');

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
(60, '99999999', '99999999', 'cachimayo 112', NULL, '2000-11-11', 0);

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
(20, 18, 2, 55000.00, '2026-10-28 03:51:11', 'APROBADO', 'COMP-327117.0');

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
(1, 1, 'admin@autorent.com', 'hash_admin_123', 'Carlos', 'Gómez', '', '20-30111222-8', '1144556677', 0, 0, '2026-09-28 20:15:30', NULL),
(2, 2, 'empleado1@autorent.com', 'hash_emp_123', 'Lucía', 'Fernández', '', '27-35222333-4', '1155667788', 0, 0, '2026-09-28 20:15:30', NULL),
(3, 3, 'juan.perez@email.com', 'hash_cliente_1', 'Juan', 'Pérez', '', '20-38444555-9', '1166778899', 0, 0, '2026-09-28 20:15:30', NULL),
(4, 3, 'maria.lopez@email.com', 'hash_cliente_2', 'María', 'López', '', '27-39555666-1', '1177889900', 0, 0, '2026-09-28 20:15:30', NULL),
(46, 3, 'sebas@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Sebastian', 'Quintero', '12345680', '20957453504', '1111111111', 0, 0, '2026-10-19 22:36:53', NULL),
(50, 3, 'leo@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'leo', 'tello', '12345673', '20123456784', '8888888888', 0, 0, NULL, '2004-11-11'),
(60, 3, 'sebastian.quinteroet32@gmail.com', '8d969eef6ecad3c29a3a629280e686cf0c3f5d5a86aff3ca12020c923adc6c92', 'Sebastian', 'Quintero', '99999999', '20957453544', '1111111111', 0, 0, NULL, '2000-11-11');

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
(1, 'AF123JK', 3, 1, 1, 45000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(2, 'AE987MN', 1, 2, 1, 32000, 'ALQUILADO', '2027-03-15', '2026-12-31'),
(3, 'A321BCD', 2, 3, 2, 60000, 'DISPONIBLE', '2026-11-20', '2026-12-31'),
(4, 'CEN-001', 1, 3, 1, 12000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(5, 'CEN-004', 15, 3, 1, 30000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(6, 'CEN-005', 18, 2, 1, 15000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(7, 'CEN-006', 21, 3, 1, 22000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(8, 'CEN-008', 27, 1, 1, 5000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(9, 'CEN-009', 29, 1, 1, 3500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(10, 'CEN-010', 30, 1, 1, 4200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(14, 'CAB-001', 2, 2, 2, 14000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(15, 'CAB-002', 9, 2, 2, 45000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(16, 'CAB-003', 21, 3, 2, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(17, 'CAB-005', 28, 1, 2, 8000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(18, 'CAB-006', 30, 1, 2, 9500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(19, 'CAB-007', 14, 2, 2, 11200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(20, 'PAL-001', 1, 3, 3, 11000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(21, 'PAL-003', 10, 2, 3, 16000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(22, 'PAL-006', 27, 1, 3, 6200, 'ALQUILADO', '2027-01-01', '2026-12-31'),
(23, 'PAL-007', 29, 1, 3, 4100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(24, 'BEL-001', 4, 2, 4, 18000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(25, 'BEL-004', 18, 2, 4, 19500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(26, 'BEL-006', 28, 1, 4, 7200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(27, 'BEL-007', 24, 2, 4, 25000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(28, 'FLO-001', 6, 2, 5, 39000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(29, 'FLO-002', 14, 2, 5, 12000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(30, 'FLO-003', 21, 3, 5, 28000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(31, 'FLO-005', 23, 2, 5, 42000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(32, 'FLO-006', 27, 1, 5, 8900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(33, 'FLO-007', 30, 1, 5, 6300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(42, 'VCR-001', 2, 2, 6, 17000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(43, 'VCR-002', 10, 2, 6, 23000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(44, 'VCR-003', 15, 3, 6, 31000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(45, 'VCR-004', 18, 2, 6, 14000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(46, 'VCR-005', 21, 3, 6, 26000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(47, 'VCR-006', 28, 1, 6, 5400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(48, 'VCR-007', 30, 1, 6, 7100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(49, 'ALM-001', 1, 3, 7, 20000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(50, 'ALM-002', 9, 2, 7, 41000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(51, 'ALM-004', 24, 2, 7, 33000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(52, 'ALM-005', 27, 1, 7, 6100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(53, 'ALM-006', 29, 1, 7, 3900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(55, 'REC-001', 1, 3, 8, 13000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(56, 'REC-004', 15, 3, 8, 27000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(57, 'REC-006', 29, 1, 8, 2800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(58, 'REC-007', 30, 1, 8, 4900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(62, 'STE-001', 4, 2, 9, 35000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(63, 'STE-002', 10, 2, 9, 22000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(64, 'STE-003', 18, 2, 9, 18000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(65, 'STE-004', 21, 3, 9, 29000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(66, 'STE-005', 28, 1, 9, 9100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(67, 'STE-006', 30, 1, 9, 8200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(68, 'BAR-002', 6, 2, 10, 48000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(69, 'BAR-003', 14, 2, 10, 21000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(70, 'BAR-006', 27, 1, 10, 7500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(71, 'BAR-007', 30, 1, 10, 6800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(75, 'DEV-001', 2, 2, 11, 15000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(76, 'DEV-002', 9, 2, 11, 38000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(77, 'DEV-004', 21, 3, 11, 20000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(78, 'DEV-005', 28, 1, 11, 6700, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(79, 'DEV-006', 30, 1, 11, 5100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(81, 'NUN-001', 1, 3, 12, 16000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(82, 'NUN-003', 10, 2, 12, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(83, 'NUN-006', 27, 1, 12, 4800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(84, 'NUN-007', 29, 1, 12, 3300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(88, 'LIN-001', 4, 2, 13, 41000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(89, 'LIN-002', 6, 2, 13, 49000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(90, 'LIN-003', 15, 3, 13, 33000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(91, 'LIN-004', 23, 2, 13, 37000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(92, 'LIN-005', 28, 1, 13, 8500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(93, 'LIN-006', 30, 1, 13, 9200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(94, 'BOE-001', 2, 2, 14, 18000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(95, 'BOE-002', 10, 2, 14, 24000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(96, 'BOE-003', 18, 2, 14, 16000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(97, 'BOE-004', 21, 3, 14, 22000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(98, 'BOE-005', 24, 2, 14, 30000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(99, 'BOE-006', 27, 1, 14, 5900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(100, 'BOE-007', 30, 1, 14, 7300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(101, 'SAA-001', 1, 3, 15, 21000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(102, 'SAA-002', 9, 2, 15, 36000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(103, 'SAA-005', 28, 1, 15, 6400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(104, 'SAA-006', 29, 1, 15, 4100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(107, 'PPT-001', 6, 2, 16, 44000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(108, 'PPT-002', 14, 2, 16, 26000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(109, 'PPT-004', 21, 3, 16, 25000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(110, 'PPT-005', 27, 1, 16, 8100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(111, 'PPT-006', 30, 1, 16, 6900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(112, 'PPT-007', 26, 3, 16, 32000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(114, 'MAT-003', 15, 3, 17, 28000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(115, 'MAT-005', 28, 1, 17, 7800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(116, 'MAT-006', 30, 1, 17, 8800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(120, 'VUR-001', 2, 2, 18, 14000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(121, 'VUR-002', 7, 3, 18, 27000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(122, 'VUR-004', 18, 2, 18, 17000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(123, 'VUR-005', 21, 3, 18, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(124, 'VUR-006', 27, 1, 18, 5200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(125, 'VUR-007', 29, 1, 18, 3600, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(127, 'VLO-001', 1, 3, 19, 15000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(128, 'VLO-004', 15, 3, 19, 23000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(129, 'VLO-005', 28, 1, 19, 6100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(130, 'VLO-006', 30, 1, 19, 5400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(133, 'OLI-001', 2, 2, 20, 16000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(134, 'OLI-002', 10, 2, 20, 20000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(135, 'OLI-005', 21, 3, 20, 18000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(136, 'OLI-006', 27, 1, 20, 4300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(137, 'OLI-007', 29, 1, 20, 3100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(140, 'SSI-005', 29, 1, 21, 2100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(141, 'SSI-006', 30, 1, 21, 3800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(146, 'MAR-001', 1, 3, 22, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(147, 'MAR-002', 9, 2, 22, 33000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(148, 'MAR-003', 15, 3, 22, 24000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(149, 'MAR-005', 28, 1, 22, 5700, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(150, 'MAR-006', 30, 1, 22, 6200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(153, 'SFE-001', 4, 2, 23, 37000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(154, 'SFE-002', 10, 2, 23, 25000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(155, 'SFE-003', 18, 2, 23, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(156, 'SFE-005', 27, 1, 23, 7100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(157, 'SFE-006', 30, 1, 23, 8300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(159, 'TIG-006', 28, 1, 24, 5200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(160, 'TIG-007', 29, 1, 24, 4600, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(166, 'PIL-006', 27, 1, 25, 8900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(172, 'ESC-002', 9, 2, 26, 39000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(173, 'ESC-003', 15, 3, 26, 30000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(174, 'ESC-005', 28, 1, 26, 9400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(175, 'ESC-006', 30, 1, 26, 8100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(176, 'ESC-007', 24, 2, 26, 36000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(179, 'SMA-001', 2, 2, 27, 21000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(180, 'SMA-002', 10, 2, 27, 27000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(181, 'SMA-003', 18, 2, 27, 20000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(182, 'SMA-004', 21, 3, 27, 24000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(183, 'SMA-005', 27, 1, 27, 6300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(184, 'SMA-006', 30, 1, 27, 7200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(185, 'VBA-001', 4, 2, 28, 32000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(186, 'VBA-002', 6, 2, 28, 46000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(187, 'VBA-003', 14, 2, 28, 23000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(188, 'VBA-004', 23, 2, 28, 39000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(189, 'VBA-005', 28, 1, 28, 8800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(190, 'VBA-006', 30, 1, 28, 9100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(191, 'VBA-007', 26, 3, 28, 28000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(192, 'MOR-001', 6, 2, 29, 38000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(193, 'MOR-002', 10, 2, 29, 17000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(194, 'MOR-003', 15, 3, 29, 25000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(195, 'MOR-004', 24, 2, 29, 31000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(196, 'MOR-005', 28, 1, 29, 7400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(197, 'MOR-006', 30, 1, 29, 8900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(198, 'CAS-001', 2, 2, 30, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(199, 'CAS-002', 7, 3, 30, 28000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(200, 'CAS-004', 18, 2, 30, 15000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(201, 'CAS-005', 21, 3, 30, 23000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(202, 'CAS-006', 27, 1, 30, 5800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(203, 'CAS-007', 29, 1, 30, 4200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(205, 'RAM-001', 1, 3, 31, 17000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(206, 'RAM-002', 9, 2, 31, 34000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(207, 'RAM-003', 15, 3, 31, 22000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(208, 'RAM-005', 28, 1, 31, 6900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(209, 'RAM-006', 30, 1, 31, 5500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(211, 'SJU-001', 4, 2, 32, 43000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(212, 'SJU-002', 6, 2, 32, 51000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(213, 'SJU-003', 14, 2, 32, 29000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(214, 'SJU-004', 21, 3, 32, 31000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(215, 'SJU-005', 23, 2, 32, 44000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(216, 'SJU-006', 27, 1, 32, 9800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(217, 'SJU-007', 30, 1, 32, 8600, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(218, 'ITU-001', 2, 2, 33, 22000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(219, 'ITU-002', 10, 2, 33, 26000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(220, 'ITU-003', 18, 2, 33, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(221, 'ITU-005', 28, 1, 33, 7100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(222, 'ITU-006', 30, 1, 33, 6400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(224, 'MER-001', 6, 2, 34, 47000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(225, 'MER-002', 14, 2, 34, 33000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(226, 'MER-003', 21, 3, 34, 29000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(227, 'MER-004', 24, 2, 34, 38000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(228, 'MER-005', 27, 1, 34, 9100, 'ALQUILADO', '2027-01-01', '2026-12-31'),
(229, 'MER-006', 30, 1, 34, 9900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(230, 'MER-007', 26, 3, 34, 35000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(236, 'HUR-001', 2, 2, 36, 18000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(237, 'HUR-002', 9, 2, 36, 35000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(238, 'HUR-003', 15, 3, 36, 27000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(239, 'HUR-004', 18, 2, 36, 16000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(240, 'HUR-005', 27, 1, 36, 6200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(241, 'HUR-006', 30, 1, 36, 7500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(242, 'HUR-007', 23, 2, 36, 41000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(243, 'PAL-101', 4, 2, 37, 33000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(244, 'PAL-102', 10, 2, 37, 21000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(245, 'PAL-104', 21, 3, 37, 23000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(246, 'PAL-105', 28, 1, 37, 5900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(247, 'PAL-106', 30, 1, 37, 6800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(249, 'CAS-101', 6, 2, 38, 42000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(250, 'CAS-102', 14, 2, 38, 27000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(251, 'CAS-104', 24, 2, 38, 34000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(252, 'CAS-105', 27, 1, 38, 7700, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(253, 'CAS-106', 29, 1, 38, 4800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(256, 'AVE-001', 2, 2, 39, 19000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(257, 'AVE-002', 9, 2, 39, 41000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(258, 'AVE-005', 27, 1, 39, 5800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(259, 'AVE-006', 30, 1, 39, 3900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(262, 'QUI-001', 1, 3, 40, 18000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(263, 'QUI-003', 10, 2, 40, 22000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(264, 'QUI-004', 15, 3, 40, 26000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(265, 'QUI-005', 21, 3, 40, 20000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(266, 'QUI-006', 28, 1, 40, 6500, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(267, 'QUI-007', 30, 1, 40, 7200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(269, 'LAN-001', 4, 2, 41, 36000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(270, 'LAN-002', 6, 2, 41, 45000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(271, 'LAN-003', 14, 2, 41, 25000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(272, 'LAN-004', 18, 2, 41, 17000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(273, 'LAN-005', 27, 1, 41, 8200, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(274, 'LAN-006', 29, 1, 41, 5100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(275, 'LDE-001', 2, 2, 42, 16000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(276, 'LDE-004', 21, 3, 42, 24000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(277, 'LDE-005', 24, 2, 42, 33000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(278, 'LDE-006', 28, 1, 42, 7100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(279, 'LDE-007', 30, 1, 42, 8400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(282, 'BAN-001', 1, 3, 43, 21000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(283, 'BAN-002', 10, 2, 43, 23000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(284, 'BAN-004', 23, 2, 43, 38000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(285, 'BAN-005', 27, 1, 43, 6000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(286, 'BAN-006', 30, 1, 43, 6700, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(288, 'TEM-001', 6, 2, 44, 41000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(289, 'TEM-002', 9, 2, 44, 37000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(290, 'TEM-003', 15, 3, 44, 28000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(291, 'TEM-004', 18, 2, 44, 18000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(292, 'TEM-005', 28, 1, 44, 7900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(293, 'TEM-006', 30, 1, 44, 9300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(294, 'TEM-007', 26, 3, 44, 31000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(295, 'ADR-001', 2, 2, 45, 17000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(296, 'ADR-002', 7, 3, 45, 25000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(297, 'ADR-004', 21, 3, 45, 21000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(298, 'ADR-005', 27, 1, 45, 5300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(299, 'ADR-006', 29, 1, 45, 3800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(301, 'BER-001', 4, 2, 46, 39000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(302, 'BER-002', 10, 2, 46, 28000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(303, 'BER-003', 14, 2, 46, 31000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(304, 'BER-005', 28, 1, 46, 8600, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(305, 'BER-006', 30, 1, 46, 9100, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(308, 'FVA-001', 6, 2, 47, 48000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(309, 'FVA-003', 15, 3, 47, 30000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(310, 'FVA-004', 24, 2, 47, 42000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(311, 'FVA-005', 27, 1, 47, 9400, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(312, 'FVA-006', 30, 1, 47, 9800, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(314, 'EZE-005', 21, 3, 48, 23000, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(315, 'EZE-006', 28, 1, 48, 6300, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(316, 'EZE-007', 30, 1, 48, 5900, 'DISPONIBLE', '2027-01-01', '2026-12-31'),
(317, 'FER488', 31, 1, 1, 5000, 'ALQUILADO', '2026-12-31', '2026-12-31'),
(318, 'URU666', 32, 1, 1, 8000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(319, 'BMWM40', 33, 1, 1, 12000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(320, 'R8V100', 34, 1, 1, 15000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(321, 'POR911', 35, 1, 1, 9000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(322, 'AMG630', 36, 1, 1, 11000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(323, 'CORV88', 37, 1, 1, 4000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(324, 'ASTON1', 38, 1, 1, 10000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(325, 'MASER1', 39, 1, 1, 13000, 'DISPONIBLE', '2026-12-31', '2026-12-31'),
(326, 'BENTL1', 40, 1, 1, 7000, 'DISPONIBLE', '2026-12-31', '2026-12-31');

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
  MODIFY `id_alquiler` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=19;

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
  MODIFY `id_pago` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=21;

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
  MODIFY `id_usuario` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=61;

--
-- AUTO_INCREMENT de la tabla `vehiculos`
--
ALTER TABLE `vehiculos`
  MODIFY `id_vehiculo` int(11) NOT NULL AUTO_INCREMENT, AUTO_INCREMENT=327;

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
