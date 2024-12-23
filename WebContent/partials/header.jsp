<%@ page import="model.bean.Cliente"%>
<header>
	<nav class="navbar">
		<div class="left-block">
			<a href="${pageContext.request.contextPath}/pages/index.jsp"> <img
				class="logo"
				src="${pageContext.request.contextPath}/images/logo.png" alt="Logo"></a>
		</div>
		<div class="right-block">
			<form action="RicercaProdotti" method="get">
				<input type="text" id="searchForm" name="searchForm" required>
				<input type="submit" value="RicercaProdotti"></input>
			</form>
			<%
			Cliente cliente = (Cliente) session.getAttribute("cliente");
			%>
			<%
			if (cliente != null) {
			%>
			<%
			if ("admin".equals(cliente.getRuolo())) {
			%>
			<a href="${pageContext.request.contextPath}/pages/visualizzaClienti.jsp">Clienti</a>

			<%
			}
			%>
			<a href="${pageContext.request.contextPath}/Logout">Logout</a> <a
				href="${pageContext.request.contextPath}/pages/riepilogoOrdini.jsp">Riepilogo
				Ordini</a>
			<%
			} else {
			%>
			<a href="${pageContext.request.contextPath}/pages/login.jsp">Login</a>
			<%
			}
			%>
			<a href="${pageContext.request.contextPath}/pages/carrello.jsp">Carrello</a>
		</div>
	</nav>
	<div class="sub-navbar container">
		<div>
			<a href="${pageContext.request.contextPath}/pages/cucina.jsp">Cucina</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/pages/salaDaPranzo.jsp">Sala
				da pranzo</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/pages/cameraDaLetto.jsp">Camera
				da letto</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/pages/ufficio.jsp">Ufficio</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/pages/bagno.jsp">Bagno</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/pages/esterni.jsp">Esterni</a>
		</div>
	</div>
</header>