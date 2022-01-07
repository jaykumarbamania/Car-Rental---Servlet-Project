package app.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;


import app.controllers.UserController;
import app.model.User;

@WebServlet("/Update")
public class UpdateServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		HttpSession session=req.getSession();
		String admin = (String)session.getAttribute("admin");
		if(admin==null) {
			res.sendRedirect("Login");
		}
		User user = new User();
		user.setId(Integer.parseInt(req.getParameter("id")));
		user.setName(req.getParameter("name"));
		user.setUserName(req.getParameter("username"));
		user.setEmail(req.getParameter("email"));
		user.setMobileNo(req.getParameter("mobile"));
		user.setPassword(req.getParameter("password"));
		user.setUserType(req.getParameter("user_type"));
		if(UserController.updateUser(user) == 1) {
			res.sendRedirect("ViewUsers");
			
		}else {
			res.sendRedirect("AddUser");
		}
	}

}
