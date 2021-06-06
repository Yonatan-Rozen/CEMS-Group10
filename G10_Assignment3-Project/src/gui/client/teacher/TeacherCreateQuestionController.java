package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class TeacherCreateQuestionController implements Initializable {
	public static TeacherCreateQuestionController tcqController;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private ChoiceBox<String> sbQuestionSubjectCb;

	@FXML
	private Button sbCreateQuestionBtn;

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private TextArea sbQuestionBodyTa;

	@FXML
	private ToggleGroup sbAnswersTg;

	@FXML
	private RadioButton sbMarkAnswer1Rb;

	@FXML
	private RadioButton sbMarkAnswer2Rb;

	@FXML
	private RadioButton sbMarkAnswer3Rb;

	@FXML
	private RadioButton sbMarkAnswer4Rb;

	@FXML
	private TextArea sbAnswer1Ta;

	@FXML
	private TextArea sbAnswer2Ta;

	@FXML
	private TextArea sbAnswer3Ta;

	@FXML
	private TextArea sbAnswer4Ta;

	@FXML
	private Button sbChangeSubjectBtn;

	@FXML
	private Button sbSaveQuestionBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> questionSubjectCb;
	private static Button createQuestionBtn;
	private static AnchorPane botPanelAp;
	private static TextArea questionBodyTa;
	private static RadioButton markAnswer1Rb;
	private static RadioButton markAnswer2Rb;
	private static RadioButton markAnswer3Rb;
	private static RadioButton markAnswer4Rb;
	private static TextArea answer1Ta;
	private static TextArea answer2Ta;
	private static TextArea answer3Ta;
	private static TextArea answer4Ta;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> subjectList = FXCollections.observableArrayList("----------");
	private static RadioButton selected;
	private static String msg;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcqController = new TeacherCreateQuestionController();
		
		/**** First panel ****/
		topPanelAp = sbTopPanelAp;
		//////////////////////////////////////////////////////
		questionSubjectCb = sbQuestionSubjectCb;
		// set "----------" as the first value of the choice box
		questionSubjectCb.setValue("----------");
		
		// set the choice box to get it's items from 'subjectList'
		questionSubjectCb.setItems(subjectList);

		// set up a listener that sets the disable value of 
		// 'createQuestionBtn' acurding to the selected value
		questionSubjectCb.getSelectionModel().selectedItemProperty().addListener(
			(ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
			{
				if (newValue != null) {
					if (newValue.equals("----------"))
						createQuestionBtn.setDisable(true);
					else
						createQuestionBtn.setDisable(false);
				}
			});
		//////////////////////////////////////////////////////
		createQuestionBtn = sbCreateQuestionBtn;
		createQuestionBtn.setDisable(true);

		/**** Second panel ****/
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		questionBodyTa = sbQuestionBodyTa;
		selected = markAnswer1Rb = sbMarkAnswer1Rb;
		markAnswer2Rb = sbMarkAnswer2Rb;
		markAnswer3Rb = sbMarkAnswer3Rb;
		markAnswer4Rb = sbMarkAnswer4Rb;
		answer1Ta = sbAnswer1Ta;
		answer2Ta = sbAnswer2Ta;
		answer3Ta = sbAnswer3Ta;
		answer4Ta = sbAnswer4Ta;

		if (subjectList.size() == 1) // add subjects only once
			ClientUI.chat.accept(new String[] { "GetSubjects", ChatClient.user.getUsername() });
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressCreateQuestion(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::btnPressCreateQuestion");
		topPanelAp.setDisable(true);
		botPanelAp.setDisable(false);
	}

	@FXML
	public void rbPressMarkAnswer1(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::rbPressMarkAnswer1");
		selected = markAnswer1Rb;
	}

	@FXML
	public void rbPressMarkAnswer2(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::rbPressMarkAnswer2");
		selected = markAnswer2Rb;
	}

	@FXML
	public void rbPressMarkAnswer3(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::rbPressMarkAnswer3");
		selected = markAnswer3Rb;
	}

	@FXML
	public void rbPressMarkAnswer4(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::rbPressMarkAnswer4");
		selected = markAnswer4Rb;
	}

	@FXML
	public void btnPressChangeSubject(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::btnPressChangeSubject");
		topPanelAp.setDisable(false);
		botPanelAp.setDisable(true);
		questionSubjectCb.setValue("----------");
	}

	@FXML
	public void btnPressSaveQuestion(ActionEvent event) throws IOException {
		System.out.println("TeacherCreateQuestion::btnPressSaveQuestion");
		String correctAnswer, author = ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname();
		
		correctAnswer = CommonMethodsHandler.getInstance().getSelectedAnswer(selected);
		
		if (questionBodyTa.getText().equals("") || answer1Ta.getText().equals("") || 
				answer2Ta.getText().equals("") || answer3Ta.getText().equals("") || answer4Ta.getText().equals("")) {
			
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message",
					"Missing questions","All fields are required!").showAndWait();
		}
		else {
			ClientUI.chat.accept(new String[] {"btnPressSaveQuestion", questionSubjectCb.getValue(), questionBodyTa.getText(), answer1Ta.getText(), 
				answer2Ta.getText(), answer3Ta.getText(), answer4Ta.getText(), correctAnswer, ChatClient.user.getUsername(), author});
		
			ButtonType buttonYes = new ButtonType("Yes - Same Subject");
			ButtonType buttonNo = new ButtonType("No - Change Subject");
			Optional<ButtonType> result = CommonMethodsHandler.getInstance().getNewAlert(AlertType.CONFIRMATION, 
					"Successful question creation",msg,"Create another question under the same subject?",buttonYes,buttonNo).showAndWait();
			if (result.get() == buttonYes){
				System.out.println("alert::Yes");
				questionBodyTa.setText("");
				markAnswer1Rb.setSelected(true);
				selected = markAnswer1Rb;
				answer1Ta.setText("");
				answer2Ta.setText("");
				answer3Ta.setText("");
				answer4Ta.setText("");
			} else if (result.get() == buttonNo) {
				System.out.println("alert::No");
				TeacherMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateQuestion"));
			}
		}
	}

	// EXTERNAL USE METHODS **************************************************
	public void setSubjectChoiceBox(List<String> msg) {
		System.out.println(msg.toString());
		subjectList.addAll(msg);
	}

	public void successfulCreateQuestion(String Msg) {
		msg = Msg;
	}

}