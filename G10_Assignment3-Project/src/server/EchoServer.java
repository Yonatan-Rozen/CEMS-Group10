package server;

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class EchoServer extends AbstractServer {

	public static EchoServer es;

	// CONSTRUCTORS
	// *****************************************************************
	public EchoServer(int port) {
		super(port);
	}

	// handleMessageFromClient METHOD
	// ***********************************************
	/**
	 * Called when any [client = {Student, Teacher, Principle}] sends a request to
	 * the server
	 */
	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {

		if (msg instanceof Object[]) {
			String request = (String) ((Object[]) msg)[0];
			ServerUI.serverConsole.println(">>> " + request + " from " + client);
			
			// check if it's a 'SendSendMessage' request (to send message to other clients)
			if (request.contains("SendMessage")) {
				es.sendToAllClients(msg);
				try { client.sendToClient("MessageHasBeenSent");
				} catch (IOException e) { e.printStackTrace(); }
			}
			else useDatabase(msg, client);
		} 
		else {
			ServerUI.serverConsole.println(">>> " + msg + " from " + client);
			useDatabase(msg, client);
		}
	}

	/**
	 * Uses info from the database and returns it to the client
	 * @param msg The specified data request from the database
	 * @param client The client that sent the message
	 */
	public void useDatabase(Object msg, ConnectionToClient client) {
		try { DBconnector.getInstance().parseData(msg, client);
		} catch (IOException e) {
			e.printStackTrace();
			ServerUI.serverConsole.println("ERROR - Could not answer client");
		}
	}

	/**
	 * Handle with messages sent between clients
	 */
	@Override
	public void sendToAllClients(Object msg) {
		Thread[] clientThreadList = getClientConnections();
		
		if (msg instanceof Object[]) {
			String request = (String) ((Object[]) msg)[0];
			
			switch(request) {
			case "SendMessageExamIDExamTypeAndExamCode":
				for (int i = 0; i < clientThreadList.length; i++) {
					ConnectionToClient client = (ConnectionToClient)clientThreadList[i];
					try {
						if (client.getInfo(client.getName()) == "Student")
							client.sendToClient(msg);
					} catch (Exception ex) { }
				}
				break;
			default:
				break;
			}
		}
		
		// default for any message
		super.sendToAllClients(msg);
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
