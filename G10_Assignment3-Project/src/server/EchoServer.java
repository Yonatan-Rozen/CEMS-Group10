package server;

import java.io.BufferedOutputStream;
import java.io.FileOutputStream;
import java.io.IOException;

import common.MyFile;
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
//		if (msg instanceof MyFile) {
//			System.out.println("asdasdad123124asd");
//			int fileSize = ((MyFile) msg).getSize();
//			System.out.println("Message received: " + ((MyFile) msg).getFileName() + " from " + client);
//			System.out.println("length " + fileSize);
//
//			try {
//				FileOutputStream fos = new FileOutputStream(((MyFile) msg).getFileName()); // write the data from
//																							// file(byte by byte)
//				BufferedOutputStream bos = new BufferedOutputStream(fos); // write data to memory
//				bos.write(((MyFile) msg).getMybytearray(), 0, fileSize); // write
//				bos.flush(); // empty
//				fos.flush();
//			} catch (Exception e) {
//				System.out.println("Error send " + ((MyFile) msg).getFileName() + " to Client");
//			}
//		}
//
//		else 
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
	 * 
	 * @param msg    The specified data request from the database
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

			switch (request) {
			case "SendMessageExamIDExamTypeAndExamCode":
				for (int i = 0; i < clientThreadList.length; i++) {
					ConnectionToClient student = (ConnectionToClient)clientThreadList[i];
					try {
						if (student.getInfo(student.getName()).equals("Student"))
							student.sendToClient(msg);
					} catch (Exception ex) {
					}
				}
				return;
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
