package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.HashMap;
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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableRow;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.question.Question;
import logic.question.QuestionInExam;

public class TeacherCreateExamController implements Initializable {
	public static TeacherCreateExamController tceController;

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private Button sbContinue1Btn;

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private ChoiceBox<String> sbChooseCourseCb;

	@FXML
	private ChoiceBox<String> sbExamBankCb;

	@FXML
	private TableView<QuestionInExam> sbAvailableQuestionsTv;

	@FXML
	private TableColumn<QuestionInExam, String> sbQuestionID1Tc;

	@FXML
	private TableColumn<QuestionInExam, Void> sbPreview1Tc;

	@FXML
	private TableColumn<QuestionInExam, Void> sbAddToExamTc;

	@FXML
	private TableView<QuestionInExam> sbCurrentQuestionsTable;

	@FXML
	private TableColumn<QuestionInExam, String> sbQuestionID2Tc;

	@FXML
	private TableColumn<QuestionInExam, Void> sbPreview2Tc;

	@FXML
	private TableColumn<QuestionInExam, Void> sbRemoveFromExamTc;

	@FXML
	private Button sbChangeBankBtn;

	@FXML
	private Button sbContinue2Btn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> examBankCb;
	private static Button continue1Btn;
	private static AnchorPane botPanelAp;
	private static ChoiceBox<String> chooseCourseCb;
	private static TableView<QuestionInExam> availableQuestionsTv;
	private static TableColumn<QuestionInExam, String> questionID1Tc;
	private static TableColumn<QuestionInExam, Void> preview1Tc;
	private static TableColumn<QuestionInExam, Void> addToExamTc;
	private static TableView<QuestionInExam> currentQuestionsTable;
	private static TableColumn<QuestionInExam, String> questionID2Tc;
	private static TableColumn<QuestionInExam, Void> preview2Tc;
	private static TableColumn<QuestionInExam, Void> removeFromExamTc;
	private static Button changeBankBtn;
	private static Button continue2Btn;

	// STATIC INSTANCES *****************************************************
	private static CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();
	public static ObservableList<String> bankList = FXCollections.observableArrayList();
	public static ObservableList<String> CourseList = FXCollections.observableArrayList("--------------------");
	private static List<QuestionInExam> questionList;
	private static List<QuestionInExam> questionInExam  = new ArrayList<>();
	private ObservableList<QuestionInExam> questionObservableList = FXCollections.observableArrayList();
	private static HashMap<QuestionInExam, TableRow<QuestionInExam>> locateRow = new HashMap<>();
	private static String msg;
	public static TableView<QuestionInExam> xx;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tceController = new TeacherCreateExamController();

		/**** First panel ****/
		topPanelAp = sbTopPanelAp;

		examBankCb = sbExamBankCb;
		// set "----------" as the first value of 'examBankCb'
		bankList.clear();
		bankList.add("-------------");
		examBankCb.setValue("-------------");

		// set the choice box to get it's items from 'bankList'
		examBankCb.setItems(bankList);

