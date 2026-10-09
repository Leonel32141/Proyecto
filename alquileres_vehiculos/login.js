const formLogin = document.getElementById('formLogin');
const alertaError = document.getElementById('alertaError');
const alertaExito = document.getElementById('alertaExito');

formLogin.addEventListener('submit', function(e) {
    e.preventDefault();

    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;

    // Recuperar usuarios guardados en localStorage
    let usuarios = JSON.parse(localStorage.getItem('usuarios_pipiautos')) || [];

    // Buscar si coinciden el email y la contraseña
    let usuarioEncontrado = usuarios.find(u => u.email === email && u.password === password);

    if (!usuarioEncontrado) {
        alertaError.textContent = "Correo o contraseña incorrectos.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Guardar la sesión actual del usuario activo
    localStorage.setItem('usuario_activo', JSON.stringify(usuarioEncontrado));

    alertaError.style.display = 'none';
    alertaExito.textContent = `¡Bienvenido, ${usuarioEncontrado.nombre}! Redirigiendo al catálogo...`;
    alertaExito.style.display = 'block';

    formLogin.reset();

    // Redirigir a la siguiente vista (por ejemplo, el catálogo que hagamos después)
    setTimeout(() => {
        window.location.href = 'index.html'; // O la vista principal que definamos
    }, 1500);
});