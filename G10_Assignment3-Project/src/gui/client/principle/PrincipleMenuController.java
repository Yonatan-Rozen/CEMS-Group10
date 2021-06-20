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
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
/**
 * A controller class which controls the main menu of the Principle user.
 * @author Michael Malka, Tuval Zitelbach & Meitar El Ezra
 */
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

	@FXML
	private ImageView sbTeacherBgImg;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Hyperlink principleLnk;
	private static Hyperlink logoutLnk;
	private static Label welcomeLbl;
	private static Button viewRequestsBtn;
	private static Button viewInfoBtn;
	private static Button viewReportsBtn;
	private static Button settingsBtn;
	private static ImageView teacherBgImg;

	// CONTROLLER INSTANCES
	protected static PrincipleMenuBarController pmbController;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		ClientUI.mainStage.setWidth(1150);
		ClientUI.mainStage.setHeight(650);
		ClientUI.mainStage.setTitle("Computerized Exam Management System (Principle)");
		principleLnk = sbPrincipleLnk;
		principleLnk.setText(ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname());
		logoutLnk = sbLogoutLnk;
		welcomeLbl = sbWelcomeLbl;
		welcomeLbl.setText("Welcome, " + ChatClient.user.getFirstname());
		// TODO welcomeLbl should be set with a name (e.x : 'welcome, Ron')
		viewRequestsBtn = sbViewRequestsBtn;
		viewInfoBtn = sbViewInfoBtn;
		viewReportsBtn = sbViewReportsBtn;
		settingsBtn = sbSettingsBtn;
		pmbController = new PrincipleMenuBarController();
		teacherBgImg = sbTeacherBgImg;
		teacherBgImg.setImage(new Image("/img_bg.png"));
		teacherBgImg.setPreserveRatio(false);
		teacherBgImg.setFitHeight(620);
		teacherBgImg.setFitWidth(1135);
		ClientUI.mainStage.show();
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressViewRequest(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressViewRequest");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenuBar.fxml")));
		pmbController.btnPressViewRequests(event);
	}

	@FXML
	public void btnPressViewInfo(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressViewInfo");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenuBar.fxml")));
		pmbController.btnPressViewInfo(event);
	}

	@FXML
	public void btnPressViewReports(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressViewReports");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenuBar.fxml")));
		pmbController.btnPressViewReports(event);
	}

	@FXML
	public void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::btnPressSettings");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenuBar.fxml")));
		pmbController.btnPressSettings(event);
	}

	@FXML
	public void lnkPressLogout(ActionEvent event) throws Exception {
		System.out.println("PrincipleMenu::lnkPressLogout");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenuBar.fxml")));
		pmbController.lnkPressLogout(event);
	}

}
