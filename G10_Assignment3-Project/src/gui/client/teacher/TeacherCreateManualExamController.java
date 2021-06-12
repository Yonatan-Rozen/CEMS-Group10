package gui.client.teacher;

import java.awt.Desktop;
import java.io.File;
import java.net.URL;
import java.util.List;
import java.util.Optional;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
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
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.stage.FileChooser;
import javafx.stage.Stage;

public class TeacherCreateManualExamController implements Initializable {
	public static TeacherCreateManualExamController tcmeController;
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
	private TextField sbUploadFileTf;

	@FXML
	private TextField sbAllocatedTimeTf;

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
	private static TextField uploadFileTf;
	private static TextField allocatedTimeTf;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> bankList = FXCollections.observableArrayList();
	public static ObservableList<String> CourseList = FXCollections.observableArrayList();
	private static String msg;
	FileChooser fileChooser = new FileChooser();
	private Desktop desktop = Desktop.getDesktop();
	private static String FileName;
	private static String FilePath;
	private static String examID;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tcmeController = new TeacherCreateManualExamController();

		topPanelAp = sbTopPanelAp;

		examBankCb = sbExamBankCb;
		bankList.clear();
		bankList.add("----------");
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
		CourseList.clear(); // clear list
		CourseList.add("----------");
		chooseCourseCb.setValue("----------");
		chooseCourseCb.setItems(CourseList);

		allocatedTimeTf = sbAllocatedTimeTf;
		CommonMethodsHandler.getInstance().setIntegersOnlyTextLimiter(allocatedTimeTf, 3);
		uploadFileTf = sbUploadFileTf;

		ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername(), "2" });

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
			if (selectedFile != null) {

				FileName = selectedFile.getName();
				FilePath = selectedFile.getPath();
				uploadFileTf.setText(FilePath);
			}
		} catch (RuntimeException e) { e.printStackTrace(); }
	}

	@FXML
	void btnPressChangeQustionBank(ActionEvent event) {
		System.out.println("TeacherCreateManualExam::btnPressChangeQustionBank");
		sbTopPanelAp.setDisable(false);
		sbBotPanelAp.setDisable(true);
		examBankCb.setValue("----------");
		chooseCourseCb.setValue("----------");
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherCreateManualExam::btnPressContinue1");
		if (!examBankCb.getValue().equals("----------")) {

			CourseList.clear();
			CourseList.add("----------");
			chooseCourseCb.setValue("----------");
			chooseCourseCb.setItems(CourseList);

			sbTopPanelAp.setDisable(true);
			sbBotPanelAp.setDisable(false);
			ClientUI.chat.accept(
					new String[] { "GetCourseBySubject", examBankCb.getValue(), ChatClient.user.getUsername(), "2" });

		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing subject",
					"Please choose a subject from the list.").showAndWait();
		}
	}

	@FXML
	void btnPressFinish(ActionEvent event) throws Exception {
		System.out.println("TeacherCreateManualExam::btnPressFinish");
		String insertTime = allocatedTimeTf.getText();
		if (!chooseCourseCb.getValue().equals("----------")) {
			if (!insertTime.isEmpty()) {
				if (!sbUploadFileTf.getText().isEmpty()) {
					String correctAnswer, author = ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname();
					ClientUI.chat.accept(new String[] { "btnPressFinishCreateManualExam", chooseCourseCb.getValue(),
							examBankCb.getValue(), author, allocatedTimeTf.getText(), ChatClient.user.getUsername() });
					// TODO examID should have a value here
					ClientUI.chat.accept(new String[] { "TeacherUploadFile", examID, FilePath });

					ButtonType buttonYes = new ButtonType("Yes");
					ButtonType buttonNo = new ButtonType("No");
					Optional<ButtonType> request = CommonMethodsHandler.getInstance().getNewAlert(AlertType.INFORMATION, "Exam saved", "File was uploaded successfuly!",
							"Upload another file?",buttonYes, buttonNo).showAndWait();
					
					if (request.get() == buttonYes)
						TeacherMenuBarController.mainPaneBp.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherCreateManualExam"));
					else ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));

				} else {
					CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing file",
							"Please select a word document to upload.").showAndWait();
				}

			} else {
				CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message",
						"Missing allocated time", "Please insert the allocated time (min) for the exam.").showAndWait();
			}
		} else
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message",
					"Missing course ", "Please choose a course from the list.").showAndWait();
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

	public void successfulCreateDetailsAndSetExamID(String ID, String Msg) {
		examID = ID;
		msg = Msg;
	}
}
