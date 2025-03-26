package control;

import java.io.IOException;
import java.net.URLEncoder;
import java.sql.Connection;
import java.sql.SQLException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cliente;
import model.bean.ConnessioneDb;
import model.dao.ClienteDAO;

@WebServlet(name = "Login", value = "/Login")
public class Login extends HttpServlet {
	private ConnessioneDb connectionDb;
	private Connection connection;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		
		this.connectionDb = new ConnessioneDb();
		connection = connectionDb.getCon();

		try {

			ClienteDAO clienteDAO = new ClienteDAO();
			Cliente cliente = clienteDAO.login(username, password);

			if (cliente != null) {
				HttpSession session = request.getSession();
				session.setAttribute("cliente", cliente);
				session.setAttribute("utenteLoggato", true);

				System.out.println("Utente loggato: " + cliente.getNome() + " " + cliente.getCognome() + " (Username: "
						+ cliente.getUsername() + ")");
				System.out.println(cliente.getRuolo());	
				response.sendRedirect(request.getContextPath() + "/pages/index.jsp");
			} else {
				Cookie errorCookie = new Cookie("errorMessage", URLEncoder.encode("Username o password errati.", "UTF-8"));
				errorCookie.setMaxAge(5); 
				response.addCookie(errorCookie);
				response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
				
				
			}
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
