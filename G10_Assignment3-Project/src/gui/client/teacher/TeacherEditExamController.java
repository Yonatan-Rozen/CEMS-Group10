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
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;
import logic.exam.Exam;
import logic.question.Question;

public class TeacherEditExamController implements Initializable {
	public static TeacherEditExamController teeController;

	// JAVAFX INSTNCES ******************************************************
	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private Button sbEditSelectedExamBtn;

	@FXML
	private Button sbChangeCourseBtn;

	@FXML
	private TableView<Exam> sbExamsTv;

	@FXML
	private TableColumn<Exam, String> sbExamIDTc;

	@FXML
	private Button sbPreviewExamBtn;

	@FXML
	private AnchorPane sbMidPanelAp;

	@FXML
	private ChoiceBox<String> sbChooseCourseCb;

	@FXML
	private Button sbContinue2Btn;

	@FXML
	private Button sbChangeBankBtn;

	@FXML
	private Button sbDeleteExamBtn1;

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private ChoiceBox<String> sbChooseBankCb;

	@FXML
	private Button sbContinue1Btn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static Button editSelectedExamBtn;
	private static Button changeCourseBtn;
	private static TableView<Exam> examsTv;
	private static TableColumn<Exam, String> examIDTc;
	private static Button previewExamBtn;
	private static AnchorPane midPanelAp;
	private static ChoiceBox<String> chooseCourseCb;
	private static Button continue2Btn;
	private static Button changeBankBtn;
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> chooseBankCb;
	private static Button continue1Btn;
	private static Button deleteExamBtn1;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");
	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
	private static List<Exam> examList;
	ObservableList<Exam> examObservableList = FXCollections.observableArrayList();
	private static String msg;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		teeController = new TeacherEditExamController();

		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		editSelectedExamBtn = sbEditSelectedExamBtn;
		changeCourseBtn = sbChangeCourseBtn;
		examsTv = sbExamsTv;
		examIDTc = sbExamIDTc;
		previewExamBtn = sbPreviewExamBtn;
		midPanelAp = sbMidPanelAp;
		midPanelAp.setDisable(true);
		chooseCourseCb = sbChooseCourseCb;
		continue2Btn = sbContinue2Btn;
		changeBankBtn = sbChangeBankBtn;
		topPanelAp = sbTopPanelAp;
		chooseBankCb = sbChooseBankCb;
		continue1Btn = sbContinue1Btn;
		deleteExamBtn1 = sbDeleteExamBtn1;
		chooseBankCb.setValue("----------");
		chooseBankCb.setItems(bankList);

		if (bankList.size() == 1) { // add banks only once
			ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername(), "3" });
		}
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressContinue1");
		topPanelAp.setDisable(true);
		midPanelAp.setDisable(false);

		CourseList.clear(); // clear list

		ClientUI.chat.accept(
				new String[] { "GetCourseBySubject", chooseBankCb.getValue(), ChatClient.user.getUsername(), "3" });

		chooseCourseCb.setItems(CourseList);

		ClientUI.chat.accept(new String[] { "btnPressShowExamsBySubject", chooseBankCb.getValue(), "2",
				ChatClient.user.getUsername() });

		sbExamIDTc.setCellValueFactory(new PropertyValueFactory<Exam, String>("examID"));
		sbExamIDTc.setStyle("-fx-alignment: CENTER; -fx-font-weight: Bold;");

	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressChangeBank");
		topPanelAp.setDisable(false);
		midPanelAp.setDisable(true);
		
		examsTv.getItems().removeAll(examList); //empty list
	}

	@FXML
	void btnPressContinue2(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressContinue2");
		midPanelAp.setDisable(true);
		botPanelAp.setDisable(false);
	}

	@FXML
	void btnPressChangeCourse(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressChangeCourse");
		midPanelAp.setDisable(false);
		botPanelAp.setDisable(true);
	}

	@FXML
	void btnPressEditSelectedExam(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressEditSelectedExam");

		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherMenu"));

	}

	@FXML
	void btnPressPreviewExam(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressPreviewExam");
	}

	@FXML
	void btnPressDeleteExam(ActionEvent event) {
		System.out.println("TeacherEditExam::btnPressDeleteExam");
		
		Exam exam = examsTv.getSelectionModel().getSelectedItem();
		CommonMethodsHandler methodsHandler = CommonMethodsHandler.getInstance();
		
		ClientUI.chat.accept(new String[] {"RemoveExamFromDatabase", exam.getExamID(), ChatClient.user.getUsername()});
		examsTv.getItems().remove(examsTv.getSelectionModel().getSelectedItem());
		
		TeacherMenuBarController.mainPaneBp
				.setCenter(CommonMethodsHandler.getInstance().getPane("teacher", "TeacherMenu"));

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

	public void setExamTableView(List<Exam> exams) {
		examList = exams;
		ObservableList<Exam> examObservableList = FXCollections.observableArrayList();
		examObservableList.addAll(exams);
		examsTv.setItems(examObservableList);
	}

//	public void successfulCreateExam(String Msg) {
//		msg = Msg;
//	}

}
