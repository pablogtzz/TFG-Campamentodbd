document.addEventListener('DOMContentLoaded', function() {
    const buttons = document.querySelectorAll('.btn-ver');

    buttons.forEach(button => {
        button.addEventListener('click', function() {
            const nombre = this.getAttribute('data-nombre');
            const username = this.getAttribute('data-username');
            const telefono = this.getAttribute('data-telefono');
            const email = this.getAttribute('data-email');
            const rol = this.getAttribute('data-rol');
            const grupo = this.getAttribute('data-grupo');
            const subgrupo = this.getAttribute('data-subgrupo');
            const alergias = this.getAttribute('data-alergias');

            openModal(nombre, username, telefono, email, rol, grupo, subgrupo, alergias);
        });
    });
});

function openModal(nombre, username, telefono, email, rol, grupo, subgrupo, alergias) {
    document.getElementById('modalMonitorName').textContent = nombre;
    document.getElementById('modalMonitorUsername').textContent = 'Username: ' + username;
    document.getElementById('modalMonitorPhone').textContent = 'Teléfono: ' + telefono;
    document.getElementById('modalMonitorEmail').textContent = 'Email: ' + email;
    document.getElementById('modalMonitorRole').textContent = 'Rol: ' + rol;
    document.getElementById('modalMonitorGroup').textContent = 'Grupo: ' + grupo;
    document.getElementById('modalMonitorSubgroup').textContent = 'Subgrupo: ' + subgrupo;
    document.getElementById('modalMonitorAllergies').textContent = 'Alergias: ' + alergias;

    document.getElementById('monitorModal').style.display = 'block';
}

function closeModal() {
    document.getElementById('monitorModal').style.display = 'none';
}

window.onclick = function(event) {
    var modal = document.getElementById('monitorModal');
    if (event.target == modal) {
        modal.style.display = 'none';
    }
}
