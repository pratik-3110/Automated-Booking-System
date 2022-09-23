package com.hsbc.system.model;

/**
 * 
 * @author The Xceptionals
 * Bean to fetch details of users
 *
 */
public class Users {

	private int userId;
	private String userName;
	private String email;
	private String phone;
	private String role;
	private int credit;
	private String lastLoggedIn;
	
	public Users() {
		super();
	}
	
	
	
	public Users(String userName, String email, String phone, String role) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.role = role;
	}
	
	public Users(int userid,String userName, String email, String phone, String role)
	{
		this(userName,email,phone,role);
		this.userId=userid;
	}


	public Users(String userName, String email, String phone, String role, int credit) {
		this(userName,email,phone,role);
		this.credit=credit;
	}

	public Users(int userId, String userName, String email, String phone, String role, int credit,
			String lastLoggedIn) {
		super();
		this.userId = userId;
		this.userName = userName;
		this.email = email;
		this.phone = phone;
		this.role = role;
		this.credit = credit;
		this.lastLoggedIn = lastLoggedIn;
	}
	
	public int getUserId() {
		return userId;
	}
	public void setUserId(int userId) {
		this.userId = userId;
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
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public String getRole() {
		return role;
	}
	public void setRole(String role) {
		this.role = role;
	}
	public int getCredit() {
		return credit;
	}
	public void setCredit(int credit) {
		this.credit = credit;
	}
	public String getLastLoggedIn() {
		return lastLoggedIn;
	}
	public void setLastLoggedIn(String lastLoggedIn) {
		this.lastLoggedIn = lastLoggedIn;
	}

	@Override
	public String toString() {
		return "Users [userId=" + userId + ", userName=" + userName + ", email=" + email + ", phone=" + phone
				+ ", role=" + role + ", credit=" + credit + ", lastLoggedIn=" + lastLoggedIn + "]";
	}
	
	
	
}
