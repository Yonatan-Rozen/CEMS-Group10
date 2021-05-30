package gui.client.teacher;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
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
	private ChoiceBox<String> sbQuestionBankCb;

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
	private Button sbChangeBankBtn;

	@FXML
	private Button sbSaveQuestionBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> questionBankCb;
	private static Button createQuestionBtn;
	private static AnchorPane botPanelAp;
	private static TextArea questionBodyTa;
	private static ToggleGroup answersTg;
	private static RadioButton markAnswer1Rb;
	private static RadioButton markAnswer2Rb;
	private static RadioButton markAnswer3Rb;
	private static RadioButton markAnswer4Rb;
	private static TextArea answer1Ta;
	private static TextArea answer2Ta;
	private static TextArea answer3Ta;
	private static TextArea answer4Ta;
	private static Button changeBankBtn;
	private static Button saveQuestionBtn;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcqController = new TeacherCreateQuestionController();
		
		/**** First panel ****/
		topPanelAp = sbTopPanelAp;
		//////////////////////////////////////////////////////
		questionBankCb = sbQuestionBankCb;
		// set "----------" as the first value of the choice box
		questionBankCb.setValue("----------");
		
		// set the choice box to get it's items from 'bankList'
		questionBankCb.setItems(bankList);

		// set up a listener that sets the disable value of 
		// 'createQuestionBtn' acurding to the selected value
		questionBankCb.getSelectionModel().selectedItemProperty().addListener(
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
		answersTg = sbAnswersTg;
		markAnswer1Rb = sbMarkAnswer1Rb;
		markAnswer2Rb = sbMarkAnswer2Rb;
		markAnswer3Rb = sbMarkAnswer3Rb;
		markAnswer4Rb = sbMarkAnswer4Rb;
		answer1Ta = sbAnswer1Ta;
		answer2Ta = sbAnswer2Ta;
		answer3Ta = sbAnswer3Ta;
		answer4Ta = sbAnswer4Ta;
		changeBankBtn = sbChangeBankBtn;
		saveQuestionBtn = sbSaveQuestionBtn;

		if (bankList.size() == 1) // add subjects only once
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
	}

	@FXML
	public void rbPressMarkAnswer2(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::rbPressMarkAnswer2");
	}

	@FXML
	public void rbPressMarkAnswer3(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::rbPressMarkAnswer3");
	}

	@FXML
	public void rbPressMarkAnswer4(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::rbPressMarkAnswer4");
	}

	@FXML
	public void btnPressChangeBank(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::btnPressChangeBank");
		topPanelAp.setDisable(false);
		botPanelAp.setDisable(true);
		questionBodyTa.setText("");
		answer1Ta.setText("");
		answer2Ta.setText("");
		answer3Ta.setText("");
		answer4Ta.setText("");
		markAnswer1Rb.setSelected(true);
	}

	@FXML
	public void btnPressSaveQuestion(ActionEvent event) {
		System.out.println("TeacherCreateQuestion::btnPressSaveQuestion");
	}

	// EXTERNAL USE METHODS **************************************************
	public void setSubjectChoiceBox(List<String> msg) {
		System.out.println(msg.toString());
		bankList.addAll(msg);
	}

}