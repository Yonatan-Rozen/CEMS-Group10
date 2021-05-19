package gui.client.principle;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;

public class PrincipleMenuController implements Initializable{

	// JAVAFX INSTANCES ******************************************************
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
 		
		ClientUI.mainStage.setTitle("CEMS - Computerized Exam Management System (Principle)");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenu.fxml")));

		// scene.getStylesheets().add(getClass().getResource("/gui/client/student/StudentMenu.css").toExternalForm());
 		
 	}
 	
 	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLbl = sbWelcomeLbl;
		//TODO welcomeLbl should be set with a name (e.x : 'welcome, Ron')
	    viewRequestsBtn = sbViewRequestsBtn;
	    viewInfoBtn = sbViewInfoBtn;
	    viewReportsBtn = sbViewReportsBtn;
	    settingsBtn = sbSettingsBtn;
	    pmbController = new PrincipleMenuBarController();
	}
	
	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressViewRequest(ActionEvent event) throws Exception {
		System.out.println("btnPressViewRequest");
		pmbController.start();
		pmbController.btnPressViewRequests(event);
	}
	
	@FXML
	public void btnPressViewInfo(ActionEvent event) throws Exception {
		System.out.println("btnPressViewInfo");
		pmbController.start();
		pmbController.btnPressViewInfo(event);
	}
	
	@FXML
	public void btnPressViewReports(ActionEvent event) throws Exception {
		System.out.println("btnPressViewReports");
		pmbController.start();
		pmbController.btnPressViewReports(event);
	}
	
	@FXML
	public void btnPressSettings(ActionEvent event) throws Exception {
		System.out.println("btnPressSettings");
		pmbController.start();
		pmbController.btnPressSettings(event);
	}
	
}
