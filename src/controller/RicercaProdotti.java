package controller;

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

import model.bean.ConnessioneDb;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;

@WebServlet(name="RicercaProdotti" ,value="/RicercaProdotti")
public class RicercaProdotti extends HttpServlet {

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String nome = request.getParameter("searchForm");
			
			ConnessioneDb conn = new ConnessioneDb();
			Connection c = conn.getCon();
			
			System.out.println(nome);
			
			try {

				ProdottoDAO prodottoDAO = new ProdottoDAO();

				ArrayList<Prodotto> prodotti = prodottoDAO.selectByName(nome);
				// Impostare i prodotti come attributo della request per la JSP
				request.setAttribute("prodottiNome", prodotti);
				// Reindirizzare alla JSP che mostrerà i prodotti
				RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/visualizzaProdotti.jsp");
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
