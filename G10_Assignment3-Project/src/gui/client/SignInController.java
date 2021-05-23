package gui.client;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
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
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.stage.StageStyle;

public class SignInController implements Initializable {
	public static SignInController signInController;
	private static String errorMsg;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TextField sbUsernameTf;

	@FXML
	private PasswordField sbPasswordPf;

	@FXML
	private Label sbMessagelbl;

	@FXML
	private Button sbSignInBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextField usernameTf;
	private static PasswordField passwordPf;
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
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Client)");
		ClientUI.mainStage.setWidth(600);
		ChatClient.user = null;
		usernameTf = sbUsernameTf;
		passwordPf = sbPasswordPf;
		messagelbl = sbMessagelbl;
		signInBtn = sbSignInBtn;
	}

	// signInBtn METHOD *****************************************************
	public void btnPressSignIn(ActionEvent event) {
		ClientUI.chat.accept(new String[] {"btnPressSignIn",usernameTf.getText(),passwordPf.getText()}); // sets ChatClient.user
		try {
			switch(ChatClient.user.getType())
			{
			case "Principle":
				// principle example : **********************************************
				PrincipleMenuController pmC = new PrincipleMenuController();
				try {
					pmC.start();
				} catch (Exception e) {
				}
				break;
			case "Student":
				// student example : ************************************************
				StudentMenuController smC = new StudentMenuController();
				try {
					smC.start();
				} catch (Exception e) {
				}
				break;
			case "Teacher":
				// teacher example : ************************************************
				TeacherMenuController tmC = new TeacherMenuController();
				try {
					tmC.start();
				} catch (Exception e) {
				}
				break;
			default:
				System.out.println("error! this type doesn't exist");
				break;
			}
		}catch(NullPointerException e) {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("Error message");
		alert.setHeaderText(null);
		alert.setContentText(errorMsg);
		alert.showAndWait();}
	}

	public void setErrorMsg(String errorMsg) {
		SignInController.errorMsg = errorMsg;
	}
	
	
}
