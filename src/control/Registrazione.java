package control;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Cliente;
import model.dao.ClienteDAO;

import java.io.IOException;
import java.sql.SQLException;

@WebServlet(name = "Registrazione", value = "/Registrazione")
public class Registrazione extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String cf = request.getParameter("cf");
		String nome = request.getParameter("nome");
		String cognome = request.getParameter("cognome");
		String username = request.getParameter("username");
		String email = request.getParameter("email");
		String password = request.getParameter("password");
		String ruolo = "utente";
		String indirizzo = request.getParameter("indirizzo");
		String cellulare = request.getParameter("cellulare");

		System.out.println("cf: " + cf);
		System.out.println("nome: " + nome);
		System.out.println("cognome: " + cognome);
		System.out.println("username: " + username);
		System.out.println("email: " + email);
		System.out.println("ruolo: " + ruolo);
		System.out.println("password: " + password);
		System.out.println("indirizzo: " + indirizzo);
		System.out.println("cellulare: " + cellulare);
		Cliente cliente = new Cliente(cf, nome, cognome, username, email, password, ruolo, indirizzo, cellulare);

		ClienteDAO clienteDAO = new ClienteDAO();

		try {
			if (!clienteDAO.usernameExists(username)) {
			System.out.println(cliente);
				clienteDAO.insertCliente(cliente);
				response.sendRedirect("./pages/registrazioneResult.jsp");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}