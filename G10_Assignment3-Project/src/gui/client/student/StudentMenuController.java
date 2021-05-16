package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class StudentMenuController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
    @FXML
    private Label sbWelcomeLbl;
    
    @FXML
	private Button sbTakeExamBtn;

	@FXML
	private Button sbViewExamResultsBtn;

	@FXML
	private Button sbSettingsBtn;

	// STATIC JAVAFX INSTANCES **********************************************
    private static Label welcomeLbl;
	private static Button takeExamBtn;
	private static Button viewExamResultsBtn;
	private static Button settingsBtn;
	
	// CONTROLLER INSTANCES
    public static StudentMenuBarController smbController;

	// START METHOD *********************************************************
	/**
	 * Opens PrincipleMenu.fxml
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {

		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Student)");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));

		// scene.getStylesheets().add(getClass().getResource("/gui/client/principle/PrincipleMenu.css").toExternalForm());

	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLbl = sbWelcomeLbl;
		takeExamBtn = sbTakeExamBtn;
		viewExamResultsBtn = sbViewExamResultsBtn;
		settingsBtn = sbSettingsBtn;
		smbController = new StudentMenuBarController();
	}

	@FXML
	void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("btnPressSettings");
		smbController.start();
		smbController.btnPressSettings(event);
	}

	@FXML
	void btnPressTakeExam(ActionEvent event) throws Exception {
		System.out.println("btnPressTakeExam");
		smbController.start();
		smbController.btnPressTakeAnExam(event);
	}

	@FXML
	void btnPressViewExamResults(ActionEvent event) throws Exception {
		System.out.println("btnPressViewExamResults");
		smbController.start();
		smbController.btnPressViewExamResults(event);
	}

}
