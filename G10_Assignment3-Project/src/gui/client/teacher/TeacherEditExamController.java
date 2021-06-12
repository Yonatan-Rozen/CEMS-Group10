package gui.client.teacher;

import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import common.CommonMethodsHandler;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Alert.AlertType;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
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
	private TextArea sbStudentCommentsTa;

	@FXML
	private TextArea sbTeacherCommentsTa1;

	@FXML
	private TextArea sbAllocatedTimeTa;

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
	private ChoiceBox<String> sbChooseBankCb;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;

	private static TableView<IExam> examsTv;
	private static TableColumn<IExam, String> examIDTc;
	private static TableColumn<IExam, String> courseIDTc;
	private static AnchorPane leftPanelAp;
	private static AnchorPane topPanelAp;
	private static AnchorPane rightPanelAp;
	private static ChoiceBox<String> chooseBankCb;
	private static Button continue1Btn;
	private static Button deleteExamBtn1;
	private static Button editSelectedExamBtn;
	private static Button changeBankBtn;
	private static Button finishBtn;
	private static Button changeExamBtn;
	private static TextArea studentCommentsTa;
	private static TextArea teacherCommentsTa1;
	private static TextArea allocatedTimeTa;

	// STATIC INSTANCES *****************************************************
	private static String ExamID;
	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");
	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
	private static List<IExam> examList;
	ObservableList<IExam> examObservableList = FXCollections.observableArrayList();
	private static String msg;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teeController = new TeacherEditExamController();

		examsTv = sbExamsTv;
		examIDTc = sbExamIDTc;
		courseIDTc = sbcourseIDTc;
		leftPanelAp = sbLeftPanelAp;
		rightPanelAp = sbRightPanelAp;
		topPanelAp = sbTopPanelAp;
		chooseBankCb = sbChooseBankCb;
		continue1Btn = sbContinue1Btn;
		deleteExamBtn1 = sbDeleteExamBtn1;
		editSelectedExamBtn = sbEditSelectedExamBtn;
		changeBankBtn = sbChangeBankBtn;
		finishBtn = sbFinishBtn;
		changeExamBtn = sbChangeExamBtn;
		studentCommentsTa = sbStudentCommentsTa;
		teacherCommentsTa1 = sbTeacherCommentsTa1;
		allocatedTimeTa = sbAllocatedTimeTa;
		teacherCommentsTa1.setWrapText(true);
		studentCommentsTa.setWrapText(true);
		
		leftPanelAp.setDisable(true);
		rightPanelAp.setDisable(true);
		chooseBankCb.setValue("----------");
		chooseBankCb.setItems(bankList);

		if (bankList.size() == 1) { // add banks only once
			ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername(), "3" });
		}
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressContinue1");
		if (chooseBankCb.getValue() != "----------") {

			topPanelAp.setDisable(true);
			leftPanelAp.setDisable(false);

			CourseList.clear(); // clear list

			ClientUI.chat.accept(new String[] { "btnPressShowExamsBySubject", chooseBankCb.getValue(),
					ChatClient.user.getUsername() });

			examIDTc.setCellValueFactory(new PropertyValueFactory<IExam, String>("examID"));
			examIDTc.setStyle("-fx-alignment: CENTER; -fx-font-weight: Bold;");

			///// this is not bankID , the result is courseID
			courseIDTc.setCellValueFactory(new PropertyValueFactory<IExam, String>("courseID"));
			courseIDTc.setStyle("-fx-alignment: CENTER; -fx-font-weight: Bold;");

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

	}

	@FXML
	void btnPressFinish(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressFinish");

		String insertTime = allocatedTimeTa.getText();
		if (!insertTime.equals("Enter Time!")) {
			if (Integer.parseInt(insertTime) > 0) {
				System.out.println("check info = " + ExamID + studentCommentsTa.getText() + teacherCommentsTa1.getText()
						+ allocatedTimeTa.getText());
				ClientUI.chat.accept(new String[] { "btnPressFinishCreateComputerizedExam", ExamID,
						studentCommentsTa.getText(), teacherCommentsTa1.getText(), allocatedTimeTa.getText(), "2",
						ChatClient.user.getUsername() });

				TeacherMenuBarController.mainPaneBp
						.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherEditExam"));
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
		allocatedTimeTa.setText("Enter Time!");
		studentCommentsTa.setText("TextText");
		teacherCommentsTa1.setText(
				"text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text text ");
		IExam exam = examsTv.getSelectionModel().getSelectedItem();
		if (exam != null) {
			ExamID = exam.getExamID();
			rightPanelAp.setDisable(false);
			leftPanelAp.setDisable(true);
		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing edit exam",
					"Must to select an exam to edit").showAndWait();
		}
	}

	@FXML
	void btnPressChangeExam(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressChangeExam");

		leftPanelAp.setDisable(false);
		rightPanelAp.setDisable(true);

	}

	@FXML
	void btnPressDeleteExam(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressDeleteExam");

		IExam exam = examsTv.getSelectionModel().getSelectedItem();
		if (exam != null) {
			ClientUI.chat
					.accept(new String[] { "RemoveExamFromDatabase", exam.getExamID(), ChatClient.user.getUsername() });
			examsTv.getItems().remove(examsTv.getSelectionModel().getSelectedItem());
		} else {
			CommonMethodsHandler.getInstance().getNewAlert(AlertType.ERROR, "Error message", "Missing delete exam",
					"Must to select an exam to delete").showAndWait();
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
