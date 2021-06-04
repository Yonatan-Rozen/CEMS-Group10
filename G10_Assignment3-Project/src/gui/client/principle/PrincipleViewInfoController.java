package gui.client.principle;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
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
	void btnPressViewExams(ActionEvent event) throws Exception {
		// TODO get exams data info from the database
		System.out.println("PrincipleViewInfo::btnPressViewExams");

	}

	@FXML
	void btnPressViewQuestions(ActionEvent event) {
		// TODO get questions data info from the database
		System.out.println("PrincipleViewInfo::btnPressViewQuestions");
	}

	@FXML
	void btnPressViewUsers(ActionEvent event) throws Exception {
		// TODO get users data info from the database
		System.out.println("PrincipleViewInfo::btnPressViewUsers");
		// take care of menu bar
		PrincipleMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewUsersInfoScreen"));

		//ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleViewUsersInfoScreen.fxml")));

	}
}
