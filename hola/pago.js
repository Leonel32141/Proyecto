document.addEventListener("DOMContentLoaded", () => {
    const datosTransaccionJson = localStorage.getItem("datosTransaccion");

    if (!datosTransaccionJson) {
        alert("No hay datos de alquiler pendientes.");
        window.location.href = "index.html";
        return;
    }

    const transaccion = JSON.parse(datosTransaccionJson);

    // Mostrar resumen
    document.getElementById("resumenModelo").textContent = transaccion.vehiculo.modelo;
    document.getElementById("resumenFechas").textContent = `Retiro: ${transaccion.fechaInicio} al ${transaccion.fechaFin}`;
    document.getElementById("resumenTotal").textContent = `Total a Pagar: $${transaccion.totalFinal.toLocaleString()}`;

    // Recuperar datos del usuario logueado
    const nombreGuardado = localStorage.getItem('usuarioNombre');
    const apellidoGuardado = localStorage.getItem('usuarioApellido');
    const dniGuardado = localStorage.getItem('usuarioDni') || localStorage.getItem('usuarioDocumento') || "45123456";
    const domicilioUsuario = localStorage.getItem('usuarioDomicilio') || "CABA";

    if (nombreGuardado) {
        document.getElementById("nombreTitular").value = `${nombreGuardado} ${apellidoGuardado || ''}`;
    }
    
    const inputDni = document.getElementById("dniPasaporte");
    inputDni.value = dniGuardado;

    // --- FORMATEO AUTOMÁTICO DE VENCIMIENTO ---
    const inputVencimiento = document.getElementById("vencimiento");
    inputVencimiento.addEventListener("input", (e) => {
        let valor = e.target.value.replace(/\D/g, "");
        if (valor.length >= 3) {
            valor = valor.substring(0, 2) + "/" + valor.substring(2, 4);
        }
        e.target.value = valor;
    });

	// Formato automático para la tarjeta de crédito
	const inputTarjeta = document.getElementById("nroTarjeta");
	inputTarjeta.addEventListener("input", (e) => {
	    let valor = e.target.value.replace(/\D/g, "").substring(0, 16);
	    let valorConEspacios = valor.match(/.{1,4}/g)?.join(" ") || "";
	    e.target.value = valorConEspacios;
	});

    const inputCvv = document.getElementById("cvv");
    inputCvv.addEventListener("input", (e) => {
        e.target.value = e.target.value.replace(/\D/g, "").substring(0, 3);
    });

    // Manejar el pago
    const formPago = document.getElementById("formPago");
    formPago.addEventListener("submit", async (e) => {
        e.preventDefault();

        const nombreTitular = document.getElementById("nombreTitular").value;
        const metodoPagoId = document.getElementById("metodoPago").value; // "2" para Tarjeta, "3" para Transferencia
        const nroTarjeta = document.getElementById("nroTarjeta").value;
        const vencimiento = inputVencimiento.value;
        const cvv = inputCvv.value;

        if (nroTarjeta.length < 15 || vencimiento.length < 5 || cvv.length < 3) {
            alert("Por favor revise los datos de su tarjeta de crédito. Hay campos incompletos.");
            return;
        }

        const idClienteLogueado = localStorage.getItem('usuarioId') || localStorage.getItem('id_usuario') || "1";

        // Determinamos la sucursal según el domicilio registrado
        const sucursalAsignada = domicilioUsuario.toLowerCase().includes("caballito") 
            ? "Sucursal Caballito (Av. Rivadavia 4500, CABA)" 
            : "Sucursal Central Palermo (Av. Juan B. Justo 1200, CABA)";

        const idSucursalNum = domicilioUsuario.toLowerCase().includes("caballito") ? 2 : 1;

		const datosAlquilerJava = {
		    idCliente: parseInt(idClienteLogueado),
		    idVehiculo: transaccion.vehiculo.id,
            idMetodoPago: parseInt(metodoPagoId), // Mandamos el método de pago correcto (2 o 3)
		    fechaInicioPautada: transaccion.fechaInicio + "T00:00:00",
		    fechaFinPautada: transaccion.fechaFin + "T00:00:00",
		    montoTotal: transaccion.totalFinal,
		    montoSena: transaccion.totalFinal,
		    emailUsuario: localStorage.getItem("usuarioEmail") || "sebastian@demo.com",
		    idSucursalRetiro: idSucursalNum,
		    idSucursalDevolucion: idSucursalNum,
		    esOneway: false,
		    estadoReserva: "RESERVADA"
		};

        try {
            const respuesta = await fetch("api/alquileres", {
                method: "POST",
                headers: {
                    "Content-Type": "application/json"
                },
                body: JSON.stringify(datosAlquilerJava)
            });

            const resultado = await respuesta.json();

            if (respuesta.ok) {
                generarComprobanteContrato(transaccion, nombreTitular, dniGuardado, metodoPagoId, sucursalAsignada);
            } else {
                alert("Error al registrar el alquiler: " + (resultado.error || "Desconocido"));
            }
        } catch (error) {
            console.error("Error de red:", error);
            alert("Hubo un error al conectar con el servidor.");
        }
    });
});

