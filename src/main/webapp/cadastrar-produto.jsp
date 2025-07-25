<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<title>Cadastrar Produto</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/style.css" />
</head>
<body>
	<div class="navbar">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/home">Mini Ficha
				Limpa</a>
		</div>
	</div>

	<div class="container">
		<h1>Cadastrar Produto</h1>
		<form action="${pageContext.request.contextPath}/cadastrarproduto" method="post">
			<label>Nome:</label><br /> <input type="text" name="nome" required /><br />

			<label>Preço:</label><br /> <input type="text" name="preco" required /><br />

			<button type="submit">Salvar</button>
		</form>
	</div>

	<p style="margin: 20px;">
		<button
			onclick="window.location.href='${pageContext.request.contextPath}/produto'">Voltar</button>
	</p>
</body>
</html>
