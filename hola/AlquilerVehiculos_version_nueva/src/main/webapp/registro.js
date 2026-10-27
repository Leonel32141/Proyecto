const formRegistro = document.getElementById('formRegistro');
const alertaError = document.getElementById('alertaError');
const alertaExito = document.getElementById('alertaExito');
const inputFechaNac = document.getElementById('fecha_nacimiento');

// Configurar límites didácticos para la fecha de nacimiento en tiempo real
// Para tener 21 años, la fecha máxima de nacimiento debe ser hace exactamente 21 años atrás.
const hoy = new Date();
const anioMaximo = hoy.getFullYear() - 21;
const mesActual = String(hoy.getMonth() + 1).padStart(2, '0');
const diaActual = String(hoy.getDate()).padStart(2, '0');

// Fecha máxima permitida (ej: hoy en 2005 si estamos en 2026)
const fechaMaxPermitida = `${anioMaximo}-${mesActual}-${diaActual}`;
// Fecha mínima lógica (ej: año 1900 para evitar que pongan 3000 o años raros de más de 4 dígitos)
const fechaMinPermitida = '1900-01-01';

inputFechaNac.setAttribute('max', fechaMaxPermitida);
inputFechaNac.setAttribute('min', fechaMinPermitida);

// 1. Restringir DNI (máx 8), CUIL (máx 11) y Teléfono (máx 10) en tiempo real
const inputsNumericos = ['dni', 'cuil', 'telefono'];

inputsNumericos.forEach(id => {
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

    // Validación extra del año ingresado manualmente por si acaso (controlando que sean 4 dígitos del año)
    if (fechaNac) {
        let anioIngresado = new Date(fechaNac).getFullYear();
        if (anioIngresado < 1900 || anioIngresado > anioMaximo) {
            alertaError.textContent = `Error: El año de nacimiento no es válido. Debes tener al menos 21 años.`;
            alertaError.style.display = 'block';
            alertaExito.style.display = 'none';
            return;
        }
    }

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

    // Calcular edad exacta por seguridad en frontend
    let fechaNacimientoObj = new Date(fechaNac);
    let edad = hoy.getFullYear() - fechaNacimientoObj.getFullYear();
    let m = hoy.getMonth() - fechaNacimientoObj.getMonth();
    if (m < 0 || (m === 0 && hoy.getDate() < fechaNacimientoObj.getDate())) {
        edad--;
    }

    if (edad < 21) {
        alertaError.textContent = "Error: Debes tener al menos 21 años para registrarte en Los Pipiautos.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Preparamos el objeto JSON para enviarlo a la API REST de Usuarios (`api/usuarios`)
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
        // Petición POST apuntando a nuestra API REST configurada en el Servlet UsuarioServlet (`/api/usuarios`)
        const respuesta = await fetch('api/usuarios', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json',
            },
            body: JSON.stringify(objetoUsuario)
        });

        // Verificamos si la respuesta viene en JSON
        const contentType = respuesta.headers.get("content-type");
        let resultado;
        if (contentType && contentType.indexOf("application/json") !== -1) {
            resultado = await respuesta.json();
        } else {
            resultado = { mensaje: await respuesta.text() };
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
            alertaError.textContent = "Error: " + (resultado.error || resultado.mensaje || "No se pudo completar el registro.");
            alertaError.style.display = 'block';
            alertaExito.style.display = 'none';
        }

    } catch (error) {
        console.error("Error en la conexión con la API:", error);
        alertaError.textContent = "Ocurrió un error al intentar conectar con la API del servidor.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
    }
});