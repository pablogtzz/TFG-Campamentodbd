document.addEventListener('DOMContentLoaded', () => {
    const modal = document.getElementById("modalNuevaTarea");
    const btn = document.getElementById("openModalBtn");
    const closeModal = document.getElementsByClassName("close")[0];

    btn.addEventListener('click', () => {
        modal.style.display = "block";
    });

    closeModal.addEventListener('click', () => {
        modal.style.display = "none";
    });

    window.addEventListener('click', (event) => {
        if (event.target == modal) {
            modal.style.display = "none";
        }
    });
});
