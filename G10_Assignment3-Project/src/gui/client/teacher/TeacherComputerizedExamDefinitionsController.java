package gui.client.teacher;

import java.net.URL;
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
	private static List<QuestionInExam> questionList;
	private static String QuestionIDToEdit , questionScoreToEDIT;
	private static String msg;
	private static QuestionInExam questionupdatescore;

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
		questionIDTc = sbQuestionIDTc;
		scoreTc = sbScoreTc;
		editTc = sbEditTc;
		backBtn = sbBackBtn;
//		backBtn.setBackground(Color.red);
		finishBtn = sbFinishBtn;
		editBtn = sbEditBtn;
		totalScoreLbl = sbTotalScoreLbl;
		studentCommentsTa = sbStudentCommentsTa;
		teacherCommentsTa1 = sbTeacherCommentsTa1;
		editTf = sbEditTf;
		CommonMethodsHandler.getInstance().setIntegersOnlyTextLimiter(editTf,3);
		allocatedTimeTf = sbAllocatedTimeTf;
		CommonMethodsHandler.getInstance().setIntegersOnlyTextLimiter(allocatedTimeTf,3);
		editBtn.setDisable(true);
		editTf.setDisable(true);
		totalScoreLbl.setText("0");
		// set up table view
		questionIDTc.setCellValueFactory(new PropertyValueFactory<QuestionInExam, String>("questionID"));
		// set up table view
		scoreTc.setCellValueFactory(new PropertyValueFactory<QuestionInExam, String>("questionScore"));

		//////
		// need to show score column (set to zero and when we edit(with sql query) to
		////// show new
		// value in table)
		//////

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
								QuestionIDToEdit = question.getQuestionID(); //save id
//								questionScoreToEDIT = question.getQuestionScore(); //save score
								questionupdatescore = getTableRow().getItem();
								ShowQuestionAndEditScore(question);
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
		Optional<ButtonType> request = CommonMethodsHandler.getInstance().getNewAlert(AlertType.CONFIRMATION, "Discard exam", "Are you sure you want to cancel the creation of the current exam?",buttonYes,buttonNo).showAndWait();
		if (request.get().equals(buttonYes)) {

			TeacherMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateExam"));
			scoreQuestionsTv.setItems(null);
//			questionInExam.clear();
		}
	}

	@FXML
	void btnPressEdit(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressEdit");
		ClientUI.chat.accept(new String[] { "UpdateQuestionAndScoreToExam", ExamID, editTf.getText(), QuestionIDToEdit,
				ChatClient.user.getUsername() });

		////there is bug to fix (with remove and add to table)
		questionObservableList.remove(questionupdatescore); //remove
		questionupdatescore.setQuestionScore(editTf.getText()); //update
		questionObservableList.add(questionupdatescore); //add
		scoreQuestionsTv.setItems(questionObservableList); //update table with new value
		
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

		totalScoreLbl.setText(String.valueOf(total));

//		scoreQuestionsTv.setItems(questionObservableList);

	}

	@FXML
	void btnPressFinish(ActionEvent event) {
		System.out.println("TeacherComputerizedExamDefinitions::btnPressFinish");
		String insertTime = allocatedTimeTf.getText();
		if (!insertTime.equals("Enter Time!")) {
			if (Integer.parseInt(insertTime) > 0) {
				ClientUI.chat.accept(new String[] { "btnPressFinishCreateComputerizedExam", ExamID,
						studentCommentsTa.getText(), teacherCommentsTa1.getText(), allocatedTimeTf.getText(), "1",
						ChatClient.user.getUsername() });

				//////
				// pop-up message that computerized exam success or something like that
				//////
				TeacherMenuBarController.mainPaneBp
						.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateExam"));
			} else {
				CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Negative/zero time",
						"Must to choose positive value for allocated time").showAndWait();
			}
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
		editTf.setDisable(false);
		editTf.setText("---");

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

	public void successfulUpdateQuestionInExam(String Msg) {
		msg = Msg;
	}

}
