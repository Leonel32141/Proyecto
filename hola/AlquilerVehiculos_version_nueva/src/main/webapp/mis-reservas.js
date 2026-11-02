document.addEventListener("DOMContentLoaded", () => {
    // Cargar nombre del usuario en el saludo de la navbar
    const nombreUsuario = localStorage.getItem('usuarioNombre');
    if (nombreUsuario) {
        const saludoEl = document.getElementById("saludoUsuario");
        if (saludoEl) {
            saludoEl.textContent = `Hola, ${nombreUsuario}`;
        }
        const btnLogout = document.getElementById("btnLogout");
        if (btnLogout) {
            btnLogout.style.display = "inline-block";
            btnLogout.onclick = () => {
                localStorage.clear();
                window.location.href = "login.html";
            };
        }
    }

    cargarReservas();
});

function cargarReservas() {
    fetch('misReservas')
        .then(response => response.json())
        .then(data => {
            const tbody = document.getElementById("tablaMisReservasBody");
            tbody.innerHTML = "";

            if (data.length === 0) {
                tbody.innerHTML = `<tr><td colspan="7" style="text-align: center; padding: 20px; color: #64748b;">No hay reservas registradas.</td></tr>`;
                return;
            }

            data.forEach(r => {
                let badgeClass = "badge-reservada";
                if (r.estadoReserva === 'EN_CURSO') badgeClass = "badge-curso";
                if (r.estadoReserva === 'FINALIZADA') badgeClass = "badge-finalizada";
                if (r.estadoReserva === 'CANCELADA') badgeClass = "badge-cancelada";

                let montoVal = r.montoTotal !== undefined ? r.montoTotal : 
                               (r.monto_total !== undefined ? r.monto_total : 
                               (r.totalFinal !== undefined ? r.totalFinal : 0));

                let tr = document.createElement("tr");
                tr.style.borderBottom = "1px solid #f1f5f9";
                
                tr.innerHTML = `
                    <td style="padding: 14px 12px;">${r.idAlquiler}</td>
                    <td style="padding: 14px 12px;">${r.idVehiculo}</td>
                    <td style="padding: 14px 12px;">${r.fechaInicioPautada}</td>
                    <td style="padding: 14px 12px;">${r.fechaFinPautada}</td>
                    <td style="padding: 14px 12px;"><span class="${badgeClass}">${r.estadoReserva}</span></td>
                    <td style="padding: 14px 12px;">$${Number(montoVal).toLocaleString('es-AR')}</td>
                    <td style="padding: 14px 12px;">
                        ${r.estadoReserva === 'RESERVADA' ? `<button class="btn-cancelar" onclick="cancelarReserva(${r.idAlquiler})">Cancelar</button>` : ''}
                    </td>
                `;
                tbody.appendChild(tr);
            });
        })
        .catch(error => {
            console.error("Error al cargar reservas:", error);
            document.getElementById("tablaMisReservasBody").innerHTML = `<tr><td colspan="7" style="text-align: center; padding: 20px; color: #dc2626;">Error al cargar las reservas.</td></tr>`;
        });
}

function cancelarReserva(idAlquiler) {
    if (confirm("¿Estás seguro de que deseas cancelar esta reserva?")) {
        fetch('misReservas', {
            method: 'POST',
            headers: {
                'Content-Type': 'application/x-www-form-urlencoded',
            },
            body: 'accion=cancelar&idAlquiler=' + idAlquiler
        })
        .then(response => response.json())
        .then(data => {
            alert(data.message);
            if (data.status === 'success') {
                cargarReservas(); 
            }
        })
        .catch(error => {
            console.error('Error:', error);
            alert("Ocurrió un error al procesar la cancelación.");
        });
    }
}