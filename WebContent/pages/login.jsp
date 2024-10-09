<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
<%@ include file="/partials/head.jsp"%>
<title>Login</title>
</head>
<body>
	
		<%@ include file="/partials/header.jsp"%>
	
	<div>
		<form action="/submit-login" method="POST">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required><br> <br> <label
				for="password">Password:</label> <input type="password"
				id="password" name="password" required><br> <br> <input
				type="submit" value="Login"> o <a href="${pageContext.request.contextPath}/pages/registrazione.jsp">Registrati</a>
		</form>
	</div>

		<%@ include file="/partials/footer.jsp"%>
	
</body>
</html>