package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class TeacherViewReportsController implements Initializable{

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
    
    private static TextField teacherNameTf;
    private static Button produceByTeacherBtn;
    private static TextField courseNameTf;
    private static Button produceByCourseBtn;
    private static TextField studentNameTf;
    private static Button produceByStudentBtn;

    private static TeacherReportsController rController;
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teacherNameTf = sbTeacherNameTf;
		produceByTeacherBtn = sbProduceByTeacherBtn;
		courseNameTf = sbCourseNameTf;
		produceByCourseBtn = sbProduceByCourseBtn;
		studentNameTf = sbStudentNameTf;
		produceByStudentBtn = sbProduceByStudentBtn;
		rController = new TeacherReportsController();
	}

    @FXML
    void btnPressProduceByCourse(ActionEvent event) throws IOException {
    	// TODO show exam reports by course
    	// example *********************************************
    	rController.start();
    	// *****************************************************
    	
    }

    @FXML
    void btnPressProduceByStudent(ActionEvent event) {
    	// TODO show exam reports by student
    }

    @FXML
    void btnPressProduceByTeacher(ActionEvent event) {
    	// TODO show exam reports by teacher
    }
}
