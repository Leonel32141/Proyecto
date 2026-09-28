CREATE DATABASE IF NOT EXISTS bd_alquiler_vehiculos;
USE bd_alquiler_vehiculos;

-- 1. ROLES
CREATE TABLE roles (
    id_rol INT AUTO_INCREMENT PRIMARY KEY,
    nombre_rol VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT
) ENGINE=InnoDB;

-- 2. USUARIOS
CREATE TABLE usuarios (
    id_usuario INT AUTO_INCREMENT PRIMARY KEY,
    id_rol INT NOT NULL,
    email VARCHAR(150) NOT NULL UNIQUE,
    password_hash VARCHAR(255) NOT NULL,
    nombre VARCHAR(100) NOT NULL,
    apellido VARCHAR(100) NOT NULL,
    cuil VARCHAR(20) NOT NULL UNIQUE,
    telefono VARCHAR(30),
    intentos_fallidos INT DEFAULT 0,
    cuenta_bloqueada BOOLEAN DEFAULT FALSE,
    fecha_creacion DATETIME DEFAULT CURRENT_TIMESTAMP,
    CONSTRAINT fk_usuarios_roles FOREIGN KEY (id_rol) 
        REFERENCES roles(id_rol)
) ENGINE=InnoDB;

-- 3. CLIENTES
CREATE TABLE clientes (
    id_cliente INT PRIMARY KEY,
    dni VARCHAR(20) NOT NULL UNIQUE,
    licencia_conducir VARCHAR(50) NOT NULL,
    domicilio VARCHAR(200) NOT NULL,
    foto_identificacion_url VARCHAR(255),
    CONSTRAINT fk_clientes_usuarios FOREIGN KEY (id_cliente) 
        REFERENCES usuarios(id_usuario) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 4. SUCURSALES
CREATE TABLE sucursales (
    id_sucursal INT AUTO_INCREMENT PRIMARY KEY,
    nombre VARCHAR(100) NOT NULL,
    direccion VARCHAR(200) NOT NULL,
    ciudad VARCHAR(100) NOT NULL,
    activa BOOLEAN DEFAULT TRUE
) ENGINE=InnoDB;

-- 5. MARCAS
CREATE TABLE marcas (
    id_marca INT AUTO_INCREMENT PRIMARY KEY,
    nombre_marca VARCHAR(50) NOT NULL UNIQUE
) ENGINE=InnoDB;

-- 6. MODELOS
CREATE TABLE modelos (
    id_modelo INT AUTO_INCREMENT PRIMARY KEY,
    id_marca INT NOT NULL,
    nombre_modelo VARCHAR(50) NOT NULL,
    anio INT,
    imagen VARCHAR(100),
    CONSTRAINT fk_modelos_marcas FOREIGN KEY (id_marca) 
        REFERENCES marcas(id_marca)
) ENGINE=InnoDB;

-- 7. CATEGORIAS_VEHICULOS
CREATE TABLE categorias_vehiculos (
    id_categoria INT AUTO_INCREMENT PRIMARY KEY,
    nombre_categoria VARCHAR(50) NOT NULL UNIQUE,
    descripcion TEXT,
    tarifa_base_dia DECIMAL(10, 2) NOT NULL
) ENGINE=InnoDB;

-- 8. VEHICULOS
CREATE TABLE vehiculos (
    id_vehiculo INT AUTO_INCREMENT PRIMARY KEY,
    patente VARCHAR(15) NOT NULL UNIQUE,
    id_modelo INT NOT NULL,
    id_categoria INT NOT NULL,
    id_sucursal_actual INT NOT NULL,
    kilometraje_actual INT DEFAULT 0,
    estado ENUM('DISPONIBLE', 'ALQUILADO', 'EN_MANTENIMIENTO', 'REPARACION') DEFAULT 'DISPONIBLE',
    vencimiento_seguro DATE NOT NULL,
    vencimiento_patente DATE NOT NULL,
    CONSTRAINT fk_vehiculos_modelos FOREIGN KEY (id_modelo) 
        REFERENCES modelos(id_modelo),
    CONSTRAINT fk_vehiculos_categorias FOREIGN KEY (id_categoria) 
        REFERENCES categorias_vehiculos(id_categoria),
    CONSTRAINT fk_vehiculos_sucursales FOREIGN KEY (id_sucursal_actual) 
        REFERENCES sucursales(id_sucursal)
) ENGINE=InnoDB;

-- 9. TRASLADOS_VEHICULOS (Relación M:N entre Vehículos y Sucursales)
CREATE TABLE traslados_vehiculos (
    id_traslado INT AUTO_INCREMENT PRIMARY KEY,
    id_vehiculo INT NOT NULL,
    id_sucursal_origen INT NOT NULL,
    id_sucursal_destino INT NOT NULL,
    id_usuario_chofer INT NOT NULL,
    fecha_salida DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_llegada DATETIME,
    observaciones TEXT,
    CONSTRAINT fk_traslados_vehiculos FOREIGN KEY (id_vehiculo) 
        REFERENCES vehiculos(id_vehiculo),
    CONSTRAINT fk_traslados_origen FOREIGN KEY (id_sucursal_origen) 
        REFERENCES sucursales(id_sucursal),
    CONSTRAINT fk_traslados_destino FOREIGN KEY (id_sucursal_destino) 
        REFERENCES sucursales(id_sucursal),
    CONSTRAINT fk_traslados_chofer FOREIGN KEY (id_usuario_chofer) 
        REFERENCES usuarios(id_usuario)
) ENGINE=InnoDB;

-- 10. MANTENIMIENTOS
CREATE TABLE mantenimientos (
    id_mantenimiento INT AUTO_INCREMENT PRIMARY KEY,
    id_vehiculo INT NOT NULL,
    tipo_mantenimiento ENUM('PREVENTIVO', 'CORRECTIVO', 'SERVICE_OFICIAL', 'REPARACION_MECANICA') NOT NULL,
    fecha_ingreso DATE NOT NULL,
    fecha_egreso_estimada DATE,
    costo_total DECIMAL(10, 2) DEFAULT 0.00,
    taller_mecanico VARCHAR(100),
    detalle_trabajo TEXT,
    CONSTRAINT fk_mantenimientos_vehiculos FOREIGN KEY (id_vehiculo) 
        REFERENCES vehiculos(id_vehiculo)
) ENGINE=InnoDB;

-- 11. ALQUILERES
CREATE TABLE alquileres (
    id_alquiler INT AUTO_INCREMENT PRIMARY KEY,
    id_cliente INT NOT NULL,
    id_vehiculo INT NOT NULL,
    id_sucursal_retiro INT NOT NULL,
    id_sucursal_devolucion INT NOT NULL,
    fecha_reserva DATETIME DEFAULT CURRENT_TIMESTAMP,
    fecha_inicio_pautada DATETIME NOT NULL,
    fecha_fin_pautada DATETIME NOT NULL,
    es_oneway BOOLEAN DEFAULT FALSE,
    monto_total DECIMAL(12, 2) NOT NULL,
    monto_sena DECIMAL(12, 2) NOT NULL,
    estado_reserva ENUM('RESERVADA', 'EN_CURSO', 'FINALIZADA', 'CANCELADA') DEFAULT 'RESERVADA',
    CONSTRAINT fk_alquileres_clientes FOREIGN KEY (id_cliente) 
        REFERENCES clientes(id_cliente),
    CONSTRAINT fk_alquileres_vehiculos FOREIGN KEY (id_vehiculo) 
        REFERENCES vehiculos(id_vehiculo),
    CONSTRAINT fk_alquileres_sucursal_retiro FOREIGN KEY (id_sucursal_retiro) 
        REFERENCES sucursales(id_sucursal),
    CONSTRAINT fk_alquileres_sucursal_devolucion FOREIGN KEY (id_sucursal_devolucion) 
        REFERENCES sucursales(id_sucursal)
) ENGINE=InnoDB;

-- 12. OPCIONALES_EXTRAS
CREATE TABLE opcionales_extras (
    id_extra INT AUTO_INCREMENT PRIMARY KEY,
    nombre_extra VARCHAR(50) NOT NULL,
    descripcion TEXT,
    precio_diario DECIMAL(8, 2) NOT NULL
) ENGINE=InnoDB;

-- 13. ALQUILERES_EXTRAS (Tabla Intermedia M:N)
CREATE TABLE alquileres_extras (
    id_alquiler INT NOT NULL,
    id_extra INT NOT NULL,
    cantidad INT DEFAULT 1,
    PRIMARY KEY (id_alquiler, id_extra),
    CONSTRAINT fk_ae_alquileres FOREIGN KEY (id_alquiler) 
        REFERENCES alquileres(id_alquiler) ON DELETE CASCADE,
    CONSTRAINT fk_ae_extras FOREIGN KEY (id_extra) 
        REFERENCES opcionales_extras(id_extra)
) ENGINE=InnoDB;

-- 14. CHECKINS_CHECKOUTS
CREATE TABLE checkins_checkouts (
    id_registro INT AUTO_INCREMENT PRIMARY KEY,
    id_alquiler INT NOT NULL,
    id_usuario_empleado INT NOT NULL,
    tipo ENUM('ENTREGA', 'DEVOLUCION') NOT NULL,
    fecha_hora_real DATETIME DEFAULT CURRENT_TIMESTAMP,
    kilometraje INT NOT NULL,
    nivel_combustible ENUM('RESERVA', '1/4', '1/2', '3/4', 'LLENO') NOT NULL,
    observaciones TEXT,
    CONSTRAINT fk_checkins_alquileres FOREIGN KEY (id_alquiler) 
        REFERENCES alquileres(id_alquiler),
    CONSTRAINT fk_checkins_usuarios FOREIGN KEY (id_usuario_empleado) 
        REFERENCES usuarios(id_usuario)
) ENGINE=InnoDB;

-- 15. FOTOS_INSPECCIONES
CREATE TABLE fotos_inspecciones (
    id_foto INT AUTO_INCREMENT PRIMARY KEY,
    id_registro INT NOT NULL,
    foto_url VARCHAR(255) NOT NULL,
    observacion_dano TEXT,
    CONSTRAINT fk_fotos_checkins FOREIGN KEY (id_registro) 
        REFERENCES checkins_checkouts(id_registro) ON DELETE CASCADE
) ENGINE=InnoDB;

-- 16. METODOS_PAGOS (Catálogo)
CREATE TABLE metodos_pagos (
    id_metodo_pago INT AUTO_INCREMENT PRIMARY KEY,
    nombre_metodo VARCHAR(50) NOT NULL UNIQUE,
    requiere_comprobante BOOLEAN DEFAULT FALSE
) ENGINE=InnoDB;

-- 17. PAGOS (Historial de Transacciones)
CREATE TABLE pagos (
    id_pago INT AUTO_INCREMENT PRIMARY KEY,
    id_alquiler INT NOT NULL,
    id_metodo_pago INT NOT NULL,
    monto DECIMAL(12, 2) NOT NULL,
    fecha_pago DATETIME DEFAULT CURRENT_TIMESTAMP,
    estado_pago ENUM('PENDIENTE', 'APROBADO', 'RECHAZADO') DEFAULT 'APROBADO',
    numero_comprobante VARCHAR(100),
    CONSTRAINT fk_pagos_alquileres FOREIGN KEY (id_alquiler) 
        REFERENCES alquileres(id_alquiler),
    CONSTRAINT fk_pagos_metodos FOREIGN KEY (id_metodo_pago) 
        REFERENCES metodos_pagos(id_metodo_pago)
) ENGINE=InnoDB;
