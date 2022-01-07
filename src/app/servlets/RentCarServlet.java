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
import app.model.RentCar;
import app.model.User;


@WebServlet("/RentCar")
public class RentCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		HttpSession session=request.getSession();
		String rentailer = (String)session.getAttribute("rentailer");
		if(rentailer==null) {
			response.sendRedirect("Login");
		}
		
		request.getRequestDispatcher("rentailer/rentcarform.html").include(request, response);
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		response.setContentType("text/html");
		PrintWriter out=response.getWriter();
		
		String carno=request.getParameter("carno");
		int userid=Integer.parseInt(request.getParameter("userid"));
		String username=request.getParameter("username");
		Long usermobile=Long.parseLong(request.getParameter("usermobile"));
		
		RentCar rc = new RentCar(carno, userid, username, usermobile);
		int status = 0;
		User u = null;
		try {
			 
			 u = UserController.getUser(userid);
			 if(u.getUserName().equals(username)) {
				 status = CarController.rentCar(rc);
			 }
		} catch (Exception e) {
			e.printStackTrace();
		}
		if( status == 0 ) {
			out.println("<script>alert('Enter Proper username, user_id and car_no')</script>");
			request.getRequestDispatcher("rentailer/rentcarform.html").include(request, response);
			
		}
//		else if(status == 2) {
//			out.println("<script>alert("+carno+"' Car is not available')</script>");
//			request.getRequestDispatcher("issuecarform.html").include(request, response);
//		}
		else if(status ==1) {
			out.println("<script>alert(' Rented Successfully')</script>");
			response.sendRedirect("ViewRentedCars");
		}
		
		request.getRequestDispatcher("footer.html").include(request, response);
		out.close();
	}

}
