package gui.client.principle;

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
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;

public class PrincipleMenuBarController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Button sbViewRequestsBtn;

	@FXML
	private Button sbViewInfoBtn;

	@FXML
	private Button sbViewReportsBtn;

	@FXML
	private Button sbSettingsBtn;

	@FXML
	private Hyperlink sbLogoutLnk;

	@FXML
	private ImageView sbLogoIv;

	@FXML
	private BorderPane sbMainPaneBp;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Button viewRequestsBtn; 
	private static Button viewInfoBtn;
	private static Button viewReportsBtn; 
	private static Button settingsBtn;
	private static Hyperlink logoutLnk;
	protected static BorderPane mainPaneBp;
	private static int loaded = 0;

	// START METHOD *********************************************************
	/**
	 * Opens PrincipleMenuBar.fxml
	 * 
	 * @throws Exception
	 */
	public void start() throws Exception {
		
		ClientUI.mainStage.setWidth(750);
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenuBar.fxml")));
		// scene.getStylesheets().add(getClass().getResource("/gui/client/principle/PrincipleMenuBar.css").toExternalForm());
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewRequestsBtn = sbViewRequestsBtn; 
		viewInfoBtn = sbViewInfoBtn;
		viewReportsBtn = sbViewReportsBtn; 
		settingsBtn = sbSettingsBtn; 
		logoutLnk = sbLogoutLnk;
		mainPaneBp = sbMainPaneBp;
		//sbLogoIv.setImage(new Image (getClass().getResourceAsStream("../util/logo.png")));
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressViewRequests(ActionEvent event) throws IOException {
		System.out.println("BtnPressViewRequests");
		// uncomment if there are any methods that are used externally from the controller
		//PrincipleViewRequestController pvreqC = new PrincipleViewRequestController();
		if (loaded != 1)
		{
			mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("PrincipleViewRequests"));
			loaded = 1;
			System.out.println(loaded);
		}
	}

	@FXML
	public void btnPressViewInfo(ActionEvent event) throws IOException {
		// TODO open PrincipleViewInfo.fxml
		System.out.println("BtnPressViewInfo");
		// uncomment if there are any methods that are used externally from the controller
		// PrincipleViewInfoController pviC = new PrincipleViewInfoController();
		if (loaded != 2)
		{
			mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("PrincipleViewInfo"));
			loaded = 2;
			System.out.println(loaded);
		}
	}
	@FXML
	public void btnPressViewReports(ActionEvent event) throws IOException {
		// TODO open PrincipleViewReports.fxml
		System.out.println("BtnPressViewReports");
		// uncomment if there are any methods that are used externally from the controller
		// PrincipleViewReportsController pvreqC = new PrincipleViewRequestController();
		if (loaded != 3)
		{
			mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("PrincipleViewReports"));
			loaded = 3;
			System.out.println(loaded);
		}
	}
	@FXML
	public void btnPressSettings(ActionEvent event) throws IOException {
		// TODO open PrincipleSettings.fxml
		System.out.println("BtnPressSettings");
		// uncomment if there are any methods that are used externally from the controller
		// PrincipleSettingsController pvreqC = new PrincipleSettingsController();
		if (loaded != 4)
		{
			mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("PrincipleSettings"));
			loaded = 4;
			System.out.println(loaded);
		}
	}

	@FXML
	public void lnkPressLogout(ActionEvent event) throws IOException {
		// TODO open SignIn.fxml
		System.out.println("LnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Client)");
		ClientUI.mainStage.setWidth(600);
	}
	
	@FXML
	public void btnPressBack(ActionEvent event) throws IOException {
		// TODO I prefer it to be a menu button rather than back - Yonatan
	}
}
