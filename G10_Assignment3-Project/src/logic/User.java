package logic;

import java.io.Serializable;

/**
 * Contains all the details of any user
 */
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
	
	/**
	 * Get the username of the user
	 * @return The username
	 */
	public String getUsername() {
		return username;
	}
	/**
	 * Get the password of the user
	 * @return The password
	 */
	public String getPassword() {
		return password;
	}
	/**
	 * Sets the password of the user
	 */
	public void setPassword(String password) {
		this.password = password;
	}
	/**
	 * Get the firstname of the user
	 * @return The firstname
	 */
	public String getFirstname() {
		return firstname;
	}
	/**
	 * Get the lastname of the user
	 * @return The lastname
	 */
	public String getLastname() {
		return lastname;
	}
	/**
	 * Get the phonenumber of the user
	 * @return The phonenumber
	 */
	public String getPhonenumber() {
		return phonenumber;
	}
	/**
	 * Get the email of the user
	 * @return The email
	 */
	public String getEmail() {
		return email;
	}
	/**
	 * Get the type of the user {Teacher/Principle/Student}
	 * @return The type
	 */
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
