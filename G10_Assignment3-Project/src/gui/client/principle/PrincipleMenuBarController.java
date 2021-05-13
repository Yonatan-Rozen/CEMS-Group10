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
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.stage.StageStyle;

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
    private AnchorPane sbMainPaneAp;
    
    // STATIC JAVAFX INSTANCES **********************************************
    private Button viewRequestsBtn;
    private Button viewInfoBtn;
    private Button viewReportsBtn;
    private Button settingsBtn;
    private Hyperlink logoutLnk;
    private AnchorPane mainPaneAp;
    
    // START METHOD *********************************************************
  	/**
  	 * Opens PrincipleMenu.fxml
  	 * @param primaryStage
  	 * @throws Exception
  	 */
  	public void start(Stage primaryStage) throws Exception {
  		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/principle/PrincipleMenuBar.fxml"));
  		Scene scene = new Scene(root);
  		// scene.getStylesheets().add(getClass().getResource("/gui/client/principle/PrincipleMenuBar.css").toExternalForm());
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
		viewRequestsBtn = sbViewRequestsBtn;
	    viewInfoBtn = sbViewInfoBtn;
	    viewReportsBtn = sbViewReportsBtn;
	    settingsBtn = sbSettingsBtn;
	    logoutLnk = sbLogoutLnk;
	    mainPaneAp = sbMainPaneAp;
	    //sbLogoIv.setImage(new Image (getClass().getResourceAsStream("/util/logo.png")));
	}
	
	// ACTION METHODS *******************************************************
    @FXML
    private void BtnPressViewRequests(ActionEvent event) {
    	// TODO open PrincipleViewRequest.fxml
    }

    @FXML
    private void BtnPressViewInfo(ActionEvent event) {
    	// TODO open PrincipleViewInfo.fxml
    }

    @FXML
    private void BtnPressViewReports(ActionEvent event) {
    	// TODO open PrincipleViewReports.fxml
    }
    
    @FXML
    private void BtnPressSettings(ActionEvent event) {
    	// TODO open PrincipleSettings.fxml
    }

    @FXML
    private void LnkPressLogout(ActionEvent event) {
    	// TODO open SignIn.fxml
    }
}
