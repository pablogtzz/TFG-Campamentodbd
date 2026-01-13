function filtrarJuegosPorTipo() {
    var checkboxes = document.querySelectorAll('.tipo-checkbox:checked');
    var tiposSeleccionados = Array.from(checkboxes).map(function(checkbox) {
        return checkbox.value;
    });

    var juegoCards = document.querySelectorAll('.juego-card');
    juegoCards.forEach(function(card) {
        var tipoJuego = card.getAttribute('data-tipo');
        if (tiposSeleccionados.length === 0 || tiposSeleccionados.includes(tipoJuego)) {
            card.style.display = 'block';  // Mostrar tarjeta si coincide con el tipo seleccionado
        } else {
            card.style.display = 'none';   // Ocultar tarjeta si no coincide
        }
    });
}

// Agregar evento para filtrar cuando cambie algún checkbox
document.querySelectorAll('.tipo-checkbox').forEach(function(checkbox) {
    checkbox.addEventListener('change', filtrarJuegosPorTipo);
});