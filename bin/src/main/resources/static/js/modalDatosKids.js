document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.btn-ver');

    buttons.forEach(button => {
        button.addEventListener('click', function() {
            const nombre = this.getAttribute('data-nombre');
            const fechaNacimiento = this.getAttribute('data-fecha-nacimiento');
            const grupo = this.getAttribute('data-grupo');
            const talla = this.getAttribute('data-talla');
            const padreNombre = this.getAttribute('data-padre-nombre');
            const padreTelefono = this.getAttribute('data-padre-telefono');
            const madreNombre = this.getAttribute('data-madre-nombre');
            const madreTelefono = this.getAttribute('data-madre-telefono');
            const subgrupo = this.getAttribute('data-subgrupo');
            const alergias = this.getAttribute('data-alergias');

            openModal(nombre, fechaNacimiento, grupo, talla, padreNombre, padreTelefono, madreNombre, madreTelefono, subgrupo, alergias);
        });
    });
});

function openModal(nombre, fechaNacimiento, grupo, talla, padreNombre, padreTelefono, madreNombre, madreTelefono, subgrupo, alergias) {
    // Asignar datos al modal
    document.getElementById('modalKidName').textContent = nombre;
    document.getElementById('modalKidDOB').textContent = 'Fecha de nacimiento: ' + fechaNacimiento;
    document.getElementById('modalKidShirtSize').textContent = 'Talla de Camiseta: ' + talla;
    document.getElementById('modalKidParentName').textContent = 'Nombre del Padre: ' + padreNombre;
    document.getElementById('modalKidParentPhone').textContent = 'Teléfono del Padre: ' + padreTelefono;
    document.getElementById('modalKidMotherName').textContent = 'Nombre de la Madre: ' + madreNombre;
    document.getElementById('modalKidMotherPhone').textContent = 'Teléfono de la Madre: ' + madreTelefono;
    document.getElementById('modalKidGroup').textContent = 'Grupo: ' + grupo;
    document.getElementById('modalKidSubgroup').textContent = 'Subgrupo: ' + subgrupo;
    document.getElementById('modalKidAllergies').textContent = 'Alergias: ' + alergias;

    // Mostrar el modal
    document.getElementById('kidModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('kidModal').style.display = 'none';
}

// Cerrar el modal si se hace clic fuera del contenido del modal
window.onclick = function(event) {
    var modal = document.getElementById('kidModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}
