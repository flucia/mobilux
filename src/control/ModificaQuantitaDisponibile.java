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

@WebServlet(name = "ModificaQuantitaDisponibile", value = "/ModificaQuantitaDisponibile")

public class ModificaQuantitaDisponibile extends HttpServlet{
	
		 protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	        String idProdotto = request.getParameter("idProdotto");
	        int quantitaAggiunta = Integer.parseInt(request.getParameter("quantitaDisponibile"));
	        
	        ProdottoDAO prodottoDao = new ProdottoDAO();
	        int quantitaEsistente = 0;
			try {
				quantitaEsistente = prodottoDao.getQuantitaDisponibile(idProdotto);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
	        int nuovaQuantita = quantitaEsistente + quantitaAggiunta;
	        
	        try {
				prodottoDao.updateQuantitaDisponibile(idProdotto, nuovaQuantita);
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

	        // Redirige alla pagina del prodotto o a una pagina di successo
	        response.sendRedirect(request.getContextPath() + "/pages/cucina.jsp");
	    }	

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}