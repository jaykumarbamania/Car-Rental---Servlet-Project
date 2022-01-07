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


@WebServlet("/UserRegisteration")
public class UserRegisteration extends HttpServlet {
	private static final long serialVersionUID = 1L;

	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session=request.getSession();
		String admin = (String)session.getAttribute("admin");
		if(admin == null){
			request.getRequestDispatcher("register.html").include(request, response);
		}else {
			request.getRequestDispatcher("adminpanel/addUser.html").include(request, response);
		}
	}
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		
		String name=request.getParameter("name");
		String username=request.getParameter("username");
		String email=request.getParameter("email");
		String mobile=request.getParameter("mobile");
		String password=request.getParameter("password");
		String user_type = "";
		HttpSession session=request.getSession();
		String admin = (String)session.getAttribute("admin");
		if(admin != null){
			request.getRequestDispatcher("adminpanel/adminnav.html").include(request, response);
			user_type =request.getParameter("user_type");
		}else {
			request.getRequestDispatcher("mainnav.html").include(request, response);
			user_type = "normal";
		}
		
		User user = new User(name, username, email, mobile, password);
		int result=0;
		try {
			result = UserController.addUser(user,user_type);
		} catch (Exception e) {
			e.printStackTrace();
		}
		out.println("<div id='container'>");
		if(result ==1) {
			
			out.println("<div style='color:rgb(3, 201, 3);  background-color: rgb(248, 248, 248);border-radius: 5px;padding: 10px;margin: 10px;height: 15px;\r\n"
					+ "  '> Registered successfully</div>");
			if(admin != null) {
				response.sendRedirect("ViewUsers");
			}else {
				request.getRequestDispatcher("loginform.html").include(request, response);
			}
		}else {
			out.println("<div style='color:red;  background-color: rgb(248, 248, 248);border-radius: 5px;padding: 10px;margin: 10px;height: 15px;\r\n"
					+ "  '> Registeration Not successfull</div>");
			if(admin != null) {
				request.getRequestDispatcher("adminpanel/addUserForm.html").include(request, response);
			}else {
				request.getRequestDispatcher("loginform.html").include(request, response);
			}
		}
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
