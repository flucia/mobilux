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

import model.bean.ConnessioneDb;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;

@WebServlet(name = "RicercaProdotti", value = "/RicercaProdotti")
public class RicercaProdotti extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String nome = request.getParameter("searchForm");
		System.out.println(nome);
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();

		System.out.println(nome);

		try {

			ProdottoDAO prodottoDAO = new ProdottoDAO();

			ArrayList<Prodotto> prodotti = prodottoDAO.selectByName(nome);
			System.out.println(prodotti);

			request.setAttribute("prodottiNome", prodotti);
			request.setAttribute("nomeRicerca", nome);

			if (prodotti.isEmpty()) {
				request.setAttribute("nessunProdotto", true);
			}

			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/visualizzaProdotti.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

}
