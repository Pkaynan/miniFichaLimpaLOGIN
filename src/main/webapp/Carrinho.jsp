<%@page import="model.Produto"%>
<%@page import="carrinho.CarrinhoViewHelper"%>
<%@page import="model.Carrinho"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Produtos</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/style.css">
<script src="carrinho.js"></script>
</head>
<body>

	<div class="navbar">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/home"
				style="color: white; text-decoration: none;"> Mini Ficha Limpa </a>
		</div>
			
		<div class="menu">
			<a href="${pageContext.request.contextPath}/politico">Políticos</a> 
			<a href="${pageContext.request.contextPath}/produto">Produtos</a>
		</div>
	</div>

	<h1>&#x1f6d2; Seu carrinho de compras</h1>

	<%
	Carrinho carrinho = CarrinhoViewHelper.recuperarCarrinho(request);
	if (carrinho.isEmpty()) {
	%>
	<p>Carrinho vazio</p>
	<%
	} else {
	%>
	<h2>
		Quantidade:
		<%=carrinho.getNumeroItens()%>
	</h2>

	<table border="1">
		<tr class="header">
			<th>ID</th>
			<th>Produto</th>
			<th>Preço</th>
		</tr>
		<%
		int row = 1;
		for (Produto p : carrinho.getItens()) {
		%>
		<tr id="row-<%=row%>">
			<td><%=p.getId()%></td>
			<td><%=p.getNome()%></td>
			<td><%=p.getPreco()%></td>
			<td>
				<form action="${pageContext.request.contextPath}/carrinho"
					method="post">
					<input type="hidden" name="produto" value="<%=p.getId()%>">
					<button type="submit" name="enviar" value="remover">Remover</button>
				</form>
			</td>
		</tr>
		<%
		row++;
		}
		%>
	</table>

	<%
	}
	%>

	<p style="margin: 20px;">
		<button
			onclick="window.location.href='${pageContext.request.contextPath}/produto'">Voltar</button>
	</p>
</body>
</html>