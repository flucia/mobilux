package control;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.dao.ClienteDAO;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

@WebServlet(name = "Registrazione", value = "/Registrazione")
public class Registrazione extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String cf = request.getParameter("cf");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String ruolo = "utente";
		String indirizzo = request.getParameter("indirizzo");
		String cellulare = request.getParameter("cellulare");


		Cliente cliente = new Cliente(cf, nome, cognome, username, email, password, ruolo, indirizzo, cellulare);
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();

		try {
			ClienteDAO clienteDAO = new ClienteDAO();
			if (!clienteDAO.usernameExists(username)) {
				clienteDAO.insertCliente(cliente);
				response.sendRedirect(request.getContextPath() + "/pages/registrazioneResult.jsp"); 
			} else {

				request.setAttribute("errorMessage", "Username già esistente. Scegli un altro username.");

				response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
				return;
			}
		} catch (SQLException throwables) {
			throwables.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante l'inserimento dei dati. Riprova.");
			response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
			return;
		}


	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
