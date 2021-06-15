package gui.client.student;

import java.awt.Desktop;
import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.TextField;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.exam.ManualExam;

public class StudentTakeExamManuallyController implements Initializable {
	public static StudentTakeExamManuallyController stemController=new StudentTakeExamManuallyController();;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Hyperlink sbDownloadExamFileLnk;

	@FXML
	private Button sbSubmitExamBtn;

	@FXML
	private Button sbSearchBtn;

	@FXML
	private TextField sbUploadFileTf;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink downloadExamFileLnk;
	private static Button submitExamBtn;
	private static TextField uploadFileTf;
	private static Button searchBtn;

	// STATIC INSTANCES *****************************************************
	private static String examID;
	//private static Blob manualExamRecived;
	//maybe need get the file into this param?
	private static ManualExam exam;
	FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	private static String FileName;
	private static String FilePath;
	private static long startTime;
	private static long estimatedTime;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		downloadExamFileLnk = sbDownloadExamFileLnk;
		submitExamBtn = sbSubmitExamBtn;
		//stemController
		searchBtn = sbSearchBtn;
		uploadFileTf = sbUploadFileTf;
		startTime = System.nanoTime();
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressSubmitExam(ActionEvent event) throws IOException {
		System.out.println("StudentTakeExamManually::btnPressSubmitExam");
		//TODO take care of pane for uploading BLOB file by dragging the file ?

		//TODO prompt message "Are you sure you want to submit?"
		//TODO go to "exam submitted successfully"
		System.out.println("StudentTakeComputerizedExam::btnPressSubmit");
		estimatedTime = System.nanoTime() - startTime; // elapsed time in nanoseconds
		//convert to minutes
		//There are 60,000,000,000 nanosecond in 1 minute.
		estimatedTime=estimatedTime/600000;
		estimatedTime=estimatedTime/100000;
		//TODO if the teacher pressed "lock exam" the submit button has to be disabled, the exam has to be submitted as is automatically, and the time must be
		//calculated for all the students that are still connected
		System.out.println("before the query of submit");
		// successful submit example ***********************************
		System.out.println("before going to update : estimatedTime = "+String.format("%d", estimatedTime));
		ClientUI.chat.accept(new String[] { "btnPressSubmitManual","successful", String.format("%d", estimatedTime), ChatClient.user.getUsername(), examID ,exam.getAllocatedTime()});
		System.out.println("in the middle of the query of submit");

		ClientUI.chat.accept(new String[] { "StudentUploadFile", examID, FilePath,"S",ChatClient.user.getUsername() });
		System.out.println("after the query of submit");

		// TODO maybe add alert "are you sure you want to submit?"
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentExamSubmitted.fxml")));
		//TODO go to main menu
		//TeacherStartExamController.tseController.studentsInExam--;
		ClientUI.chat.accept(new String[] {"SendMessageDecNumStudentsInExam",StudentMenuController.examiningTeacherID}); // TODO (Decrements the amount of students that are in the running exam)
	}


	@FXML
	void lnkPressDownloadExamFile(ActionEvent event) {
		System.out.println("StudentTakeExamManually::lnkPressDownloadExamFile");
		//TODO BLOB stuff
		ClientUI.chat.accept(new String[] {"lnkPressDownloadExamFile",examID});
		//TODO add method  of getFile from DB
		//added table of manual exams
	}

	@FXML
	void btnPressSearch(ActionEvent event) {
		System.out.println("StudentTakeExamManually::btnPressSearch");
		fileChooser.setTitle("Choose Exam File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("docx Files", "*.docx"),
				new FileChooser.ExtensionFilter("doc Files", "*.doc"));
		try {
			File selectedFile = fileChooser.showOpenDialog(new Stage());
			if (selectedFile != null) {

				FileName = selectedFile.getName();
				FilePath = selectedFile.getPath();
				uploadFileTf.setText(FilePath);
			}
		} catch (RuntimeException e) { e.printStackTrace(); }
	}
	// EXTERNAL USE METHODS *************************************************

	/**
	 * *
	 * @param examIDFromTeacher the running exam ID sent from the teacher
	 */
	public void setExamID(String examIDFromTeacher) {
		if (examIDFromTeacher != null && !examIDFromTeacher.equals("")  )
			//TODO get examID from teacher to all connected students
			examID = examIDFromTeacher;
		else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Code inserting failed",
					"There was no examID chosen by a teacher").showAndWait();
		}
	}

	/**
	 *
	 * @param examTupple one exam from the exams table, with all it's fields
	 */
	public void setExam(ManualExam examTupple) {
		exam = examTupple;
	}

	//TODO check if works after LOCK EXAM is implemented in Teacher
	public void setSubmitButtonWhenLockInvoked () throws IOException {
		if (StudentMenuController.smController.examLocked) {
			submitExamBtn.setDisable(true);
			// turn it around : diasble the EXAM and FORCE him to press Submit
			System.out.println("StudentTakeComputerizedExam::btnPressSubmit");
			estimatedTime = System.nanoTime() - startTime; // elapsed time in nanoseconds
			//convert to minutes
			//There are 60,000,000,000 nanosecond in 1 minute.
			estimatedTime = estimatedTime / 600000;
			estimatedTime = estimatedTime / 100000;
			ClientUI.mainScene
			.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentExamSubmitted.fxml")));
			//			ClientUI.mainScene
			//			.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
			//			// NOT successful submit - the exam is locked and submitted automatically ***********************************
			// update "submited" column to 1 in DB's exams_results table
			ClientUI.chat.accept(new String[] { "setSubmitButtonWhenLockInvokedManual", "NOT successful",
					String.format("%d", estimatedTime), ChatClient.user.getUsername(), examID,exam.getAllocatedTime() });

			//TeacherStartExamController.tseController.studentsInExam--;
			ClientUI.chat.accept(new String[] {"SendMessageDecNumStudentsInExam",StudentMenuController.examiningTeacherID}); // TODO (Decrements the amount of students that are in the running exam)
		}
	}
}
