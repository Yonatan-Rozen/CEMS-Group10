package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import client.ChatClient;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import logic.User;
import ocsf.server.ConnectionToClient;

// singleton class
public class DBconnector {
	public static Connection con;
	private static DBconnector dbCinstance;
	// ***********************************************************************************************
	private DBconnector() { }

	public static DBconnector getInstance() {
		if (dbCinstance == null)
			dbCinstance = new DBconnector();
		return dbCinstance;
	}
	// ***********************************************************************************************
	/**
	 *  Handles with the connection to the database
	 */
	public void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ServerUI.serverConsole.println("mysql driver definition succeed");
		} catch (Exception ex) {
			ServerUI.serverConsole.println("mysql driver definition failed");
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/cems?serverTimezone=IST", "root", "Group10*");
			ServerUI.serverConsole.println("SQL connection succeed.");
		} catch (SQLException ex) {
			ServerUI.serverConsole.println("SQLException: " + ex.getMessage());
			ServerUI.serverConsole.println("SQLState: " + ex.getSQLState());
			ServerUI.serverConsole.println("VendorError: " + ex.getErrorCode());
		}

	}
	// ***********************************************************************************************
	/**
	 * Parses any object that can be deserialized
	 * @param data 		The data to parse
	 * @param client 	The user who sent the message to the server
	 * @throws IOException if an I/O error occur when sending the message.
	 */
	public void parseData(Object data, ConnectionToClient client) throws IOException {
		if (data instanceof String)
			client.sendToClient(data);
		else if (data instanceof String[]) {
			
			String[] request = (String[])data;
			
			switch(request[0]) {
			case "btnPressSignIn":
				getUserInfoByUsernameAndPassword(request[1], request[2], client);
				break;
			case "btnPressConfirmChange":
				setNewPasswordByusername(request[1], request[2], request[3], request[4], request[5], client);
				break;
			case "GetSubjects":
				getSubjectsByUsername(request[1], client);
				break;
			default:
				ServerUI.serverConsole.println(request[0] + " is not a valid case!");
				break;
			}
		}
	}
	// ***********************************************************************************************
	// QUERY METHODS
	// ***********************************************************************************************
	/**
	 * Sends to the teacher an (ArrayList) of her subjects of study
	 * @param username 	The username of the teacher
	 * @param client 	The teacher
	 */
	private void getSubjectsByUsername(String username, ConnectionToClient client) {
		List<String> bankList = new ArrayList<>();
		bankList.add("getSubjectsByUsername");
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT S.SubjectName FROM cems.subjects S, cems.subjects_of_teacher SOT "
					+ "WHERE S.SubjectID = SOT.SubjectID AND SOT.Username = \""+ username +"\";");
			
			while(rs.next())
				bankList.add(rs.getString(1));
			client.sendToClient(bankList);
			
		} catch (SQLException | IOException e) {
			// * This method should always work!!! ; Add Missing information if it doesn't*
			e.printStackTrace();
		}
	}
	// ***********************************************************************************************
	/**
	 * Sets a new password for the user by his choice
	 * @param username 		The username of the user
	 * @param actaulPass 	The actaul password; retrived earlier from the DB
	 * @param tryPass 		The current password; inputed by the user
	 * @param newPass 		The new password; inputed by the user
	 * @param reNewPass 	The retyped new password; inputed by the user
	 * @param client 		The user which is requesting to change his password
	 * @throws IOException if an I/O error occur when sending the message.
	 */
	private void setNewPasswordByusername(String username, String actaulPass, String tryPass, String newPass, String reNewPass, ConnectionToClient client) throws IOException {
	   	
		if (tryPass.equals("") || newPass.equals("") || reNewPass.equals("")) {
			client.sendToClient("ChangePassword ERROR - All fields are required!");
			return;
		}
		if (!actaulPass.equals(tryPass)) {
	   		client.sendToClient("ChangePassword ERROR - Wrong current password input!");
	   		return;
		}
		if (!newPass.equals(reNewPass)) {
			client.sendToClient("ChangePassword ERROR - RePassword must be the same as NewPassword!");
			return;
		}
		
		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("UPDATE users SET Password =? WHERE Username =?");
			stmt.setString(1,newPass);
			stmt.setString(2,username);
			stmt.executeUpdate();
		}
		catch (SQLException e) {e.printStackTrace();}
		
		client.sendToClient("ChangePassword SUCCESS - Your password was set successfully!");
	}
	// ***********************************************************************************************
	/**
	 * Sends to the user all the details about his user (get stored in user thread)
	 * @param username 	The username; inputed by the user
	 * @param password 	The new password; inputed by the user
	 * @param client 	A user of one of the following types : Student / Teacher / Principle
	 * @throws IOException if an I/O error occur when sending the message.
	 */
	public void getUserInfoByUsernameAndPassword(String username, String password, ConnectionToClient client) throws IOException {

		if (username.equals("") || password.equals(""))
		{
			client.sendToClient("SignIn ERROR - All fields are required!");
			return;
		}
		
		int NumberOfColumns = 7;
		List<String> userDetails = new ArrayList<>();
		
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * From users WHERE Username = \"" + username + "\" AND Password = \"" + password + "\"");

			if (rs.next()) {
				for (int i = 1; i <= NumberOfColumns; i++) {
					userDetails.add(rs.getString(i));
				}
				User user = new User(userDetails.get(0), userDetails.get(1), userDetails.get(2), userDetails.get(3),
						userDetails.get(4), userDetails.get(5), userDetails.get(6));

				client.sendToClient(user);
			} 
			else client.sendToClient("SignIn ERROR - Wrong username or password!");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	// ***********************************************************************************************
	
	
}