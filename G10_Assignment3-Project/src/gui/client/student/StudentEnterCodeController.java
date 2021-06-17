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
	private static String examiningTeacherID;
	protected static String additionalTime;

	// CONTROLLER INSTANCES ****************************************************
	public static StudentEnterCodeController secController=new StudentEnterCodeController();
	private static CommonMethodsHandler commonMethodHandler = CommonMethodsHandler.getInstance();

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		codeTf = sbCodeTf;
		commonMethodHandler.setTextLimiter(codeTf, 4);
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressStartExam(ActionEvent event) throws IOException {
		// go to [Computerized / Manual] exam screen
		System.out.println("StudentEnterCode::btnPressStartExam");

		if (code.equals(codeTf.getText())) {
			if (examType.equals("C"))
				StudentMenuBarController.smbController.mainPaneBp.setCenter(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentTakeComputerizedExam.fxml")));
			if (examType.equals("M"))
				StudentMenuBarController.smbController.mainPaneBp.setCenter(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentTakeExamManually.fxml")));
			StudentMenuBarController.smbController.menuBarContainerAp.setDisable(true);

			ClientUI.chat.accept(new String[] {"SendMessageIncNumStudentsInExam",examiningTeacherID}); // TODO (increments the amount of students that are in the running exam)
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
	 * @param examTypeFromTeacher the type of the exam (manual or computerized)
	 * @param examID the ID of the exam that started
	 *
	 *                        TODO get current code and exam-type from teacher to
	 *                        all active students
	 */
	public void setReadyExam(String codeFromTeacher, String examTypeFromTeacher, String examID, String examiningTeacherID) {
		StudentEnterCodeController.examiningTeacherID=examiningTeacherID;
		code = codeFromTeacher;

		examType = examTypeFromTeacher;
		if(examType.equals("C"))
			StudentTakeComputerizedExamController.stceController.setExamID(examID);
		else if(examType.equals("M"))
			StudentTakeExamManuallyController.stemController.setExamID(examID);
	}

	public void setAdditionalTime(String newAllocatedTime) {
		additionalTime = newAllocatedTime;
	}
}
