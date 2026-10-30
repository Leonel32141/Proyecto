document.addEventListener("DOMContentLoaded", () => {
    const datosTransaccionJson = localStorage.getItem("datosTransaccionLujo");

    if (!datosTransaccionJson) {
        alert("No hay datos de alquiler de lujo pendientes.");
        window.location.href = "index.html";
        return;
    }

    const transaccion = JSON.parse(datosTransaccionJson);
    const auto = transaccion.vehiculo;

    // Mostrar resumen con el modelo del vehículo de lujo
    document.getElementById("resumenModelo").textContent = auto.modelo;
    document.getElementById("resumenFechas").textContent = `Retiro VIP: ${transaccion.fechaInicio} al ${transaccion.fechaFin}`;
    document.getElementById("resumenTotal").textContent = `Total a Pagar VIP: $${transaccion.montoTotal.toLocaleString('es-AR')}`;

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

    // Manejar el pago de lujo
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

        const idClienteLogueado = localStorage.getItem('usuarioId') || localStorage.getItem('id_usuario') || "60";

        // Determinamos la sucursal según el domicilio registrado
        const sucursalAsignada = domicilioUsuario.toLowerCase().includes("caballito") 
            ? "Sucursal Caballito (Av. Rivadavia 4500, CABA)" 
            : "Sucursal Central Palermo (Av. Juan B. Justo 1200, CABA)";

        const idSucursalNum = domicilioUsuario.toLowerCase().includes("caballito") ? 2 : 1;

        const datosAlquilerJava = {
            idCliente: parseInt(idClienteLogueado),
            idVehiculo: auto.id || 50,
            idMetodoPago: parseInt(metodoPagoId), 
            fechaInicioPautada: transaccion.fechaInicio + "T00:00:00",
            fechaFinPautada: transaccion.fechaFin + "T00:00:00",
            montoTotal: transaccion.montoTotal,
            montoSena: transaccion.montoTotal,
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

            if (respuesta.ok) {
                generarComprobanteContratoLujo(transaccion, nombreTitular, dniGuardado, metodoPagoId, sucursalAsignada);
            } else {
                const resultado = await respuesta.json();
                alert("Error al registrar el alquiler de lujo: " + (resultado.error || "Desconocido"));
            }
        } catch (error) {
            console.error("Error de red:", error);
            alert("Hubo un error al conectar con el servidor.");
        }
    });
});

function generarComprobanteContratoLujo(transaccion, titular, dni, metodoPagoId, sucursal) {
    const contenedor = document.querySelector(".transaccion-container");
    const nroComprobante = Math.floor(100000 + Math.random() * 900000);
    const fechaActual = new Date().toLocaleDateString();
    const auto = transaccion.vehiculo;
    
    const nombreMetodo = metodoPagoId === "2" ? "Tarjeta de Crédito / Black" : "Transferencia Bancaria";

    contenedor.innerHTML = `
        <div style="text-align: center;">
            <h2 style="color: #34d399; margin-bottom: 5px;">¡Pago VIP Aprobado con Éxito!</h2>
            <p style="color: #9ca3af; font-size: 0.9rem;">Comprobante de Contrato de Alta Gama N° #${nroComprobante}</p>
        </div>

        <div style="background-color: #1f2937; padding: 15px; border-radius: 8px; margin: 15px 0; border: 1px solid #374151; font-size: 0.9rem;">
            <p><strong>Fecha de Emisión:</strong> ${fechaActual}</p>
            <p><strong>Titular VIP:</strong> ${titular} (DNI Verificado: ${dni})</p>
            <p><strong>Método de Pago:</strong> ${nombreMetodo}</p>
            <p><strong>Unidad de Lujo:</strong> ${auto.modelo} (${auto.tipo})</p>
            <p><strong>Período VIP:</strong> Del ${transaccion.fechaInicio} al ${transaccion.fechaFin}</p>
            <p><strong>📍 Sucursal de Retiro Preferencial:</strong> <span style="color: #d4af37;">${sucursal}</span></p>
            <p><strong>🕒 Atención Exclusiva:</strong> 09:00 Hs - Presentarse con DNI y Licencia</p>
            <hr style="border-color: #374151; margin: 10px 0;">
            <p style="font-size: 1.1rem; color: #34d399;"><strong>Total Pagado VIP:</strong> $${transaccion.montoTotal.toLocaleString('es-AR')}</p>
        </div>

        <p style="font-size: 0.75rem; color: #9ca3af; text-align: center;">
            Este comprobante digital cuenta con validez legal como constancia de reserva exclusiva y contrato temporal de locación de vehículo de alta gama.
        </p>

        <button onclick="window.print()" class="btn-confirmar btn-lujo-action" style="background-color: #34d399; color: #000; margin-bottom: 10px;">Imprimir / Guardar Contrato PDF</button>
        <button onclick="finalizarFlujoLujo()" class="btn-confirmar" style="background-color: #1f2937; border: 1px solid #374151; color: #fff;">Volver al Inicio</button>
    `;
}

function finalizarFlujoLujo() {
    localStorage.removeItem("vehiculoAlquiler");
    localStorage.removeItem("datosTransaccionLujo");
    window.location.href = "index.html";
}