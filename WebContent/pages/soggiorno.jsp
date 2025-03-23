<%@ page import="model.dao.ProdottoDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.Prodotto"%>
<%@ page import="model.bean.Cliente"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<script
	src="<%=request.getContextPath()%>/scripts/toastAggiuntaCarrello.js"></script>
<%
String categoriaId = "2";
ProdottoDAO prodottoDao = new ProdottoDAO();
ArrayList<Prodotto> listaProdotti = prodottoDao.selectByIdCategoria("2");
%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Soggiorno</title>
</head>
<body>
	<%@ include file="/partials/header.jsp"%>
	<main>
		<div class="prodotto-body">
			<% if (cliente == null || "utente".equals(cliente.getRuolo())) { 
			for (Prodotto p : listaProdotti) {
			%>

			<div class="grid-item prodotto">
				<img src="<%=request.getContextPath()%>/images/<%=p.getImmagine()%>"
					alt="img-prodotto" class="prodotto-img">
				<h2><%=p.getNome()%></h2>
				<p><%=p.getDescrizione()%></p>
				<span><%=p.getPrezzo()%>€</span>
				<p>
					Disponibili:
					<%=p.getQuantitaDisponibile()%></p>
				<form class="quantitàProdotti" action="../AggiungiCarrello"
					method="post">
					<label for="quantita">Quantità:</label> <input type="number"
						id="quantita" name="quantita" min="1"
						max="<%=p.getQuantitaDisponibile()%>" value="1" required>
					<input type="hidden" name="idProdotto" value=<%=p.getIdProdotto()%>>
					<input type="hidden" name="quantitaDisponibile"
						value=<%=p.getQuantitaDisponibile()%>> <br></br>
					<button type="submit">Aggiungi al carrello</button>
				</form>

			</div>

			<%} } %>

			<% if (cliente != null && "admin".equals(cliente.getRuolo())) { 
			         for (Prodotto prodotto : listaProdotti) {
			%>

			<div class="grid-item prodotto">
				<form action="../ModificaProdotto" method="post">
					<img
						src="<%=request.getContextPath()%>/images/<%=prodotto.getImmagine()%>"
						alt="img-prodotto" class="prodotto-img"> <input
						type="hidden" name="idProdotto"
						value=<%=prodotto.getIdProdotto()%>> <input
						value="<%=prodotto.getNome()%>" name="nome" id="nome"> <input
						value="<%=prodotto.getDescrizione()%>" name="descrizione"
						id="descrizione"> <input value="<%=prodotto.getPrezzo()%>"
						name="prezzo" id="prezzo">€
					<button type="submit">Modifica</button>
				</form>

				<form action="../ModificaQuantitaDisponibile" method="post">
					<input type="number" id="quantitaDisponibile"
						name="quantitaDisponibile" min="1" value="1" required> <input
						type="hidden" name="idProdotto"
						value="<%=prodotto.getIdProdotto()%>">
					<button type="submit">Modifica quantità prodotto</button>
				</form>

				<form action="../EliminaProdottoCatalogo" method="post">
					<input type="hidden" name="idProdotto"
						value="<%=prodotto.getIdProdotto()%>">
					<button type="submit">Elimina prodotto</button>
				</form>
				<form action="../AggiungiCarrello" method="post">
					<label for="quantity">Quantità:</label> <input type="number"
						id="quantita" name="quantita" value="1" required> <input
						type="hidden" name="idProdotto"
						value=<%=prodotto.getIdProdotto()%>>
					<button type="submit">Aggiungi al carrello</button>
				</form>
			</div>
			<% } }%>

			<% if (cliente != null && "admin".equals(cliente.getRuolo())) { %>
			<div class="grid-item prodotto">
				<h3>
					Aggiungi un nuovo prodotto
					<%= categoriaId %></h3>
				<form action="../AggiungiProdottoCatalogo" method="post"">

					<label for="nome">Nome prodotto:</label> <input type="text"
						id="nome" name="nome" required><br> <label
						for="descrizione">Descrizione prodotto:</label>
					<textarea id="descrizione" name="descrizione" required></textarea>
					<br> <label for="prezzo">Prezzo (€):</label> <input
						type="number" id="prezzo" name="prezzo" required><br>

					<input type="hidden" id="quantità" name="quantità" min="1" required><br>

					<label for="quantitaDisponibile">Quantità disponibile:</label> <input
						type="number" id="quantitaDisponibile" name="quantitaDisponibile"
						min="1" required><br> <label for="immagine">Immagine
						prodotto:</label> <input type="text" id="immagine" name="immagine"
						required><br> <input type="hidden" name="categoria"
						value="<%= categoriaId %>">

					<button type="submit">Aggiungi prodotto</button>
				</form>
			</div>
			<% }	%>
		
	</main>
	<%

String toastMessage = (String) session.getAttribute("toastMessage");
if (toastMessage != null) { 
    session.removeAttribute("toastMessage");
%>
	<script>
    window.onload = function() {
        showToast("<%= toastMessage %>");
    };
</script>
	<% } %>
	<%@ include file="/partials/footer.jsp"%>
</body>

</html>