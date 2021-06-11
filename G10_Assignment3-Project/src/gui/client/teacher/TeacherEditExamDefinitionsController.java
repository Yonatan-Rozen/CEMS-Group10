package gui.client.teacher;

import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import logic.question.Question;
import logic.question.QuestionInExam;


/*
Another page for full exam editing - optional
if we want to edit also questions in exams then we need to split edit into 2-3 fxml+controller - this is one of them
fxml1 - choose bank -> choose row in exam tables -->next
fxml1 - add/remove question in exam + change time/comments
fxml2(optinal) -  split fxml1
*/


public class TeacherEditExamDefinitionsController implements Initializable {
	public static TeacherEditExamDefinitionsController teedController;

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

	@FXML
	private Label sbScoreLbl;

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
	private static Label scoreLbl;

	// STATIC INSTANCES *****************************************************
	private static String ExamID;
	ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
	private static List<Question> questionList;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teedController = new TeacherEditExamDefinitionsController();

		System.out.println("here222");
		/**** First panel ****/
		botPanelAp = sbBotPanelAp;
		questionBodyLbl = sbQuestionBodyLbl;
		scoreLbl = sbScoreLbl;
		answer1Rb = sbAnswer1Rb;
		answer2Rb = sbAnswer2Rb;
		answer3Rb = sbAnswer3Rb;
		answer4Rb = sbAnswer4Rb;
		answer1Rb.setDisable(true);
		answer2Rb.setDisable(true);
		answer3Rb.setDisable(true);
		answer4Rb.setDisable(true);
		questionBodyLbl.setDisable(true);
		scoreLbl.setDisable(true);
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
		editBtn.setDisable(true);
		studentCommentsTa.setText("___Enter___");
		teacherCommentsTa1.setText("___Enter___");
		editTa.setDisable(true);
		allocatedTimeTa.setText("Enter Time !!!");
		totalScoreLbl.setText("0");

//		// set up table view
//		questionIDTc.setCellValueFactory(new PropertyValueFactory<Question, String>("questionID"));
//		questionIDTc.setStyle("-fx-alignment: CENTER; -fx-font-weight: Bold;");
//		// set up table view
//		scoreTc.setCellValueFactory(new PropertyValueFactory<Question, String>("CorrectAnswer"));
//		scoreTc.setStyle("-fx-alignment: CENTER; -fx-font-weight: Bold;");
//
//		// set button cells for the 'Update Time' Column
//		Callback<TableColumn<Question, Void>, TableCell<Question, Void>> btnCellFactory5 = new Callback<TableColumn<Question, Void>, TableCell<Question, Void>>() {
//
//			@Override
//			public TableCell<Question, Void> call(final TableColumn<Question, Void> param5) {
//				final TableCell<Question, Void> cell2 = new TableCell<Question, Void>() {
//					private final Button btn = new Button();
//					private final ImageView addicon = new ImageView(new Image("/icon_edit.png"));
//
//					@Override
//					public void updateItem(Void item, boolean empty) {
//						super.updateItem(item, empty);
//						btn.setStyle("-fx-background-color: transparent;");
//						btn.setPrefSize(40, 20);
//						addicon.setPreserveRatio(true);
//						addicon.setFitHeight(20);
//						addicon.setFitWidth(40);
//						btn.setGraphic(addicon);
//						if (empty) {
//							setGraphic(null);
//						} else {
//							btn.setOnAction(e -> {
//								Question question = getTableRow().getItem();
//								ShowQuestionAndEditScore(question);
//							});
//							setGraphic(btn);
//						}
//					}
//				};
//				cell2.setAlignment(Pos.CENTER);
//				return cell2;
//			}
//		};
//		editTc.setCellFactory(btnCellFactory5);
//
//		questionList = TeacherCreateExamController.tceController.getCurrentList();
//		questionObservableList.addAll(questionList);
//		System.out.println("2 = " + questionObservableList);
//		scoreQuestionsTv.setItems(questionObservableList);

	}

	@FXML
	void btnPressBack(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressBack");

		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditExam"));
	}

	@FXML
	void btnPressEdit(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressEdit");

		// sql for update score in 'question in exam'
		String curScore = totalScoreLbl.getText();
		int IcurScore = Integer.parseInt(curScore);
//		IcurScore += 15;
		totalScoreLbl.setText(String.valueOf(IcurScore += 15));

	}

	@FXML
	void btnPressFinish(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressFinish");
		String insertTime = allocatedTimeTa.getText();
		if (!insertTime.equals("Enter Time!")) {
			ClientUI.chat
					.accept(new String[] { "btnPressFinishCreateComputerizedExam", ExamID, studentCommentsTa.getText(),
							teacherCommentsTa1.getText(), allocatedTimeTa.getText(), ChatClient.user.getUsername() });

			TeacherMenuBarController.mainPaneBp
					.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherMenu"));
		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing allocated time",
					"Must to enter value (allocated time)").showAndWait();
		}
	}

	// EXTERNAL USE METHODS **************************************************
	public void setExamID(String ExamID) {
		this.ExamID = ExamID;
	}

	public void ShowQuestionAndEditScore(Question question) {
		answer1Rb.setDisable(false);
		answer2Rb.setDisable(false);
		answer3Rb.setDisable(false);
		answer4Rb.setDisable(false);
		questionBodyLbl.setDisable(false);
		scoreLbl.setDisable(false);
		editBtn.setDisable(false);
		editTa.setDisable(false);

		////
		answer1Rb.setSelected(false);
		answer2Rb.setSelected(false);
		answer3Rb.setSelected(false);
		answer4Rb.setSelected(false);
		questionBodyLbl.setText(question.getQuestionBody());
		answer1Rb.setText(question.getAnswer1());
		answer2Rb.setText(question.getAnswer2());
		answer3Rb.setText(question.getAnswer3());
		answer4Rb.setText(question.getAnswer4());

		switch (question.getCorrectAnswer()) {
		case "1":
			answer1Rb.setSelected(true);
			break;
		case "2":
			answer2Rb.setSelected(true);
			break;
		case "3":
			answer3Rb.setSelected(true);
			break;
		case "4":
			answer4Rb.setSelected(true);
			break;
		default:
			break;
		}
	}

}
