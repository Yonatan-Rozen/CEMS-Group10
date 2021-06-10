package gui.client.student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class StudentEnterCodeController implements Initializable {

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Button sbStartExamBtn;

	@FXML
	private TextField sbCodeTf;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextField codeTf;

	// STATIC INSTANCES ****************************************************
	private static String code;
	private static String examType;
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codeTf = sbCodeTf;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressStartExam(ActionEvent event) throws IOException {
		// go to [Computerized / Manual] exam screen
		System.out.println("StudentEnterCode::btnPressStartExam");

		System.out.println(code);
		if (code.equals(codeTf.getText())) {
			if (examType.equals("computerized"))
				ClientUI.mainScene.setRoot(FXMLLoader
						.load(getClass().getResource("/gui/client/student/StudentTakeComputerizedExam.fxml")));
			// StudentMenuBarController.smbController.mainPaneBp.setCenter(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentTakeComputerizedExam.fxml")));
			if (examType.equals("manual"))
				ClientUI.mainScene.setRoot(
						FXMLLoader.load(getClass().getResource("/gui/client/student/StudentTakeExamManually.fxml")));
		}

		// if the student didn't insert the correct code
		else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Code inserting failed",
					"the code you inserted is wrong", "Please try again").showAndWait();
			codeTf.clear();
		}

		// *******************************************************************
	}

	// EXTERNAL USE METHODS *************************************************
	/**
	 * sets code and exam's type of current exam
	 * 
	 * @param codeFromTeacher the code of the exam that is being taken
	 * @param examType        the type of the exam (manual or computerized)
	 * @param examID		  the ID of the exam that started
	 *
	 *                        TODO get current code and exam-type from teacher to
	 *                        all active students
	 */
	public void setCode(String codeFromTeacher, String examType, String examID) {
		if (!codeFromTeacher.equals("") && codeFromTeacher != null) {
			code = codeFromTeacher;
			System.out.println("code=" + code);
		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Code inserting failed",
					"There was no code inserted by a teacher", "Please try again").showAndWait();
		}
		if (!examType.equals("") && examType != null) {
			examType = examType;
			System.out.println("examType=" + examType);
			if(examType.equals("computerized"))
				StudentTakeComputerizedExamController.stceController.setExamID(examID);
			else if(examType.equals("manually"))
				StudentTakeExamManuallyController.stemController.setExamID(examID);
		}
		else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Code inserting failed",
					"There don't seem to be any details of a running exam").showAndWait();
		}
	}

}
