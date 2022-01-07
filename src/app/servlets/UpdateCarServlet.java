package app.servlets;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.controllers.CarController;
import app.model.Cars;

@WebServlet("/UpdateCar")
public class UpdateCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

//	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
//		HttpSession session=req.getSession();
//		String admin = (String)session.getAttribute("admin");
//		if(admin==null) {
//			res.sendRedirect("Login");
//		}
//	}
	
	protected void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		HttpSession session=req.getSession();
		String admin = (String)session.getAttribute("admin");
		String rentailer = (String)session.getAttribute("rentailer");
		if(rentailer==null || admin==null) {
			res.sendRedirect("Login");
		}
		if(admin!=null) {
			req.getRequestDispatcher("adminpanel/viewcar.html").include(req, res);
		}
		else {
			req.getRequestDispatcher("rentailer/viewcar.html").include(req, res);
		}
		Cars car = new Cars();
		car.setCarId(Integer.parseInt(req.getParameter("id")));
		car.setCarNo(req.getParameter("carno"));
		car.setCarName(req.getParameter("carname"));
		car.setCarMaker(req.getParameter("carmaker"));
		car.setCarModel(req.getParameter("carmodel"));
		car.setCarColor(req.getParameter("carcolor"));
		car.setCarAvailable(Integer.parseInt(req.getParameter("availability")));
		int status= CarController.updateCar(car);
		req.getRequestDispatcher("/AddCar").forward(req, res);
	}

}
