document.addEventListener("DOMContentLoaded", function() {
    // 1. Recuperar el vehículo de lujo seleccionado desde el localStorage
    const vehiculoGuardado = localStorage.getItem("vehiculoAlquiler");
    
    if (!vehiculoGuardado) {
        alert("No se ha seleccionado ningún vehículo de lujo.");
        window.location.href = "index.html";
        return;
    }

    const auto = JSON.parse(vehiculoGuardado);

    // 2. Pintar los datos y las carpetas de imágenes correctas
    document.getElementById("nombreAuto").textContent = auto.modelo;
    document.getElementById("tipoAuto").textContent = auto.tipo || "Alta Gama VIP";
    document.getElementById("precioAuto").textContent = auto.precioStr || `$${Number(auto.precio).toLocaleString()} / día`;
    
    const imgAuto = document.getElementById("imgAuto");
    const imgInterior = document.getElementById("imgInterior");

    if (auto.imagen) {
        imgAuto.src = auto.imagen; 
        const partesRuta = auto.imagen.split('/');
        const nombreArchivo = partesRuta[partesRuta.length - 1];
        imgInterior.src = `fotos_interiores/${nombreArchivo}`;
        
        imgAuto.onerror = function() { this.src = 'foto_vehiculos/PORSCHE_911.jpg'; };
        imgInterior.onerror = function() { this.src = `fotos_interiores/${nombreArchivo}`; };
    }

    // 3. Configurar fecha mínima de hoy y asegurar despliegue del calendario al hacer clic en cualquier parte
    const hoy = new Date().toISOString().split('T')[0];
    const inputFechaInicio = document.getElementById("fechaInicio");
    const inputFechaFin = document.getElementById("fechaFin");

    inputFechaInicio.min = hoy;
    inputFechaFin.min = hoy;

    // Al hacer clic en cualquier parte del input, se despliega el selector de fecha nativo de forma limpia
    [inputFechaInicio, inputFechaFin].forEach(input => {
        input.addEventListener("click", function() {
            if (typeof this.showPicker === 'function') {
                this.showPicker();
            }
        });
    });

    inputFechaInicio.addEventListener("change", function() {
        inputFechaFin.min = inputFechaInicio.value;
        if (inputFechaFin.value && inputFechaFin.value < inputFechaInicio.value) {
            inputFechaFin.value = inputFechaInicio.value;
        }
        calcularTotal();
    });

    const checkboxesExtras = document.querySelectorAll(".extra-opcion");
    const totalFinalSpan = document.getElementById("totalFinal");
    const formTransaccion = document.getElementById("formTransaccion");

    let precioBaseNumerico = Number(auto.precio);
    if (isNaN(precioBaseNumerico) || precioBaseNumerico <= 0) {
        precioBaseNumerico = Number(String(auto.precioStr || "").replace(/[^0-9]/g, '')) || 250000;
    }

    function calcularTotal() {
        const valInicio = inputFechaInicio.value;
        const valFin = inputFechaFin.value;

        let dias = 1; // 1 día base para sumar extras al instante

        if (valInicio && valFin) {
            const fechaInicio = new Date(valInicio);
            const fechaFin = new Date(valFin);

            if (fechaFin >= fechaInicio) {
                const diferenciaTiempo = fechaFin - fechaInicio;
                dias = Math.max(1, Math.ceil(diferenciaTiempo / (1000 * 60 * 60 * 24)));
            }
        }

        let subtotalBase = precioBaseNumerico * dias;
        let totalExtras = 0;

        checkboxesExtras.forEach(chk => {
            if (chk.checked) {
                const valorExtra = Number(chk.value);
                if (valorExtra === 8000) {
                    totalExtras += valorExtra; // Pago único fijo de entrega
                } else {
                    totalExtras += valorExtra * dias; // Multiplicado por días
                }
            }
        });

        const granTotal = subtotalBase + totalExtras;
        totalFinalSpan.textContent = `$${granTotal.toLocaleString('es-AR')}`;
        return granTotal;
    }

    inputFechaFin.addEventListener("change", calcularTotal);
    checkboxesExtras.forEach(chk => chk.addEventListener("change", calcularTotal));
    
    // Inicializar cálculo al cargar
    calcularTotal();

    // 4. Manejo del formulario para pasar a la vista de pago de lujo
    formTransaccion.addEventListener("submit", function(e) {
        e.preventDefault();

        if (!inputFechaInicio.value || !inputFechaFin.value) {
            alert("Por favor selecciona las fechas de retiro y devolución VIP.");
            return;
        }

        // Guardamos los datos de la reserva de lujo para consumirlos en pago_lujo.html
        const datosReservaLujo = {
            vehiculo: auto,
            fechaInicio: inputFechaInicio.value,
            fechaFin: inputFechaFin.value,
            montoTotal: calcularTotal()
        };

        localStorage.setItem("datosTransaccionLujo", JSON.stringify(datosReservaLujo));
        
        // Redirigir a la vista de pago independiente
        window.location.href = "pago_lujo.html";
    });
});