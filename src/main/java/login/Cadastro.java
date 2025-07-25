package login;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Usuario;
import percistense.UsuarioDao;


@WebServlet("/cadastro")
public class Cadastro extends HttpServlet {
	private static final long serialVersionUID = 1L;
   
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("cadastro-usuario.jsp").forward(request, response);
	}

	
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String nome = (String)request.getParameter("user");
		String pass = (String)request.getParameter("pass");
		
		UsuarioDao usuarioDao = new UsuarioDao();
		Usuario usuario = new Usuario();
		usuario.setNome(nome);
		usuario.setPass(pass);
		usuarioDao.save(usuario);
		
		response.sendRedirect("login");
	}

}
