package app.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import app.model.User;

public class UserController {

	static Statement st = null;

	// method to get all user
	public static List<User> getAllUsers() throws SQLException {
		Connection con = DB.getCon();

		List<User> users = new ArrayList<User>();
		PreparedStatement pst = null;
		try {
			String query = "SELECT * FROM CARRENTALDB.USERS";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getTimestamp(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pst.close();
			con.close();
		}
		return users;
	}

	// method to register user in database
	public static int addUser(User u, String user_type) throws Exception {

		Connection con = DB.getCon();
		PreparedStatement pst = null;
		String insertQuery = "INSERT INTO CARRENTALDB.USERS(user_id,user_fullname,username,user_email,"
				+ "user_mobile,user_password,user_type) VALUES(null,?,?,?,?,?,?)";
		pst = con.prepareStatement(insertQuery);
		pst.setString(1, u.getName());
		pst.setString(2, u.getUserName());
		pst.setString(3, u.getEmail());
		pst.setString(4, u.getMobileNo());
		pst.setString(5, u.getPassword());
		pst.setString(6, user_type);
		int result = pst.executeUpdate();
		System.out.println("Insert " + result);
		pst.close();
		return result;
	}

	public static int updateUser(User user) {
		int status = 0;
		Connection con = DB.getCon();
		String query = "UPDATE  CARRENTALDB.USERS SET user_fullname = ?,username=?,user_email=?,user_mobile = ?,user_password = ?,user_type = ? where user_id=?";
		try {

			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, user.getName());
			pst.setString(2, user.getUserName());
			pst.setString(3, user.getEmail());
			pst.setString(4, user.getMobileNo());
			pst.setString(5, user.getPassword());
			pst.setString(6, user.getUserType());
			pst.setInt(7, user.getId());
			status = pst.executeUpdate();
			con.close();
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}

		return status;
	}

	public static int delete(int userId) {
		int status = 0;
		PreparedStatement pst = null;
		try {
			Connection con = DB.getCon();

			String deleteQuery = "DELETE FROM USERS WHERE user_id = ?";
			pst = con.prepareStatement(deleteQuery);
			pst.setInt(1, userId);
			status = pst.executeUpdate();
			pst.close();
			deleteQuery = "DELETE FROM RENTCAR WHERE user_id = ?";
			pst = con.prepareStatement(deleteQuery);
			pst.setInt(1, userId);
			status = pst.executeUpdate();
			String selectQuery = "SELECT * FROM RENTCAR WHERE user_id = ?";
			pst = con.prepareStatement(selectQuery);
			pst.setInt(1, userId);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				pst = con.prepareStatement("UPDATE CARS SET AVAILABILITY = ? WHERE CAR_NO = ?");
				int avail = CarController.carAvailability(rs.getString(2)).getCarAvailable() + 1;
				pst.setInt(1, avail);
				pst.setString(2, rs.getString(2));
				status = pst.executeUpdate();
			}
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	// method to check user for login purpose
	public static User userLogin(String useremail, String password) {

		Connection con = DB.getCon();
		User u = null;
		String query = "SELECT * FROM  CARRENTALDB.USERS WHERE user_email = ? AND user_password = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst.setString(1, useremail);
			pst.setString(2, password);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
				u.setUserType(rs.getString(7));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;

	}

	public static User getUser(int id) {
		Connection con = DB.getCon();
		User u = null;
		String query = "SELECT * FROM  CARRENTALDB.USERS WHERE user_id = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst = con.prepareStatement(query);
			pst.setInt(1, id);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				u = new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return u;
	}

	public static List<User> getSpecificTypeofUsers(String userType) throws SQLException {
		Connection con = DB.getCon();

		List<User> users = new ArrayList<User>();
		PreparedStatement pst = null;
		try {
			String query = "SELECT * FROM CARRENTALDB.USERS WHERE user_type = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, userType);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				users.add(new User(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getString(7), rs.getTimestamp(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pst.close();
			con.close();
		}
		return users;
	}

}
