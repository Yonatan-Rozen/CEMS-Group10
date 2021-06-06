package client;

import gui.client.SignInController;
import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class ClientUI extends Application {
	public static ClientController chat;
	public static Stage mainStage;
	public static Scene mainScene;
	public static void main(String args[]) throws Exception {
		launch(args);
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		chat = new ClientController("localhost", 5555); 
		chat.accept("checking if server is up...");
		mainStage = primaryStage;
		SignInController siC = new SignInController();
		siC.start();
	}
}