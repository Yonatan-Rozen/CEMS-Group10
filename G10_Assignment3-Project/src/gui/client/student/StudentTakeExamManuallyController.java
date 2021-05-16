package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;

public class StudentTakeExamManuallyController implements Initializable {

	@FXML
	private Hyperlink sbDownloadExamFileLnk;

	@FXML
	private Button sbSubmitExamBtn;

	private static Hyperlink downloadExamFileLnk;
	private static Button submitExamBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		downloadExamFileLnk = sbDownloadExamFileLnk;
		submitExamBtn = sbSubmitExamBtn;
	}

	@FXML
	void btnPressSubmitExam(ActionEvent event) {
		//TODO take care of pane for uploading BLOB file
		//TODO prompt message "Are you sure you want to submit?"
		//TODO go to "exam submitted successfully"
		//TODO go to main menu
	}

	@FXML
	void lnkPressDownloadExamFile(ActionEvent event) {
		//TODO BLOB stuff
	}
}
