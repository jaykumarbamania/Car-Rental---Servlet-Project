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

@WebServlet("/ReturnCar")
public class ReturnCar extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String rentailer = (String)session.getAttribute("rentailer");
		if(rentailer==null) {
			response.sendRedirect("Login");
		}
		
		request.getRequestDispatcher("rentailer/ReturnCarForm.html").include(request, response);
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String carno=request.getParameter("carno");
		String username=request.getParameter("username");
		int status = 0;
		
		try {
			status = CarController.returnCar(carno, username);
			if(status == 1) {
				response.sendRedirect("ViewRentedCars");
				out.println("<script>alert('Returned Successfully')</script>");
			}else {
				out.println("<script>alert('Not Returned Successfully... please check Car No and username')</script>");
				response.sendRedirect("ReturnCar");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
