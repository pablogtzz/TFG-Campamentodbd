document.addEventListener('DOMContentLoaded', function() {
    const addMonitorBtn = document.getElementById('addMonitorBtn');
    const addMonitorModal = document.getElementById('addMonitorModal');
    const closeAddMonitorModalBtn = addMonitorModal.querySelector('.close');
    const addMonitorForm = document.getElementById('addMonitorForm');

    addMonitorBtn.addEventListener('click', function() {
        addMonitorModal.style.display = 'block';
    });

    closeAddMonitorModalBtn.addEventListener('click', function() {
        addMonitorModal.style.display = 'none';
    });

    window.addEventListener('click', function(event) {
        if (event.target == addMonitorModal) {
            addMonitorModal.style.display = 'none';
        }
    });

    addMonitorForm.addEventListener('submit', function(event) {
        // Validaciones o lógica adicional antes de enviar el formulario
    });
});
