package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.stage.Modality;
import javafx.stage.Stage;
import logic.question.Question;

/**
 * A controller that controls all the functionalites of previewing a question
 * @author Yonatan Rozen
 */
public class TeacherPreviewQuestionController implements Initializable {
	public static TeacherPreviewQuestionController tpqController;
	// JAVAFX INSTANCES ******************************************************
	@FXML
	private Label sbPreviewQuestionLbl;

	@FXML
	private Label sbQuestionBodyLbl;

	@FXML
	private RadioButton sbAnswer1Rb;

	@FXML
	private RadioButton sbAnswer2Rb;

	@FXML
	private RadioButton sbAnswer3Rb;

	@FXML
	private RadioButton sbAnswer4Rb;

	@FXML
	private Button sbOKBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Label previewQuestionLbl;
	private static Label questionBodyLbl;
	private static RadioButton answer1Rb;
	private static RadioButton answer2Rb;
	private static RadioButton answer3Rb;
	private static RadioButton answer4Rb;

	// STATIC INSTANCES ****************************************************
	private static Stage previewStage;

	// START METHOD *********************************************************
	public void start(Stage stage) throws IOException {
		tpqController = this;
		previewStage = stage;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherPreviewQuestion.fxml"));
		Scene mainScene = new Scene(root);
		mainScene.getStylesheets().add(getClass().getResource("/common/Client.css").toExternalForm());
		stage.setScene(mainScene);
		stage.getIcons().add(CommonMethodsHandler.CEMS_ICON);
		stage.setResizable(false);
		stage.initModality(Modality.APPLICATION_MODAL);
		stage.initOwner(ClientUI.mainStage);
		stage.setOnCloseRequest(e -> {
			stage.hide();
			TeacherMenuBarController.mainPaneBp.setDisable(false);
			TeacherMenuBarController.menuBarAp.setDisable(false);
			stage.close();
		});
		stage.show();
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		previewQuestionLbl = sbPreviewQuestionLbl;
		questionBodyLbl = sbQuestionBodyLbl;
		answer1Rb = sbAnswer1Rb;
		answer2Rb = sbAnswer2Rb;
		answer3Rb = sbAnswer3Rb;
		answer4Rb = sbAnswer4Rb;
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressOK(ActionEvent event) {
		previewStage.hide();
		previewStage.close();
	}

	// EXTERNAL USE METHODS *************************************************
	/**
	 * Sets all the detials of the chosen question
	 * @param question The question to preview
	 */
	public void setQuestion(Question question) {
		previewQuestionLbl.setText("Preview Question #" + question.getQuestionID());
		questionBodyLbl.setText(question.getQuestionBody());
		answer1Rb.setText(question.getAnswer1());
		answer2Rb.setText(question.getAnswer2());
		answer3Rb.setText(question.getAnswer3());
		answer4Rb.setText(question.getAnswer4());

		switch (question.getCorrectAnswer()) {
		case "1":
			answer1Rb.setSelected(true);
			break;
		case "2":
			answer2Rb.setSelected(true);
			break;
		case "3":
			answer3Rb.setSelected(true);
			break;
		case "4":
			answer4Rb.setSelected(true);
			break;
		default:
			break;
		}
	}
}
