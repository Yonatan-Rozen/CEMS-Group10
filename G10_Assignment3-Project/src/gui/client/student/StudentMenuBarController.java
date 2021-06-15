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
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.Hyperlink;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;

public class StudentMenuBarController implements Initializable {
	public static StudentMenuBarController smbController;
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Button sbTakeExamBtn;

	@FXML
	private Button sbViewExamResultsBtn;

	@FXML
	private Button sbSettingsBtn;

	@FXML
	private AnchorPane sbMenuBarContainerAp;

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

	// STATIC INSTANCES *****************************************************
	private CommonMethodsHandler commonmeMethodsHandler = CommonMethodsHandler.getInstance();
	private static Button currentBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Button takeExamBtn;
	private static Button viewExamResultsBtn;
	private static Button settingsBtn;
	protected static BorderPane mainPaneBp;
	protected static AnchorPane menuBarContainerAp;
	private static ImageView menuBg;
	private static Button backBtn;

	// INITIALIZE METHOD *********************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		smbController = new StudentMenuBarController();
		takeExamBtn = sbTakeExamBtn;
		smbController=new StudentMenuBarController();
		viewExamResultsBtn = sbViewExamResultsBtn;
		settingsBtn = sbSettingsBtn;
		mainPaneBp = sbMainPaneBp;
		menuBarContainerAp=sbMenuBarContainerAp;
		backBtn = sbBackBtn;
		backBtn.setStyle("-fx-background-color: transparent ; -fx-background-image: url('/icon_menu.png') ; -fx-background-repeat: no-repeat;");
		
		menuBg = sbMenuBg;
		menuBg.setImage(new Image("/menubar_bg.png"));
//		menuBg.setPreserveRatio(false);
		menuBg.setFitHeight(600);
		menuBg.setFitWidth(230);
		
		takeExamBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		settingsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		viewExamResultsBtn.setStyle("-fx-background-image: url('/icon_empty.png') ; -fx-background-repeat: no-repeat;");	
		
		sbLogoIv.setImage(CommonMethodsHandler.CEMS_LOGO);
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressTakeAnExam(ActionEvent event) {
		System.out.println("StudentMenuBar::btnPressTakeAnExam");
		String[] readyExamData=StudentMenuController.smController.getReadyExam();
		if(readyExamData[0]==null)//||examType==null||examCode==null||examID.isEmpty()||examType.isEmpty()||examCode.isEmpty())
		{
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION,
					"Error : cannot start any exam","There is no exam running.", "Please try again some other time").showAndWait();
		}
		else{
			StudentEnterCodeController.secController.setReadyExam(readyExamData[0],readyExamData[1],readyExamData[2]);
			mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("student", "StudentEnterCode"));
			currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, takeExamBtn);
		}
	}

	@FXML
	public void btnPressSettings(ActionEvent event) {
		System.out.println("StudentMenuBar::btnPressSettings");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("client", "UserSettings"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, settingsBtn);
	}

	@FXML
	public void btnPressViewExamResults(ActionEvent event) {
		System.out.println("StudentMenuBar::btnPressViewExamResults");
		mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("student", "StudentExamResults"));
		currentBtn = commonmeMethodsHandler.disablePropertySwapper(currentBtn, viewExamResultsBtn);
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
