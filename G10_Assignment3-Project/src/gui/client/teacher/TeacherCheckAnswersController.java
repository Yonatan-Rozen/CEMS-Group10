package gui.client.teacher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import logic.exam.ComputerizedResults;
import logic.question.QuestionInExam;

/**
 * A controller that controls functionalities that allow the teacher
 * Check the question in a specific exam result of a specific student
 * @author Yonatan Rozen
 */
public class TeacherCheckAnswersController implements Initializable {
	public static TeacherCheckAnswersController tcaController;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TextArea sbQuestionCommentTa;

	@FXML
	private Label sbStudentIDLbl;

	@FXML
	private ButtonBar sbQuestionsBb;

	@FXML
	private Label sbExamIDLbl;

	@FXML
	private Label sbQuestionBodyLbl;

	@FXML
	private RadioButton sbAnswer1Rb;

	@FXML
	private ToggleGroup sbQuestionsTg;

	@FXML
	private RadioButton sbAnswer2Rb;

	@FXML
	private RadioButton sbAnswer3Rb;

	@FXML
	private RadioButton sbAnswer4Rb;

	@FXML
	private ImageView sbCheckAnswerIv;

	@FXML
	private Button sbAcceptGradeBtn;

	@FXML
	private Label sbQuestionNumberLbl;

	@FXML
	private Label sbNoAnswerLbl;

	@FXML
	private Label sbQuestionScoreLbl;

	@FXML
	private TextField sbFinalGradeTf;

	@FXML
	private Label sbComputerizedGradeLbl;

	@FXML
	private TextArea sbExamCommentTa;

	@FXML
	private Button sbBackBtn;

	@FXML
	private ImageView sbQuestionLegendIv;

	@FXML
	private ImageView sbFinalScoreIv;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextArea questionCommentTa;
	private static TextArea examCommentTa;
	private static Label studentIDLbl;
	private static ButtonBar questionsBb;
	private static Label examIDLbl;
	private static ImageView checkAnswerIv;
	private static Label questionBodyLbl;
	private static Label computerizedGradeLbl;
	private static TextField finalGradeTf;
	private static ToggleGroup questionsTg;
	private static RadioButton answer1Rb;
	private static RadioButton answer2Rb;
	private static RadioButton answer3Rb;
	private static RadioButton answer4Rb;
	private static Label questionNumberLbl;
	private static Label questionScoreLbl;
	private static Label noAnswerLbl;
	private static Button backBtn;

	// STATIC INSTANCES *****************************************************
	private static CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();
	private static ComputerizedResults examOfStudent;
	private static List<QuestionInExam> que = new ArrayList<>();
	private static List<String> answers = new ArrayList<>();
	private static List<Boolean> cor = new ArrayList<>();
	private static List<String> com = new ArrayList<>();
	private static boolean[] isCorrect;
	private static boolean[] checked;
	private static QuestionInExam lastQuestion;
	private static Button lastButton;
	private static String lastComment;
	private static int currentIndex;
	private static int lastIndex;
	private static RadioButton selected;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcaController = new TeacherCheckAnswersController();
		questionCommentTa = sbQuestionCommentTa;
		examCommentTa = sbExamCommentTa;
		studentIDLbl = sbStudentIDLbl;
		questionsBb = sbQuestionsBb;
		examIDLbl = sbExamIDLbl;
		finalGradeTf = sbFinalGradeTf;
		questionNumberLbl = sbQuestionNumberLbl;
		questionBodyLbl = sbQuestionBodyLbl;
		computerizedGradeLbl = sbComputerizedGradeLbl;
		questionsTg = sbQuestionsTg;
		answer1Rb = sbAnswer1Rb;
		answer2Rb = sbAnswer2Rb;
		answer3Rb = sbAnswer3Rb;
		answer4Rb = sbAnswer4Rb;
		questionScoreLbl = sbQuestionScoreLbl;
		checkAnswerIv = sbCheckAnswerIv;
		noAnswerLbl = sbNoAnswerLbl;
		sbQuestionLegendIv.setImage(CommonMethodsHandler.ICON_TOOLTIP);
		sbFinalScoreIv.setImage(CommonMethodsHandler.ICON_TOOLTIP);
		TeacherMenuBarController.menuBarAp.setDisable(true);
		que.clear();
		answers.clear();
		cor.clear();
		com.clear();

