<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dao.OrdineDAO"%>
<%@ page import="model.dao.ClienteDAO"%>
<%@ page import="model.bean.Ordine"%>
<%@ page import="model.bean.Cliente"%>
<%@ page import="model.bean.OrdineCliente"%>
<%@ page import="java.util.List"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
<title>Riepilogo ordini</title>
</head>
<body>

	<%@ include file="/partials/header.jsp"%>
	<%
	Cliente user = (Cliente) request.getSession().getAttribute("cliente");
	OrdineDAO ordineDao = new OrdineDAO();
	ClienteDAO clienteDao = new ClienteDAO();

	String idUtente = user != null ? user.getCodiceFiscale() : null;
	ArrayList<Ordine> listaOrdini = null;
	ArrayList<Ordine> tuttiGliOrdini = ordineDao.selectAll();

	if (idUtente != null) {
		listaOrdini = ordineDao.selectById(idUtente);
	}
	%>
	<main>
		<div id="listaOrdini">
			<%
			if ("admin".equals(cliente.getRuolo())) {
			%>
			<form onsubmit="cercaOrdini(event)">
				<input type="text" id="inputCerca" placeholder="cerca">
				<button type="submit">Cerca</button>
			</form>
			<div id="risultatoRicerca"></div>
			<%
			}
			%>
			<%
			if (listaOrdini != null && !listaOrdini.isEmpty()) {
				if ("admin".equals(cliente.getRuolo())) {
			%>
			<table id="tuttiOrdiniTable" border="1"
				style="width: 100%; margin-top: 20px;">
				<thead>
					<tr>
						<th>ID Ordine</th>
						<th>Nome Cliente</th>
						<th>Cognome Cliente</th>
						<th>Data Ordine</th>
						<th>Prezzo Totale</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Ordine o : tuttiGliOrdini) {
						Cliente cliente1 = clienteDao.selectById(o.getIdUtente());
					%>
					<tr>
						<td><%=o.getIdOrdine()%></td>
						<td><%=cliente1.getNome()%></td>
						<td><%=cliente1.getCognome()%></td>
						<td><%=o.getDataOrdine()%></td>
						<td><%=o.getPrezzoTotale()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			} else {
			%>
			<table border="1" style="width: 100%; margin-top: 20px;">
				<thead>
					<tr>
						<th>Nome Cliente</th>
						<th>Cognome Cliente</th>
						<th>Data Ordine</th>
						<th>Prezzo Totale</th>
					</tr>
				</thead>
				<tbody>
					<%
					for (Ordine or : listaOrdini) {
						Cliente cliente2 = clienteDao.selectById(or.getIdUtente());
					%>
					<tr>
						<td><%=cliente2.getNome()%></td>
						<td><%=cliente2.getCognome()%></td>
						<td><%=or.getDataOrdine()%></td>
						<td><%=or.getPrezzoTotale()%></td>
					</tr>
					<%
					}
					%>
				</tbody>
			</table>
			<%
			}
			}
			%>

			<style>
.hidden {
	display: none;
}
</style>

			<script>
function cercaOrdini(event) {
    event.preventDefault();

    const parola = document.getElementById("inputCerca").value.trim();
    const encodedParola = encodeURIComponent(parola);
   
    const url = `RicercaOrdini?query=`+ encodedParola;
  
    // Hide the original table when searching
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
            ['ID Ordine', 'Nome Cliente', 'Data Ordine', 'Prezzo Totale'].forEach(headerText => {
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
</script>
</body>
</html>