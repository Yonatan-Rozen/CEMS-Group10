package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Background;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

public class TeacherMenuController implements Initializable {
	public static TeacherMenuController tmController;
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
	private Button sbComputerizedResultsBtn;

	@FXML
	private Button sbSettingsBtn;

	@FXML
	private ImageView sbTeacherBgImg;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink teacherNameLnk;
	private static Label welcomeLbl;
	private static ImageView teacherBgImg;

	// STATIC INSTANCES *****************************************************
	private CommonMethodsHandler commonmeMethodsHandler = CommonMethodsHandler.getInstance();
	protected static List<String> choiceBoxesList = new ArrayList<>();
	protected static boolean choiceBoxRequested;
	protected static boolean setByMenu;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tmController = new TeacherMenuController();
		ClientUI.mainStage.setWidth(1150);
		ClientUI.mainStage.setHeight(650);
		ClientUI.mainStage.setTitle("Computerized Exam Management System (Teacher)");
		teacherNameLnk = sbTeacherNameLnk;
		teacherNameLnk.setText(ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname());
		welcomeLbl = sbWelcomeLbl;
		welcomeLbl.setText("Welcome, " + ChatClient.user.getFirstname());
		ClientUI.mainStage.show();
		teacherBgImg = sbTeacherBgImg;
<<<<<<< HEAD
		teacherBgImg.setImage(new Image("/img_bgTeacher2.png"));
		teacherBgImg.setPreserveRatio(false);
		teacherBgImg.setFitHeight(620);


=======
		teacherBgImg.setImage(new Image("/img_bgTeacher2.png"));
>>>>>>> branch 'master' of https://github.com/DeathSource/Group10.git
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
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
		TeacherMenuBarController.tmbController.btnPressCreateExam(event);
	}

	@FXML
	public void btnPressCreateQuestion(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressCreateQuestion");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
		TeacherMenuBarController.tmbController.btnPressCreateQuestion(event);
	}

	@FXML
	public void btnPressEditExam(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressEditExam");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
		TeacherMenuBarController.tmbController.btnPressEditExam(event);
	}

	@FXML
	public void btnPressEditQuestion(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressEditQuestion");

		String[] request = new String[] { "GetExistingBanks", ChatClient.user.getUsername() };
		Alert alert = commonmeMethodsHandler.getNewAlert(AlertType.WARNING, "Missing questions",
				"Please note that you should create a question first!");
		choiceBoxesList = commonmeMethodsHandler.getListRequest(request, alert);

		if (choiceBoxesList != null) {
			choiceBoxRequested = true;
			ClientUI.mainScene
					.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
			TeacherMenuBarController.tmbController.btnPressEditQuestion(event);
			TeacherChooseEditQuestionController.tceqController.setSubjectChoiceBox(choiceBoxesList);
		}
	}

	@FXML
	public void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressSettings/lnkTeacherName");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
		TeacherMenuBarController.tmbController.btnPressSettings(event);
	}

	@FXML
	public void btnPressStartExam(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressStartExam");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
		TeacherMenuBarController.tmbController.btnPressStartExam(event);
	}

	@FXML
	public void btnPressViewReport(ActionEvent event) throws Exception {
		System.out.println("TeacherMenu::btnPressViewReport");

		String[] request = new String[] { "GetCourses", ChatClient.user.getUsername(), "T" };
		Alert alert = commonmeMethodsHandler.getNewAlert(AlertType.INFORMATION, "Missing info",
				"There are no exams results yet!");
		choiceBoxesList = commonmeMethodsHandler.getListRequest(request, alert);

		if (choiceBoxesList != null) {
			setByMenu = true;
			ClientUI.mainScene
					.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
			TeacherMenuBarController.tmbController.btnPressViewReports(event);
		}
	}
	
	@FXML
	public void btnPressComputerizedResults(ActionEvent event) throws IOException {
		System.out.println("TeacherMenu::btnPressComputerizedResults");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenuBar.fxml")));
		TeacherMenuBarController.tmbController.btnPressComputerizedResults(event);
	}
}