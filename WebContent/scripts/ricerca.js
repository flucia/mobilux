$(document).ready(function() {
    $('#searchForm').on('submit', function(event) {
        event.preventDefault();
        var nome = $('#searchInput').val();
        $.ajax({
            url: "/mobilux/RicercaProdotti",
            type: "GET",
            data: { searchForm: nome },
            success: function() {
                window.location.href = "/mobilux/RicercaProdotti?searchForm=" + encodeURIComponent(nome);
            },
            error: function(xhr, status, error) {
                alert("Errore nella ricerca. Riprova.");
            }
        });
    });
});
