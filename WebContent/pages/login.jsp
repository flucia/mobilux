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
	<main>
		<div class="w-full flex justify-content-center login-body">
			<form action="../Login" method="post"
				class="flex flex-col align-items-center gap-y-2">
				<label class="label" for="username">Username:</label> <input class="form-registrazione"
				 type="text"
					id="username" name="username" required> <label
					for="password">Password:</label> <input class="form-registrazione" type="password"
					id="password" name="password" required>
				<div>
					<input class="form-registrazione"type="submit" value="Login"> o <a
						href="${pageContext.request.contextPath}/pages/registrazione.jsp">Registrati</a>
				</div>
			</form>
		</div>
	</main>
	<%@ include file="/partials/footer.jsp"%>

</body>
</html>