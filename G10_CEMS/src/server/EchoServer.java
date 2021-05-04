package server;

import control.DBconnector;
import ocsf.server.*;

public class EchoServer extends AbstractServer {

	public static EchoServer es;
	public EchoServer(int port) {
		super(port);
	}

	@Override
	protected void handleMessageFromClient(Object msg, ConnectionToClient client) {
		ServerUI.serverConsole.appendTextToConsole(">>> " + msg + " from " + client);
		DBconnector.getInstance().parseData(msg);
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
