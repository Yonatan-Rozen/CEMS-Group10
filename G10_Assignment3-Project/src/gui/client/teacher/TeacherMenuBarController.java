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
import javafx.scene.image.Image;
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
	protected static BorderPane mainPaneBp;

	public void start() throws Exception {
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
		sbLogoIv.setImage(new Image (getClass().getResourceAsStream("/logo.png")));
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressBack(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::btnPressBack");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
	}

	@FXML
	public void btnPressCreateExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressCreateExam");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateExam"));
	}

	@FXML
	public void btnPressCreateQuestion(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressCreateQuestion");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateQuestion"));
	}

	@FXML
	public void btnPressEditExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressEditExam");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditExam"));
	}

	@FXML
	public void btnPressEditQuestion(ActionEvent event){
		System.out.println("TeacherMenuBar::btnPressEditQuestion");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditQuestion"));
	}

	@FXML
	public void btnPressSettings(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressSettings");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("client", "UserSettings"));
	}

	@FXML
	public void btnPressStartExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressStartExam");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherStartExam"));
	}

	@FXML
	public void btnPressViewReports(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressViewReports");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherReports"));
	}

	@FXML
	public void lnkPressLogout(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::lnkPressLogout");
		TeacherCreateQuestionController.subjectList.clear();
		TeacherCreateQuestionController.subjectList.add("----------");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}
}
