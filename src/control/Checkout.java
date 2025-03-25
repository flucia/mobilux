package control;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
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
import model.bean.Ordine;
import model.bean.Prodotto;
import model.dao.OrdineDAO;
import model.dao.ProdottoDAO;

@WebServlet(name="Checkout", value="/Checkout")
public class Checkout extends HttpServlet {
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
		Double prezzoTotale = (Double) request.getSession().getAttribute("totaleCarrello"); 
		ArrayList<Carrello> carrelli = (ArrayList<Carrello>) request.getSession().getAttribute("carrello");
		OrdineDAO ordineDAO = new OrdineDAO();
		ProdottoDAO prodottoDao = new ProdottoDAO();
		Ordine ordine = new Ordine();
		String stringData = request.getParameter("dataOrdine");

		LocalDate dataOrdine = null;
		int idOrdine = 0;

		dataOrdine = LocalDate.now();

		if(cliente != null && carrelli != null && !carrelli.isEmpty()) {
			
			ordine = new Ordine(cliente.getCodiceFiscale(),dataOrdine,prezzoTotale);

			try {
				idOrdine = ordineDAO.insertOrdine(ordine);
				System.out.println(ordine);
				
				if(idOrdine > 0) {
					for (Carrello item : carrelli) {
					    String idProdotto = item.getIdProdotto();
					    int quantitaAcquistata = item.getQuantita();
					    int quantitaDisponibile = prodottoDao.getQuantitaDisponibile(idProdotto);

					    if (quantitaAcquistata > quantitaDisponibile) {
					        request.setAttribute("errorMessage", "Quantità insufficiente per il prodotto: ");
					        request.getRequestDispatcher("/pages/carrello.jsp").forward(request, response);
					        return;
					    }

					    prodottoDao.riduciQuantitaDisponibile(idProdotto, quantitaAcquistata);
					    
					 
					    boolean prodottoEsistente = ordineDAO.prodottoEsistente(idOrdine, idProdotto);
					    if (prodottoEsistente) {
					       
					        ordineDAO.aggiornaQuantitaProdotto(idOrdine, idProdotto, quantitaAcquistata);
					    } else {
					    	double prezzoProdotto = prodottoDao.getPrezzoProdotto(idProdotto);
					        ordineDAO.insertDettaglioOrdine(idOrdine, idProdotto, quantitaAcquistata, prezzoProdotto,prezzoTotale);
					    }
					}
					
					request.getSession().removeAttribute("totaleCarrello");
					request.getSession().removeAttribute("carrello");
					response.sendRedirect(request.getContextPath() + "/pages/riepilogoOrdini.jsp");
				} else {
					request.setAttribute("errorMessage", "Non posso completare l'ordine");
					response.sendRedirect(request.getContextPath() + "/pages/carrello.jsp");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}

	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
