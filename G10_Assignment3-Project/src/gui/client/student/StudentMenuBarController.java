package gui.client.student;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class StudentMenuBarController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Button sbTakeExamBtn;

	@FXML
	private Button sbViewExamResultsBtn;

	@FXML
	private Button sbSettingsBtn;

	@FXML
	private Hyperlink sbLogoutLnk;

	@FXML
	private ImageView sbLogoIv;

	@FXML
	private Button sbBackBtn;

	@FXML
	private BorderPane sbMainPaneBp;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Button takeExamBtn;
	private static Button viewExamResultsBtn;
	private static Button settingsBtn;
	private static Hyperlink logoutLnk;
	private static Button backBtn;
	private static BorderPane mainPaneBp;

	// START METHOD *********************************************************
	/**
	 * Opens StudentMenuBar.fxml
	 *
	 * @throws Exception
	 */
	public void start() throws Exception {
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenuBar.fxml")));
		// scene.getStylesheets().add(getClass().getResource("/gui/client/student/StudentMenuBar.css").toExternalForm());
	}

	// INITIALIZE METHOD *********************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.setWidth(1150);
		ClientUI.mainStage.setHeight(650);
		takeExamBtn = sbTakeExamBtn;
		viewExamResultsBtn = sbViewExamResultsBtn;
		settingsBtn = sbSettingsBtn;
		logoutLnk = sbLogoutLnk;
		backBtn = sbBackBtn;
		mainPaneBp = sbMainPaneBp;
		sbLogoIv.setImage(new Image (getClass().getResourceAsStream("/logo.png")));
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressTakeAnExam(ActionEvent event) {
		System.out.println("StudentMenuBar::btnPressTakeAnExam");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("student", "StudentEnterCode"));
	}

	@FXML
	public void btnPressSettings(ActionEvent event) {
		System.out.println("StudentMenuBar::btnPressSettings");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("client", "UserSettings"));
	}

	@FXML
	public void btnPressViewExamResults(ActionEvent event) {
		System.out.println("StudentMenuBar::btnPressViewExamResults");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("student", "StudentExamResults"));
	}

	@FXML
	public void lnkPressLogout(ActionEvent event) throws IOException {
		System.out.println("StudentMenuBar::lnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}

	@FXML
	public void btnPressBack(ActionEvent event) throws IOException {
		System.out.println("StudentMenuBar::btnPressBack");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/student/StudentMenu.fxml")));
	}
}
