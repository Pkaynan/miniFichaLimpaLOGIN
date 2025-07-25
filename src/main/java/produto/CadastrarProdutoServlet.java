package produto;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import percistense.ProdutoDAO;


@WebServlet("/cadastrarproduto")
public class CadastrarProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
    
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		RequestDispatcher rd = request.getRequestDispatcher("cadastrar-produto.jsp");
		rd.forward(request, response);
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("nome");
		String precoStr = request.getParameter("preco");

		try {
			double preco = Double.parseDouble(precoStr);

			Produto produto = new Produto();
			produto.setNome(nome);
			produto.setPreco(preco);

			ProdutoDAO dao = new ProdutoDAO();
			dao.save(produto);

			response.sendRedirect(request.getContextPath() + "/produto");
		} catch (NumberFormatException e) {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Preço inválido");
		}
	}


}
