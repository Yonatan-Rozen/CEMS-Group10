package logic;

import java.io.Serializable;

@SuppressWarnings("serial")
public class User implements Serializable{
	
	private String username;
	private String password;
	private String firstname;
	private String lastname;
	private String phonenumber;
	private String email;
	private String type;
	
	public User(String username, String password, String firstname, String lastname, String phonenumber, String email, String string) {
		this.username = username;
		this.password = password;
		this.firstname = firstname;
		this.lastname = lastname;
		this.phonenumber = phonenumber;
		this.email = email;
		this.type = string;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getUsername() {
		return username;
	}
	public String getFirstname() {
		return firstname;
	}
	public String getLastname() {
		return lastname;
	}
	public String getPhonenumber() {
		return phonenumber;
	}
	public String getEmail() {
		return email;
	}
	public String getType() {
		return type;
	}
	
	

}
