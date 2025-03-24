package control;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import model.bean.Carrello;
@WebServlet(name="SvuotaCarrello", value="/SvuotaCarrello")
public class SvuotaCarrello extends HttpServlet {

		protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			System.out.println("sono qui");
			ArrayList<Carrello> carrello = (ArrayList<Carrello>) request.getSession().getAttribute("carrello");
			 if (carrello == null) {
		            
		            request.getSession().invalidate();
		        } else if (carrello.isEmpty()) {
		           
		        } else {
		          
		            carrello.clear();
		        }

			response.sendRedirect(request.getContextPath() + "/pages/carrello.jsp");
		}

		@Override
		protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}
	}

