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

@WebServlet("/EditCar")
public class EditCarServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		HttpSession session=req.getSession();
		String admin = (String)session.getAttribute("admin");
		String rentailer = (String)session.getAttribute("rentailer");
		if(admin==null && rentailer == null) {
			res.sendRedirect("Login");
		}
		if(admin!= null) {
			req.getRequestDispatcher("adminpanel/editcar.html").include(req, res);
		}else {
			req.getRequestDispatcher("rentailer/editcar.html").include(req, res);
		}
		
		String carId = req.getParameter("id");
		Cars car = CarController.getCar(Integer.parseInt(carId));
		out.println("<h1>Edit Car</h1>");
		out.print("<table id='tableBorder'>");   
		out.print("<form action='UpdateCar' id='signupForm' method='post'>");    
        
        out.println("<tr><input type='hidden' name='id' value='"+car.getCarId()+"'/>\r\n"
        		+ "                        <th><label for='fname' id='fnamelabel'>Car No : </label></th>\r\n"
        		+ "                        <td><input type='text' disabled name='carno' id='fname'  value='"+car.getCarNo()+"' onblur='fnameVal()' /><span id='fnameError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='lname' id='lnamelabel'>Car Name: </label></th>\r\n"
        		+ "                        <td><input type='text' name='carname' id='lname' value='"+car.getCarName()+"' onblur='lnameVal()' /><span id='lnameError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='email' id='emaillabel'>Car Maker </label></th>\r\n"
        		+ "                        <td><input type='text' name='carmaker' id='email' value='"+car.getCarMaker()+"' onblur='emailVal()' /><span id='emailError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='phoneno' id='phonelabel'>Car Model : </label></th>\r\n"
        		+ "                        <td><input type='text' name='carmodel' id='phoneno' value='"+car.getCarModel()+"' onblur='phoneVal()' /><span id='phoneError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='password' id='passlabel'>Car Color : </label></th>\r\n"
        		+ "                        <td><input type='text' name='carcolor' id='password' value='"+car.getCarColor()+"'  onblur='passVal()'/><span id='passError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='cpassword' id='cpasslabel'>Car Availibility </label></th>\r\n"
        		+ "                        <td><input type='text' name='availability' id='cpassword' value='"+car.getCarAvailable()+"' onblur='cpassVal()'/><span id='cpassError'></span></td>\r\n"
        		+ "                    </tr>\r\n"        		
        		+ "                    <tr>\r\n"
        		+ "                        <td colspan='2'><button type='submit' id='subButton' >Edit Car</button></td>\r\n"
        		+ "                    </tr>");
        
        out.println("</form>");
        out.println("</table");
        out.println("</div>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
