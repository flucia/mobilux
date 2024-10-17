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

import model.bean.Carrello;
import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.dao.CarrelloDAO;

@WebServlet(name = "ProdottiCarrello", value = "/ProdottiCarrello")
public class ProdottiCarrello extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();
		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		try {

			CarrelloDAO carrelloDAO = new CarrelloDAO();

			ArrayList<Carrello> carrelli = carrelloDAO.selectAllCarrello(cliente.getCodiceFiscale());

			request.setAttribute("carrelloPerId",carrelli);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/carrello.jsp");
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

