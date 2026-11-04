document.addEventListener("DOMContentLoaded", async function() {
    const vehiculoGuardado = localStorage.getItem("vehiculoAlquiler");
    
    if (!vehiculoGuardado) {
        alert("No se ha seleccionado ningún vehículo de lujo.");
        window.location.href = "index.html";
        return;
    }

    const auto = JSON.parse(vehiculoGuardado);
    const vehiculoId = auto.id || auto.idVehiculo || auto.id_vehiculo;

    let alquileresExistentesLujo = [];
    try {
        const [respAlq, respVeh] = await Promise.all([
            fetch("api/alquileres"),
            fetch("api/vehiculos")
        ]);
        
        if (respAlq.ok) {
            const todosLosAlquileres = await respAlq.json();
            
            try {
                const todosLosVehiculos = await respVeh.json();
                
                // Filtramos solo las unidades físicas VIP que estén DISPONIBLES (ignora mantenimiento)
                const idsVehiculosDelModelo = todosLosVehiculos
                    .filter(v => parseInt(v.idModelo || v.id_modelo) === parseInt(vehiculoId))
                    .filter(v => (v.estado || "DISPONIBLE").toUpperCase() === "DISPONIBLE")
                    .map(v => parseInt(v.idVehiculo || v.id_vehiculo));

                alquileresExistentesLujo = todosLosAlquileres.filter(a => {
                    const vId = parseInt(a.idVehiculo || a.vehiculoId || a.id_vehiculo || 0);
                    return idsVehiculosDelModelo.includes(vId);
                });
            } catch (e) {
                alquileresExistentesLujo = todosLosAlquileres.filter(a => {
                    const vId = parseInt(a.idVehiculo || a.vehiculoId || a.id_vehiculo || 0);
                    return vId === parseInt(vehiculoId);
                });
            }
        }
    } catch (err) {
        console.warn("No se pudieron cargar los alquileres VIP previos:", err);
    }

    // Aviso visual de fechas ocupadas VIP
    const contenedorAviso = document.getElementById("avisoFechasOcupadas");
    const listaOcupadas = document.getElementById("listaFechasOcupadas");
    
    if (alquileresExistentesLujo.length > 0 && contenedorAviso && listaOcupadas) {
        listaOcupadas.innerHTML = "";
        alquileresExistentesLujo.forEach(alq => {
            const rawIni = alq.fechaInicioPautada || alq.fechaInicio || alq.fecha_inicio_pautada;
            const rawFin = alq.fechaFinPautada || alq.fechaFin || alq.fecha_fin_pautada;
            if (rawIni && rawFin) {
                const fIni = rawIni.split('T')[0];
                const fFin = rawFin.split('T')[0];
                const li = document.createElement("li");
                li.textContent = `Desde el ${fIni} hasta el ${fFin}`;
                listaOcupadas.appendChild(li);
            }
        });
        contenedorAviso.style.display = "block";
    }

    document.getElementById("nombreAuto").textContent = auto.modelo;
    document.getElementById("tipoAuto").textContent = auto.tipo || "Alta Gama VIP";
    document.getElementById("precioAuto").textContent = auto.precioStr || `$${Number(auto.precio).toLocaleString()} / día`;
    
    const imgAuto = document.getElementById("imgAuto");
    const imgInterior = document.getElementById("imgInterior");

    if (auto.imagen) {
        imgAuto.src = auto.imagen; 
        const partesRuta = auto.imagen.split('/');
        const nombreArchivo = partesRuta[partesRuta.length - 1];
        imgInterior.src = `fotos_interiores/${nombreArchivo}`;
        
        imgAuto.onerror = function() { this.src = 'foto_vehiculos/PORSCHE_911.jpg'; };
        imgInterior.onerror = function() { this.src = `fotos_interiores/${nombreArchivo}`; };
    }

    const hoy = new Date().toISOString().split('T')[0];
    const inputFechaInicio = document.getElementById("fechaInicio");
    const inputFechaFin = document.getElementById("fechaFin");

    inputFechaInicio.min = hoy;
    inputFechaFin.min = hoy;

    function seSolapaConAlquilerLujo(fInicio, fFin) {
        const selInicio = new Date(fInicio);
        const selFin = new Date(fFin);

        for (let alq of alquileresExistentesLujo) {
            const rawIni = alq.fechaInicioPautada || alq.fechaInicio || alq.fecha_inicio_pautada;
            const rawFin = alq.fechaFinPautada || alq.fechaFin || alq.fecha_fin_pautada;
            if (!rawIni || !rawFin) continue;

            const ocupadoInicio = new Date(rawIni.split('T')[0]);
            const ocupadoFin = new Date(rawFin.split('T')[0]);

            if (selInicio <= ocupadoFin && selFin >= ocupadoInicio) {
                return true;
            }
        }
        return false;
    }

    [inputFechaInicio, inputFechaFin].forEach(input => {
        input.addEventListener("click", function() {
            if (typeof this.showPicker === 'function') {
                this.showPicker();
            }
        });
    });

    inputFechaInicio.addEventListener("change", function() {
        inputFechaFin.min = inputFechaInicio.value;
        if (inputFechaFin.value && inputFechaFin.value < inputFechaInicio.value) {
            inputFechaFin.value = inputFechaInicio.value;
        }
        calcularTotal();
    });

    inputFechaFin.addEventListener("change", function() {
        if (inputFechaInicio.value && inputFechaFin.value) {
            if (seSolapaConAlquilerLujo(inputFechaInicio.value, inputFechaFin.value)) {
                alert("⚠️ Atención VIP: Este vehículo de alta gama ya se encuentra reservado en parte del rango seleccionado.");
                inputFechaFin.value = inputFechaInicio.value;
            }
        }
        calcularTotal();
    });

    const checkboxesExtras = document.querySelectorAll(".extra-opcion");
    const totalFinalSpan = document.getElementById("totalFinal");
    const formTransaccion = document.getElementById("formTransaccion");

    let precioBaseNumerico = Number(auto.precio);
    if (isNaN(precioBaseNumerico) || precioBaseNumerico <= 0) {
        precioBaseNumerico = Number(String(auto.precioStr || "").replace(/[^0-9]/g, '')) || 250000;
    }

    function calcularTotal() {
        const valInicio = inputFechaInicio.value;
        const valFin = inputFechaFin.value;

        let dias = 1;

        if (valInicio && valFin) {
            const fechaInicio = new Date(valInicio);
            const fechaFin = new Date(valFin);

            if (fechaFin >= fechaInicio) {
                const diferenciaTiempo = fechaFin - fechaInicio;
                dias = Math.max(1, Math.ceil(diferenciaTiempo / (1000 * 60 * 60 * 24)));
            }
        }

        let subtotalBase = precioBaseNumerico * dias;
        let totalExtras = 0;

        checkboxesExtras.forEach(chk => {
            if (chk.checked) {
                const valorExtra = Number(chk.value);
                if (valorExtra === 8000) {
                    totalExtras += valorExtra;
                } else {
                    totalExtras += valorExtra * dias;
                }
            }
        });

        const granTotal = subtotalBase + totalExtras;
        totalFinalSpan.textContent = `$${granTotal.toLocaleString('es-AR')}`;
        return granTotal;
    }

    checkboxesExtras.forEach(chk => chk.addEventListener("change", calcularTotal));
    calcularTotal();

    formTransaccion.addEventListener("submit", async function(e) {
        e.preventDefault();

        if (!inputFechaInicio.value || !inputFechaFin.value) {
            alert("Por favor selecciona las fechas de retiro y devolución VIP.");
            return;
        }

        if (seSolapaConAlquilerLujo(inputFechaInicio.value, inputFechaFin.value)) {
            alert("❌ No se puede avanzar: el auto VIP seleccionado ya está reservado en esas fechas.");
            return;
        }

        const datosReservaLujo = {
            vehiculo: auto,
            fechaInicio: inputFechaInicio.value,
            fechaFin: inputFechaFin.value,
            montoTotal: calcularTotal()
        };

        localStorage.setItem("datosTransaccionLujo", JSON.stringify(datosReservaLujo));
        window.location.href = "pago_lujo.html";
    });
});