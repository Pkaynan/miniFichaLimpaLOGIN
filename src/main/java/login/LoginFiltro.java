package login;

import java.io.IOException;
import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.annotation.WebFilter;
import javax.servlet.http.HttpFilter;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebFilter("/*")
public class LoginFiltro extends HttpFilter implements Filter {

	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		HttpServletRequest httpServletRequest = (HttpServletRequest) request;
		HttpServletResponse httpServletResponse = (HttpServletResponse) response;

		// detecta o final da url (/login) e permite que o usuario acesse a tela de
		// login
		if ("/login".equals(httpServletRequest.getServletPath())
				|| "/cadastro".equals(httpServletRequest.getServletPath())) {
			chain.doFilter(request, response);
			return;
		}

		HttpSession httpSession = httpServletRequest.getSession();
		String username = (String) httpSession.getAttribute("username");

		if (username != null && !username.isBlank()) {
			chain.doFilter(request, response);
		} else {
			httpServletResponse.sendRedirect("login");
		}
	}

}
