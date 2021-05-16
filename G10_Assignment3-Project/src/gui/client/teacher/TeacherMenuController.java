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

public class TeacherMenuController implements Initializable {

	@FXML
	private Hyperlink sbTeacherNameLnk;

	@FXML
	private Hyperlink sbLogoutLnk;

	@FXML
	private Button sbStartExamBtn;

	@FXML
	private Button sbCreateQuestionBtn;

	@FXML
	private Button sbEditQuestionBtn;

	@FXML
	private Button sbCrreateExamBtn;

	@FXML
	private Button sbEditExamBtn;

	@FXML
	private Button sbViewReportBtn;

	@FXML
	private Button sbSettingsBtn;

	@FXML
	void LnkLogout(ActionEvent event) {

	}

	@FXML
	void LnkTeacherName(ActionEvent event) {

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
	void btnSettings(ActionEvent event) {

	}

	@FXML
	void btnStartExam(ActionEvent event) {

	}

	@FXML
	void btnViewReport(ActionEvent event) {

	}

	private static Stage currentStage;

	public void start(Stage mainStage) {
		try {
			currentStage = mainStage;
			Parent root = FXMLLoader.load(getClass().getResource("/gui/StudentSettings.fxml"));
			Scene scene = new Scene(root);
			// scene.getStylesheets().add(getClass().getResource("/gui/StudentSettings.css").toExternalForm());
			mainStage.setTitle("Student Settings");
			mainStage.setScene(scene);
			mainStage.setResizable(false);
			mainStage.setOnCloseRequest(e -> {
				// ClientUI.chat.accept("client disconnected");
				mainStage.hide();
				Alert alert = new Alert(AlertType.INFORMATION);
				alert.setTitle("Client disconnected");
				alert.setHeaderText("You have been disconnected from the server(StudentSettings)");
				alert.setContentText("Press ok to continue...");
				alert.showAndWait();
				System.exit(0);
			});
			mainStage.show();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
		// TODO Auto-generated method stub

	}
}