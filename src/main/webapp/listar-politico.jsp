<%@page import="carrinho.CarrinhoViewHelper"%>
<%@page import="model.Carrinho"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="java.util.List"%>
<%@ page import="model.Politico"%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
<meta charset="UTF-8">
<title>Lista de Políticos</title>
<link rel="stylesheet"
	href="${pageContext.request.contextPath}/static/style.css">
</head>
<body>

	<div class="navbar">
		<div class="logo">
			<a href="${pageContext.request.contextPath}/home"
				style="color: white; text-decoration: none;"> Mini Ficha Limpa </a>
		</div>

		<div class="form-busca">
			<form action="${pageContext.request.contextPath}/politico"
				method="get">
				<input type="text" name="query"
					placeholder="Digite o nome do político" value="${param.query}">
				<button type="submit">🔍</button>
			</form>
		</div>

		<div class="menu">
			<a href="${pageContext.request.contextPath}/carrinho">
        	<%
				Carrinho carrinho = CarrinhoViewHelper.recuperarCarrinho(request);
			%>
        	Carrinho <%=carrinho.getNumeroItens()%></a>
			<a href="${pageContext.request.contextPath}/cadastropolitico">Cadastrar Político</a> 
			<a href="${pageContext.request.contextPath}/produto">Produtos</a>
		</div>
	</div>

	<div class="container">
		<h1>Lista de Políticos</h1>

		<table border="1" cellpadding="8" cellspacing="0">
			<thead>
				<tr>
					<th>ID</th>
					<th>Nome</th>
					<th>Número Eleitoral</th>
					<th>Nome Eleitoral</th>
					<th>Partido (ID)</th>
				</tr>
			</thead>
			<tbody>
				<%
				List<Politico> politicos = (List<Politico>) request.getAttribute("politicos");
				if (politicos != null) {
					for (Politico p : politicos) {
				%>
				<tr>
					<td><%=p.getId()%></td>
					<td><a
						href="<%=request.getContextPath()%>/perfilpolitico?id=<%=p.getId()%>">
							<%=p.getNome()%>
					</a></td>
					<td><%=p.getNumeroEleitoral()%></td>
					<td><%=p.getNomeEleitoral()%></td>
					<td><%=p.getPartido()%></td>
				</tr>
				<%
				}
				} else {
				%>
				<tr>
					<td colspan="5">Nenhum político encontrado.</td>
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
