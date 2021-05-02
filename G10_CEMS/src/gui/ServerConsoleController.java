package gui;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.ServerUI;

public class ServerConsoleController implements Initializable {

	// fxml variables

	@FXML
	private TextField txtFPort;

	@FXML
	private Button btnConnectServer;

	@FXML
	private TextArea txtAServerConsole;

	private static TextField portTxt;
	private static Button connectServerBtn;
	private static TextArea consoleTxt;

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
				System.exit(0);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		portTxt = txtFPort;
		connectServerBtn = btnConnectServer;
		consoleTxt = txtAServerConsole;
	}
	
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
