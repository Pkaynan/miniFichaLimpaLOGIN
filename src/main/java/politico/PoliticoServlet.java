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
import percistense.PoliticoDAO;

@WebServlet("/politico")
public class PoliticoServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
	        throws ServletException, IOException {
	    
	    PoliticoDAO dao = new PoliticoDAO();
	    String query = request.getParameter("query");

	    List<Politico> politicos = new ArrayList<>();

	    if (query != null && !query.trim().isEmpty()) {
	        politicos = dao.findByNomeContaining(query.trim());
	    } else {
	        politicos = dao.findAll();
	    }

	    request.setAttribute("politicos", politicos);
	    RequestDispatcher rd = request.getRequestDispatcher("listar-politico.jsp");
	    rd.forward(request, response);
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
	}

}
