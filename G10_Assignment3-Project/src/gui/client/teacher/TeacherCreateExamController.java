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

public class TeacherCreateExamController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private Button sbContinue2Btn;

	@FXML
	private Button sbChangeBankBtn;

	@FXML
	private Button sbPreviewExam1Btn;

	@FXML
	private TableView<?> sbAvailableQuestionsTv;

	@FXML
	private TableColumn<?, ?> sbQuestionID1Tc;

	@FXML
	private TableColumn<?, ?> sbAddToExamTc;

	@FXML
	private TableView<?> sbCurrentQuestionsTable;

	@FXML
	private TableColumn<?, ?> sbQuestionID2Tc;

	@FXML
	private TableColumn<?, ?> sbRemoveFromExamTc;

	@FXML
	private ComboBox<?> sbChooseCourseBtn;

	@FXML
	private Button sbEditExamsBtn;

	@FXML
	private Button sbPreviewExam2Btn;

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private Button sbContinue1Btn;

	@FXML
	private Button sbCancelCreationBtn;

	@FXML
	private ComboBox<?> sbChooseBankCb;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static Button continue2Btn;
	private static Button changeBankBtn;
	private static Button previewExam1Btn;
	private static TableView<?> availableQuestionsTv;
	private static TableColumn<?, ?> questionID1Tc;
	private static TableColumn<?, ?> addToExamTc;
	private static TableView<?> currentQuestionsTable;
	private static TableColumn<?, ?> questionID2Tc;
	private static TableColumn<?, ?> removeFromExamTc;
	private static ComboBox<?> chooseCourseBtn;
	private static Button editExamsBtn;
	private static Button previewExam2Btn;
	private static AnchorPane topPanelAp;
	private static Button continue1Btn;
	private static Button cancelCreationBtn;
	private static ComboBox<?> chooseBankCb;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		botPanelAp = sbBotPanelAp;
		sbBotPanelAp.setDisable(true);
		continue2Btn = sbContinue2Btn;
		changeBankBtn = sbChangeBankBtn;
		previewExam1Btn = sbPreviewExam1Btn;
		availableQuestionsTv = sbAvailableQuestionsTv;
		questionID1Tc = sbQuestionID1Tc;
		addToExamTc = sbAddToExamTc;
		currentQuestionsTable = sbCurrentQuestionsTable;
		questionID2Tc = sbQuestionID2Tc;
		removeFromExamTc = sbRemoveFromExamTc;
		chooseCourseBtn = sbChooseCourseBtn;
		editExamsBtn = sbEditExamsBtn;
		previewExam2Btn = sbPreviewExam2Btn;
		topPanelAp = sbTopPanelAp;
		continue1Btn = sbContinue1Btn;
		cancelCreationBtn = sbCancelCreationBtn;
		chooseBankCb = sbChooseBankCb;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressCancelCreation(ActionEvent event) {
		
	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		sbTopPanelAp.setDisable(false);
		
		sbBotPanelAp.setDisable(true);
	}

	@FXML
	void btnPressChooseCourse(ActionEvent event) {

	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		sbTopPanelAp.setDisable(true);
		
		sbBotPanelAp.setDisable(false);
	}

	@FXML
	void btnPressContinue2(ActionEvent event) {

	}

	@FXML
	void btnPressEditExams(ActionEvent event) {

	}

	@FXML
	void btnPressPreviewExam1(ActionEvent event) {

	}

	@FXML
	void btnPressPreviewExam2(ActionEvent event) {

	}

	@FXML
	void cbPressChooseBank(ActionEvent event) {

	}
}
