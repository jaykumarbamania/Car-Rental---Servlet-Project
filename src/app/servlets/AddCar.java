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
import app.model.Cars;


@WebServlet("/AddCar")
public class AddCar extends HttpServlet {
	private static final long serialVersionUID = 1L;
	
	@Override
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String admin = (String)session.getAttribute("admin");
		String rentailer = (String)session.getAttribute("rentailer");
		if(rentailer!=null) {
			request.getRequestDispatcher("rentailer/addCarForm.html").include(request, response);
		}
		else if(admin!=null) {
			request.getRequestDispatcher("adminpanel/addCarForm.html").include(request, response);
		}
		else {
			response.sendRedirect("Login");
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
		}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String rentailer = (String)session.getAttribute("rentailer");
		String carno=request.getParameter("carno");
		String carname=request.getParameter("carname");
		String carmaker=request.getParameter("carmaker");
		String carmodel=request.getParameter("carmodel");
		String carcolor=request.getParameter("carcolor");
		int availableCars = Integer.parseInt(request.getParameter("availability"));
		Cars car = new Cars(0, carno, carname, carmaker, carmodel, carcolor, availableCars);
		try {
			if(CarController.addCar(car) >0 ) {
				response.sendRedirect("ViewCars");
				out.println("<script>alert('Car Registered Successfully')</script>");
			}else {
				if(rentailer!=null) {
					request.getRequestDispatcher("rentailer/addCarForm.html").include(request, response);
				}
				else {
					request.getRequestDispatcher("adminpanel/addCarForm.html").include(request, response);
				}
				out.println("<script>alert('Problem while Registering the car')</script>");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}


}
