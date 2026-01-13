document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const monitorCards = document.querySelectorAll('.monitor-card');

    searchInput.addEventListener('input', function() {
        const searchValue = searchInput.value.toLowerCase();

        monitorCards.forEach(card => {
            const nombre = card.querySelector('.monitor-name').textContent.toLowerCase();
            if (nombre.includes(searchValue)) {
                card.style.display = '';
            } else {
                card.style.display = 'none';
            }
        });
    });
});
