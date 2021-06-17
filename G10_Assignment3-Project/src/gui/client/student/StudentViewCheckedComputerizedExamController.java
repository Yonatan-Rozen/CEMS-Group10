package gui.client.student;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

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
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import logic.exam.ComputerizedResults;
import logic.exam.ExamResultsTableStudent;
import logic.question.QuestionInExam;

public class StudentViewCheckedComputerizedExamController implements Initializable {
	public static StudentViewCheckedComputerizedExamController svcceController;
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
	private Button sbGoBackToExamResultsBtn;

	@FXML
	private Label sbQuestionNumberLbl;

	@FXML
	private Label sbNoAnswerLbl;

	@FXML
	private Label sbQuestionScoreLbl;

	@FXML
	private Label sbFinalGradeLbl;

	@FXML
	private ImageView sbQuestionLegendIv;


	// STATIC JAVAFX INSTANCES **********************************************
	private static TextArea questionCommentTa;
	private static Label studentIDLbl;
	private static ButtonBar questionsBb;
	private static Label examIDLbl;
	private static ImageView checkAnswerIv;
	private static Label questionBodyLbl;
	private static ToggleGroup questionsTg;
	private static RadioButton answer1Rb;
	private static RadioButton answer2Rb;
	private static RadioButton answer3Rb;
	private static RadioButton answer4Rb;
	private static Label questionNumberLbl;
	private static Label questionScoreLbl;
	private static Label noAnswerLbl;
	private static Label finalGradeLbl;
	//private static Button backBtn;

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
		svcceController = new StudentViewCheckedComputerizedExamController();
		questionCommentTa = sbQuestionCommentTa;
		studentIDLbl = sbStudentIDLbl;
		questionsBb = sbQuestionsBb;
		examIDLbl = sbExamIDLbl;
		questionNumberLbl = sbQuestionNumberLbl;
		questionBodyLbl = sbQuestionBodyLbl;
		questionsTg = sbQuestionsTg;
		answer1Rb = sbAnswer1Rb;
		answer2Rb = sbAnswer2Rb;
		answer3Rb = sbAnswer3Rb;
		answer4Rb = sbAnswer4Rb;
		questionScoreLbl = sbQuestionScoreLbl;
		checkAnswerIv = sbCheckAnswerIv;
		noAnswerLbl = sbNoAnswerLbl;
		finalGradeLbl = sbFinalGradeLbl;
		sbQuestionLegendIv.setImage(CommonMethodsHandler.ICON_TOOLTIP);
		StudentMenuBarController.menuBarContainerAp.setDisable(true);
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
	void btnPressGoBack(ActionEvent event) {
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonKeepChecking = new ButtonType("Keep checking");
		Optional<ButtonType> result = cmh.getNewAlert(AlertType.CONFIRMATION, "Finish view", "You will be brought back to your checked exams table.",
				"Are you sure you want to continue?",buttonYes,buttonKeepChecking).showAndWait();
		if (result.get() == buttonYes) {
			StudentMenuBarController.mainPaneBp.setCenter(cmh.getPane("student", "StudentExamResults"));
			StudentMenuBarController.menuBarContainerAp.setDisable(false);
		}
	}




	// EXTERNAL USE METHODS **************************************************
	public void setExamOfStudentDetails(ExamResultsTableStudent examOfStudent) {
		//		this.examOfStudent = examOfStudent;
		examIDLbl.setText(examOfStudent.getExamID());
		studentIDLbl.setText(examOfStudent.getStudentID());
		finalGradeLbl.setText(examOfStudent.getGrade());
		questionsBb.getButtons().clear();

		int amount = que.size();

		Button btn;
		for (int questionIndex = 1; questionIndex <= amount; questionIndex++) {
			btn = new Button(questionIndex + "");
			setQuestionButton(questionIndex, btn,answers.get(questionIndex - 1));
			questionsBb.getButtons().add(btn);
		}

		((Button) questionsBb.getButtons().get(0)).fire();
	}

	private void setQuestionButton(int questionIndex, Button b, String studentAnswer) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			QuestionInExam q;

			@Override
			public void handle(ActionEvent event) {
				//				if (lastComment != null)
				//					com.set(lastIndex, com.get(questionIndex));
				//					examOfStudent.setCommentAtIndex(lastIndex, lastComment);
				if (lastButton != null)
					lastButton.setStyle("-fx-background-color: #90EE90;");
				b.setStyle("-fx-background-color: #2E5984;");
				lastButton = b;
				lastIndex = currentIndex;
				currentIndex = questionIndex - 1;
				q = que.get(currentIndex);
				questionNumberLbl.setText(questionIndex + "");
				questionBodyLbl.setText(q.getQuestionBody());
				answer1Rb.setText(q.getAnswer1());
				answer2Rb.setText(q.getAnswer2());
				answer3Rb.setText(q.getAnswer3());
				answer4Rb.setText(q.getAnswer4());
				questionScoreLbl.setText(que.get(currentIndex).getQuestionScore());
				//				lastComment = questionCommentTa.getText();
				questionCommentTa.setText(com.get(currentIndex));

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
				if (cor.get(currentIndex)) {
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

	@SuppressWarnings("unchecked")
	public void setQuestionInExamWithStudentAnswers(Object[] msg) {

		que.addAll((List<QuestionInExam>) msg[1]);
		answers.addAll((List<String>) msg[2]);
		cor.addAll((List<Boolean>) msg[3]);
		com.addAll((List<String>) msg[4]);

		int amount = que.size();
		checked = new boolean[amount];
	}


}
