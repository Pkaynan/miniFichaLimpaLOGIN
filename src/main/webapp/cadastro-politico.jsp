<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Cadastrar Político</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>
	<div class="navbar">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/home"
				style="color: white; text-decoration: none;">Ficha Limpa</a>
		</div>
	</div>

	<div class="container">
		<h1>Cadastrar Novo Político</h1>
		<form action="${pageContext.request.contextPath}/cadastropolitico"
			method="post">
			<label>Nome:</label> <input type="text" name="nome" required><br>

			<label>Número Eleitoral:</label> <input type="text"
				name="numeroEleitoral" required><br> <label>Nome
				Eleitoral:</label> <input type="text" name="nomeEleitoral" required><br>

			<label>ID do Partido:</label> <input type="number" name="partidoId"
				required><br>

			<button type="submit">Salvar</button>
		</form>
	</div>

	<p style="margin: 20px;">
		<button
			onclick="window.location.href='${pageContext.request.contextPath}/politico'">Voltar</button>
	</p>

</body>
</html>
