package application;

import java.util.Date;

public class Customer {
	// Attributes
	private String foreName; // Tuple of forename and familyname act as key
	private String lastName;
	private String address;
	private String postalCode; // Postal code could be non numerical and arithmetic operations are useless
	private String email;
	private int creditLimit;
	
	// Additional attributes
	// Inspired from https://www.researchgate.net/figure/Example-of-typical-customer-attributes-and-cardinality_tbl1_220802715
	private String gender; // Either "M" or "F"
	private Date dateOfBirth;
	
	// Constructor
	public Customer(String forename, String lastName, String address, String postalCode, String email, int creditLimit,
			String gender, Date dateOfBirth) {
		super();
		this.foreName = forename;
		this.lastName = lastName;
		this.address = address;
		this.postalCode = postalCode;
		this.email = email;
		this.creditLimit = creditLimit;
		this.gender = gender;
		this.dateOfBirth = dateOfBirth;
	}
	
	// Getter and Setter
	public String getForename() {
		return foreName;
	}
	public void setForename(String forename) {
		this.foreName = forename;
	}
	public String getLastName() {
		return lastName;
	}
	public void setLastName(String lastName) {
		this.lastName = lastName;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getPostalCode() {
		return postalCode;
	}
	public void setPostalCode(String postalCode) {
		this.postalCode = postalCode;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	public int getCreditLimit() {
		return creditLimit;
	}
	public void setCreditLimit(int creditLimit) {
		this.creditLimit = creditLimit;
	}
	public String getGender() {
		return gender;
	}
	public void setGender(String gender) {
		this.gender = gender;
	}
	public Date getDateOfBirth() {
		return dateOfBirth;
	}
	public void setDateOfBirth(Date dateOfBirth) {
		this.dateOfBirth = dateOfBirth;
	}
	
	
	
	
}
