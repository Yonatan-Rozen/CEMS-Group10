package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class TeacherMenuController implements Initializable {

	// JAVAFX INSTANCES *****************************************************
	@FXML
	private Hyperlink sbTeacherNameLnk;

	@FXML
	private Hyperlink sbLogoutLnk;

	@FXML
	private Label sbWelcomeLbl;

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
	private Button sbViewReportBtn;

	@FXML
	private Button sbSettingsBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink teacherNameLnk;
	private static Hyperlink logoutLnk;
	private static Button startExamBtn;
	private static Button createQuestionBtn;
	private static Button editQuestionBtn;
	private static Button createExamBtn;
	private static Button editExamBtn;
	private static Button viewReportBtn;
	private static Button settingsBtn;

	// CONTROLLER INSTANCES *************************************************
	protected static TeacherMenuBarController tmbController;

	public void start() throws Exception {
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Teacher)");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teacherNameLnk = sbTeacherNameLnk;
		logoutLnk = sbLogoutLnk;
		startExamBtn = sbStartExamBtn;
		createQuestionBtn = sbCreateQuestionBtn;
		editQuestionBtn = sbEditQuestionBtn;
		createExamBtn = sbCreateExamBtn;
		editExamBtn = sbEditExamBtn;
		viewReportBtn = sbViewReportBtn;
		settingsBtn = sbSettingsBtn;
		tmbController = new TeacherMenuBarController();
	}

	@FXML
	void LnkLogout(ActionEvent event) throws Exception {
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}

	@FXML
	void LnkTeacherName(ActionEvent event) throws Exception {
		System.out.println("LnkTeacherName");
		tmbController.start();
		tmbController.btnPressSettings(event);
	}

	@FXML
	void btnPressCreateExam(ActionEvent event) throws Exception {
		System.out.println("btnPressCreateExam");
		tmbController.start();
		tmbController.btnPressCreateExam(event);
	}

	@FXML
	void btnPressCreateQuestion(ActionEvent event) throws Exception {
		System.out.println("btnPressCreateQuestion");
		tmbController.start();
		tmbController.btnPressCreateQuestion(event);
	}

	@FXML
	void btnPressEditExam(ActionEvent event) throws Exception {
		System.out.println("btnPressEditExam");
		tmbController.start();
		tmbController.btnPressEditExam(event);
	}

	@FXML
	void btnPressEditQuestion(ActionEvent event) throws Exception {
		System.out.println("btnPressEditQuestion");
		tmbController.start();
		tmbController.btnPressEditQuestion(event);
	}

	@FXML
	void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("btnPressSettings");
		tmbController.start();
		tmbController.btnPressSettings(event);
	}

	@FXML
	void btnPressStartExam(ActionEvent event) throws Exception {
		System.out.println("btnPressStartExam");
		tmbController.start();
		tmbController.btnPressStartExam(event);
	}

	@FXML
	void btnPressViewReport(ActionEvent event) throws Exception {
		System.out.println("btnPressViewReport");
		tmbController.start();
		tmbController.btnPressViewReports(event);
	}
}