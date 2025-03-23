package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Carrello;

@WebServlet(name = "ModificaQuantitaCarrello", value = "/ModificaQuantitaCarrello")
public class ModificaQuantitaCarrello extends HttpServlet {

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Carrello> carrello = (ArrayList<Carrello>) request.getSession().getAttribute("carrello");
        String idProdotto = request.getParameter("idProdotto");
        String quantitaString = request.getParameter("quantita");
        int quantita = Integer.parseInt(quantitaString);

        
        for (Carrello item : carrello) {
            if (String.valueOf(item.getIdProdotto()).equals(idProdotto)) {
                item.setQuantita(quantita);
                break;  
            }
        }
        request.getSession().setAttribute("carrello", carrello);
        response.sendRedirect(request.getContextPath() + "/pages/carrello.jsp");
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
