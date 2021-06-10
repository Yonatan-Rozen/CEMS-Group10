package gui.client.student;

import java.net.URL;
import java.sql.Blob;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import logic.exam.ManualExam;

public class StudentTakeExamManuallyController implements Initializable {
	public static StudentTakeExamManuallyController stemController;
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Hyperlink sbDownloadExamFileLnk;

	@FXML
	private Button sbSubmitExamBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink downloadExamFileLnk;
	private static Button submitExamBtn;

	// STATIC INSTANCES *****************************************************
	private static String examID;
	private static Blob manualExamRecived;
	//maybe need get the file into this param?
	private static ManualExam exam;



	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		downloadExamFileLnk = sbDownloadExamFileLnk;
		submitExamBtn = sbSubmitExamBtn;
		stemController=new StudentTakeExamManuallyController();
		setExamID(null);

	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressSubmitExam(ActionEvent event) {
		System.out.println("StudentTakeExamManually::btnPressSubmitExam");
		//TODO take care of pane for uploading BLOB file
		//TODO prompt message "Are you sure you want to submit?"
		//TODO go to "exam submitted successfully"
		//TODO go to main menu


	}

	@FXML
	void lnkPressDownloadExamFile(ActionEvent event) {
		System.out.println("StudentTakeExamManually::lnkPressDownloadExamFile");
		//TODO BLOB stuff
		ClientUI.chat.accept(new String[] {"lnkPressDownloadExamFile",examID});
		//TODO add method  of getFile from DB
		//added table of manual exams
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
		else examID="012345"; // default exam
	}

	/**
	 *
	 * @param examTupple one exam from the exams table, with all it's fields
	 */
	public void setExam(ManualExam examTupple) {
		exam = examTupple;
	}
}
