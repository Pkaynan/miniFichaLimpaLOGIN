package produto;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Produto;
import percistense.ProdutoDAO;


@WebServlet("/produto")
public class ProdutoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        ProdutoDAO dao = new ProdutoDAO();
        String query = request.getParameter("query");
        List<Produto> produtos = new ArrayList<>();

        if (query != null && !query.trim().isEmpty()) {
            produtos = dao.findByNomeContaining(query.trim());
        } else {
            produtos = dao.findAll();
        }

        request.setAttribute("produtos", produtos);
        RequestDispatcher rd = request.getRequestDispatcher("listar-produto.jsp");
        rd.forward(request, response);
    }

}
