package app.controllers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import app.model.RentCar;
import app.model.Cars;
import app.model.User;

public class CarController {
	public static int addCar(Cars c) throws Exception {

		Connection con = DB.getCon();
		PreparedStatement pst = null;
		String insertQuery = "INSERT INTO CARRENTALDB.CARS(car_id,car_no,car_name,car_maker,"
				+ "car_model,car_color,availability) VALUES(null,?,?,?,?,?,?)";
		pst = con.prepareStatement(insertQuery);
		pst.setString(1, c.getCarNo());
		pst.setString(2, c.getCarName());
		pst.setString(3, c.getCarMaker());
		pst.setString(4, c.getCarModel());
		pst.setString(5, c.getCarColor());
		pst.setInt(6, c.getCarAvailable());
		int result = pst.executeUpdate();
		System.out.println("Insert " + result);
		pst.close();
		return result;
	}

	public static List<Cars> getAllCars() throws SQLException {
		Connection con = DB.getCon();

		List<Cars> cars = new ArrayList<Cars>();
		PreparedStatement pst = null;
		try {
			String query = "SELECT * FROM CARRENTALDB.CARS";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				cars.add(new Cars(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pst.close();
			con.close();
		}
		return cars;
	}

	public static int rentCar(RentCar c) throws Exception {
		int result = 0;
		Connection con = DB.getCon();
		PreparedStatement pst = null;
		System.out.println("User Id : " + c.getUserid());
		System.out.println("Car No : " + c.getCarNo());
		User u = UserController.getUser(c.getUserid());
		Cars car = carAvailability(c.getCarNo());
		System.out.println(u.toString());
		System.out.println(car.toString());
		if (u != null && car != null) {
			String insertQuery = "INSERT INTO CARRENTALDB.RENTCAR(rent_id,car_no,user_id,username,"
					+ "user_mobile,return_status) VALUES(null,?,?,?,?,?)";
			pst = con.prepareStatement(insertQuery);
			pst.setString(1, c.getCarNo());
			pst.setInt(2, c.getUserid());
			pst.setString(3, c.getUsername());
			pst.setLong(4, c.getUser_mobile());
			pst.setString(5, "no");
			result = pst.executeUpdate();
//		else {
//				result =  2;
//			}
		}

		if (result == 1) {
			String updateQuery = "UPDATE CARRENTALDB.CARS SET AVAILABILITY = ? WHERE car_id = ?";
			pst = con.prepareStatement(updateQuery);
			pst.setInt(1, (car.getCarAvailable() - 1));
			pst.setInt(2, car.getCarId());
			pst.executeUpdate();
		}
		pst.close();
		return result;
	}

	public static int delete(String carNo) {
		int status = 0;
		PreparedStatement pst = null;
		try {
			Connection con = DB.getCon();

			String deleteQuery = "DELETE FROM CARS WHERE car_no = ?";
			pst = con.prepareStatement(deleteQuery);
			pst.setString(1, carNo);
			status = pst.executeUpdate();
			pst.close();
			deleteQuery = "DELETE FROM RENTCAR WHERE car_no = ?";
			pst = con.prepareStatement(deleteQuery);
			pst.setString(1, carNo);
			status = pst.executeUpdate();
			pst.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static Cars getCar(int carId) {
		Connection con = DB.getCon();
		Cars car = null;
		String query = "SELECT * FROM  CARRENTALDB.CARS WHERE car_id = ?";
		try {
			PreparedStatement pst = con.prepareStatement(query);
			pst = con.prepareStatement(query);
			pst.setInt(1, carId);
			ResultSet rs = pst.executeQuery();
			if (rs.next()) {
				car = new Cars(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
						rs.getString(6), rs.getInt(7));
				;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return car;
	}

	public static int updateCar(Cars car) {
		int status = 0;
		Connection con = DB.getCon();
		String query = "UPDATE  CARRENTALDB.CARS SET car_name=?,car_maker=?,car_model = ?,car_color = ?,availability = ? where car_id=?";
//		String query2 = "UPDATE CARRENTALDB.RENTCAR SET car_no = ? WHERE car_no = ? ";
		try {

			PreparedStatement pst = con.prepareStatement(query);
//			PreparedStatement pst2 = con.prepareStatement(query2);
//			pst2.setString(1, car.getCarNo());
//			pst2.setString(2, car.getCarNo());
//			status = pst2.executeUpdate();
//			pst.setString(1, car.getCarNo());
			pst.setString(1, car.getCarName());
			pst.setString(2, car.getCarMaker());
			pst.setString(3, car.getCarModel());
			pst.setString(4, car.getCarColor());
			pst.setInt(5, car.getCarAvailable());
			pst.setInt(6, car.getCarId());
			status = pst.executeUpdate();
			con.close();
		} catch (Exception ex1) {
			ex1.printStackTrace();
		}

		return status;
	}

	public static Cars carAvailability(String carNo) throws SQLException {
		Connection con = DB.getCon();

		PreparedStatement pst = null;
		Cars car = null;
		try {
			String query = "SELECT * FROM CARRENTALDB.CARS WHERE car_no = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, carNo);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				if (rs.getInt(7) > 0) {
					car = new Cars(rs.getInt(1), rs.getString(2), rs.getString(3), rs.getString(4), rs.getString(5),
							rs.getString(6), rs.getInt(7));
				}
			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pst.close();
			con.close();
		}
		System.out.println("From Car Availability" + car.toString());
		return car;
	}

	public static int returnCar(String carNo, String username) throws SQLException {
		Connection con = DB.getCon();

		PreparedStatement pst = null;
		int status = 0;
		try {
			String query = "SELECT * FROM CARRENTALDB.RENTCAR WHERE car_no = ? AND username = ?";
			pst = con.prepareStatement(query);
			pst.setString(1, carNo);
			pst.setString(2, username);
			ResultSet rs = pst.executeQuery();

			while (rs.next()) {
				System.out.println(rs.getString(7));
				if (rs.getString(6).equals("no")) {
					System.out.println("True...");
					pst = con.prepareStatement("UPDATE RENTCAR SET RETURN_STATUS = ? WHERE CAR_NO = ?");
					pst.setString(1, "yes");
					pst.setString(2, rs.getString(2));
					status = pst.executeUpdate();
					pst.close();
					pst = con.prepareStatement("UPDATE CARS SET AVAILABILITY = ? WHERE CAR_NO = ?");
					int avail = carAvailability(rs.getString(2)).getCarAvailable() + 1;
//					System.out.println("Availability :"+avail);
					pst.setInt(1, avail);
					pst.setString(2, rs.getString(2));
					status = pst.executeUpdate();
					pst.close();
				}

			}

		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pst.close();
			con.close();
		}
		return status;
	}

	public static List<RentCar> getAllRentedCars() throws SQLException {
		Connection con = DB.getCon();

		List<RentCar> rc = new ArrayList<RentCar>();
		PreparedStatement pst = null;
		try {
			String query = "SELECT * FROM CARRENTALDB.RENTCAR";
			pst = con.prepareStatement(query);
			ResultSet rs = pst.executeQuery();
			while (rs.next()) {
				rc.add(new RentCar(rs.getInt(1), rs.getString(2), rs.getInt(3), rs.getString(4), rs.getLong(5),
						rs.getTimestamp(7), rs.getString(6), rs.getTimestamp(8)));
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			pst.close();
			con.close();
		}
		return rc;
	}

}
