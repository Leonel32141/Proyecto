const formRegistro = document.getElementById('formRegistro');
const alertaError = document.getElementById('alertaError');
const alertaExito = document.getElementById('alertaExito');

// 1. Restringir DNI (máx 8), CUIL (máx 11) y Teléfono (máx 10) en tiempo real
const inputsNumericos = ['dni', 'cuil', 'telefono'];

inputsNumericos.forEach(id => {
    const input = document.getElementById(id);
    
    if (input) {
        // Bloquea letras y símbolos mientras los tipea en el teclado
        input.addEventListener('keydown', function(e) {
            const teclasPermitidas = ['Backspace', 'Tab', 'ArrowLeft', 'ArrowRight', 'Delete', 'Enter'];
            if (teclasPermitidas.includes(e.key) || (e.ctrlKey === true && ['c', 'v', 'x', 'a'].includes(e.key.toLowerCase()))) {
                return;
            }
            if (!/^[0-9]$/.test(e.key)) {
                e.preventDefault();
            }
        });

        // Limpia texto pegado y limita la longitud máxima según el campo
        input.addEventListener('input', function(e) {
            let valorLimpio = this.value.replace(/\D/g, '');
            if (id === 'dni') {
                this.value = valorLimpio.slice(0, 8);
            } else if (id === 'cuil') {
                this.value = valorLimpio.slice(0, 11);
            } else if (id === 'telefono') {
                this.value = valorLimpio.slice(0, 10);
            }
        });
    }
});

// 2. Manejador del envío del formulario
formRegistro.addEventListener('submit', async function(e) {
    e.preventDefault();

    let nombre = document.getElementById('nombre').value.trim();
    let apellido = document.getElementById('apellido').value.trim();
    let fechaNac = document.getElementById('fecha_nacimiento').value;
    let dniInput = document.getElementById('dni').value.trim();
    let cuilInput = document.getElementById('cuil').value.trim();
    let telefonoInput = document.getElementById('telefono').value.trim();
    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;

    // Validar DNI (7 u 8 dígitos)
    let dniLimpiado = dniInput.replace(/\D/g, '');
    if (dniLimpiado.length < 7 || dniLimpiado.length > 8) {
        alertaError.textContent = "Error: El DNI debe tener 7 u 8 dígitos.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Validar CUIL (exactamente 11 números)
    let cuilLimpiado = cuilInput.replace(/-/g, '');
    if (cuilLimpiado.length !== 11) {
        alertaError.textContent = "Error: El CUIL debe tener exactamente 11 números.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Validar teléfono (exactamente 10 dígitos)
    let telefonoLimpiado = telefonoInput.replace(/\D/g, '');
    if (telefonoLimpiado.length !== 10) {
        alertaError.textContent = "Error: El número de teléfono debe tener exactamente 10 dígitos.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Calcular edad exacta en base a la fecha de nacimiento
    let hoy = new Date();
    let nacimiento = new Date(fechaNac);
    let edad = hoy.getFullYear() - nacimiento.getFullYear();
    let m = hoy.getMonth() - nacimiento.getMonth();
    if (m < 0 || (m === 0 && hoy.getDate() < nacimiento.getDate())) {
        edad--;
    }

    // Validación de edad mínima (21 años)
    if (edad < 21) {
        alertaError.textContent = "Error: Debes tener al menos 21 años para registrarte en Los Pipiautos.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Preparamos los datos para enviarlos al Servlet de Java mediante fetch
    const datosFormulario = new URLSearchParams({
        nombre: nombre,
        apellido: apellido,
        email: email,
        password: password,
        dni: dniLimpiado,
        cuil: cuilLimpiado,
        telefono: telefonoLimpiado
    });

	try {
	// Petición POST al backend apuntando al context path limpio de Tomcat
		const respuesta = await fetch('registro', {
	    method: 'POST',
	    headers: {
	        'Content-Type': 'application/x-www-form-urlencoded',
	    },
	    body: datosFormulario
	});

	const resultado = await respuesta.json();
	// ... el resto de tu código que ya funcionaba ...

        if (resultado.status === "success") {
            alertaError.style.display = 'none';
            alertaExito.textContent = resultado.mensaje || "¡Registro exitoso! Redirigiendo al login...";
            alertaExito.style.display = 'block';

            formRegistro.reset();

            setTimeout(() => {
                window.location.href = 'login.html';
            }, 1500);
        } else {
            alertaError.textContent = "Error: " + resultado.mensaje;
            alertaError.style.display = 'block';
            alertaExito.style.display = 'none';
        }

    } catch (error) {
        console.error("Error en la conexión con el servidor:", error);
        alertaError.textContent = "Ocurrió un error al intentar conectar con el servidor.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
    }
});