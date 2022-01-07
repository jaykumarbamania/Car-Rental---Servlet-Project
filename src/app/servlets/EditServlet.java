package app.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import app.controllers.UserController;
import app.model.User;

@WebServlet("/Edit")
public class EditServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	protected void doGet(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		res.setContentType("text/html");
		PrintWriter out = res.getWriter();
		HttpSession session=req.getSession();
		String admin = (String)session.getAttribute("admin");
		if(admin==null) {
			res.sendRedirect("Login");
		}
		req.getRequestDispatcher("adminpanel/edituser.html").include(req, res);
		int id = Integer.parseInt(req.getParameter("userId"));
		User user = UserController.getUser(id);
		out.println("<h1>Edit User</h1>");
		out.print("<table id='tableBorder'>");   
		out.print("<form action='Update' id='signupForm' method='post'>");    
        
        out.println("<tr><input type='hidden' name='id' value='"+user.getId()+"'/>\r\n"
        		+ "                        <th><label for='fname' id='fnamelabel'>Name : </label></th>\r\n"
        		+ "                        <td><input type='text' name='name' id='fname'  value='"+user.getName()+"' onblur='fnameVal()' /><span id='fnameError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='lname' id='lnamelabel'>Username: </label></th>\r\n"
        		+ "                        <td><input type='text' name='username' id='lname' value='"+user.getUserName()+"' onblur='lnameVal()' /><span id='lnameError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='email' id='emaillabel'>Email </label></th>\r\n"
        		+ "                        <td><input type='email' name='email' id='email' value='"+user.getEmail()+"' onblur='emailVal()' /><span id='emailError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='phoneno' id='phonelabel'>Phone No. : </label></th>\r\n"
        		+ "                        <td><input type='text' name='mobile' id='phoneno' value='"+user.getMobileNo()+"' onblur='phoneVal()' /><span id='phoneError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='password' id='passlabel'>Password : </label></th>\r\n"
        		+ "                        <td><input type='password' name='password' id='password' value='"+user.getPassword()+"'  onblur='passVal()'/><span id='passError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='cpassword' id='cpasslabel'>Confirm Password : </label></th>\r\n"
        		+ "                        <td><input type='password' name='password' id='cpassword' value='"+user.getPassword()+"' onblur='cpassVal()'/><span id='cpassError'></span></td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <th><label for='cpassword' id='select'>User Type </label></th>\r\n"
        		+ "                        <td>\r\n"
        		+ "                        	<select name='user_type' value='"+user.getUserType()+"'>\r\n"
        		+ "                        		<option value='rentailer'>Rentailer</option>\r\n"
        		+ "                        		<option value='normal'>Normal</option>\r\n"
        		+ "                        	</select>\r\n"
        		+ "                        </td>\r\n"
        		+ "                    </tr>\r\n"
        		+ "                    <tr>\r\n"
        		+ "                        <td colspan='2'><button type='submit' id='subButton' >Edit User</button></td>\r\n"
        		+ "                    </tr>");
        
        out.println("</form>");
        out.println("</table");
        out.println("</div>");
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
