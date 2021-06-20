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
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * A controller that controls the buttons of the teacher menu side bar
 * @author Yonatan Rozen
 */
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
	private Button sbCheckExamResultsBtn;

	@FXML
	private Hyperlink sbLogoutLnk;

	@FXML
	private ImageView sbLogoIv;

	@FXML
	private Button sbBackBtn;

	@FXML
	private BorderPane sbMainPaneBp;
	
    @FXML
    private ImageView sbMenuBg;

	// STATIC JAVAFX INSTANCES **********************************************
	public static AnchorPane menuBarAp;
	private static Button startExamBtn;
	private static Button createQuestionBtn;
	private static Button editQuestionBtn;
	private static Button createExamBtn;
	private static Button editExamBtn;
	private static Button viewReportsBtn;
	private static Button checkExamResultsBtn;
	private static Button settingsBtn;
	protected static BorderPane mainPaneBp;
	private static ImageView menuBg;
	private static Button backBtn;
	
	// STATIC INSTANCES *****************************************************
	private CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();
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
		checkExamResultsBtn = sbCheckExamResultsBtn;
		settingsBtn = sbSettingsBtn;
		backBtn = sbBackBtn;
		backBtn.getStyleClass().clear();
		backBtn.getStyleClass().add("backToMenu-button");
		mainPaneBp = sbMainPaneBp;
		menuBg = sbMenuBg;
		menuBg.setImage(new Image("/menubar_bg.png"));
		menuBg.setFitHeight(600);
		menuBg.setFitWidth(230);

		startExamBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		createQuestionBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		editQuestionBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		createExamBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		editExamBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		viewReportsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");
		checkExamResultsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");
		settingsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		
		sbLogoIv.setImage(CommonMethodsHandler.CEMS_LOGO);
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressCreateExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressCreateExam");
		cmh.fadeInAndOut(mainPaneBp, "teacher", "TeacherExamType");
		currentBtn = cmh.disablePropertySwapper(currentBtn, createExamBtn);
		
	}

	@FXML
	public void btnPressCreateQuestion(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressCreateQuestion");
		cmh.fadeInAndOut(mainPaneBp, "teacher", "TeacherCreateQuestion");
		currentBtn = cmh.disablePropertySwapper(currentBtn, createQuestionBtn);
	}

	@FXML
	public void btnPressEditExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressEditExam");
		cmh.fadeInAndOut(mainPaneBp, "teacher", "TeacherEditExam");
		currentBtn = cmh.disablePropertySwapper(currentBtn, editExamBtn);
	}

	@FXML
	public void btnPressEditQuestion(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::btnPressEditQuestion");

		if (!TeacherMenuController.choiceBoxRequested) {
			String[] request = new String[]{"GetExistingBanks", ChatClient.user.getUsername()};
			Alert alert = cmh.getNewAlert(AlertType.WARNING, "Missing questions", "Please note that you should create a question first!");
			TeacherMenuController.choiceBoxesList = cmh.getListRequest(request, alert);
		}

		if (TeacherMenuController.choiceBoxesList != null) {
			cmh.fadeInAndOut(mainPaneBp, "teacher", "TeacherChooseEditQuestion");
			TeacherChooseEditQuestionController.tceqController.setSubjectChoiceBox(TeacherMenuController.choiceBoxesList);
			TeacherMenuController.choiceBoxRequested = false;

			currentBtn = cmh.disablePropertySwapper(currentBtn, editQuestionBtn);
		}
	}

	@FXML
	public void btnPressStartExam(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressStartExam");
		cmh.fadeInAndOut(mainPaneBp, "teacher", "TeacherStartExam");
		currentBtn = cmh.disablePropertySwapper(currentBtn, startExamBtn);
	}

	@FXML
	public void btnPressViewReports(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::btnPressViewReports");

		if (!TeacherMenuController.setByMenu) {
			String[] request = new String[] { "GetCourses", ChatClient.user.getUsername(),"T"};
			Alert alert = cmh.getNewAlert(AlertType.INFORMATION, "Missing info", "There are no exams results yet!");
			TeacherMenuController.choiceBoxesList = cmh.getListRequest(request, alert);
			TeacherMenuController.setByMenu = false;
		}

		if (TeacherMenuController.choiceBoxesList != null) {
			cmh.fadeInAndOut(mainPaneBp, "teacher", "TeacherReports");
			TeacherReportsController.trController.setCoursesChoiseBox(TeacherMenuController.choiceBoxesList);
			currentBtn = cmh.disablePropertySwapper(currentBtn, viewReportsBtn);
			TeacherMenuController.setByMenu = false;
		}
	}

	
	@FXML
	public void btnPressCheckExamResults(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressComputerizedResults");
		cmh.fadeInAndOut(mainPaneBp, "teacher", "TeacherCheckExamResults");
		currentBtn = cmh.disablePropertySwapper(currentBtn, checkExamResultsBtn);
	}

	@FXML
	public void btnPressSettings(ActionEvent event) {
		System.out.println("TeacherMenuBar::btnPressSettings");
		cmh.fadeInAndOut(mainPaneBp, "client", "UserSettings");
		currentBtn = cmh.disablePropertySwapper(currentBtn, settingsBtn);
	}

	@FXML
	public void lnkPressLogout(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::lnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}


	@FXML
	public void btnPressBack(ActionEvent event) throws IOException {
		System.out.println("TeacherMenuBar::btnPressBack");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
	}
}
