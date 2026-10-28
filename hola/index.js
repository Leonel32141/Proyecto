document.addEventListener("DOMContentLoaded", function() {
    const saludoUsuario = document.getElementById('saludoUsuario');
    const btnLogout = document.getElementById('btnLogout');

    // 1. Recuperar los datos del usuario logueado desde localStorage
    const nombre = localStorage.getItem('usuarioNombre');
    const apellido = localStorage.getItem('usuarioApellido') || '';
    const fechaNacimientoStr = localStorage.getItem('usuarioFechaNacimiento');

    let usuarioActual = null;

    if (nombre) {
        let edad = 0;
        if (fechaNacimientoStr) {
            const anioNacimiento = new Date(fechaNacimientoStr).getFullYear();
            const anioActual = new Date().getFullYear();
            edad = anioActual - anioNacimiento;
        }

        usuarioActual = {
            nombre: nombre,
            apellido: apellido,
            edad: edad > 0 ? edad : 26
        };
    }

    // 2. Mostrar el saludo en la barra de navegación
    if (usuarioActual && usuarioActual.nombre) {
        if (saludoUsuario) {
            saludoUsuario.textContent = `Hola, ${usuarioActual.nombre} ${usuarioActual.apellido}`;
        }
        if (btnLogout) {
            btnLogout.style.display = 'inline-block';
        }
    } else {
        if (saludoUsuario) {
            saludoUsuario.innerHTML = `Hola, Invitado | <a href="login.html" style="color: #60a5fa;">Iniciar Sesión</a>`;
        }
        if (btnLogout) {
            btnLogout.style.display = 'none';
        }
    }

    // 3. Manejo del botón de salida (Logout)
    if (btnLogout) {
        btnLogout.addEventListener('click', function() {
            localStorage.clear();
            sessionStorage.clear();
            window.location.href = 'login.html';
        });
    }

    // 4. Renderizar Flota General por Marcas
    renderizarFlotaGeneral();

    // 5. Renderizar Flota de Lujo y Alta Gama evaluando la edad
    renderizarFlotaLujo(usuarioActual);
});

