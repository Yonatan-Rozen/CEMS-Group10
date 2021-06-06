package gui.server;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import server.EchoServer;
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
	 * @param mainStage
	 * @throws Exception
	 */
	public void start(Stage mainStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/server/ServerConsole.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui.server/ServerConsole.css").toExternalForm());
		mainStage.setTitle("Computerized Exam Management System (Server)");
		mainStage.setScene(scene);
		mainStage.setResizable(false);
		mainStage.getIcons().add(CommonMethodsHandler.CEMS_ICON);
		mainStage.setOnCloseRequest(event -> {
			mainStage.hide();
			CommonMethodsHandler methodsHandler = CommonMethodsHandler.getInstance();
			methodsHandler.getNewAlert(AlertType.INFORMATION, "Server shut down",
					"The server has been shut down\n(All client will be terminated shortly after...)","Press ok to continue.").showAndWait();
			EchoServer.es.sendToAllClients("TerminateClient");
			System.exit(0);
		});
		mainStage.show();
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		portTf = sbPortTf;
		startServerBtn = sbStartServerBtn;
		consoleTa = sbConsoleTa;
		btnPressStartServer(new ActionEvent()); // Automatic login

	}

	// ACTION METHOD ********************************************************
	public void btnPressStartServer(ActionEvent event) {
		int portNumber;
		try {
			portNumber = getPort();
		} catch (NumberFormatException e) {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Wrong input",
					"You must enter a port number!","Press ok to continue.").showAndWait();
			return;
		}

		setDisable(true);
		ServerUI.startServer(portNumber);
	}

	// INSTANCE METHODS ****************************************************
	private int getPort() throws NumberFormatException {
		return Integer.parseInt(portTf.getText());
	}

	public void setDisable(boolean value) {
		portTf.setDisable(value);
		startServerBtn.setDisable(value);
	}

	public void print(String info) {
		consoleTa.appendText(info);
	}

	public void println(String info) {
		consoleTa.appendText(info + "\n");
	}

}
