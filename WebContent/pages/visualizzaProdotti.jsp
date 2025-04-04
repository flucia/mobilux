<%@ page import="model.dao.ProdottoDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.Prodotto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>VisualizzaProdotti</title>
</head>
<%
ProdottoDAO prodottoDao = new ProdottoDAO();
String nome = request.getParameter("searchForm");
ArrayList<Prodotto> listaProdotti = prodottoDao.selectByName(nome);
%>
<body>
	<%@ include file="/partials/header.jsp"%>
	<main>
		<div class="prodotto-body">
			<%
			for (Prodotto p : listaProdotti) {
			%>
			<div id="prodotti" class="grid-item prodotto">
				<img src="<%=request.getContextPath()%>/images/<%=p.getImmagine()%>"
					alt="img-prodotto" class="prodotto-img">
				<h2><%=p.getNome()%></h2>
				<p><%=p.getDescrizione()%></p>
				<span><%=p.getPrezzo()%>€</span>
				<form action="../AggiungiCarrello" method="post">
					<label for="quantity">Quantità:</label> <input type="number"
						id="quantita" name="quantita" min="1" value="1" required>
					<input type="hidden" name="idProdotto" value=<%=p.getIdProdotto()%>>
					<br></br>
					<button type="submit">Aggiungi al carrello</button>
				</form>
			</div>
			<%
			}
			%>
		</div>
	</main>
	<%@ include file="/partials/footer.jsp"%>

</body>

</html>