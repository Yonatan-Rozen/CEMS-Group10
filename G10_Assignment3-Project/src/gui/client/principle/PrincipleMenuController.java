package gui.client.principle;

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

public class PrincipleMenuController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Hyperlink sbPrincipleLnk;

	@FXML
	private Hyperlink sbLogoutLnk;
	
	@FXML
	private Label sbWelcomeLbl;

	@FXML
	private Button sbViewRequestsBtn;

	@FXML
	private Button sbViewInfoBtn;

	@FXML
	private Button sbViewReportsBtn;

	@FXML
	private Button sbSettingsBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink principleLnk;
	private static Hyperlink logoutLnk;
	private static Label welcomeLbl;
	private static Button viewRequestsBtn;
	private static Button viewInfoBtn;
	private static Button viewReportsBtn;
	private static Button settingsBtn;

	// CONTROLLER INSTANCES
	protected static PrincipleMenuBarController pmbController;

	// START METHOD *********************************************************
	/**
	 * Opens PrincipleMenu.fxml
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenu.fxml")));
		// scene.getStylesheets().add(getClass().getResource("/gui/client/principle/PrincipleMenu.css").toExternalForm());

	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.setWidth(750);
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Principle)");
		principleLnk = sbPrincipleLnk;
		logoutLnk = sbLogoutLnk;
		welcomeLbl = sbWelcomeLbl;
		// TODO welcomeLbl should be set with a name (e.x : 'welcome, Ron')
		viewRequestsBtn = sbViewRequestsBtn;
		viewInfoBtn = sbViewInfoBtn;
		viewReportsBtn = sbViewReportsBtn;
		settingsBtn = sbSettingsBtn;
		pmbController = new PrincipleMenuBarController();
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressViewRequest(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressViewRequest");
		pmbController.start();
		pmbController.btnPressViewRequests(event);
	}

	@FXML
	public void btnPressViewInfo(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressViewInfo");
		pmbController.start();
		pmbController.btnPressViewInfo(event);
	}

	@FXML
	public void btnPressViewReports(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressViewReports");
		pmbController.start();
		pmbController.btnPressViewReports(event);
	}

	@FXML
	public void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressSettings");
		pmbController.start();
		pmbController.btnPressSettings(event);
	}

	@FXML
	void lnkPressLogout(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::lnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}

}
