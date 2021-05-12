package gui.server;

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
import javafx.stage.Stage;
import javafx.stage.StageStyle;
import server.ServerUI;

public class ServerConsoleController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TextField sbPortTf;

	@FXML
	private Button sbStartServerBtn;

	@FXML
	private TextArea sbConsoleTa;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextField portTf;
	private static Button startServerBtn;
	private static TextArea consoleTa;

	// START METHOD *********************************************************
	/**
	 * Opens ServerConsole.fxml
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/server/ServerConsole.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui.server/ServerConsole.css").toExternalForm());
		primaryStage.setTitle("CEMS - Computerized Exam Management System (Server)");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			primaryStage.hide();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Server shut down");
			alert.setHeaderText("The server has been shut down");
			alert.setContentText("Press ok to continue.");
			alert.showAndWait();
			System.exit(0);
		});
		primaryStage.show();
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		portTf = sbPortTf;
		startServerBtn = sbStartServerBtn;
		consoleTa = sbConsoleTa;
	}

	// startServerBtn METHOD ************************************************
	public void btnPressStartServer(ActionEvent event) {
		int portNumber;
		try {
			portNumber = getPort();
		} catch (NumberFormatException e) {
			Alert alert = new Alert(AlertType.ERROR);
			alert.setTitle("Wrong input");
			alert.setHeaderText("You must enter a port number!");
			alert.setContentText("Press ok to continue...");
			alert.showAndWait();
			return;
		}

		portTf.setDisable(true);
		startServerBtn.setDisable(true);
		ServerUI.startServer(portNumber);
	}

	// INSTANCE METHODS ****************************************************
	private int getPort() throws NumberFormatException {
		return Integer.parseInt(portTf.getText());
	}

	public void print(String info) {
		consoleTa.appendText(info);
	}

	public void println(String info) {
		consoleTa.appendText(info + "\n");
	}

}
