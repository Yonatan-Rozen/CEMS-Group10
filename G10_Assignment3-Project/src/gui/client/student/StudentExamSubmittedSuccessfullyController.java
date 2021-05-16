package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StudentExamSubmittedSuccessfullyController implements Initializable {

	@FXML
	private Button sbBackToManuBtn;

	private static Button backToManuBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		backToManuBtn = sbBackToManuBtn;
	}

	@FXML
	void btnPressBackToMenu(ActionEvent event) {
		//TODO go to menu screen
	}

}
