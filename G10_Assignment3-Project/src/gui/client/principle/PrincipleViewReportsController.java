package gui.client.principle;

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

public class PrincipleViewReportsController implements Initializable{

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

	// STATIC JAVAFX INSTANCES **********************************************
	private static TextField teacherUserNameTf;
	private static Button produceByTeacherBtn;
	private static TextField courseIDTf;
	private static Button produceByCourseBtn;
	private static TextField studentIDTf;
	private static Button produceByStudentBtn;
	public static String insertedValue="";

	// STATIC  INSTANCES **********************************************
	public static Boolean doesExist=false;
	private CommonMethodsHandler methodsHandler = CommonMethodsHandler.getInstance();

	// CONTROLLER INSTANCES ********************************************
	public static PrincipleViewReportsController pvrController;
	protected static PrincipleReportsByTeacherController prbtController;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teacherUserNameTf = sbteacherUserNameTf;
		produceByTeacherBtn = sbProduceByTeacherBtn;
		courseIDTf = sbcourseIDTf;
		produceByCourseBtn = sbProduceByCourseBtn;
		studentIDTf = sbstudentIDTf;
		produceByStudentBtn = sbProduceByStudentBtn;
		pvrController=new PrincipleViewReportsController();
		prbtController = new PrincipleReportsByTeacherController();
		methodsHandler.addTextLimiter(teacherUserNameTf, 9);
		methodsHandler.addTextLimiter(courseIDTf, 9);//CHECK WAHT ABOUT SUBJECT ID ???????????
		methodsHandler.addTextLimiter(studentIDTf, 9);
	}

	// ACTION METHODS *******************************************************
	@FXML
	void btnPressProduceByCourse(ActionEvent event) throws IOException {
		// TODO show exam reports by course
		System.out.println("PrincipleViewReports::btnPressProduceByCourse");
		insertedValue=courseIDTf.getText();
		System.out.println(insertedValue);

		if(insertedValue.equals("") || insertedValue.equals(null)) {
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "No course ID was inserted. Please re-enter.").showAndWait();
		}
		else if(insertedValue.length() < 4 ) {
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "The course ID id too short. Make sure you enter the full courseID (including the subject's ID).").showAndWait();
		}
		else if(!checkIfSearchedIDExists(insertedValue,"C"))
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "This course's ID does not exist in the system's database. Please re-enter.").showAndWait();
		else {
			try {
				PrincipleMenuBarController.mainPaneBp.setCenter(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleReportsByCourse.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void btnPressProduceByStudent(ActionEvent event) {
		System.out.println("PrincipleViewReports::btnPressProduceByStudent");
		insertedValue=studentIDTf.getText();

		if(insertedValue.equals("") || insertedValue.equals(null)) {
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "No student ID was inserted. Please re-enter.").showAndWait();
		}
		else if(!checkIfSearchedIDExists(insertedValue,"S"))
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "This student's ID does not exist in the system's database. Please re-enter.").showAndWait();
		else {
			try {
				PrincipleMenuBarController.mainPaneBp.setCenter(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleReportsByStudent.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	@FXML
	void btnPressProduceByTeacher(ActionEvent event){
		System.out.println("PrincipleViewReports::btnPressProduceByTeacher");
		insertedValue=teacherUserNameTf.getText();

		if(insertedValue.equals("") || insertedValue.equals(null)) {
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "No teacher ID was inserted. Please re-enter.").showAndWait();
		}
		else if(!checkIfSearchedIDExists(insertedValue,"T"))
			methodsHandler.getNewAlert(AlertType.ERROR, "Error message", "This teacher's ID does not exist in the system's database. Please re-enter.").showAndWait();
		else {
			try {
				PrincipleMenuBarController.mainPaneBp.setCenter(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleReportsByTeacher.fxml")));
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// EXTERNAL METHODS *******************************************************
	/**
	 *
	 * @param value
	 */
	public void setDoesExit(boolean value) {
		doesExist=value;
	}

	/**
	 * checks if the value inserted into the textField is legal
	 * @param userName
	 * @param type
	 * @return
	 */
	public boolean checkIfSearchedIDExists (String userName,String type) {
		ClientUI.chat.accept(new String[] { "checkIfSearchedIDExists", insertedValue, type});
		if(doesExist) return true;
		return false;
	}
}
