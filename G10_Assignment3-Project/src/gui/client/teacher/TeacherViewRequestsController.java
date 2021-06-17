package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;

public class TeacherViewRequestsController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
	@FXML
	private TableView<?> sbRequestsTv;

	@FXML
	private TableColumn<?, String> sbTeacherTc;

	@FXML
	private TableColumn<?, String> sbExamIdTc;

	@FXML
	private TableColumn<?, Integer> sbOrigTimeTc;

	@FXML
	private TableColumn<?, Integer> sbNewTimeTc;

	@FXML
	private Button sbAcceptRequestBtn;

	@FXML
	private Button sbDeclineRequestBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private TableView<?> requestsTv;
	private TableColumn<?, String> teacherTc;
	private TableColumn<?, String> examIdTc;
	private TableColumn<?, Integer> origTimeTc;
	private TableColumn<?, Integer> newTimeTc;
	private Button acceptRequestBtn;
	private Button declineRequestBtn;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		requestsTv = sbRequestsTv;
		teacherTc = sbTeacherTc;
		examIdTc = sbExamIdTc;
		origTimeTc = sbOrigTimeTc;
		newTimeTc = sbNewTimeTc;
		acceptRequestBtn = sbAcceptRequestBtn;
		declineRequestBtn = sbDeclineRequestBtn;
	}

	// ACTION METHODS *******************************************************
	@FXML
	void BtnPressAcceptRequest(ActionEvent event) {
		//  send change allocated time request to server
		System.out.println("TeacherViewRequests::BtnPressAcceptRequest");
	}

	@FXML
	void BtnPressDeclineRequest(ActionEvent event) {
		//  send message "request declined" to teacher
		System.out.println("TeacherViewRequests::BtnPressDeclineRequest");
	}
}
