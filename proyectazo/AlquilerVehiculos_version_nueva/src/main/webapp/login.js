const formLogin = document.getElementById('formLogin');
const alertaError = document.getElementById('alertaError');
const alertaExito = document.getElementById('alertaExito');

formLogin.addEventListener('submit', async function(e) {
    e.preventDefault();

    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;

    // Preparamos los datos para enviarlos al LoginServlet de Java
    const formData = new URLSearchParams();
    formData.append('email', email);
    formData.append('password', password);

    try {
        const respuesta = await fetch('login', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded'
            },
            body: formData.toString()
        });

        const resultado = await respuesta.json();

        if (resultado.status === 'success') {
            alertaError.style.display = 'none';
            alertaExito.textContent = `¡Bienvenido, ${resultado.nombre}! Redirigiendo al catálogo...`;
            alertaExito.style.display = 'block';

            formLogin.reset();

            // Redirigir al index después de 1.5 segundos
            setTimeout(() => {
                window.location.href = 'index.html';
            }, 1500);
        } else {
            alertaError.textContent = resultado.mensaje || "Correo o contraseña incorrectos.";
            alertaError.style.display = 'block';
            alertaExito.style.display = 'none';
        }

    } catch (error) {
        console.error("Error en la conexión con el servidor:", error);
        alertaError.textContent = "Error de conexión con el servidor.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
    }
});