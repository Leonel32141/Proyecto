document.addEventListener("DOMContentLoaded", async () => {
    const idUsuarioLogueado = localStorage.getItem('usuarioId') || localStorage.getItem('id_usuario');

    if (!idUsuarioLogueado) {
        alert("Sesión no encontrada. Por favor inicie sesión nuevamente.");
        window.location.href = "login.html";
        return;
    }

    // Ocultamos el contenedor del formulario visualmente mientras valida para que no parpadee si ya está completo
    const mainContainer = document.querySelector(".form-container") || document.body;
    if (mainContainer) mainContainer.style.visibility = "hidden";

    try {
        // 1. Verificar si ya tiene un alquiler activo
        const respAlquiler = await fetch(`api/alquileres?idClienteActivo=${idUsuarioLogueado}`);
        if (respAlquiler.ok) {
            const dataAlq = await respAlquiler.json();
            if (dataAlq.tieneActivo === true) {
                alert("⚠️ Ya tenés un vehículo alquilado y vigente. No puedes realizar otra reserva ni modificar el perfil hasta finalizarla.");
                window.location.href = "index.html";
                return;
            }
        }

        // 2. Verificar si el perfil ya fue completado en la base de datos
        const respCliente = await fetch(`api/clientes?id=${idUsuarioLogueado}`);
        if (respCliente.ok) {
            const clienteExistente = await respCliente.json();
            if (clienteExistente && clienteExistente.domicilio) {
                localStorage.setItem("usuarioDomicilio", clienteExistente.domicilio);
                localStorage.setItem("clienteCompletado", "true");
                
                const autoGuardado = localStorage.getItem("vehiculoAlquiler");
                if (autoGuardado) {
                    window.location.href = "transaccion.html";
                } else {
                    window.location.href = "index.html";
                }
                return;
            }
        }
    } catch (err) {
        console.warn("Aviso de verificación previa:", err);
    }

    // Si pasa todas las validaciones y realmente es su primera vez, mostramos el formulario
    if (mainContainer) mainContainer.style.visibility = "visible";

    const dniRegistrado = localStorage.getItem('usuarioDni') || localStorage.getItem('usuarioDocumento') || "45123456";
    const fechaNacimientoRegistrada = localStorage.getItem('usuarioFechaNacimiento') || "2000-01-01";
    
    document.getElementById("dni").value = dniRegistrado;
    document.getElementById("licencia").value = dniRegistrado;
    document.getElementById("fechaNacimiento").value = fechaNacimientoRegistrada;

    let fechaConfirmada = false;
    const btnConfirmarFecha = document.getElementById("btnConfirmarFecha");
    const estadoFecha = document.getElementById("estadoFecha");

    btnConfirmarFecha.addEventListener("click", () => {
        fechaConfirmada = true;
        btnConfirmarFecha.textContent = "¡Confirmada! ✔";
        btnConfirmarFecha.style.backgroundColor = "#dcfce7";
        btnConfirmarFecha.style.color = "#166534";
        btnConfirmarFecha.style.borderColor = "#bbf7d0";
        estadoFecha.textContent = "Fecha de nacimiento verificada correctamente.";
        estadoFecha.style.color = "#166534";
    });

    const formCliente = document.getElementById("formCliente");
    formCliente.addEventListener("submit", async (e) => {
        e.preventDefault();

        const domicilio = document.getElementById("domicilio").value;

        if (!domicilio) {
            alert("Por favor ingrese su domicilio actual.");
            return;
        }

        if (!fechaConfirmada) {
            alert("Por favor haga clic en el botón 'Confirmar Fecha' antes de continuar.");
            return;
        }

        const datosClienteJava = {
            idCliente: parseInt(idUsuarioLogueado),
            dni: dniRegistrado,
            licenciaConducir: dniRegistrado,
            domicilio: domicilio,
            fechaNacimiento: fechaNacimientoRegistrada,
            esAptoLujo: false
        };

        try {
            const respuesta = await fetch("api/clientes", {
                method: "POST",
                headers: { "Content-Type": "application/json" },
                body: JSON.stringify(datosClienteJava)
            });

            if (respuesta.ok) {
                localStorage.setItem("usuarioDomicilio", domicilio);
                localStorage.setItem("clienteCompletado", "true");

                alert("¡Perfil registrado en el sistema con éxito!");
                
                const autoGuardado = localStorage.getItem("vehiculoAlquiler");
                if (autoGuardado) {
                    window.location.href = "transaccion.html";
                } else {
                    window.location.href = "index.html";
                }

            } else {
                const errorData = await respuesta.json();
                alert("Error al registrar el perfil: " + (errorData.error || "Desconocido"));
            }
        } catch (error) {
            console.error("Error de red:", error);
            alert("No se pudo conectar con el servidor.");
        }
    });
});