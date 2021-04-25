package gui;

import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;

public class ServerConsoleController {
	
	@FXML
	private TextArea txtServerConsole;
	// not to be called!
	
	private static TextArea console;
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerConsole.fxml"));
			Scene scene = new Scene(root);
			primaryStage.setTitle("CEMS - Server");
			primaryStage.setScene(scene);
			primaryStage.show();
		} catch(Exception e) {
			e.printStackTrace();
		}
	}
	
	@FXML
	public void initialize()
	{
		txtServerConsole.setEditable(false);
		console = txtServerConsole;
	}
	
	public void appendTextToConsole(String text)
	{
		console.appendText(text+"\n");
		//System.out.println(text);
	}
	
	
}
