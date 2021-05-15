package gui.client.principle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class PrincipleSettings implements Initializable {
	
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
	private static Label usernameLbl;
	private static Label fullnameLbl;
	private static Label phoneLbl;
	private static Label emailLbl;
	private static Label accountTypeLbl;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		changePassLnk = sbChangePassLnk;
		usernameLbl = sbUsernameLbl;
		fullnameLbl = sbFullnameLbl;
		phoneLbl = sbPhoneLbl;
		emailLbl = sbEmailLbl;
		accountTypeLbl = sbAccountTypeLbl;

	}

	// ACTION METHODS *******************************************************
	@FXML
	void LnkPressChangePass(ActionEvent event) {
		// TODO open new window for changing password
	}
}
