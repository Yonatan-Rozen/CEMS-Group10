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
	
	public String getUsername() {
		return username;
	}
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
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
	
	@Override
	public boolean equals(Object other) {
		if (!(other instanceof User))
			return false;
		User otherUser = (User) other;
		return getUsername().equals(otherUser.getUsername()) && getPassword().equals(otherUser.getPassword()) &&
				getFirstname().equals(otherUser.getFirstname()) && getLastname().equals(otherUser.getLastname()) && 
				getPhonenumber().equals(otherUser.getPhonenumber()) && getEmail().equals(otherUser.getEmail()) &&
				getType().equals(otherUser.getType());
	}

}
