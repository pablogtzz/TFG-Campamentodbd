document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.btn-ver');

    buttons.forEach(button => {
        button.addEventListener('click', function() {
            const nombre = this.getAttribute('data-nombre');
            const estado = this.getAttribute('data-estado');
            const descripcion = this.getAttribute('data-descripcion');
            const dia = this.getAttribute('data-dia');
            const monitor = this.getAttribute('data-monitor');

            openModal(nombre, estado, descripcion, dia, monitor);
        });
    });
});

function openModal(nombre, estado, descripcion, dia, monitor) {
    // Asignar datos al modal
    document.getElementById('modalTareaNombre').textContent = nombre;
    document.getElementById('modalTareaEstado').textContent = 'Estado: ' + estado;
    document.getElementById('modalTareaDescripcion').textContent = 'Descripción: ' + descripcion;
    document.getElementById('modalTareaDia').textContent = 'Día: ' + dia;
    document.getElementById('modalTareaMonitor').textContent = 'Monitor Asignado: ' + monitor;

    // Mostrar el modal
    document.getElementById('modalVerTarea').style.display = 'block';
}

function closeModal(modalId) {
    document.getElementById(modalId).style.display = 'none';
}

// Cerrar el modal si se hace clic fuera del contenido del modal
window.onclick = function(event) {
    const modalVerTarea = document.getElementById('modalVerTarea');
    if (event.target == modalVerTarea) {
        modalVerTarea.style.display = 'none';
    }
}
