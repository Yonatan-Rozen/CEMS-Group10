package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TeacherEditQuestionController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private Button sbEditQuestionBtn;

	@FXML
	private TableView<String> sbAvailableQuestionsTv;

	@FXML
	private TableColumn<?, String> sbQuestionIDTc;

	@FXML
	private Button sbChangeBankBtn;

	@FXML
	private Button sbPreviewQuestionBtn;

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private ComboBox<String> sbChooseBankCb;

	@FXML
	private Button sbShowQuestionsByBankBtn;

	@FXML
	private Button sbDiscardChangesBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static Button editQuestionBtn;
	private static TableView<String> availableQuestionsTv;
	private static TableColumn<?, String> questionIDTc;
	private static Button changeBankBtn;
	private static Button previewQuestionBtn;
	private static AnchorPane topPanelAp;
	private static ComboBox<String> chooseBankCb;
	private static Button showQuestionsByBankBtn;
	private static Button discardChangesBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		topPanelAp = sbTopPanelAp;
		chooseBankCb = sbChooseBankCb;
		discardChangesBtn = sbDiscardChangesBtn;
		showQuestionsByBankBtn = sbShowQuestionsByBankBtn;
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		editQuestionBtn = sbEditQuestionBtn;
		availableQuestionsTv = sbAvailableQuestionsTv;
		questionIDTc = sbQuestionIDTc;
		changeBankBtn = sbChangeBankBtn;
		previewQuestionBtn = sbPreviewQuestionBtn;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void cbPressChooseBank(ActionEvent event) {

	}

	@FXML
	void btnPressDiscardChanges(ActionEvent event) {

	}

	@FXML
	void btnPressShowQuestionsByBank(ActionEvent event) {
		//example ***********************************************************
		// first panel
		topPanelAp.setDisable(true);
		// second panel
		botPanelAp.setDisable(false);
		//*******************************************************************
	}

	@FXML
	void btnPressPreviewQuestion(ActionEvent event) {

	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		//example ***********************************************************
		// first panel
		topPanelAp.setDisable(false);
		// second panel
		botPanelAp.setDisable(true);
		//*******************************************************************

	}

	@FXML
	void btnPressEditQuestion(ActionEvent event) {

	}

}
