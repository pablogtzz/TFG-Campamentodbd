// Quitamos toda la lógica relacionada con la selección de subgrupos
document.addEventListener('DOMContentLoaded', function() {
    const addChildBtn = document.getElementById('addChildBtn');
    const addChildModal = document.getElementById('addChildModal');
    const closeAddChildBtn = addChildModal.querySelector('.close');
    const groupSelect = document.getElementById('group');

    addChildBtn.addEventListener('click', function() {
        addChildModal.style.display = 'block';
    });

    closeAddChildBtn.addEventListener('click', function() {
        addChildModal.style.display = 'none';
    });

    window.addEventListener('click', function(event) {
        if (event.target === addChildModal) {
            addChildModal.style.display = 'none';
        }
    });
});
