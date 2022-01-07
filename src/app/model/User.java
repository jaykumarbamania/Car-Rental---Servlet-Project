package app.model;

import java.util.Date;

public class User {
	
	private int id;
	private String name;
	private String userName;
	private String email;
	private String mobileNo;
	private String password;
	private String userType;
	private Date createdAt;
	
	public User() {
		// TODO Auto-generated constructor stub
	}
	
	public User(int id, String name, String userName, String email, String mobileNo, String password) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
	}
	


	public User(int id, String name, String userName, String email, String mobileNo, String password, String userType,
			Date createdAt) {
		super();
		this.id = id;
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
		this.userType = userType;
		this.createdAt = createdAt;
	}







	public User(String name, String userName, String email, String mobileNo, String password) {
		super();
		this.name = name;
		this.userName = userName;
		this.email = email;
		this.mobileNo = mobileNo;
		this.password = password;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getMobileNo() {
		return mobileNo;
	}

	public void setMobileNo(String mobileNo) {
		this.mobileNo = mobileNo;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	

	public String getUserType() {
		return userType;
	}

	public void setUserType(String userType) {
		this.userType = userType;
	}
	
	

	public Date getCreatedAt() {
		return createdAt;
	}







	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}







	@Override
	public String toString() {
		return "User [id=" + id + ", name=" + name + ", userName=" + userName + ", email=" + email + ", mobileNo="
				+ mobileNo + ", password=" + password + ", userType=" + userType + "]";
	}


	

}
