const formRegistro = document.getElementById('formRegistro');
const alertaError = document.getElementById('alertaError');
const alertaExito = document.getElementById('alertaExito');

formRegistro.addEventListener('submit', function(e) {
    e.preventDefault();

    let nombre = document.getElementById('nombre').value.trim();
    let apellido = document.getElementById('apellido').value.trim();
    let fechaNac = document.getElementById('fecha_nacimiento').value;
    let email = document.getElementById('email').value.trim();
    let password = document.getElementById('password').value;

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

    // Determinar si habilita vehículos de lujo (25 años o más)
    let accesoLujo = edad >= 25;

    // Buscar usuarios previos en localStorage
    let usuarios = JSON.parse(localStorage.getItem('usuarios_pipiautos')) || [];
    let existe = usuarios.some(u => u.email === email);

    if (existe) {
        alertaError.textContent = "El correo electrónico ya está registrado.";
        alertaError.style.display = 'block';
        alertaExito.style.display = 'none';
        return;
    }

    // Registrar nuevo usuario guardando su acceso a lujo
    let nuevoUsuario = { 
        nombre, 
        apellido, 
        fechaNac, 
        edad, 
        email, 
        password, 
        accesoLujo 
    };

    usuarios.push(nuevoUsuario);
    localStorage.setItem('usuarios_pipiautos', JSON.stringify(usuarios));

    alertaError.style.display = 'none';
    alertaExito.textContent = "¡Registro exitoso! Redirigiendo al login...";
    alertaExito.style.display = 'block';

    formRegistro.reset();

    setTimeout(() => {
        window.location.href = 'login.html';
    }, 1500);
});