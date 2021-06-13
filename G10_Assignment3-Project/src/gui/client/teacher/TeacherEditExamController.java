package gui.client.teacher;

import java.io.File;
import java.io.IOException;
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
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import javafx.scene.shape.Circle;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import logic.exam.IExam;

public class TeacherEditExamController implements Initializable {
	public static TeacherEditExamController teeController;

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbLeftPanelAp;

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private AnchorPane sbRightPanelAp;
	
	@FXML
	private AnchorPane sbCommentsAp;
	
	@FXML
	private AnchorPane sbUploadAp;	

	@FXML
	private TextArea sbStudentCommentsTa;

	@FXML
	private TextArea sbTeacherCommentsTa1;

	@FXML
	private TextArea sbAllocatedTimeTa;
	
	@FXML
	private TextField sbAllocatedTimeTf;

	@FXML
	private TableView<IExam> sbExamsTv;

	@FXML
	private TableColumn<IExam, String> sbExamIDTc;

	@FXML
	private TableColumn<IExam, String> sbcourseIDTc;

	@FXML
	private Button sbFinishBtn;

	@FXML
	private Button sbEditSelectedExamBtn;

	@FXML
	private Button sbChangeBankBtn;

	@FXML
	private Button sbDeleteExamBtn1;

	@FXML
	private Button sbContinue1Btn;

	@FXML
	private Button sbChangeExamBtn;

	@FXML
	private Button sbSearchBtn;

	@FXML
	private TextField sbUploadFileTf;

	@FXML
	private ChoiceBox<String> sbChooseBankCb;

	@FXML
	private Circle sbCircle;

	// STATIC JAVAFX INSTANCES **********************************************
	private static Circle circle;
	private static AnchorPane botPanelAp;
	private static TableView<IExam> examsTv;
	private static TableColumn<IExam, String> examIDTc;
	private static TableColumn<IExam, String> courseIDTc;
	private static AnchorPane leftPanelAp;
	private static AnchorPane topPanelAp;
	private static AnchorPane rightPanelAp;
	private static AnchorPane commentsAp;
	private static AnchorPane uploadAp;	
	private static ChoiceBox<String> chooseBankCb;
	
	private static Button continue1Btn;
	private static Button deleteExamBtn1;
	private static Button editSelectedExamBtn;
	private static Button changeBankBtn;
	private static Button finishBtn;
	private static Button changeExamBtn;
	private static Button searchBtn;
	private static TextArea studentCommentsTa;
	private static TextArea teacherCommentsTa1;
//	private static TextArea allocatedTimeTa;
	private static TextField uploadFileTf;
	private static TextField allocatedTimeTf;

	// STATIC INSTANCES *****************************************************
	private static String ExamID, ExamType, FileName, FilePath;
	public static ObservableList<String> bankList = FXCollections.observableArrayList();
	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
	private static List<IExam> examList;
	ObservableList<IExam> examObservableList = FXCollections.observableArrayList();
	private static String msg;
	private static FileChooser fileChooser = new FileChooser();

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teeController = new TeacherEditExamController();

		examsTv = sbExamsTv;
		CommonMethodsHandler.getInstance().disableTableColumnSwap(examsTv);
		examIDTc = sbExamIDTc;
		courseIDTc = sbcourseIDTc;
		leftPanelAp = sbLeftPanelAp;
		rightPanelAp = sbRightPanelAp;
		commentsAp = sbCommentsAp; 
		uploadAp = sbUploadAp;	    
		topPanelAp = sbTopPanelAp;
		chooseBankCb = sbChooseBankCb;
		continue1Btn = sbContinue1Btn;
		deleteExamBtn1 = sbDeleteExamBtn1;
		editSelectedExamBtn = sbEditSelectedExamBtn;
		changeBankBtn = sbChangeBankBtn;
		finishBtn = sbFinishBtn;
		changeExamBtn = sbChangeExamBtn;
		searchBtn = sbSearchBtn;
		studentCommentsTa = sbStudentCommentsTa;
		teacherCommentsTa1 = sbTeacherCommentsTa1;
		allocatedTimeTf = sbAllocatedTimeTf;
		CommonMethodsHandler.getInstance().setIntegersOnlyTextLimiter(allocatedTimeTf, 3);
		uploadFileTf = sbUploadFileTf;
		teacherCommentsTa1.setWrapText(true);
		studentCommentsTa.setWrapText(true);
		circle = sbCircle;
		circle.setStyle("-fx-fill: #f4f4f4;");

