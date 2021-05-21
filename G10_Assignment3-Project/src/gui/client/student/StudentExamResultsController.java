package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentExamResultsController implements Initializable {
	
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Button sbGetCopyBtn;

	@FXML
	private Label sbCommentExamResultLbl;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Button getCopyBtn;
	private static Label commentExamResultLbl;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getCopyBtn = sbGetCopyBtn;
		commentExamResultLbl = sbCommentExamResultLbl;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressGetCopy(ActionEvent event) {
		//TODO DOWNLOAD BLOB FILE ?
		System.out.println("StudentExamResults::btnPressGetCopy");
	}

}
