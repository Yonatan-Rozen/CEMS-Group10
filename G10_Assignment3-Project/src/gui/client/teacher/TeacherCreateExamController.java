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

public class TeacherCreateExamController implements Initializable {

	@FXML
	private Button sbBackBtn;

	@FXML
	private ComboBox<?> sbChooseCourseComboBox;

	@FXML
	private Button sbEditExistingExamsBtn;

	@FXML
	private ComboBox<?> sbChooseExamBankComboBox;

	@FXML
	private Button sbContinueBtn;

	@FXML
	private Button sbCancelCreationBtn;

	@FXML
	private TableView<?> sbAvailableQuestionsTable;

	@FXML
	private TableView<?> sbCurrentQuestionsTable;

	@FXML
	private Button sbChangeQuestionBankBtn;

	@FXML
	private Button sbFinishContinueBtn;

	@FXML
	void ComboBoxChooseCourse(ActionEvent event) {

	}

	@FXML
	void ComboBoxChooseExamBank(ActionEvent event) {

	}

	@FXML
	void btnBack(ActionEvent event) {

	}

	@FXML
	void btnCancelCreation(ActionEvent event) {

	}

	@FXML
	void btnChangeQuestionBank(ActionEvent event) {

	}

	@FXML
	void btnContinue(ActionEvent event) {

	}

	@FXML
	void btnEditExistingExams(ActionEvent event) {

	}

	@FXML
	void btnFinishContinue(ActionEvent event) {

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

//	public void getBtn(ActionEvent event) throws Exception {
//		Point windowPosition = new Point(currentStage.getX(), currentStage.getY());
//		((Node) event.getSource()).getScene().getWindow().hide();
//		edic = new ExamDataInfoController();
//		edic.start(new Stage(), windowPosition);
//	}

	@Override
	public void initialize(URL location, ResourceBundle resources) {
	}

}
