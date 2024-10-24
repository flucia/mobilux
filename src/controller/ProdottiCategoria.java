package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ConnessioneDb;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;

@WebServlet(name = "ProdottiCategoria", value = "/ProdottiCategoria")
public class ProdottiCategoria extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();
		
		try {

			ProdottoDAO prodottoDAO = new ProdottoDAO();

			ArrayList<Prodotto> prodotti = prodottoDAO.selectByIdCategoria("1");
			// Impostare i prodotti come attributo della request per la JSP
			request.setAttribute("prodottiCategoria1", prodotti);
			// Reindirizzare alla JSP che mostrerà i prodotti
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/cucina.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
