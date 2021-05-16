package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
	void btnPressTakeExam(ActionEvent event) {
		//TODO go to [Computerized / Manual] exam screen
	}
}
