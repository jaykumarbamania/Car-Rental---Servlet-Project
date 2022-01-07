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

import app.controllers.CarController;
import app.model.RentCar;

@WebServlet("/ViewRentedCars")
public class ViewRentedCars extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String rentailer = (String)session.getAttribute("rentailer");
		if(rentailer==null) {
			response.sendRedirect("Login");
		}
		
		List<RentCar> rentCars = new ArrayList<RentCar>() ;
		try {
			rentCars = CarController.getAllRentedCars();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		request.getRequestDispatcher("rentailer/viewrentcar.html").include(request, response);
		out.println("<div id='container'>");
        out.println("<h2>Rented Cars List</h2>");
        out.println("<div id=\"tableBorder\">");
		out.println("<table border='1'>");
		out.println("<tr ><th>Car No</th><th>User Id</th><th>Username</th><th>User Movbile</th><th>Rent Date</th><th>Return Status</th><th>Return Date</th></tr>");
		rentCars.stream().forEach(rc -> {
			out.println("<tr >");
			out.println("<td>"+rc.getCarNo()+"</td>");
			out.println("<td>"+rc.getUserid()+"</td>");
			out.println("<td>"+rc.getUsername()+"</td>");
			out.println("<td>"+rc.getUser_mobile()+"</td>");
			out.println("<td>"+rc.getIssueddate()+"</td>");
			out.println("<td>"+rc.getReturnstatus()+"</td>");
			if(rc.getReturnstatus().equals("yes")) {
				out.println("<td>"+rc.getReturnDate()+"</td>");
			}
			out.println("</tr>");
		});
		out.println("</table>");
		out.println("</div>");
		out.println("</div>");
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}


}
