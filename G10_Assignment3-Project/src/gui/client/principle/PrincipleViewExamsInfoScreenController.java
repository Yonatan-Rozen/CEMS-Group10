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
import logic.exam.Exam;

public class PrincipleViewExamsInfoScreenController implements Initializable{

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private TableView<Exam> tblVExamsDetails;

	@FXML
	private TableColumn<Exam, String> sbExamsIDClm;

	@FXML
	private TableColumn<Exam, String> sbUserNameClm;

	@FXML
	private TableColumn<Exam, String> sbCourseIDClm;

	@FXML
	private TableColumn<Exam, String> sbAllocatedTimeClm;

	@FXML
	private TableColumn<Exam, String> sbStudentsCommentsClm;

	@FXML
	private TableColumn<Exam, String> sbTeachersCommentsClm;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TableView <Exam> tblE;
	public static PrincipleViewExamsInfoScreenController pveisController;
	private static ObservableList<Exam> examsDetails;// = new ArrayList<>();


	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tblE = tblVExamsDetails;
		examsDetails= FXCollections.observableArrayList();
		pveisController=new PrincipleViewExamsInfoScreenController();
		// set up columns
		sbExamsIDClm.setCellValueFactory(new PropertyValueFactory<Exam, String>("examID"));
		sbUserNameClm.setCellValueFactory(new PropertyValueFactory<Exam, String>("username"));
		sbCourseIDClm.setCellValueFactory(new PropertyValueFactory<Exam, String>("courseID"));
		sbAllocatedTimeClm.setCellValueFactory(new PropertyValueFactory<Exam, String>("allocatedTime"));
		sbStudentsCommentsClm.setCellValueFactory(new PropertyValueFactory<Exam, String>("studentComments"));
		sbTeachersCommentsClm.setCellValueFactory(new PropertyValueFactory<Exam, String>("teacherComments"));

		ClientUI.chat.accept(new String[] { "sbViewExamsBtn" });
	}

	// EXTERNAL USE METHODS *************************************************

	/**
	 * This method puts the tuples of the exams from the DB into the tableView
	 * @param usersList List<Exam> of the exams in the table from DB
	 */
	//public void addExamToObservableList(List<String> rowInUsersTable)
	public void setExamsInfoList(List<Exam> examsList) {
		{
			//	ObservableList<User> users = FXCollections.observableArrayList();
			for (Exam row : examsList)
				examsDetails.add(row);
			tblE.setItems(examsDetails);
		}
	}
}
