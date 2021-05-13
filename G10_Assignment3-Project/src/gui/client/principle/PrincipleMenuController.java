package gui.client.principle;
import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.Alert.AlertType;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

public class PrincipleMenuController implements Initializable{

	// JAVAFX INSTNCES ******************************************************
    @FXML
    private Label sbWelcomeLbl;

    @FXML
    private Button sbViewRequestBtn;

    @FXML
    private Button sbViewInfoBtn;

    @FXML
    private Button sbViewReportsBtn;

    @FXML
    private Button sbSettingsBtn;
    
    // STATIC JAVAFX INSTANCES **********************************************
    private Label welcomeLbl;
    private Button viewRequestBtn;
    private Button viewInfoBtn;
    private Button viewReportsBtn;
    private Button settingsBtn;
    
    // START METHOD *********************************************************
 	/**
 	 * Opens PrincipleMenu.fxml
 	 * 
 	 * @param primaryStage
 	 * @throws Exception
 	 */
 	public void start(Stage primaryStage) throws Exception {
 		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenu.fxml"));
 		Scene scene = new Scene(root);
 		// scene.getStylesheets().add(getClass().getResource("/gui/client/principle/PrincipleMenu.css").toExternalForm());
 		primaryStage.setTitle("CEMS - Computerized Exam Management System (Principle)");
 		primaryStage.setScene(scene);
 		primaryStage.setResizable(false);
 		primaryStage.setOnCloseRequest(e -> {
 			primaryStage.hide();
 			Alert alert = new Alert(AlertType.INFORMATION);
 			alert.initStyle(StageStyle.UTILITY);
 			alert.setTitle("Client window closed");
 			alert.setHeaderText("You have been disconnected");
 			alert.setContentText("Press ok to continue.");
 			alert.showAndWait();
 			System.exit(0);
 		});
 		primaryStage.show();
 	}
 	
 	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		welcomeLbl = sbWelcomeLbl;
		//TODO welcomeLbl should be set with a name (e.x : 'welcome, Ron')
	    viewRequestBtn = sbViewRequestBtn;
	    viewInfoBtn = sbViewInfoBtn;
	    viewReportsBtn = sbViewReportsBtn;
	    settingsBtn = sbSettingsBtn;
	}
	
	// viewRequestBtn METHOD *****************************************************
	public void BtnPressViewRequest(ActionEvent event) {
		// TODO 
	}
	
	// viewInfoBtn METHOD *****************************************************
	public void BtnPresssbViewInfo(ActionEvent event) {
		// TODO 
	}
	
	// viewReportsBtn METHOD *****************************************************
	public void BtnPressViewReports(ActionEvent event) {
		// TODO 
	}
	
	// settingsBtn METHOD *****************************************************
	public void BtnPressSettings(ActionEvent event) {
		// TODO
	}
	
}
