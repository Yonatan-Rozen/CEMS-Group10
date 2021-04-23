package control;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBconnector 
{
	static Connection con;
	
	public void connectToDB()
	{
		try 
		{
            Class.forName("com.mysql.cj.jdbc.Driver");
            System.out.println("Driver definition succeed");
        } catch (Exception ex) {
        	/* handle the error*/
        	System.out.println("Driver definition failed");
    	}
        
        try 
        {
            con = DriverManager.getConnection("jdbc:mysql://localhost/CEMS?serverTimezone=IST","root","Group10*");
            System.out.println("SQL connection succeed");
     	} catch (SQLException ex) {
     		/* handle any errors*/
            System.out.println("SQLException: " + ex.getMessage());
            System.out.println("SQLState: " + ex.getSQLState());
            System.out.println("VendorError: " + ex.getErrorCode());
        }
	}
	
	public void parseData() {}
	
	public void saveToDB() {}
	
	public void removeFromDB() {}
	
	public void updateDB() {}
	
	public void selectQuery() {}
}
