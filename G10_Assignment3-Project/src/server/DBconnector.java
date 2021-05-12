package server;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

// singleton class
public class DBconnector {
	public static Connection con;
	private static DBconnector dbCinstance;

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
	 * @param msg the data to parse 
	 */
	public void parseData(Object data) {
	}
	
	// QUERY METHODS ***********************************************************************************************
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
}