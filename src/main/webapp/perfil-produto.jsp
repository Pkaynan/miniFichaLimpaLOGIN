<%@page import="carrinho.CarrinhoViewHelper"%>
<%@page import="model.Carrinho"%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ page import="model.Produto" %>

<%
    Produto produto = (Produto) request.getAttribute("produto");
%>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8" />
    <title>Perfil do Produto</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/static/style.css" />
</head>
<body>
    <div class="navbar">
        <div class="logo">
            <a href="${pageContext.request.contextPath}/home">Mini Ficha Limpa</a>
        </div>
        <div class="menu">
       	 	<a href="${pageContext.request.contextPath}/carrinho">
        	<%
				Carrinho carrinho = CarrinhoViewHelper.recuperarCarrinho(request);
			%>
        	Carrinho <%=carrinho.getNumeroItens()%></a>
            <a href="${pageContext.request.contextPath}/cadastrarproduto">Cadastrar Produto</a>
        </div>
    </div>

    <div class="container">
        <h1><%= produto.getNome() %></h1>
        <p>
            <strong>Preço:</strong> R$ <%= String.format("%.2f", produto.getPreco()) %>
        </p>

        <form action="${pageContext.request.contextPath}/carrinho" method="post">
        	<input type="hidden" name="produto" value="<%= produto.getId()%>">
        	<button type="submit" name="enviar" value="adicionar">ADICIONAR AO CARRINHO</button>
        </form>

        <p style="margin: 20px;">
            <button onclick="history.back()">Voltar</button>
        </p>
    </div>
</body>
</html>
