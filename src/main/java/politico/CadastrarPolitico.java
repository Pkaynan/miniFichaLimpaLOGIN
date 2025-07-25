package politico;

import java.io.IOException;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.Politico;
import percistense.PoliticoDAO;

@WebServlet("/cadastropolitico")
public class CadastrarPolitico extends HttpServlet {
    private static final long serialVersionUID = 1L;

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher rd = request.getRequestDispatcher("cadastro-politico.jsp");
        rd.forward(request, response);
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String nome = request.getParameter("nome");
        String nomeEleitoral = request.getParameter("nomeEleitoral");
        String numeroEleitoralStr = request.getParameter("numeroEleitoral");
        String partidoIdStr = request.getParameter("partidoId");

        try {
            int numeroEleitoral = Integer.parseInt(numeroEleitoralStr);
            Long partidoId = Long.parseLong(partidoIdStr);

            Politico politico = new Politico();
            politico.setNome(nome);
            politico.setNomeEleitoral(nomeEleitoral);
            politico.setNumeroEleitoral(numeroEleitoral);
            politico.setPartido(partidoId);

            PoliticoDAO dao = new PoliticoDAO();
            dao.save(politico);

            response.sendRedirect(request.getContextPath() + "/politico");
        } catch (NumberFormatException e) {
            response.sendError(HttpServletResponse.SC_BAD_REQUEST, "Erro ao converter número.");
        }
    }
}
