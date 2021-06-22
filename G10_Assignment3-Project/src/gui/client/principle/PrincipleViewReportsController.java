package gui.client.principle;

import java.io.IOException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
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
import javafx.scene.image.ImageView;
/**
 * A controller class which controls the "View Reports" option screen of the Principle user.
 * @author Michael Malka, Tuval Zitelbach & Meitar El Ezra
 */
public class PrincipleViewReportsController implements Initializable {
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private TextField sbteacherUserNameTf;

	@FXML
	private Button sbProduceByTeacherBtn;

	@FXML
	private TextField sbcourseIDTf;

	@FXML
	private Button sbProduceByCourseBtn;

	@FXML
	private TextField sbstudentIDTf;

	@FXML
	private Button sbProduceByStudentBtn;

	@FXML
	private ImageView sbQuestionLegendIv;

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextField teacherUserNameTf;
	private static TextField courseIDTf;
	private static TextField studentIDTf;
	public static String insertedValue = "";
	private static ImageView checkAnswerIv;

	// STATIC INSTANCES **********************************************
	public static Boolean doesExist = false;
	public static List<String> list = new ArrayList<>();

	// CONTROLLER INSTANCES ********************************************
	public static PrincipleViewReportsController pvrController;
	protected static PrincipleReportsByTeacherController prbtController;
	private CommonMethodsHandler methodsHandler = CommonMethodsHandler.getInstance();

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teacherUserNameTf = sbteacherUserNameTf;
		courseIDTf = sbcourseIDTf;
		studentIDTf = sbstudentIDTf;
		pvrController = new PrincipleViewReportsController();
		prbtController = new PrincipleReportsByTeacherController();
		methodsHandler.setTextLimiter(teacherUserNameTf, 9);
		methodsHandler.setTextLimiter(courseIDTf, 4);
		methodsHandler.setTextLimiter(studentIDTf, 9);
		PrincipleMenuBarController.menuBarAp.setDisable(false);
		sbQuestionLegendIv.setImage(CommonMethodsHandler.ICON_TOOLTIP);
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressProduceByCourse(ActionEvent event) throws IOException {
		System.out.println("PrincipleViewReports::btnPressProduceByCourse");
		insertedValue = courseIDTf.getText();

		if (insertedValue.equals("") || insertedValue.equals(null)) {
			methodsHandler
			.getNewAlert(AlertType.ERROR, "Error message", "No course ID was inserted.", "Please re-enter.")
			.showAndWait();
		} else if (insertedValue.length() < 4) {
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message",
					"The course ID id too short. Make sure you enter the full courseID (including the subject's ID).")
			.showAndWait();
		} else if (!checkIfSearchedIDExists(insertedValue, "C")) {
			methodsHandler
			.getNewAlert(AlertType.ERROR, "Error message",
					"This course's ID does not exist in the system's database.", "Please re-enter.")
			.showAndWait();
		}
		// else
		else {
			ClientUI.chat.accept(new String[] { "GetTeachers", insertedValue });
			if (list.size() == 0) {
				methodsHandler
				.getNewAlert(AlertType.ERROR, "Error message",
						"There are no teachers who had an exam done in this course.", "Press OK to return.")
				.showAndWait();
			} else {
				try {
					PrincipleMenuBarController.mainPaneBp.setCenter(FXMLLoader
							.load(getClass().getResource("/gui/client/principle/PrincipleReportsByCourse.fxml")));
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
		}
	}

	@FXML
	void btnPressProduceByStudent(ActionEvent event) {
		System.out.println("PrincipleViewReports::btnPressProduceByStudent");
		insertedValue = studentIDTf.getText();
		ClientUI.chat.accept(new String[] { "GetCourses", insertedValue, "S" });

		if (insertedValue.equals("") || insertedValue.equals(null)) {
			methodsHandler
			.getNewAlert(AlertType.ERROR, "Error message", "No student ID was inserted.", "Please re-enter.")
			.showAndWait();
		} else if (!checkIfSearchedIDExists(insertedValue, "S"))
			methodsHandler
			.getNewAlert(AlertType.ERROR, "Error message",
					"This student's ID does not exist in the system's database.", "Please re-enter.")
			.showAndWait();
		else if (list.size() == 0) {
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "There are no exams done by this student.",
					"Press OK to return.").showAndWait();
		} else {
			try {
				PrincipleMenuBarController.mainPaneBp.setCenter(FXMLLoader
						.load(getClass().getResource("/gui/client/principle/PrincipleReportsByStudent.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void btnPressProduceByTeacher(ActionEvent event) {
		System.out.println("PrincipleViewReports::btnPressProduceByTeacher");
		insertedValue = teacherUserNameTf.getText();
		ClientUI.chat.accept(new String[] { "GetCourses", insertedValue, "P" });

		if (insertedValue.equals("") || insertedValue.equals(null)) {
			methodsHandler
			.getNewAlert(AlertType.ERROR, "Error message", "No teacher ID was inserted.", "Please re-enter.")
			.showAndWait();
		} else if (!checkIfSearchedIDExists(insertedValue, "T"))
			methodsHandler
			.getNewAlert(AlertType.ERROR, "Error message",
					"This teacher's ID does not exist in the system's database.", "Please re-enter.")
			.showAndWait();
		else if (list.size() == 0) {
			methodsHandler
			.getNewAlert(AlertType.ERROR, "Error message",
					"There are no exams done in any of this teacher's courses.", "Press OK to return.")
			.showAndWait();
		} else {
			try {
				PrincipleMenuBarController.mainPaneBp.setCenter(FXMLLoader
						.load(getClass().getResource("/gui/client/principle/PrincipleReportsByTeacher.fxml")));

			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// EXTERNAL METHODS *******************************************************
	/**
	 *
	 * @param value a boolean value that determines whether a given ID of
	 *  teacher/stuent/course exists in the DB
	 */
	public void setDoesExit(boolean value) {
		doesExist = value;
	}

	/**
	 * checks if the value inserted into the textField is legal
	 *
	 * @param userName ID to search in DB
	 * @param type type of what we're searching :
	 *  (T --> teacher, S --> student, C --> course)
	 * @return boolean value: TRUE if the ID exists in DB, else FALSE
	 */
	public boolean checkIfSearchedIDExists(String userName, String type) {
		ClientUI.chat.accept(new String[] { "checkIfSearchedIDExists", insertedValue, type });
		if (doesExist)
			return true;
		return false;
	}

	/**
	 *
	 * @param list list of objects to display in the choisebox of a certain reports screen.
	 */
	public void setChoiseBoxList(List<String> list) {
		PrincipleViewReportsController.list.clear();
		PrincipleViewReportsController.list.addAll(list);
	}

}
