document.addEventListener("DOMContentLoaded", () => {
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

        // Recuperamos el ID del usuario logueado para que coincida exactamente con id_usuario en la BD
        const idUsuarioLogueado = localStorage.getItem('usuarioId') || localStorage.getItem('id_usuario') || "1";

        // Estructura de datos adaptada para la tabla clientes y su relación con usuarios
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
                
                // ⭐ VALIDACIÓN INTELIGENTE ⭐
                // Si el usuario ya tenía un vehículo seleccionado previamente, lo mandamos a transaccion.html para que no lo pierda.
                // Si no hay vehículo, lo mandamos al catálogo (index.html).
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