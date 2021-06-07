package gui.client.teacher;

import javafx.scene.control.TextArea;
import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.layout.AnchorPane;
import logic.question.Question;

public class TeacherComputerizedExamDefinitionsController implements Initializable {
	public static TeacherComputerizedExamDefinitionsController tcedController;

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private TableView<Question> sbScoreQuestionsTv;

	@FXML
	private TableColumn<Question, String> sbQuestionIDTc;

	@FXML
	private TableColumn<Question, String> sbScoreTc;

	@FXML
	private TableColumn<Question, Void> sbEditTc;

	@FXML
	private Label sbQuestionBodyLbl;

	@FXML
	private RadioButton sbAnswer1Rb;

	@FXML
	private RadioButton sbAnswer2Rb;

	@FXML
	private RadioButton sbAnswer3Rb;

	@FXML
	private RadioButton sbAnswer4Rb;

	@FXML
	private Button sbEditBtn;

	@FXML
	private Button sbFinishBtn;

	@FXML
	private Button sbBackBtn;

	@FXML
	private Label sbTotalScoreLbl;

	@FXML
	private TextArea sbStudentCommentsTa;

	@FXML
	private TextArea sbTeacherCommentsTa1;

	@FXML
	private TextArea sbEditTa;

	@FXML
	private TextArea sbAllocatedTimeTa;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static TextArea studentCommentsTa;
	private static TextArea teacherCommentsTa1;
	private static TextArea editTa;
	private static TextArea allocatedTimeTa;
	private static Label questionBodyLbl;
	private static RadioButton answer1Rb;
	private static RadioButton answer2Rb;
	private static RadioButton answer3Rb;
	private static RadioButton answer4Rb;
	private static Button backBtn;
	private static Button finishBtn;
	private static Button editBtn;
	private static TableView<Question> scoreQuestionsTv;
	private static TableColumn<Question, String> questionIDTc;
	private static TableColumn<Question, String> scoreTc;
	private static TableColumn<Question, Void> editTc;
	private static Label totalScoreLbl;

	// STATIC INSTANCES *****************************************************

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcedController = new TeacherComputerizedExamDefinitionsController();

		/**** First panel ****/
		botPanelAp = sbBotPanelAp;
		questionBodyLbl = sbQuestionBodyLbl;
		answer1Rb = sbAnswer1Rb;
		answer2Rb = sbAnswer2Rb;
		answer3Rb = sbAnswer3Rb;
		answer4Rb = sbAnswer4Rb;
		scoreQuestionsTv = sbScoreQuestionsTv;
		questionIDTc = sbQuestionIDTc;
		scoreTc = sbScoreTc;
		editTc = sbEditTc;
		backBtn = sbBackBtn;
		finishBtn = sbFinishBtn;
		editBtn = sbEditBtn;
		totalScoreLbl = sbTotalScoreLbl;
		studentCommentsTa = sbStudentCommentsTa;
		teacherCommentsTa1 = sbTeacherCommentsTa1;
		editTa = sbEditTa;
		allocatedTimeTa = sbAllocatedTimeTa;
	}

	@FXML
	void btnPressBack(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressBack");

		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateExam"));

	}

	@FXML
	void btnPressEdit(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressEdit");

	}

	@FXML
	void btnPressFinish(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressFinish");

		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherMenu"));
	}

}
