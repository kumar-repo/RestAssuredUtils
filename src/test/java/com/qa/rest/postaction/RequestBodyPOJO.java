package com.qa.rest.postaction;

/*
 * POJO class to create request body
 * Adv: we can create more user dynamically with out writing hard code for each use
 * 
 */
public class RequestBodyPOJO {

	private String name;
	private String gender;
	private String email;
	private String status;
	
	// generate constructor using ecplise short cut 
	//right click on eclipse editor go to source > generate constructor with all fields

	public RequestBodyPOJO(String name, String gender, String email, String status) {
		super();
		this.name = name;
		this.gender = gender;
		this.email = email;
		this.status = status;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getGender() {
		return gender;
	}

	public void setGender(String gender) {
		this.gender = gender;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}
		
}
