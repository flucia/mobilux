<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>Login</title>
</head>
<body>
	<header>
		<nav class="navbar">
			<div class="left-block">
				<a href="#"><img class="logo" src="${pageContext.request.contextPath}/images/logo.png" alt="Logo"></a>
			</div>
			<div class="right-block">
				<form>
					<input id="searchForm">
					<button type="submit">Search</button>
				</form>
				<a href="pages/login.jsp">Login</a> 
				<a href="${pageContext.request.contextPath}/pages/carrello.jsp">Cart</a>
			</div>
		</nav>
	</header>
	<div></div>
	<footer>
		<div class="col-container">
			<div class="col">
				<h2>Per i nostri clienti</h2>
				<p>Contattaci</p>
				<p>Pagamenti e Sicurezza</p>
				<p>Consegna e installazione</p>
			</div>

			<div class="col">
				<h2>L'azienda</h2>
				<p>Chi siamo</p>
				<p>Ci troviamo in:</p>
				<p>Via San Benedetto 14 Comiziano (NA) 80030</p>

			</div>

			<div class="col">
				<h2>Social</h2>
				<p>Trovi il nostro store anche su:</p>
				<p>
					<i class="fab fa-facebook"></i>
				</p>
				<p>
					<i class="fab fa-instagram"></i>
				</p>
			</div>
		</div>
	</footer>
</body>
</html>