package gui.client.student;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import logic.exam.ComputerizedExam;
import logic.question.Question;

public class StudentTakeComputerizedExamController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Label sbExamOfCourseLbl;

	@FXML
	private AnchorPane sbExamContainerAp;

	@FXML
	private TextArea sbGeneralInfoTa;

	@FXML
	private ScrollPane sbQuestionSp;

	@FXML
	private ButtonBar sbQuestionBarBb;

	@FXML
	private Label sbScorelbl;

	@FXML
	private ScrollPane sbAnswersSp;

	@FXML
	private Label sbQuestionLbl;

	@FXML
	private RadioButton sbAnswer1Rb;

	@FXML
	private ToggleGroup sbAnswerTg;

	@FXML
	private RadioButton sbAnswer2Rb;

	@FXML
	private RadioButton sbAnswer3Rb;

	@FXML
	private RadioButton sbAnswer4Rb;

	@FXML
	private Button sbSubmitBtn;

	@FXML
	private TextField sbStudentIDTf;

	@FXML
	private Button sbStartExamBtn;

	@FXML
	private Label sbAlertCoreectIDLbl;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Label examOfCourseLbl;
	private static TextArea generalInfoTa;
	private static Label scorelbl;
	private static ButtonBar questionBarBb;
	private static Label questionLbl;
	private static ToggleGroup answerTg;
	private static RadioButton answer1Rb;
	private static RadioButton answer2Rb;
	private static RadioButton answer3Rb;
	private static RadioButton answer4Rb;
	private static Button submitBtn;
	private static Button startExamBtn;
	private static AnchorPane examContainerAp;
	private static TextField studentIDTf;
	private static Label alertCoreectIDLbl;

	// STATIC INSTANCES **********************************************
	private static String examID; // get from teacher somehow
	private static ComputerizedExam exam;
	private static List<Question> questionsOfExam;// = new ArrayList<>();
	private static int currentQuestionIndex;
	private static List<String> scoresOfQuestions;
	private static String[] answersOfStudent;

	// CONTROLLER INSTANCES *******************************************
	public static StudentTakeComputerizedExamController stceController = new StudentTakeComputerizedExamController();;

	// INITIALIZE METHOD **********************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// stceController
		examOfCourseLbl = sbExamOfCourseLbl;
		generalInfoTa = sbGeneralInfoTa;
		scorelbl = sbScorelbl;
		questionBarBb = sbQuestionBarBb;
		questionLbl = sbQuestionLbl;
		answerTg = sbAnswerTg;
		answer1Rb = sbAnswer1Rb;
		answer2Rb = sbAnswer2Rb;
		answer3Rb = sbAnswer3Rb;
		answer4Rb = sbAnswer4Rb;
		submitBtn = sbSubmitBtn;
		studentIDTf = sbStudentIDTf;
		alertCoreectIDLbl = sbAlertCoreectIDLbl;
		examContainerAp = sbExamContainerAp;
		examContainerAp.setDisable(true);
		startExamBtn = sbStartExamBtn;
		ClientUI.chat.accept(new String[] { "btnPressStartExam", examID });
		System.out.println("scoresOfQuestions : " + scoresOfQuestions);
		answersOfStudent = new String[scoresOfQuestions.size()];
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressStartExam(ActionEvent event) {
		System.out.println("StudentTakeComputerizedExam::btnPressStartExam");
		Button b;
		// may be check if it's repressed and if it was pressed again-delete all
		// progress? (boolean?)
		if (questionBarBb.getButtons().size() == 0) {
			for (int questionIndex = 1; questionIndex <= questionsOfExam.size(); questionIndex++) {
				b = new Button(questionIndex + "");
				setQuestionButton(questionIndex, b);
				questionBarBb.getButtons().add(b);
			}
		}
		sbExamContainerAp.setDisable(false);
	}

	@FXML
	void rbPressAnswer1(ActionEvent event) {
		System.out.println("StudentTakeComputerizedExam::rbPressAnswer1");
		answersOfStudent[currentQuestionIndex] = "1";
	}

	@FXML
	void rbPressAnswer2(ActionEvent event) {
		System.out.println("StudentTakeComputerizedExam::rbPressAnswer2");
		answersOfStudent[currentQuestionIndex] = "2";
	}

	@FXML
	void rbPressAnswer3(ActionEvent event) {
		System.out.println("StudentTakeComputerizedExam::rbPressAnswer3");
		answersOfStudent[currentQuestionIndex] = "3";
	}

	@FXML
	void rbPressAnswer4(ActionEvent event) {
		System.out.println("StudentTakeComputerizedExam::rbPressAnswer4");
		answersOfStudent[currentQuestionIndex] = "4";
	}

	@FXML
	void btnPressSubmit(ActionEvent event) throws IOException {
		System.out.println("StudentTakeComputerizedExam::btnPressSubmit");
		/*
		 * //why does it print the pointer address ?!?
		 * System.out.println(answersOfStudent.); /*
		 * System.out.println("/nthe answers:"); for(int
		 * i=0;i<answersOfStudent.length;i++)
		 * System.out.print(answersOfStudent[i]+", ");
		 */

		// successful submit example ***********************************
		// TODO maybe add alert "are you sure you want to submit?"
		ClientUI.mainScene
				.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentExamSubmitted.fxml")));
		// *************************************************************
	}

	// EXTERNAL USE METHODS *************************************************

	/**
	 * *
	 *
	 * @param examIDFromTeacher the running exam ID sent from the teacher
	 */
	public void setExamID(String examIDFromTeacher) {
		if (examIDFromTeacher != null && !examIDFromTeacher.equals("")) {
			// TODO get examID from teacher to all connected students
			examID = examIDFromTeacher;
		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Code inserting failed",
					"There was no examID chosen by a teacher").showAndWait();
		}
	}

	/**
	 *
	 * @param examTupple one exam from the exams table, with all it's fields
	 */
	public void setExam(ComputerizedExam examTupple) {
		exam = examTupple;
	}

	/**
	 *
	 * @param questionsOfExamlist an arrayList of questions of all the questions of
	 *                            the running exam
	 */
	public void setQuestionsOfExam(List<Question> questionsOfExamlist) {
		questionsOfExam = questionsOfExamlist;
	}

	/**
	 *
	 * @param questionsScoresOfExamlist
	 */
	public void setQuestionsScoresOfExam(List<String> questionsScoresOfExamlist) {
		scoresOfQuestions = questionsScoresOfExamlist;
	}

	/**
	 *
	 * @param courseName the exam's course name
	 */
	public void setCourseName(String courseName) {
		examOfCourseLbl.setText("Exam - " + courseName);
	}

	/**
	 *
	 * @param questionIndex the current pressed question's index in the exam's
	 *                      questions bar
	 * @param b             a button for the button bar of questions to be defined
	 *                      as the button of the questionIndex's question
	 */
	void setQuestionButton(int questionIndex, Button b) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			Question q;

			@Override
			public void handle(ActionEvent event) {
				currentQuestionIndex = questionIndex - 1;
				q = questionsOfExam.get(questionIndex - 1);
				questionLbl.setText(questionIndex + ") " + q.getQuestionBody());
				answer1Rb.setText(q.getAnswer1());
				answer2Rb.setText(q.getAnswer2());
				answer3Rb.setText(q.getAnswer3());
				answer4Rb.setText(q.getAnswer4());
				scorelbl.setText("(score : " + scoresOfQuestions.get(questionIndex - 1) + ")");
			}
		});
	}

}
