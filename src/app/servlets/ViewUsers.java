package app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.controllers.UserController;
import app.model.User;

@WebServlet("/ViewUsers")
public class ViewUsers extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String admin = (String)session.getAttribute("admin");
		if(admin==null) {
			response.sendRedirect("Login");
		}
		
		List<User> users = new ArrayList<User>() ;
		try {
			users = UserController.getAllUsers();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("adminpanel/viewuser.html").include(request, response);
		out.println("<div id='container'>");
        out.println("<h2>User List</h2>");
        out.println("<div id='tableBorder'>");
		out.println("<table border='1'>");
		out.println("<tr ><th>Id</th><th>Username</th><th>Fullname</th><th>Email</th><th>Mobile</th><th>UserType</th><th>CreatedOn</th><th>Edit</th><th>Delete</th></tr>");
		users.stream().forEach(user -> {
			out.println("<tr >");
			out.println("<td>"+user.getId()+"</td>");
			out.println("<td>"+user.getUserName()+"</td>");
			out.println("<td>"+user.getName()+"</td>");
			out.println("<td>"+user.getEmail()+"</td>");
			out.println("<td>"+user.getMobileNo()+"</td>");
			out.println("<td>"+user.getUserType()+"</td>");
			out.println("<td>"+user.getCreatedAt()+"</td>");
			out.println("<td><a href='Edit?userId="+user.getId()+"'>Edit</a></td>");
			out.println("<td><a href='Delete?userId="+user.getId()+"'>Delete</a></td>");
			out.println("</tr>");
		});
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
