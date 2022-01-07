package app.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.controllers.UserController;
import app.model.User;


@WebServlet("/Login")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		request.getRequestDispatcher("login.html").include(request, response);
		
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String email=request.getParameter("loginemail");
		String password=request.getParameter("lpassword");
		User u = null;
		out.println("<!DOCTYPE html>");
		out.println("<html lang='en'>");
		out.println("<head>");
		if(email.equals("admin@gmail.com") && password.equals("admin123")) {
			
			session.setAttribute("admin","true");
			response.sendRedirect("Admin");
			
		}
		
		else if((u = UserController.userLogin(email, password))!=null) {
				if(u.getUserType().equals("normal")) {
					session.setAttribute("normal","true");
					response.sendRedirect("NormalUserPanel");
				}else {
					session.setAttribute("rentailer","true");
					response.sendRedirect("Rentailer");
				}
		}else {
			request.getRequestDispatcher("mainnav.html").include(request, response);
			out.println("<div id='container'>");
			out.println("<div style='color:red;  background-color: rgb(248, 248, 248);border-radius: 5px;padding: 10px;margin: 10px;height: 15px;'"
					+ " > Invalid Credentials</div>");
			request.getRequestDispatcher("loginform.html").include(request, response);
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.println();
	}

}
