package carrinho;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Carrinho;
import model.Produto;
import percistense.ProdutoDAO;

@WebServlet("/carrinho")
public class CarrinhoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		RequestDispatcher dispatcher = request.getRequestDispatcher("Carrinho.jsp");
		dispatcher.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String acao = request.getParameter("enviar");

		switch (acao) {
		case "adicionar": 
			adicionar(request, response);
			break;
		case "remover":
			remover(request, response);
			break;
		}
	}
	
	public void adicionar(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long produtoId = Long.parseLong(request.getParameter("produto"));
		ProdutoDAO dao = new ProdutoDAO();
		Produto produto = dao.findById(produtoId);
		Carrinho carrinho = CarrinhoViewHelper.recuperarCarrinho(request);
		carrinho.adicionar(produto);
		response.sendRedirect(request.getContextPath() + "/produto");
	}
	
	public void remover(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Long produtoId = Long.parseLong(request.getParameter("produto"));
		Carrinho carrinho = CarrinhoViewHelper.recuperarCarrinho(request);
		carrinho.remover(produtoId);
		response.sendRedirect(request.getContextPath() + "/carrinho");
	}

}
