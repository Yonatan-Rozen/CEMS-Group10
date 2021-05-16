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
import javafx.scene.control.RadioButton;
import javafx.scene.control.TableView;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.stage.Stage;

public class TeacherCreateQuestionController implements Initializable {

	@FXML
	private RadioButton sbchoose1RadioBtn;

	@FXML
	private RadioButton sbchoose2RadioBtn;

	@FXML
	private RadioButton sbchoose3RadioBtn;

	@FXML
	private RadioButton sbchoose4RadioBtn;

	@FXML
	private Label sbAns2Text;

	@FXML
	private Label sbAns3Text;

	@FXML
	private Label sbAns4Text;

	@FXML
	private Label sbAns1Text;

	@FXML
	private Button sbDiscardQuestion2Btn;

	@FXML
	private Button sbSaveQuestionBtn;

	@FXML
	private Label sbInsertBodyText;

	@FXML
	private Button sbDiscardQuestionBtn;

	@FXML
	private Button sbCreateQuestionBtn;

	@FXML
	private Hyperlink sbAddNewProffesionLnk;

	@FXML
	private Hyperlink sbRemoveSelectedProfessionLnk;

	@FXML
	private ComboBox<?> sbChooseQuestionBankComboBox;

	@FXML
	void ComboBoxChooseQuestionBank(ActionEvent event) {

	}

	@FXML
	void LnkAddNewProffesion(ActionEvent event) {

	}

	@FXML
	void LnkRemoveSelectedProfession(ActionEvent event) {

	}

	@FXML
	void btnCreateQuestion(ActionEvent event) {

	}

	@FXML
	void btnDiscardQuestion(ActionEvent event) {

	}

	@FXML
	void btnDiscardQuestion2(ActionEvent event) {

	}

	@FXML
	void btnSaveQuestion(ActionEvent event) {

	}

	@FXML
	void radioBtnchoose1(ActionEvent event) {

	}

	@FXML
	void radioBtnchoose2(ActionEvent event) {

	}

	@FXML
	void radioBtnchoose3(ActionEvent event) {

	}

	@FXML
	void radioBtnchoose4(ActionEvent event) {

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
