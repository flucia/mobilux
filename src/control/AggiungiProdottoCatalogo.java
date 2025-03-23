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

@WebServlet("/AggiungiProdottoCatalogo")
public class AggiungiProdottoCatalogo extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
       
    	String idProdotto = request.getParameter("idprodotto");
    	String nome = request.getParameter("nome");
        double prezzo = Double.parseDouble(request.getParameter("prezzo"));
        String descrizione = request.getParameter("descrizione");
        String immagine = request.getParameter("immagine");
        String categoriaId = request.getParameter("categoria");
        int quantitaDisponibile = Integer.parseInt(request.getParameter("quantitaDisponibile"));

        Prodotto nuovoProdotto = new Prodotto(idProdotto, nome, prezzo,descrizione, immagine, categoriaId, quantitaDisponibile);

        ProdottoDAO prodottoDao = new ProdottoDAO();
        try {
			prodottoDao.insertProdotto(nuovoProdotto);
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