		leftPanelAp.setDisable(true);
		rightPanelAp.setDisable(true);
		bankList.clear();
		bankList.add("----------");
		chooseBankCb.setValue("----------");
		chooseBankCb.setItems(bankList);

		ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername(), "3" });
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressContinue1");
		if (!chooseBankCb.getValue().equals("----------")) {

			topPanelAp.setDisable(true);
			leftPanelAp.setDisable(false);
			circle.setStyle("-fx-fill: #f4f4f4;");

//			CourseList.clear(); // clear list <---- not used - Yonatan
			
			ClientUI.chat.accept(new String[] { "btnPressShowExamsBySubject", chooseBankCb.getValue(),ChatClient.user.getUsername() });

			examIDTc.setCellValueFactory(new PropertyValueFactory<IExam, String>("examID"));
			courseIDTc.setCellValueFactory(new PropertyValueFactory<IExam, String>("courseID"));
			
		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message",
					"Missing Exam Bank/Subject Name", "Must to choose Subject name/bank").showAndWait();
		}
	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressChangeBank");
		topPanelAp.setDisable(false);
		leftPanelAp.setDisable(true);
		rightPanelAp.setDisable(true);
		circle.setStyle("-fx-fill: #f4f4f4;");

	}

	@FXML
	void btnPressFinish(ActionEvent event) throws IOException {
		System.out.println("TeacherEditExam::btnPressFinish");
		String insertTime = allocatedTimeTf.getText();
		if (!insertTime.equals("Enter Time!")) {
			if (Integer.parseInt(insertTime) > 0) {

				if (ExamType.equals("C")) {
					System.out.println("check info = " + ExamID +" "+ studentCommentsTa.getText()
							+ teacherCommentsTa1.getText() + allocatedTimeTf.getText());
					ClientUI.chat.accept(new String[] { "btnPressFinishCreateComputerizedExam", ExamID,
							studentCommentsTa.getText(), teacherCommentsTa1.getText(), allocatedTimeTf.getText(), "2",
							ChatClient.user.getUsername() });
				} else { // ExamType = "M"
					System.out.println("check info(manual) = " + ExamID + allocatedTimeTf.getText() + FilePath);
					ClientUI.chat.accept(new String[] { "btnPressFinishEditManualExam", ExamID, FilePath,
							allocatedTimeTf.getText(), ChatClient.user.getUsername() });
				}

				ButtonType buttonYes = new ButtonType("Yes");
				ButtonType buttonNo = new ButtonType("No");
				Optional<ButtonType> request = CommonMethodsHandler
						.getInstance().getNewAlert(AlertType.INFORMATION, "Exam saved",
								"File was uploaded successfuly!", "Upload another file?", buttonYes, buttonNo)
						.showAndWait();

				if (request.get() == buttonYes) {
					TeacherMenuBarController.mainPaneBp
							.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditExam"));
				} else {
					ClientUI.mainScene
							.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherMenu.fxml")));
				}

			} else {
				CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Negative/zero time",
						"Must to choose positive value for allocated time").showAndWait();
			}
		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing allocated time",
					"Must to enter value (allocated time)").showAndWait();
		}
	}

	@FXML
	void btnPressEditSelectedExam(ActionEvent event) {

		System.out.println("TeacherEditExam::btnPressEditSelectedExam");

		IExam exam = examsTv.getSelectionModel().getSelectedItem();
		if (exam != null) {
			rightPanelAp.setDisable(false);
			leftPanelAp.setDisable(true);
			ExamType = exam.getType();

			circle.setStyle("-fx-fill: #f4f4f4;");
			allocatedTimeTf.setText(exam.getAllocatedTime());
			ExamID = exam.getExamID();

			if (exam.getType().equals("C"))
				uploadAp.setDisable(true);
			else  // case "M"
				commentsAp.setDisable(true);
				

			//////
			// set text in student&teacher comments(with sql query??)
			//////

			allocatedTimeTf.setText(exam.getAllocatedTime());
			ExamID = exam.getExamID();

		} else {
			circle.setStyle("-fx-fill: #f4f4f4;");
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing edit exam",
					"Must to select an exam to edit").showAndWait();
		}
	}

	@FXML
	void btnPressChangeExam(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressChangeExam");

		leftPanelAp.setDisable(false);
		rightPanelAp.setDisable(true);
		uploadAp.setDisable(false);
		commentsAp.setDisable(false);

	}

	@FXML
	void btnPressDeleteExam(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressDeleteExam");
		circle.setStyle("-fx-fill: #f4f4f4;");

		IExam exam = examsTv.getSelectionModel().getSelectedItem();
		if (exam != null) {
			ClientUI.chat.accept(new String[] { "RemoveExamFromDatabase", exam.getExamID(), ChatClient.user.getUsername() });
			examsTv.getItems().remove(examsTv.getSelectionModel().getSelectedItem());
			circle.setStyle("-fx-fill: GREEN;");

		} else {
			circle.setStyle("-fx-fill: RED;");
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing delete exam",
					"Must to select an exam to delete").showAndWait();
		}

	}

	@FXML
	void btnPressSearch(ActionEvent event) {
		System.out.println("TeacherEditManualExam::btnPressSearch");

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
		} catch (RuntimeException e) {
			e.printStackTrace();
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

	public void setExamTableView(List<IExam> examsList) {
		examList = examsList;
		examObservableList.clear();
		examObservableList.addAll(examsList);
		System.out.println("examObservableList " + examObservableList);
		examsTv.setItems(examObservableList);
	}

	public void successfulEditExam(String Msg) {
		msg = Msg;
	}
}
