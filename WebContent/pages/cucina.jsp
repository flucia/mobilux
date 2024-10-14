<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>Cucina</title>
</head>
<body>

		<%@ include file="/partials/header.jsp"%>
		
	
	<div>
	<div>
	<img src="${pageContext.request.contextPath}/images/tavolo.jpg" alt="img-prodotto" style= width:10%>
	<button type="submit" name="idProdotto">Aggiungi al carrello</button>
	<button type="submit">Quantità</button>
	</div>
	</div>
	
		<%@ include file="/partials/footer.jsp"%>
	
</body>
</html>