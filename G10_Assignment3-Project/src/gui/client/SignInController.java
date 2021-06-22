package gui.client;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyEvent;

public class SignInController implements Initializable {
	public static SignInController siController;
	private static String errorMsg;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TextField sbUsernameTf;

	@FXML
	private PasswordField sbPasswordPf;

	@FXML
	private ImageView sblogoImg;

	@FXML
	private Button sbSignInBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextField usernameTf;
	private static PasswordField passwordPf;
	private static ImageView logoimg;
	// START METHOD *********************************************************
	/**
	 * Opens SignIn.fxml
	 *
	 * @throws Exception
	 */
	public void start() throws Exception {
		siController = this;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml"));
		ClientUI.mainScene = new Scene(root);
		ClientUI.mainScene.getStylesheets().add(getClass().getResource("/common/Client.css").toExternalForm());
		ClientUI.mainStage.setScene(ClientUI.mainScene);
		ClientUI.mainStage.setResizable(false);
		ClientUI.mainStage.getIcons().add(CommonMethodsHandler.CEMS_ICON);

		ClientUI.mainStage.setOnCloseRequest(event -> {

			ButtonType buttonYes = new ButtonType("Yes");
			ButtonType buttonCancel = new ButtonType("Cancel");
			Optional<ButtonType> result = CommonMethodsHandler.getInstance().getNewAlert(AlertType.CONFIRMATION, "Closing client window",
					"You are about to close the window and disconnect!", "Are you sure you want to proceed?", buttonYes,
					buttonCancel).showAndWait();

			if (result.get() == buttonYes) {
				ClientUI.mainStage.hide();
				try{ClientUI.chat.accept(new String[] {"Disconnect",ChatClient.user.getUsername()});
				}catch(NullPointerException e) {System.out.println("client has logged out and then closed the window...");};

				CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Client window closed", "You have been disconnected from the server",
						"Press ok to continue").showAndWait();
				System.exit(0);
			}
			event.consume(); // cancels the execution of closing the server
		});

	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.hide();
		ClientUI.mainStage.setTitle("Computerized Exam Management System (Client)");
		ClientUI.mainStage.setWidth(600);
		ClientUI.mainStage.setHeight(400);
		try {
			ClientUI.chat.accept(new String[] { "Disconnect", ChatClient.user.getUsername() });
		} catch (NullPointerException e) {
			System.out.println("> client window is now active.\n> waiting for user input...");
		}

		ChatClient.user = null;
		usernameTf = sbUsernameTf;
		passwordPf = sbPasswordPf;
		logoimg = sblogoImg;
		logoimg.setImage(new Image("/logo.png"));
		ClientUI.mainStage.show();
		sbSignInBtn.setStyle("-fx-background-color: transparent;");

		ImageView view = new ImageView(new Image("/icon_signIn.png"));
		view.setFitHeight(40);
		view.setPreserveRatio(true);
		sbSignInBtn.setGraphic(view);
	}

	// ACTION METHOD *****************************************************

	@FXML
	public void enterPressSignIn(KeyEvent e) {

		if (e.getCode().toString().equals("ENTER"))
			btnPressSignIn(new ActionEvent());
	}

	@FXML
	public void btnPressSignIn(ActionEvent event) {
		String username = usernameTf.getText();
		String password =  passwordPf.getText();

		Object[] alertDetails = checkEmptyInput(username, password);
		
		if (alertDetails != null)
			CommonMethodsHandler.getInstance().getNewAlert((AlertType)alertDetails[0],(String)alertDetails[1],(String)alertDetails[2]).showAndWait();
		
		else {
			// sets ChatClient.user
			ClientUI.chat.accept(new String[] { "btnPressSignIn", usernameTf.getText(), passwordPf.getText() });
			try {
				switch (ChatClient.user.getType()) {
				case "Principle":
					ClientUI.mainStage.hide();
					ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenu.fxml")));
					break;
				case "Student":
					ClientUI.mainStage.hide();
					ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
					break;
				case "Teacher":
					ClientUI.mainStage.hide();
					ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
					break;
				default:
					System.out.println("error! this type doesn't exist");
					break;
				}
			} catch (NullPointerException | IOException e) {

				if (e instanceof IOException)
					System.out.println("could not load fxml");
				else
					CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", errorMsg).showAndWait();
			}
		}
	}

	public Object[] checkEmptyInput(String username, String password) {
		if (username.isEmpty() || password.isEmpty())
			return new Object[] {AlertType.ERROR, "Error message", "All fields are required!"};
		return null;
	}

	public void setErrorMsg(String errorMsg) {
		SignInController.errorMsg = errorMsg;
	}

}
