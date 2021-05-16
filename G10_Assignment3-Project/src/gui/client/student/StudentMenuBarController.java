package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class StudentMenuBarController implements Initializable {

	@FXML
	private Button sbTakeExamBtn;

	@FXML
	private Button sbViewExamResultsBtn;

	@FXML
	private Button sbSettingsBtn;

	@FXML
	private Hyperlink sbLogoutLnk;

	@FXML
	private ImageView sbLogoIv;

	@FXML
	private Button sbBackBtn;

	@FXML
	private BorderPane sbMainPaneBp;

	private static Button takeExamBtn;
	private static Button viewExamResultsBtn;
	private static Button settingsBtn;
	private static Hyperlink logoutLnk;
	private static ImageView logoIv;
	private static Button backBtn;
	private static BorderPane mainPaneBp;
	//	private static int loaded = 0;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		takeExamBtn = sbTakeExamBtn;
		viewExamResultsBtn = sbViewExamResultsBtn;
		settingsBtn = sbSettingsBtn;
		logoutLnk = sbLogoutLnk;
		logoIv = sbLogoIv;
		backBtn = sbBackBtn;
		mainPaneBp = sbMainPaneBp;
	}

	@FXML
	void btnPressBack(ActionEvent event) {
		//TODO
	}

	@FXML
	void btnPressSettings(ActionEvent event) {
		//TODO go to settings of student
	}

	@FXML
	void btnPressTakeAnExam(ActionEvent event) {
		// TODO open StudentEnterCode.fxml
		System.out.println("BtnPressTakeAnExam");
		// uncomment if there are any methods that are used externally from the controller
		// StudentEnterCodeController pvreqC = new StudentEnterCodeController();
		//if (loaded != 4)
		//	{
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("StudentEnterCode"));
		//	loaded = 4;
		//	System.out.println(loaded);
		//	}
	}

	@FXML
	void btnPressViewExamResults(ActionEvent event) {
		//TODO go to student's exam results
	}

	@FXML
	void lnkPressLogout(ActionEvent event) {
		//TODO go to log in screen
	}

}
