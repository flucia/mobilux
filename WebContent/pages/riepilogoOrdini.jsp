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
			<style>
.hidden {
	display: none;
}
</style>
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
		<div class="order" id="listaOrdini">
			<%
			if ("admin".equals(cliente.getRuolo())) {
			%>
			<form onsubmit="cercaOrdini(event)">
				<input class="input-search mobile-width" type="text" id="inputCerca" placeholder="cerca">
				<button class="button-order" type="submit">Cerca</button>
			</form>
			<div id="risultatoRicerca"></div>
			<%
			}
			%>
			<%
			if (listaOrdini != null && !listaOrdini.isEmpty()) {
				if ("admin".equals(cliente.getRuolo())) {
			%>
			<table id="tuttiOrdiniTable">
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
</div>
</main>
</body>
</html>