package gui.client.student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class StudentExamSubmittedController implements Initializable{

	@FXML
	private Button sbBackToMenuBtn;

	@FXML
	void btnPressBackToMenu(ActionEvent event) throws IOException {
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.setWidth(750);
		ClientUI.mainStage.setHeight(400);
	}

}
