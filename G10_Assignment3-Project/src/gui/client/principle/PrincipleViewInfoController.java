package gui.client.principle;

import java.net.URL;
import java.util.ResourceBundle;

import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;

public class PrincipleViewInfoController implements Initializable {
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private Button sbViewUsersBtn;

	@FXML
	private Button sbViewExamsBtn;

	@FXML
	private Button sbViewQuestionsBtn;

	// STATIC JAVAFX INSTANCES **********************************************
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
		System.out.println("PrincipleViewInfo::btnPressViewExams");
		PrincipleMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewExamsInfoScreen"));
	}

	@FXML
	void btnPressViewQuestions(ActionEvent event) {
		System.out.println("PrincipleViewInfo::btnPressViewQuestions");
		PrincipleMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewQuestionsInfoScreen"));
	}


	@FXML
	void btnPressViewUsers(ActionEvent event) throws Exception {
		System.out.println("PrincipleViewInfo::btnPressViewUsers");
		PrincipleMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewUsersInfoScreen"));
	}

	// EXTERNAL USE METHODS *************************************************

}
