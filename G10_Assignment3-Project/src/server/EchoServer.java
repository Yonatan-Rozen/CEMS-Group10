package server;

import java.io.IOException;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.StageStyle;
import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class EchoServer extends AbstractServer{

	public static EchoServer es;
	
	// CONSTRUCTORS *****************************************************************
	public EchoServer(int port) {
		super(port);
	}
	
	// handleMessageFromClient METHOD ***********************************************
	/**
	 * Called when any [client = {Student, Teacher, Principle}] sends a request to the server
	 */
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		ServerUI.serverConsole.println(">>> " + msg + " from " + client);
		DBconnector.getInstance().parseData(msg);
		
		// send message back to client
		try {
			client.sendToClient(msg);
		} catch (IOException e) {
			ServerUI.serverConsole.println("ERROR - Could not send message back to client");
		}
	}
	
	/**
	 * Called when the server starts listening for connections.
	 */
	@Override
	protected void serverStarted() {
		ServerUI.serverConsole.println("Server started listening for connections on port " + getPort());
		es = this;
	}
	
	/**
	 * Called when the server stops listening for connections.
	 */
	@Override
	protected void serverStopped() {
		ServerUI.serverConsole.println("Server has stopped listening for connections.");
	}
}
