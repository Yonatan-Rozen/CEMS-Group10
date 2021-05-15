package gui.client.principle;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PrincipleViewInfoController implements Initializable {
	
	@FXML
    private Button sbViewUsersBtn;

    @FXML
    private Button sbViewExamsBtn;

    @FXML
    private Button sbViewQuestionsBtn;
    
    private static Button viewUsersBtn;
    private static Button viewExamsBtn;
    private static Button viewQuestionsBtn;

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewUsersBtn = sbViewUsersBtn;
		viewExamsBtn = sbViewExamsBtn;
		viewQuestionsBtn = sbViewQuestionsBtn;
		
	}

    @FXML
    void btnPressViewExams(ActionEvent event) {
    	// TODO get exams data info from the database
    }

    @FXML
    void btnPressViewQuestions(ActionEvent event) {
    	// TODO get questions data info from the database
    }

    @FXML
    void btnPressViewUsers(ActionEvent event) {
    	// TODO get users data info from the database
    }
}
