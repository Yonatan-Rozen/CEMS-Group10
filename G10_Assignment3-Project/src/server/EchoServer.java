package server;

import java.io.IOException;

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
		if (msg instanceof Object[])
			ServerUI.serverConsole.println(">>> " + ((Object[])msg)[0] + " from " + client);
		else ServerUI.serverConsole.println(">>> " + msg + " from " + client);
		// send message back to client
		try {
			DBconnector.getInstance().parseData(msg, client);
		} catch (IOException e) {
			e.printStackTrace();
			ServerUI.serverConsole.println("ERROR - Could not answer client");
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
