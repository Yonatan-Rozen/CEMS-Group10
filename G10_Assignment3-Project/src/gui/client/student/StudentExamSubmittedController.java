package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StudentExamSubmittedController implements Initializable {

	@FXML
	private Button sbBackToMenuBtn;

	private static Button backToMenuBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		backToMenuBtn = sbBackToMenuBtn;
	}

	@FXML
	void btnPressBackToMenu(ActionEvent event) {
		//TODO go to menu screen
	}

}
