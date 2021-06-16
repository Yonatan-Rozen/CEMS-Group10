package gui.client.student;

import java.awt.Desktop;
import java.io.File;
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
import javafx.stage.DirectoryChooser;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.exam.ExamResultOfStudent;
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
	FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	private static String FileName;
	private static String FilePath;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tableViewExam = sbExamResultsTbl;
		examsDetails = FXCollections.observableArrayList();
		getCopyBtn = sbGetCopyBtn;
		commentExamResultLbl = sbCommentExamResultLbl;
		sbExamIdClm.setCellValueFactory(new PropertyValueFactory<ExamResultsTableStudent, String>("examID"));
		sbCourseNameClm.setCellValueFactory(new PropertyValueFactory<ExamResultsTableStudent, String>("courseName"));
		sbGradeClm.setCellValueFactory(new PropertyValueFactory<ExamResultsTableStudent, String>("grade"));
		System.out.println("StudentExamResultsController :: BEFORE ACCEPT *********************");
		ClientUI.chat.accept(new String[] { "getExamResultsForStudentsExamResults", ChatClient.user.getUsername()});
		System.out.println("StudentExamResultsController :: AFTER ACCEPT *********************");

	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressGetCopy(ActionEvent event) {
		// TODO DOWNLOAD BLOB FILE ?
		System.out.println("StudentExamResults::btnPressGetCopy");
		ExamResultsTableStudent selectedResult = tableViewExam.getSelectionModel().getSelectedItem();
		if(selectedResult.getType().equals("M"))
		{
			//TODO download file
			DirectoryChooser chooser = new DirectoryChooser();
			chooser.setTitle("Save file");
			//File defaultDirectory = new File("D:");
			File defaultDirectory = new File("C:");
			chooser.setInitialDirectory(defaultDirectory); // set default
			try {
				// get folder path
				File selectedDirectory = chooser.showDialog(new Stage());
				if (selectedDirectory != null) {
					FileName = selectedDirectory.getName();
					FilePath = selectedDirectory.getPath(); // path
				//	uploadFileTf.setText(FilePath);
				}
			} catch (RuntimeException e) {
				e.printStackTrace();
			}

			System.out.println("path = " + FilePath);
		ClientUI.chat.accept(new String[] { "lnkPressDownloadExamFile", selectedResult.getExamID() , FilePath , "viewRes", selectedResult.getStudentID()});
		}
		else {// type == "C"
			// TODO go to viwing checked exam FXML
		}
	}

	public void setExamDetails(List<ExamResultsTableStudent> ExamResultsList) {
		examsDetails.clear();
		examsDetails.addAll(ExamResultsList);
		try {
			tableViewExam.setItems(examsDetails);
		} catch (IllegalStateException e) {
		}
		System.out.println("StudentExamResultsController :: AFTER SET EXAM DETAILS");
	}
}
