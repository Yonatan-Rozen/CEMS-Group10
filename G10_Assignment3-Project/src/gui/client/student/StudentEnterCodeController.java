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
import javafx.scene.control.TextField;

public class StudentEnterCodeController implements Initializable {
	
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Button sbStartExamBtn;

	@FXML
	private TextField sbCodeTf;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Button startExamBtn;
	private static TextField codeTf;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startExamBtn = sbStartExamBtn;
		codeTf = sbCodeTf;
	}
	
	// ACTION METHODS *******************************************************
	@FXML
	void btnPressStartExam(ActionEvent event) throws IOException {
		//TODO go to [Computerized / Manual] exam screen
		//computerized examp example ****************************************
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentTakeComputerizedExam.fxml")));
		ClientUI.mainStage.setWidth(900);
		ClientUI.mainStage.setHeight(650);
		//*******************************************************************
	}
}
