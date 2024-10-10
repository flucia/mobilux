<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>Registrazione</title>
</head>
<body>

	<%@ include file="/partials/header.jsp"%>

	<div>
		<form action="/submit-registration" method="POST">
			<label for="nome">Nome:</label> <input type="text" id="nome"
				name="nome" required><br><br><label for="cognome">Cognome:</label>
			<input type="text" id="cognome" name="cognome" required><br><br>
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required><br> <br> <label
				for="email">Email:</label> <input type="email" id="email"
				name="email" required><br> <br> <label
				for="password">Password:</label> <input type="password"
				id="password" name="password" required><br> <br> <label
				for="confirm-password">Conferma Password:</label><input
				type="password" id="confirm-password" name="confirm-password"
				required><br> <br> <label for="indirizzo">Indirizzo:</label> <input type="text" id="indirizzo"
				name="indirizzo" required><br><br> <label for="cellulare">Cellulare:</label> <input type="text" id="cellulare"
				name="cellulare" required><br><br> <input type="submit"
				value="Registrati">
		</form>
	</div>

	<%@ include file="/partials/footer.jsp"%>

</body>
</html>