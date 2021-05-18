package gui.client.teacher;

import java.awt.Button;
import java.awt.Label;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Hyperlink;

public class TeacherMenuController implements Initializable {

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
	private Button sbCrreateExamBtn;

	@FXML
	private Button sbEditExamBtn;

	@FXML
	private Button sbViewReportBtn;

	@FXML
	private Button sbSettingsBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink TeacherNameLnk;
	private static Hyperlink LogoutLnk;
	private static Button StartExamBtn;
	private static Button CreateQuestionBtn;
	private static Button EditQuestionBtn;
	private static Button CrreateExamBtn;
	private static Button EditExamBtn;
	private static Button ViewReportBtn;
	private static Button SettingsBtn;

	// CONTROLLER INSTANCES
	public static TeacherMenuBarController tmbController;
	public Scene secscene;
	public void start() throws Exception {
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (teacher)");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
		secscene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		TeacherNameLnk = sbTeacherNameLnk;
		LogoutLnk = sbLogoutLnk;
		StartExamBtn = sbStartExamBtn;
		CreateQuestionBtn = sbCreateQuestionBtn;
		EditQuestionBtn = sbEditQuestionBtn;
		CrreateExamBtn = sbCrreateExamBtn;
		EditExamBtn = sbEditExamBtn;
		ViewReportBtn = sbViewReportBtn;
		SettingsBtn = sbSettingsBtn;
		tmbController = new TeacherMenuBarController();
	}

	@FXML
	void LnkLogout(ActionEvent event) {

	}

	@FXML
	void LnkTeacherName(ActionEvent event) {

	}

	@FXML
	void btnCreateQuestion(ActionEvent event) throws Exception {
		System.out.println("btnCreateQuestion");
		tmbController.start();
		tmbController.btnPressCreateQuestion(event);
	}

	@FXML
	void btnCrreateExam(ActionEvent event) throws Exception {
		System.out.println("btnCrreateExam");
		tmbController.start();
		tmbController.btnPressCreateExam(event);
	}

	@FXML
	void btnEditExam(ActionEvent event) throws Exception {
		System.out.println("btnEditExam");
		tmbController.start();
		tmbController.btnPressEditExam(event);
	}

	@FXML
	void btnEditQuestion(ActionEvent event) throws Exception {
		System.out.println("btnEditQuestion");
		tmbController.start();
		tmbController.btnPressEditQuestion(event);
	}

	@FXML
	void btnSettings(ActionEvent event) throws Exception {
		System.out.println("btnSettings");
		tmbController.start();
		tmbController.btnPressSettings(event);
	}

//	@FXML
//	void btnStartExam(ActionEvent event) throws Exception {
//		System.out.println("btnStartExam");
//		tmbController.start();
//		tmbController.btnPressViewRequests(event);
//	}

	@FXML
	void btnViewReport(ActionEvent event) throws Exception {
		System.out.println("btnViewReport");
		tmbController.start();
		tmbController.btnPressViewReports(event);
	}
}