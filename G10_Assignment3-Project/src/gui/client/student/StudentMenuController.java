package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;

public class StudentMenuController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
    @FXML
    private Hyperlink sbStudentLnk;

    @FXML
    private Hyperlink sbLogoutLnk;
    
    @FXML
    private Label sbWelcomeLbl;
    
    @FXML
	private Button sbTakeExamBtn;

	@FXML
	private Button sbViewExamResultsBtn;

	@FXML
	private Button sbSettingsBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink studentLnk;
    private static Hyperlink logoutLnk;
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
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
		// scene.getStylesheets().add(getClass().getResource("/gui/client/student/StudentMenu.css").toExternalForm());

	}
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.setWidth(900);
		ClientUI.mainStage.setHeight(600);
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Student)");
		studentLnk = sbStudentLnk;
		logoutLnk = sbLogoutLnk;
		welcomeLbl = sbWelcomeLbl;
		takeExamBtn = sbTakeExamBtn;
		viewExamResultsBtn = sbViewExamResultsBtn;
		settingsBtn = sbSettingsBtn;
		smbController = new StudentMenuBarController();
	}
	
	// ACTION METHODS *******************************************************
	@FXML
	public void lnkPressLogout(ActionEvent event) throws Exception {
		System.out.println("StudentMenuBar::LnkLogout");
		smbController.start();
		smbController.lnkPressLogout(event);
	}

	@FXML
	public void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("StudentMenuBar::btnPressSettings/lnkStudentName");
		smbController.start();
		smbController.btnPressSettings(event);
	}

	@FXML
	public void btnPressTakeExam(ActionEvent event) throws Exception {
		System.out.println("StudentMenuBar::btnPressTakeExam");
		smbController.start();
		smbController.btnPressTakeAnExam(event);
	}

	@FXML
	public void btnPressViewExamResults(ActionEvent event) throws Exception {
		System.out.println("StudentMenuBar::btnPressViewExamResults");
		smbController.start();
		smbController.btnPressViewExamResults(event);
	}

}
