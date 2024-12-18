package controller;
import model.dao.ProdottoDAO;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name="EliminaProdottoCatalogo", value="/EliminaProdottoCatalogo")
public class EliminaProdottoCatalogo extends HttpServlet {
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String idProdotto = request.getParameter("idProdotto");

		ProdottoDAO prodottoDao = new ProdottoDAO();
		try {
			prodottoDao.eliminaProdotto(idProdotto);
			response.sendRedirect(request.getContextPath() + "/pages/cucina.jsp");
		} catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante l'eliminazione del prodotto.");
			response.sendRedirect(request.getContextPath() + "/pages/cucina.jsp");
		}
	}
}

