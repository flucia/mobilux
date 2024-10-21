package controller;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import model.bean.Cliente;

import java.io.IOException;

@WebServlet(name = "Logout", value = "/Logout")
public class Logout extends HttpServlet{

	    @Override
	    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
	       Cliente cliente = (Cliente) request.getSession().getAttribute("cliente");
	       
	        if (cliente == null) {
	            response.sendRedirect(request.getContextPath() + "/login.jsp");
	            return;
	        }
	        request.getSession().invalidate();
	        response.sendRedirect(request.getContextPath() + "/pages/login.jsp");
	        
	       
	    }

	}

