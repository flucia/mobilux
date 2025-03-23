package control;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import model.bean.Prodotto;
import model.dao.ProdottoDAO;

@WebServlet(name="ModificaProdotto", value="/ModificaProdotto")
public class ModificaProdotto extends HttpServlet  {
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		String idProdotto = request.getParameter("idProdotto");
		System.out.println(idProdotto);
		String nome = request.getParameter("nome");
		System.out.println(nome);
		String descrizione = request.getParameter("descrizione");
		System.out.println(descrizione);
		String prezzoString = request.getParameter("prezzo");
		System.out.println(prezzoString);
		Double prezzo = Double.parseDouble(prezzoString);
		System.out.println(prezzo);

		ProdottoDAO prodottoDao = new ProdottoDAO();
		Prodotto prodottoAggiornato = new Prodotto(idProdotto, nome, prezzo, descrizione);
		prodottoAggiornato.setNome(nome);
		prodottoAggiornato.setPrezzo(prezzo);
		prodottoAggiornato.setDescrizione(descrizione);
		
	    try {
			prodottoDao.modificaProdotto(prodottoAggiornato);
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
		response.sendRedirect(request.getContextPath() + "/pages/cucina.jsp");
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
