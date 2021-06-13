package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TeacherCheckComputerizedResultsController implements Initializable {
	public static TeacherCheckComputerizedResultsController tccrController;
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private TableView<?> sbComputerizedResultsTv;

	@FXML
	private TableColumn<?, ?> sbExamIDTc;

	@FXML
	private TableColumn<?, ?> sbStudentIDTc;

	@FXML
	private TableColumn<?, ?> sbComputerizedGrade;

	@FXML
	private TableColumn<?, ?> sbTeacherGradeTc;

	@FXML
	private Button sbCheckAnswersBtn;

	@FXML
	private Button sbChangeGradeBtn;

	@FXML
	private Button sbAcceptSelected;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView<?> computerizedResultsTv;
	private static TableColumn<?, ?> examIDTc;
	private static TableColumn<?, ?> studentIDTc;
	private static TableColumn<?, ?> computerizedGrade;
	private static TableColumn<?, ?> teacherGradeTc;
	private static Button checkAnswersBtn;
	private static Button changeGradeBtn;
	private static Button acceptSelected;
	
	// STATIC INSTANCES *****************************************************
	private static CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tccrController = new TeacherCheckComputerizedResultsController();
		computerizedResultsTv = sbComputerizedResultsTv;
		examIDTc = sbExamIDTc;
		studentIDTc = sbStudentIDTc;
		computerizedGrade = sbComputerizedGrade;
		teacherGradeTc = sbTeacherGradeTc;
		checkAnswersBtn = sbCheckAnswersBtn;
		changeGradeBtn = sbChangeGradeBtn;
		acceptSelected = sbAcceptSelected;
		
		cmh.disableTableColumnSwap(computerizedResultsTv);
		
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressAcceptSelected(ActionEvent event) {
		System.out.println("TeacherCheckComputerizedResultsController::btnPressAcceptSelected");
	}

	@FXML
	void btnPressChangeGrade(ActionEvent event) {
		System.out.println("TeacherCheckComputerizedResultsController::btnPressChangeGrade");
	}

	@FXML
	void btnPressCheckAnswers(ActionEvent event) {
		System.out.println("TeacherCheckComputerizedResultsController::btnPressCheckAnswers");
	}
	
	
	// INTERNAL USE METHODS *************************************************

	// EXTERNAL USE METHODS *************************************************
}
