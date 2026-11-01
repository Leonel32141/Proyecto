const SUCURSALES_MAP = {
    1: "Central - Microcentro", 2: "Sucursal Caballito", 3: "Sucursal Palermo", 4: "Sucursal Belgrano",
    5: "Sucursal Flores", 6: "Sucursal Villa Crespo", 7: "Sucursal Almagro", 8: "Sucursal Recoleta",
    9: "Sucursal San Telmo", 10: "Sucursal Barracas", 11: "Sucursal Devoto", 12: "Sucursal Núñez",
    13: "Sucursal Liniers", 14: "Sucursal Boedo", 15: "Sucursal Saavedra", 16: "Sucursal Parque Patricios",
    17: "Sucursal Mataderos", 18: "Sucursal Villa Urquiza", 19: "Sucursal Vicente López", 20: "Sucursal Olivos",
    21: "Sucursal San Isidro", 22: "Sucursal Martínez", 23: "Sucursal San Fernando", 24: "Sucursal Tigre",
    25: "Sucursal Pilar", 26: "Sucursal Escobar", 27: "Sucursal San Martín", 28: "Sucursal Villa Ballester",
    29: "Sucursal Morón", 30: "Sucursal Castelar", 31: "Sucursal Ramos Mejía", 32: "Sucursal San Justo",
    33: "Sucursal Ituzaingó", 34: "Sucursal Merlo", 35: "Sucursal Moreno", 36: "Sucursal Hurlingham",
    37: "Sucursal El Palomar", 38: "Sucursal Caseros", 39: "Sucursal Avellaneda", 40: "Sucursal Quilmes",
    41: "Sucursal Lanús", 42: "Sucursal Lomas de Zamora", 43: "Sucursal Banfield", 44: "Sucursal Temperley",
    45: "Sucursal Adrogué", 46: "Sucursal Berazategui", 47: "Sucursal Florencio Varela", 48: "Sucursal Ezeiza"
};

