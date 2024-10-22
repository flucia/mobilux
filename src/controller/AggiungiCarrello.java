package controller;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.dao.CarrelloDAO;
import model.dao.ClienteDAO;
import model.bean.Carrello;
import model.bean.Cliente;


@WebServlet(name = "AggiungiCarrello", value = "/AggiungiCarrello")
public class AggiungiCarrello  extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		String idProdotto = request.getParameter("idProdotto");
		String sessionId = request.getSession().getId();
		System.out.println(sessionId);


		String quantitaString = request.getParameter("quantita");
		int quantita= Integer.parseInt(quantitaString);
		CarrelloDAO carrelloDAO = new CarrelloDAO();
		


		if (cliente == null) {
			Cookie[] cookies = request.getCookies();
			boolean hasTemporaryUser = false;

			if (cookies != null) {
				for (Cookie cookie : cookies) {
					if ("temporaryUserId".equals(cookie.getName())) {
						sessionId = cookie.getValue();
						hasTemporaryUser = true;
						break;
					}
				}
			}
			if (!hasTemporaryUser) {
				ClienteDAO clienteDAO = new ClienteDAO();
				try {
					clienteDAO.insertClienteTemporaneo(sessionId);
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

				Cookie cookie = new Cookie("temporaryUserId", sessionId);
				cookie.setMaxAge(60 * 60 * 24 * 7); 
				response.addCookie(cookie);
			}


			cliente = new Cliente();
			cliente.setCodiceFiscale(sessionId); 
		}

		try {
			if(carrelloDAO.productExists(idProdotto,cliente.getCodiceFiscale())) {
				Carrello exists = carrelloDAO.selectById(cliente.getCodiceFiscale(), idProdotto);
				System.out.println(exists);
				int nuovaQuantita = exists.getQuantita() + quantita;
				exists.setQuantita(nuovaQuantita);
				carrelloDAO.updateQuantitaCarrello(exists);

			} else {
				Carrello carrello = new Carrello();
				carrello.setIdProdotto(idProdotto);
				carrello.setQuantita(quantita);
				carrello.setIdUtente(cliente.getCodiceFiscale());
				carrelloDAO.insertCarrello(carrello);
			}
			response.sendRedirect(request.getContextPath() + "/pages/carrello.jsp");
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			request.setAttribute("errorMessage", "Errore durante l'inserimento dei dati. Riprova.");
			RequestDispatcher view = request.getRequestDispatcher("/pages/index.jsp");
			view.forward(request, response);
		}
		System.out.println("idProdotto: " + idProdotto);
		System.out.println("Codice Fiscale Cliente: " + cliente.getCodiceFiscale());

	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		doGet(request,response);
	}
}