function generarComprobanteContrato(transaccion, titular, dni, metodoPagoId, sucursal) {
    const contenedor = document.querySelector(".transaccion-container");
    const nroComprobante = Math.floor(100000 + Math.random() * 900000);
    const fechaActual = new Date().toLocaleDateString();
    
    // ⭐ Validamos correctamente según los IDs reales de tu BD (2: Tarjeta, 3: Transferencia) ⭐
    const nombreMetodo = metodoPagoId === "2" ? "Tarjeta de Crédito" : "Transferencia Bancaria";

    contenedor.innerHTML = `
        <div style="text-align: center;">
            <h2 style="color: #3fb950; margin-bottom: 5px;">¡Pago Aprobado con Éxito!</h2>
            <p style="color: #8b949e; font-size: 0.9rem;">Comprobante de Contrato de Alquiler N° #${nroComprobante}</p>
        </div>

        <div style="background-color: #21262d; padding: 15px; border-radius: 8px; margin: 15px 0; border: 1px solid #30363d; font-size: 0.9rem;">
            <p><strong>Fecha de Emisión:</strong> ${fechaActual}</p>
            <p><strong>Titular:</strong> ${titular} (DNI Verificado: ${dni})</p>
            <p><strong>Método de Pago:</strong> ${nombreMetodo}</p>
            <p><strong>Vehículo:</strong> ${transaccion.vehiculo.modelo} (${transaccion.vehiculo.tipo})</p>
            <p><strong>Período:</strong> Del ${transaccion.fechaInicio} al ${transaccion.fechaFin}</p>
            <p><strong>📍 Sucursal de Retiro:</strong> <span style="color: #58a6ff;">${sucursal}</span></p>
            <p><strong>🕒 Horario Sugerido:</strong> 09:00 Hs - Presentarse con DNI y Licencia</p>
            <p><strong>Extras:</strong> ${transaccion.extras.length > 0 ? transaccion.extras.join(', ') : 'Ninguno'}</p>
            <hr style="border-color: #30363d; margin: 10px 0;">
            <p style="font-size: 1.1rem; color: #3fb950;"><strong>Total Pagado:</strong> $${transaccion.totalFinal.toLocaleString()}</p>
        </div>

        <p style="font-size: 0.75rem; color: #8b949e; text-align: center;">
            Este comprobante digital tiene validez legal como constancia de reserva y contrato temporal de locación de vehículo automotor.
        </p>

        <button onclick="window.print()" class="btn-confirmar" style="background-color: #58a6ff; margin-bottom: 10px;">Imprimir / Guardar PDF</button>
        <button onclick="finalizarFlujo()" class="btn-confirmar" style="background-color: #21262d; border: 1px solid #30363d;">Volver al Inicio</button>
    `;
}

function finalizarFlujo() {
    localStorage.removeItem("vehiculoAlquiler");
    localStorage.removeItem("datosTransaccion");
    window.location.href = "index.html";
}