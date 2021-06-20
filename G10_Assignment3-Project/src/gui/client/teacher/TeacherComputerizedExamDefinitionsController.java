package gui.client.teacher;

import java.net.URL;
import java.util.HashMap;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.util.Callback;
import logic.question.Question;
import logic.question.QuestionInExam;

/**
 * A controller that controls all functionalites of the final stage of the creation of an exam,
 * including:<br>* setting scores to each question<br>* setting allocated time<br>* setting optional
 * comments for supervising teacher and/or examinees students
 * @author Eliran Amerzoyev
 */
public class TeacherComputerizedExamDefinitionsController implements Initializable {
	public static TeacherComputerizedExamDefinitionsController tcedController;

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private TableView<QuestionInExam> sbScoreQuestionsTv;

	@FXML
	private TableColumn<QuestionInExam, String> sbQuestionIDTc;

	@FXML
	private TableColumn<QuestionInExam, String> sbScoreTc;

	@FXML
	private TableColumn<QuestionInExam, Void> sbEditTc;

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
	private TextField sbEditTf;

	@FXML
	private TextField sbAllocatedTimeTf;

	@FXML
	private Label sbScoreLbl;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static TextArea studentCommentsTa;
	private static TextArea teacherCommentsTa1;
	private static TextField editTf;
	private static TextField allocatedTimeTf;
	private static Label questionBodyLbl;
	private static RadioButton answer1Rb;
	private static RadioButton answer2Rb;
	private static RadioButton answer3Rb;
	private static RadioButton answer4Rb;
	private static Button backBtn;
	private static Button finishBtn;
	private static Button editBtn;
	private static TableView<QuestionInExam> scoreQuestionsTv;
	private static TableColumn<QuestionInExam, String> questionIDTc;
	private static TableColumn<QuestionInExam, String> scoreTc;
	private static TableColumn<QuestionInExam, Void> editTc;
	private static Label totalScoreLbl;
	private static Label scoreLbl;

	// STATIC INSTANCES *****************************************************
	private static String ExamID;
	ObservableList<QuestionInExam> questionObservableList = FXCollections.observableArrayList();
	private static CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();
	private static List<QuestionInExam> questionList;
	private static String QuestionIDToEdit, questionScoreToEDIT;
	private static String msg;
	private static QuestionInExam questionupdatescore;
	private static HashMap<String, String> locateRow = new HashMap<>();

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcedController = new TeacherComputerizedExamDefinitionsController();

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
		cmh.disableTableColumnSwap(scoreQuestionsTv);
		questionIDTc = sbQuestionIDTc;
		scoreTc = sbScoreTc;
		editTc = sbEditTc;
		backBtn = sbBackBtn;
		finishBtn = sbFinishBtn;
		editBtn = sbEditBtn;
		totalScoreLbl = sbTotalScoreLbl;
		studentCommentsTa = sbStudentCommentsTa;
		teacherCommentsTa1 = sbTeacherCommentsTa1;
		editTf = sbEditTf;
		cmh.setIntegersOnlyTextLimiter(editTf, 3);
		allocatedTimeTf = sbAllocatedTimeTf;
		cmh.setIntegersOnlyTextLimiter(allocatedTimeTf, 3);
		editBtn.setDisable(true);
		editTf.setDisable(true);
		totalScoreLbl.setText("0");
		// set up table view
		questionIDTc.setCellValueFactory(new PropertyValueFactory<QuestionInExam, String>("questionID"));
		// set up table view
		scoreTc.setCellValueFactory(new PropertyValueFactory<QuestionInExam, String>("questionScore"));

