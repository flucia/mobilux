package controller;
import model.dao.ClienteDAO;

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
		String idCliente = request.getParameter("idCliente");

		ClienteDAO clienteDao = new ClienteDAO();
		try {
			clienteDao.deleteCliente(idCliente);
			// Redirect alla pagina clienti o aggiorna la listaresponse.sendRedirect(request.getContextPath() + "/pages/visualizzaClienti.jsp");
			response.sendRedirect(request.getContextPath() + "/pages/visualizzaClienti.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante l'eliminazione del cliente.");
			request.getRequestDispatcher("/pages/visualizzaClienti.jsp").forward(request, response);
		}
	}
}