const MODELOS_CATALOGO = {
    1: { nombre: "Corolla 2023", precio: 60000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/COROLLA_2023.jpg" },
    2: { nombre: "Yaris 2022", precio: 52000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/YARIS_2022.jpg" },
    3: { nombre: "Hilux 2024", precio: 90000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/HILUX_2024.jpg" },
    4: { nombre: "Etios 2021", precio: 40000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/ETIOS_2021.jpg" },
    5: { nombre: "Ranger 2023", precio: 88000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/RANGER_2023.jpg" },
    6: { nombre: "Ka 2021", precio: 38000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/KA_2021.jpg" },
    7: { nombre: "Focus 2020", precio: 48000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/FOCUS_2020.jpg" },
    8: { nombre: "Territory 2023", precio: 82000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/TERRITORY_2023.jpg" },
    9: { nombre: "Gol Trend 2022", precio: 45000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/GOL_TREND_2022.jpg" },
    10: { nombre: "Polo 2023", precio: 55000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/POLO_2023.jpg" },
    11: { nombre: "Amarok 2024", precio: 85000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/AMAROK_2024.jpg" },
    12: { nombre: "T-Cross 2023", precio: 78000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/T_CROSS_2023.jpg" },
    13: { nombre: "Taos 2024", precio: 95000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/TAOS_2024.jpg" },
    14: { nombre: "Onix 2022", precio: 46000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/ONIX_2022.jpg" },
    15: { nombre: "Cruze 2023", precio: 65000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/CRUZE_2023.jpg" },
    16: { nombre: "Tracker 2023", precio: 76000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/TRACKER_2023.jpg" },
    17: { nombre: "S10 2024", precio: 87000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/S10_2024.jpg" },
    18: { nombre: "208 2023", precio: 53000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/208_2023.jpg" },
    19: { nombre: "2008 2022", precio: 62000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/2008_2022.jpg" },
    20: { nombre: "3008 2023", precio: 92000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/3008_2023.jpg" },
    21: { nombre: "Cronos 2023", precio: 49000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/CRONOS_2023.jpg" },
    22: { nombre: "Toro 2024", precio: 84000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/TORO_2024.jpg" },
    23: { nombre: "Mobi 2022", precio: 35000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/MOBI_2022.jpg" },
    24: { nombre: "Sandero 2022", precio: 44000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/SANDERO_2022.jpg" },
    25: { nombre: "Duster 2023", precio: 74000, categoriaId: 3, categoriaNombre: "CAMIONETA", imagen: "foto_vehiculos/DUSTER_2023.jpg" },
    26: { nombre: "Logan 2021", precio: 42000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/LOGAN_2021.jpg" },
    27: { nombre: "XR 150L 2023", precio: 25000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/XR150L_2023.jpg" },
    28: { nombre: "Wave 110S 2023", precio: 18000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/WAVE110S_2023.jpg" },
    29: { nombre: "CB300R 2024", precio: 32000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/CB300R_2024.jpg" },
    30: { nombre: "YBR 125 2022", precio: 20000, categoriaId: 1, categoriaNombre: "ECONOMICO", imagen: "foto_vehiculos/YBR125_2022.jpg" },
    31: { nombre: "Ferrari 488 GTB 2017", precio: 280000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/FERRARI_488.jpg" },
    32: { nombre: "Lamborghini Urus 2019", precio: 260000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/LAMBO_URUS.jpg" },
    33: { nombre: "BMW M4 Competition 2018", precio: 210000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/BMW_M4.jpg" },
    34: { nombre: "Audi R8 V10 2016", precio: 230000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/AUDI_R8.jpg" },
    35: { nombre: "Porsche 911 Turbo S 2018", precio: 250000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/PORSCHE_911.jpg" },
    36: { nombre: "Mercedes-AMG G 63 2019", precio: 240000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/AMG_G63.jpg" },
    37: { nombre: "Chevrolet Corvette C8 2020", precio: 220000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/CORVETTE_C8.jpg" },
    38: { nombre: "Aston Martin Vantage 2017", precio: 235000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/ASTON_VANTAGE.jpg" },
    39: { nombre: "Maserati Levante Trofeo 2018", precio: 215000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/MASERATI_LEVANTE.jpg" },
    40: { nombre: "Bentley Continental GT 2016", precio: 245000, categoriaId: 2, categoriaNombre: "SEDAN", imagen: "foto_vehiculos/BENTLEY_GT.jpg" }
};

document.addEventListener("DOMContentLoaded", async () => {
    const nombreUsuario = localStorage.getItem('usuarioNombre');
    if (nombreUsuario) {
        const adminNombreEl = document.getElementById("adminNombre");
        if (adminNombreEl) adminNombreEl.textContent = nombreUsuario;
    }

    const inputPatente = document.getElementById("nuevaPatente");
    if (inputPatente) {
        inputPatente.addEventListener("input", (e) => {
            e.target.value = e.target.value.toUpperCase();
        });
    }

    const selectModelo = document.getElementById("nuevoModelo");
    const inputCategoriaVisual = document.getElementById("infoCategoriaVisual");

    if (selectModelo) {
        selectModelo.addEventListener("change", (e) => {
            const idMod = parseInt(e.target.value);
            if (idMod && MODELOS_CATALOGO[idMod]) {
                inputCategoriaVisual.value = MODELOS_CATALOGO[idMod].categoriaNombre;
            } else {
                inputCategoriaVisual.value = "Se selecciona automáticamente...";
            }
        });
    }

    const hoy = new Date().toISOString().split('T')[0];
    const inputSeguro = document.getElementById("nuevoVencSeguro");
    const inputPatenteFecha = document.getElementById("nuevoVencPatente");
    if (inputSeguro) inputSeguro.min = hoy;
    if (inputPatenteFecha) inputPatenteFecha.min = hoy;

    configurarSelectorFecha("nuevoVencSeguro", "displayVencSeguro");
    configurarSelectorFecha("nuevoVencPatente", "displayVencPatente");

    await cargarDatosAdmin();

    const formVehiculo = document.getElementById("formNuevoVehiculo");
    if (formVehiculo) {
        formVehiculo.addEventListener("submit", async (e) => {
            e.preventDefault();

            const idModSeleccionado = parseInt(selectModelo.value);
            const idSuc = parseInt(document.getElementById("nuevaSucursal").value);
            const catFinal = MODELOS_CATALOGO[idModSeleccionado] ? MODELOS_CATALOGO[idModSeleccionado].categoriaId : 1;

            const nuevoVehiculo = {
                patente: inputPatente.value.trim(),
                idModelo: idModSeleccionado,
                idCategoria: catFinal,
                idSucursalActual: idSuc,
                vencimientoSeguro: document.getElementById("nuevoVencSeguro").value,
                vencimientoPatente: document.getElementById("nuevoVencPatente").value,
                estado: "DISPONIBLE"
            };

            try {
                const respuesta = await fetch("api/vehiculos", {
                    method: "POST",
                    headers: { "Content-Type": "application/json" },
                    body: JSON.stringify(nuevoVehiculo)
                });

                if (respuesta.ok) {
                    alert("¡Vehículo incorporado a la flota con éxito!");
                    formVehiculo.reset();
                    inputCategoriaVisual.value = "Se selecciona automáticamente...";
                    document.getElementById("displayVencSeguro").value = "";
                    document.getElementById("displayVencPatente").value = "";
                    await cargarDatosAdmin();
                } else {
                    alert("Error al registrar el vehículo en el servidor.");
                }
            } catch (error) {
                console.error("Error de red:", error);
                alert("No se pudo conectar con el servidor backend.");
            }
        });
    }
});

function configurarSelectorFecha(idInputDate, idInputDisplay) {
    const inputDate = document.getElementById(idInputDate);
    const inputDisplay = document.getElementById(idInputDisplay);

    if (inputDate && inputDisplay) {
        inputDate.addEventListener("change", (e) => {
            const val = e.target.value;
            if (val) {
                const [anio, mes, dia] = val.split("-");
                inputDisplay.value = `${dia}/${mes}/${anio}`;
            } else {
                inputDisplay.value = "";
            }
        });
    }
}

async function cargarDatosAdmin() {
    try {
        let alquileres = [];
        const respuestaAlquileres = await fetch("api/alquileres");
        if (respuestaAlquileres.ok) {
            alquileres = await respuestaAlquileres.json();
            document.getElementById("statAlquileres").textContent = alquileres.length;
            
            let ingresosTotales = alquileres.reduce((acc, curr) => acc + (curr.montoTotal || curr.total || 0), 0);
            document.getElementById("statIngresos").textContent = `$${ingresosTotales.toLocaleString('es-AR')}`;

            const tbody = document.getElementById("tablaReservasBody");
            if (tbody) {
                if (alquileres.length > 0) {
                    tbody.innerHTML = alquileres.map(alq => `
                        <tr>
                            <td>#${alq.id || alq.idAlquiler || alq.id_alquiler || Math.floor(Math.random() * 90000 + 10000)}</td>
                            <td>Cliente #${alq.idCliente || alq.usuarioId || alq.id_cliente}</td>
                            <td>Vehículo #${alq.idVehiculo || alq.vehiculoId || alq.id_vehiculo}</td>
                            <td>${(alq.fechaInicioPautada || alq.fechaInicio || alq.fecha_inicio_pautada || '').split('T')[0]}</td>
                            <td style="color: #16a34a; font-weight: 600;">$${(alq.montoTotal || alq.totalFinal || alq.monto_total || 0).toLocaleString('es-AR')}</td>
                            <td><span style="background: #dcfce7; color: #166534; padding: 4px 8px; border-radius: 4px; font-size: 0.8rem; font-weight: 600;">${alq.estadoReserva || alq.estado_reserva || 'RESERVADA'}</span></td>
                        </tr>
                    `).join('');
                } else {
                    tbody.innerHTML = `<tr><td colspan="6" style="text-align: center; color: #94a3b8;">No hay reservas registradas todavía.</td></tr>`;
                }
            }
        }

        const respuestaVehiculos = await fetch("api/vehiculos");
        if (respuestaVehiculos.ok) {
            const vehiculos = await respuestaVehiculos.json();
            document.getElementById("statVehiculos").textContent = vehiculos.length;

            const gridMonitoreo = document.getElementById("gridMonitoreoFlota");
            if (gridMonitoreo) {
                if (vehiculos.length > 0) {
                    const gruposModelos = {};
                    
                    vehiculos.forEach(v => {
                        const vehiculoId = parseInt(v.id || v.idVehiculo || v.id_vehiculo);
                        const idMod = parseInt(v.idModelo || v.modeloId || v.id_modelo);
                        let nombreModelo = v.modelo || v.nombreModelo || v.nombre || v.descripcion;
                        let precioRef = v.precio || v.precioPorDia || 0;
                        let imagenRef = v.imagen || v.foto || "foto_vehiculos/auto_nuevo.jpg";

                        if (idMod && MODELOS_CATALOGO[idMod]) {
                            nombreModelo = MODELOS_CATALOGO[idMod].nombre;
                            if (!precioRef || precioRef === 0) precioRef = MODELOS_CATALOGO[idMod].precio;
                            if (!imagenRef || imagenRef === "foto_vehiculos/auto_nuevo.jpg") {
                                imagenRef = MODELOS_CATALOGO[idMod].imagen;
                            }
                        }

                        if (!nombreModelo || nombreModelo.trim() === "") {
                            nombreModelo = idMod ? `Modelo ID #${idMod}` : `Flota General`;
                        }

                        if (!gruposModelos[nombreModelo]) {
                            gruposModelos[nombreModelo] = {
                                imagen: imagenRef,
                                precio: precioRef,
                                unidades: []
                            };
                        }
                        gruposModelos[nombreModelo].unidades.push({ ...v, vehiculoIdReal: vehiculoId });
                    });

                    gridMonitoreo.innerHTML = Object.entries(gruposModelos).map(([nombreModelo, datos]) => {
                        const totalUnidades = datos.unidades.length;
                        
                        const listaUnidadesHtml = datos.unidades.map(v => {
                            const vId = v.vehiculoIdReal;
                            const patente = v.patente || "N/A";
                            
                            const alquileresActivos = alquileres.filter(a => {
                                const alqVehiculoId = parseInt(a.idVehiculo || a.vehiculoId || a.id_vehiculo || 0);
                                return alqVehiculoId === vId;
                            });
                            
                            if (alquileresActivos.length > 0) {
                                const bloquesAlquileres = alquileresActivos.map(alertaAlq => {
                                    const fechaInicio = (alertaAlq.fechaInicioPautada || alertaAlq.fechaInicio || alertaAlq.fecha_inicio_pautada || '').split('T')[0];
                                    const fechaFin = (alertaAlq.fechaFinPautada || alertaAlq.fechaFin || alertaAlq.fecha_fin_pautada || '').split('T')[0];
                                    return `
                                        <div style="font-size: 0.8rem; color: #b91c1c; margin-top: 2px;">
                                            🔴 <strong>Alquilado:</strong> ${fechaInicio} al ${fechaFin}
                                        </div>
                                    `;
                                }).join('');

                                return `
                                    <div class="unit-item-row" style="background: #fff1f2; border-left: 4px solid #ef4444; flex-direction: column; align-items: flex-start; padding: 8px;">
                                        <div style="display: flex; justify-content: space-between; width: 100%; align-items: center;">
                                            <span><strong>Patente:</strong> ${patente}</span>
                                            <span class="badge-estado-admin badge-alq">CON RESERVAS</span>
                                        </div>
                                        <div style="width: 100%; margin-top: 4px; border-top: 1px solid #fecdd3; padding-top: 4px;">
                                            ${bloquesAlquileres}
                                        </div>
                                    </div>
                                `;
                            } else if (v.estado === 'EN_MANTENIMIENTO' || v.estado === 'MANTENIMIENTO') {
                                return `
                                    <div class="unit-item-row" style="background: #fefce8; border-left: 4px solid #eab308; display: flex; justify-content: space-between; align-items: center; padding: 8px;">
                                        <div>
                                            <span><strong>Patente:</strong> ${patente}</span><br>
                                            <span style="font-size: 0.8rem; color: #854d0e;">🛠️ En Taller / Mantenimiento</span>
                                        </div>
                                        <button onclick="cambiarEstadoVehiculo(${vId}, 'DISPONIBLE')" style="background: #22c55e; color: #fff; border: none; padding: 4px 8px; border-radius: 4px; cursor: pointer; font-size: 0.75rem; font-weight: bold;">Liberar ✅</button>
                                    </div>
                                `;
                            } else {
                                const sucursalActualId = v.idSucursalActual || v.idSucursal || v.sucursalId || v.id_sucursal_actual || 1;
                                return `
                                    <div class="unit-item-row" style="background: #f0fdf4; border-left: 4px solid #22c55e; display: flex; justify-content: space-between; align-items: center; padding: 8px;">
                                        <div>
                                            <span><strong>Patente:</strong> ${patente}</span><br>
                                            <span style="font-size: 0.8rem; color: #166534;">Sucursal ID: #${sucursalActualId}</span>
                                        </div>
                                        <button onclick="cambiarEstadoVehiculo(${vId}, 'EN_MANTENIMIENTO')" style="background: #eab308; color: #000; border: none; padding: 4px 8px; border-radius: 4px; cursor: pointer; font-size: 0.75rem; font-weight: bold;">Mantenimiento 🛠️</button>
                                    </div>
                                `;
                            }
                        }).join('');

                        return `
                            <div class="catalog-card">
                                <img src="${datos.imagen}" alt="${nombreModelo}" onerror="this.src='foto_vehiculos/auto_nuevo.jpg'">
                                <div class="catalog-card-body">
                                    <h4>${nombreModelo}</h4>
                                    <span class="precio-tag">$${Number(datos.precio).toLocaleString('es-AR')} / día</span>
                                    <p style="font-size: 0.8rem; color: #64748b; margin: 4px 0;">Total en flota: <strong>${totalUnidades}</strong></p>
                                    <div class="units-container">
                                        ${listaUnidadesHtml}
                                    </div>
                                </div>
                            </div>
                        `;
                    }).join('');

                } else {
                    gridMonitoreo.innerHTML = `<p style="color: #94a3b8;">No hay vehículos registrados en la flota actualmente.</p>`;
                }
            }
        }

    } catch (error) {
        console.warn("Error al cargar los datos del admin:", error);
    }
}

async function cambiarEstadoVehiculo(idVehiculo, nuevoEstado) {
    if (!confirm(`¿Estás seguro de cambiar el estado de la unidad #${idVehiculo} a ${nuevoEstado}?`)) {
        return;
    }

    try {
        const respuestaGet = await fetch("api/vehiculos");
        if (!respuestaGet.ok) throw new Error("No se pudo obtener la flota.");
        
        const vehiculos = await respuestaGet.json();
        const vehiculoActual = vehiculos.find(v => parseInt(v.id || v.idVehiculo || v.id_vehiculo) === parseInt(idVehiculo));

        if (!vehiculoActual) {
            alert("No se encontró el vehículo en el servidor.");
            return;
        }

        const vehiculoActualizado = {
            idVehiculo: parseInt(idVehiculo),
            patente: vehiculoActual.patente,
            idModelo: parseInt(vehiculoActual.idModelo || vehiculoActual.id_modelo),
            idCategoria: parseInt(vehiculoActual.idCategoria || vehiculoActual.id_categoria),
            idSucursalActual: parseInt(vehiculoActual.idSucursalActual || vehiculoActual.id_sucursal_actual || 1),
            vencimientoSeguro: vehiculoActual.vencimientoSeguro || vehiculoActual.vencimiento_seguro,
            vencimientoPatente: vehiculoActual.vencimientoPatente || vehiculoActual.vencimiento_patente,
            estado: nuevoEstado
        };

        const respuestaPut = await fetch(`api/vehiculos`, {
            method: "PUT",
            headers: { "Content-Type": "application/json" },
            body: JSON.stringify(vehiculoActualizado)
        });

        if (respuestaPut.ok) {
            alert(`¡Vehículo actualizado con éxito a ${nuevoEstado}!`);
            await cargarDatosAdmin();
        } else {
            alert("Error al actualizar el estado del vehículo en el servidor.");
        }
    } catch (error) {
        console.error("Error de red:", error);
        alert("Hubo un error de conexión al actualizar el vehículo.");
    }
}

function cerrarSesionAdmin() {
    localStorage.clear();
    window.location.href = "login.html";
}