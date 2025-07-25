package carrinho;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import model.Carrinho;

public class CarrinhoViewHelper {

	public static Carrinho recuperarCarrinho (HttpServletRequest request) {
		
		HttpSession httpSession = request.getSession();
		Object refcarrinho = httpSession.getAttribute("carrinho");
		
		final Carrinho carrinho;
		
		if(refcarrinho instanceof Carrinho) {
			carrinho = (Carrinho) refcarrinho;
		}else {
			carrinho = new Carrinho();
			httpSession.setAttribute("carrinho", carrinho);
		}
		
		return carrinho;
	}
}
