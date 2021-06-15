package server;

import java.io.IOException;

import ocsf.server.AbstractServer;
import ocsf.server.ConnectionToClient;

public class EchoServer extends AbstractServer {

	public static EchoServer es;
	private static int amountOfStudents;

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
				try {
					switch(request) {
					case "SendMessageExamIDExamTypeAndExamCode":
						client.sendToClient(new Object[] {"MessageSentExamIDExamTypeAndExamCode", amountOfStudents});
						break;
					default:
						client.sendToClient("MessageSent");
						break;
					}
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
				amountOfStudents = 0;
				for (int i = 0; i < clientThreadList.length; i++) {
					ConnectionToClient student = (ConnectionToClient)clientThreadList[i];
					try {
						if (student.getInfo(student.getName()).equals("Student")) {
							//System.out.println("GOT HERE");
							student.sendToClient(msg);
							amountOfStudents++;
						}
					} catch (Exception ex) { }
				}
				return;
			case "SendMessageLockExam":
				for (int i = 0; i < clientThreadList.length; i++) {
					ConnectionToClient student = (ConnectionToClient)clientThreadList[i];
					try {
						if (student.getInfo(student.getName()).equals("Student"))
							student.sendToClient(msg);
					} catch (Exception ex) {
					}
				}
				return;
			case "SendMessageIncNumStudentsInExam":
			case "SendMessageDecNumStudentsInExam":
				for (int i = 0; i < clientThreadList.length; i++) {
					ConnectionToClient teacher = (ConnectionToClient)clientThreadList[i];
					//teacherID=msg[1]
					try {
						if (teacher.getName().equals(((String[]) msg)[1])) {
							System.out.println("ECHOSERVER : INC OR DEC");
							teacher.sendToClient(msg);//messageInc,examinaning teacher ID
						}
					} catch (Exception ex) {
					}
				}
				return;
			default:
				break;
			}
		}
		// default for any other message
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
