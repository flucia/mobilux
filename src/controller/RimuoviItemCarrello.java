package controller;

import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Carrello;

@WebServlet(name="RimuoviItemCarrello", value="/RimuoviItemCarrello")
public class RimuoviItemCarrello extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		ArrayList<Carrello> carrello = (ArrayList<Carrello>) request.getSession().getAttribute("carrello");
		String idProdotto = request.getParameter("idProdotto");


		for (int i = 0; i < carrello.size(); i++) {
			Carrello item = carrello.get(i);
			if (String.valueOf(item.getIdProdotto()).equals(idProdotto)) {
				carrello.remove(i);
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

