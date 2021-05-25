package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TeacherCreateExamController implements Initializable {

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private ComboBox<String> sbChooseBankCb;

	@FXML
	private Button sbContinue1Btn;

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private ComboBox<String> sbChooseCourseBtn;

	@FXML
	private TableView<?> sbAvailableQuestionsTv;

	@FXML
	private TableColumn<?, ?> sbQuestionID1Tc;

	@FXML
	private TableColumn<?, ?> sbPreview1Tc;

	@FXML
	private TableColumn<?, ?> sbAddToExamTc;

	@FXML
	private TableView<?> sbCurrentQuestionsTable;

	@FXML
	private TableColumn<?, ?> sbQuestionID2Tc;

	@FXML
	private TableColumn<?, ?> sbPreview2Tc;

	@FXML
	private TableColumn<?, ?> sbRemoveFromExamTc;

	@FXML
	private Button sbChangeBankBtn;

	@FXML
	private Button sbContinue2Btn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ComboBox<String> chooseBankCb;
	private static Button continue1Btn;
	private static AnchorPane botPanelAp;
	private static ComboBox<String> chooseCourseBtn;
	private static TableView<?> availableQuestionsTv;
	private static TableColumn<?, ?> questionID1Tc;
	private static TableColumn<?, ?> preview1Tc;
	private static TableColumn<?, ?> addToExamTc;
	private static TableView<?> currentQuestionsTable;
	private static TableColumn<?, ?> questionID2Tc;
	private static TableColumn<?, ?> preview2Tc;
	private static TableColumn<?, ?> removeFromExamTc;
	private static Button changeBankBtn;
	private static Button continue2Btn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		topPanelAp = sbTopPanelAp;
		chooseBankCb = sbChooseBankCb;
		continue1Btn = sbContinue1Btn;
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		chooseCourseBtn = sbChooseCourseBtn;
		availableQuestionsTv = sbAvailableQuestionsTv;
		questionID1Tc = sbQuestionID1Tc;
		preview1Tc = sbPreview1Tc;
		addToExamTc = sbAddToExamTc;
		currentQuestionsTable = sbCurrentQuestionsTable;
		questionID2Tc = sbQuestionID2Tc;
		preview2Tc = sbPreview2Tc;
		removeFromExamTc = sbRemoveFromExamTc;
		changeBankBtn = sbChangeBankBtn;
		continue2Btn = sbContinue2Btn;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressCancelCreation(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressCancelCreation");

	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressChangeBank");
		sbTopPanelAp.setDisable(false);
		sbBotPanelAp.setDisable(true);
	}

	@FXML
	void btnPressChooseCourse(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressChooseCourse");
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressContinue1");
		sbTopPanelAp.setDisable(true);
		sbBotPanelAp.setDisable(false);
	}

	@FXML
	void btnPressContinue2(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressContinue2");
	}

	@FXML
	void btnPressEditExams(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressEditExams");
	}

	@FXML
	void btnPressPreviewExam1(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressPreviewExam1");
	}

	@FXML
	void btnPressPreviewExam2(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressPreviewExam2");
	}

	@FXML
	void cbPressChooseBank(ActionEvent event) {
		System.out.println("TeacherCreateExam::cbPressChooseBank");
	}
}
