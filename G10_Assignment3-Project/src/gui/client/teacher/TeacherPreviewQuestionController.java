package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;

import gui.GuiTester;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.ToggleGroup;
import logic.question.Question;

public class TeacherPreviewQuestionController implements Initializable {
	public static TeacherPreviewQuestionController tpqController;
	public static Question question = new Question("01023", "How much is 1+1?", "3", "2", "11", "12", "2");
	//public static Question question;
	// JAVAFX INSTANCES ******************************************************
	

	@FXML
	private Label sbPreviewQuestionLbl;

	@FXML
	private Label sbQuestionBodyLbl;

	@FXML
	private RadioButton sbAnswer1Rb;

	@FXML
	private ToggleGroup sbAnswersTg;

	@FXML
	private RadioButton sbAnswer2Rb;

	@FXML
	private RadioButton sbAnswer3Rb;

	@FXML
	private RadioButton sbAnswer4Rb;
	
	@FXML
	private Button sbOKBtn;
	
	// STATIC JAVAFX INSTANCES **********************************************

	// START METHOD *********************************************************
	public void start() throws IOException {
		tpqController = this;
		Parent root = FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherPreviewQuestion.fxml"));
		Scene mainScene = new Scene(root);
		// scene.getStylesheets().add(getClass().getResource("/gui/client/SignIn.css").toExternalForm());
		GuiTester.primaryStage.setScene(mainScene);
		// primaryStage.setResizable(false);
		GuiTester.primaryStage.setOnCloseRequest(e -> {
			GuiTester.primaryStage.hide();
			System.out.println("Question preview window was closed");
			GuiTester.primaryStage.close();
		});

		GuiTester.primaryStage.show();
	}

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {

		sbPreviewQuestionLbl.setText("Preview Question #" + question.getQuestionID());
		sbQuestionBodyLbl.setText(question.getQuestionBody());
		sbAnswer1Rb.setText(question.getAnswer1());
		sbAnswer2Rb.setText(question.getAnswer2());
		sbAnswer3Rb.setText(question.getAnswer3());
		sbAnswer4Rb.setText(question.getAnswer4());

		switch (question.getCorrectAnswer()) {
		case "1":
			sbAnswer1Rb.setSelected(true);
			break;
		case "2":
			sbAnswer2Rb.setSelected(true);
			break;
		case "3":
			sbAnswer3Rb.setSelected(true);
			break;
		case "4":
			sbAnswer4Rb.setSelected(true);
			break;
		default:
			break;
		}
		
		System.out.println("Everything was set (Preview Question Controller)");
	}
	
	
	// ACTION METHODS *******************************************************
	@FXML
	public void btnPressOK(ActionEvent event) {
		GuiTester.primaryStage.hide();
		System.out.println("Question preview window was closed");
		GuiTester.primaryStage.close();
	}

	// EXTERNAL USE METHODS *************************************************
	public void setQuestion(Question question) {
		this.question = question;
	}
}
