package test;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

import org.junit.Before;
import org.junit.Test;

import gui.client.SignInController;
import javafx.scene.control.Alert.AlertType;

public class ClientSignInTest {
	
	private SignInController signInController;
	private String username;
	private String password;

	@Before
	public void setUp() throws Exception {
		signInController = new SignInController();
	}

	/**
	 * user inserts only password (username stays blank)
	 * input : username = "", password = "4"
	 * expected : alert details (new Object[] {AlertType.ERROR, "Error message", "All fields are required!"})
	 */
	@Test
	public void testCheckEmptyInputWithEmtpyUsername() {
		username = "";
		password = "4";
		Object[] expected  = new Object[] {AlertType.ERROR, "Error message", "All fields are required!"};
		
		Object[] actual = signInController.checkEmptyInput(username, password);
		for (int i = 0 ; i < 3; i ++)
			assertEquals(expected[i] , actual[i]);
	}
	
	/**
	 * user inserts only username (password stays blank)
	 * input : username = "4", password = ""
	 * expected : alert details (new Object[] {AlertType.ERROR, "Error message", "All fields are required!"})
	 */
	@Test
	public void testCheckEmptyInputWithEmtpyPassword() {
		username = "4";
		password = "";
		Object[] expected  = new Object[] {AlertType.ERROR, "Error message", "All fields are required!"};
		
		Object[] actual = signInController.checkEmptyInput(username, password);
		for (int i = 0 ; i < 3; i ++)
			assertEquals(expected[i] , actual[i]);
	}
	
	/**
	 * user doesn't insert username and password
	 * input : username = "", password = ""
	 * expected : alert details (new Object[] {AlertType.ERROR, "Error message", "All fields are required!"})
	 */
	@Test
	public void testCheckEmptyInputWithEmtpyUsernameAndPassword() {
		username = "";
		password = "";
		Object[] expected  = new Object[] {AlertType.ERROR, "Error message", "All fields are required!"};
		
		Object[] actual = signInController.checkEmptyInput(username, password);
		for (int i = 0 ; i < 3; i ++)
			assertEquals(expected[i] , actual[i]);
	}
	
	
	/**
	 * user inserts both username & password
	 * input : username = "125", password = "15221"
	 * expected : no alert details (null)
	 */
	@Test
	public void testFullyInsertedInput() {
		username = "125";
		password = "15221";
		
		assertNull(signInController.checkEmptyInput(username, password));
	}
}
