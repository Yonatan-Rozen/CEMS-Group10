package gui.client.student;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import logic.question.Question;

public class StudentExamSubmittedController implements Initializable {
	public static StudentExamSubmittedController sesController;
	// JAVAFX INSTNCES ******************************************************

	@FXML
	private Button sbBackToMenuBtn;

	// PRIVATE INSTNCES *****************************************************
	private static boolean computerized;
	private static String submited;
	private static String estimatedTime;
	private static String examID;
	private static String grade;
	private static String allocatedTime;
	private static String FilePath;
	private static String[] answersOfStudent = new String[] {};
	private static List<Question> questionsOfExam = new ArrayList<>();;// = new ArrayList<>();

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		sesController = new StudentExamSubmittedController();
		ClientUI.mainStage.setWidth(1100);
		ClientUI.mainStage.setHeight(600);
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressBackToMenu(ActionEvent event) throws IOException {
		System.out.println("StudentExamSubmitted::btnPressBackToMenu");
		if (computerized) { // update grade into exams_results_computerized
			ClientUI.chat.accept(new String[] { "updateCopmuterizedSubmittedExamInfoByExamIDandStudentID", submited,estimatedTime, ChatClient.user.getUsername(), examID, grade, allocatedTime });
			ClientUI.chat.accept(new Object[] { "updateCopmuterizedSubmittedExamAnswersByExamIDStudentIDandQuestionID", examID, ChatClient.user.getUsername(), questionsOfExam, answersOfStudent });

		}
		else { // manually
			ClientUI.chat.accept(new String[] {"updateManualSubmittedExamInfoByExamIDandStudentID" , submited, estimatedTime, ChatClient.user.getUsername(),examID, allocatedTime});
			ClientUI.chat.accept(new String[] { "StudentUploadFile", examID, FilePath, "S", ChatClient.user.getUsername() });
		}
		// decrement the amount of students that are in the running exam
		ClientUI.chat.accept(new String[] { "SendMessageDecNumStudentsInExam", StudentMenuController.examiningTeacherID });
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
	}

	public void setExamDetailsComputerized(String estimatedTime, String examID, String grade, String allocatedTime, String submited, List<Question> questionsOfExamo, String[] answersOfStudent) {
		StudentExamSubmittedController.computerized = true;
		StudentExamSubmittedController.estimatedTime = estimatedTime;
		StudentExamSubmittedController.examID = examID;
		StudentExamSubmittedController.grade = grade;
		StudentExamSubmittedController.allocatedTime = allocatedTime;
		StudentExamSubmittedController.submited = submited;
		System.out.println("setExamDetailsComputerized :: questionsOfExam = "+questionsOfExamo);
		StudentExamSubmittedController.questionsOfExam.addAll(questionsOfExamo);
		System.out.println("setExamDetailsComputerized :: answersOfStudent = "+answersOfStudent);
		StudentExamSubmittedController.answersOfStudent=answersOfStudent;

		System.out.println("computerized "+estimatedTime +" "+ examID +" "+ grade +" "+ allocatedTime +" "+ submited);
	}

	public void setExamDetailsManual(String estimatedTime, String examID, String allocatedTime, String submited, String FilePath) {
		StudentExamSubmittedController.computerized = false;
		StudentExamSubmittedController.estimatedTime = estimatedTime;
		StudentExamSubmittedController.examID = examID;
		StudentExamSubmittedController.allocatedTime = allocatedTime;
		StudentExamSubmittedController.submited = submited;
		StudentExamSubmittedController.FilePath = FilePath;
	}
}
