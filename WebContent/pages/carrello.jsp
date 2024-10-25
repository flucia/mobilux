<%@ page import="model.dao.ProdottoDAO"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="model.bean.Carrello"%>
<%@ page import="model.bean.Prodotto"%>
<%@ page import="model.bean.Cliente"%>
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
	<main>
		<%
		ProdottoDAO prodottoDAO = new ProdottoDAO();

		Cliente user = (Cliente) request.getSession().getAttribute("cliente");

		ArrayList<Carrello> carrelli = (ArrayList<Carrello>) request.getSession().getAttribute("carrello");

		if (carrelli == null) {
			carrelli = new ArrayList<Carrello>();
			request.getSession().setAttribute("carrello", carrelli);
		}

		double totale = 0;
		%>

		<div class="container justify-content-center gap-x-2 carrello-body">
			<div class="grid grid-cols-3 column-gap-2">
				<%
				if (!carrelli.isEmpty()) {

					for (Carrello item : carrelli) {
						Prodotto prodotto = prodottoDAO.selectAllProdottiById(item.getIdProdotto());

						if (prodotto != null) {
					int quantita = item.getQuantita();
					String nome = prodotto.getNome();
					String immagine = prodotto.getImmagine();
					double prezzo = prodotto.getPrezzo();
					totale += prezzo * quantita;
					request.getSession().setAttribute("totaleCarrello", totale);
				%>


				<img src="<%=request.getContextPath()%>/images/<%=immagine%>"
					alt="Immagine prodotto" class="prodotto-img mt-3">
				<div class="flex flex-col mt-3">
					<span><%=nome%></span> <span><%=prezzo%> €</span> <span>Quantità:
						<%=quantita%></span> <span>Modifica quantità</span>
					<div>
						<form action="../ModificaQuantitaCarrello" method="post">
							<input type="number" id="quantita" name="quantita" min="1"
								value="<%=quantita%>" required> <input type="hidden"
								name="idProdotto" value="<%=prodotto.getIdProdotto()%>">
							<button type="submit">Modifica</button>
						</form>
					</div>
				</div>

				<div class="flex flex-col gap-y-2 mt-3">
					<form action="../RimuoviItemCarrello" method=post>
						<input type="hidden" name="idProdotto"
							value="<%=prodotto.getIdProdotto()%>">
						<button type="submit" class="w-full">Rimuovi</button>
					</form>
				</div>

				<%
				}
				}
				} else {
				%>
				<span>Il tuo carrello è vuoto.</span>

				<%
				}
				if (user != null && !carrelli.isEmpty()) {
				%>
				<form action="../Checkout" method="post">
					<input type="submit" value="Checkout">
				</form>

				<span>Totale: <%=totale%> €
				</span>

				<%
				} else if(user == null && !carrelli.isEmpty()){
				%>
				<div class="flex justify-content-center gap-x-2 my-2">
					<a
						href="${pageContext.request.contextPath}/pages/registrazione.jsp">Registrati</a>
					o <a href="${pageContext.request.contextPath}/pages/login.jsp">Accedi</a>
					<input type="button" onClick="showToast()" value="Checkout">
				</div>

				<br>
				<%
				}
				%>

				<div id="toast" class="toast">Non puoi effettuare l'ordine se
					non sei registrato al nostro sito</div>

			</div>
		</div>

	</main>
	<%@ include file="/partials/footer.jsp"%>

</body>
<script>
	function showToast() {
		let toast = document.getElementById("toast");
		toast.className = "toast show";
		setTimeout(function() {
			toast.className = toast.className.replace("show", "");
		}, 3000);
	}
</script>
</html>
