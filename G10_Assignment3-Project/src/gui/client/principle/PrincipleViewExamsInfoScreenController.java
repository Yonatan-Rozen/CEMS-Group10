package gui.client.principle;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.exam.IExam;

public class PrincipleViewExamsInfoScreenController implements Initializable{

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TableView<IExam> tblVExamsDetails;

	@FXML
	private TableColumn<IExam, String> sbExamsIDClm;

	@FXML
	private TableColumn<IExam, String> sbUserNameClm;

	@FXML
	private TableColumn<IExam, String> sbCourseIDClm;

	@FXML
	private TableColumn<IExam, String> sbAllocatedTimeClm;

	@FXML
	private TableColumn<IExam, String> sbStudentsCommentsClm;

	@FXML
	private TableColumn<IExam, String> sbTeachersCommentsClm;

	@FXML
	private TableColumn<IExam, String> sbExamTypeClm;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView <IExam> tblE;
	public static PrincipleViewExamsInfoScreenController pveisController;
	private static ObservableList<IExam> examsDetails;// = new ArrayList<>();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblE = tblVExamsDetails;
		examsDetails= FXCollections.observableArrayList();
		pveisController=new PrincipleViewExamsInfoScreenController();
		// set up columns
		sbExamsIDClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("examID"));
		sbUserNameClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("author"));
		sbCourseIDClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("courseID"));
		sbAllocatedTimeClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("allocatedTime"));
		sbStudentsCommentsClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("studentComments"));
		sbTeachersCommentsClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("teacherComments"));
		sbExamTypeClm.setCellValueFactory(new PropertyValueFactory<IExam, String>("type"));

		ClientUI.chat.accept(new String[] { "sbViewExamsBtn" });
	}

	// EXTERNAL USE METHODS *************************************************

	/**
	 * This method puts the tuples of the exams from the DB into the tableView
	 * @param usersList List<Exam> of the exams in the table from DB
	 */
	//public void addExamToObservableList(List<String> rowInUsersTable)
	public void setExamsInfoList(List<IExam> examsList) {
		{
			examsDetails.addAll(examsList);
			tblE.setItems(examsDetails);
		}
	}
}
