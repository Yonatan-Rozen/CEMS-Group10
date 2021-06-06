package gui.client.teacher;

import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.ResourceBundle;

import client.ChatClient;
import client.ClientUI;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.control.Button;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TableCell;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import javafx.util.Callback;
import logic.question.Question;

public class TeacherCreateManualExamController implements Initializable {
	public static TeacherCreateManualExamController tcmeController;

	// JAVAFX INSTNCES ******************************************************

	@FXML
	private AnchorPane sbTopPanelAp;

	@FXML
	private Button sbContinue1Btn;

	@FXML
	private AnchorPane sbBotPanelAp;

	@FXML
	private ChoiceBox<String> sbChooseCourseCb;

	@FXML
	private ChoiceBox<String> sbExamBankCb;

	@FXML
	private TableColumn<Question, Void> sbRemoveFromExamTc;

	@FXML
	private Button sbChangeBankBtn;

	@FXML
	private Button sbContinue2Btn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane topPanelAp;
	private static ChoiceBox<String> examBankCb;
	private static Button continue1Btn;
	private static AnchorPane botPanelAp;
	private static ChoiceBox<String> chooseCourseCb;
	private static Button changeBankBtn;
	private static Button continue2Btn;

	// STATIC INSTANCES *****************************************************
	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");
	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
	private static List<Question> questionList;
	ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
	private static String msg;

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

		continue1Btn = sbContinue1Btn;
		botPanelAp = sbBotPanelAp;
		botPanelAp.setDisable(true);
		chooseCourseCb = sbChooseCourseCb;
		changeBankBtn = sbChangeBankBtn;
		continue2Btn = sbContinue2Btn;

		if (bankList.size() == 1) { // add banks only once
			ClientUI.chat.accept(new String[] { "GetBanks", ChatClient.user.getUsername() });
		}
	}

	// ACTION METHODS *******************************************************
//	@FXML
//	void btnPressCancelCreation(ActionEvent event) {
//		System.out.println("TeacherCreateExam::btnPressCancelCreation");
//	}

	@FXML
	void btnPressChangeBank(ActionEvent event) {
		System.out.println("TeacherCreateManualExam::btnPressChangeBank");
		sbTopPanelAp.setDisable(false);
		sbBotPanelAp.setDisable(true);
		examBankCb.setValue("----------");
	}

	@FXML
	void btnPressContinue1(ActionEvent event) {
		System.out.println("TeacherCreateManualExam::btnPressContinue1");
		CourseList.clear(); // clear list

		sbTopPanelAp.setDisable(true);
		sbBotPanelAp.setDisable(false);
//		chooseCourseCb.setValue("----------");
		ClientUI.chat
				.accept(new String[] { "GetCourseBySubject", examBankCb.getValue(), ChatClient.user.getUsername() });

		chooseCourseCb.setItems(CourseList);
	}

	@FXML
	void btnPressContinue2(ActionEvent event) throws Exception {
		System.out.println("TeacherCreateManualExam::btnPressContinue2");
		String correctAnswer, author = ChatClient.user.getFirstname() + " " + ChatClient.user.getLastname();

		ClientUI.chat.accept(new String[] { "btnPressContinue2CreateManualExam",chooseCourseCb.getValue(),examBankCb.getValue(),author, ChatClient.user.getUsername() });
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherExamType.fxml")));
	}
	
}
