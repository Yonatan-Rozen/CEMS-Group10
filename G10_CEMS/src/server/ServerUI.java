package server;

import control.DBconnector;
import gui.ServerConsoleController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ServerUI extends Application {
	final public static int DEFAULT_PORT = 5555;
	public static ServerConsoleController scc;

	public static void main(String[] args) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		scc = new ServerConsoleController();
		scc.start(primaryStage);

		int port = 0; // Port to listen on

		try {
			port = DEFAULT_PORT; // Set port to 5555

		} catch (Throwable t) {
			scc.appendTextToConsole("ERROR - Could not connect!");
		}

		EchoServer sv = new EchoServer(port);

		try {
			sv.listen(); // Start listening for connections
		} catch (Exception ex) {
			System.out.println("ERROR - Could not listen for clients!"); 
		};
		
		DBconnector.connectToDB(); // Connect server to data base
	}
}
