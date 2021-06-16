package gui.client.principle;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

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
import javafx.scene.control.cell.PropertyValueFactory;
import logic.exam.IExam;

public class PrincipleViewExamsInfoScreenController implements Initializable{

	// JAVAFX INSTNCES ******************************************************
	//------->first table view
	@FXML
	private TableView<IExam> tblVExamsDetails1;

	@FXML
	private TableColumn<IExam, String> sbExamsIDClm;

	@FXML
	private TableColumn<IExam, String> sbUserNameClm;

	@FXML
	private TableColumn<IExam, String> sbCourseIDClm;

	@FXML
	private TableColumn<IExam, String> sbSubjectIDClm;

	@FXML
	private TableColumn<IExam, String> sbAllocatedTimeClm;

	@FXML
	private TableColumn<IExam, String> sbStudentsCommentsClm;

	@FXML
	private TableColumn<IExam, String> sbTeachersCommentsClm;

	@FXML
	private TableColumn<IExam, String> sbExamTypeClm;

	//------->second table view
	@FXML
	private TableView<IExam> tblVExamsDetails2;

	@FXML
	private TableColumn<IExam, String> sbExamsID_1Clm;

	@FXML
	private TableColumn<IExam, String> sbDateClm;

	@FXML
	private TableColumn<IExam, String> sbDurationClm;

	@FXML
	private TableColumn<IExam, String> sbAllStudentsClm;

	@FXML
	private TableColumn<IExam, String> sbNumSubmitted_1Clm;

	@FXML
	private TableColumn<IExam, String> sbNumSubmitted_0Clm;

	@FXML
	private Button sbBackToViewInfoBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView <IExam> tblE1;
	private static TableView <IExam> tblE2;
	public static PrincipleViewExamsInfoScreenController pveisController;
	private static ObservableList<IExam> examsDetails;// = new ArrayList<>();
	private static Button backToViewInfoBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblE1 = tblVExamsDetails1;
		tblE2 = tblVExamsDetails2;
		examsDetails= FXCollections.observableArrayList();
		pveisController=new PrincipleViewExamsInfoScreenController();
		backToViewInfoBtn=sbBackToViewInfoBtn;
		// set up columns
		sbExamsIDClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("examID"));
		sbUserNameClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("author"));
		sbCourseIDClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("courseID"));
		sbSubjectIDClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("subjectID"));
		sbAllocatedTimeClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("allocatedTime"));
		sbStudentsCommentsClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("studentComments"));
		sbTeachersCommentsClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("teacherComments"));
		sbExamTypeClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("type"));
		sbExamsID_1Clm.setCellValueFactory(new PropertyValueFactory<IExam, String>("examID"));
		sbDateClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("date"));
		sbDurationClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("duration"));
		sbAllStudentsClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("numtotalStudents"));
		sbNumSubmitted_1Clm.setCellValueFactory(new PropertyValueFactory<IExam, String>("numSubmitted_1"));
		sbNumSubmitted_0Clm.setCellValueFactory(new PropertyValueFactory<IExam, String>("numSubmitted_0"));

		ClientUI.chat.accept(new String[] { "sbViewExamsBtn" });
	}

	@FXML
	void btnPressBackToViewInfo(ActionEvent event) throws IOException {
		PrincipleMenuBarController.	mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewInfo"));
	}

	// EXTERNAL USE METHODS *************************************************

	/**
	 * This method puts the tuples of the exams from the DB into the tableView
	 * @param usersList List<Exam> of the exams in the table from DB
	 */
	//public void addExamToObservableList(List<String> rowInUsersTable)
	public void setExamsInfoList(List<IExam> examsList) {
		examsDetails.clear();
		examsDetails.addAll(examsList);
		try {
			tblE1.setItems(examsDetails);
			tblE2.setItems(examsDetails);
		} catch (IllegalStateException e) {}
	}
}
