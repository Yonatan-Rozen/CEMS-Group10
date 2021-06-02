package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class TeacherMenuController implements Initializable {
	public static TeacherMenuBarController tmbController;
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
	private static Label welcomeLbl;
	private static Button startExamBtn;
	private static Button createQuestionBtn;
	private static Button editQuestionBtn;
	private static Button createExamBtn;
	private static Button editExamBtn;
	private static Button viewReportBtn;
	private static Button settingsBtn;

	// START METHOD *********************************************************
	/**
	 * Opens PrincipleMenu.fxml
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
		// scene.getStylesheets().add(getClass().getResource("/gui/client/teacher/TeacherMenu.css").toExternalForm());
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.setWidth(1150);
		ClientUI.mainStage.setHeight(650);
		ClientUI.mainStage.setTitle("Computerized Exam Management System (Teacher)");
		teacherNameLnk = sbTeacherNameLnk;
		teacherNameLnk.setText(ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname());
		logoutLnk = sbLogoutLnk;
		welcomeLbl = sbWelcomeLbl;
		welcomeLbl.setText("Welcome, " + ChatClient.user.getFirstname());
		startExamBtn = sbStartExamBtn;
		createQuestionBtn = sbCreateQuestionBtn;
		editQuestionBtn = sbEditQuestionBtn;
		createExamBtn = sbCreateExamBtn;
		editExamBtn = sbEditExamBtn;
		viewReportBtn = sbViewReportBtn;
		settingsBtn = sbSettingsBtn;
		tmbController = new TeacherMenuBarController();
		ClientUI.mainStage.show();
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void lnkPressLogout(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::LnkLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}

	@FXML
	public void btnPressCreateExam(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressCreateExam");
		tmbController.start();
		tmbController.btnPressCreateExam(event);
	}

	@FXML
	public void btnPressCreateQuestion(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressCreateQuestion");
		tmbController.start();
		tmbController.btnPressCreateQuestion(event);
	}

	@FXML
	public void btnPressEditExam(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressEditExam");
		tmbController.start();
		tmbController.btnPressEditExam(event);
	}

	@FXML
	public void btnPressEditQuestion(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressEditQuestion");
		tmbController.start();
		tmbController.btnPressEditQuestion(event);
	}

	@FXML
	public void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressSettings/lnkTeacherName");
		tmbController.start();
		tmbController.btnPressSettings(event);
	}

	@FXML
	public void btnPressStartExam(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressStartExam");
		tmbController.start();
		tmbController.btnPressStartExam(event);
	}

	@FXML
	public void btnPressViewReport(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressViewReport");
		tmbController.start();
		tmbController.btnPressViewReports(event);
	}
}