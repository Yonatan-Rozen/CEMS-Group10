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

	// STATIC  INSTANCES ****************************************************
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

		setCode("","");
		System.out.println(code);
		if (code.equals(codeTf.getText()))
		{
			if(examType.equals("computerized"))
				ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentTakeComputerizedExam.fxml")));
			if(examType.equals("manual"))
				ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentTakeExamManually.fxml")));
		}

		// if the student didn't insert the correct code
		else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Code inserting failed",
					"the code you inserted is wrong","Please try again").showAndWait();
			codeTf.setText("");
		}


		//*******************************************************************
	}

	// EXTERNAL USE METHODS *************************************************
	/**
	 * sets code and exam's type of current exam
	 * @param codeFromTeacher the code of the exam taken
	 * @param et the type of the exam (manual or computerized)
	 *
	 * TODO get current code and exam-type from teacher to all active students
	 */
	public void setCode(String codeFromTeacher,String et) {
		System.out.println("START OF SETCODE");
		if(!codeFromTeacher.equals("") && codeFromTeacher!=null) {
			code=codeFromTeacher;
			System.out.println("code="+code);
		}
		else {
			code="1111"; //default exam code for now
			System.out.println("code="+code);
		}
		if(!et.equals("") && et!=null) {
			examType=et;
			System.out.println("code="+code);
		}
		else {
			examType="computerized"; //default exam type for now
			System.out.println("type="+examType);
		}
		System.out.println("END OF SETCODE");

	}

}
