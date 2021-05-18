package gui.client.student;

import java.io.IOException;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.Button;

public class StudentExamSubmittedController {

	@FXML
	private Button sbBackToMenuBtn;

	@FXML
	void btnPressBackToMenu(ActionEvent event) throws IOException {
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
	}

}
