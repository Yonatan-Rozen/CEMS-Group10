package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class TeacherMenuBarController implements Initializable {

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

	public void start() throws Exception {
		ClientUI.mainStage.setWidth(750);
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));

		// scene.getStylesheets().add(getClass().getResource("/gui/client/teacher/TeacherMenuBar.css").toExternalForm());
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startExamBtn = sbStartExamBtn;
		createQuestionBtn = sbCreateQuestionBtn;
		editQuestionBtn = sbEditQuestionBtn;
		createExamBtn = sbCreateExamBtn;
		editExamBtn = sbEditExamBtn;
		viewReportsBtn = sbViewReportsBtn;
		settingsBtn = sbSettingsBtn;
		logoutLnk = sbLogoutLnk;
		logoIv = sbLogoIv;
		backBtn = sbBackBtn;
		mainPaneBp = sbMainPaneBp;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressBack(ActionEvent event) throws IOException {
		System.out.println("btnPressBack");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
		ClientUI.mainStage.setWidth(600);

	}

	@FXML
	void btnPressCreateExam(ActionEvent event) throws IOException {
		System.out.println("btnPressCreateExam");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateExam"));
	}

	@FXML
	void btnPressCreateQuestion(ActionEvent event) throws IOException {
		System.out.println("btnPressCreateQuestion");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateQuestion"));
	}

	@FXML
	void btnPressEditExam(ActionEvent event) throws IOException {
		System.out.println("btnPressEditExam");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditExam"));
	}

	@FXML
	void btnPressEditQuestion(ActionEvent event) throws IOException {
		System.out.println("btnPressEditQuestion");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditQuestion"));
	}

	@FXML
	void btnPressSettings(ActionEvent event) throws IOException {
		System.out.println("btnPressSettings");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherSettings"));
	}

//	@FXML
//	void btnPressStartExam(ActionEvent event) throws IOException {
//
//	}

	@FXML
	void btnPressViewReports(ActionEvent event) throws IOException {
		System.out.println("btnPressViewReports");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherViewReports"));
	}

	@FXML
	void lnkPressLogout(ActionEvent event) throws IOException {
		System.out.println("lnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Client)");
		ClientUI.mainStage.setWidth(600);
	}
}
