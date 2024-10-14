package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.ConnessioneDb;
import model.Cliente;
import model.SelectMySql;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*Inserimento username e password*/
		String username = request.getParameter("username");
		String password = request.getParameter("password");

		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.connessione();
		Cliente cliente = null;
		String query = "SELECT * FROM Cliente WHERE username = '"+username+"' AND password = '"+password+"' ";
		SelectMySql select = new SelectMySql(c);
		ResultSet rs = select.select(query);

		String cf ="";
		String nome = "";
		String cognome = "";
		String email = "";
		String user = "";
		String pass = "";
		String ruolo = "";
		String indirizzo = "";
		String cellulare = "";

		try {
			if (rs.next()){
				cf = rs.getString(1);
				nome = rs.getString(2);
				cognome = rs.getString(3);
				user = rs.getString(4);
				email = rs.getString(5);
				pass = rs.getString(6);
				ruolo = rs.getString(7);
				indirizzo = rs.getString(8);
				cellulare = rs.getString(9);


				cliente = new Cliente(cf, nome, cognome, email, user, pass, ruolo, indirizzo, cellulare);
				
				HttpSession session = request.getSession();
				session.setAttribute("cliente", cliente);
				
				//info cliente loggato
				 System.out.println("Utente loggato: " + cliente.getNome() + " " + cliente.getCognome() + " (Username: " + cliente.getUsername() + ")");

				response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
			} else {
				request.setAttribute("errorMessage", "Username o password errati.");
				request.getRequestDispatcher("/pages/login.jsp").forward(request, response);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}

