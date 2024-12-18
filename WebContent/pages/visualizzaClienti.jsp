<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.Cliente"%>
<%@ page import="model.dao.ClienteDAO"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<title>Clienti</title>
</head>
<body>
	<%@ include file="/partials/header.jsp"%>
	<%
	ClienteDAO clienteDao = new ClienteDAO();
	ArrayList<Cliente> listaClienti = clienteDao.selectAllCliente();
	%>

	<main>
		<% 
		if (listaClienti != null && !listaClienti.isEmpty()) { %>
		<table>
			<thead>
				<tr>
					<th>Nome</th>
					<th>Cognome</th>
					<th>Email</th>
					<th>Indirizzo</th>
					<th>Cellulare</th>
					<th></th>
				</tr>
			</thead>
			<% 
			for (Cliente c : listaClienti) {
		%>
			<tr>
				<td><%= c.getNome() %></td>
				<td><%= c.getCognome() %></td>
				<td><input type="email" name="email"
					value="<%= c.getEmail() %>"
					form="modificaForm<%= c.getCodiceFiscale() %>"></td>
				<td><input type="text" name="indirizzo"
					value="<%= c.getIndirizzo() %>"
					form="modificaForm<%= c.getCodiceFiscale() %>"></td>
				<td><input type="text" name="cellulare"
					value="<%= c.getCellulare() %>"
					form="modificaForm<%= c.getCodiceFiscale() %>"></td>

				<td>
					<form id="modificaForm<%= c.getCodiceFiscale() %>"
						action="ModificaClienteServlet" method="post">
						<input type="hidden" name="idCliente"
							value="<%= c.getCodiceFiscale() %>">
						<button type="submit">Salva</button>
					</form>
				</td>
				<td>
						<input type="hidden" name="idCliente"
							value="<%= c.getCodiceFiscale() %>">
						<a href="${pageContext.request.contextPath}/pages/riepilogoOrdini.jsp">Ordini</a>
				</td>
				<td>
					<form action="../EliminaCliente" method="post"
						style="display: inline;">
						<input type="hidden" name="idCliente"
							value="<%= c.getCodiceFiscale() %>">
						<button type="submit">X</button>
					</form>
				</td>
			</tr>
			<% } %>
		</table>
		<% } %>
	</main>
	<%@ include file="/partials/footer.jsp"%>
</body>
</html>