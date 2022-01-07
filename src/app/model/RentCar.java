package app.model;

import java.util.Date;

public class RentCar {
	
	private int car_id, userid; 
	private String username,carNo;
	private long user_mobile;
	private Date issueddate;
	private String returnstatus;
	private Date returnDate;
	

	public RentCar(int car_id, String carNo, int userid, String username, long user_mobile, Date issueddate,
			String returnstatus, Date returnDate) {
		super();
		this.car_id = car_id;
		this.carNo = carNo;
		this.userid = userid;
		this.username = username;
		this.user_mobile = user_mobile;
		this.issueddate = issueddate;
		this.returnstatus = returnstatus;
		this.returnDate =returnDate;
	}
	
	public RentCar(String carNo, int userid, String username, long user_mobile) {
		this.carNo = carNo;
		this.userid = userid;
		this.username = username;
		this.user_mobile = user_mobile;
	}

	public String getCarNo() {
		return carNo;
	}

	public void setCarNo(String carNo) {
		this.carNo = carNo;
	}

	public int getUserid() {
		return userid;
	}

	public void setUserid(int userid) {
		this.userid = userid;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public long getUser_mobile() {
		return user_mobile;
	}

	public void setUser_mobile(long user_mobile) {
		this.user_mobile = user_mobile;
	}

	public Date getIssueddate() {
		return issueddate;
	}

	public void setIssueddate(Date issueddate) {
		this.issueddate = issueddate;
	}

	public String getReturnstatus() {
		return returnstatus;
	}

	public void setReturnstatus(String returnstatus) {
		this.returnstatus = returnstatus;
	}

	public int getCar_id() {
		return car_id;
	}

	public void setCar_id(int car_id) {
		this.car_id = car_id;
	}

	public Date getReturnDate() {
		return returnDate;
	}

	public void setReturnDate(Date returnDate) {
		this.returnDate = returnDate;
	}
	
	
	
}
