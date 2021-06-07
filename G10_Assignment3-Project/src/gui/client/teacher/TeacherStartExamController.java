package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import logic.exam.IExam;
import logic.question.Question;

public class TeacherStartExamController implements Initializable {
	public static TeacherStartExamController tseController;
	// JAVAFX INSTANCES *****************************************************
	@FXML
    private AnchorPane sbTopAp;

    @FXML
    private ChoiceBox<String> sbChooseExamCb;

    @FXML
    private TextField sbCodeTf;

    @FXML
    private Button sbStartBtn;

    @FXML
    private AnchorPane sbBotAp;

    @FXML
    private TextField sbAddedAmountTf;

    @FXML
    private Button sbSendRequestBtn;

    @FXML
    private Button sbLockExamBtn;

    
	// STATIC JAVAFX INSTANCES **********************************************
    private static AnchorPane topAp;
    private static ChoiceBox<String> chooseExamCb;
    private static TextField codeTf;
    private static Button startBtn;
    private static AnchorPane botAp;
    private static TextField addedAmountTf;
    private static Button sendRequestBtn;
    private static Button lockExamBtn;
	
	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> examSubjectCourseIDList = FXCollections.observableArrayList();
	public static List<Question> examQuestions;
	public static IExam exam;
	public static boolean examActive = true;
	
	// START METHOD *********************************************************
	public void start(Stage mainStage) throws IOException {
	}

	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tseController = new TeacherStartExamController();
		CommonMethodsHandler commonMethodHandler = CommonMethodsHandler.getInstance();
		topAp = sbTopAp;
		chooseExamCb = sbChooseExamCb;
		codeTf = sbCodeTf;
		commonMethodHandler.addTextLimiter(codeTf, 4);
		startBtn = sbStartBtn;
	    botAp = sbBotAp;
	    botAp.setDisable(true);
	    addedAmountTf = sbAddedAmountTf;
	    sendRequestBtn = sbSendRequestBtn;
	    lockExamBtn = sbLockExamBtn;
		
		//**********************************
		//populate choseExamCb with all available exams from the database
		examSubjectCourseIDList.clear();
		examSubjectCourseIDList.add("------------------------------");
		ClientUI.chat.accept(new String[] {"GetSubjectCourseIDofExam"});
		chooseExamCb.setValue("------------------------------");
		chooseExamCb.setItems(examSubjectCourseIDList);
		chooseExamCb.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
				{
					if (newValue != null) {
						if (newValue.equals("------------------------------")) {
							codeTf.setDisable(true);
							startBtn.setDisable(true);
						}
						else {
							codeTf.setDisable(false);
							startBtn.setDisable(false);
						}
					}
				});
		codeTf.setDisable(true);
		startBtn.setDisable(true);
	}
	
	// ACTION METHODS *******************************************************
	
	@FXML
	void btnPressStart(ActionEvent event) {
		CommonMethodsHandler commonMethodHandler = CommonMethodsHandler.getInstance();
		System.out.println("TeacherStartExam::btnPressStart");
		if (codeTf.getLength() != 4)
			commonMethodHandler.getNewAlert(AlertType.WARNING, "Wrong Code", "Please enter a 4 character code.", "Press ok to continue.").showAndWait();
		else {
			TeacherMenuBarController.menuBarAp.setDisable(true);
			topAp.setDisable(true);
			botAp.setDisable(false);
			String examID = chooseExamCb.getValue().split("\\#")[1]; // get exam ID from the selected value
			ClientUI.chat.accept(new String[] {"GetExamByID", examID});
			if (exam.getType().equals("C"))
				ClientUI.chat.accept(new String[] {"GetQuestionsInExam", examID});
			commonMethodHandler.getNewAlert(AlertType.INFORMATION, "Exam Started", "The exam is now in execution mode",
					"Please provide examinees with the entered code.").showAndWait();
			if (exam.getType().equals("C"))
				ClientUI.chat.accept(new Object[] {"SendComputerizedExamToAllConnectedStudents", exam, examQuestions, codeTf.getText()}); // TODO
			else ClientUI.chat.accept(new Object[] {"SendManualExamToAllConnectedStudents", exam, codeTf.getText()}); // TODO
			commonMethodHandler.addTextLimiter(addedAmountTf, 2);
		}
		
		// TODO (###) make a thread wait here, and continue after an interrupt or after locking the exam
		
//		TeacherMenuBarController.menuBarAp.setDisable(false);
//		try { TeacherMenuController.tmbController.btnPressBack(new ActionEvent());
//		} catch (IOException e) { e.printStackTrace(); }
	}
	
	@FXML
    void btnPressLockExam(ActionEvent event) throws IOException {
		ButtonType buttonYes = new ButtonType("Confirm");
		ButtonType buttonCancel = new ButtonType("Cancel");
		Optional<ButtonType> request = CommonMethodsHandler.getInstance().getNewAlert(AlertType.WARNING, "Exam Lock", 
				"Are you sure you want to lock the exam before the time", buttonYes, buttonCancel).showAndWait();
		if (request.get() == buttonYes) {
			try {ClientUI.chat.accept(new String[] {"LockExam",exam.getExamID()}); // TODO (locks exam at the clients of the students)
			}catch(NullPointerException e) {System.out.println("The exam wasn't loaded!");}
			lockExam(); // TODO continue from (###)
		}
    }

    @FXML
    void btnPressSendRequest(ActionEvent event) {
    	CommonMethodsHandler commonMethodHandler = CommonMethodsHandler.getInstance();
    	if (addedAmountTf.getText().isEmpty()) {
    		commonMethodHandler.getNewAlert(AlertType.ERROR, "Missing Input", "Please provide the amount of time (in minutes) to add").showAndWait();
    	}
    	else {
    		commonMethodHandler.getNewAlert(AlertType.INFORMATION, "Time Request", 
    				"Your request has been sent","Press ok to continue.").showAndWait();
    		ClientUI.chat.accept(new String[] {"RequestExtraTime", addedAmountTf.getText()} ); // TODO
    	}
    }
	
	// EXTERNAL USE METHODS *************************************************
	public void setExamIDs(List<String> examIDs) {
		examSubjectCourseIDList.addAll(examIDs);
	}
	
	public void setReadyExam(IExam readyExam) {
		exam = readyExam;
	}
	
	public void setExamQuestions(List<Question> questionList) {
		examQuestions = questionList;
	}
	
	public void lockExam() {
		examActive = false;
	}
}
