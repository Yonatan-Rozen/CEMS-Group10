package server;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import logic.User;
import ocsf.server.ConnectionToClient;

// singleton class
public class DBconnector {
	public static Connection con;
	private static DBconnector dbCinstance;
	private static int counter = 0;

	private DBconnector() {
	}

	public static DBconnector getInstance() {
		if (dbCinstance == null)
			dbCinstance = new DBconnector();
		return dbCinstance;
	}

	// Handles with the connection to the database
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

	/**
	 * Parses any object that can be deserialized
	 * 
	 * @param client
	 * 
	 * @param msg    the data to parse
	 * @throws IOException
	 */
	public void parseData(Object data, ConnectionToClient client) throws IOException {
		if (data instanceof String)
			client.sendToClient(data);
		else if (data instanceof String[]){
			
			String[] request = (String[])data;
			
			switch(request[0]) {
			case "btnPressSignIn":
				getUserInfoByUsernameAndPassword(request[1], request[2], client);
				break;
			default:
				ServerUI.serverConsole.println(request[0] + " is not a valid case!");
				break;
			}
		}
	}

	// QUERY METHODS
	// ***********************************************************************************************
	// SELECT * FROM users WHERE Username = "1" AND Password = "1";
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
			String s = "";

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
}