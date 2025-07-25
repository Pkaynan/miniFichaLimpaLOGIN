package login;

import java.io.IOException;
import java.lang.ProcessBuilder.Redirect;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import percistense.UsuarioDao;


@WebServlet("/login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
  
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
		dispatcher.forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String)request.getParameter("user");
		String pass = (String)request.getParameter("pass");
		
		UsuarioDao userDao = new UsuarioDao();
		boolean validar = userDao.validar(nome, pass);
		
		if(validar) {
			request.getSession().setAttribute("username", nome);
			response.sendRedirect("home.jsp");
		}else {
			response.sendRedirect("login");
		}
	}

}
