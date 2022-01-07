package app.servlets;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.controllers.CarController;
import app.model.Cars;

@WebServlet("/ViewCars")
public class ViewCars extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String admin = (String)session.getAttribute("admin");
		String rentailer = (String)session.getAttribute("rentailer");
		
		List<Cars> cars = new ArrayList<Cars>() ;
		try {
			cars = CarController.getAllCars();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		if(admin!=null) {
			request.getRequestDispatcher("adminpanel/viewcar.html").include(request, response);
		}
		else if(rentailer != null) {
			request.getRequestDispatcher("rentailer/viewcar.html").include(request, response);
		}
		else {
			request.getRequestDispatcher("normal/viewcar.html").include(request, response);
		}
		
		out.println("<div id='container'>");
        out.println("<h2>Car List</h2>");
        out.println("<div id=\"tableBorder\">");
		out.println("<table border='1'>");
		out.print("<tr ><th>Car No</th><th>Car Name</th><th>Car Maker</th><th>Car Model</th><th>Car Color</th><th>Availability</th>");
		if(admin != null || rentailer != null ) {
			out.print("<th>Edit</th>");
			out.print("<th>Delete</th>");
		}
		out.println("</tr>");
		Comparator<Cars> comparebyId = Comparator.comparing(Cars::getCarId).reversed();
	List<Cars> reversebyId = cars.stream().sorted(comparebyId).collect(Collectors.toList());
		reversebyId.stream().forEach(car -> {
			out.println("<tr >");
			out.println("<td>"+car.getCarNo()+"</td>");
			out.println("<td>"+car.getCarName()+"</td>");
			out.println("<td>"+car.getCarMaker()+"</td>");
			out.println("<td>"+car.getCarModel()+"</td>");
			out.println("<td>"+car.getCarColor()+"</td>");
			out.println("<td>"+car.getCarAvailable()+"</td>");
			if(admin != null || rentailer != null ) {
				out.println("<td><a href='EditCar?id="+car.getCarId()+"'>Edit</a></td>");
				out.println("<td><a href='Delete?carNo="+car.getCarNo()+"'>Delete</a></td>");
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
