package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Carrello;
import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.dao.CarrelloDAO;

@WebServlet(name = "ModificaQuantitaCarrello",value="/ModificaQuantitaCarrello") 
public class ModificaQuantitaCarrello  extends HttpServlet{
	protected void doGet(HttpServletRequest request,HttpServletResponse response) throws ServletException, IOException {
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");	
		String idProdotto = request.getParameter("idProdotto");
		String quantitaString = request.getParameter("quantita");
		int quantita= Integer.parseInt(quantitaString);
		Carrello carrello = new Carrello();
		carrello.setQuantita(quantita);
		carrello.setIdUtente(cliente.getCodiceFiscale());
		carrello.setIdProdotto(idProdotto);
		
		CarrelloDAO carrelloDAO = new CarrelloDAO();

		try {
			carrelloDAO.updateQuantitaCarrello(carrello);
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
