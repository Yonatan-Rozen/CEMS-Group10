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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

/**
 * A controller class which controls the side menu of the Principle user.
 * @author Michael Malka, Tuval Zitelbach & Meitar El Ezra
 */
public class PrincipleMenuBarController implements Initializable {

	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Button sbViewRequestsBtn;

	@FXML
	private AnchorPane sbMenuBarAp;

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
	private Button sbBackBtn;

	@FXML
	private BorderPane sbMainPaneBp;

	@FXML
	private ImageView sbMenuBg;


	// STATIC JAVAFX INSTANCES **********************************************
	private static Button viewRequestsBtn;
	private static Button viewInfoBtn;
	private static Button viewReportsBtn;
	private static Button settingsBtn;
	private static Button backBtn;
	protected static BorderPane mainPaneBp;
	protected static AnchorPane menuBarAp;
	private static ImageView menuBg;

	// STATIC INSTANCES *****************************************************
	private static Button currentBtn;

	// CONTROLLER INSTANCES ***************************************************
	private CommonMethodsHandler cmh = CommonMethodsHandler.getInstance();

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		viewRequestsBtn = sbViewRequestsBtn;
		viewInfoBtn = sbViewInfoBtn;
		viewReportsBtn = sbViewReportsBtn;
		settingsBtn = sbSettingsBtn;
		mainPaneBp = sbMainPaneBp;
		menuBarAp=sbMenuBarAp;
		backBtn = sbBackBtn;
		backBtn.getStyleClass().clear();
		backBtn.getStyleClass().add("backToMenu-button");
		menuBg = sbMenuBg;
		menuBg.setImage(new Image("/menubar_bg.png"));
		menuBg.setFitHeight(600);
		menuBg.setFitWidth(230);
		//css
		viewRequestsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");
		viewInfoBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");
		viewReportsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");
		settingsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");

		sbLogoIv.setImage(CommonMethodsHandler.CEMS_LOGO);
	}

	// ACTION METHODS *******************************************************
	//=======================================================================================================
	//Show Request
	@FXML
	public void btnPressViewRequests(ActionEvent event) throws IOException {
		System.out.println("PrincipleMenuBar::BtnPressViewRequests");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewRequests"));
		cmh.fadeInAndOut(mainPaneBp, "principle", "PrincipleViewRequests");
		currentBtn = cmh.disablePropertySwapper(currentBtn, viewRequestsBtn);
	}
	//=======================================================================================================
	//Show Info
	@FXML
	public void btnPressViewInfo(ActionEvent event) throws IOException {
		System.out.println("PrincipleMenuBar::BtnPressViewInfo");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewInfo"));
		cmh.fadeInAndOut(mainPaneBp, "principle", "PrincipleViewInfo");
		currentBtn = cmh.disablePropertySwapper(currentBtn, viewInfoBtn);
	}
	//=======================================================================================================
	//Open Reports
	@FXML
	public void btnPressViewReports(ActionEvent event) throws IOException {
		System.out.println("PrincipleMenuBar::BtnPressViewReports");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("principle", "PrincipleViewReports"));
		cmh.fadeInAndOut(mainPaneBp, "principle", "PrincipleViewReports");
		currentBtn = cmh.disablePropertySwapper(currentBtn, viewReportsBtn);
	}
	//=======================================================================================================
	//Open Settings
	@FXML
	public void btnPressSettings(ActionEvent event) throws IOException {
		System.out.println("PrincipleMenuBar::BtnPressSettings");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("client", "UserSettings"));
		cmh.fadeInAndOut(mainPaneBp, "client", "UserSettings");
		currentBtn = cmh.disablePropertySwapper(currentBtn, settingsBtn);
	}
	//=======================================================================================================
	//Go Back To SignIn Form
	@FXML
	public void lnkPressLogout(ActionEvent event) throws IOException {
		System.out.println("PrincipleMenuBar::lnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/SignIn.fxml")));
	}
	//=======================================================================================================
	//Go Back To Principle Menu
	@FXML
	public void btnPressBack(ActionEvent event) throws IOException {
		System.out.println("PrincipleMenuBar::btnPressBack");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenu.fxml")));
	}
}
