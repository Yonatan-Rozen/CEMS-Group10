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
		if (msg instanceof Object[])
			ServerUI.serverConsole.println(">>> " + ((Object[]) msg)[0] + " from " + client);
		else
			ServerUI.serverConsole.println(">>> " + msg + " from " + client);
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
