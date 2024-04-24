package org.jsp.bank;

public class Bank {
	private String userFirstName;
	private String userLastName;
	private String mobileNumber;
	private String emailId;
	private String password;
	private double amount;
	private String address;
	private String accountNum;
	
	public Bank(String userFirstName, String userLastName, String mobileNumber, String emailId,
			String password, String address,double amount, String accountNum) {
		this.setUserFirstName(userFirstName);
		this.setUserLastName(userLastName);
		this.setMobileNumber(mobileNumber);
		this.setEmailId(emailId);
		this.setPassword(password);
		this.setAmount(amount);
		this.setAddress(address);
		this.setAccountNum(accountNum);
	}
	public String getUserFirstName() {
		return userFirstName;
	}
	public void setUserFirstName(String userFirstName) {
		this.userFirstName = userFirstName;
	}
	public String getUserLastName() {
		return userLastName;
	}
	public void setUserLastName(String userLastName) {
		this.userLastName = userLastName;
	}
	public String getMobileNumber() {
		return mobileNumber;
	}
	public void setMobileNumber(String mobileNumber) {
		this.mobileNumber = mobileNumber;
	}
	public String getEmailId() {
		return emailId;
	}
	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getAmount() {
		return amount;
	}
	public void setAmount(double amount) {
		this.amount = amount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getAccountNum() {
		return accountNum;
	}
	public void setAccountNum(String accountNum) {
		this.accountNum = accountNum;
	}
	
}
