package control;

import java.io.IOException;
import java.sql.SQLException;
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
import model.bean.Prodotto;
import model.dao.ProdottoDAO;


@WebServlet(name = "AggiungiCarrello", value = "/AggiungiCarrello")
public class AggiungiCarrello extends HttpServlet {

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession session = request.getSession();
		Cliente cliente = (Cliente) session.getAttribute("cliente");
		String idProdotto = request.getParameter("idProdotto");
		String quantitaString = request.getParameter("quantita");
		String quantitaDisponibileStr = request.getParameter("quantitaDisponibile");
	
		int quantitaDisponibile = Integer.parseInt(quantitaDisponibileStr);
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
		
		session.setAttribute("toastMessage", "Articolo aggiunto al carrello!");
		response.sendRedirect(request.getContextPath() + "/pages/cucina.jsp");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
