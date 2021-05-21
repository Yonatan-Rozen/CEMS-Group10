package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class StudentTakeExamManuallyController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Hyperlink sbDownloadExamFileLnk;

	@FXML
	private Button sbSubmitExamBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink downloadExamFileLnk;
	private static Button submitExamBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		downloadExamFileLnk = sbDownloadExamFileLnk;
		submitExamBtn = sbSubmitExamBtn;
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
	}
}