// ==========================================
// FLOTA GENERAL ORGANIZADA POR MARCAS
// ==========================================
function renderizarFlotaGeneral() {
    const listaFlotaGeneral = [
        // Volkswagen
        { id: 1, nombre: "VW Amarok 2024", tipo: "SUV / Pickup", precio: 85000, precioStr: "$85.000 / día", img: "foto_vehiculos/AMAROK_2024.jpg", contenedor: "catalogoVW" },
        { id: 2, nombre: "VW Gol Trend 2022", tipo: "Auto", precio: 45000, precioStr: "$45.000 / día", img: "foto_vehiculos/GOL_TREND_2022.jpg", contenedor: "catalogoVW" },
        { id: 3, nombre: "VW Polo 2023", tipo: "Auto", precio: 55000, precioStr: "$55.000 / día", img: "foto_vehiculos/POLO_2023.jpg", contenedor: "catalogoVW" },
        { id: 4, nombre: "VW Taos 2024", tipo: "SUV", precio: 95000, precioStr: "$95.000 / día", img: "foto_vehiculos/TAOS_2024.jpg", contenedor: "catalogoVW" },
        { id: 5, nombre: "VW T-Cross 2023", tipo: "SUV", precio: 78000, precioStr: "$78.000 / día", img: "foto_vehiculos/T_CROSS_2023.jpg", contenedor: "catalogoVW" },

        // Toyota
        { id: 6, nombre: "Toyota Hilux 2024", tipo: "SUV / Pickup", precio: 90000, precioStr: "$90.000 / día", img: "foto_vehiculos/HILUX_2024.jpg", contenedor: "catalogoToyota" },
        { id: 7, nombre: "Toyota Corolla 2023", tipo: "Auto", precio: 60000, precioStr: "$60.000 / día", img: "foto_vehiculos/COROLLA_2023.jpg", contenedor: "catalogoToyota" },
        { id: 8, nombre: "Toyota Yaris 2022", tipo: "Auto", precio: 52000, precioStr: "$52.000 / día", img: "foto_vehiculos/YARIS_2022.jpg", contenedor: "catalogoToyota" },
        { id: 9, nombre: "Toyota Etios 2021", tipo: "Auto", precio: 40000, precioStr: "$40.000 / día", img: "foto_vehiculos/ETIOS_2021.jpg", contenedor: "catalogoToyota" },

        // Ford
        { id: 10, nombre: "Ford Ranger 2023", tipo: "SUV / Pickup", precio: 88000, precioStr: "$88.000 / día", img: "foto_vehiculos/RANGER_2023.jpg", contenedor: "catalogoFord" },
        { id: 11, nombre: "Ford Focus 2020", tipo: "Auto", precio: 48000, precioStr: "$48.000 / día", img: "foto_vehiculos/FOCUS_2020.jpg", contenedor: "catalogoFord" },
        { id: 12, nombre: "Ford Ka 2021", tipo: "Auto", precio: 38000, precioStr: "$38.000 / día", img: "foto_vehiculos/KA_2021.jpg", contenedor: "catalogoFord" },
        { id: 13, nombre: "Ford Territory 2023", tipo: "SUV", precio: 82000, precioStr: "$82.000 / día", img: "foto_vehiculos/TERRITORY_2023.jpg", contenedor: "catalogoFord" },

        // Chevrolet
        { id: 14, nombre: "Chevrolet S10 2024", tipo: "SUV / Pickup", precio: 87000, precioStr: "$87.000 / día", img: "foto_vehiculos/S10_2024.jpg", contenedor: "catalogoChevrolet" },
        { id: 15, nombre: "Chevrolet Cruze 2023", tipo: "Auto", precio: 62000, precioStr: "$62.000 / día", img: "foto_vehiculos/CRUZE_2023.jpg", contenedor: "catalogoChevrolet" },
        { id: 16, nombre: "Chevrolet Onix 2022", tipo: "Auto", precio: 46000, precioStr: "$46.000 / día", img: "foto_vehiculos/ONIX_2022.jpg", contenedor: "catalogoChevrolet" },
        { id: 17, nombre: "Chevrolet Tracker 2023", tipo: "SUV", precio: 70000, precioStr: "$70.000 / día", img: "foto_vehiculos/TRACKER_2023.jpg", contenedor: "catalogoChevrolet" },

        // Fiat
        { id: 18, nombre: "Fiat Cronos 2023", tipo: "Auto", precio: 50000, precioStr: "$50.000 / día", img: "foto_vehiculos/CRONOS_2023.jpg", contenedor: "catalogoFiat" },
        { id: 19, nombre: "Fiat Toro 2024", tipo: "SUV / Pickup", precio: 80000, precioStr: "$80.000 / día", img: "foto_vehiculos/TORO_2024.jpg", contenedor: "catalogoFiat" },
        { id: 20, nombre: "Fiat Mobi 2022", tipo: "Auto", precio: 35000, precioStr: "$35.000 / día", img: "foto_vehiculos/MOBI_2022.jpg", contenedor: "catalogoFiat" },

        // Renault
        { id: 21, nombre: "Renault Sandero 2022", tipo: "Auto", precio: 42000, precioStr: "$42.000 / día", img: "foto_vehiculos/SANDERO_2022.jpg", contenedor: "catalogoRenault" },
        { id: 22, nombre: "Renault Duster 2023", tipo: "SUV", precio: 72000, precioStr: "$72.000 / día", img: "foto_vehiculos/DUSTER_2023.jpg", contenedor: "catalogoRenault" },
        { id: 23, nombre: "Renault Logan 2021", tipo: "Auto", precio: 40000, precioStr: "$40.000 / día", img: "foto_vehiculos/LOGAN_2021.jpg", contenedor: "catalogoRenault" },

        // Peugeot
        { id: 24, nombre: "Peugeot 208 2023", tipo: "Auto", precio: 52000, precioStr: "$52.000 / día", img: "foto_vehiculos/208_2023.jpg", contenedor: "catalogoPeugeot" },
        { id: 25, nombre: "Peugeot 2008 2022", tipo: "SUV", precio: 65000, precioStr: "$65.000 / día", img: "foto_vehiculos/2008_2022.jpg", contenedor: "catalogoPeugeot" },
        { id: 26, nombre: "Peugeot 3008 2023", tipo: "SUV", precio: 95000, precioStr: "$95.000 / día", img: "foto_vehiculos/3008_2023.jpg", contenedor: "catalogoPeugeot" },

        // Motos
        { id: 27, nombre: "Honda Wave 110S", tipo: "Moto", precio: 18000, precioStr: "$18.000 / día", img: "foto_vehiculos/WAVE110S_2023.jpg", contenedor: "catalogoMotos" },
        { id: 28, nombre: "Honda XR 150L", tipo: "Moto Enduro", precio: 24000, precioStr: "$24.000 / día", img: "foto_vehiculos/XR150L_2023.jpg", contenedor: "catalogoMotos" },
        { id: 29, nombre: "Yamaha YBR 125", tipo: "Moto", precio: 20000, precioStr: "$20.000 / día", img: "foto_vehiculos/YBR125_2022.jpg", contenedor: "catalogoMotos" },
        { id: 30, nombre: "Honda CB300R 2024", tipo: "Moto Naked", precio: 28000, precioStr: "$28.000 / día", img: "foto_vehiculos/CB300R_2024.jpg", contenedor: "catalogoMotos" }
    ];

    listaFlotaGeneral.forEach(auto => {
        const contenedor = document.getElementById(auto.contenedor);
        if (contenedor) {
            let card = document.createElement('div');
            card.className = 'card-vehiculo';
            card.innerHTML = `
                <img src="${auto.img}" alt="${auto.nombre}" style="width: 100%; height: 160px; object-fit: cover;" onerror="this.src='fotos_decoracion/decoracion_1.jpg'">
                <div class="card-body">
                    <h3>${auto.nombre}</h3>
                    <p class="tipo">${auto.tipo}</p>
                    <p class="precio">${auto.precioStr}</p>
                    <button class="btn-alquilar" id="btn-alquiler-${auto.id}">Alquilar</button>
                </div>
            `;
            contenedor.appendChild(card);

            const btnAlquilar = card.querySelector('.btn-alquilar');
            btnAlquilar.addEventListener('click', async () => {
                const clienteCompletado = localStorage.getItem("clienteCompletado");

                if (!clienteCompletado) {
                    alert("Es tu primera vez alquilando. Por favor completa tus datos personales.");
                    window.location.href = "perfil-cliente.html";
                    return;
                }

                const idClienteLogueado = localStorage.getItem('usuarioId') || localStorage.getItem('id_usuario');
                
                if (idClienteLogueado) {
                    try {
                        const respuesta = await fetch(`api/alquileres?idClienteActivo=${idClienteLogueado}`);
                        
                        if (respuesta.ok) {
                            const resultado = await respuesta.json();
                            
                            if (resultado.tieneActivo === true) {
                                alert("⚠️ ¡Atención! Ya cuentas con un alquiler vigente y activo. Debes finalizarlo antes de solicitar un nuevo vehículo.");
                                return; 
                            }
                        }
                    } catch (error) {
                        console.error("Error al verificar alquiler activo:", error);
                    }
                }

                const vehiculoData = {
                    id: auto.id,
                    modelo: auto.nombre,
                    tipo: auto.tipo,
                    precio: auto.precio,
                    imagen: auto.img
                };
                localStorage.setItem("vehiculoAlquiler", JSON.stringify(vehiculoData));
                window.location.href = "transaccion.html";
            });
        }
    });
}

