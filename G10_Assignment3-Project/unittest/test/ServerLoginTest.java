package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.fail;

import java.io.IOException;

import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import gui.server.IServerConsoleController;
import logic.User;
import server.DBconnector;

public class ServerLoginTest {
	
	private DBconnector dbconnector;
	private String username = null;
	private String password = null;
	
	private class ServerConsoleControllerStub implements IServerConsoleController{

		/* stubing to avoid NullPointerException */
		@Override
		public void println(String info) {
			System.out.println(info);
		}
	}

	@Before
	public void setUp() throws Exception {
		dbconnector = DBconnector.getInstance();
		dbconnector.setServerConsole(new ServerConsoleControllerStub());
		dbconnector.connectToDB();
		dbconnector.resetUserConnections();
	}

	/**
	 * user inserts correct username and password and connects to the system
	 * input : username = '4', password = '4'
	 * expected : user (successful connection)
	 */
	@Test
	public void testCorrectUsername() {
		username = "4";
		password = "4";
		User expected = new User("4","4","Yonatan","Rozen","0508122784","yon969@gmail.com","Teacher");
		User actual = null;
		try { actual = (User) dbconnector.getUserInfoByUsernameAndPassword(username,password);
		} catch (IOException e) {  fail(); }
		
		assertEquals(expected, actual);
	}
	
	/**
	 * user trying to log in again after succesful login
	 * input : username = '4' , password = '4';
	 * expected : "SignIn ERROR - This user is already connected!"
	 */
	@Test
	public void testSameUserTryingToLoginAgainWithoutDisconnecting() {
		username = "4";
		password = "4";
		String expected = "SignIn ERROR - This user is already connected!";
		String actual = null;
		
		try { 
			dbconnector.getUserInfoByUsernameAndPassword(username,password); // first login
			actual = (String) dbconnector.getUserInfoByUsernameAndPassword(username,password); // scond login
		} catch (IOException e) { fail(); }
		
		assertEquals(expected, actual);
	}
	
	/**
	 * user trying to log in with a wrong username or password
	 * input : username = '4', password = '5' (no user has this combination in the database)
	 * expected : "SignIn ERROR - Wrong username or password!"
	 */
	@Test
	public void testInsertWrongUsernameOrPassword(){
		username = "4";
		password = "5";
		String expected = "SignIn ERROR - Wrong username or password!";
		String actual = null;
		
		try { 
			actual = (String) dbconnector.getUserInfoByUsernameAndPassword(username,password); // scond login
		} catch (IOException e) { fail(); }
		
		assertEquals(expected, actual);
	}
	
	@After
	public void tearDown() throws Exception {
		dbconnector.resetUserConnections();
	}
}
