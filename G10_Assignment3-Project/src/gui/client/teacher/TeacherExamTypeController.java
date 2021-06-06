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
import jdk.jshell.spi.ExecutionControl.ExecutionControlException;
import logic.question.Question;

public class TeacherExamTypeController implements Initializable {
	public static TeacherExamTypeController tetController;

	// JAVAFX INSTNCES ******************************************************

    @FXML
    private AnchorPane sbBotPanelAp;

    @FXML
    private Button sbComputerizedBtn;

    @FXML
    private Button sbManualBtn;

	// STATIC JAVAFX INSTANCES **********************************************
	private static AnchorPane botPanelAp;
	private static Button computerizedBtn;
	private static Button manualBtn;

	// STATIC INSTANCES *****************************************************
//	public static ObservableList<String> bankList = FXCollections.observableArrayList("----------");
//	public static ObservableList<String> CourseList = FXCollections.observableArrayList("----------");
//	private static List<Question> questionList;
//	ObservableList<Question> questionObservableList = FXCollections.observableArrayList();
//	private static String msg;

	// INITIALIZE METHOD ****************************************************
	@Override
	public void initialize(URL location, ResourceBundle resources) {
		tetController = new TeacherExamTypeController();

		/**** First panel ****/
		botPanelAp = sbBotPanelAp;
		computerizedBtn = sbComputerizedBtn;
		manualBtn = sbManualBtn;
	}

	// ACTION METHODS *******************************************************
//	@FXML
//	void btnPressCancelCreation(ActionEvent event) {
//		System.out.println("TeacherCreateExam::btnPressCancelCreation");
//	}

    @FXML
    void btnPressComputerized(ActionEvent event) throws Exception{
		System.out.println("TeacherExamType::btnPressComputerized");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherCreateExam.fxml")));
    }

    @FXML
    void btnPressManual(ActionEvent event) throws Exception{
		System.out.println("TeacherExamType::btnPressManual");
		ClientUI.mainScene.setRoot(FXMLLoader.load(getClass().getResource("/gui/client/teacher/TeacherCreateManualExam.fxml")));
    }
}