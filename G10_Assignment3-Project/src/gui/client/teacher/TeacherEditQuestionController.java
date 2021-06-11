package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.Scene;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonBar.ButtonData;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.control.RadioButton;
import javafx.scene.control.TextArea;
import javafx.scene.control.ToggleGroup;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.stage.Stage;
import logic.question.Question;

public class TeacherEditQuestionController implements Initializable {
	public static TeacherEditQuestionController teqController;
	
	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbBotPanelAp;
	
	@FXML
	private Label sbEditQuestionLbl;
	
	@FXML
	private TextArea sbQuestionBodyTa;

	@FXML
	private ToggleGroup sbAnswersTg;

	@FXML
	private RadioButton sbMarkAnswer1Rb;

	@FXML
	private RadioButton sbMarkAnswer2Rb;

	@FXML
	private RadioButton sbMarkAnswer3Rb;

	@FXML
	private RadioButton sbMarkAnswer4Rb;

	@FXML
	private TextArea sbAnswer1Ta;

	@FXML
	private TextArea sbAnswer2Ta;

	@FXML
	private TextArea sbAnswer3Ta;

	@FXML
	private TextArea sbAnswer4Ta;

	@FXML
	private Button sbDiscardChangesBtn;

	@FXML
	private Button sbConfirmChangesBtn;
	
	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static Label editQuestionLbl;
	private static TextArea questionBodyTa;
	private static RadioButton markAnswer1Rb;
	private static RadioButton markAnswer2Rb;
	private static RadioButton markAnswer3Rb;
	private static RadioButton markAnswer4Rb;
	private static TextArea answer1Ta;
	private static TextArea answer2Ta;
	private static TextArea answer3Ta;
	private static TextArea answer4Ta;
	
	// STATIC INSTANCES *****************************************************
	private static boolean questionSet = false;
	private static Question question;
	private static RadioButton selected;
	private static String title = "";
	private static String header = "";
	private static String context = "";
	private static AlertType type;
	

