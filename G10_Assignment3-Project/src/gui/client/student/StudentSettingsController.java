package gui.client.student;

import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Node;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.text.Text;

public class StudentSettingsController implements Initializable {
	//public static ExamDataInfoController edic;

	@FXML
	private ImageView imgView;

	 @FXML
	    private Hyperlink lnkLogout;

	    @FXML
	    private Button btnTakeExam;

	    @FXML
	    private Button btnExamResults;

	    @FXML
	    private Button btnSettings;

	    @FXML
	    private Label Lblusername;

	    @FXML
	    private Label LblFullname;

	    @FXML
	    private Label LblPhonenumber;

	    @FXML
	    private Label LblEmail;

	    @FXML
	    private Label LblAccounttype;

	    @FXML
	    private Hyperlink LnkChangepassword;

	    @FXML
	    private Text usernameTxt;

	    @FXML
	    private Text FullnameTxt;

	    @FXML
	    private Text PhonenumberTxt;

	    @FXML
	    private Text EmailTxt;

	    @FXML
	    private Text AccounttypeTxt;

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Label LblSettings;

	    @FXML
	    void BackBtn(ActionEvent event) {

	    }

	    @FXML
	    void ChangepasswordLnk(ActionEvent event) {

	    }

	    @FXML
	    void ExamResultsBtn(ActionEvent event) {

	    }

	    @FXML
	    void LogoutLnk(ActionEvent event) {

	    }

	    @FXML
	    void SettingsBtn(ActionEvent event) {

	    }

	    @FXML
	    void TakeExamBtn(ActionEvent event) {

	    }

	private static Stage currentStage;

	public void start(Stage primaryStage) {
		try {
			currentStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/gui/StudentSettings.fxml"));
			Scene scene = new Scene(root);
		//	scene.getStylesheets().add(getClass().getResource("/gui/StudentSettings.css").toExternalForm());
			primaryStage.setTitle("Student Settings");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				//ClientUI.chat.accept("client disconnected");
				primaryStage.hide();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Client disconnected");
				alert.setHeaderText("You have been disconnected from the server(StudentSettings)");
				alert.setContentText("Press ok to continue...");
				alert.showAndWait();
				System.exit(0);
			});
			primaryStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

//	public void getBtn(ActionEvent event) throws Exception {
//		Point windowPosition = new Point(currentStage.getX(), currentStage.getY());
//		((Node) event.getSource()).getScene().getWindow().hide();
//		edic = new ExamDataInfoController();
//		edic.start(new Stage(), windowPosition);
//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		imgView.setImage(new Image(getClass().getResourceAsStream("/logo.png")));
	}

}
