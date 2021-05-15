package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class TeacherMenuBarController implements Initializable{
	
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Button sbStartExamBtn;

	@FXML
	private Button sbCreateQuestionBtn;

	@FXML
	private Button sbEditQuestionBtn;

	@FXML
	private Button sbCreateExamBtn;

	@FXML
	private Button sbEditExamBtn;

	@FXML
	private Button sbViewReportsBtn;

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
	
	// STATIC JAVAFX INSTANCES **********************************************
	private static Button startExamBtn;
	private static Button createQuestionBtn;
	private static Button editQuestionBtn;
	private static Button createExamBtn;
	private static Button editExamBtn;
	private static Button viewReportsBtn;
	private static Button settingsBtn;
	private static Hyperlink logoutLnk;
	private static ImageView logoIv;
	private static Button backBtn;
	private static BorderPane mainPaneBp;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startExamBtn = sbStartExamBtn;
		createQuestionBtn = sbCreateQuestionBtn;
		editQuestionBtn = sbEditQuestionBtn;
		createExamBtn = sbCreateExamBtn;
		editExamBtn = sbEditExamBtn;
		viewReportsBtn = sbViewReportsBtn;
		settingsBtn =sbSettingsBtn;
		logoutLnk = sbLogoutLnk;
		logoIv = sbLogoIv;
		backBtn = sbBackBtn;
		mainPaneBp = sbMainPaneBp;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressBack(ActionEvent event) {
		// TODO
	}

	@FXML
	void btnPressCreateExam(ActionEvent event) {
		// TODO
	}

	@FXML
	void btnPressCreateQuestion(ActionEvent event) {
		// TODO
	}

	@FXML
	void btnPressEditExam(ActionEvent event) {
		// TODO
	}

	@FXML
	void btnPressEditQuestion(ActionEvent event) {
		// TODO
	}

	@FXML
	void btnPressSettings(ActionEvent event) {
		// TODO
	}

	@FXML
	void btnPressStartExam(ActionEvent event) {
		// TODO
	}

	@FXML
	void btnPressViewReports(ActionEvent event) {
		// TODO
	}

	@FXML
	void lnkPressLogout(ActionEvent event) {
		// TODO
	}
}
