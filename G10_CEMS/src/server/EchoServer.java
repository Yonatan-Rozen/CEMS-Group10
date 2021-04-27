package server;

import java.sql.Connection;

import control.DBconnector;
import gui.ServerConsoleController;
import ocsf.server.*;

public class EchoServer extends AbstractServer {

	public static Connection con;
	public static EchoServer es;
	public EchoServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		// System.out.println("Message received: " + msg + " from " + client);
		ServerUI.scc.appendTextToConsole("Message received: " + msg + " from " + client);

		parsingTheData(msg);
		this.sendToAllClients(msg);

	}

	private void parsingTheData(Object msg) {
		
		if (msg instanceof String)
		{ // handle messages
			String[] s = msg.toString().split(" ");
			switch (s[0]) {
				case "Request":
					DBconnector.selectQuery(s);
					break;
				case "Update":
					DBconnector.updateDB(s);
					break;
				default:
					break;
			}
		}
	}

	/**
	 * This method overrides the one in the superclass. Called when the server
	 * starts listening for connections.
	 */
	protected void serverStarted() {
		ServerUI.scc.appendTextToConsole("Server listening for connections on port " + getPort());
		es = this;
		// System.out.println("Server listening for connections on port " + getPort());
	}

	/**
	 * This method overrides the one in the superclass. Called when the server stops
	 * listening for connections.
	 */
	protected void serverStopped() {
		ServerUI.scc.appendTextToConsole("Server has stopped listening for connections.");
		// System.out.println("Server has stopped listening for connections.");
	}

}
