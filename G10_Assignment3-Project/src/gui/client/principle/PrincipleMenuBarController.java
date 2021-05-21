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
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
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
		sbLogoIv.setImage(new Image (getClass().getResourceAsStream("/logo.png")));
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressViewRequests(ActionEvent event) throws IOException {
		System.out.println("BtnPressViewRequests");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewRequests"));
	}

	@FXML
	public void btnPressViewInfo(ActionEvent event) throws IOException {
		System.out.println("BtnPressViewInfo");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewInfo"));

	}
	@FXML
	public void btnPressViewReports(ActionEvent event) throws IOException {
		System.out.println("BtnPressViewReports");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewReports"));
	}
	@FXML
	public void btnPressSettings(ActionEvent event) throws IOException {
		System.out.println("BtnPressSettings");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("client", "UserSettings"));
	}

	@FXML
	public void lnkPressLogout(ActionEvent event) throws IOException {
		System.out.println("LnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}

	@FXML
	public void btnPressBack(ActionEvent event) throws IOException {
		System.out.println("btnPressBack");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenu.fxml")));
	}
}
