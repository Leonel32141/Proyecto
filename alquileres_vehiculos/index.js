// Cargar usuario activo
let usuarioActivo = JSON.parse(localStorage.getItem('usuario_activo'));
const saludoUsuario = document.getElementById('saludoUsuario');
const btnLogout = document.getElementById('btnLogout');

if (usuarioActivo) {
    saludoUsuario.textContent = `Hola, ${usuarioActivo.nombre} ${usuarioActivo.apellido}`;
    btnLogout.style.display = 'inline-block';
} else {
    saludoUsuario.innerHTML = `Hola, Invitado | <a href="login.html" style="color: #60a5fa;">Iniciar Sesión</a>`;
}

btnLogout.addEventListener('click', function() {
    localStorage.removeItem('usuario_activo');
    window.location.href = 'login.html';
});

// LISTA COMPLETA DISTRIBUIDA POR MARCAS Y SECCIONES INDEPENDIENTES
const flotaGeneral = [
    // Volkswagen
    { nombre: "VW Amarok 2024", tipo: "SUV / Pickup", precio: "$85.000 / día", img: "foto_vehiculos/AMAROK_2024.jpg", contenedor: "catalogoVW" },
    { nombre: "VW Gol Trend 2022", tipo: "Auto", precio: "$45.000 / día", img: "foto_vehiculos/GOL_TREND_2022.jpg", contenedor: "catalogoVW" },
    { nombre: "VW Polo 2023", tipo: "Auto", precio: "$55.000 / día", img: "foto_vehiculos/POLO_2023.jpg", contenedor: "catalogoVW" },
    { nombre: "VW Taos 2024", tipo: "SUV", precio: "$95.000 / día", img: "foto_vehiculos/TAOS_2024.jpg", contenedor: "catalogoVW" },
    
    // Toyota
    { nombre: "Toyota Hilux 2024", tipo: "SUV / Pickup", precio: "$90.000 / día", img: "foto_vehiculos/HILUX_2024.jpg", contenedor: "catalogoToyota" },
    { nombre: "Toyota Corolla 2023", tipo: "Auto", precio: "$60.000 / día", img: "foto_vehiculos/COROLLA_2023.jpg", contenedor: "catalogoToyota" },
    { nombre: "Toyota Yaris 2022", tipo: "Auto", precio: "$52.000 / día", img: "foto_vehiculos/YARIS_2022.jpg", contenedor: "catalogoToyota" },
    { nombre: "Toyota Etios 2021", tipo: "Auto", precio: "$40.000 / día", img: "foto_vehiculos/ETIOS_2021.jpg", contenedor: "catalogoToyota" },
    
    // Ford
    { nombre: "Ford Ranger 2023", tipo: "SUV / Pickup", precio: "$88.000 / día", img: "foto_vehiculos/RANGER_2023.jpg", contenedor: "catalogoFord" },
    { nombre: "Ford Focus 2020", tipo: "Auto", precio: "$48.000 / día", img: "foto_vehiculos/FOCUS_2020.jpg", contenedor: "catalogoFord" },
    { nombre: "Ford Ka 2021", tipo: "Auto", precio: "$38.000 / día", img: "foto_vehiculos/KA_2021.jpg", contenedor: "catalogoFord" },
    { nombre: "Ford Territory 2023", tipo: "SUV", precio: "$82.000 / día", img: "foto_vehiculos/TERRITORY_2023.jpg", contenedor: "catalogoFord" },
    
    // Chevrolet
    { nombre: "Chevrolet S10 2024", tipo: "SUV / Pickup", precio: "$87.000 / día", img: "foto_vehiculos/S10_2024.jpg", contenedor: "catalogoChevrolet" },
    { nombre: "Chevrolet Cruze 2023", tipo: "Auto", precio: "$62.000 / día", img: "foto_vehiculos/CRUZE_2023.jpg", contenedor: "catalogoChevrolet" },
    { nombre: "Chevrolet Onix 2022", tipo: "Auto", precio: "$46.000 / día", img: "foto_vehiculos/ONIX_2022.jpg", contenedor: "catalogoChevrolet" },
    { nombre: "Chevrolet Tracker 2023", tipo: "SUV", precio: "$70.000 / día", img: "foto_vehiculos/TRACKER_2023.jpg", contenedor: "catalogoChevrolet" },

    // Fiat
    { nombre: "Fiat Cronos 2023", tipo: "Auto", precio: "$50.000 / día", img: "foto_vehiculos/CRONOS_2023.jpg", contenedor: "catalogoFiat" },
    { nombre: "Fiat Toro 2024", tipo: "SUV / Pickup", precio: "$80.000 / día", img: "foto_vehiculos/TORO_2024.jpg", contenedor: "catalogoFiat" },
    { nombre: "Fiat Mobi 2022", tipo: "Auto", precio: "$35.000 / día", img: "foto_vehiculos/MOBI_2022.jpg", contenedor: "catalogoFiat" },

    // Renault
    { nombre: "Renault Sandero 2022", tipo: "Auto", precio: "$42.000 / día", img: "foto_vehiculos/SANDERO_2022.jpg", contenedor: "catalogoRenault" },
    { nombre: "Renault Duster 2023", tipo: "SUV", precio: "$72.000 / día", img: "foto_vehiculos/DUSTER_2023.jpg", contenedor: "catalogoRenault" },
    { nombre: "Renault Logan 2021", tipo: "Auto", precio: "$40.000 / día", img: "foto_vehiculos/LOGAN_2021.jpg", contenedor: "catalogoRenault" },

    // Peugeot
    { nombre: "Peugeot 208 2023", tipo: "Auto", precio: "$52.000 / día", img: "foto_vehiculos/208_2023.jpg", contenedor: "catalogoPeugeot" },
    { nombre: "Peugeot 2008 2022", tipo: "SUV", precio: "$65.000 / día", img: "foto_vehiculos/2008_2022.jpg", contenedor: "catalogoPeugeot" },
    { nombre: "Peugeot 3008 2023", tipo: "SUV", precio: "$95.000 / día", img: "foto_vehiculos/3008_2023.jpg", contenedor: "catalogoPeugeot" },

    // Motos Exclusivas
    { nombre: "Honda Wave 110S", tipo: "Moto", precio: "$18.000 / día", img: "foto_vehiculos/WAVE110S_2023.jpg", contenedor: "catalogoMotos" },
    { nombre: "Honda XR 150L", tipo: "Moto Enduro", precio: "$24.000 / día", img: "foto_vehiculos/XR150L_2023.jpg", contenedor: "catalogoMotos" },
    { nombre: "Yamaha YBR 125", tipo: "Moto", precio: "$20.000 / día", img: "foto_vehiculos/YBR125_2022.jpg", contenedor: "catalogoMotos" },
    { nombre: "Honda CB300R 2024", tipo: "Moto Naked", precio: "$28.000 / día", img: "foto_vehiculos/CB300R_2024.jpg", contenedor: "catalogoMotos" }
];

// Renderizar Flota General por su respectiva marca
flotaGeneral.forEach(auto => {
    const contenedor = document.getElementById(auto.contenedor);
    if (contenedor) {
        let card = document.createElement('div');
        card.className = 'card-vehiculo';
        card.innerHTML = `
            <img src="${auto.img}" alt="${auto.nombre}" style="width: 100%; height: 160px; object-fit: cover;" onerror="this.style.display='none'">
            <div class="card-body">
                <h3>${auto.nombre}</h3>
                <p class="tipo">${auto.tipo}</p>
                <p class="precio">${auto.precio}</p>
                <button class="btn-alquilar" onclick="alert('Seleccionaste ${auto.nombre}')">Alquilar</button>
            </div>
        `;
        contenedor.appendChild(card);
    }
});

// FLOTA DE LUJO Y ALTA GAMA (Junta en su apartado)
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
let tieneAccesoLujo = usuarioActivo && usuarioActivo.accesoLujo;

if (tieneAccesoLujo) {
    avisoLujo.style.display = 'none';
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
    avisoLujo.style.display='block';
    contenedorLujo.innerHTML = `
        <div class="bloqueado-box">
            <p>Contenido exclusivo para usuarios mayores de 25 años registrados.</p>
        </div>
    `;
}