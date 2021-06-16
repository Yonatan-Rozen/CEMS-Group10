package gui.client.student;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import logic.exam.ExamResultsTableStudent;

public class StudentExamResultsController implements Initializable {
	
	public static StudentExamResultsController serController=new StudentExamResultsController();
	
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Button sbGetCopyBtn;
	@FXML
	private Label sbCommentExamResultLbl;
	@FXML
	private TableView<ExamResultsTableStudent> sbExamResultsTbl;
	@FXML
	private TableColumn<ExamResultsTableStudent, String> sbExamIdClm;
	@FXML
	private TableColumn<ExamResultsTableStudent, String> sbCourseNameClm;
	@FXML
	private TableColumn<ExamResultsTableStudent, String> sbGradeClm;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Button getCopyBtn;
	private static Label commentExamResultLbl;
	private static TableView<ExamResultsTableStudent> tableViewExam;
	private static ObservableList<ExamResultsTableStudent> examsDetails;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableViewExam = sbExamResultsTbl;
		examsDetails = FXCollections.observableArrayList();
		getCopyBtn = sbGetCopyBtn;
		commentExamResultLbl = sbCommentExamResultLbl;
		sbExamIdClm.setCellValueFactory(new PropertyValueFactory<ExamResultsTableStudent, String>("examID"));
		sbCourseNameClm.setCellValueFactory(new PropertyValueFactory<ExamResultsTableStudent, String>("examID"));
		sbGradeClm.setCellValueFactory(new PropertyValueFactory<ExamResultsTableStudent, String>("examID"));
		
		ClientUI.chat.accept(new String[] { "getExamResultsForStudentsExamResults", ChatClient.user.getUsername()});
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressGetCopy(ActionEvent event) {
		// TODO DOWNLOAD BLOB FILE ?
		System.out.println("StudentExamResults::btnPressGetCopy");
	}

	public void setExamDetails(List<ExamResultsTableStudent> ExamResultsList) {
		examsDetails.clear();
		examsDetails.addAll(ExamResultsList);
		try {
			tableViewExam.setItems(examsDetails);
		} catch (IllegalStateException e) {
		}
	}
}
