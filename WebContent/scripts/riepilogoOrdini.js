function cercaOrdini(event) {
	event.preventDefault();

	const parola = $("#inputCerca").val().trim();
	const encodedParola = encodeURIComponent(parola);

	const url = `RicercaOrdini?query=${encodedParola}`;
	$("#tuttiOrdiniTable").addClass("hidden");


	$.ajax({
		url: url,
		method: "GET",
		success: function(data) {
			const risultatoDiv = $("#risultatoRicerca");

			if (!risultatoDiv.length) {
				console.error("Elemento con ID 'risultatoRicerca' non trovato nel DOM!");
				return;
			}

			risultatoDiv.empty();

			if (data.length === 0) {
				risultatoDiv.html("<p>Nessun ordine trovato.</p>");
				return;
			}


			const table = $('<table>').attr('border', '1').css('width', '100%').css('margin-top', '20px');
			const thead = $('<thead>');
			const headerRow = $('<tr>');


			['ID Ordine', 'Nome Cliente', 'Cognome Cliente', 'Data Ordine', 'Prezzo Totale'].forEach(headerText => {
				const th = $('<th>').text(headerText);
				headerRow.append(th);
			});

			thead.append(headerRow);
			table.append(thead);


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