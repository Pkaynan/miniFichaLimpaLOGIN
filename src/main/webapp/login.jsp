<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Login - Mini Ficha Limpa</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>

	<div class="navbar">
		<div class="logo">Mini Ficha Limpa</div>
	</div>

	<div class="container">
		<h2>Login</h2>

		<%
		if (request.getParameter("next") != null) {
		%>
		<div class="alert">Você precisa fazer login primeiro.</div>
		<%
		} else if (request.getParameter("erro") != null) {
		%>
		<div class="alert">Usuário ou senha inválidos.</div>
		<%
		}
		%>

		<form method="post" class="login-form">
			<label for="user">Usuário</label> <input type="text" id="user"
				name="user" required> <label for="pass">Senha</label> <input
				type="password" id="pass" name="pass" required>

			<button type="submit">Entrar</button>
		</form>
		
		<br>
		<br>
		<br>
		
		<div>
			<form method="get" class="login-form" action="${pageContext.request.contextPath}/cadastro">
				<button type="submit">Cadastrar</button>
			</form>

		</div>

	</div>

</body>
</html>
