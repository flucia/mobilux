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
<%
ProdottoDAO prodottoDao = new ProdottoDAO();
ArrayList<Prodotto> listaProdotti = prodottoDao.selectByIdCategoria("1");
%>
<title>Cucina</title>
</head>
<body>
	<%@ include file="/partials/header.jsp"%>
	<main>
		<div class="prodotto-body">

			<%
			for (Prodotto p : listaProdotti) {
			%>

			<div class="grid-item prodotto">
				<img src="<%=request.getContextPath()%>/images/<%=p.getImmagine()%>"
					alt="img-prodotto" class="prodotto-img">
				<h2><%=p.getNome()%></h2>
				<p><%=p.getDescrizione()%></p>
				<span><%=p.getPrezzo()%>€</span>
				<p>Disponibili: <%=p.getQuantitaDisponibile()%></p>
				  <% if ("admin".equals(cliente.getRuolo())) { %>	
				     <form action="../ModificaQuantitaDisponibile" method="post">
						 <input type="number" id="quantitaDisponibile" name="quantitaDisponibile" 
                   min="1" value="1" required>
                   <input type="hidden" name="idProdotto" value="<%=p.getIdProdotto()%>">
				<button type="submit">Aggiungi prodotti</button>
				</form>	
				 <form action="../EliminaProdottoCatalogo" method="post">
                   <input type="hidden" name="idProdotto" value="<%=p.getIdProdotto()%>">
				<button type="submit">Elimina prodotto</button>
				</form>	
				
				<% } %>
				<form action="../AggiungiCarrello" method="post">
					<label for="quantity">Quantità:</label> <input type="number"
						id="quantita" name="quantita" min="1"  max="<%=p.getQuantitaDisponibile()%>" 
						 value="1" required>
					<input type="hidden" name="idProdotto" value=<%=p.getIdProdotto()%>>
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