		lastButton = null;
		lastComment = null;
		lastIndex = 0;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressAcceptGrade(ActionEvent event) {
		for (boolean checked : checked)
			if (!checked) {
				cmh.getNewAlert(AlertType.INFORMATION, "Question Checking", "Please check all the questions!").showAndWait();
				return;
			}

		if (finalGradeTf.getText().isEmpty()) {
			cmh.getNewAlert(AlertType.INFORMATION, "Insert Final Grade", "Please insert final grade.Note that you must provide a reason for a grade change", "Press ok to continue.") .showAndWait();
			return;
		}
		if (!finalGradeTf.getText().equals(examOfStudent.getComputerizedGrade()) && examCommentTa.getText().isEmpty()) {
			cmh.getNewAlert(AlertType.INFORMATION, "Missing comment", "Note that you must provide a reason for a grade change", "Press ok to continue.") .showAndWait();
			return;
		}

		examOfStudent.setTeacherComment(examCommentTa.getText());
		ClientUI.chat.accept(new Object[] { "UpdateStudentFinalGrade", examOfStudent});
		TeacherMenuBarController.mainPaneBp.setCenter(cmh.getPane("teacher", "TeacherCheckExamResults"));
	}

	@FXML
	public void btnPressBack(ActionEvent event) {
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonKeepChecking = new ButtonType("Keep checking");
		Optional<ButtonType> result = cmh.getNewAlert(AlertType.CONFIRMATION, "Cancel Check", "Note that the checking process will reset,\nand all added comments will be discarded.",
				"Are you sure you want to continue?",buttonYes,buttonKeepChecking).showAndWait();
		if (result.get() == buttonYes)
			TeacherMenuBarController.mainPaneBp.setCenter(cmh.getPane("teacher", "TeacherCheckExamResults"));
	}

	// EXTERNAL USE METHODS **************************************************
	
	/**
	 * Sets the details of the exam results of a specific student
	 * @param examOfStudent The computerized exam which was completed by a specific student
	 */
	public void setExamOfStudentDetails(ComputerizedResults examOfStudent) {
		this.examOfStudent = examOfStudent;
		examIDLbl.setText(examOfStudent.getExamID());
		studentIDLbl.setText(examOfStudent.getStudentID());
		computerizedGradeLbl.setText(examOfStudent.getComputerizedGrade());
		ClientUI.chat.accept(new String[] { "GetQuestionInExamWithStudentAnswers", examOfStudent.getExamID(),
				examOfStudent.getStudentID(),"T" });

		questionsBb.getButtons().clear();

		int amount = que.size();

		Button btn;
		for (int questionIndex = 1; questionIndex <= amount; questionIndex++) {
			btn = new Button(questionIndex + "");
			setQuestionButton(questionIndex, btn, examOfStudent.getStudentAnswers()[questionIndex - 1]);
			questionsBb.getButtons().add(btn);
		}

		((Button) questionsBb.getButtons().get(0)).fire();
	}

	/**
	 * Sets the buttons at the button bar component and connects each button to a specific answered question
	 * @param questionIndex The number of the button and question
	 * @param b The button
	 * @param studentAnswer The answer of the student for the current question
	 */
	private void setQuestionButton(int questionIndex, Button b, String studentAnswer) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			QuestionInExam q;