		// set button cells for the 'Update Time' Column
		Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>> btnCellFactory5 = new Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>>() {

			@Override
			public TableCell<QuestionInExam, Void> call(final TableColumn<QuestionInExam, Void> param5) {
				final TableCell<QuestionInExam, Void> cell2 = new TableCell<QuestionInExam, Void>() {
					private final Button btn = new Button();
					private final ImageView addicon = new ImageView(new Image("/icon_edit.png"));

					@Override
					public void updateItem(Void item, boolean empty) {
						super.updateItem(item, empty);
						btn.setStyle("-fx-background-color: transparent;");
						btn.setPrefSize(40, 20);
						addicon.setPreserveRatio(true);
						addicon.setFitHeight(20);
						addicon.setFitWidth(40);
						btn.setGraphic(addicon);
						if (empty) {
							setGraphic(null);
						} else {
							btn.setOnAction(e -> {
								QuestionInExam question = getTableRow().getItem();
								QuestionIDToEdit = question.getQuestionID(); // save id
								locateRow.put(question.getQuestionID(), question.getQuestionScore()); // TODO <-->
								questionupdatescore = getTableRow().getItem();
								ShowQuestionAndEditScore(question);
								questionObservableList.remove(question); // remove
								sbScoreQuestionsTv.setDisable(true);
							});
							setGraphic(btn);
						}
					}
				};
				cell2.setAlignment(Pos.CENTER);
				return cell2;
			}
		};
		editTc.setCellFactory(btnCellFactory5);
		questionObservableList.clear();
		questionList = TeacherCreateExamController.tceController.getCurrentList();
		questionObservableList.addAll(questionList);
		scoreQuestionsTv.setItems(questionObservableList);
	}

	@FXML
	void btnPressBack(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressBack");
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonNo = new ButtonType("No");
		Optional<ButtonType> request = cmh.getNewAlert(AlertType.CONFIRMATION, "Discard exam",
						"Are you sure you want to cancel the creation of the current exam?", buttonYes, buttonNo).showAndWait();
		if (request.get().equals(buttonYes)) {

			TeacherMenuBarController.mainPaneBp
					.setCenter(cmh.getPane("teacher", "TeacherCreateExam"));
			scoreQuestionsTv.setItems(null);
		}
	}

	@FXML
	void btnPressEdit(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressEdit");
		String insertScore = editTf.getText();
		if (!insertScore.isEmpty()) {
			ClientUI.chat.accept(new String[] { "UpdateQuestionAndScoreToExam", ExamID, editTf.getText(),
					QuestionIDToEdit, ChatClient.user.getUsername() });

			questionupdatescore.setQuestionScore(editTf.getText()); // update
			questionObservableList.add(questionupdatescore); // add
			scoreQuestionsTv.setItems(questionObservableList); // update table with new value
			scoreQuestionsTv.getSortOrder().add(new TableColumn<QuestionInExam, String>());

			answer1Rb.setDisable(true);
			answer2Rb.setDisable(true);
			answer3Rb.setDisable(true);
			answer4Rb.setDisable(true);
			questionBodyLbl.setDisable(true);
			scoreLbl.setDisable(true);
			editBtn.setDisable(true);
			editTf.setDisable(true);
			sbScoreQuestionsTv.setDisable(false);

			int total = 0;
			String curScore;
			for (QuestionInExam questioninexam : sbScoreQuestionsTv.getItems()) {
				curScore = questioninexam.getQuestionScore();
				int IcurScore = Integer.parseInt(curScore);
				total += IcurScore;
			}
			if (total == 100) {
				totalScoreLbl.setText(String.valueOf(total));
				totalScoreLbl.setStyle("-fx-background-color: #ccffcc;");
			} else {
				totalScoreLbl.setText(String.valueOf(total));
				totalScoreLbl.setStyle("-fx-background-color: #ffcccc;");
			}
		} else {
			cmh.getNewAlert(AlertType.ERROR, "Error message", "Missing Score (Edit)",
					"Please insert score for question").showAndWait();
		}
	}

	@FXML
	void btnPressFinish(ActionEvent event) throws Exception {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressFinish");
		String insertTime = allocatedTimeTf.getText();
		if (!insertTime.isEmpty()) {
			if (checkScores()) {
				ClientUI.chat.accept(new String[] { "btnPressFinishCreateComputerizedExam", ExamID,
						studentCommentsTa.getText(), teacherCommentsTa1.getText(), allocatedTimeTf.getText(), "1",
						ChatClient.user.getUsername() });				
			
				ButtonType buttonYes = new ButtonType("Yes");
				ButtonType buttonNo = new ButtonType("No");
				Optional<ButtonType> request = cmh.getNewAlert(AlertType.INFORMATION, "Exam saved",
								"Exam was created successfully!", "Create another exam?", buttonYes, buttonNo).showAndWait();

				if (request.get() == buttonYes) {
					TeacherMenuBarController.mainPaneBp.setCenter(cmh.getPane("teacher", "TeacherCreateExam"));
				} else {
					ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
				}
			} else {
				cmh.getNewAlert(AlertType.ERROR, "Error message", "Missing Scores",
						"Please insert score for all question and check total is 100").showAndWait();
			}
		} else {
			cmh.getNewAlert(AlertType.ERROR, "Error message", "Missing allocated time",
					"Please insert the allocated time (min) for the exam.").showAndWait();

		}
	}

	/**
	 * Checks if the sum of all scores reaches 100
	 * @return true if the total score is exactly 100; false otherwise
	 */
	private boolean checkScores() {

		int total = 0;
		String curScore;
		for (QuestionInExam questioninexam : sbScoreQuestionsTv.getItems()) {
			curScore = questioninexam.getQuestionScore();
			int IcurScore = Integer.parseInt(curScore);
			if (IcurScore == 0)
				return false;
			total += IcurScore;
		}
		if (total != 100)
			return false;
		return true;
	}

	// EXTERNAL USE METHODS **************************************************
	/**
	 * Sets the exam ID of the exam currently in the making
	 * @param ExamID The ID of the exam
	 */
	public void setExamID(String ExamID) {
		this.ExamID = ExamID;
	}

	/**
	 * Sets the question for editing
	 * @param question The chosen question from the tableview
	 */
	public void ShowQuestionAndEditScore(Question question) {
		answer1Rb.setDisable(false);
		answer2Rb.setDisable(false);
		answer3Rb.setDisable(false);
		answer4Rb.setDisable(false);
		questionBodyLbl.setDisable(false);
		scoreLbl.setDisable(false);
		editBtn.setDisable(false);
		editTf.setDisable(false);
		editTf.setText(locateRow.get(questionupdatescore.getQuestionID())); 
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

	/**
	 * @deprecated
	 */
	public void successfulUpdateQuestionInExam(String Msg) {
		msg = Msg;
	}

}
