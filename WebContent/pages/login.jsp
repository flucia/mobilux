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

	<div class="w-full flex justify-content-center login-body">
		<form action="/submit-login" method="POST"
			class="flex flex-col align-items-center gap-y-2">
			<label for="username">Username:</label> <input type="text"
				id="username" name="username" required> 
			<label
				for="password">Password:</label> <input type="password"
				id="password" name="password" required>
			<div>
				<input type="submit" value="Login"> o <a
					href="${pageContext.request.contextPath}/pages/registrazione.jsp">Registrati</a>
			</div>
		</form>
	</div>

	<%@ include file="/partials/footer.jsp"%>

</body>
</html>