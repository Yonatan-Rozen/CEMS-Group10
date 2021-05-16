package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.MenuButton;
import javafx.scene.control.MenuItem;

public class StudentEnterCodeController implements Initializable {
	@FXML
	private MenuButton sbStartExamBtn;

	@FXML
	private MenuItem sbStartExamManuallyBtn;

	@FXML
	private MenuItem sbStartExamComputerizedBtn;

	private static MenuButton startExamBtn;
	private static MenuItem startExamManuallyBtn;
	private static MenuItem startExamComputerizedBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		startExamBtn = sbStartExamBtn;
		startExamManuallyBtn = sbStartExamManuallyBtn;
		startExamComputerizedBtn = sbStartExamComputerizedBtn;
	}
	@FXML
	void btnPressStartExamComputerized(ActionEvent event) {
		//TODO go to computerized exam screen ( insert ID Screen)
	}

	@FXML
	void btnPressStartExamManually(ActionEvent event) {
		//TODO go to manual exam screen
	}


}
