package net.codejava.javaee.people;

public class People {

	protected int id;
	protected String firstName;
	protected String lastName;
	protected String emailId;

	public People() {
	}

	public People(int id) {
		this.id = id;
	}

	public People(int id, String firstName, String lastName, String emailId) {
		this(firstName, lastName, emailId);
		this.id = id;
	}
	
	public People(String firstName, String lastName, String emailId) {
		this.firstName = firstName;
		this.lastName = lastName;
		this.emailId = emailId;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
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

	public String getEmailId() {
		return emailId;
	}

	public void setEmailId(String emailId) {
		this.emailId = emailId;
	}

	


}
