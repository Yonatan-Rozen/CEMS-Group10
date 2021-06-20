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

public class ServerSignInTest {
	
	private DBconnector dbconnector;
	private String username = null;
	private String password = null;
	
	private class ServerConsoleControllerStub implements IServerConsoleController{

		/* stubing to avoid NullPointerException 'connectoToDB()'*/
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
		username = "4";
		password = "4";
	}

	/**
	 * user inserts correct username and password and connects to the system
	 * input : username = '4', password = '4'
	 * expected : user (successful connection)
	 */
	@Test
	public void testCorrectUsername() {
		User expected = new User("4","4","Yonatan","Rozen","0508122784","yon969@gmail.com","Teacher");
		User actual = null;
		
		actual = (User) dbconnector.getUserInfoByUsernameAndPassword(username,password);
		
		assertEquals(expected, actual);
	}
	
	/**
	 * user trying to log in again after succesful login
	 * input : username = '4' , password = '4';
	 * expected : "SignIn ERROR - This user is already connected!"
	 */
	@Test
	public void testSameUserTryingToLoginAgainWithoutDisconnecting() {
		String expected = "SignIn ERROR - This user is already connected!";
		String actual = null;
		
		// first login returns User object
		if (!(dbconnector.getUserInfoByUsernameAndPassword(username,password) instanceof User)) {
			fail();
			return;
		}
		// second login returns String error message
		actual = (String) dbconnector.getUserInfoByUsernameAndPassword(username,password); 
		
		assertEquals(expected, actual);
	}
	
	/**
	 * user trying to log in with a wrong username or password
	 * input : username = '4', password = '5' (no user has this combination in the database)
	 * expected : "SignIn ERROR - Wrong username or password!"
	 */
	@Test
	public void testInsertWrongUsernameOrPassword(){
		password = "5"; // set different password
		
		String expected = "SignIn ERROR - Wrong username or password!";
		String actual = null;
		
		actual = (String) dbconnector.getUserInfoByUsernameAndPassword(username,password);
		
		assertEquals(expected, actual);
	}
	
	@After
	public void tearDown() throws Exception {
		dbconnector.resetUserConnections();
	}
}
