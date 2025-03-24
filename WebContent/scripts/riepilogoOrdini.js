function cercaOrdini(event) {
    event.preventDefault(); // Impedisce l'invio del form

    const parola = $("#inputCerca").val().trim(); // Ottieni la parola di ricerca
    const encodedParola = encodeURIComponent(parola); // Codifica la query

    const url = `RicercaOrdini?query=${encodedParola}`; // URL per la ricerca

    // Nascondi la tabella di tutti gli ordini per mostrare i risultati della ricerca
    $("#tuttiOrdiniTable").addClass("hidden");

    // Esegui la richiesta AJAX
    $.ajax({
        url: url, 
        method: "GET", 
        success: function(data) {
            const risultatoDiv = $("#risultatoRicerca");

            if (!risultatoDiv.length) {
                console.error("Elemento con ID 'risultatoRicerca' non trovato nel DOM!");
                return;
            }

            risultatoDiv.empty();  // Pulisci il contenuto precedente

            if (data.length === 0) {
                risultatoDiv.html("<p>Nessun ordine trovato.</p>");
                return;
            }

            // Crea la tabella per visualizzare i risultati
            const table = $('<table>').attr('border', '1').css('width', '100%').css('margin-top', '20px');
            const thead = $('<thead>');
            const headerRow = $('<tr>');

            // Intestazioni della tabella
            ['ID Ordine', 'Nome Cliente', 'Cognome Cliente', 'Data Ordine', 'Prezzo Totale'].forEach(headerText => {
                const th = $('<th>').text(headerText);
                headerRow.append(th);
            });

            thead.append(headerRow);
            table.append(thead);

            // Corpo della tabella con i dati
            const tbody = $('<tbody>');
            data.forEach(ordine => {
                const row = $('<tr>');
                
                row.append($('<td>').text(ordine.idOrdine));
                row.append($('<td>').text(ordine.nomeCliente));
                row.append($('<td>').text(ordine.cognomeCliente));
                row.append($('<td>').text(ordine.dataOrdine));
                row.append($('<td>').text(ordine.prezzoTotale));

                tbody.append(row);
            });

            table.append(tbody);
            risultatoDiv.append(table);
        },
        error: function(xhr, status, error) {
            console.error("Errore nella richiesta:", error);
        }
    });
}


/*
function cercaOrdini(event) {
    event.preventDefault();

    const parola = document.getElementById("inputCerca").value.trim();
    const encodedParola = encodeURIComponent(parola);
   
    const url = `RicercaOrdini?query=`+ encodedParola;
  
    const tuttiOrdiniTable = document.getElementById("tuttiOrdiniTable");
    if (tuttiOrdiniTable) {
        tuttiOrdiniTable.classList.add("hidden");
    }

    fetch(url)
        .then(response => {
            if (!response.ok) {
                throw new Error(`HTTP error! status: ${response.status}`);
            }
            return response.json();
        })
        .then(data => {
            const risultatoDiv = document.getElementById("risultatoRicerca");
            if (!risultatoDiv) {
                console.error("Elemento con ID 'risultatoRicerca' non trovato nel DOM!");
                return;
            }
            risultatoDiv.innerHTML = ""; 

            if (data.length === 0) {
                risultatoDiv.innerHTML = "<p>Nessun ordine trovato.</p>";
                return;
            }
            
            const table = document.createElement('table');
            table.border = "1";
            table.style.width = "100%";
            table.style.marginTop = "20px";

            const thead = document.createElement('thead');
            const headerRow = document.createElement('tr');
            ['ID Ordine', 'Nome Cliente', 'Cognome Cliente', 'Data Ordine', 'Prezzo Totale'].forEach(headerText => {
                const th = document.createElement('th');
                th.textContent = headerText;
                headerRow.appendChild(th);
            });
            thead.appendChild(headerRow);
            table.appendChild(thead);

            const tbody = document.createElement('tbody');
            data.forEach(ordine => {
                const row = document.createElement('tr');
                
                const cellIdOrdine = document.createElement('td');
                cellIdOrdine.textContent = ordine.idOrdine;
                row.appendChild(cellIdOrdine);

                const cellNomeCliente = document.createElement('td');
                cellNomeCliente.textContent = ordine.nomeCliente;
                row.appendChild(cellNomeCliente);
                
                const cellCognomeCliente = document.createElement('td');
                cellCognomeCliente.textContent = ordine.cognomeCliente;
                row.appendChild(cellCognomeCliente);

                const cellDataOrdine = document.createElement('td');
                cellDataOrdine.textContent = ordine.dataOrdine;
                row.appendChild(cellDataOrdine);

                const cellPrezzoTotale = document.createElement('td');
                cellPrezzoTotale.textContent = ordine.prezzoTotale;
                row.appendChild(cellPrezzoTotale);

                tbody.appendChild(row);
            });

            table.appendChild(tbody);
            risultatoDiv.appendChild(table);
        })
        .catch(error => {
            console.error("Errore nella richiesta:", error);
        });
}
*/