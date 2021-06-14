package gui.client.teacher;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.image.ImageView;
import logic.exam.ComputerizedResults;
import logic.question.QuestionInExam;

public class TeacherCheckAnswersController implements Initializable {
	public static TeacherCheckAnswersController tcaController = new TeacherCheckAnswersController();

	@FXML
	private TextArea sbQuestionCommentTa;

	@FXML
	private Label sbStudentIDLbl;

	@FXML
	private ButtonBar sbQuestionsBb;

	@FXML
	private Label sbExamIDLbl;
	
	@FXML
	private ImageView sbCheckAnswerIv;

	@FXML
	private Label sbQuestionBodyLbl;

	@FXML
	private ToggleGroup sbQuestionsTg;
	
	@FXML
	private RadioButton sbAnswer1Rb;

	@FXML
	private RadioButton sbAnswer2Rb;

	@FXML
	private RadioButton sbAnswer3Rb;

	@FXML
	private RadioButton sbAnswer4Rb;

	@FXML
	private Label sbQuestionNumberLbl;

	@FXML
	private Button sbConfirmBtn;

	@FXML
	private Label sbQuestionScoreLbl;
	
	@FXML
	private Label sbNoAnswerLbl;
	

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
	private static Button confirmBtn;
	private static Label questionScoreLbl;
	private static Label noAnswerLbl;
	
	private static String examID;
	private static String studentID;
	private static List<QuestionInExam> questions;
	private static QuestionInExam lastQuestion;
	private static List<String> questionScores;
	private static List<String> commentsToQuestions = new ArrayList<>();
	private static boolean[] checkedAnswer;
	private static int currentQuestionIndex;
	private static RadioButton selected;
	private static ComputerizedResults examOfStudent;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcaController = this;
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
		confirmBtn = sbConfirmBtn;
		questionScoreLbl = sbQuestionScoreLbl;
		checkAnswerIv = sbCheckAnswerIv;
		noAnswerLbl = sbNoAnswerLbl;
	}

	@FXML
	void btnPressConfirm(ActionEvent event) {
//		for (boolean checked : checkedAnswer)
//			if (!checked) {
//				CommonMethodsHandler.getInstance().getNewAlert(AlertType.CONFIRMATION, "Question Checking", "You didn't check all the questions!").showAndWait();
//				return;
//			}
//		// save comments
//		ClientUI.chat.accept(new Object[] {"",questions, commentsToQuestions});
		
	}
	
	public void setExamOfStudentDetails(ComputerizedResults examOfStudent) { // works before initialize
//		this.examOfStudent = examOfStudent;
		System.out.println(examOfStudent);
		examIDLbl.setText(examOfStudent.getExamID());
		commentsToQuestions.addAll(examOfStudent.getComments());
		String[] studentAnswers = examOfStudent.getStudentAnswers();
		Button b;
		for (int questionIndex = 1; questionIndex <= questions.size(); questionIndex++) {
			b = new Button(questionIndex + "");
			setQuestionButton(questionIndex, b,studentAnswers[questionIndex]);
			questionsBb.getButtons().add(b);
		}
	}
	
	private void setQuestionButton(int questionIndex, Button b, String studentAnswer) {
		b.setOnAction(new EventHandler<ActionEvent>() {
			QuestionInExam q;

			@Override
			public void handle(ActionEvent event) {
				
				b.setStyle("-fx-background-color: GREEN;");
				currentQuestionIndex = questionIndex - 1;
				q = questions.get(currentQuestionIndex);
				if (lastQuestion!= null)
					commentsToQuestions.set(currentQuestionIndex, questionCommentTa.getText());
				lastQuestion = q;
				questionNumberLbl.setText(questionIndex+"");
				questionBodyLbl.setText(q.getQuestionBody());
				answer1Rb.setText(q.getAnswer1());
				answer2Rb.setText(q.getAnswer2());
				answer3Rb.setText(q.getAnswer3());
				answer4Rb.setText(q.getAnswer4());
				questionScoreLbl.setText(questionScores.get(currentQuestionIndex));
				questionCommentTa.setText(commentsToQuestions.get(currentQuestionIndex));
				if(selected != null) selected.setStyle(null);
				switch(studentAnswer) {
				case "1":
					answer1Rb.setSelected(true);
					selected = answer1Rb;
					break;
				case "2":
					answer2Rb.setSelected(true);
					selected = answer1Rb;
					break;
				case "3":
					answer3Rb.setSelected(true);
					selected = answer1Rb;
					break;
				case "4":
					answer4Rb.setSelected(true);
					selected = answer1Rb;
					break;
				default: // 0
					questionsTg.getSelectedToggle().setSelected(false);
					selected = null;
					break;
				}
				
				if (q.getCorrectAnswer().equals(studentAnswer)) {
					checkAnswerIv.setImage(CommonMethodsHandler.ICON_CORRECT);
					noAnswerLbl.setVisible(false);
				}
				else {
					checkAnswerIv.setImage(CommonMethodsHandler.ICON_WRONG);
					noAnswerLbl.setVisible(true);
				}
				
				answer1Rb.setStyle(null);
				answer2Rb.setStyle(null);
				answer3Rb.setStyle(null);
				answer4Rb.setStyle(null);
				
				selected.setStyle("-fx-fill: RED; -fx-font-weight: Bold;");
				
				switch(q.getCorrectAnswer()) {
				case "1":
					answer1Rb.setStyle("-fx-fill: GREEN; -fx-font-weight: Bold;");
					break;
				case "2":
					answer2Rb.setStyle("-fx-fill: GREEN; -fx-font-weight: Bold;");
					break;
				case "3":
					answer3Rb.setStyle("-fx-fill: GREEN; -fx-font-weight: Bold;");
					break;
				case "4":
					answer4Rb.setStyle("-fx-fill: GREEN; -fx-font-weight: Bold;");
					break;
				default:
					System.out.println("wrong answer value!");
					break;
				}
				
				checkedAnswer[questionIndex] = true;
			}
		});
	}

	

}
