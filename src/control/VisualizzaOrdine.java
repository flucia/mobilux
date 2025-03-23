package control;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.bean.Ordine;
import model.dao.OrdineDAO;

@WebServlet(name = "VisualizzaOrdine", value = "/VisualizzaOrdine")
public class VisualizzaOrdine extends HttpServlet {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		OrdineDAO ordineDAO = new OrdineDAO();
		ArrayList<Ordine> ordini = new ArrayList<>();

		if (cliente != null) {
			try {
				ordini = ordineDAO.selectById(cliente.getCodiceFiscale());
				if (ordini != null && !ordini.isEmpty()) {
					request.setAttribute("ordini", ordini);
					request.getRequestDispatcher("/pages/visualizzaOrdini.jsp").forward(request, response);
				} else {
					request.setAttribute("errorMessage", "Nessun ordine trovato per l'utente.");
					request.getRequestDispatcher("/pages/carrello.jsp").forward(request, response);

				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				request.setAttribute("errorMessage", "Si è verificato un errore durante il recupero degli ordini.");
				request.getRequestDispatcher("/pages/carrello.jsp").forward(request, response);
			}

			response.sendRedirect(request.getContextPath() + "/pages/cucina.jsp");
		} else {
			response.sendRedirect(request.getContextPath() + "/login.jsp");

		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}