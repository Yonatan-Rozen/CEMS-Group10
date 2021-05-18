package gui.client;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import gui.client.principle.PrincipleMenuController;
import gui.client.student.StudentMenuController;
import gui.client.teacher.TeacherMenuController;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class SignInController implements Initializable {
	public static SignInController signInController;
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
	 * Opens SignIn.fxml
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		signInController = this;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml"));
		ClientUI.mainScene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui/client/SignIn.css").toExternalForm());
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Client)");
		ClientUI.mainStage.setScene(ClientUI.mainScene);
		ClientUI.mainStage.setResizable(false);
		ClientUI.mainStage.setOnCloseRequest(e -> {
			ClientUI.mainStage.hide();
			Alert alert = new Alert(AlertType.INFORMATION);
			alert.initStyle(StageStyle.UTILITY);
			alert.setTitle("Client window closed");
			alert.setHeaderText("You have been disconnected");
			alert.setContentText("Press ok to continue.");
			alert.showAndWait();
			System.exit(0);
		});
		ClientUI.mainStage.show();
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
		
		
//		// principle example : **********************************************
//		PrincipleMenuController pmC = new PrincipleMenuController();
//		try {
//			pmC.start();
//		} catch (Exception e) {
//		}
		
		
//		// student example : ************************************************
//		StudentMenuController smC = new StudentMenuController();
//		try {
//			smC.start();
//		} catch (Exception e) {
//		}

		
//		// teacher example : ************************************************
//		TeacherMenuController tmC = new TeacherMenuController();
//		try {
//			tmC.start();
//		} catch (Exception e) {
//		}
	}
}
