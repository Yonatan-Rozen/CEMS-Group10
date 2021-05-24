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

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Button sbBackToMenuBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.setWidth(1100);
		ClientUI.mainStage.setHeight(600);
	}
	
	// ACTION METHODS *******************************************************
	@FXML
	void btnPressBackToMenu(ActionEvent event) throws IOException {
		System.out.println("StudentExamSubmitted::btnPressBackToMenu");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
	}

}
