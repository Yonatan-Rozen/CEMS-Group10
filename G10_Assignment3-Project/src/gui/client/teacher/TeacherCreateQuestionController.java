package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;

public class TeacherCreateQuestionController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private ComboBox<String> sbQuestionBankCb;

	@FXML
	private Button sbDiscardQuestionBtn;

	@FXML
	private Button sbCreateQuestionBtn;

	@FXML
	private Hyperlink sbAddBankLnk;

	@FXML
	private Hyperlink sbRemoveBankLnk;

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
	private static ComboBox<String> questionBankCb;
	private static Button discardQuestionBtn;
	private static Button createQuestionBtn;
	private static Hyperlink addBankLnk;
	private static Hyperlink removeBankLnk;
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

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// first panel
		topPanelAp = sbTopPanelAp;
		questionBankCb = sbQuestionBankCb;
		removeBankLnk = sbRemoveBankLnk;
		addBankLnk = sbAddBankLnk;
		discardQuestionBtn = sbDiscardQuestionBtn;
		createQuestionBtn = sbCreateQuestionBtn;

		// second panel
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
	}

	// ACTION METHODS *******************************************************
	@FXML
	void cbPressQuestionBank(ActionEvent event) {

	}

	@FXML
	void lnkPressRemoveBank(ActionEvent event) {

	}

	@FXML
	void btnPressDiscardQuestion(ActionEvent event) {

	}

	@FXML
	void btnPressCreateQuestion(ActionEvent event) {
		// example ***********************************************************
		// first panel
		topPanelAp.setDisable(true);
		// second panel
		botPanelAp.setDisable(false);
		// *******************************************************************
	}

	@FXML
	void lnkPressAddBank(ActionEvent event) {

	}

	@FXML
	void rbPressMarkAnswer1(ActionEvent event) {

	}

	@FXML
	void rbPressMarkAnswer2(ActionEvent event) {

	}

	@FXML
	void rbPressMarkAnswer3(ActionEvent event) {

	}

	@FXML
	void rbPressMarkAnswer4(ActionEvent event) {

	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		// example ***********************************************************
		// first panel
		topPanelAp.setDisable(false);
		// second panel
		botPanelAp.setDisable(true);
		questionBodyTa.setText("");
		answer1Ta.setText("");
		answer2Ta.setText("");
		answer3Ta.setText("");
		answer4Ta.setText("");
		markAnswer1Rb.setSelected(true);
		// *******************************************************************
	}

	@FXML
	void btnPressSaveQuestion(ActionEvent event) {

	}

}