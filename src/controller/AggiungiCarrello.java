package controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CarrelloDAO;
import model.bean.Carrello;
import model.bean.Cliente;


@WebServlet(name = "AggiungiCarrello", value = "/AggiungiCarrello")
public class AggiungiCarrello  extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		String idProdotto = request.getParameter("idProdotto");

		String quantitaString = request.getParameter("quantita");
		int quantita= Integer.parseInt(quantitaString);
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		Carrello carrello = new Carrello();
		carrello.setIdProdotto(idProdotto);
		carrello.setQuantita(quantita);
		carrello.setIdUtente(cliente.getCodiceFiscale());
		try {
			carrelloDAO.insertCarrello(carrello);
			response.sendRedirect(request.getContextPath() + "/pages/carrello.jsp"); 

		}catch (SQLException e) {
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante l'inserimento dei dati. Riprova.");
			RequestDispatcher view = request.getRequestDispatcher("pages/index.jsp");
			view.forward(request, response);
			return;
		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
	}
}
