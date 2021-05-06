package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.EchoServer;
import server.ServerUI;

//DBconnector is a singleton class
public class DBconnector {
	public static Connection con;
	private static DBconnector dbCinstance;

	private DBconnector() {}

	public static DBconnector getInstance() {
		if (dbCinstance == null)
			dbCinstance = new DBconnector();
		return dbCinstance;
	}

	// Handles the connection with the database
	public void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			ServerUI.serverConsole.appendTextToConsole("Driver definition succeed");
		} catch (Exception ex) {
			ServerUI.serverConsole.appendTextToConsole("Driver definition failed");
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/OOOO?serverTimezone=IST", "root", "OOOO2");
		} catch (SQLException ex) {
			ServerUI.serverConsole.appendTextToConsole("SQLException: " + ex.getMessage());
			ServerUI.serverConsole.appendTextToConsole("SQLState: " + ex.getSQLState());
			ServerUI.serverConsole.appendTextToConsole("VendorError: " + ex.getErrorCode());
		}
		ServerUI.serverConsole.appendTextToConsole("SQL connection succeed...");
	}

	/**
	 * @param msg the object to parse.
	 * @if msg is instance of String -> split msg and handle the request accordingly
	 */
	public void parseData(Object msg) {
		if (msg instanceof String) { // handle messages
			String[] s = msg.toString().split(" ");
			switch (s[0]) {
			case "Request":
				getInstance().selectQuery(s);
				break;
			case "Update":
				getInstance().updateDB(s);
				break;
			default:
				break;
			}
		}
	}
	
	
	/**
	 * 
	 * @param sArr contains the info for updating 
	 * @example for sArr = [Update,Test,AllocatedTime,120,ID,010201]
	 * we get the query: {Update Test SET AllocatedTime = "120" WHERE ID = "010201"}
	 */
	public void updateDB(String[] sArr) {

		PreparedStatement stmt;
		try {
			stmt = con.prepareStatement("UPDATE " + sArr[1] + " SET " + sArr[2] + " = \"" 
							+ sArr[3] + "\" WHERE " + sArr[4] + " = \"" + sArr[5] + "\"");
			stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * @param sArr contains the required table name
	 * @example for sArr=[Request,Test] we get the query {SELECT * FROM Test}
	 */
	public void selectQuery(String[] sArr) {
		List<String> tableRowsInfo = new ArrayList<>();
		int NumberOfColumns = 5;
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + sArr[1]);
			String s = "";
			while (rs.next()) {
				for (int i = 0; i < NumberOfColumns; i++) {
					s += rs.getString(i+1) + ",";
				}
				tableRowsInfo.add(s);
				s = "";
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		EchoServer.es.sendToAllClients(tableRowsInfo);
	}
}
