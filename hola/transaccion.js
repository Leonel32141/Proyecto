document.addEventListener("DOMContentLoaded", () => {
    const autoJson = localStorage.getItem("vehiculoAlquiler");

    if (autoJson) {
        const autoSeleccionado = JSON.parse(autoJson);

        // 1. Cargamos imágenes
        document.getElementById("imgAuto").src = autoSeleccionado.imagen || "foto_vehiculos/decoracion_1.jpg";
        
        const partesRuta = autoSeleccionado.imagen ? autoSeleccionado.imagen.split('/') : ["decoracion_1.jpg"];
        const nombreArchivo = partesRuta[partesRuta.length - 1];
        document.getElementById("imgInterior").src = `fotos_interiores/${nombreArchivo}`;

        document.getElementById("nombreAuto").textContent = autoSeleccionado.modelo;
        document.getElementById("tipoAuto").textContent = autoSeleccionado.tipo;
        document.getElementById("precioAuto").textContent = `$${autoSeleccionado.precio.toLocaleString()} / día`;

        // 2. Fechas dinámicas
        const inputInicio = document.getElementById("fechaInicio");
        const inputFin = document.getElementById("fechaFin");
        const spanTotal = document.getElementById("totalFinal");
        const checkboxesExtras = document.querySelectorAll(".extra-opcion");

        const hoy = new Date().toISOString().split("T")[0];
        inputInicio.min = hoy;
        inputInicio.value = hoy;
        inputFin.min = hoy;

        inputInicio.addEventListener("change", () => {
            inputFin.min = inputInicio.value;
            calcularTotal();
        });

        inputFin.addEventListener("change", calcularTotal);
        checkboxesExtras.forEach(chk => chk.addEventListener("change", calcularTotal));

        // Permitir abrir el calendario al hacer clic en todo el input
        document.querySelectorAll(".input-calendario").forEach(input => {
            input.addEventListener("click", () => {
                if (typeof input.showPicker === "function") {
                    input.showPicker();
                }
            });
        });

        function calcularTotal() {
            if (inputInicio.value && inputFin.value) {
                const fecha1 = new Date(inputInicio.value);
                const fecha2 = new Date(inputFin.value);
                const diferenciaMilisegundos = fecha2 - fecha1;
                const dias = Math.ceil(diferenciaMilisegundos / (1000 * 60 * 60 * 24));

                if (dias > 0) {
                    // Precio base del auto por los días
                    let costoTotal = dias * autoSeleccionado.precio;

                    // Sumar extras seleccionados por día
                    checkboxesExtras.forEach(chk => {
                        if (chk.checked) {
                            costoTotal += dias * parseInt(chk.value);
                        }
                    });

                    spanTotal.textContent = `$${costoTotal.toLocaleString()}`;
                    return costoTotal;
                } else {
                    spanTotal.textContent = "$0 (Fechas inválidas)";
                }
            }
            return 0;
        }

        // Calcular el total inicial por defecto al cargar la página
        calcularTotal();

        // 3. Control de redirección a la nueva vista de pago
        const form = document.getElementById("formTransaccion");

        if (form) {
            form.addEventListener("submit", (e) => {
                e.preventDefault();

                const totalCalculado = calcularTotal();
                if (totalCalculado <= 0) {
                    alert("Por favor seleccione un rango de fechas válido.");
                    return;
                }

                // Recolectar extras elegidos
                const extrasSeleccionados = [];
                checkboxesExtras.forEach(chk => {
                    if (chk.checked) {
                        extrasSeleccionados.push(chk.parentElement.textContent.trim());
                    }
                });

                // Empaquetamos todo en un objeto para pasarlo por localStorage a pago.html
                const datosTransaccion = {
                    vehiculo: autoSeleccionado,
                    fechaInicio: inputInicio.value,
                    fechaFin: inputFin.value,
                    extras: extrasSeleccionados,
                    totalFinal: totalCalculado
                };

                localStorage.setItem("datosTransaccion", JSON.stringify(datosTransaccion));

                // Salto a la nueva vista de pago
                window.location.href = "pago.html";
            });
        }

    } else {
        alert("No se encontró ningún vehículo seleccionado.");
        window.location.href = "index.html";
    }
});