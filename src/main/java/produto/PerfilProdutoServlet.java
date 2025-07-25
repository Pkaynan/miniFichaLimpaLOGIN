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

@WebServlet("/perfilproduto")
public class PerfilProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String idStr = request.getParameter("id");
		if (idStr != null) {
			try {
				Long id = Long.parseLong(idStr);
				ProdutoDAO dao = new ProdutoDAO();
				Produto produto = dao.findById(id);
				if (produto != null) {
					request.setAttribute("produto", produto);
					RequestDispatcher rd = request.getRequestDispatcher("perfil-produto.jsp");
					rd.forward(request, response);
				} else {
					response.sendError(HttpServletResponse.SC_NOT_FOUND);
				}
			} catch (NumberFormatException e) {
				response.sendError(HttpServletResponse.SC_BAD_REQUEST);
			}
		} else {
			response.sendError(HttpServletResponse.SC_BAD_REQUEST);
		}
	}

}
