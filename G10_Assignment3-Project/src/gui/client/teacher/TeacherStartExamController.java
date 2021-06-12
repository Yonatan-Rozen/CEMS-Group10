package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.Arrays;
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
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

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
    private TextArea sbCommentsTa;	

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
    private static TextArea commentsTa;
    private static TextField addedAmountTf;
    private static Button sendRequestBtn;
    private static Button lockExamBtn;
	
	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> examSubjectCourseIDList = FXCollections.observableArrayList();
	private static CommonMethodsHandler commonMethodHandler = CommonMethodsHandler.getInstance();
	public static String examID;
	public static String examType;
	private static boolean activeStudents = false;
	
	// START METHOD *********************************************************
	public void start(Stage mainStage) throws IOException {
	}

	
	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tseController = new TeacherStartExamController();
		topAp = sbTopAp;
		chooseExamCb = sbChooseExamCb;
		codeTf = sbCodeTf;
		commonMethodHandler.addTextLimiter(codeTf, 4);
		startBtn = sbStartBtn;
	    botAp = sbBotAp;
	    botAp.setDisable(true);
	    commentsTa = sbCommentsTa;
	    addedAmountTf = sbAddedAmountTf;
	    sendRequestBtn = sbSendRequestBtn;
	    lockExamBtn = sbLockExamBtn;
		commonMethodHandler.addTextLimiter(addedAmountTf, 2);
		//**********************************
		//populate choseExamCb with all available exams from the database
		examSubjectCourseIDList.clear();
		examSubjectCourseIDList.add("--------------------------------------------");
		ClientUI.chat.accept(new String[] {"GetSubjectCourseIDofExam"});
		chooseExamCb.setValue("--------------------------------------------");
		chooseExamCb.setItems(examSubjectCourseIDList);
		chooseExamCb.getSelectionModel().selectedItemProperty().addListener(
				(ObservableValue<? extends String> observable, String oldValue, String newValue) -> 
				{
					if (newValue != null) {
						if (newValue.equals("--------------------------------------------")) {
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
		System.out.println("TeacherStartExam::btnPressStart");
		if (codeTf.getLength() != 4)
			commonMethodHandler.getNewAlert(AlertType.WARNING, "Wrong Code", "Please enter a 4 character code.", "Press ok to continue.").showAndWait();
		else {
			TeacherMenuBarController.menuBarAp.setDisable(true);
			topAp.setDisable(true);
			botAp.setDisable(false);
			examID = chooseExamCb.getValue().split("\\#")[1]; // get exam ID from the selected value
			ClientUI.chat.accept(new String[] {"GetTypeOfExamAndOptionalComments", examID});
			ClientUI.chat.accept(new String[] {"SendMessageExamIDExamTypeAndExamCode", examID, examType, codeTf.getText()}); // TODO send message to all students
			if (activeStudents) {
				commonMethodHandler.getNewAlert(AlertType.INFORMATION, "Exam Started", "The exam is now in execution mode",
						"Please provide examinees with the entered code.").showAndWait();
			}
			else {
				commonMethodHandler.getNewAlert(AlertType.WARNING, "Exam Canceled", "There are no students connected at this moment!",
						"Please wait for at least ONE student to be able to start.").showAndWait();
				TeacherMenuBarController.menuBarAp.setDisable(false);
				topAp.setDisable(false);
				botAp.setDisable(true);
			}
		}
	}
	
	@FXML
    void btnPressLockExam(ActionEvent event) throws IOException {
		ButtonType buttonYes = new ButtonType("Confirm");
		ButtonType buttonCancel = new ButtonType("Cancel");
		Optional<ButtonType> request = commonMethodHandler.getNewAlert(AlertType.WARNING, "Exam Lock", 
				"Are you sure you want to lock the exam before the time", buttonYes, buttonCancel).showAndWait();
		if (request.get() == buttonYes) {
			ClientUI.chat.accept(new String[] {"SendMessageLockExam", examID}); // TODO (locks exam at the clients of the students)
			TeacherMenuBarController.menuBarAp.setDisable(false);
			ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
		}
    }

    @FXML
    void btnPressSendRequest(ActionEvent event) {
    	if (addedAmountTf.getText().isEmpty())
    		commonMethodHandler.getNewAlert(AlertType.ERROR, "Missing Input", "Please provide the amount of time (in minutes) to add").showAndWait();
    	else {
    		commonMethodHandler.getNewAlert(AlertType.INFORMATION, "Time Request", 
    				"Your request has been sent","Press ok to continue.").showAndWait();
    		ClientUI.chat.accept(new String[] {"RequestExtraTime", addedAmountTf.getText()} ); // TODO send message to principle
    	}
    }
	
	// EXTERNAL USE METHODS *************************************************
	public void setExamIDs(List<String> examIDs) {
		examSubjectCourseIDList.addAll(examIDs);
	}
	
	public void setTypeAndOptionalComments(String[] typeAndComments) {
		examType = typeAndComments[1];
		if (typeAndComments[2] != null )
			commentsTa.setText(typeAndComments[2]);
	}


	public void checkStartExam(Object[] msg) {
		activeStudents = Integer.parseInt(msg[1].toString()) != 0;
	}
}
