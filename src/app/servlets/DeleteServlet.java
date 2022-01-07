package app.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.controllers.CarController;
import app.controllers.UserController;

@WebServlet("/Delete")
public class DeleteServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.setContentType("text/html");
		HttpSession session = request.getSession();
		String admin = (String) session.getAttribute("admin");
		PrintWriter out = response.getWriter();

		if (admin != null) {
			String userid = request.getParameter("userId");
			String carNo = request.getParameter("carNo");
			if (carNo != null) {
				CarController.delete(carNo);
				response.sendRedirect("ViewCars");
				out.println("<script>alert('Deleted Successfully')</script>");
			}
			if (userid != null) {
				UserController.delete(Integer.parseInt(userid));
				response.sendRedirect("ViewUsers");
				out.println("<script>alert('Deleted Successfully')</script>");
			}

		} else {
			String carNo = request.getParameter("carNo");
			CarController.delete(carNo);
			out.println("<script>alert('Deleted Successfully')</script>");
			response.sendRedirect("ViewCars");
		}

	}

}
