package server;

import java.sql.Connection;

import control.DBconnector;
import gui.ServerConsoleController;
import ocsf.server.*;

public class EchoServer extends AbstractServer {

	public static EchoServer es;
	public EchoServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// System.out.println("Message received: " + msg + " from " + client);
		ServerUI.serverConsole.appendTextToConsole("Message received: " + msg + " from " + client);

		//parsingTheData(msg); // deleted parsingTheData from EchoServer and created paresData in DBconnector - Yonatan
		DBconnector.parseData(msg);
		this.sendToAllClients(msg);

	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		ServerUI.serverConsole.appendTextToConsole("Server listening for connections on port " + getPort());
		es = this;
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		ServerUI.serverConsole.appendTextToConsole("Server has stopped listening for connections.");
	}

}
