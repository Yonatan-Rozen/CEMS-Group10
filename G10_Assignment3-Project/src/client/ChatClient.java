package client;

import java.io.IOException;
import java.util.List;

import gui.client.ChangePasswordController;
import gui.client.SignInController;
import gui.client.teacher.TeacherCreateQuestionController;
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
	 * @param msg The message from the server.
	 */
	@Override
	protected void handleMessageFromServer(Object msg) {
		
		/**** short execution checks ***/
		if (msg instanceof User) { // SignIn Success
			user = (User) msg;
		} 
		
		/**** method execution handling ****/
		else if (msg instanceof String) {
			handleStringMessagesFromServer((String) msg);
		}
		else if (msg instanceof List) {
			handleListMessagesFromserver((List<?>)msg);
		}
		
		// releases 'handleMessageFromClientUI' to continue getting new input
		awaitResponse = false;
	}
	
	/**
	 * Handle with (String) type messages.
	 * @param msg The (String) object.
	 */
	private void handleStringMessagesFromServer(String msg) {
		
		if (msg.contains("SignIn ERROR - ")) { // SignIn Errors
			SignInController.siController.setErrorMsg((msg).substring("SignIn ERROR - ".length()));
		} 
		else if (msg.contains("ChangePassword ERROR - ")) { // ChangePassword Errors
			ChangePasswordController.cpController.badChangePassword((msg).substring("ChangePassword ERROR - ".length()));
		} 
		else if (msg.contains("ChangePassword SUCCESS - ")) { // ChangePassword Success
			ChangePasswordController.cpController.successfulChangePassword((msg).substring("ChangePassword SUCCESS - ".length()));
		}
		else if (msg.contains("CreateQuestion SUCCESS - ")) { // CreateQuestion Success
			TeacherCreateQuestionController.tcqController.successfulCreateQuestion((msg).substring("CreateQuestion SUCCESS - ".length()));
		}
		else ClientController.display(msg);
	}
	
	/**
	 * Handles with (List<?>) type messages.
	 * @param msg The (List<?>) object. first parameter 
	 */
	private void handleListMessagesFromserver(List<?> msg) {
		String s = (String) msg.get(0);
		msg.remove(0);
		switch (s) {
		case "getSubjectsByUsername":
			TeacherCreateQuestionController.tcqController.setSubjectChoiceBox((List<String>)msg);
			break;
		default:
			break;
		}
	}
	
	/**
	 * Handles with messages that the client sends to the server
	 * @param obj The message to send
	 */
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
		} catch (IOException e) { }
		System.exit(0);
	}

}
