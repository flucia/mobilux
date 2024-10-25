package controller;

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
		Ordine ordine = new Ordine();
		String stringData = request.getParameter("dataOrdine");

		LocalDate dataOrdine = null;
		boolean ordineInserito = false;

		dataOrdine = LocalDate.now();

		if(cliente != null && carrelli != null && !carrelli.isEmpty()) {

			ordine = new Ordine(cliente.getCodiceFiscale(),dataOrdine,prezzoTotale);

			try {
				ordineInserito = ordineDAO.insertOrdine(ordine);
				if(ordineInserito) {
					request.getSession().removeAttribute("totaleCrrello");
					request.getSession().removeAttribute("carrello");
					response.sendRedirect(request.getContextPath() + "/pages/riepilogoOrdini.jsp");
				} else {
					request.setAttribute("errorMessage", "Non posso completare l'ordine");
					request.getRequestDispatcher("/pages/carrello.jsp").forward(request, response);
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
