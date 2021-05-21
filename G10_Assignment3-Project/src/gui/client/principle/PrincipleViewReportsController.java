package gui.client.principle;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrincipleViewReportsController implements Initializable{

	// JAVAFX INSTANCES ******************************************************
    @FXML
    private TextField sbTeacherNameTf;

    @FXML
    private Button sbProduceByTeacherBtn;

    @FXML
    private TextField sbCourseNameTf;

    @FXML
    private Button sbProduceByCourseBtn;

    @FXML
    private TextField sbStudentNameTf;

    @FXML
    private Button sbProduceByStudentBtn;
    
 // STATIC JAVAFX INSTANCES **********************************************
    private static TextField teacherNameTf;
    private static Button produceByTeacherBtn;
    private static TextField courseNameTf;
    private static Button produceByCourseBtn;
    private static TextField studentNameTf;
    private static Button produceByStudentBtn;

    // CONTROLLER INSTANCES
    protected static PrincipleReportsController prController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teacherNameTf = sbTeacherNameTf;
		produceByTeacherBtn = sbProduceByTeacherBtn;
		courseNameTf = sbCourseNameTf;
		produceByCourseBtn = sbProduceByCourseBtn;
		studentNameTf = sbStudentNameTf;
		produceByStudentBtn = sbProduceByStudentBtn;
		prController = new PrincipleReportsController();
	}

	// ACTION METHODS *******************************************************
    @FXML
    void btnPressProduceByCourse(ActionEvent event) throws IOException {
    	// TODO show exam reports by course
    	System.out.println("PrincipleViewReports::btnPressProduceByCourse");
    	// example *********************************************
    	prController.start();
    	// *****************************************************
    	
    }

    @FXML
    void btnPressProduceByStudent(ActionEvent event) {
    	// TODO show exam reports by student
    	System.out.println("PrincipleViewReports::btnPressProduceByStudent");
    }

    @FXML
    void btnPressProduceByTeacher(ActionEvent event) {
    	// TODO show exam reports by teacher
    	System.out.println("PrincipleViewReports::btnPressProduceByTeacher");
    }
}
