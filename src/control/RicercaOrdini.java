package control;

import java.io.IOException;
import java.sql.Connection;
import java.time.LocalDate;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ConnessioneDb;
import model.bean.OrdineCliente;
import model.dao.OrdineDAO;

@WebServlet("/pages/RicercaOrdini")
public class RicercaOrdini extends HttpServlet {
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String query = request.getParameter("query");
        response.setContentType("application/json");
        response.setCharacterEncoding("UTF-8");

        
        ConnessioneDb conn = new ConnessioneDb();
        Connection c = conn.getCon();

        OrdineDAO ordineDAO = new OrdineDAO();

        
        ArrayList<OrdineCliente> ordini = ordineDAO.cercaOrdini(query);


        StringBuilder jsonResponse = new StringBuilder();
        jsonResponse.append("[");

        for (int i = 0; i < ordini.size(); i++) {
            OrdineCliente ordine = ordini.get(i);
            jsonResponse.append("{")
                        .append("\"idOrdine\":\"").append(ordine.getIdOrdine()).append("\",")
                        .append("\"nomeCliente\":\"").append(ordine.getCliente().getNome()).append("\",")
                        .append("\"cognomeCliente\":\"").append(ordine.getCliente().getCognome()).append("\",")
                        .append("\"dataOrdine\":\"").append(ordine.getDataOrdine().toString()).append("\",")
                        .append("\"prezzoTotale\":").append(ordine.getPrezzoTotale())
                        .append("}");

            if (i < ordini.size() - 1) {
                jsonResponse.append(",");
            }
        }

        jsonResponse.append("]");

      
        response.getWriter().write(jsonResponse.toString());
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
