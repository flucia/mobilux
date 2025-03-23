<%@ page import="model.bean.Cliente"%>

<header>
	<nav class="navbar">
		<div class="left-block">
			<a href="${pageContext.request.contextPath}/pages/index.jsp">
				<img class="logo" src="${pageContext.request.contextPath}/images/logo.png" alt="Logo" />
			</a>
		</div>
		<div class="right-block gap-x-1">
			<form id="searchForm" class="searchForm gap-x-1">
				<input class="input-search mobile-width" type="text" id="searchInput"
					name="searchForm" placeholder="cerca..." />
				<button class="input-search" type="submit" value="Cerca">
					<img class="" src="${pageContext.request.contextPath}/images/search.svg" alt="Search" />
				</button>
			</form>
			<div id="risultati"></div>
			<% Cliente cliente = (Cliente) session.getAttribute("cliente"); %>
			<% if (cliente != null) { %>
			<% if ("admin".equals(cliente.getRuolo())) { %>
			<a class="login-label" href="${pageContext.request.contextPath}/pages/visualizzaClienti.jsp">Clienti</a>
			<%
			}
			%>
			<div class="dropdown">
				<a>
					<i id="icon" class="fa fa-user"></i>
				</a>
				<div class="dropdown-content">
					<a href="${pageContext.request.contextPath}/pages/riepilogoOrdini.jsp">
						Riepilogo Ordini
					</a>
				</div>
			</div>
			<a href="${pageContext.request.contextPath}/pages/carrello.jsp">
				<span id="icon">
					<img class="" src="${pageContext.request.contextPath}/images/cart.svg" alt="Cart" />
				</span>
				<span class="tooltip">Carrello</span>
			</a>
			<a href="${pageContext.request.contextPath}/Logout">
				<span id="icon">
					<img class="" src="${pageContext.request.contextPath}/images/box-arrow-right.svg" alt="logout" />
				</span>
				<span class="tooltip">Logout</span>
			</a>
			<%
			} else {
			%>
			<a href="${pageContext.request.contextPath}/pages/login.jsp" class="login-label">
				Login
			</a>
			<% } %>
		</div>
	</nav>
	<div class="sub-navbar container">
		<div>
			<a href="${pageContext.request.contextPath}/pages/cucina.jsp">Cucina</a>
		</div>
		<div>
			<a href="${pageContext.request.contextPath}/pages/soggiorno.jsp">Soggiorno</a>
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
<script src="../scripts/ricerca.js"></script>