package gui;

import java.io.File;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import server.ServerUI;

public class ServerConsoleController implements Initializable {

	// FXML variables *********************************************************
	
	@FXML
	private ImageView imgView;
	
	@FXML
	private TextField txtFPort;

	@FXML
	private Button btnConnectServer;

	@FXML
	private TextArea txtAServerConsole;

	// static variables *******************************************************
	private static TextField portTxt;
	private static Button connectServerBtn;
	private static TextArea consoleTxt;
	
	// start method ***********************************************************
	public void start(Stage primaryStage) throws Exception {
		try {
			Parent root = FXMLLoader.load(getClass().getResource("/gui/ServerConsole.fxml"));
			Scene scene = new Scene(root);
			scene.getStylesheets().add(getClass().getResource("/gui/ServerConsole.css").toExternalForm());
			primaryStage.setTitle("CEMS - Server");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				primaryStage.hide();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Server shut down");
				alert.setHeaderText("Server has been shut down");
				alert.setContentText("Press ok to continue...");
				alert.showAndWait();
				System.exit(0);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// initialize method ******************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imgView.setImage(new Image (getClass().getResourceAsStream("/logo.png")));
		portTxt = txtFPort;
		connectServerBtn = btnConnectServer;
		consoleTxt = txtAServerConsole;
	}
	
	// start server connection
	public void getServerPort(ActionEvent event) throws Exception {
		int p;
		try {
			p = getPort();
		} catch(NumberFormatException e) {
			ServerUI.serverConsole.appendTextToConsole("You must enter a port number");
			return;
		}
		
		portTxt.setDisable(true);
		connectServerBtn.setDisable(true);
		ServerUI.startServer(p);
	}

	private int getPort() throws NumberFormatException{
		return Integer.parseInt(portTxt.getText());
	}

	public void appendTextToConsole(String text) {
		consoleTxt.appendText(text + "\n");
		// System.out.println(text);
	}
	
	

}
