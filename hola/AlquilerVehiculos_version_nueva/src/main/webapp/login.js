const formLogin = document.getElementById('formLogin');
const alertaError = document.getElementById('alertaError');
const alertaExito = document.getElementById('alertaExito');

formLogin.addEventListener('submit', async function(e) {
    e.preventDefault();

    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;

    // Validación básica en frontend
    if (!email || !password) {
        alertaError.textContent = "Por favor, completa todos los campos.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Preparamos el objeto JSON para enviarlo a la API REST
    const credenciales = {
        email: email,
        password: password
    };

    try {
        // Petición POST apuntando al endpoint de autenticación/usuarios
        const respuesta = await fetch('api/auth/login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/json'
            },
            body: JSON.stringify(credenciales)
        });

        // Verificamos si la respuesta es JSON válido
        const contentType = respuesta.headers.get("content-type");
        let resultado;
        if (contentType && contentType.indexOf("application/json") !== -1) {
            resultado = await respuesta.json();
        } else {
            resultado = { mensaje: await respuesta.text() };
        }

        if (respuesta.ok || resultado.status === 'success') {
            alertaError.style.display = 'none';
            alertaExito.textContent = `¡Bienvenido, ${resultado.nombre || 'Usuario'}! Redirigiendo al catálogo...`;
            alertaExito.style.display = 'block';

            formLogin.reset();

            // ⭐ GUARDAR DATOS CLAVE EN LOCALSTORAGE PARA EL INDEX Y LA FLOTA DE LUJO ⭐
            if (resultado.nombre) localStorage.setItem('usuarioNombre', resultado.nombre);
            if (resultado.apellido) localStorage.setItem('usuarioApellido', resultado.apellido);
            if (resultado.fechaNacimiento) localStorage.setItem('usuarioFechaNacimiento', resultado.fechaNacimiento);
            if (resultado.token) localStorage.setItem('token', resultado.token);

            // Redirigir al index después de 1.5 segundos
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 1500);
        } else {
            alertaError.textContent = resultado.error || resultado.mensaje || "Correo o contraseña incorrectos.";
            alertaError.style.display = 'block';
            alertaExito.style.display = 'none';
        }

    } catch (error) {
        console.error("Error en la conexión con la API:", error);
        alertaError.textContent = "Error de conexión con la API del servidor.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
    }
});