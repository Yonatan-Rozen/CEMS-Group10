package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class TeacherMenuBarController implements Initializable {
	public static TeacherMenuBarController tmbController;
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private AnchorPane sbMenuBarAp;

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
	protected static AnchorPane menuBarAp;
	private static Button startExamBtn;
	private static Button createQuestionBtn;
	private static Button editQuestionBtn;
	private static Button createExamBtn;
	private static Button editExamBtn;
	private static Button viewReportsBtn;
	private static Button settingsBtn;
	protected static BorderPane mainPaneBp;

	// STATIC INSTANCES *****************************************************
	private CommonMethodsHandler commonmeMethodsHandler = CommonMethodsHandler.getInstance();
	private static Button currentBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tmbController = new TeacherMenuBarController();
		menuBarAp = sbMenuBarAp;
		startExamBtn = sbStartExamBtn;
		createQuestionBtn = sbCreateQuestionBtn;
		editQuestionBtn = sbEditQuestionBtn;
		createExamBtn = sbCreateExamBtn;
		editExamBtn = sbEditExamBtn;
		viewReportsBtn = sbViewReportsBtn;
		settingsBtn = sbSettingsBtn;
		mainPaneBp = sbMainPaneBp;
		sbLogoIv.setImage(CommonMethodsHandler.CEMS_LOGO);
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
		mainPaneBp.setCenter(commonmeMethodsHandler.getPane("teacher", "TeacherExamType"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, createExamBtn);
	}

	@FXML
	public void btnPressCreateQuestion(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressCreateQuestion");
		mainPaneBp.setCenter(commonmeMethodsHandler.getPane("teacher", "TeacherCreateQuestion"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, createQuestionBtn);
	}

	@FXML
	public void btnPressEditExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressEditExam");
		mainPaneBp.setCenter(commonmeMethodsHandler.getPane("teacher", "TeacherEditExam"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, editExamBtn);
	}

	@FXML
	public void btnPressEditQuestion(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressEditQuestion");
		mainPaneBp.setCenter(commonmeMethodsHandler.getPane("teacher", "TeacherChooseEditQuestion"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, editQuestionBtn);
	}

	@FXML
	public void btnPressSettings(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressSettings");
		mainPaneBp.setCenter(commonmeMethodsHandler.getPane("client", "UserSettings"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, settingsBtn);
	}

	@FXML
	public void btnPressStartExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressStartExam");
		mainPaneBp.setCenter(commonmeMethodsHandler.getPane("teacher", "TeacherStartExam"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, startExamBtn);
	}

	@FXML
	public void btnPressViewReports(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::btnPressViewReports");
		if (!TeacherMenuController.choiceBoxRequested) {
			String[] request = new String[] { "GetCourses", ChatClient.user.getUsername(),"T"};
			Alert alert = commonmeMethodsHandler.getNewAlert(AlertType.INFORMATION, "Missing info", "There are no exams results yet!");
			TeacherMenuController.choiceBoxesList = commonmeMethodsHandler.getListRequest(request, alert);
		}
		if (TeacherMenuController.choiceBoxesList != null) {
			TeacherMenuBarController.mainPaneBp.setCenter(commonmeMethodsHandler.getPane("teacher", "TeacherReports"));
			TeacherReportsController.trController.setCoursesCoiseBox(TeacherMenuController.choiceBoxesList);
			TeacherMenuController.choiceBoxRequested = false;
			
			currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, viewReportsBtn);
		}
	}

	@FXML
	public void lnkPressLogout(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::lnkPressLogout");
		TeacherCreateQuestionController.subjectList.clear();
		TeacherCreateQuestionController.subjectList.add("----------");
		TeacherChooseEditQuestionController.subjectList.clear();
		TeacherChooseEditQuestionController.subjectList.add("----------");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}
}