// ==========================================
// FLOTA DE LUJO Y ALTA GAMA (> 25 AÑOS)
// ==========================================
function renderizarFlotaLujo(usuario) {
    const autosLujo = [
        { nombre: "Porsche 911 Carrera", anio: "2024", precio: "$150.000 / día", img: "foto_vehiculos/PORSCHE_911.jpg" },
        { nombre: "Mercedes-Benz AMG G63", anio: "2024", precio: "$180.000 / día", img: "foto_vehiculos/AMG_G63.jpg" },
        { nombre: "Audi R8 Spyder", anio: "2023", precio: "$165.000 / día", img: "foto_vehiculos/AUDI_R8.jpg" },
        { nombre: "Aston Martin Vantage", anio: "2023", precio: "$190.000 / día", img: "foto_vehiculos/ASTON_VANTAGE.jpg" },
        { nombre: "Bentley Continental GT", anio: "2024", precio: "$210.000 / día", img: "foto_vehiculos/BENTLEY_GT.jpg" },
        { nombre: "BMW M4 Competition", anio: "2023", precio: "$140.000 / día", img: "foto_vehiculos/BMW_M4.jpg" },
        { nombre: "Corvette C8", anio: "2024", precio: "$155.000 / día", img: "foto_vehiculos/CORVETTE_C8.jpg" },
        { nombre: "Ferrari 488 Spider", anio: "2023", precio: "$250.000 / día", img: "foto_vehiculos/FERRARI_488.jpg" },
        { nombre: "Lamborghini Urus", anio: "2024", precio: "$230.000 / día", img: "foto_vehiculos/LAMBO_URUS.jpg" },
        { nombre: "Maserati Levante", anio: "2023", precio: "$170.000 / día", img: "foto_vehiculos/MASERATI_LEVANTE.jpg" }
    ];

    const contenedorLujo = document.getElementById('catalogoLujo');
    const avisoLujo = document.getElementById('avisoLujo');

    let tieneAccesoLujo = usuario && usuario.edad >= 25; 

    if (tieneAccesoLujo && contenedorLujo) {
        if (avisoLujo) avisoLujo.style.display = 'none';
        contenedorLujo.innerHTML = '';
        autosLujo.forEach(auto => {
            let card = document.createElement('div');
            card.className = 'card-vehiculo lujo';
            card.innerHTML = `
                <img src="${auto.img}" alt="${auto.nombre}" style="width: 100%; height: 160px; object-fit: cover;" onerror="this.style.display='none'">
                <div class="card-body">
                    <span class="badge-lujo">Alta Gama</span>
                    <h3>${auto.nombre} (${auto.anio})</h3>
                    <p class="precio">${auto.precio}</p>
                    <button class="btn-lujo" onclick="window.location.href='reservar_lujo.html'">Reservar Lujo</button>
                </div>
            `;
            contenedorLujo.appendChild(card);
        });
    } else {
        if (avisoLujo) avisoLujo.style.display = 'block';
        if (contenedorLujo) {
            contenedorLujo.innerHTML = `
                <div class="bloqueado-box" style="grid-column: 1 / -1; text-align: center; padding: 20px; background: #f8fafc; border-radius: 8px;">
                    <p style="color: #64748b;">Contenido exclusivo para usuarios mayores de 25 años registrados e iniciados sesión.</p>
                </div>
            `;
        }
    }
}