<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>Registrazione</title>
<script src="<%=request.getContextPath()%>/scripts/validazione.js"></script>	
</head>
<body>

	<%@ include file="/partials/header.jsp"%>
<main>
	<div class="w-full flex justify-content-center login-body">
		<form class="form-registrazione-wrap" action="../Registrazione" method="post" id="registrazione"
		class="flex-col align-items-center">
		 <label for="cf">Codice Fiscale:</label>
        <input class="form-registrazione" type="text" id="cf" name="cf" onchange="validaCodiceFiscale(cf)" required>
        
        <label for="nome">Nome:</label>
        <input class="form-registrazione" type="text" id="nome" name="nome" required>
        
        <label for="cognome">Cognome:</label>
        <input class="form-registrazione" type="text" id="cognome" name="cognome" required>
        
        <label for="username">Username:</label>
        <input class="form-registrazione" type="text" id="username" name="username" required>
        
        <label for="email">Email:</label>
        <input class="form-registrazione" type="email" id="email" name="email" onchange="validateEmail(email)" required>
        <small>Formato valido: esempio@email.com</small>
        
        <label for="password">Password:</label>
        <input class="form-registrazione" type="password" id="password" name="password" required>
        
        <label for="indirizzo">Indirizzo:</label>
        <input class="form-registrazione" type="text" id="indirizzo" name="indirizzo" required>
        
        <label for="cellulare">Cellulare:</label>
        <input class="form-registrazione" type="text" id="cellulare" name="cellulare" onchange="validaCellulare(cellulare)" required>
        <small>Formato valido: +39	3456789012</small>
        
        <br> <br>
        
        <button type="submit">Registrati</button>
		</form>
	</div>
</main>
<%@ include file="/partials/footer.jsp"%>

</body>
</html>