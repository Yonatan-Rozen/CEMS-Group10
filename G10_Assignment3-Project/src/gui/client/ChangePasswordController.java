package gui.client;

import java.io.IOException;
import java.net.URL;
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
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;

public class ChangePasswordController implements Initializable{
	
	// JAVAFX INSTNCES ******************************************************
	public static ChangePasswordController cpController;
    @FXML
    private PasswordField sbCurrentPasswordPf;

    @FXML
    private PasswordField sbNewPasswordPf;

    @FXML
    private PasswordField sbReNewPasswordPf;

    @FXML
    private Button sbConfirmChangeBtn;

    @FXML
    private Button sbCancelBtn;
    
    // JAVAFX STATIC INSTNCES ***********************************************
    private static PasswordField currentPasswordPf;
    private static PasswordField newPasswordPf;
    private static PasswordField reNewPasswordPf;
    private static Stage stage;
    
    // STATIC INSTANCES *****************************************************
    private static AlertType type;
    private static String msg;
    private static String title;
    
	// START METHOD *********************************************************
	/**
	 * Opens ChangePasswordController.fxml
	 * 
	 * @throws Exception
	 */
	public void start() throws IOException{
		cpController = this;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/ChangePassword.fxml"));
		Scene scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentPasswordPf = sbCurrentPasswordPf;
	    newPasswordPf = sbNewPasswordPf;
	    reNewPasswordPf = sbReNewPasswordPf;
	}

    @FXML
    void btnPressCancel(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void btnPressConfirmChange(ActionEvent event) {
    	
    	ClientUI.chat.accept(new String[] {"btnPressConfirmChange" ,ChatClient.user.getUsername(), ChatClient.user.getPassword(), currentPasswordPf.getText(), newPasswordPf.getText(), reNewPasswordPf.getText()});
    	CommonMethodsHandler.getInstance().getNewAlert(type, title, msg).showAndWait();
    }
    
    // EXTERNAL USE METHODS **************************************************
    public void badChangePassword(String Msg) {
    	System.out.println("badChangePassword");
    	msg = Msg;
    	title = "Error message";
    	type = AlertType.ERROR;
    }
    
    public void successfulChangePassword(String Msg) {
    	System.out.println("succesfullChangePassword");
    	msg = Msg;
    	title = "Password changed";
    	type = AlertType.INFORMATION;
    	
    	// save the new password in ChatClient.user
    	ChatClient.user.setPassword(newPasswordPf.getText());
    }
}
