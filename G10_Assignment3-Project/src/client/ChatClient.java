package client;

import java.io.IOException;

import gui.client.ChangePasswordController;
import gui.client.SignInController;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import logic.User;
import ocsf.client.AbstractClient;

public class ChatClient extends AbstractClient {

	// Instance variables **********************************************

	/**
	 * The interface type variable. It allows the implementation of the display
	 * method in the client.
	 */
	ChatIF ClientController;
	public static boolean awaitResponse = false;
	public static User user;
	// Constructors ****************************************************

	/**
	 * Constructs an instance of the chat client.
	 *
	 * @param host     The server to connect to.
	 * @param port     The port number to connect on.
	 * @param clientUI The interface type variable.
	 */

	public ChatClient(String host, int port, ChatIF clientUI) throws IOException {
		super(host, port); // Call the superclass constructor
		this.ClientController = clientUI;
	}

	// Instance methods ************************************************

	/**
	 * This method handles all data that comes in from the server.
	 *
	 * @param msg The message from the server.
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		
		
		if (msg instanceof String)
		{
			String message = (String)msg;
			if (msg.toString().contains("SignIn ERROR - ")) {
				SignInController.signInController.setErrorMsg(((String)msg).substring(15));
			}
			else if(msg.toString().contains("ChangePassword ERROR - ")) {
				System.out.println(((String)msg).substring(23));
				ChangePasswordController.changePasswordController.badChangePassword(((String)msg).substring(23));
			}
			else if(msg.toString().contains("ChangePassword SUCCESS - ")) {
				System.out.println(((String)msg).substring(25));
				ChangePasswordController.changePasswordController.succesfullChangePassword(((String)msg).substring(25));
			}
		}
		if (msg instanceof User) {
			user = (User)msg;
		}
		
		awaitResponse = false;

	}

	public void handleMessageFromClientUI(Object obj) {
		try {
			openConnection();// in order to send more than one message
			awaitResponse = true;
			sendToServer(obj);
			// wait for response
			while (awaitResponse) {
				try {
					Thread.sleep(100);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
			ClientController.display("Could not send message to server: Terminating client." + e);
			Alert alert = new Alert(AlertType.WARNING);
			alert.setTitle("Connection attempt");
			alert.setHeaderText("Unable to connect to server!");
			alert.setContentText("Please make sure the server is online.");
			alert.showAndWait();
			quit();
		}
	}

	/**
	 * This method terminates the client.
	 */
	public void quit() {
		try {
			closeConnection();
		} catch (IOException e) {
		}
		System.exit(0);
	}

}
