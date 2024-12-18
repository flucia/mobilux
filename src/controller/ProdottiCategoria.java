package controller;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.ConnessioneDb;
import model.bean.Prodotto;
import model.dao.ProdottoDAO;

@WebServlet(name = "ProdottiCategoria", value = "/ProdottiCategoria")
public class ProdottiCategoria extends HttpServlet {

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ConnessioneDb conn = new ConnessioneDb();
		Connection c = conn.getCon();
		
		try {

			ProdottoDAO prodottoDAO = new ProdottoDAO();

			ArrayList<Prodotto> prodotti = prodottoDAO.selectByIdCategoria("1");
			
			request.setAttribute("prodottiCategoria1", prodotti);
			
			response.sendRedirect(request.getContextPath() + "/pages/cucina.jsp");
		} catch (SQLException e) {
			e.printStackTrace();

		}
	}
	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}
}
