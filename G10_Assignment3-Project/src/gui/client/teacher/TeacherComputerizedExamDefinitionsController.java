package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.question.Question;

public class TeacherComputerizedExamDefinitionsController implements Initializable {
	public static TeacherComputerizedExamDefinitionsController tcedController;

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
	private TableView<Question> sbAvailableQuestionsTv;

	@FXML
	private TableColumn<Question, String> sbQuestionID1Tc;

	@FXML
	private TableColumn<Question, Void> sbPreview1Tc;

	@FXML
	private TableColumn<Question, Void> sbAddToExamTc;

	@FXML
	private TableView<Question> sbCurrentQuestionsTable;

	@FXML
	private TableColumn<Question, String> sbQuestionID2Tc;

	@FXML
	private TableColumn<Question, Void> sbPreview2Tc;

	@FXML
	private TableColumn<Question, Void> sbRemoveFromExamTc;

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
	private static TableView<Question> availableQuestionsTv;
	private static TableColumn<Question, String> questionID1Tc;
	private static TableColumn<Question, Void> preview1Tc;
	private static TableColumn<Question, Void> addToExamTc;
	private static TableView<Question> currentQuestionsTable;
	private static TableColumn<Question, String> questionID2Tc;
	private static TableColumn<Question, Void> preview2Tc;
	private static TableColumn<Question, Void> removeFromExamTc;
	private static Button changeBankBtn;
	private static Button continue2Btn;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");
	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
	private static List<Question> questionList;
	ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
	private static String msg;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcedController = new TeacherComputerizedExamDefinitionsController();

		/**** First panel ****/
		topPanelAp = sbTopPanelAp;

		examBankCb = sbExamBankCb;
		// set "----------" as the first value of the choice box
		examBankCb.setValue("----------");
		// set the choice box to get it's items from 'bankList'
		examBankCb.setItems(bankList);

		continue1Btn = sbContinue1Btn;
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		chooseCourseCb = sbChooseCourseCb;
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

		if (bankList.size() == 1) { // add banks only once
			ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername() });
		}
	}

	// ACTION METHODS *******************************************************
//	@FXML
//	void btnPressCancelCreation(ActionEvent event) {
//		System.out.println("TeacherCreateExam::btnPressCancelCreation");
//	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		System.out.println("TeacherCreateExam::btnPressChangeBank");
		sbTopPanelAp.setDisable(false);
		sbBotPanelAp.setDisable(true);
		examBankCb.setValue("----------");
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {

	}

	@FXML
	void btnPressContinue2(ActionEvent event) throws Exception {
}

//	@FXML
//	void btnPressPreviewExam1(ActionEvent event) {
//		System.out.println("TeacherCreateExam::btnPressPreviewExam1");
//	}
//
//	@FXML
//	void btnPressPreviewExam2(ActionEvent event) {
//		System.out.println("TeacherCreateExam::btnPressPreviewExam2");
//	}

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

	public void addQuestionToCurrentQuestions(Question question) {
		questionObservableList.add(question);
		currentQuestionsTable.setItems(questionObservableList);
	}
	
	public void removeQuestionFromCurrentQuestions(Question question) {
		questionObservableList.remove(question);
		currentQuestionsTable.setItems(questionObservableList);
	}
	
	public void setQuestionTableView(List<Question> questions) {
		questionList = questions;
		ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
		questionObservableList.addAll(questions);
		availableQuestionsTv.setItems(questionObservableList);
	}
	
	public void successfulCreateExam(String Msg) {
		msg = Msg;
	}

}
