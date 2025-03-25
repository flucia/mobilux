<%@ page import="java.util.ArrayList"%>
<%@ page import="model.dao.OrdineDAO"%>
<%@ page import="model.dao.ClienteDAO"%>
<%@ page import="model.dao.DettaglioOrdineDAO"%>
<%@ page import="model.bean.Ordine"%>
<%@ page import="model.bean.Cliente"%>
<%@ page import="model.bean.DettaglioOrdine"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="it">
<head>
    <%@ include file="/partials/head.jsp"%>
    <script src="https://code.jquery.com/jquery-3.6.0.min.js"></script>
    <title>Riepilogo ordini</title>
    <style>
        .hidden {
            display: none;
        }
    </style>
</head>
<body>

    <%@ include file="/partials/header.jsp"%>
    
    <%
        Cliente user = (Cliente) request.getSession().getAttribute("cliente");
        OrdineDAO ordineDao = new OrdineDAO();
        ClienteDAO clienteDao = new ClienteDAO();
        DettaglioOrdineDAO dettaglioOrdineDAO = new DettaglioOrdineDAO();
        
        String idUtente = user != null ? user.getCodiceFiscale() : null;
        ArrayList<Ordine> listaOrdini = null;

        if (user != null) {
            if ("admin".equals(user.getRuolo())) {
               
                listaOrdini = ordineDao.selectAll();
            } else {
              
                listaOrdini = ordineDao.selectById(idUtente);
            }
        }
    %>

    <main>
        <div class="order" id="listaOrdini">
            <%
            if ("admin".equals(user.getRuolo())) {
            %>
            <form onsubmit="cercaOrdini(event)">
                <input class="input-search mobile-width" type="text" id="inputCerca"
                    placeholder="cerca">
                <button class="button-order" type="submit">Cerca</button>
            </form>
            <div id="risultatoRicerca"></div>
            <%
            }
            %>

            <%
            if (listaOrdini != null && !listaOrdini.isEmpty()) {
                if ("admin".equals(user.getRuolo())) {
            %>
            <table id="tuttiOrdiniTable">
                <thead>
                    <tr>
                        <th>Nome Cliente</th>
                        <th>Cognome Cliente</th>
                        <th>Data Ordine</th>
                        <th>Prodotti acquistati</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                   
                    for (Ordine o : listaOrdini) {
                        Cliente cliente1 = clienteDao.selectById(o.getIdUtente());
                        ArrayList<DettaglioOrdine> dettagliOrdine = dettaglioOrdineDAO.selectDettagliOrdineById(o.getIdOrdine());
                    %>
                    <tr>
                        <td><%=cliente1.getNome()%></td>
                        <td><%=cliente1.getCognome()%></td>
                        <td><%=o.getDataOrdine()%></td>
                        <td>
                            <%
                        
                            for (DettaglioOrdine dettaglio : dettagliOrdine) {
                                out.println("Prodotto: " + dettaglio.getNome() + "<br>");
                                out.println("Descrizione: " + dettaglio.getDescrizione() + "<br>");
                                out.println("Quantità: " + dettaglio.getQuantita() + "<br>");
                                out.println("Prezzo: " + dettaglio.getPrezzoProdotto() + "<br>");
                                out.println("Prezzo totale: " + dettaglio.getPrezzoTotale() + "<br>");
                            }
                            %>
                        </td>
                    </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
            <%
            } else {
            %>
            <table id="tuttiOrdiniTable">
                <thead>
                    <tr>
                        <th>Data Ordine</th>
                        <th>Prodotti acquistati</th>
                    </tr>
                </thead>
                <tbody>
                    <%
                   
                    for (Ordine o : listaOrdini) {
                        Cliente cliente1 = clienteDao.selectById(o.getIdUtente());
                        ArrayList<DettaglioOrdine> dettagliOrdine = dettaglioOrdineDAO.selectDettagliOrdineById(o.getIdOrdine());
                    %>
                    <tr>
                        <td><%=o.getDataOrdine()%></td>
                        <td>
                            <%
                            // Mostra i dettagli degli ordini
                            for (DettaglioOrdine dettaglio : dettagliOrdine) {
                                out.println("Prodotto: " + dettaglio.getNome() + "<br>");
                                out.println("Descrizione: " + dettaglio.getDescrizione() + "<br>");
                                out.println("Quantità: " + dettaglio.getQuantita() + "<br>");
                                out.println("Prezzo: " + dettaglio.getPrezzoProdotto() + "<br>");
                                out.println("Prezzo totale: " + dettaglio.getPrezzoTotale() + "<br>");
                            }
                            %>
                        </td>
                    </tr>
                    <%
                    }
                    %>
                </tbody>
            </table>
            <%
            }
            }
            %>
        </div>
    </main>
</body>
</html>
