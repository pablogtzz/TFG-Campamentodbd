document.addEventListener('DOMContentLoaded', function() {
    const searchInput = document.getElementById('searchInput');
    const kidsInfo = document.getElementById('kidsInfo');
    const kidsCards = kidsInfo.getElementsByClassName('kid-card');

    searchInput.addEventListener('input', function() {
        const filter = searchInput.value.toLowerCase();

        Array.from(kidsCards).forEach(function(card) {
            const kidName = card.querySelector('.kid-name').textContent.toLowerCase();
            if (kidName.includes(filter)) {
                card.style.display = '';
            } else {
                card.style.display = 'none';
            }
        });
    });
});
