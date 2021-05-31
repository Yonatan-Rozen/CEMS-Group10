package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.layout.AnchorPane;

public class TeacherChooseEditQuestionController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbTopPanelAp;
	
	@FXML
	private ChoiceBox<String> sbQuestionSubject;

	@FXML
	private Button sbShowQuestionsBySubjectBtn;
	
	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private TableView<String> sbAvailableQuestionsTv;

	@FXML
	private TableColumn<?, String> sbQuestionIDTc;

	@FXML
	private Button sbChangeSubjectBtn;

	@FXML
	private Button sbEditQuestionBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> questionSubject;
	private static Button showQuestionsBySubjectBtn;
	private static AnchorPane botPanelAp;
	private static TableView<String> availableQuestionsTv;
	private static TableColumn<?, String> questionIDTc;
	private static Button changeSubjectBtn;
	private static Button editQuestionBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		/**** First panel ****/
		topPanelAp = sbTopPanelAp;
		questionSubject = sbQuestionSubject;
		showQuestionsBySubjectBtn = sbShowQuestionsBySubjectBtn;
		
		/**** Second panel ****/
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		availableQuestionsTv = sbAvailableQuestionsTv;
		questionIDTc = sbQuestionIDTc;
		changeSubjectBtn = sbChangeSubjectBtn;
		editQuestionBtn = sbEditQuestionBtn;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressShowQuestionsBySubject(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressShowQuestionsByBank");
		topPanelAp.setDisable(true);
		botPanelAp.setDisable(false);
	}

	@FXML
	void btnPressChangeSubject(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressChangeBank");
		topPanelAp.setDisable(false);
		botPanelAp.setDisable(true);
	}

	@FXML
	void btnPressEditQuestion(ActionEvent event) {
		System.out.println("TeacherEditQuestion::btnPressEditQuestion");
	}

}
