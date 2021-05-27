package gui.client;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class UserSettingsController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Hyperlink sbChangePassLnk;

	@FXML
	private Label sbUsernameLbl;

	@FXML
	private Label sbFullnameLbl;

	@FXML
	private Label sbPhoneLbl;

	@FXML
	private Label sbEmailLbl;

	@FXML
	private Label sbAccountTypeLbl;
	
	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink changePassLnk;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		changePassLnk = sbChangePassLnk;
		sbUsernameLbl.setText(ChatClient.user.getUsername());
		sbFullnameLbl.setText(ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname());
		sbPhoneLbl.setText(ChatClient.user.getPhonenumber());
		sbEmailLbl.setText(ChatClient.user.getEmail());
		sbAccountTypeLbl.setText(ChatClient.user.getType());
	}

	// ACTION METHODS *******************************************************
	@FXML
	void lnkPressChangePass(ActionEvent event) throws IOException  {
		ChangePasswordController cpc = new ChangePasswordController();
		cpc.start();
	}

}
