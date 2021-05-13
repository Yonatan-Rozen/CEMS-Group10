package gui.client.teacher;

import java.net.URL;
import java.util.ResourceBundle;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Hyperlink;
import javafx.scene.control.Label;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class CreateExamController{
	
	//

	 @FXML
	    private ImageView imgView;

	    @FXML
	    private Hyperlink lnkLogout;

	    @FXML
	    private Button btnStartexam;

	    @FXML
	    private Button btnCreatequestion;

	    @FXML
	    private Button btnEditquestion;

	    @FXML
	    private Button btnCreateexam;

	    @FXML
	    private Button btnEditexam;

	    @FXML
	    private Button btnViewreports;

	    @FXML
	    private Button btnSettings;

	    @FXML
	    private Button btnBack;

	    @FXML
	    private Label LblExamCreation;

	    @FXML
	    private ComboBox<?> ComboBoxChooseCourse;

	    @FXML
	    private Button EditExistingExamsBtn;

	    @FXML
	    private ComboBox<?> ComboBoxChooseExamBank;

	    @FXML
	    private Button ContinueBtn;

	    @FXML
	    private Button CancelCreationBtn;

	    @FXML
	    private TableView<?> AvailableQuestionsTable;

	    @FXML
	    private TableView<?> CurrentQuestionsTable;

	    @FXML
	    private Button ChangeQuestionBankBtn;

	    @FXML
	    private Button FinishContinueBtn;

	    @FXML
	    void BackBtn(ActionEvent event) {

	    }

	    @FXML
	    void CancelCreationBtn(ActionEvent event) {

	    }

	    @FXML
	    void ChangeQuestionBankBtn(ActionEvent event) {

	    }

	    @FXML
	    void ComboBoxChooseCourse(ActionEvent event) {

	    }

	    @FXML
	    void ComboBoxChooseExamBank(ActionEvent event) {

	    }

	    @FXML
	    void ContinueBtn(ActionEvent event) {

	    }

	    @FXML
	    void CreateexamBtn(ActionEvent event) {

	    }

	    @FXML
	    void CreatequestionBtn(ActionEvent event) {

	    }

	    @FXML
	    void EditExistingExamsBtn(ActionEvent event) {

	    }

	    @FXML
	    void EditexamBtn(ActionEvent event) {

	    }

	    @FXML
	    void EditquestionBtn(ActionEvent event) {

	    }

	    @FXML
	    void FinishContinueBtn(ActionEvent event) {

	    }

	    @FXML
	    void LogoutLnk(ActionEvent event) {

	    }

	    @FXML
	    void SettingsBtn(ActionEvent event) {

	    }

	    @FXML
	    void StartexamBtn(ActionEvent event) {

	    }

	    @FXML
	    void ViewreportsBtn(ActionEvent event) {

	    }


	private static Stage currentStage;

	public void start(Stage primaryStage) {
		try {
			currentStage = primaryStage;
			Parent root = FXMLLoader.load(getClass().getResource("/gui/StudentSettings.fxml"));
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("/gui/StudentSettings.css").toExternalForm());
			primaryStage.setTitle("Student Settings");
			primaryStage.setScene(scene);
			primaryStage.setResizable(false);
			primaryStage.setOnCloseRequest(e -> {
				// ClientUI.chat.accept("client disconnected");
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
