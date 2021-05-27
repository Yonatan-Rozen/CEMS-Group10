package gui.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.PasswordField;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class ChangePasswordController implements Initializable{
	public static ChangePasswordController changePasswordController;
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
    
    
    private static PasswordField currentPasswordPf;
    private static PasswordField newPasswordPf;
    private static PasswordField reNewPasswordPf;
    private static Button confirmChangeBtn;
    private static Button cancelBtn;
    
    private static Stage stage;
    
    private static AlertType type;
    private static String msg;
    private static String title;
    
	public void start() throws IOException{
		changePasswordController = this;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/ChangePassword.fxml"));
		Scene scene = new Scene(root);
		stage = new Stage();
		stage.setScene(scene);
		stage.setResizable(false);
		stage.show();
	}
	
	
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		currentPasswordPf = sbCurrentPasswordPf;
	    newPasswordPf = sbNewPasswordPf;
	    reNewPasswordPf = sbReNewPasswordPf;
	    confirmChangeBtn = sbConfirmChangeBtn;
	    cancelBtn = sbCancelBtn;
	}

    @FXML
    void btnPressCancel(ActionEvent event) {
    	stage.close();
    }

    @FXML
    void btnPressConfirmChange(ActionEvent event) {
    	
    	ClientUI.chat.accept(new String[] {"btnPressConfirmChange" ,ChatClient.user.getUsername(), ChatClient.user.getPassword(), currentPasswordPf.getText(), newPasswordPf.getText(), reNewPasswordPf.getText()});

    	Alert alert = new Alert(type);
    	alert.initStyle(StageStyle.UTILITY);
		alert.setTitle(title);
		alert.setHeaderText(null);
		alert.setContentText(msg);
		alert.showAndWait();
    }
    
    // only server get to this methods through ChatClient's handleMessageFromServer
    public void badChangePassword(String Msg) {
    	System.out.println("badChangePassword");
    	msg = Msg;
    	title = "Error message";
    	type = AlertType.ERROR;
    }
    
    public void succesfullChangePassword(String Msg) {
    	System.out.println("succesfullChangePassword");
    	msg = Msg;
    	title = "Password changed";
    	type = AlertType.INFORMATION;
    	
    	ChatClient.user.setPassword(newPasswordPf.getText());
    	System.out.println(ChatClient.user.getPassword());
    }
}
