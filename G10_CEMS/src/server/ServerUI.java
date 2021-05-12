package server;

import control.DBconnector;
import gui.ServerConsoleController;
import javafx.application.Application;
import javafx.stage.Stage;

public class ServerUI extends Application {
	//final public static int DEFAULT_PORT = 5555;
	public static ServerConsoleController serverConsole;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		serverConsole = new ServerConsoleController();
		serverConsole.start(primaryStage);
	}
	
	public static void startServer(int p) {
		int port = 0; // Port to listen on

		try {
			port = p; // 5555 is the default port
		} catch (Throwable t) {
			serverConsole.appendTextToConsole("ERROR - Could not connect!");
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			serverConsole.appendTextToConsole("ERROR - Could not listen for clients!"); 
		}
		
		DBconnector.getInstance().connectToDB(); // Connect server to data base
	}
}
