<%@ page import="model.dao.CarrelloDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.Carrello"%>
<%@ page import="model.bean.Cliente"%>
<%@ page import="model.bean.Prodotto"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>Carrello</title>
</head>
<body>

	<%@ include file="/partials/header.jsp"%>
	<%
	CarrelloDAO carrelloDao = new CarrelloDAO();
	Cliente user = (Cliente) request.getSession().getAttribute("cliente");
	String sessionId = request.getSession().getId();
	String userId = (user != null) ? user.getCodiceFiscale() : sessionId;
	ArrayList<Carrello> carrelli = carrelloDao.selectAllCarrello(userId);
	String context = request.getContextPath();
	String home = context;
	%>
	<div>
		<table>
			<tr>
				<th>Rimuovi oggetto dal carrello</th>
				<th>Immagine</th>
				<th>Nome</th>
				<th>Prezzo</th>
				<th>Quantit&#224</th>
				<th>Modifica quantit&#224</th>
				<th>Totale carrello</th>
			</tr>
			<%
	
		if (carrelli != null && !carrelli.isEmpty()) {
			for (Carrello c : carrelli) {
		Prodotto prodotto = c.getProdotto();
		int quantita = c.getQuantita();
		if (prodotto != null && quantita > 0) {
			String nome = prodotto.getNome();
			String immagine = prodotto.getImmagine();
			double prezzo = prodotto.getPrezzo();
			double totale = prezzo * quantita;
	%>

			<tr>
				<td>
					<form action="../RimuoviItemCarrello" method=post>
						<input type="hidden" name="idProdotto"
							value="<%=prodotto.getIdProdotto()%>">
						<button type="submit">Rimuovi</button>
					</form>
				</td>

				<td><img
					src="<%=request.getContextPath()%>/images/<%=immagine%>"
					alt="Immagine prodotto" width="50"></td>
				<td><%=nome%></td>
				<td><%=prezzo%> €</td>
				<td><%=quantita%></td>
				<td>
					<form action="../ModificaQuantitaCarrello" method="post">
						<input type="number" id="quantita" name="quantita" min="1"
							value="1" required> <input type="hidden"
							name="idProdotto" value=<%=prodotto.getIdProdotto()%>>
						<button type="submit">Modifica</button>
					</form>
				</td>
				<td><%=totale%> €</td>
			</tr>
			<%
			}
			}
			} else {
			%>
			<tr>
				<td>Il tuo carrello è vuoto.</td>
			</tr>
			<%
			}
			%>
		</table>
	</div>

	<%@ include file="/partials/footer.jsp"%>

</body>
</html>
