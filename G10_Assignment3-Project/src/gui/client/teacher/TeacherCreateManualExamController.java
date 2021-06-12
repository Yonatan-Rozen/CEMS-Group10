package gui.client.teacher;

import java.awt.Desktop;
import java.awt.Font;
import java.io.BufferedInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import common.MyFile;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextArea;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TeacherCreateManualExamController implements Initializable {
	public static TeacherCreateManualExamController tcmeController;
	/**
	 * The instance of the client that created this ConsoleChat.
	 */
	// ChatClient client;
	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private Button sbFinishBtn;

	@FXML
	private Button sbChangeQustionBankBtn;

	@FXML
	private Button sbSearchBtn;

	@FXML
	private Button sbContinue1Btn;

	@FXML
	private TextArea sbUploadFileTa;

	@FXML
	private TextArea sbAllocatedTimeTa;

	@FXML
	private ChoiceBox<String> sbChooseCourseCb;

	@FXML
	private ChoiceBox<String> sbExamBankCb;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> examBankCb;
	private static AnchorPane botPanelAp;
	private static ChoiceBox<String> chooseCourseCb;
	private static Button finishBtn;
	private static Button changeQustionBankBtn;
	private static Button searchBtn;
	private static Button continue1Btn;
	private static TextArea uploadFileTa;
	private static TextArea allocatedTimeTa;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");
	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
	private static String msg;
	FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	private static String FileName;
	private static String FilePath;
	private static String examID = "010107";

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcmeController = new TeacherCreateManualExamController();

		/**** First panel ****/
		topPanelAp = sbTopPanelAp;
		examBankCb = sbExamBankCb;
		// set "----------" as the first value of the choice box
		examBankCb.setValue("----------");
		// set the choice box to get it's items from 'bankList'
		examBankCb.setItems(bankList);
		searchBtn = sbSearchBtn;
		continue1Btn = sbContinue1Btn;
		botPanelAp = sbBotPanelAp;
		changeQustionBankBtn = sbChangeQustionBankBtn;
		finishBtn = sbFinishBtn;
		botPanelAp.setDisable(true);
		chooseCourseCb = sbChooseCourseCb;
		allocatedTimeTa = sbAllocatedTimeTa;
		allocatedTimeTa.setText("Enter Time!");
		uploadFileTa = sbUploadFileTa;
		uploadFileTa.setText("File path");
		uploadFileTa.getFont();
		System.out.println(uploadFileTa.getFont());
//		uploadFileTa.setFont(new Font("Arial",1,15));

		if (bankList.size() == 1) { // add banks only once
			ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername(), "2" });
		}
	}

	// ACTION METHODS *******************************************************

	@FXML
	void btnPressSearch(ActionEvent event) throws RuntimeException {
		System.out.println("TeacherCreateManualExam::btnPressSearch");

		fileChooser.setTitle("Choose Exam File");
		fileChooser.getExtensionFilters().addAll(new FileChooser.ExtensionFilter("docx Files", "*.docx"),
				new FileChooser.ExtensionFilter("doc Files", "*.doc"));
		try {
			File selectedFile = fileChooser.showOpenDialog(new Stage());
			
			/////
			//add listener ?? if we close the stage without choose file there is nullpointer exception
			/////
			
			
			FileName = selectedFile.getName();
			FilePath = selectedFile.getPath();
			uploadFileTa.setText(FilePath);

			if (FilePath != null) {

//				
//				MyFile msg = new MyFile(FileName);
//				File newFile = new File(FilePath);
//
//				byte[] mybytearray = new byte[(int) newFile.length()];
//				FileInputStream fis = new FileInputStream(newFile); // reads the data from file(byte by byte) 
//				BufferedInputStream bis = new BufferedInputStream(fis); //reads data from memory
//
//				msg.initArray(mybytearray.length);
//				msg.setSize(mybytearray.length);
//
//				bis.read(msg.getMybytearray(), 0, mybytearray.length); //reads
//				sendToServer(msg);

			}

		} catch (RuntimeException e) { //include nullpointer exception
			e.printStackTrace();
		} 
	}

	@FXML
	void btnPressChangeQustionBank(ActionEvent event) {
		System.out.println("TeacherCreateManualExam::btnPressChangeQustionBank");
		sbTopPanelAp.setDisable(false);
		sbBotPanelAp.setDisable(true);
		examBankCb.setValue("----------");
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherCreateManualExam::btnPressContinue1");
		if (examBankCb.getValue() != "----------") {

			CourseList.clear(); // clear list
			allocatedTimeTa.setText("Enter Time!");
			uploadFileTa.setText("File path");
			sbTopPanelAp.setDisable(true);
			sbBotPanelAp.setDisable(false);
			ClientUI.chat.accept(
					new String[] { "GetCourseBySubject", examBankCb.getValue(), ChatClient.user.getUsername(), "2" });

			chooseCourseCb.setItems(CourseList);

		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message",
					"Missing Exam Bank/Subject Name", "Must to choose Subject name/bank").showAndWait();
		}
	}

	@FXML
	void btnPressFinish(ActionEvent event) throws Exception {
		System.out.println("TeacherCreateManualExam::btnPressFinish");
		String insertTime = allocatedTimeTa.getText();
		if (chooseCourseCb.getValue() != null) {
			if (!insertTime.equals("Enter Time!")) {
				if (Integer.parseInt(insertTime) > 0) {
					if (!sbUploadFileTa.getText().equals("File path")) {
						String correctAnswer, author = ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname();
						ClientUI.mainScene.setRoot(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherMenu"));
						ClientUI.chat.accept(new String[] { "btnPressFinishCreateManualExam", chooseCourseCb.getValue(),
								examBankCb.getValue(), author, allocatedTimeTa.getText(), ChatClient.user.getUsername() });
						// TODO examID should have a value
						ClientUI.chat.accept(new String[] {"TeacherUploadFile",examID, FilePath});		

						//////
						// pop-up message that exam success or something like that
						//////
						
						
//		MyFile msg = new MyFile(FileName);
//		String LocalfilePath = FilePath;
//		System.out.println(LocalfilePath);
//		try {
//
//			File newFile = new File(LocalfilePath);
//
//			byte[] mybytearray = new byte[(int) newFile.length()];
//			FileInputStream fis = new FileInputStream(newFile); // reads the data from file(byte by byte)
//			BufferedInputStream bis = new BufferedInputStream(fis); // reads data from memory
//
//			msg.initArray(mybytearray.length);
//			msg.setSize(mybytearray.length);
//
//			bis.read(msg.getMybytearray(), 0, mybytearray.length); // reads
//		//	sendToServer(msg);
//		} catch (Exception e) {
//			System.out.println("Error send " + ((MyFile) msg).getFileName() + " to Server");
//		}
//		ClientUI.chat.accept(msg);

//		ClientUI.chat.accept(new String[] { "SaveFileExam",msg, FileName, FilePath, ChatClient.user.getUsername() });
					} else {
						CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing File",
								"Must to Enter file or right path").showAndWait();
					}

				} else {
					CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message",
							"Negative/zero time", "Must to choose positive value for allocated time").showAndWait();
				}
			} else {
				CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message",
						"Missing allocated time", "Must to enter value (allocated time)").showAndWait();
			}
		} else {
			CommonMethodsHandler.getInstance()
					.getNewAlert(AlertType.ERROR, "Error message", "Missing Course Name", "Must to choose course name")
					.showAndWait();
		}
	}

	// EXTERNAL USE METHODS **************************************************
	public void setBankChoiceBox(List<String> msg) {
		System.out.println(msg.toString());
		bankList.addAll(msg);
	}

	public void setCourseChoiceBox(List<String> msg) {
		System.out.println(msg.toString());
		CourseList.addAll(msg);
		System.out.println(CourseList);
	}

	public void successfulCreateExam(String Msg) {
		msg = Msg;
	}
	
	public void setExamID(String ID) {
		examID = ID;
	}
}
