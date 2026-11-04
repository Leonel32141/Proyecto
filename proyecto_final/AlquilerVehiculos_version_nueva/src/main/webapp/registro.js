const formRegistro = document.getElementById('formRegistro');
const alertaError = document.getElementById('alertaError');
const alertaExito = document.getElementById('alertaExito');
const inputFechaNac = document.getElementById('fecha_nacimiento');

// Configurar límites para la fecha de nacimiento en tiempo real
const hoy = new Date();
const anioMaximo = hoy.getFullYear() - 21;
const mesActual = String(hoy.getMonth() + 1).padStart(2, '0');
const diaActual = String(hoy.getDate()).padStart(2, '0');

const fechaMaxPermitida = `${anioMaximo}-${mesActual}-${diaActual}`;
const fechaMinPermitida = '1900-01-01';

inputFechaNac.setAttribute('max', fechaMaxPermitida);
inputFechaNac.setAttribute('min', fechaMinPermitida);

// Abrir el selector de fecha al hacer clic en CUALQUIER PARTE del input
inputFechaNac.addEventListener('click', function() {
    try {
        this.showPicker();
    } catch (error) {
        // Fallback por si el navegador no soporta showPicker de manera directa
        this.focus();
    }
});

// Bloquear tipeo manual de teclado en la fecha para forzar el uso del calendario
inputFechaNac.addEventListener('keydown', function(e) {
    e.preventDefault();
});

// Restricciones de tipeo numérico estricto para DNI, CUIL y Teléfono
const inputsNumericosStrict = ['dni', 'cuil', 'telefono'];

inputsNumericosStrict.forEach(id => {
    const input = document.getElementById(id);
    if (input) {
        input.addEventListener('keydown', function(e) {
            const teclasPermitidas = ['Backspace', 'Tab', 'ArrowLeft', 'ArrowRight', 'Delete', 'Enter'];
            if (teclasPermitidas.includes(e.key) || (e.ctrlKey === true && ['c', 'v', 'x', 'a'].includes(e.key.toLowerCase()))) {
                return;
            }
            if (!/^[0-9]$/.test(e.key)) {
                e.preventDefault();
            }
        });

        input.addEventListener('input', function(e) {
            let valorLimpio = this.value.replace(/\D/g, '');
            if (id === 'dni') {
                this.value = valorLimpio.slice(0, 8);
            } else if (id === 'cuil') {
                this.value = valorLimpio.slice(0, 11); // Exactamente 11 números sin guiones
            } else if (id === 'telefono') {
                this.value = valorLimpio.slice(0, 10);
            }
        });
    }
});

// Manejador del envío del formulario
formRegistro.addEventListener('submit', async function(e) {
    e.preventDefault();

    alertaError.style.display = 'none';
    alertaExito.style.display = 'none';

    let nombre = document.getElementById('nombre').value.trim();
    let apellido = document.getElementById('apellido').value.trim();
    let fechaNac = document.getElementById('fecha_nacimiento').value;
    let dniInput = document.getElementById('dni').value.trim();
    let cuilInput = document.getElementById('cuil').value.trim();
    let telefonoInput = document.getElementById('telefono').value.trim();
    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;

    if (!fechaNac) {
        alertaError.textContent = "Error: Debes seleccionar tu fecha de nacimiento.";
        alertaError.style.display = 'block';
        return;
    }

    // Validar DNI (7 u 8 dígitos)
    let dniLimpiado = dniInput.replace(/\D/g, '');
    if (dniLimpiado.length < 7 || dniLimpiado.length > 8) {
        alertaError.textContent = "Error: El DNI debe tener 7 u 8 dígitos.";
        alertaError.style.display = 'block';
        return;
    }

    // Validar CUIL (exactamente 11 números, sin guiones)
    let cuilLimpiado = cuilInput.replace(/\D/g, '');
    if (cuilLimpiado.length !== 11) {
        alertaError.textContent = "Error: El CUIL debe tener exactamente 11 números.";
        alertaError.style.display = 'block';
        return;
    }

    // Validar teléfono (10 dígitos)
    let telefonoLimpiado = telefonoInput.replace(/\D/g, '');
    if (telefonoLimpiado.length !== 10) {
        alertaError.textContent = "Error: El número de teléfono debe tener exactamente 10 dígitos.";
        alertaError.style.display = 'block';
        return;
    }

    // Calcular edad exacta
    let fechaNacimientoObj = new Date(fechaNac);
    let edad = hoy.getFullYear() - fechaNacimientoObj.getFullYear();
    let m = hoy.getMonth() - fechaNacimientoObj.getMonth();
    if (m < 0 || (m === 0 && hoy.getDate() < fechaNacimientoObj.getDate())) {
        edad--;
    }

    if (edad < 21) {
        alertaError.textContent = "Error: Debes tener al menos 21 años para registrarte en Los Pipiautos.";
        alertaError.style.display = 'block';
        return;
    }

    const objetoUsuario = {
        nombre: nombre,
        apellido: apellido,
        email: email,
        password: password,
        dni: dniLimpiado,
        cuil: cuilLimpiado,
        telefono: telefonoLimpiado,
        fechaNacimiento: fechaNac
    };

    try {
        const respuesta = await fetch('api/usuarios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(objetoUsuario)
        });

        const contentType = respuesta.headers.get("content-type");
        let resultado;
        if (contentType && contentType.indexOf("application/json") !== -1) {
            resultado = await respuesta.json();
        } else {
            let textoRespuesta = await respuesta.text();
            resultado = { mensaje: textoRespuesta, error: textoRespuesta };
        }

        if (respuesta.ok || resultado.status === "success" || respuesta.status === 201) {
            alertaError.style.display = 'none';
            alertaExito.textContent = resultado.mensaje || "¡Registro exitoso! Redirigiendo al login...";
            alertaExito.style.display = 'block';

            formRegistro.reset();

            setTimeout(() => {
                window.location.href = 'login.html';
            }, 1500);
        } else {
            let mensajeServidor = resultado.error || resultado.mensaje || "No se pudo completar el registro en el servidor.";
            alertaError.textContent = "Error del servidor: " + mensajeServidor;
            alertaError.style.display = 'block';
            alertaExito.style.display = 'none';
        }

    } catch (error) {
        console.error("Error en la conexión con la API:", error);
        alertaError.textContent = "Error de conexión: No se pudo conectar con el servidor backend.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
    }
});