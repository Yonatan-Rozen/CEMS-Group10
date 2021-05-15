package gui.client.principle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TextField;

public class PrincipleViewReportsController implements Initializable{

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teacherNameTf = sbTeacherNameTf;
		produceByTeacherBtn = sbProduceByTeacherBtn;
		courseNameTf = sbCourseNameTf;
		produceByCourseBtn = sbProduceByCourseBtn;
		studentNameTf = sbStudentNameTf;
		produceByStudentBtn = sbProduceByStudentBtn;
	}

    @FXML
    void btnPressProduceByCourse(ActionEvent event) {
    	// TODO show exam reports by course
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
