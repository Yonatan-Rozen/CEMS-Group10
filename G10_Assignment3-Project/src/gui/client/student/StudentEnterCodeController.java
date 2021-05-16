package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class StudentEnterCodeController implements Initializable {
	/*
	 * @FXML private Button sbTakeExamBtn;
	 *
	 * @FXML private Button sbViewExamResultsBtn;
	 *
	 * @FXML private Button sbSettingsBtn;
	 *
	 * @FXML private Hyperlink sbLogoutLnk;
	 *
	 * @FXML private ImageView sbLogoIv;
	 *
	 * @FXML private Button sbBackBtn;
	 *
	 * @FXML private BorderPane sbMainPaneBp;
	 */

	@FXML
	private MenuButton sbStartExamBtn;

	@FXML
	private MenuItem sbStartExamManuallyBtn;

	@FXML
	private MenuItem sbStartExamComputerizedBtn;

	private static MenuButton startExamBtn;
	private static MenuItem startExamManuallyBtn;
	private static MenuItem startExamComputerizedBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startExamBtn = sbStartExamBtn;
		startExamManuallyBtn = sbStartExamManuallyBtn;
		startExamComputerizedBtn = startExamComputerizedBtn;
	}

	/*
	@FXML
	void btnPressBack(ActionEvent event) {

	}

	@FXML
	void btnPressSettings(ActionEvent event) {

	}
		@FXML
	void btnPressViewExamResults(ActionEvent event) {

	}

	@FXML
	void lnkPressLogout(ActionEvent event) {

	}
	 */

	@FXML
	void btnPressStartExamComputerized(ActionEvent event) {

	}

	@FXML
	void btnPressStartExamManually(ActionEvent event) {

	}


}
