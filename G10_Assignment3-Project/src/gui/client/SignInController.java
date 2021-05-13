package gui.client;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class SignInController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TextField sbUsernameTf;

	@FXML
	private TextField sbPasswordTf;
	
	@FXML
	private Label sbMessagelbl;

	@FXML
	private Button sbSignInBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextField usernameTf;
	private static TextField passwordTf;
	private static Label messagelbl;
	private static Button signInBtn;

	// START METHOD *********************************************************
	/**
	 * Opens ServerConsole.fxml
	 * 
	 * @param primaryStage
	 * @throws Exception
	 */
	public void start(Stage primaryStage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml"));
		Scene scene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui/client/SignIn.css").toExternalForm());
		primaryStage.setTitle("CEMS - Computerized Exam Management System (Client)");
		primaryStage.setScene(scene);
		primaryStage.setResizable(false);
		primaryStage.setOnCloseRequest(e -> {
			primaryStage.hide();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Client window closed");
			alert.setHeaderText("You have been disconnected");
			alert.setContentText("Press ok to continue.");
			alert.showAndWait();
			System.exit(0);
		});
		primaryStage.show();
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		usernameTf = sbUsernameTf;
		passwordTf = sbPasswordTf;
		messagelbl = sbMessagelbl;
		signInBtn = sbSignInBtn;
	}

	// signInBtn METHOD *****************************************************
	public void btnPressSignIn(ActionEvent event) {
		// TODO get info from text fields
		// TODO create USER object
		// TODO send the object to the server
		// TODO open the correct client window (student / teacher / principle)
	}
}
