package server;

import gui.server.ServerConsoleController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application{
	
	public final static int DEFAULT_PORT = 5555;
	public static ServerConsoleController serverConsole;
	
	public static void main(String[] args) {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		serverConsole = new ServerConsoleController();
		serverConsole.start(primaryStage);
	}

	/**
	 * Starts listening for client connections by using 'EchoServer' which is
	 * a subclass of 'AbstractServer'.<br>Also starts the connection with the database
	 * @param portNumber the port to listen to connections from
	 */
	public static void startServer(int portNumber) {
		
		EchoServer sv = new EchoServer(portNumber);
		
		try {
			sv.listen();
		} catch(Exception ex) {
			serverConsole.println("ERROR - Could not listent for clients!");
		}
		
		DBconnector.getInstance().connectToDB();
	}
}
