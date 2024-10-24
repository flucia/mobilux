package controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Carrello;
import model.bean.Cliente;


@WebServlet(name = "AggiungiCarrello", value = "/AggiungiCarrello")
public class AggiungiCarrello extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		String idProdotto = request.getParameter("idProdotto");
		String quantitaString = request.getParameter("quantita");

		if (quantitaString == null) {
			request.setAttribute("errorMessage", "Quantità non valida.");
			RequestDispatcher view = request.getRequestDispatcher("/pages/index.jsp");
			view.forward(request, response);
			return;
		}

		int quantita = Integer.parseInt(quantitaString);
		ArrayList<Carrello> carrelloSessione = (ArrayList<Carrello>) session.getAttribute("carrello");

		
		if (carrelloSessione == null) {
			carrelloSessione = new ArrayList<>();
		}

		boolean prodottoEsistente = false;
		for (Carrello item : carrelloSessione) {
			if (item.getIdProdotto().equals(idProdotto)) {
				item.setQuantita(item.getQuantita() + quantita); 
				prodottoEsistente = true;
				break;
			}
		}
		
		if (!prodottoEsistente) {
			Carrello nuovoItem = new Carrello();
			nuovoItem.setIdProdotto(idProdotto);
			nuovoItem.setQuantita(quantita);
			carrelloSessione.add(nuovoItem);
		}

		session.setAttribute("carrello", carrelloSessione);

		response.sendRedirect(request.getContextPath() + "/pages/carrello.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
