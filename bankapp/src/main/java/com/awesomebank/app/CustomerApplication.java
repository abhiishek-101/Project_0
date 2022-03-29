package com.awesomebank.app;

public class CustomerApplication {

	String accStatus ="pending";
	String accType;
	String firstName;
	String lastName;
	String pan;
	String password;
	double intialDeposit;
	public String getAccStatus() {
		return accStatus;
	}
	public void setAccStatus(String accStatus) {
		this.accStatus = accStatus;
	}
	public String getAccType() {
		return accType;
	}
	public void setAccType(String accType) {
		this.accType = accType;
	}
	public String getFirstName() {
		return firstName;
	}
	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getPan() {
		return pan;
	}
	public void setPan(String pan) {
		this.pan = pan;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public double getIntialDeposit() {
		return intialDeposit;
	}
	public void setIntialDeposit(double intialDeposit) {
		this.intialDeposit = intialDeposit;
	}
	
	
	
}
