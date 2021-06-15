package gui.client.teacher;

import java.net.URL;
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
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import logic.exam.ComputerizedResults;
import logic.exam.ExamResultOfStudent;
import logic.exam.ManualResults;

public class TeacherCheckExamResultsController implements Initializable {
	public static TeacherCheckExamResultsController tcrController;
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private TableView<ExamResultOfStudent> sbComputerizedResultsTv;

	@FXML
	private TableColumn<ExamResultOfStudent, String> sbExamIDTc;

	@FXML
	private TableColumn<ExamResultOfStudent, String> sbStudentIDTc;

	@FXML
	private TableColumn<ExamResultOfStudent, String> sbTypeTc;

	@FXML
	private Button sdCheckAnswersBtn;

	@FXML
	private AnchorPane sbUploadCopyAp;

	@FXML
	private TextField sbPathTf;

	@FXML
	private Button sbSearchBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView<ExamResultOfStudent> computerizedResultsTv;
	private static TableColumn<ExamResultOfStudent, String> examIDTc;
	private static TableColumn<ExamResultOfStudent, String> studentIDTc;
	private static TableColumn<ExamResultOfStudent, String> typeTc;
	private static AnchorPane uploadCopyAp;
	private static TextField pathTf;
	private static Button searchBtn;

	// STATIC INSTANCES *****************************************************
	private static CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();
	private static ObservableList<ExamResultOfStudent> computerizedExamResults = FXCollections.observableArrayList();;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcrController = new TeacherCheckExamResultsController();
		computerizedResultsTv = sbComputerizedResultsTv;
		examIDTc = sbExamIDTc;
		studentIDTc = sbStudentIDTc;
		typeTc = sbTypeTc;
		uploadCopyAp = sbUploadCopyAp;
		uploadCopyAp.setVisible(false);
		pathTf = sbPathTf;
		searchBtn = sbSearchBtn;
		TeacherMenuBarController.menuBarAp.setDisable(false);
		cmh.disableTableColumnSwap(computerizedResultsTv);
		examIDTc.setCellValueFactory(new PropertyValueFactory<ExamResultOfStudent, String>("examID"));
		studentIDTc.setCellValueFactory(new PropertyValueFactory<ExamResultOfStudent, String>("studentID"));
		typeTc.setCellValueFactory(new PropertyValueFactory<ExamResultOfStudent, String>("type"));
		ClientUI.chat.accept(new String[] { "GetExamResultsByTeacherUsername", ChatClient.user.getUsername() });
		
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressCheckAnswers(ActionEvent event) {

		System.out.println("TeacherCheckComputerizedResultsController::btnPressCheckAnswers");
		ExamResultOfStudent selectedResult = computerizedResultsTv.getSelectionModel().getSelectedItem();
		if (selectedResult instanceof ComputerizedResults) {
			TeacherMenuBarController.mainPaneBp.setCenter(cmh.getPane("teacher", "TeacherCheckAnswers"));
			TeacherCheckAnswersController.tcaController.setExamOfStudentDetails((ComputerizedResults)selectedResult);
		} else if (selectedResult instanceof ManualResults) {
			computerizedResultsTv.setDisable(true);
			uploadCopyAp.setVisible(true);
			
			//TODO download file
			
			
		}
	}

	@FXML
	void btnPressSearch(ActionEvent event) {
		
	}
	// INTERNAL USE METHODS *************************************************

	// EXTERNAL USE METHODS *************************************************
	public void setComputerizedResults(List<ExamResultOfStudent> results) {

		if (results == null || results.contains(null))
			System.err.println("bad info!");
		else {
			computerizedExamResults.clear();
			computerizedExamResults.addAll(results);
			computerizedResultsTv.setItems(computerizedExamResults);
		}

	}
}
