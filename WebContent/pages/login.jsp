<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.net.URLDecoder"%>
<%@ page import="javax.servlet.http.Cookie"%>

<!DOCTYPE html>
<html lang="it">
<head>
<script src="<%=request.getContextPath()%>/scripts/loginVal.js"></script>
<%@ include file="/partials/head.jsp"%>
<title>Login</title>
</head>
<body>

	<%@ include file="/partials/header.jsp"%>
	<main>
		<div class="w-full flex justify-content-center login-body">
			<form id="loginForm" action="../Login" method="post"
				class="flex flex-col align-items-center gap-y-2">
				<label class="label" for="username">Username:</label> <input
					class="form-registrazione" type="text" id="username"
					name="username" required oninput="validateUsername()"> <label
					for="password">Password:</label> <input class="form-registrazione"
					type="password" id="password" name="password" required
					oninput="validatePassword()">

				<div>
					<input class="form-registrazione" type="submit" value="Login">
					o <a
						href="${pageContext.request.contextPath}/pages/registrazione.jsp">Registrati</a>
				</div>

				<%
				String errorMessage = null;
				Cookie[] cookies = request.getCookies();
				if (cookies != null) {
					for (Cookie cookie : cookies) {
						if ("errorMessage".equals(cookie.getName())) {
					errorMessage = URLDecoder.decode(cookie.getValue(), "UTF-8");

					Cookie cookieToDelete = new Cookie("errorMessage", "");
					cookieToDelete.setMaxAge(0);
					response.addCookie(cookieToDelete);
					break;
						}
					}
				}
				if (errorMessage != null) {
				%>
				<div class="errore"><%=errorMessage%></div>
				<%
}
%>

				<div id="errorMessagesU" class="errore"></div>
				<div id="errorMessagesP" class="errore"></div>
			</form>
		</div>
	</main>

	<%@ include file="/partials/footer.jsp"%>
	<script>
		validateForm()
	</script>
</body>
</html>
