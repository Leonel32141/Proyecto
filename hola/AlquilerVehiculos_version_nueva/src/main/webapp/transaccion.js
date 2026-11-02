document.addEventListener("DOMContentLoaded", async () => {
    const autoJson = localStorage.getItem("vehiculoAlquiler");

    if (!autoJson) {
        alert("No se encontró ningún vehículo seleccionado.");
        window.location.href = "index.html";
        return;
    }

    const autoSeleccionado = JSON.parse(autoJson);
    const vehiculoId = autoSeleccionado.id || autoSeleccionado.idVehiculo || autoSeleccionado.id_vehiculo;

    // 1. Cargar alquileres y vehículos para verificar stock físico y descartar unidades en mantenimiento
    let alquileresExistentes = [];
    try {
        const [respAlq, respVeh] = await Promise.all([
            fetch("api/alquileres"),
            fetch("api/vehiculos")
        ]);
        
        if (respAlq.ok) {
            const todosLosAlquileres = await respAlq.json();
            
            try {
                const todosLosVehiculos = await respVeh.json();
                
                // Filtramos solo las unidades físicas del modelo que estén en estado DISPONIBLE (ignora mantenimiento)
                const idsVehiculosDelModelo = todosLosVehiculos
                    .filter(v => parseInt(v.idModelo || v.id_modelo) === parseInt(vehiculoId))
                    .filter(v => (v.estado || "DISPONIBLE").toUpperCase() === "DISPONIBLE")
                    .map(v => parseInt(v.idVehiculo || v.id_vehiculo));

                alquileresExistentes = todosLosAlquileres.filter(a => {
                    const vId = parseInt(a.idVehiculo || a.vehiculoId || a.id_vehiculo || 0);
                    return idsVehiculosDelModelo.includes(vId);
                });
            } catch (e) {
                alquileresExistentes = todosLosAlquileres.filter(a => {
                    const vId = parseInt(a.idVehiculo || a.vehiculoId || a.id_vehiculo || 0);
                    return vId === parseInt(vehiculoId);
                });
            }
        }
    } catch (err) {
        console.warn("No se pudieron cargar los alquileres previos para validación de fechas:", err);
    }

    // Mostrar visualmente las fechas ocupadas en la interfaz si las hay
    const contenedorAviso = document.getElementById("avisoFechasOcupadas");
    const listaOcupadas = document.getElementById("listaFechasOcupadas");
    
    if (alquileresExistentes.length > 0 && contenedorAviso && listaOcupadas) {
        listaOcupadas.innerHTML = "";
        alquileresExistentes.forEach(alq => {
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

    // 2. Cargamos imágenes y detectamos si es moto
    const tipoVehiculo = (autoSeleccionado.tipo || "").toLowerCase();
    const esMoto = tipoVehiculo.includes("moto");

    document.getElementById("imgAuto").src = autoSeleccionado.imagen || "foto_vehiculos/decoracion_1.jpg";
    
    const cajaInterior = document.getElementById("cajaInterior");
    const cajaExtras = document.getElementById("cajaExtras");
    const cajaVehiculo = document.getElementById("cajaVehiculo");

    if (esMoto) {
        if (cajaInterior) cajaInterior.style.display = "none";
        if (cajaExtras) cajaExtras.style.display = "none";
        if (cajaVehiculo) cajaVehiculo.style.width = "100%";
    } else {
        const partesRuta = autoSeleccionado.imagen ? autoSeleccionado.imagen.split('/') : ["decoracion_1.jpg"];
        const nombreArchivo = partesRuta[partesRuta.length - 1];
        document.getElementById("imgInterior").src = `fotos_interiores/${nombreArchivo}`;
    }

    document.getElementById("nombreAuto").textContent = autoSeleccionado.modelo;
    document.getElementById("tipoAuto").textContent = autoSeleccionado.tipo;
    document.getElementById("precioAuto").textContent = `$${autoSeleccionado.precio.toLocaleString()} / día`;

    // 3. Fechas dinámicas y restricciones
    const inputInicio = document.getElementById("fechaInicio");
    const inputFin = document.getElementById("fechaFin");
    const spanTotal = document.getElementById("totalFinal");
    const checkboxesExtras = document.querySelectorAll(".extra-opcion");

    const hoy = new Date().toISOString().split("T")[0];
    inputInicio.min = hoy;
    inputInicio.value = hoy;
    inputFin.min = hoy;

    function seSolapaConAlquiler(fInicio, fFin) {
        const selInicio = new Date(fInicio);
        const selFin = new Date(fFin);

        for (let alq of alquileresExistentes) {
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

    inputInicio.addEventListener("change", () => {
        inputFin.min = inputInicio.value;
        if (inputFin.value && inputFin.value < inputInicio.value) {
            inputFin.value = inputInicio.value;
        }
        calcularTotal();
    });

    inputFin.addEventListener("change", () => {
        if (inputInicio.value && inputFin.value) {
            if (seSolapaConAlquiler(inputInicio.value, inputFin.value)) {
                alert("⚠️ Atención: Las fechas seleccionadas incluyen días en los que este vehículo ya se encuentra reservado. Por favor elija otro rango.");
                inputFin.value = inputInicio.value;
            }
        }
        calcularTotal();
    });

    checkboxesExtras.forEach(chk => chk.addEventListener("change", calcularTotal));

    document.querySelectorAll(".input-calendario").forEach(input => {
        input.addEventListener("click", () => {
            if (typeof input.showPicker === "function") {
                input.showPicker();
            }
        });
    });

    function calcularTotal() {
        if (inputInicio.value && inputFin.value) {
            const fecha1 = new Date(inputInicio.value);
            const fecha2 = new Date(inputFin.value);
            const diferenciaMilisegundos = fecha2 - fecha1;
            const dias = Math.max(1, Math.ceil(diferenciaMilisegundos / (1000 * 60 * 60 * 24)));

            if (dias > 0) {
                let costoTotal = dias * autoSeleccionado.precio;

                if (!esMoto) {
                    checkboxesExtras.forEach(chk => {
                        if (chk.checked) {
                            costoTotal += dias * parseInt(chk.value);
                        }
                    });
                }

                spanTotal.textContent = `$${costoTotal.toLocaleString()}`;
                return costoTotal;
            } else {
                spanTotal.textContent = "$0 (Fechas inválidas)";
            }
        }
        return 0;
    }

    calcularTotal();

    const form = document.getElementById("formTransaccion");
    if (form) {
        form.addEventListener("submit", async (e) => {
            e.preventDefault();

            if (seSolapaConAlquiler(inputInicio.value, inputFin.value)) {
                alert("❌ No se puede procesar la reserva: el vehículo ya está ocupado en esas fechas.");
                return;
            }

            const totalCalculado = calcularTotal();
            if (totalCalculado <= 0) {
                alert("Por favor seleccione un rango de fechas válido.");
                return;
            }

            const extrasSeleccionados = [];
            if (!esMoto) {
                checkboxesExtras.forEach(chk => {
                    if (chk.checked) {
                        extrasSeleccionados.push(chk.parentElement.textContent.trim());
                    }
                });
            }

            const datosTransaccion = {
                vehiculo: autoSeleccionado,
                fechaInicio: inputInicio.value,
                fechaFin: inputFin.value,
                extras: extrasSeleccionados,
                totalFinal: totalCalculado
            };

            localStorage.setItem("datosTransaccion", JSON.stringify(datosTransaccion));
            window.location.href = "pago.html";
        });
    }
});