		continue1Btn = sbContinue1Btn;
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		chooseCourseCb = sbChooseCourseCb;
		chooseCourseCb.setValue("--------------------");
		chooseCourseCb.setItems(CourseList);
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
		cmh.disableTableColumnSwap(availableQuestionsTv);
		cmh.disableTableColumnSwap(currentQuestionsTable);
		//if (bankList.size() == 1) { // add banks only once
		ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername(), "1" });
		//}
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressChangeBank(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressChangeBank");
		sbTopPanelAp.setDisable(false);
		sbBotPanelAp.setDisable(true);
		examBankCb.setValue("-------------");
		chooseCourseCb.setValue("--------------------");
		availableQuestionsTv.setItems(null);
		currentQuestionsTable.setItems(null);
		questionInExam.clear();
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressContinue1");

		if (examBankCb.getValue() != "-------------") {
			sbTopPanelAp.setDisable(true);
			sbBotPanelAp.setDisable(false);
			
			// reset 'chooseCourseCb'
			CourseList.clear();
			CourseList.add("--------------------");
			chooseCourseCb.setValue("--------------------");
			chooseCourseCb.setItems(CourseList);
			
			// request course list under the chosen subject
			ClientUI.chat.accept(new String[] { "GetCourseBySubject", examBankCb.getValue(), ChatClient.user.getUsername(), "1" });
			
			// request exam list under the chosen subject
			ClientUI.chat.accept(new String[] { "btnPressShowQuestionsBySubject", examBankCb.getValue(), "2", ChatClient.user.getUsername() });

			// set up table view
			questionID1Tc.setCellValueFactory(new PropertyValueFactory<QuestionInExam, String>("questionID"));
			

			// set preview col
			Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>> btnCellFactory = new Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>>() {
				@Override
				public TableCell<QuestionInExam, Void> call(final TableColumn<QuestionInExam, Void> param) {
					final TableCell<QuestionInExam, Void> cell1 = new TableCell<QuestionInExam, Void>() {

						private final Button btn = new Button();
						private final ImageView previewEye = new ImageView(new Image("/previewEye.png"));

						@Override
						public void updateItem(Void item, boolean empty) {
							super.updateItem(item, empty);
							btn.setStyle("-fx-background-color: transparent;");
							btn.setPrefSize(40, 20);
							previewEye.setPreserveRatio(true);
							previewEye.setFitHeight(20);
							previewEye.setFitWidth(40);
							btn.setGraphic(previewEye);
							if (empty) {
								setGraphic(null);
							} else {
								btn.setOnAction(e -> {
									Question question = getTableRow().getItem();
									TeacherMenuBarController.mainPaneBp.setDisable(true);
									TeacherMenuBarController.menuBarAp.setDisable(true);
									chooseQuestionToPreview(question);
								});
								setGraphic(btn);
							}
						}
					};
					cell1.setAlignment(Pos.CENTER);
					return cell1;
				}
			};
			preview1Tc.setCellFactory(btnCellFactory);

			// set button cells for the 'Update Time' Column
			Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>> btnCellFactory2 = new Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>>() {

				@Override
				public TableCell<QuestionInExam, Void> call(final TableColumn<QuestionInExam, Void> param2) {
					final TableCell<QuestionInExam, Void> cell2 = new TableCell<QuestionInExam, Void>() {

						private final Button btn = new Button();
						private final ImageView addicon = new ImageView(new Image("/icon_add.png"));

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
									locateRow.put(question, getTableRow()); // TODO <-->
									getTableRow().setDisable(true);
									addQuestionToCurrentQuestions(question);
								});
								setGraphic(btn);
							}
						}
					};
					cell2.setAlignment(Pos.CENTER);
					return cell2;
				}
			};
			addToExamTc.setCellFactory(btnCellFactory2);

			// set up current table view
			questionID2Tc.setCellValueFactory(new PropertyValueFactory<QuestionInExam, String>("questionID"));

			// set preview col
			Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>> btnCellFactory3 = new Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>>() {
				@Override
				public TableCell<QuestionInExam, Void> call(final TableColumn<QuestionInExam, Void> param3) {
					final TableCell<QuestionInExam, Void> cell3 = new TableCell<QuestionInExam, Void>() {

						private final Button btn = new Button();
						private final ImageView previewEye = new ImageView(new Image("/previewEye.png"));

						@Override
						public void updateItem(Void item, boolean empty) {
							super.updateItem(item, empty);
							btn.setStyle("-fx-background-color: transparent;");
							btn.setPrefSize(40, 20);
							previewEye.setPreserveRatio(true);
							previewEye.setFitHeight(20);
							previewEye.setFitWidth(40);
							btn.setGraphic(previewEye);
							if (empty) {
								setGraphic(null);
							} else {
								btn.setOnAction(e -> {
									QuestionInExam question = getTableRow().getItem();
									TeacherMenuBarController.mainPaneBp.setDisable(true);
									TeacherMenuBarController.menuBarAp.setDisable(true);
									chooseQuestionToPreview(question);
								});
								setGraphic(btn);
							}
						}
					};
					cell3.setAlignment(Pos.CENTER);
					return cell3;
				}
			};
			preview2Tc.setCellFactory(btnCellFactory3);

			// set button cells for the 'Update Time' Column
			Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>> btnCellFactory4 = new Callback<TableColumn<QuestionInExam, Void>, TableCell<QuestionInExam, Void>>() {

				@Override
				public TableCell<QuestionInExam, Void> call(final TableColumn<QuestionInExam, Void> param4) {
					final TableCell<QuestionInExam, Void> cell4 = new TableCell<QuestionInExam, Void>() {

						private final Button btn = new Button();
						private final ImageView removeicon = new ImageView(new Image("/icon_remove.png"));

						@Override
						public void updateItem(Void item, boolean empty) {
							super.updateItem(item, empty);
							btn.setStyle("-fx-background-color: transparent;");
							btn.setPrefSize(40, 20);
							removeicon.setPreserveRatio(true);
							removeicon.setFitHeight(20);
							removeicon.setFitWidth(40);
							btn.setGraphic(removeicon);
							if (empty) {
								setGraphic(null);
							} else {
								btn.setOnAction(e -> {
									QuestionInExam question = getTableRow().getItem();
									locateRow.get(question).setDisable(false); // TODO <-->
									questionInExam.remove(question);
									removeQuestionFromCurrentQuestions(question);
								});
								setGraphic(btn);
							}
						}
					};
					cell4.setAlignment(Pos.CENTER);
					return cell4;
				}
			};
			removeFromExamTc.setCellFactory(btnCellFactory4);

		} else {
			cmh.getNewAlert(AlertType.ERROR, "Error message",
					"Missing Subject Name", "Please choose a subject from the list.").showAndWait();
		}
	}

	@FXML
	void btnPressContinue2(ActionEvent event) throws Exception {
		System.out.println("TeacherCreateExam::btnPressContinue2");
		String correctAnswer, author = ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname();

		if (chooseCourseCb.getValue() != "--------------------") {
			if (!questionObservableList.isEmpty()) {
				
				TeacherMenuBarController.mainPaneBp.setCenter(cmh.getPane("teacher", "TeacherComputerizedExamDefinitions"));
				
				ClientUI.chat.accept(new String[] { "btnPressContinue2CreateExam", chooseCourseCb.getValue(),
						examBankCb.getValue(), author, ChatClient.user.getUsername() }); // chooseCourseCb.getValue() & examBankCb.getValue() are sometimes null
				
				/* Inserting info should be at the very end with all the details, to eliminate cases where the user doesn't finish the proccess of creating the exam
				 Not doing so might lead to even more complicated code because then there is a need in removing half pieces of info - Yonatan*/

			} else {
				cmh.getNewAlert(AlertType.ERROR, "Error message", "Missing questions", "Please add atleast ONE question from the left table.").showAndWait();
			}
		} else {
			cmh.getNewAlert(AlertType.ERROR, "Error message", "Missing Course", "Please choose a course from the list.").showAndWait();
		}
	}

	// EXTERNAL USE METHODS **************************************************
	public void setBankChoiceBox(List<String> msg) {
		System.out.println(msg.toString());
		bankList.addAll(msg);
	}

	public void setCourseChoiceBox(List<String> msg) {
		System.out.println(msg.toString());
		CourseList.addAll(msg);
		System.out.println(CourseList);
	}

	public void chooseQuestionToPreview(Question question) {
		try {
			new TeacherPreviewQuestionController().start(new Stage());
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(question);
		TeacherPreviewQuestionController.tpqController.setQuestion(question);
	}

	public void addQuestionToCurrentQuestions(QuestionInExam question) {
		questionObservableList.add(question);
		currentQuestionsTable.setItems(questionObservableList);
		questionInExam = currentQuestionsTable.getItems();
	}

	public void removeQuestionFromCurrentQuestions(QuestionInExam question) {
		questionObservableList.remove(question);
		currentQuestionsTable.setItems(questionObservableList);
		questionInExam = currentQuestionsTable.getItems();
	}

	public void setQuestionTableView(List<QuestionInExam> questions) {
		questionList = questions;
		questionObservableList.clear();
		questionObservableList.addAll(questions);
		availableQuestionsTv.setItems(questionObservableList);
	}

	public void successfulCreateExam(String Msg) {
		msg = Msg;
	}

	public ObservableList<QuestionInExam> getCurrentObservableList() {
		return questionObservableList;
	}

	public List<QuestionInExam> getCurrentList() {
		return questionInExam;
	}

}
