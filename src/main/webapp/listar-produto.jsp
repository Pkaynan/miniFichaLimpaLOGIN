<%@page import="carrinho.CarrinhoViewHelper"%>
<%@page import="model.Carrinho"%>
<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Produto"%>
<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8" />
<title>Lista de Produtos</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/style.css" />
</head>
<body>
	<div class="navbar">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/home">Mini Ficha
				Limpa</a>
		</div>

		<div class="form-busca">
			<form action="${pageContext.request.contextPath}/produto"
				method="get">
				<input type="text" name="query" placeholder="Buscar produto..." value="${param.query}" />
				<button type="submit">🔍</button>
			</form>
		</div>

		<div class="menu">
			<a href="${pageContext.request.contextPath}/carrinho">
        	<%
				Carrinho carrinho = CarrinhoViewHelper.recuperarCarrinho(request);
			%>
        	Carrinho <%=carrinho.getNumeroItens()%></a>
			<a href="${pageContext.request.contextPath}/cadastrarproduto">Cadastrar Produto</a>
			<a href="${pageContext.request.contextPath}/politico">Políticos</a>
		</div>
	</div>

	<div class="container">
		<h1>Lista de Produtos</h1>
		<table border="1" cellpadding="8" cellspacing="0">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Preço</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Produto> produtos = (List<Produto>) request.getAttribute("produtos");
				if (produtos != null && !produtos.isEmpty()) {
					for (Produto p : produtos) {
				%>
				<tr>
					<td><%=p.getId()%></td>
					<td>
						<a href="<%=request.getContextPath() + "/perfilproduto?id=" + p.getId()%>">
							<%=p.getNome()%>
						</a>
					</td>
					<td>R$ <%=String.format("%.2f", p.getPreco())%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="3">Nenhum produto encontrado.</td>
				</tr>
				<%
				}
				%>
			</tbody>
		</table>
	</div>

	<p style="margin: 20px;">
		<button
			onclick="window.location.href='${pageContext.request.contextPath}/home'">Voltar</button>
	</p>

</body>
</html>
