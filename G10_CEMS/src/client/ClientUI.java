package client;

import gui.ClientMenuController;
import javafx.application.Application;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat; // only one instance

	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		chat = new ClientController("localhost", 5555); 
		chat.accept("client connected successfully");
		ClientMenuController cmc = new ClientMenuController();
		cmc.start(primaryStage);
	}
}
