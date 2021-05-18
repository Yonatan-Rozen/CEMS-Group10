package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentExamResultsController implements Initializable {

	@FXML
	private Button sbGetCopyBtn;

	@FXML
	private Label sbCommentExamResultLbl;

	private static Button getCopyBtn;
	private static Label commentExamResultLbl;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		getCopyBtn = sbGetCopyBtn;
		commentExamResultLbl = sbCommentExamResultLbl;
	}

	@FXML
	void btnPressGetCopy(ActionEvent event) {
		//TODO DOWNLOAD BLOB FILE ?
	}

}
