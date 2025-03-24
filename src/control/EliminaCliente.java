package control;
import model.dao.ClienteDAO;
import model.dao.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="EliminaCliente", value="/EliminaCliente")
public class EliminaCliente extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idCliente = request.getParameter("cf");
		
		ClienteDAO clienteDao = new ClienteDAO();
		ProdottoDAO prodottoDao = new ProdottoDAO();
		try {
			clienteDao.deleteCliente(idCliente);
			prodottoDao.eliminaProdottoByIdCliente(idCliente);
			
			response.sendRedirect(request.getContextPath() + "/pages/visualizzaClienti.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante l'eliminazione del cliente.");
			response.sendRedirect(request.getContextPath() + "/pages/visualizzaClienti.jsp");
		}
	}
}

