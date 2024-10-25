<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dao.OrdineDAO"%>
<%@ page import="model.bean.Ordine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>Riepilogo ordini</title>
</head>
<body>

	<%@ include file="/partials/header.jsp"%>
	<%
	Cliente user = (Cliente) request.getSession().getAttribute("cliente");
	OrdineDAO ordineDao = new OrdineDAO();
	String idUtente = user != null ? user.getCodiceFiscale() : null;
	ArrayList<Ordine> listaOrdini = null;

	if (idUtente != null) {
		listaOrdini = ordineDao.selectById(idUtente);
	}
	%>
	<main>
		<%
		if (listaOrdini != null && !listaOrdini.isEmpty()) {
			for (Ordine o : listaOrdini) {
		%>
		<div class="grid-item prodotto">
			<h2>
				Ordine ID:
				<%=o.getIdOrdine()%></h2>
			<p>
				Data Ordine:
				<%=o.getDataOrdine()%></p>
			<p>
				prezzoTotale:
				<%=o.getPrezzoTotale()%></p>
		</div>
		<%
		}
		}
		%>

	</main>
	<%@ include file="/partials/footer.jsp"%>

</body>
</html>