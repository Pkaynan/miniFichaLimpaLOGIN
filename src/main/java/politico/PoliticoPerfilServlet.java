package politico;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Politico;
import model.Produto;
import percistense.PoliticoDAO;
import percistense.ProdutoDAO;


@WebServlet("/perfilpolitico")
public class PoliticoPerfilServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
 
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
		
			List<Produto> produtoList = new ArrayList<>();
	        
	        String idStr = request.getParameter("id");
	        if (idStr != null) {
	            try {
	                Long politicoiId = Long.parseLong(idStr);
	                PoliticoDAO politicoDao = new PoliticoDAO();
	                Politico politico = politicoDao.findById(politicoiId); 
	                
	                ProdutoDAO produtoDao = new ProdutoDAO();
	                produtoList = produtoDao.findByPolitico(politicoiId);
	                
	                request.setAttribute("politico", politico);
	                request.setAttribute("produto", produtoList);
	                RequestDispatcher rd = request.getRequestDispatcher("perfil-politico.jsp");
	                rd.forward(request, response);
	            } catch (NumberFormatException e) {
	                response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	            }
	        } else {
	            response.sendError(HttpServletResponse.SC_BAD_REQUEST);
	        }
	    }

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
	}

}
