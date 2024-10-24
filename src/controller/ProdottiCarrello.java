package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.bean.Carrello;
import model.bean.Cliente;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;

@WebServlet(name = "ProdottiCarrello", value = "/ProdottiCarrello")
public class ProdottiCarrello extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		Prodotto prodotto = new Prodotto();


		ArrayList<Carrello> carrelli = (ArrayList<Carrello>) request.getSession().getAttribute("carrello");

		if (carrelli == null) {
			carrelli = new ArrayList<>();
		}
		ProdottoDAO prodottoDAO = new ProdottoDAO();
		ArrayList<Prodotto> prodottiNelCarrello = new ArrayList<>();

		for (Carrello item : carrelli) {

			try {
				prodotto = prodottoDAO.selectAllProdottiById(item.getIdProdotto());
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			if (prodotto != null) {
				prodottiNelCarrello.add(prodotto);
			}
		}

		request.setAttribute("prodottiNelCarrello", prodottiNelCarrello);
		request.setAttribute("carrelloPerId", carrelli);

		RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/carrello.jsp");
		dispatcher.forward(request, response);
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
