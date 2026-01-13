document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.btn-ver');

    buttons.forEach(button => {
        button.addEventListener('click', function() {
            const titulo = this.getAttribute('data-titulo');
            const tipo = this.getAttribute('data-tipo');
            const duracion = this.getAttribute('data-duracion');
            const espacios = this.getAttribute('data-espacios');
            const descripcion = this.getAttribute('data-descripcion');
            const materiales = this.getAttribute('data-materiales');

            openModal(titulo, tipo, duracion, espacios, descripcion, materiales);
        });
    });
});

function openModal(titulo, tipo, duracion, espacios, descripcion, materiales) {
    // Asignar datos al modal
    document.getElementById('modalJuegoTitulo').textContent = titulo;
    document.getElementById('modalJuegoTipo').textContent = 'Tipo de Actividad: ' + tipo;
    document.getElementById('modalJuegoDuracion').textContent = 'Duración: ' + duracion + ' minutos';
    document.getElementById('modalJuegoEspacios').textContent = 'Espacios: ' + espacios;
    document.getElementById('modalJuegoDescripcion').textContent = 'Descripción: ' + descripcion;
    document.getElementById('modalJuegoMateriales').textContent = 'Materiales: ' + materiales;

    // Mostrar el modal
    document.getElementById('juegoModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('juegoModal').style.display = 'none';
}

// Cerrar el modal si se hace clic fuera del contenido del modal
window.onclick = function(event) {
    var modal = document.getElementById('juegoModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}