	public void start(Stage stage) throws IOException {
		Pane root = FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherEditQuestion.fxml"));
		Scene scene = new Scene(root);
		stage.setScene(scene);
	}
	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		System.out.println("everything is fine");
		teqController = new TeacherEditQuestionController();
		botPanelAp = sbBotPanelAp;
		editQuestionLbl = sbEditQuestionLbl;
		questionBodyTa = sbQuestionBodyTa;
		markAnswer1Rb = sbMarkAnswer1Rb;
		markAnswer2Rb = sbMarkAnswer2Rb;
		markAnswer3Rb = sbMarkAnswer3Rb;
		markAnswer4Rb = sbMarkAnswer4Rb;
		answer1Ta = sbAnswer1Ta;
		answer2Ta = sbAnswer2Ta;
		answer3Ta = sbAnswer3Ta;
		answer4Ta = sbAnswer4Ta;
		
	}

	// ACTION METHODS *******************************************************
	@FXML
	public void rbPressMarkAnswer1(ActionEvent event) {
		System.out.println("TeacherEditQuestionController::rbPressMarkAnswer1");
		selected = markAnswer1Rb;
	}

	@FXML
	public void rbPressMarkAnswer2(ActionEvent event) {
		System.out.println("TeacherEditQuestionController::rbPressMarkAnswer2");
		selected = markAnswer2Rb;
	}

	@FXML
	public void rbPressMarkAnswer3(ActionEvent event) {
		System.out.println("TeacherEditQuestionController::rbPressMarkAnswer3");
		selected = markAnswer3Rb;
	}

	@FXML
	public void rbPressMarkAnswer4(ActionEvent event) {
		System.out.println("TeacherEditQuestionController::rbPressMarkAnswer4");
		selected = markAnswer4Rb;
	}
	
	@FXML
	public void btnPressDiscardChanges(ActionEvent event) {
		System.out.println("TeacherEditQuestionController::btnPressDiscardChanges");
		CommonMethodsHandler methodHandler = CommonMethodsHandler.getInstance();
		ButtonType buttonYes = new ButtonType("Yes");
		ButtonType buttonCancel = new ButtonType("Cancel");
		
		if (!questionNotAltered()) {
			Optional<ButtonType> result = methodHandler.getNewAlert(AlertType.CONFIRMATION, "Cancel changes", "All edited information will be reverted!", 
					"Are you sure you want to discard all changes to this question?", buttonYes,buttonCancel).showAndWait();
			
			if (result.get() == buttonYes)
				TeacherMenuBarController.mainPaneBp.setCenter(methodHandler.getPane("teacher", "TeacherChooseEditQuestion"));
		}
		else TeacherMenuBarController.mainPaneBp.setCenter(methodHandler.getPane("teacher", "TeacherChooseEditQuestion"));
	}
	
	@FXML
	public void btnPressConfirmChanges(ActionEvent event) {
		System.out.println("TeacherEditQuestionController::btnPressConfirmChanges");
		CommonMethodsHandler methodHandler = CommonMethodsHandler.getInstance();
		ButtonType buttonNewEdit = new ButtonType("choose another");
		ButtonType buttonReEdit = new ButtonType("continue editing",ButtonData.CANCEL_CLOSE);
		
		// if any field was emptied, show an alert
		if (hasMissingField())
			methodHandler.getNewAlert(AlertType.ERROR, "Error message","Empty Fields", "All fields are required!").showAndWait();
		
		// if no field was changed, show an alert
		else if (questionNotAltered())
		{
			Optional<ButtonType> result = methodHandler.getNewAlert(AlertType.WARNING, "Unchanged details","You didn't edit any part of the quesiton", 
					"Would you rather edit another quesiton\nOR keep editing the current one?", buttonNewEdit, buttonReEdit).showAndWait();
			if (result.get() == buttonNewEdit){
				System.out.println("alert::Yes");
				try { TeacherMenuBarController.tmbController.btnPressEditQuestion(event);
				} catch (IOException e) { e.printStackTrace();}
			}
		}
		else {
			// save changes
			saveCurrentQuestionDetails();
			
			if (questionSet) {
				questionSet = false;
				Optional<ButtonType> result = methodHandler.getNewAlert(type, title,header, context,buttonNewEdit,buttonReEdit).showAndWait();
				if (result.get() == buttonNewEdit){
					System.out.println("alert::NewEdit");
					//TeacherMenuBarController.mainPaneBp.setCenter(methodHandler.getPane("teacher", "TeacherChooseEditQuestion"));
					
					try { TeacherMenuBarController.tmbController.btnPressEditQuestion(new ActionEvent());
					} catch (IOException e) { e.printStackTrace();}
				}
				else if (result.get() == buttonReEdit){
					System.out.println("alert::ReEdit");
				}
			} else System.out.println("problem at chat client!");
		}
	}
	// INTERNAL USER METHODS *************************************************
	private boolean hasMissingField() {
		return (questionBodyTa.getText().equals("") || answer1Ta.getText().equals("") || 
				answer2Ta.getText().equals("") || answer3Ta.getText().equals("") || answer4Ta.getText().equals(""));
	}
	
	private boolean questionNotAltered() {
		String currentMarkedAnswer = CommonMethodsHandler.getInstance().getSelectedAnswer(selected);
		return (questionBodyTa.getText().equals(question.getQuestionBody()) &&  answer1Ta.getText().equals(question.getAnswer1()) 
				&& answer2Ta.getText().equals(question.getAnswer2()) &&  answer3Ta.getText().equals(question.getAnswer3()) 
				&& answer4Ta.getText().equals(question.getAnswer4()) && question.getCorrectAnswer().equals(currentMarkedAnswer));
	}
	
	private void saveCurrentQuestionDetails() {
		String currentMarkedAnswer = CommonMethodsHandler.getInstance().getSelectedAnswer(selected);
		question.setQuestionBody(questionBodyTa.getText());
		question.setAnswer1(answer1Ta.getText());
		question.setAnswer2(answer2Ta.getText());
		question.setAnswer3(answer3Ta.getText());
		question.setAnswer4(answer4Ta.getText());
		System.out.println(currentMarkedAnswer);
		question.setCorrectAnswer(currentMarkedAnswer);

		ClientUI.chat.accept(new Object[] {"UpdateQuestion", question});
	}
	
	// EXTERNAL USE METHODS **************************************************
	public void setQuestion(Question questionToEdit) {
		question = questionToEdit;
		editQuestionLbl.setText("Editing question #" + question.getQuestionID() + " :");
		questionBodyTa.setText(question.getQuestionBody());
		answer1Ta.setText(question.getAnswer1());
		answer2Ta.setText(question.getAnswer2());
		answer3Ta.setText(question.getAnswer3());
		answer4Ta.setText(question.getAnswer4());

		switch(question.getCorrectAnswer()) {
		case "1":
			markAnswer1Rb.setSelected(true);
			selected = markAnswer1Rb;
			break;
		case "2":
			markAnswer2Rb.setSelected(true);
			selected = markAnswer2Rb;
			break;
		case "3":
			markAnswer3Rb.setSelected(true);
			selected = markAnswer3Rb;
			break;
		case "4":
		default:
			markAnswer4Rb.setSelected(true);
			selected = markAnswer4Rb;
			break;
		}
	}
	
	public void successfulEditQuestion(String message) {
		questionSet = true;
		type = AlertType.CONFIRMATION;
		title = "Saved changes";
		header = message;
		context = "Would you rather edit another quesiton\nOR keep editing the current one?";
		
	}
}
