package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.dao.ClienteDAO;

@WebServlet(name="VisualizzaClienti", value="/visualizzaClienti")
public class VisualizzaClienti {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();
		
		try {

			ClienteDAO clienteDAO = new ClienteDAO();

			ArrayList<Cliente> clienti = clienteDAO.selectAllCliente();
			
			request.setAttribute("clienti", clienti);
			
			RequestDispatcher dispatcher = request.getRequestDispatcher("/pages/visualizzaClienti.jsp");
			dispatcher.forward(request, response);
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
