package controller;

import model.Cliente;
import model.QueryInsert;
import model.QuerySelectAll;
import model.ConnessioneDb;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

@WebServlet(name = "Registrazione", value = "/Registrazione")
public class Registrazione extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    
    	String cf = request.getParameter("cf");
        String nome = request.getParameter("nome");
        String cognome = request.getParameter("cognome");
        String email = request.getParameter("email");
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String ruolo = "utente";
        String indirizzo = request.getParameter("indirizzo");
        String cellulare = request.getParameter("cellulare");
        
        
        Cliente cliente = new Cliente(cf, nome, cognome, username, email, password, ruolo, indirizzo, cellulare);
        ConnessioneDb conn = new ConnessioneDb();
        Connection c = conn.connessione();
        QuerySelectAll querySelectAll = new QuerySelectAll(c);
        boolean flag = true;
        try {
            List<Cliente> clienti = querySelectAll.selectAllCliente();
            for (Cliente cliente1 : clienti) {
                if(cliente1.getUsername().equals(username)) {
                    flag = false;
                    break;
                }
            }
            
            if (flag) {
                QueryInsert qi = new QueryInsert(c);
                qi.insertCliente(cliente);
                // Reindirizza alla pagina di risultati della registrazione
                response.sendRedirect(request.getContextPath() + "/pages/registrazioneResult.jsp");
                return; 
            } else {
                
                request.setAttribute("errorMessage", "Username già esistente. Scegli un altro username.");
                
                RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
                view.forward(request, response);
                return;
            }
        } catch (SQLException throwables) {
        	throwables.printStackTrace();
            request.setAttribute("errorMessage", "Errore durante l'inserimento dei dati. Riprova.");
            RequestDispatcher view = request.getRequestDispatcher("/index.jsp");
            view.forward(request, response);
            return;
        }

        
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }
}
