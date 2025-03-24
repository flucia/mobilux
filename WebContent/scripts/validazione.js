document.addEventListener("DOMContentLoaded", () => {
    const form = document.getElementById("registrazione");

    if (form) {
        form.addEventListener("submit", validaForm);
    } else {
        console.error("Form non trovato! Assicurati che il form sia presente nella pagina.");
    }

    function validaEmail(email) {
        const mailformat = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
        return mailformat.test(email);
    }

    function validaCodiceFiscale(cf) {
        const cfformat = /^[A-Z]{6}[0-9]{2}[A-Z][0-9]{2}[A-Z][0-9]{3}[A-Z]$/i;
        return cfformat.test(cf);
    }

    function validaCellulare(cellulare) {
        const cellformat = /^\+?\d{10,15}$/;
        return cellformat.test(cellulare);
    }

    function validaForm(event) {
        event.preventDefault(); 
        const cf = document.getElementById("cf").value;
        const nome = document.getElementById("nome").value;
        const cognome = document.getElementById("cognome").value;
        const username = document.getElementById("username").value;
        const email = document.getElementById("email").value;
        const password = document.getElementById("password").value;
        const indirizzo = document.getElementById("indirizzo").value;
        const cellulare = document.getElementById("cellulare").value;

        // Esegui le validazioni
        if (!validaEmail(email)) {
            alert("Email non valida");
            return;
        }
        if (!validaCodiceFiscale(cf)) {
            alert("Codice Fiscale non valido");
            return;
        }
        if (!validaCellulare(cellulare)) {
            alert("Numero di cellulare non valido");
            return;
        }
        form.submit();
    }
});