			@Override
			public void handle(ActionEvent event) {
				if (lastComment != null)
					examOfStudent.setCommentAtIndex(lastIndex, lastComment);
				if (lastButton != null)
					lastButton.setStyle("-fx-background-color: #90EE90;");
				b.setStyle("-fx-background-color: #2E5984;");
				lastButton = b;
				lastIndex = currentIndex;
				currentIndex = questionIndex - 1;
				q = examOfStudent.getQuestions().get(currentIndex);
				questionNumberLbl.setText(questionIndex + "");
				questionBodyLbl.setText(q.getQuestionBody());
				answer1Rb.setText(q.getAnswer1());
				answer2Rb.setText(q.getAnswer2());
				answer3Rb.setText(q.getAnswer3());
				answer4Rb.setText(q.getAnswer4());
				questionScoreLbl.setText(examOfStudent.getQuestions().get(currentIndex).getQuestionScore());
				lastComment = questionCommentTa.getText();
				questionCommentTa.setText(examOfStudent.getComments().get(currentIndex));

				switch (studentAnswer) {
				case "1":
					answer1Rb.setSelected(true);
					selected = answer1Rb;
					break;
				case "2":
					answer2Rb.setSelected(true);
					selected = answer2Rb;
					break;
				case "3":
					answer3Rb.setSelected(true);
					selected = answer3Rb;
					break;
				case "4":
					answer4Rb.setSelected(true);
					selected = answer4Rb;
					break;
				default: // 0
					if(questionsTg.getSelectedToggle() != null)
						questionsTg.getSelectedToggle().setSelected(false);
					break;
				}

				answer1Rb.setStyle(null);
				answer2Rb.setStyle(null);
				answer3Rb.setStyle(null);
				answer4Rb.setStyle(null);

				noAnswerLbl.setVisible(false);
				if (examOfStudent.getIsCorrect()[currentIndex]) {
					checkAnswerIv.setImage(CommonMethodsHandler.ICON_CORRECT);
				} else {
					checkAnswerIv.setImage(CommonMethodsHandler.ICON_WRONG);
					if (studentAnswer.equals("0"))
						noAnswerLbl.setVisible(true);
					else selected.setStyle("-fx-color: RED; -fx-text-fill: RED; -fx-font-size: 21; -fx-font-weight: Bold");
				}

				switch (q.getCorrectAnswer()) {
				case "1":
					answer1Rb.setStyle(
							"-fx-color: GREEN; -fx-text-fill: GREEN; -fx-font-size: 21; -fx-font-weight: Bold");
					break;
				case "2":
					answer2Rb.setStyle(
							"-fx-color: GREEN; -fx-text-fill: GREEN; -fx-font-size: 21; -fx-font-weight: Bold");
					break;
				case "3":
					answer3Rb.setStyle(
							"-fx-color: GREEN; -fx-text-fill: GREEN; -fx-font-size: 21; -fx-font-weight: Bold");
					break;
				case "4":
					answer4Rb.setStyle(
							"-fx-color: GREEN; -fx-text-fill: GREEN; -fx-font-size: 21; -fx-font-weight: Bold");
					break;
				default:
					System.out.println("wrong answer value!");
					break;
				}

				checked[currentIndex] = true;
			}
		});
	}

	/**
	 * Sets all the details of the questions and the comments to be inserted for each question
	 * @param questionAndCommentsDetails The details of the questions and the comments
	 */
	@SuppressWarnings("unchecked")
	public void setQuestionInExamWithStudentAnswers(Object[] questionAndCommentsDetails) {

		que.addAll((List<QuestionInExam>) questionAndCommentsDetails[1]);
		answers.addAll((List<String>) questionAndCommentsDetails[2]);
		cor.addAll((List<Boolean>) questionAndCommentsDetails[3]);
		com.addAll((List<String>) questionAndCommentsDetails[4]);

		int amount = que.size();
		String[] studentAnswers = new String[amount];
		boolean[] isCorrect = new boolean[amount];
		checked = new boolean[amount];

		for (int i = 0; i < amount; i++) {
			studentAnswers[i] = answers.get(i);
			isCorrect[i] = cor.get(i);
		}

		examOfStudent.setQuestions(que); // questions;
		examOfStudent.setStudentAnswers(studentAnswers); // studentAnswers;
		examOfStudent.setIsCorrect(isCorrect); // isCorrect;
		examOfStudent.setComments(com); // comments;
	}

}
