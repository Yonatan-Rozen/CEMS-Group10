package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import server.EchoServer;
import server.ServerUI;

public class DBconnector {
	public static Connection con;

	public static void connectToDB() {
		try {
			Class.forName("com.mysql.cj.jdbc.Driver");
			//System.out.println("Driver definition succeed");
			ServerUI.scc.appendTextToConsole("Driver definition succeed");
		} catch (Exception ex) {
			/* handle the error */
			//System.out.println("Driver definition failed");
			ServerUI.scc.appendTextToConsole("Driver definition failed");
		}

		try {
			con = DriverManager.getConnection("jdbc:mysql://localhost/CEMS?serverTimezone=IST", "root", "Group10*");
			//System.out.println("SQL connection succeed");
			ServerUI.scc.appendTextToConsole("SQL connection succeed");
		} catch (SQLException ex) {
			/* handle any errors */
			ServerUI.scc.appendTextToConsole("SQLException: " + ex.getMessage());
			ServerUI.scc.appendTextToConsole("SQLState: " + ex.getSQLState());
			ServerUI.scc.appendTextToConsole("VendorError: " + ex.getErrorCode());
			//System.out.println("SQLException: " + ex.getMessage());
			//System.out.println("SQLState: " + ex.getSQLState());
			//System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public static void parseData() {
	}

	public static void saveToDB() {
	}

	public static void removeFromDB() {
	}

	public static void updateDB(String[] sArr) {
		
		
	}

	public static void selectQuery(String[] sArr) {
		List<String> tableRowsInfo = new ArrayList<>();
		try {
			Statement stmt = con.createStatement();
			ResultSet rs = stmt.executeQuery("SELECT * FROM " + sArr[1]);
			String s = "";
			while (rs.next()) {
				for (int i = 1; i < 6; i++) {
					s += rs.getString(i) + ",";// Allocated Time might not work
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
