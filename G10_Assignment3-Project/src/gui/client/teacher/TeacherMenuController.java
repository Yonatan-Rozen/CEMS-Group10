package gui.client.teacher;

import java.awt.Button;
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
import javafx.scene.control.Hyperlink;
import javafx.stage.Stage;

public class TeacherMenuController implements Initializable{
	//

	@FXML
	private Hyperlink LableTeacherName;

	@FXML
	private Hyperlink btnLogoyt;

	@FXML
	private Button btnStartExam;

	@FXML
	private Button btnCreateQuestion;

	@FXML
	private Button btnEditQuestion;

	@FXML
	private Button btnCrreateExam;

	@FXML
	private Button btnEditExam;

	@FXML
	private Button btnViewReport;

	@FXML
	private Button btnSettings;

	@FXML
	void LblTeacherName(ActionEvent event) {

	}

	@FXML
	void btnCreateQuestion(ActionEvent event) {

	}

	@FXML
	void btnCrreateExam(ActionEvent event) {

	}

	@FXML
	void btnEditExam(ActionEvent event) {

	}

	@FXML
	void btnEditQuestion(ActionEvent event) {

	}

	@FXML
	void btnLogout(ActionEvent event) {

	}

	@FXML
	void btnSettings(ActionEvent event) {

	}

	@FXML
	void btnStartExam(ActionEvent event) {

	}

	@FXML
	void btnViewReport(ActionEvent event) {

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

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub
		
	}
}